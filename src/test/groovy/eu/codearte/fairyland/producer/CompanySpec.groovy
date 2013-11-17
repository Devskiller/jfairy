package eu.codearte.fairyland.producer

import eu.codearte.fairyland.Fairy
import eu.codearte.fairyland.producer.company.Company
import eu.codearte.fairyland.producer.locale.pl.NIP
import org.apache.commons.validator.routines.EmailValidator
import org.apache.commons.validator.routines.UrlValidator
import spock.lang.Specification

/**
 * @author Codearte
 * @since 2013-10-07
 */
class CompanySpec extends Specification {

	def EmailValidator emailValidator = EmailValidator.getInstance();
	def UrlValidator urlValidator = UrlValidator.getInstance();

	def "should instantiate Company producer"() {
		when:
		def company = Fairy.create().company()
		then:
		company instanceof Company
	}

	def "should be sure that data exists"() {
		when:
		def company = Fairy.create().company()
		then:
		company.name()
		emailValidator.isValid(company.email())
		urlValidator.isValid(company.url())
		NIP.isValid(company.vatIdentificationNumber())
	}
}
