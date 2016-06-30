package io.codearte.jfairy.producer.person.locale.sv

import io.codearte.jfairy.Bootstrap
import io.codearte.jfairy.Fairy
import spock.lang.Specification

class SvPersonSpec extends Specification {

	def Fairy fairy = Bootstrap.create(Locale.forLanguageTag("SV"))

	def setup() {
		Bootstrap.create()
	}

	def "should create nationalIdentificationNumber"() {
		when:
			def person = fairy.person()
		then:
			person.getNationalIdentificationNumber()
	}

}
