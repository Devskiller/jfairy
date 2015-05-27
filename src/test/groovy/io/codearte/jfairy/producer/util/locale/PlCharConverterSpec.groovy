package io.codearte.jfairy.producer.util.locale

import spock.lang.Specification

/**
 * @author Jakub Kubrynski
 */
class PlCharConverterSpec extends Specification {

	PlCharConverter charConverter = new PlCharConverter()

	def "should romanize polish text"() {
		given:
			String input = "Zażółć gęślą jaźń"
		when:
			def output = charConverter.romanize(input)
		then:
			output == "Zazolc gesla jazn"
	}
}
