package io.codearte.jfairy.producer.company.locale.se

import io.codearte.jfairy.Fairy
import spock.lang.Specification

class MomsnrProviderSpec extends Specification {

	def Fairy fairy;
	def String momsnr

	def setup() {
		fairy = Fairy.create(Locale.forLanguageTag("sv"))
		momsnr = fairy.company().vatIdentificationNumber()
	}

	def "should generate empty string since it is not yet implemented"() {
		expect:
		momsnr.length() == 0
	}
}
