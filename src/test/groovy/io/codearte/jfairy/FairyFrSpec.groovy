package io.codearte.jfairy

import io.codearte.jfairy.producer.person.Person
import spock.lang.Specification

class FairyFrSpec extends Specification {

    private final int SEED = 7
    private Fairy fairy = Fairy.builder().withRandom(new Random(SEED)).withLocale(Locale.FRENCH).build()

    def "Should create French name"() {
        when:
            Person person = fairy.person();
        then:
            person.fullName == 'Normand Besnard'
    }

    def "Should create French city"() {
        when:
            Person person = fairy.person();
        then:
            person.address.city == 'Six-Fours-les-Plages'
    }

}
