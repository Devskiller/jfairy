package eu.codearte.fairyland.producer.person

import spock.lang.Specification

import static eu.codearte.fairyland.producer.person.PeselGenerator.isValidPesel

class PeselGeneratorTest extends Specification {

    def PeselGenerator generator = new PeselGenerator();

    void shouldValidateGoodPesel() {

        isValidPesel("44051401359")

    }

    void shouldGenerateValidPesel() {

        when:
        def pesel = generator.pesel(new GregorianCalendar(), PersonHolder.Sex.male);
        println pesel

        then:
        pesel
        isValidPesel(pesel)
    }

}
