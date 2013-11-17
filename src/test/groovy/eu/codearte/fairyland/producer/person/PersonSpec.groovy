package eu.codearte.fairyland.producer.person

import eu.codearte.fairyland.Fairy
import org.joda.time.DateTime
import spock.lang.Ignore
import spock.lang.Specification

import static eu.codearte.fairyland.producer.person.PersonProperties.*

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
		def person = Fairy.create().person(female())
		then:
		person.isFemale()
	}

	def "should create male"() {
		when:
		def person = Fairy.create().person(male())
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
		def person = Fairy.create().person(telephoneFormat("###--###"))
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

	def "should create company email"() {
		given:
		def fairy = Fairy.create()

		when:
		def person = fairy.person()

		then:
		person.companyEmail()
	}

	def "should create person for company"() {
		given:
		def fairy = Fairy.create()
		def company = fairy.company()

		when:
		def person = fairy.person(withCompany(company))

		then:
		person.companyEmail() contains company.domain()
	}
}
