package io.codearte.jfairy.producer

import io.codearte.jfairy.Bootstrap
import io.codearte.jfairy.Fairy
import io.codearte.jfairy.producer.company.CompanyProperties
import org.apache.commons.validator.routines.DomainValidator
import org.apache.commons.validator.routines.EmailValidator
import org.apache.commons.validator.routines.UrlValidator
import spock.lang.Specification

import static io.codearte.jfairy.producer.company.CompanyProperties.CompanyProperty.*

/**
 * @author Codearte
 * @since 2013-10-07
 */
class CompanySpec extends Specification {

	def emailValidator = EmailValidator.getInstance();
	def urlValidator = UrlValidator.getInstance();
	def domainValidator = DomainValidator.getInstance();
	def Fairy fairy = Bootstrap.create();

	def "should instantiate Company producer"() {
		when:
			def company = fairy.company()
		then:
			company
	}

	def "should be sure that data exists"() {
		when:
			def company = fairy.company()
		then:
			company.name()
			domainValidator.isValid(company.domain())
			emailValidator.isValid(company.email())
			urlValidator.isValid(company.url())
			company.vatIdentificationNumber()
	}

	def "withName should create company with specific name"() {
		when:
			def company = fairy.company(withName("Specific Name"))
		then:
			company.name() == "Specific Name"
	}

	def "withDomain should create company with specific domain"() {
		when:
			def company = fairy.company(withDomain("specificdomain.com"))
		then:
			company.domain() == "specificdomain.com"
	}

	def "withEmail should create company with specific email"() {
		when:
			def company = fairy.company(withEmail("specificemail"))
		then:
			company.email().startsWith("specificemail@")
	}

	def "withVatIdentificationNumber should create company with specific vat identification number"() {
		when:
			def company = fairy.company(withVatIdentificationNumber("specificvatidentificationnumber"))
		then:
			company.vatIdentificationNumber() == "specificvatidentificationnumber"
	}
}
