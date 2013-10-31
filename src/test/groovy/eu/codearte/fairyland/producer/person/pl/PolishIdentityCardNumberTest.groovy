/*
 * Copyright (c) 2013 Codearte and authors
 */

package eu.codearte.fairyland.producer.person.pl

import eu.codearte.fairyland.producer.RandomGenerator
import spock.lang.Specification

/**
 * @author mariuszs
 * @since 30.10.13.
 */
class PolishIdentityCardNumberTest extends Specification {

    def randomGenerator = Mock(RandomGenerator)

    /**
     * http://en.wikipedia.org/wiki/Polish_identity_card
     */
    void shouldGenerateProperNumber() {
        setup:
            randomGenerator.randomBetween('A', 'Z') >>> ['A', 'B', 'A']
            randomGenerator.randomBetween(0, 99999) >> 0
        when:
            PolishIdentityCardNumber idNumberGenerator = new PolishIdentityCardNumber(randomGenerator)
            def id = idNumberGenerator.identityNumber()

            println id
        then:
            id == "ABA300000"
    }
}
