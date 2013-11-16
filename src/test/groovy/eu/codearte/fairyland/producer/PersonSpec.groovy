package eu.codearte.fairyland.producer

import eu.codearte.fairyland.Fairy
import eu.codearte.fairyland.producer.person.Person
import eu.codearte.fairyland.producer.person.PersonProperties
import org.joda.time.DateTime
import spock.lang.Ignore
import spock.lang.Specification

class PersonSpec extends Specification {

	def "should instantiate PersonProducer producer with person"() {
		when:
		def person = Fairy.create().person()
		then:
		person instanceof Person
	}

	def "should be sure that fullName is proper"() {
		when:
		def person = Fairy.create().person()
		then:
		person.email()
		"${person.firstName()} ${person.lastName()}" == person.fullName()
	}

	@Ignore def "second generated name should be different"() {
		setup:
		def fairy = Fairy.create()
		expect:
		fairy.person().firstName() != fairy.person().firstName()
	}

	def "should be sure that data exists"() {
		when:
		def person = Fairy.create().person()
		then:
		person.firstName()
		person.lastName()
		person.fullName()
		person.email()
		person.isMale() || person.isFemale()
		person.nationalIdentificationNumber()
		person.nationalIdentityCardNumber()
	}

	def "should create female"() {
		when:
		def person = Fairy.create().person(PersonProperties.female())
		then:
		person.isFemale()
	}

	def "should create male"() {
		when:
		def person = Fairy.create().person(PersonProperties.male())
		then:
		person.isMale()
	}

	def "should create telephone number"() {
		when:
		def person = Fairy.create().person()
		then:
		person.telephoneNumber()
	}

	def "should create telephone number in defined format"() {
		when:
		def person = Fairy.create().person(PersonProperties.telephoneFormat("###--###"))
		then:
		person.telephoneNumber() ==~ /\d\d\d--\d\d\d/
	}

	def "should create birth date"() {
		when:
		def person = Fairy.create().person()
		then:
		person.dateOfBirth().isBefore(DateTime.now())
	}

	def "should create age"() {
		when:
		def person = Fairy.create().person()
		then:
		person.age()
	}
}
