package com.devskiller.jfairy.producer.person.locale.en

import spock.lang.Specification

import com.devskiller.jfairy.Fairy
import com.devskiller.jfairy.producer.person.Address

class EnAddressSpec extends Specification {

	private final int SEED = 7
	private Fairy fairy;
	private Address address

	def setup() {
		fairy = Fairy.builder().withRandomSeed(SEED).withLocale(Locale.forLanguageTag("EN")).build()
		address = fairy.person().address
	}

	def "should generate random street"() {
		expect:
			address.street == "Washington Walk"
	}

	def "should generate random streetNumber"() {
		expect:
			address.streetNumber == "42"
	}

    def "should generate random apartmentNumber"() {
        expect:
            address.apartmentNumber == "86"
    }

	def "should generate random postalCode"() {
		expect:
			address.postalCode == "86645"
	}

	def "should generate random city"() {
		expect:
		    address.city == "San Francisco"
	}

	def "should return addressLine1 in en locale format"() {
		expect:
			address.addressLine1 == "42 Washington Walk APT 86"
	}

	def "should return addressLine2 in en locale format"() {
		expect:
			address.addressLine2 == "San Francisco 86645"
	}

	def "should return address in en locale format"() {
		expect:
            address.toString() == "42 Washington Walk APT 86" + System.lineSeparator() + "San Francisco 86645"
	}

}
