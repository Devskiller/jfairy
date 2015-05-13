package io.codearte.jfairy.producer.person.locale.en

import io.codearte.jfairy.Fairy
import spock.lang.Specification

/**
 * @author Olga Maciaszek-Sharma
 @since 15.03.15
 */
class SocialSecurityNumberProviderSpec extends Specification {

	def Fairy fairy;
	def String socialSecurityNumber
	private final int socialSecurityNumberLength = 11
	private final int[] partsDividedAtChars = [3, 6]
	private int areaPartBeginningIndex = 0
	private final int groupPartBeginningIndex = 4
	private int serialNumberBeginningIndex = 7

	def setup() {
		fairy = Fairy.create(Locale.forLanguageTag("en"))
		socialSecurityNumber = fairy.person().nationalIdentityCardNumber()
	}

	def "should generate number with 11 characters"() {
		expect:
			socialSecurityNumber.length() == socialSecurityNumberLength
	}

	def "should generate number divided by hyphens"() {
		expect:
			for (int index : partsDividedAtChars) {
				socialSecurityNumber.charAt(index) == '-'
			}
	}

	def "should generate number with valid area part"() {
		given:
			String areaPart = socialSecurityNumber.substring(areaPartBeginningIndex, partsDividedAtChars[0]);
			Integer areaNumber = Integer.parseInt(areaPart);
			def minAreaPartNumber = 1
			def maxAreaPartNumber = 899
			def incorrectAreaNumber = 666

		expect:
			for (char digit : areaPart.toCharArray()) {
				digit.isDigit()
			}
			areaNumber >= minAreaPartNumber && areaNumber <= maxAreaPartNumber
			areaNumber != incorrectAreaNumber
	}

	def "should generate number with valid group part"() {
		given:
			String groupPart = socialSecurityNumber.substring(groupPartBeginningIndex, partsDividedAtChars[1]);
			Integer groupNumber = Integer.parseInt(groupPart);
			def minGroupNumber = 1
			def maxGroupNumber = 99

		expect:
			for (char digit : groupPart.toCharArray()) {
				digit.isDigit()
			}
			groupNumber >= minGroupNumber && groupNumber <= maxGroupNumber
	}

	def "should generate number with valid serial part"() {
		given:
			String serialPart = socialSecurityNumber.substring(serialNumberBeginningIndex, socialSecurityNumberLength);
			Integer serialNumber = Integer.parseInt(serialPart);
			def minSerialNumber = 1
			def maxSerialNumber = 9999

		expect:
			for (char digit : serialPart.toCharArray()) {
				digit.isDigit()
			}
			serialNumber >= minSerialNumber && serialNumber <= maxSerialNumber
	}

}
