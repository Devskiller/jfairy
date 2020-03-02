package com.devskiller.jfairy.producer.person.locale.es

import spock.lang.Specification

import com.devskiller.jfairy.Fairy
import com.devskiller.jfairy.producer.person.Address

class EsAddressSpec extends Specification {

	private final int SEED = 3
	private Fairy fairy;
	private Address address

	def setup() {
		fairy = Fairy.builder().withRandomSeed(SEED).withLocale(Locale.forLanguageTag("ES")).build()
		address = fairy.person().address
	}

	def "should generate random street"() {
		expect:
			address.street == "Gran Vía"
	}

	def "should generate random streetNumber"() {
		expect:
			address.streetNumber == "32"
	}

	def "should generate random apartmentNumber"() {
		expect:
			address.apartmentNumber == "64"
	}

	def "should generate random postalCode"() {
		expect:
			address.postalCode == "79.638"
	}

	def "should generate random city"() {
		expect:
			address.city == "Ciudad Real"
	}

	def "should return addressLine1 in es locale format"() {
		expect:
			address.addressLine1 == "Gran Vía, 32 64"
	}

	def "should return addressLine2 in es locale format"() {
		expect:
			address.addressLine2 == "79.638 Ciudad Real"
	}

	def "should return address in es locale format"() {
		expect:
			address.toString() == "Gran Vía, 32 64" + System.lineSeparator() + "79.638 Ciudad Real"
	}

}
