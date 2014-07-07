package org.jfairy.producer.payment

import org.iban4j.IbanUtil
import org.jfairy.producer.BaseProducer
import spock.lang.Specification

class IBANProducerSpec extends Specification {
    private baseProducer


    def setup() {
        baseProducer = new BaseProducer(new Random())
    }

    /**
     * Austria	20	16n
     * ATkk bbbb bccc cccc cccc
     *
     * b = National bank code
     c = Account number
     */
    def "should return valid iban"() {
        when:
            IBAN iban = new IBAN(baseProducer)
            iban.accountNumber = "00234573201"
            iban.countryCode = "AT"

        then:
            IbanUtil.validate(iban.iban);
    }

    /**
     * Poland	28	24n	PLkk bbbs sssx cccc cccc cccc cccc	b = National bank code
     s = Branch code
     x = National check digit
     c = Account number,

     PLkk bbbssssx cccccccccccccccc
     PL60 11401010 1111000234573201
     */

    def "should return valid polish iban"() {
        when:
            IBAN iban = new IBAN(baseProducer)
        then:
            IbanUtil.validate(iban.iban);
    }
}
