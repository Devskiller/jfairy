/*
 * Copyright (c) 2013. Codearte
 */

package eu.codearte.fairyland.producer.text

import eu.codearte.fairyland.producer.RandomGenerator
import spock.lang.Specification

class StringifyUtilTest extends Specification {

    def randomGenerator = Mock(RandomGenerator);
    def text = new StringifyUtil(randomGenerator);

    def "should replace # with digit 0"() {
        expect:
        text.numerify("Tes#t#") == "Tes0t0"
    }

    def "should replace ? with letter a"() {
        expect:
        text.letterify("Tes?t?") == "Tesata"
    }

    def "should replace # and ? with 0 and a respectively"() {
        expect:
        text.bothify("Test?#") == "Testa0"
    }

    def "should replace # and ? with 9 and z respectively"() {
        setup:
        randomGenerator.randomInt(9) >> 9
        randomGenerator.randomInt(25) >> 25

        expect:
        text.bothify("Test?#") == "Testz9"
    }
}
