package com.devskiller.jfairy.producer.person.locale.br;


import spock.lang.Specification

import com.devskiller.jfairy.Fairy

/**
 * @author Nelson Rodrigues da Silva Júnior
 * @since 15.10.2020
 */
class BrPassportNumberProviderSpec extends Specification {

	private Fairy fairy;
	private String passportNumber

	def setup() {
		fairy = Fairy.create(Locale.forLanguageTag("br"))
		passportNumber = fairy.person().passportNumber
	}

	def "should generate number with nine characters"()

	{
		expect:
		passportNumber.length() == 9
	}

	def "should contain only digits and letters"()

	{
		for (Character character : passportNumber) {
			expect:
			character.isDigit() || character.isLetter()
		}
	}

}
