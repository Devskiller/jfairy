package io.codearte.jfairy

import io.codearte.jfairy.producer.person.Person
import spock.lang.Specification

class FairyFrSpec extends Specification {

    def "Second create something French"() {

        given:
            Fairy fairy = Fairy.create(Locale.FRENCH);
        when:
            Person person = fairy.person();
        then:
            println person.fullName()
    }

}
