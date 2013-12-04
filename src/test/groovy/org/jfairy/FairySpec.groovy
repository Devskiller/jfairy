package org.jfairy

import spock.lang.Specification

class FairySpec extends Specification {

	def "Second person should be different without fairy instance"() {

		given:
		def person = Fairy.create().person();
		when:
		person = Fairy.create().person();
		then:
		person.fullName() != old(person.fullName())
	}

	def "Second person should be different with one fairy"() {

		given:
		def fairy = Fairy.create()
		def person = fairy.person();
		when:
		person = fairy.person();
		then:
		person.fullName() != old(person.fullName())
	}
}
