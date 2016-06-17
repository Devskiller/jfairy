package io.codearte.jfairy.producer.company.locale.se

import io.codearte.jfairy.Fairy
import spock.lang.Specification

import static io.codearte.jfairy.producer.company.locale.sv.MomsnrProvider.isValid

class MomsnrProviderSpec extends Specification {

	private static final int MOMSNRNR_LENGTH = 14

	private Fairy fairy;
	private String momsnr

	def setup() {
		fairy = Fairy.create(Locale.forLanguageTag("sv"))
		momsnr = fairy.company().vatIdentificationNumber()
	}


	def "should generate number with 14 characters"() {
		expect:
			momsnr.length() == MOMSNRNR_LENGTH
	}

	def "should start with SE"() {
		expect:
			momsnr.startsWith("SE")
	}

	def "should generate number with valid momsnr"() {
		expect:
			isValid(momsnr)
	}

}
