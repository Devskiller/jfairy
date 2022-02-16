package com.devskiller.jfairy.producer.person.locale.sk

import spock.lang.Specification

import com.devskiller.jfairy.Fairy

/**
 * @author Olga Maciaszek-Sharma
 @since 21.02.15
 */
class SkPassportNumberProviderSpec extends Specification {

	private Fairy fairy
	private String passportNumber

	def setup() {
		fairy = Fairy.create(Locale.forLanguageTag("sk"))
		passportNumber = fairy.person().passportNumber
	}

	def "should generate number with correct length"() {
		expect:
			passportNumber.length() == 9
	}

	def "should generate number starting with series"() {
		expect:
			for (int i = 0; i < 2; i++)
				passportNumber.charAt(i).isLetter()
	}


	def "should generate number ending with 6 digits"() {
		expect:
			for (int i = 8; i > 2; i--) {
				passportNumber.charAt(i).isDigit()
			}
	}

	def "should generate number with correct checksum"() {
		expect:
			SkPassportNumberProvider.passportCheckSumIsValid(passportNumber)
	}


}
