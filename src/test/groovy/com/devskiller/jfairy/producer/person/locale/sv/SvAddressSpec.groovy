package com.devskiller.jfairy.producer.person.locale.sv

import spock.lang.Specification

import com.devskiller.jfairy.Fairy
import com.devskiller.jfairy.producer.person.Address

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
			address.street == "Sandåsgatan"
	}

	def "should generate random streetNumber"() {
		expect:
			address.streetNumber == "107"
	}

	def "should generate random apartmentNumber"() {
		expect:
			address.apartmentNumber == ""
	}

	def "should generate random postalCode"() {
		expect:
			address.postalCode == "745 69"
	}

	def "should generate random city"() {
		expect:
			address.city == "Lödöse"
	}

	def "should return addressLine1 in sv locale format"() {
		expect:
			address.addressLine1 == "Sandåsgatan 107"
	}

	def "should return addressLine2 in sv locale format"() {
		expect:
			address.addressLine2 == "745 69 Lödöse"
	}

	def "should return address in sv locale format"() {
		expect:
			address.toString() == "Sandåsgatan 107" + System.lineSeparator() + "745 69 Lödöse"
	}

}
