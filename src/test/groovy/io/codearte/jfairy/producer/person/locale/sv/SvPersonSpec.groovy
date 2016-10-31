package io.codearte.jfairy.producer.person.locale.sv

import io.codearte.jfairy.Bootstrap
import io.codearte.jfairy.Fairy
import io.codearte.jfairy.producer.person.Person
import spock.lang.Specification

class SvPersonSpec extends Specification {

	Fairy fairy = Bootstrap.create(Locale.forLanguageTag("SV"))

	def setup() {
		Bootstrap.create()
	}

	def "should create nationalIdentificationNumber"() {
		when:
			Person person = fairy.person()
		then:
			person.nationalIdentificationNumber
	}

}
