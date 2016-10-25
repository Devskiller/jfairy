package io.codearte.jfairy.producer.person

import io.codearte.jfairy.producer.company.Company
import spock.lang.Specification

class CompanyEmailProviderSpec extends Specification {

	def "should remove spaces from firstName and lastName and lowercase letters in company email"() {
		given:
			Company company = new Company(null, "companymail.com", null, null);
			CompanyEmailProvider companyEmailProvider = new CompanyEmailProvider("Emilie Agneta", "Vojnov Allerstrand", company);

		when:
			String email = companyEmailProvider.get();

		then:
			email == "emilie.agneta.vojnov.allerstrand@companymail.com"
	}

	def "should strip accents from company email"() {
		given:
			Company company = new Company(null, "åäöéáąćęłńśóźż.com", null, null);
			CompanyEmailProvider companyEmailProvider = new CompanyEmailProvider("åäöéáąćęłńśóźż", "åäöéáąćęłńśóźż", company);

		when:
			String email = companyEmailProvider.get();

		then:
			email == "aaoeaacelnsozz.aaoeaacelnsozz@aaoeaacelnsozz.com"
	}
}
