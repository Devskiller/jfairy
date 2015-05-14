package io.codearte.jfairy.producer.company.locale.es

import spock.lang.Specification
import spock.lang.Unroll

/**
 * @author graux
 * @since 24/06/2015
 */
class CIFProviderSpec extends Specification {

	def generator = new CIFProvider()

	@Unroll
	def "Should validate #nip as #valid"() {

		expect:
			generator.isValid(nip) == valid

		where:
			nip         | valid
			"L17761800" | true
			"31231231X" | false
			"G2301257H" | true
			"AB023023C" | false
	}

	def "Should always generate proper nip"() {
		expect:
			generator.isValid(generator.get())
		where:
			i << (1..100)
	}

}
