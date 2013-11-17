/*
 * Copyright (c) 2013. Codearte
 */

package eu.codearte.fairyland.producer

import eu.codearte.fairyland.producer.util.TimeProvider
import spock.lang.Specification
import spock.lang.Unroll

class RandomGeneratorSpec extends Specification {

	@Unroll
	def "should generate random number from given range #from - #to"() {
		setup:
		def randomGenerator = new BaseProducer(new TimeProvider());

		expect:
		def between = randomGenerator.randomBetween(from, to)

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
	}
}
