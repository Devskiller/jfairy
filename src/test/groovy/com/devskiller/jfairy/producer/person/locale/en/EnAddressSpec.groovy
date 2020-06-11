package com.devskiller.jfairy.producer.person.locale.en

import spock.lang.Specification

import com.devskiller.jfairy.Fairy
import com.devskiller.jfairy.producer.person.Address

class EnAddressSpec extends Specification {

	private final int SEED = 8
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
			address.streetNumber == "184"
	}

    def "should generate random apartmentNumber"() {
        expect:
            address.apartmentNumber == "32"
    }

	def "should generate random postalCode"() {
		expect:
			address.postalCode == "60772"
	}

	def "should generate random city"() {
		expect:
		    address.city == "Miami"
	}

	def "should return addressLine1 in en locale format"() {
		expect:
			address.addressLine1 == "184 Washington Walk APT 32"
	}

	def "should return addressLine2 in en locale format"() {
		expect:
			address.addressLine2 == "Miami 60772"
	}

	def "should return address in en locale format"() {
		expect:
            address.toString() == "184 Washington Walk APT 32" + System.lineSeparator() + "Miami 60772"
	}

}
