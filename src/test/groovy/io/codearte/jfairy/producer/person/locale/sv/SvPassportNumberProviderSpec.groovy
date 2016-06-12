package io.codearte.jfairy.producer.person.locale.sv

import io.codearte.jfairy.Fairy
import spock.lang.Specification

/**
 * @author Olga Maciaszek-Sharma
 @since 15.03.15
 */
class SvPassportNumberProviderSpec extends Specification {

	def Fairy fairy;
	def String passportNumber

	def setup() {
		fairy = Fairy.create(Locale.forLanguageTag("sv"))
		passportNumber = fairy.person().passportNumber()
	}

	def "should generate number with nine characters"() {
		expect:
			passportNumber.length() == 8
	}

	def "should contain only digits"() {
		for (Character character : passportNumber) {
			expect:
				character.isDigit()
		}
	}
}
