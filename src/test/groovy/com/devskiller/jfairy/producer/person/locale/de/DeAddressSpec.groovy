package com.devskiller.jfairy.producer.person.locale.de

import spock.lang.Specification

import com.devskiller.jfairy.Fairy
import com.devskiller.jfairy.producer.person.Address

class DeAddressSpec extends Specification {

	private final int SEED = 8
	private Fairy fairy;
	private Address address

	def setup() {
		fairy = Fairy.builder().withRandomSeed(SEED).withLocale(Locale.GERMAN).build()
		address = fairy.person().address
	}

	def "should generate random street"() {
		expect:
			address.street == 'Sonnenburger Weg'
	}

	def "should generate random streetNumber"() {
		expect:
			address.streetNumber == '99'
	}

	def "should generate random apartmentNumber"() {
		expect:
			address.apartmentNumber == ''
	}

	def "should generate random postalCode"() {
		expect:
			address.postalCode == '98126'
	}

	def "should generate random city"() {
		expect:
			address.city == 'Wetschen'
	}

	def "should return addressLine1 in de locale format"() {
		expect:
			address.addressLine1 == 'Sonnenburger Weg 99'
	}

	def "should return addressLine2 in de locale format"() {
		expect:
			address.addressLine2 == '98126 Wetschen'
	}

	def "should return address in de locale format"() {
		expect:
			address.toString() == "Sonnenburger Weg 99${System.lineSeparator()}98126 Wetschen"
	}

}
