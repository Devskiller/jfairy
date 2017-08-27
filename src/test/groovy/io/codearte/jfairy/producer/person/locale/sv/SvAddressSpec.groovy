package io.codearte.jfairy.producer.person.locale.sv

import io.codearte.jfairy.Fairy
import io.codearte.jfairy.producer.person.Address
import spock.lang.Specification

import static org.apache.commons.lang3.SystemUtils.LINE_SEPARATOR

class SvAddressSpec extends Specification {

	private final int SEED = 7
	private Fairy fairy;
	private Address address

	def setup() {
		fairy = Fairy.builder().withRandomSeed(SEED).withLocale(Locale.forLanguageTag("SV")).build()
		address = fairy.person().address
	}

	def "should generate random street"() {
		expect:
			address.street == "Björngårdsgatan"
	}

	def "should generate random streetNumber"() {
		expect:
			address.streetNumber == "157"
	}

	def "should generate random apartmentNumber"() {
		expect:
			address.apartmentNumber == ""
	}

	def "should generate random postalCode"() {
		expect:
			address.postalCode == "528 66"
	}

	def "should generate random city"() {
		expect:
			address.city == "Kristianstad"
	}

	def "should return addressLine1 in sv locale format"() {
		expect:
			address.addressLine1 == "Björngårdsgatan 157"
	}

	def "should return addressLine2 in sv locale format"() {
		expect:
			address.addressLine2 == "528 66 Kristianstad"
	}

	def "should return address in sv locale format"() {
		expect:
			address.toString() == "Björngårdsgatan 157" + System.lineSeparator() + "528 66 Kristianstad"
	}

}
