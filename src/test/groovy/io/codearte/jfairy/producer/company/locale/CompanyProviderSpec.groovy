package io.codearte.jfairy.producer.company.locale

import io.codearte.jfairy.data.DataMaster
import io.codearte.jfairy.producer.BaseProducer
import io.codearte.jfairy.producer.VATIdentificationNumberProvider
import io.codearte.jfairy.producer.company.CompanyProvider
import spock.lang.Specification

import static io.codearte.jfairy.producer.company.CompanyProvider.*

class CompanyProviderSpec extends Specification {

    def baseProducer = Mock(BaseProducer)
    def dataMaster = Mock(DataMaster)
    def vatIdentificationNumberProvider = Mock(VATIdentificationNumberProvider)

    def "should generate should remove slashes in company email"() {
        given:
            baseProducer.trueOrFalse() >> false
            dataMaster.getRandomValue(COMPANY_EMAIL) >> "info"
            dataMaster.getRandomValue(COMPANY_NAME) >> "mercedes/bosch"
            dataMaster.getRandomValue(DOMAIN) >> "com"

        def companyProvider = new CompanyProvider(baseProducer, dataMaster, vatIdentificationNumberProvider);

        when:
            def email = companyProvider.get().email();

        then:
            email == "info@mercedesbosch.com"
    }


    def "should generate should lowercase letters in company email"() {
        given:
            baseProducer.trueOrFalse() >> false
            dataMaster.getRandomValue(COMPANY_EMAIL) >> "info"
            dataMaster.getRandomValue(COMPANY_NAME) >> "GOOGLE"
            dataMaster.getRandomValue(DOMAIN) >> "com"

        def companyProvider = new CompanyProvider(baseProducer, dataMaster, vatIdentificationNumberProvider);

        when:
            def email = companyProvider.get().email();

        then:
            email == "info@google.com"
    }


    def "should generate should strip dots and remove spaces in company email"() {
        given:
            baseProducer.trueOrFalse() >> false
            dataMaster.getRandomValue(COMPANY_EMAIL) >> "info"
            dataMaster.getRandomValue(COMPANY_NAME) >> "company inc."
            dataMaster.getRandomValue(DOMAIN) >> "com"

        def companyProvider = new CompanyProvider(baseProducer, dataMaster, vatIdentificationNumberProvider);

        when:
            def email = companyProvider.get().email();

        then:
            email == "info@companyinc.com"
    }

    def "should strip accents from company email"() {
        given:
            baseProducer.trueOrFalse() >> false
            dataMaster.getRandomValue(COMPANY_EMAIL) >> "info"
            dataMaster.getRandomValue(COMPANY_NAME) >> "åäöéáąćęłńśóźż"
            dataMaster.getRandomValue(DOMAIN) >> "com"

        def companyProvider = new CompanyProvider(baseProducer, dataMaster, vatIdentificationNumberProvider);

        when:
            def email = companyProvider.get().email();

        then:
            email == "info@aaoeaacelnsozz.com"
    }
}
