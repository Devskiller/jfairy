package io.codearte.jfairy.producer.person

import io.codearte.jfairy.Bootstrap
import io.codearte.jfairy.Fairy
import org.apache.commons.validator.routines.EmailValidator
import org.joda.time.DateTime
import org.joda.time.Period
import spock.lang.Ignore
import spock.lang.Specification

import static io.codearte.jfairy.producer.person.PersonProperties.ageBetween
import static io.codearte.jfairy.producer.person.PersonProperties.female
import static io.codearte.jfairy.producer.person.PersonProperties.male
import static io.codearte.jfairy.producer.person.PersonProperties.maxAge
import static io.codearte.jfairy.producer.person.PersonProperties.minAge
import static io.codearte.jfairy.producer.person.PersonProperties.telephoneFormat

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
			"${person.firstName} ${person.lastName}" == person.fullName
	}

	@Ignore
	def "second generated name should be different"() {
		setup:
			def fairy = fairy
		expect:
			fairy.person().firstName != fairy.person().firstName
	}

	def "should be sure that data exists"() {
		when:
			def person = fairy.person()
		then:
			person.firstName
			person.lastName
			person.fullName
			person.email
			person.male || person.female
			person.nationalIdentityCardNumber
			person.address

			emailValidator.isValid(person.email)
	}

	def "should create female"() {
		when:
			def person = fairy.person(female())
		then:
			person.female
	}

	def "should create male"() {
		when:
			def person = fairy.person(male())
		then:
			person.male
	}

	def "should create person younger than 3 years"() {
		when:
			def person = fairy.person(maxAge(3))
		then:
			person.age <= 3
	}

	def "should create person older than 98 years"() {
		when:
			def person = fairy.person(minAge(99))
		then:
			person.age > 98
	}

	def "should create person older than 10 years and younger than 10 years"() {
		when:
			Person person = fairy.person(minAge(10), maxAge(10))
		then:
			person.age == 10
	}

	def "birth date and age should be related"() {
		when:
			def person = fairy.person(ageBetween(32, 32))
		then:
			person.age == 32
			def period = new Period(person.dateOfBirth.millis, DateTime.now().millis)
			period.years == 32
	}

	def "should create telephone number"() {
		when:
			def person = fairy.person()
		then:
			person.telephoneNumber
	}

	def "should create telephone number in defined format"() {
		when:
			def person = fairy.person(telephoneFormat("###--###"))
		then:
			person.telephoneNumber ==~ /\d\d\d--\d\d\d/
	}

	def "should create birth date"() {
		when:
			def person = fairy.person()
		then:
			person.dateOfBirth.isBefore(DateTime.now())
	}

	def "should create age"() {
		when:
			def person = fairy.person()
		then:
			person.age
	}

	def "should create company email"() {
		given:
			def fairy = fairy

		when:
			def person = fairy.person()

		then:
			person.companyEmail
			emailValidator.isValid(person.companyEmail)
	}

	def "should create address"() {
		given:
			def person = fairy.person()
		when:
			def address = person.address
		then:
			address
	}

	def "should create address postal code"() {
		given:
			def person = fairy.person()
		when:
			def postalCode = person.address.postalCode
		then:
			postalCode
	}

	def "should create address city"() {
		given:
			def person = fairy.person()
		when:
			def city = person.address.city
		then:
			city
	}

	def "should create street address"() {
		given:
			def person = fairy.person()
		when:
			def address = person.address
		then:
			address.getStreet()
			address.getStreetNumber().isNumber()
			(address.getApartmentNumber().isNumber() || address.getApartmentNumber() == "")
	}

	def "should generate middle name only sometimes"() {
		given:
			def persons = []
			(1..100).each { persons.add(fairy.person()) }
		when:
			def allWithoutMiddleName = persons.findAll { p -> p.middleName.isEmpty() }
			def allWithMiddleName = persons.findAll { p -> !p.middleName.isEmpty() }
		then:
			allWithoutMiddleName.size() > 0
			allWithMiddleName.size() > 0
	}

	def "should generate apartment number only sometimes"() {
		given:
			def persons = []
			(1..50).each { persons.add(fairy.person()) }
		when:
			def allWithoutApartmentNumber = persons.findAll { p -> p.address.getApartmentNumber().isEmpty() }
			def allWithApartmentNumber = persons.findAll { p -> !p.address.getApartmentNumber().isEmpty() }
		then:
			allWithoutApartmentNumber.size() > 0
			allWithApartmentNumber.size() > 0
	}

	def "should create passport number"() {
		given:
			def person = fairy.person()
		expect:
			person.passportNumber
	}


}
