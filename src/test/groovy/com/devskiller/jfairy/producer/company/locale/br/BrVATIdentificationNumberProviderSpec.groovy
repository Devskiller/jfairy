package com.devskiller.jfairy.producer.company.locale.br

import com.devskiller.jfairy.producer.company.locale.es.EsVATIdentificationNumberProvider
import spock.lang.Specification
import spock.lang.Unroll

/**
 * @author Nelson Rodrigues da Silva Júnior
 * @since 15.10.2020
 */
class BrVATIdentificationNumberProviderSpec extends Specification {

	private BrVATIdentificationNumberProvider generator = new BrVATIdentificationNumberProvider()

	@Unroll
	def "Should validate #vatIdentificationNumber as #valid"() {

		expect:
			generator.isValid(vatIdentificationNumber) == valid

		where:
			vatIdentificationNumber | valid
			"03.061.384/0001-40"	| true
			"03.232.334/0001-40"    | false
			"95.659.509/0001-57"    | true
			"67.706.334/0001-10"    | false
	}

	def "Should always generate proper nip"() {
		expect:
			generator.isValid(generator.get())
		where:
			i << (1..100)
	}

}
