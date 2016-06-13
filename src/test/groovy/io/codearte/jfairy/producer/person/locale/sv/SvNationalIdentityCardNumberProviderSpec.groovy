package io.codearte.jfairy.producer.person.locale.sv

import io.codearte.jfairy.Fairy
import spock.lang.Specification

class SvNationalIdentityCardNumberProviderSpec extends Specification {

	def Fairy fairy;
	def String nationalIdentityCardNumber

	def setup() {
		fairy = Fairy.create(Locale.forLanguageTag("sv"))
		nationalIdentityCardNumber = fairy.person().nationalIdentityCardNumber()
	}

	def "should generate empty string since it is not yet implemented"() {
		expect:
			nationalIdentityCardNumber.length() == 0
	}
}
