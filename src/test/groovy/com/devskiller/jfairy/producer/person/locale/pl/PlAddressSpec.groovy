package com.devskiller.jfairy.producer.person.locale.pl

import spock.lang.Specification

import com.devskiller.jfairy.Fairy
import com.devskiller.jfairy.producer.person.Address

class PlAddressSpec extends Specification {

	private final int SEED = 10
	private Fairy fairy;
	private Address address

	def setup() {
		fairy = Fairy.builder().withRandomSeed(SEED).withLocale(Locale.forLanguageTag("PL")).build()
		address = fairy.person().address
	}

	def "should generate random street"() {
		expect:
			address.street == "Zakamarek"
	}

	def "should generate random streetNumber"() {
		expect:
			address.streetNumber == "115"
	}

    def "should generate random apartmentNumber"() {
        expect:
            address.apartmentNumber == "109"
    }

	def "should generate random postalCode"() {
		expect:
			address.postalCode == "80-469"
	}

	def "should generate random city"() {
		expect:
		    address.city == "Krasnobród"
	}

	def "should return addressLine1 in pl locale format"() {
		expect:
			address.addressLine1 == "Zakamarek 115, 109"
	}

	def "should return addressLine2 in pl locale format"() {
		expect:
			address.addressLine2 == "80-469 Krasnobród"
	}

	def "should return address in pl locale format"() {
		expect:
			address.toString() == "Zakamarek 115, 109" + System.lineSeparator() + "80-469 Krasnobród"
	}

}
