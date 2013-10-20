/*
 * Copyright (c) 2013. Codearte
 */

package eu.codearte.fairyland

import spock.lang.Specification

import java.text.SimpleDateFormat

class FairyTest extends Specification {


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
        fairy.randomDateInThePast().before(new Date())
    }
}
