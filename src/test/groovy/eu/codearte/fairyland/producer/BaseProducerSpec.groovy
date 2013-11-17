/*
 * Copyright (c) 2013. Codearte
 */

package eu.codearte.fairyland.producer

import spock.lang.Specification

class BaseProducerSpec extends Specification {

	def randomGenerator = Mock(RandomGenerator);
	def fakerProducer = new FakerProducer(randomGenerator);

	def setup() {
		randomGenerator.randomBetween('0', '9') >> '7'
		randomGenerator.randomBetween('a', 'z') >> 'x'
	}

	def "should replace # with digit 0"() {
		expect:
		fakerProducer.numerify("Tes#t#") == "Tes7t7"
	}

	def "should replace ? with letter a"() {
		expect:
		fakerProducer.letterify("Tes?t?") == "Tesxtx"
	}

	def "should replace # and ? with 0 and a respectively"() {
		expect:
		fakerProducer.bothify("Test?#") == "Testx7"
	}

}
