/*
 * Copyright (c) 2013 Codearte and authors
 */

package io.codearte.jfairy.producer.person.locale.es

import io.codearte.jfairy.Fairy
import spock.lang.Specification

/**
 * @author graux
 * @since 24/06/2015
 */
class EsIdentityCardNumberProviderSpec extends Specification {

    def Fairy fairy;
    def String dniNumber
    private final int dniLength = 10
    private final int hyphenPos = 8

    def setup() {
        fairy = Fairy.create(Locale.forLanguageTag("es"))
        dniNumber = fairy.person().nationalIdentityCardNumber()
    }

    def "should generate number with 10 characters: 8 digits, one hyphen and one letter"() {
        expect:
        dniNumber.length() == dniLength
    }

    def "should generate number divided by hyphens"() {
        given:
            char letter = dniNumber.charAt(hyphenPos);

        expect:
            letter == '-';
    }

    def "should generate number with all numbers before the hyphen"() {
        given:
            String numbers = dniNumber.substring(0, hyphenPos);

        expect:
        for (char digit : numbers.toCharArray()) {
            digit.isDigit()
        }
    }


    def "should generate number with letter after the hyphen"() {
        given:
            char letter = dniNumber.charAt(hyphenPos + 1);

        expect:
            letter.isLetter();
    }
}
