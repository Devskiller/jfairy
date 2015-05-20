package io.codearte.jfairy.dataProvider.company.locale.en

import io.codearte.jfairy.Fairy
import spock.lang.Specification

/**
 * @author Olga Maciaszek-Sharma
 @since 21.03.15
 */
class EmployerIdentificationNumberProviderSpec extends Specification {

	private Fairy fairy;
	private String employerIdentificationNumber
	private final partsDividedAtChar = 2
	private final int employerIdentificationNumberLength = 10
	private final int areaPartBeginningIndex = 0
	private final int serialPartBeginningIndex = 3

	def setup() {
		fairy = Fairy.create(Locale.forLanguageTag("en"))
		employerIdentificationNumber = fairy.company().vatIdentificationNumber()
	}

	def "should generate number with 10 characters"() {
		expect:
			employerIdentificationNumber.length() == employerIdentificationNumberLength
	}

	def "should generate number divided by hyphen"() {
		expect:
			employerIdentificationNumber.charAt(partsDividedAtChar) == '-'
	}

	def "should generate number with valid area part"() {
		given:
			String areaPart = employerIdentificationNumber.substring(areaPartBeginningIndex, partsDividedAtChar)
			Integer areaNumber = Integer.parseInt(areaPart)

		expect:
			for (char digit : areaPart.toCharArray()) {
				digit.isDigit()
			}
			!EmployerIdentificationNumberProvider.excludedNumbers.contains(areaNumber)
	}

	def "should generate number with valid serial part"() {
		given:
			String serialPart = employerIdentificationNumber.substring(serialPartBeginningIndex, employerIdentificationNumberLength)

		expect:
			for (char digit : serialPart.toCharArray()) {
				digit.isDigit()
			}
	}

}
