/*
 * Copyright (c) 2013 Codearte and authors
 */

package io.codearte.jfairy.producer.person.locale.sv

import io.codearte.jfairy.Fairy
import spock.lang.Specification

class PersonalIdentityNumberProviderSpec extends Specification {

	def Fairy fairy;
	def String personalIdentityNumber
	private final int personalIdentityNumberLength = 11
	private final int hyphenPos = 7

	def setup() {
		fairy = Fairy.create(Locale.forLanguageTag("es"))
		personalIdentityNumber = fairy.person().nationalIdentityCardNumber()
	}

	def "should generate number with 11 characters: 10 digits, one hyphen"() {
		expect:
			personalIdentityNumber.length() == personalIdentityNumberLength
	}

	def "should generate number divided by hyphens"() {
		given:
			char letter = personalIdentityNumber.charAt(hyphenPos);

		expect:
			letter == '-';
	}

	def "should generate number with all numbers before the hyphen"() {
		given:
			String numbers = personalIdentityNumber.substring(0, hyphenPos);

		expect:
			for (char digit : numbers.toCharArray()) {
				digit.isDigit()
			}
	}


	def "should generate number with all numbers after the hyphen"() {
		given:
			String numbers = personalIdentityNumber.substring(hyphenPos);

		expect:
			for (char digit : numbers.toCharArray()) {
				digit.isDigit()
			}
	}
}
