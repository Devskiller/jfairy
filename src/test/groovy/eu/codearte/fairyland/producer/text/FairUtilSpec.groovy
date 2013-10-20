/*
 * Copyright (c) 2013. Codearte
 */

package eu.codearte.fairyland.producer.text

import eu.codearte.fairyland.producer.RandomGenerator
import eu.codearte.fairyland.producer.util.TimeProvider
import spock.lang.Ignore
import spock.lang.Specification

class FairUtilSpec extends Specification {

    def randomGenerator = Mock(RandomGenerator);
    def timeProvider = Mock(TimeProvider)
    def text = new FairUtil(randomGenerator, timeProvider);

    int num0 = (int) '0'
    int num9 = (int) '9'
    int letterA = (int) 'a'
    int letterZ = (int) 'z'

    def setup() {
        randomGenerator.randomBetween(num0, num9) >> num0
        randomGenerator.randomBetween(letterA, letterZ) >> letterA
    }

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

    // FIXME: How to reset mock?
    @Ignore
    def "should replace # and ? with 9 and z respectively"() {
        setup:
        randomGenerator.randomBetween(num0, num9) >> num9
        randomGenerator.randomBetween(letterA, letterZ) >> letterZ

        expect:
        text.bothify("Test?#") == "Testz9"
    }
}
