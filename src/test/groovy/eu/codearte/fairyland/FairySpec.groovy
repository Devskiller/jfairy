/*
 * Copyright (c) 2013. Codearte
 */

package eu.codearte.fairyland

import org.joda.time.DateTime
import spock.lang.Specification

class FairySpec extends Specification {

    def fairy = Fairy.create()

    def "should numerify hashed string"() {
        expect:
        fairy.numerify("Test#") ==~ /Test[0-9]/
    }

    def "should letterify hashed string"() {
        expect:
        fairy.letterify("Test?") ==~ /Test[a-z]/
    }

    def "should bothify hashed string"() {
        expect:
        fairy.bothify("Test?#") ==~ /Test[a-z][0-9]/
    }

    def "should generate random date in the past"() {
        expect:
        fairy.randomDateInThePast().isBefore(DateTime.now())
    }

    def "should generate national identity card number"() {
        expect:
        fairy.nationalIdentityNumber()
    }
}
