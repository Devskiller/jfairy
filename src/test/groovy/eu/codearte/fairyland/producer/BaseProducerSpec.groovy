/*
 * Copyright (c) 2013. Codearte
 */

package eu.codearte.fairyland.producer

import spock.lang.Specification

class BaseProducerSpec extends Specification {

	def baseProducer = Spy(BaseProducer);

	def setup() {
		baseProducer.randomBetween('0', '9') >> '7'
		baseProducer.randomBetween('a', 'z') >> 'x'
	}

	def "should replace # with digit 0"() {
		expect:
		baseProducer.numerify("Tes#t#") == "Tes7t7"
	}

	def "should replace ? with letter a"() {
		expect:
		baseProducer.letterify("Tes?t?") == "Tesxtx"
	}

	def "should replace # and ? with 0 and a respectively"() {
		expect:
		baseProducer.bothify("Test?#") == "Testx7"
	}

}
