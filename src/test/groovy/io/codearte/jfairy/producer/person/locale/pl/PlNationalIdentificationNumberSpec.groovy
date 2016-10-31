package io.codearte.jfairy.producer.person.locale.pl

import io.codearte.jfairy.producer.BaseProducer
import io.codearte.jfairy.producer.DateProducer
import io.codearte.jfairy.producer.person.NationalIdentificationNumber
import io.codearte.jfairy.producer.person.Person
import org.joda.time.DateTime
import spock.lang.Specification
import spock.lang.Unroll

import static io.codearte.jfairy.producer.person.NationalIdentificationNumberProperties.dateOfBirth
import static io.codearte.jfairy.producer.person.NationalIdentificationNumberProperties.sex
import static PlNationalIdentificationNumberProvider.isValid

class PlNationalIdentificationNumberSpec extends Specification {

	BaseProducer randomGenerator = Mock(BaseProducer)
	DateProducer dateGenerator = Mock(DateProducer)

	@Unroll
	def "should validate #nationalIdentificationNumber as #valid"() {

		expect:
			isValid(nationalIdentificationNumber) == valid

		where:
			nationalIdentificationNumber | valid
			"13881939620"                | true
			"00421297469"                | true
			"99010147513"                | true
			"44051401359"                | true
			"44051401358"                | false
			"44051401458"                | true
			"44052401458"                | false
	}

	@Unroll
	def "should generate good nationalIdentificationNumber"() {

		expect:
			PlNationalIdentificationNumberProvider generator = new PlNationalIdentificationNumberProvider(dateGenerator,
                    randomGenerator, dateOfBirth(DateTime.parse(date)), sex(Person.Sex.MALE))

			NationalIdentificationNumber nationalIdentificationNumber = generator.get()

			nationalIdentificationNumber.getValue().startsWith(prefix)
			isValid(nationalIdentificationNumber.getValue())

		where:
			date         | prefix
			"1999-01-01" | "990101"
			"1900-12-31" | "001231"
			"1800-01-31" | "008131"
			"2100-11-01" | "005101"
			"2199-01-11" | "994111"
	}
}
