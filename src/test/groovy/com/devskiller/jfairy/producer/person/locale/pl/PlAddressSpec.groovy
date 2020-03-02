package com.devskiller.jfairy.producer.person.locale.pl

import spock.lang.Specification

import com.devskiller.jfairy.Fairy
import com.devskiller.jfairy.producer.person.Address

class PlAddressSpec extends Specification {

	private final int SEED = 8
	private Fairy fairy;
	private Address address

	def setup() {
		fairy = Fairy.builder().withRandomSeed(SEED).withLocale(Locale.forLanguageTag("PL")).build()
		address = fairy.person().address
	}

	def "should generate random street"() {
		expect:
			address.street == "Długorzeczna"
	}

	def "should generate random streetNumber"() {
		expect:
			address.streetNumber == "62"
	}

    def "should generate random apartmentNumber"() {
        expect:
            address.apartmentNumber == "89"
    }

	def "should generate random postalCode"() {
		expect:
			address.postalCode == "39-682"
	}

	def "should generate random city"() {
		expect:
		    address.city == "Zławieś Wielka"
	}

	def "should return addressLine1 in pl locale format"() {
		expect:
			address.addressLine1 == "Długorzeczna 62, 89"
	}

	def "should return addressLine2 in pl locale format"() {
		expect:
			address.addressLine2 == "39-682 Zławieś Wielka"
	}

	def "should return address in pl locale format"() {
		expect:
			address.toString() == "Długorzeczna 62, 89" + System.lineSeparator() + "39-682 Zławieś Wielka"
	}

}
