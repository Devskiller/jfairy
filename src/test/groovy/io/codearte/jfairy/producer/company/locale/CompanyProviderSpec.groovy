package io.codearte.jfairy.producer.company.locale

import io.codearte.jfairy.data.DataMaster
import io.codearte.jfairy.producer.BaseProducer
import io.codearte.jfairy.producer.VATIdentificationNumberProvider
import io.codearte.jfairy.producer.company.CompanyProvider
import spock.lang.Specification

import static io.codearte.jfairy.producer.company.CompanyProvider.*

class CompanyProviderSpec extends Specification {

	private BaseProducer baseProducer = Mock(BaseProducer)
	private DataMaster dataMaster = Mock(DataMaster)
	private VATIdentificationNumberProvider vatIdentificationNumberProvider = Mock(VATIdentificationNumberProvider)

	def "should remove slashes in company email"() {
		given:
			baseProducer.trueOrFalse() >> false
			dataMaster.getRandomValue(COMPANY_EMAIL) >> "info"
			dataMaster.getRandomValue(COMPANY_NAME) >> "mercedes/bosch"
			dataMaster.getRandomValue(DOMAIN) >> "com"
			CompanyProvider companyProvider = new CompanyProvider(baseProducer, dataMaster, vatIdentificationNumberProvider);

		when:
			String email = companyProvider.get().email;

		then:
			email == "info@mercedesbosch.com"
	}


	def "should lowercase letters in company email"() {
		given:
			baseProducer.trueOrFalse() >> false
			dataMaster.getRandomValue(COMPANY_EMAIL) >> "info"
			dataMaster.getRandomValue(COMPANY_NAME) >> "GOOGLE"
			dataMaster.getRandomValue(DOMAIN) >> "com"
			CompanyProvider companyProvider = new CompanyProvider(baseProducer, dataMaster, vatIdentificationNumberProvider);

		when:
			String email = companyProvider.get().email;

		then:
			email == "info@google.com"
	}


	def "should strip dots and remove spaces in company email"() {
		given:
			baseProducer.trueOrFalse() >> false
			dataMaster.getRandomValue(COMPANY_EMAIL) >> "info"
			dataMaster.getRandomValue(COMPANY_NAME) >> "company inc."
			dataMaster.getRandomValue(DOMAIN) >> "com"
			CompanyProvider companyProvider = new CompanyProvider(baseProducer, dataMaster, vatIdentificationNumberProvider);

		when:
			String email = companyProvider.get().email;

		then:
			email == "info@companyinc.com"
	}

	def "should strip accents in company email"() {
		given:
			baseProducer.trueOrFalse() >> false
			dataMaster.getRandomValue(COMPANY_EMAIL) >> "info"
			dataMaster.getRandomValue(COMPANY_NAME) >> "åäöéáąćęłńśóźż"
			dataMaster.getRandomValue(DOMAIN) >> "com"
			CompanyProvider companyProvider = new CompanyProvider(baseProducer, dataMaster, vatIdentificationNumberProvider);

		when:
			String email = companyProvider.get().email;

		then:
			email == "info@aaoeaacelnsozz.com"
	}
}
