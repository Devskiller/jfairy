package io.codearte.jfairy.producer.person.locale.es

import io.codearte.jfairy.Fairy
import spock.lang.Specification

/**
 * @author graux
 * @since 24/06/2015
 */
class EsPassportNumberProviderSpec extends Specification {

	def Fairy fairy;
	def String passportNumber

	def setup() {
		fairy = Fairy.create(Locale.forLanguageTag("es"))
		passportNumber = fairy.person().getPassportNumber()
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
