package io.codearte.jfairy.producer

import io.codearte.jfairy.Bootstrap
import org.apache.commons.validator.routines.DomainValidator
import org.apache.commons.validator.routines.EmailValidator
import org.apache.commons.validator.routines.UrlValidator
import spock.lang.Specification

/**
 * @author Codearte
 * @since 2013-10-07
 */
class CompanySpec extends Specification {

	def emailValidator = EmailValidator.getInstance();
	def urlValidator = UrlValidator.getInstance();
	def domainValidator = DomainValidator.getInstance();

	def "should instantiate Company producer"() {
		when:
			def company = Bootstrap.create().company()
		then:
			company
	}

	def "should be sure that data exists"() {
		when:
			def company = Bootstrap.create().company()
		then:
			company.getName()
			domainValidator.isValid(company.getDomain())
			emailValidator.isValid(company.getEmail())
			urlValidator.isValid(company.getUrl())
			company.getVatIdentificationNumber()
	}

}
