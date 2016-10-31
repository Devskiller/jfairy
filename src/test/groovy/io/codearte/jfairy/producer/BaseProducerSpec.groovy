/*
 * Copyright (c) 2013. Codearte
 */

package io.codearte.jfairy.producer

import spock.lang.Specification
import spock.lang.Unroll

class BaseProducerSpec extends Specification {

	BaseProducer baseProducer = Spy(BaseProducer, constructorArgs: [new Random()]);

	def setup() {
		baseProducer.randomBetween('0', '9') >> '7'
		baseProducer.randomBetween('a', 'z') >> 'x'
	}

	def "should replace # with digit 7"() {
		expect:
			baseProducer.numerify("Tes#t#") == "Tes7t7"
	}

	def "should replace ? with letter x"() {
		expect:
			baseProducer.letterify("Tes?t?") == "Tesxtx"
	}

	def "should replace # and ? with 7 and x respectively"() {
		expect:
			baseProducer.bothify("Test?#") == "Testx7"
	}

	def "should replace ? with letter from desired range"() {
		when:
			String result = baseProducer.letterify("Test??", 'A' as char, 'A' as char)
		then:
			result == "TestAA"
	}

	@Unroll
	def "should generate random number from given range #from - #to"() {
		setup:
			BaseProducer randomGenerator = new BaseProducer(new Random());

		expect:
			double between = randomGenerator.randomBetween(from, to)

			between >= from
			between <= to

		where:
			from | to
			5    | 9
			1    | 2
			1    | 3
			0    | 4
			48   | 57
			2L   | 2L
			-5L  | -2L
			-3L  | 2L
			2.0  | 3.0
			-2.0 | -1.0
	}

	def "should retrieve random enum element"() {
		setup:
			baseProducer.randomBetween(0, 2) >> 1
		expect:
			TestEnum.B == baseProducer.randomElement(TestEnum)
	}

	def "should retrieve random vararg element"() {
		setup:
			baseProducer.randomBetween(0, 2) >> 1
		expect:
			'B' == baseProducer.randomElement('A', 'B', 'C')
	}

	static enum TestEnum {
		A, B, C
	}
}
