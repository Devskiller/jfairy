package io.codearte.jfairy

import com.google.inject.Provider
import io.codearte.jfairy.data.DataMaster
import io.codearte.jfairy.producer.person.Person
import spock.lang.Specification

class FairySpec extends Specification {

	private static final String CUSTOM_STRING = 'Custom Data Master'

	DataMaster customDataMaster = Stub(DataMaster) {
		getString(_ as String) >> CUSTOM_STRING
		getStringList(_ as String) >> Arrays.asList(CUSTOM_STRING)
		getValuesOfType(_ as String, _ as String) >> CUSTOM_STRING
		getRandomValue(_ as String) >> CUSTOM_STRING
	}

	Provider<DataMaster> customDataMasterProvider = Stub(Provider) {
		get() >> customDataMaster
	}


	def "Second person should be different without fairy instance"() {

		given:
			def person = Fairy.create().person();
		when:
			person = Fairy.create().person();
		then:
			person.fullName() != old(person.fullName())
	}

	def "Second person should be different with one fairy"() {

		given:
			def fairy = Fairy.create()
			def person = fairy.person();
		when:
			person = fairy.person();
		then:
			person.fullName() != old(person.fullName())
	}

	def "Second person should be the same with the same random seed"() {

		given:
			def firstFairy = Fairy.builder().withRandomSeed(10).build()
			def secondFairy = Fairy.builder().withRandomSeed(10).build()

			def firstPerson = firstFairy.person()
			def secondPerson = secondFairy.person()
			def thirdPerson = firstFairy.person()
			def fourthPerson = secondFairy.person()

		expect:
			firstPerson.fullName().equals(secondPerson.fullName())
			thirdPerson.fullName().equals(fourthPerson.fullName())

			!firstPerson.fullName().equals(thirdPerson.fullName())
	}

	def "Second person should be different with different random seeds"() {

		given:
			def firstFairy = Fairy.builder().withRandomSeed(10).build()
			def secondFairy = Fairy.builder().withRandomSeed(20).build()

			def firstPerson = firstFairy.person()
			def secondPerson = secondFairy.person();

		expect:
			!firstPerson.fullName().equals(secondPerson.fullName())
	}

	def "should use default DataMaster when custom not provided"() {
		given:
			Fairy fairy = Fairy.create();
		when:
			Person samplePerson = fairy.person()

		then:
			samplePerson.firstName() && samplePerson.firstName() != CUSTOM_STRING

	}

	def "should use custom DataMaster when provided"() {
		given:
			Fairy fairy = Fairy.create(customDataMasterProvider, Locale.forLanguageTag("EN"));

		when:
			Person samplePerson = fairy.person()

		then:
			samplePerson.firstName() == CUSTOM_STRING

	}
}
