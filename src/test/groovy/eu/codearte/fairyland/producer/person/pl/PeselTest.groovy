package eu.codearte.fairyland.producer.person.pl

import eu.codearte.fairyland.producer.person.Sex
import spock.lang.Specification

import static eu.codearte.fairyland.producer.person.pl.Pesel.isValidPesel

class PeselTest extends Specification {

    def Pesel generator = new Pesel();

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

    void testName() throws Exception {

        for (int i = 0; i < 10000; i++) {
            def i1 = (int) (Math.random() * 10)
            if (i1 == 10) throw new RuntimeException();
            println "" + i1 + ","
        }

        expect:
        true

    }
}
