/*
 * Copyright (c) 2013. Codearte
 */

package eu.codearte.fairyland.producer

import spock.lang.Specification
import spock.lang.Unroll

class RandomGeneratorSpec extends Specification {

    @Unroll
    def "should generate random between 5-9"() {
        setup:
        RandomGenerator randomGenerator = new RandomGenerator(100L);

        expect:
        def between = randomGenerator.randomBetween(from, to)
        println between
        between == value

        where:
        from        | to          || value
        5           | 9           || 5
        1           | 2           || 2
        1           | 3           || 2
        0           | 4           || 0
        48          | 57          || 53
//        '0' as char | '9' as char || 0

    }
}
