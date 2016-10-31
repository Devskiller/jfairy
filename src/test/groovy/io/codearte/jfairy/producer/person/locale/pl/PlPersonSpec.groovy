package io.codearte.jfairy.producer.person.locale.pl

import io.codearte.jfairy.Bootstrap
import io.codearte.jfairy.Fairy
import spock.lang.Specification

class PlPersonSpec extends Specification {

	def Fairy fairy = Bootstrap.create(Locale.forLanguageTag("PL"))

	def setup() {
		Bootstrap.create()
	}

	def "should create nationalIdentificationNumber"() {
		when:
			def person = fairy.person()
		then:
			person.nationalIdentificationNumber
	}

}
