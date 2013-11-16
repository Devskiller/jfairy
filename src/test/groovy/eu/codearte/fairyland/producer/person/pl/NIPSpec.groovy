/*
 * Copyright (c) 2013 Codearte and authors
 */

package eu.codearte.fairyland.producer.person.pl

import eu.codearte.fairyland.producer.locale.pl.NIP
import eu.codearte.fairyland.producer.util.RandomGenerator
import spock.lang.Specification
import spock.lang.Unroll

/**
 * @author mariuszs
 * @since 02.11.13.
 */
class NIPSpec extends Specification {

	def random = Mock(RandomGenerator)
	def generator = new NIP(random)

	@Unroll def "Should validate #nip as #valid"() {

		expect:
		generator.isNIPValid(nip) == valid

		where:
		nip          | valid
		"2684494529" | true
		"1234567890" | false
		"0000000000" | true
	}

	def "Should generate good NIP"() {

		when:
		def nip = generator.generate()
		then:
		nip == "1010000002"
		generator.isNIPValid(nip)

	}

}
