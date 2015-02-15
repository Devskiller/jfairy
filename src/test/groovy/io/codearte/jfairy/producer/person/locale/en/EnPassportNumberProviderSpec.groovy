package io.codearte.jfairy.producer.person.locale.en

import io.codearte.jfairy.Fairy
import spock.lang.Specification

/**
 * @author omaciaszek
 @since 15.03.15
 */
class EnPassportNumberProviderSpec extends Specification {

	def Fairy fairy;
	def String passportNumber

	def setup() {
		fairy = Fairy.create(Locale.forLanguageTag("en"))
		passportNumber = fairy.person().passportNumber()
	}

	def "should generate number with nine characters"() {
		expect:
			passportNumber.length() == 9
	}

	def "should contain only digits and letters"() {
		for (Character character : passportNumber) {
			expect:
				character.isDigit() || character.isLetter()
		}
	}
}
