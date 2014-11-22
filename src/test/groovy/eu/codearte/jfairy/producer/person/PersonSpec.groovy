package eu.codearte.jfairy.producer.person

import org.apache.commons.validator.routines.EmailValidator
import eu.codearte.jfairy.Bootstrap
import eu.codearte.jfairy.Fairy
import org.joda.time.DateTime
import spock.lang.Ignore
import spock.lang.Specification

import static PersonProperties.female
import static PersonProperties.male
import static PersonProperties.telephoneFormat

class PersonSpec extends Specification {

	def emailValidator = EmailValidator.getInstance()
	def Fairy fairy = Bootstrap.create()

	def setup() {
		Bootstrap.create()
	}

	def "should instantiate PersonProducer producer with person"() {
		when:
			def person = fairy.person()
		then:
			person instanceof Person
	}

	def "should be sure that fullName is proper"() {
		when:
			def person = fairy.person()
		then:
			"${person.firstName()} ${person.lastName()}" == person.fullName()
	}

	@Ignore
	def "second generated name should be different"() {
		setup:
			def fairy = fairy
		expect:
			fairy.person().firstName() != fairy.person().firstName()
	}

	def "should be sure that data exists"() {
		when:
			def person = fairy.person()
		then:
			person.firstName()
			person.lastName()
			person.fullName()
			person.email()
			person.isMale() || person.isFemale()
			person.nationalIdentificationNumber()
			person.nationalIdentityCardNumber()

			emailValidator.isValid(person.email())
	}

	def "should create female"() {
		when:
			def person = fairy.person(female())
		then:
			person.isFemale()
	}

	def "should create male"() {
		when:
			def person = fairy.person(male())
		then:
			person.isMale()
	}

	def "should create telephone number"() {
		when:
			def person = fairy.person()
		then:
			person.telephoneNumber()
	}

	def "should create telephone number in defined format"() {
		when:
			def person = fairy.person(telephoneFormat("###--###"))
		then:
			person.telephoneNumber() ==~ /\d\d\d--\d\d\d/
	}

	def "should create birth date"() {
		when:
			def person = fairy.person()
		then:
			person.dateOfBirth().isBefore(DateTime.now())
	}

	def "should create age"() {
		when:
			def person = fairy.person()
		then:
			person.age()
	}

	def "should create company email"() {
		given:
			def fairy = fairy

		when:
			def person = fairy.person()

		then:
			person.companyEmail()
			emailValidator.isValid(person.companyEmail())
	}

	def "should create address"() {
		given:
			def person = fairy.person()
		when:
			def address = person.getAddress()
		then:
			address
	}

	def "should create address postal code"() {
		given:
			def person = fairy.person()
		when:
			def postalCode = person.getAddress().postalCode
		then:
			postalCode
	}

	def "should create address city"() {
		given:
			def person = fairy.person()
		when:
			def city = person.getAddress().city
		then:
			city
	}

	def "Should generate middle name only sometimes"() {
		given:
			def persons = []
			(1..100).each { persons.add(fairy.person()) }
		when:
			def allWithoutMiddleName = persons.findAll { p -> p.middleName().isEmpty() }
			def allWithMiddleName = persons.findAll { p -> !p.middleName().isEmpty() }
		then:
			allWithoutMiddleName.size() > 0
			allWithMiddleName.size() > 0
	}


}
