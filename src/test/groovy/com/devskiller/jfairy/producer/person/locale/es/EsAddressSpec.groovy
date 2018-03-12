package com.devskiller.jfairy.producer.person.locale.es

import spock.lang.Specification

import com.devskiller.jfairy.Fairy
import com.devskiller.jfairy.producer.person.Address

class EsAddressSpec extends Specification {

	private final int SEED = 7
	private Fairy fairy;
	private Address address

	def setup() {
		fairy = Fairy.builder().withRandomSeed(SEED).withLocale(Locale.forLanguageTag("ES")).build()
		address = fairy.person().address
	}

	def "should generate random street"() {
		expect:
			address.street == "Vieja"
	}

	def "should generate random streetNumber"() {
		expect:
			address.streetNumber == "86"
	}

	def "should generate random apartmentNumber"() {
		expect:
			address.apartmentNumber == "127"
	}

	def "should generate random postalCode"() {
		expect:
			address.postalCode == "13.391"
	}

	def "should generate random city"() {
		expect:
			address.city == "Palencia"
	}

	def "should return addressLine1 in es locale format"() {
		expect:
			address.addressLine1 == "Vieja, 86 127"
	}

	def "should return addressLine2 in es locale format"() {
		expect:
			address.addressLine2 == "13.391 Palencia"
	}

	def "should return address in es locale format"() {
		expect:
			address.toString() == "Vieja, 86 127" + System.lineSeparator() + "13.391 Palencia"
	}

}
