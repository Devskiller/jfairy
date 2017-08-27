package io.codearte.jfairy.producer.company.locale.pl

import io.codearte.jfairy.producer.BaseProducer
import io.codearte.jfairy.producer.RandomGenerator
import org.apache.commons.math3.random.RandomDataGenerator
import spock.lang.Specification
import spock.lang.Unroll

class PlVATIdentificationNumberProviderSpec extends Specification {

	private BaseProducer baseProducer = new BaseProducer(new RandomGenerator())
	private PlVATIdentificationNumberProvider generator = new PlVATIdentificationNumberProvider(baseProducer)

	@Unroll
	def "Should validate #vatIdentificationNumber as #valid"() {

		expect:
			generator.isValid(vatIdentificationNumber) == valid

		where:
			vatIdentificationNumber | valid
			"2684494529"            | true
			"1234567890"            | false
			"0000000000"            | true
			"18947440810"           | false
	}

	def "Should always generate proper vatIdentificationNumber"() {
		expect:
			generator.isValid(generator.get())
		where:
			i << (1..100)
	}

}
