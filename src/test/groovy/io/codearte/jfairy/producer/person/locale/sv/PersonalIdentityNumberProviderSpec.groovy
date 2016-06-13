package io.codearte.jfairy.producer.person.locale.sv

import io.codearte.jfairy.producer.BaseProducer
import io.codearte.jfairy.producer.DateProducer
import io.codearte.jfairy.producer.person.Person
import org.joda.time.DateTime
import spock.lang.Specification
import spock.lang.Unroll

import static io.codearte.jfairy.producer.person.locale.sv.PersonalIdentityNumberProperties.dateOfBirth
import static io.codearte.jfairy.producer.person.locale.sv.PersonalIdentityNumberProperties.sex
import static io.codearte.jfairy.producer.person.locale.sv.PersonalIdentityNumberProvider.isValid

class PersonalIdentityNumberProviderSpec extends Specification {

	def randomGenerator = Mock(BaseProducer)
	def dateGenerator = Mock(DateProducer)

	@Unroll
	def "should validate #personalIdentityNumber as #valid"() {

		expect:
			isValid(personalIdentityNumber) == valid

		where:
			personalIdentityNumber | valid
			"370612-1450"           | true
			"370612-9529"           | true
			"640401-1550"           | true
			"690509-3966"           | true
			"870727-2441"           | false
			"880418-9043"           | false
			"610219-6694"           | false
	}

	@Unroll
	def "should generate good personalIdentityNumber"() {

		expect:
			def PersonalIdentityNumberProvider generator = new PersonalIdentityNumberProvider(dateGenerator, randomGenerator,
					dateOfBirth(DateTime.parse(date)),
					sex(Person.Sex.MALE))

			def personalIdentityNumber = generator.get()

			personalIdentityNumber.getValue().startsWith(prefix)
			isValid(personalIdentityNumber.getValue())

		where:
			date         | prefix
			"1999-01-01" | "990101"
			"1950-12-31" | "501231"
			"1910-01-31" | "100131"
			"2100-11-01" | "001101"
			"2199-01-22" | "990122"
	}
}
