package io.codearte.jfairy.producer.company.locale.sv

import io.codearte.jfairy.producer.BaseProducer
import io.codearte.jfairy.producer.company.locale.pl.NIPProvider
import spock.lang.Specification
import spock.lang.Unroll

class SvVATIdentificationNumberProviderSpec extends Specification {

	def baseProducer = new BaseProducer(new Random())
	def generator = new NIPProvider(baseProducer)

	@Unroll
	def "Should validate #nip as #valid"() {

		expect:
			generator.isValid(nip) == valid

		where:
			nip           | valid
			"2684494529"  | true
			"1234567890"  | false
			"0000000000"  | true
			"18947440810" | false
	}

	def "Should always generate proper nip"() {
		expect:
			generator.isValid(generator.get())
		where:
			i << (1..100)
	}

}
