package com.devskiller.jfairy.producer.person.locale.de

import spock.lang.Specification

import com.devskiller.jfairy.Fairy
import com.devskiller.jfairy.producer.person.Address

class DeAddressSpec extends Specification {

	private final int SEED = 7
	private Fairy fairy;
	private Address address

	def setup() {
		fairy = Fairy.builder().withRandomSeed(SEED).withLocale(Locale.GERMAN).build()
		address = fairy.person().address
	}

	def "should generate random street"() {
		expect:
			address.street == 'Unter dem Ahorn'
	}

	def "should generate random streetNumber"() {
		expect:
			address.streetNumber == '19'
	}

	def "should generate random apartmentNumber"() {
		expect:
			address.apartmentNumber == '277'
	}

	def "should generate random postalCode"() {
		expect:
			address.postalCode == '45692'
	}

	def "should generate random city"() {
		expect:
			address.city == 'Hainsbach'
	}

	def "should return addressLine1 in de locale format"() {
		expect:
			address.addressLine1 == 'Unter dem Ahorn 19, 277'
	}

	def "should return addressLine2 in de locale format"() {
		expect:
			address.addressLine2 == '45692 Hainsbach'
	}

	def "should return address in de locale format"() {
		expect:
			address.toString() == "Unter dem Ahorn 19, 277${System.lineSeparator()}45692 Hainsbach"
	}

}
