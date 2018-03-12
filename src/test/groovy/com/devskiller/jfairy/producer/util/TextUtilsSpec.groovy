package com.devskiller.jfairy.producer.util

import spock.lang.Specification

class TextUtilsSpec extends Specification {

	def "joinWithSpace should join list of strings with spaces"() {
		given:
			List<String> input = Arrays.asList("abc", "def", "ghi")
		when:
			String output = TextUtils.joinWithSpace(input)
		then:
			output == "abc def ghi"
	}

	def "stripAccents should romanize polish text"() {
		given:
			String input = "Zażółć gęślą jaźń"
		when:
			String output = TextUtils.stripAccents(input)
		then:
			output == "Zazolc gesla jazn"
	}
}
