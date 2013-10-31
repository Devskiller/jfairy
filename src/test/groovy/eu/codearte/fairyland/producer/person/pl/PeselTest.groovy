package eu.codearte.fairyland.producer.person.pl

import eu.codearte.fairyland.producer.RandomGenerator
import eu.codearte.fairyland.producer.person.Sex
import spock.lang.Specification

import static eu.codearte.fairyland.producer.person.pl.Pesel.isValidPesel

class PeselTest extends Specification {

    def RandomGenerator randomGenerator = new RandomGenerator(10002L);
    def Pesel generator = new Pesel(randomGenerator);

    void shouldValidateGoodPesel() {

        isValidPesel("44051401359")

    }

    void shouldGenerateValidPesel() {

        when:
        def pesel = generator.nationalIdentificationNumber(new GregorianCalendar(), Sex.male);
        println pesel

        then:
        pesel
        isValidPesel(pesel)
    }

}
