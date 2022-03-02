package com.devskiller.jfairy.producer.person.locale.br

import com.devskiller.jfairy.Fairy
import com.devskiller.jfairy.producer.person.Address
import spock.lang.Specification

class BrAddressSpec extends Specification {

	private final int SEED = 5
	private Fairy fairy;
	private Address address

	def setup() {
		fairy = Fairy.builder().withRandomSeed(SEED).withLocale(Locale.forLanguageTag("br")).build()
		address = fairy.person().address
	}

	def "should generate random street"() {
		expect:
			address.street == "Rua das Pedras"
	}

	def "should generate random streetNumber"() {
		expect:
			address.streetNumber == "138"
	}

	def "should generate random apartmentNumber"() {
		expect:
			address.apartmentNumber == ""
	}

	def "should generate random postalCode"() {
		expect:
			address.postalCode == "75092-797"
	}

	def "should generate random city"() {
		expect:
			address.city == "Fortaleza"
	}

	def "should return addressLine1 in es locale format"() {
		expect:
			address.addressLine1 == "Rua das Pedras, 138"
	}

	def "should return addressLine2 in es locale format"() {
		expect:
			address.addressLine2 == "75092-797 Fortaleza"
	}

	def "should return address in es locale format"() {
		expect:
			address.toString() == "Rua das Pedras, 138" + System.lineSeparator() + "75092-797 Fortaleza"
	}

}
