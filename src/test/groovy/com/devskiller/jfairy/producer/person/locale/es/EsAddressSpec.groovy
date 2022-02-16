package com.devskiller.jfairy.producer.person.locale.es

import spock.lang.Specification

import com.devskiller.jfairy.Fairy
import com.devskiller.jfairy.producer.person.Address

class EsAddressSpec extends Specification {

	private final int SEED = 4
	private Fairy fairy;
	private Address address

	def setup() {
		fairy = Fairy.builder().withRandomSeed(SEED).withLocale(Locale.forLanguageTag("ES")).build()
		address = fairy.person().address
	}

	def "should generate random street"() {
		expect:
			address.street == "Cruz"
	}

	def "should generate random streetNumber"() {
		expect:
			address.streetNumber == "30"
	}

	def "should generate random apartmentNumber"() {
		expect:
			address.apartmentNumber == "279"
	}

	def "should generate random postalCode"() {
		expect:
			address.postalCode == "65.216"
	}

	def "should generate random city"() {
		expect:
			address.city == "Talavera de la Reina"
	}

	def "should return addressLine1 in es locale format"() {
		expect:
			address.addressLine1 == "Cruz, 30 279"
	}

	def "should return addressLine2 in es locale format"() {
		expect:
			address.addressLine2 == "65.216 Talavera de la Reina"
	}

	def "should return address in es locale format"() {
		expect:
			address.toString() == "Cruz, 30 279" + System.lineSeparator() + "65.216 Talavera de la Reina"
	}

}
