/*
 * Copyright (c) 2013 Codearte and authors
 */

package com.devskiller.jfairy.producer.person.locale.br

import com.devskiller.jfairy.Fairy
import com.devskiller.jfairy.producer.company.locale.br.BrVATIdentificationNumberProvider
import spock.lang.Specification
import spock.lang.Unroll

/**
 * @author Nelson Rodrigues da Silva Júnior
 * @since 15.10.2020
 */
class BrNationalIdentityCardNumberProviderSpec extends Specification {

	private Fairy fairy;
	private String brNationalIdentityCardNumber
	private final int dniLength = 14
	private BrNationalIdentityCardNumberProvider generator = new BrNationalIdentityCardNumberProvider()

	def setup() {
		fairy = Fairy.create(Locale.forLanguageTag("br"))
		brNationalIdentityCardNumber = fairy.person().nationalIdentityCardNumber
	}

	def "should generate number with 14 characters"() {
		expect:
			brNationalIdentityCardNumber.length() == dniLength
	}

	@Unroll
	def "Should validate #vatIdentificationNumber as #valid"() {

		expect:
		generator.isValid(vatIdentificationNumber) == valid

		where:
		vatIdentificationNumber | valid
		"982.970.740-72"	| true
		"984.350.400-33"    | false
		"723.083.860-61"    | true
		"136.214.190-80"    | false
	}

	def "Should always generate proper nip"() {
		expect:
		generator.isValid(generator.get())
		where:
		i << (1..100)
	}


}
