package io.codearte.jfairy

import spock.lang.Specification

class FairySpec extends Specification {

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

}
