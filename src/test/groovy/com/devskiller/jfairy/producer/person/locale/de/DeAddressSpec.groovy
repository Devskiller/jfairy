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
			address.street == 'Sautterweg'
	}

	def "should generate random streetNumber"() {
		expect:
			address.streetNumber == '21'
	}

	def "should generate random apartmentNumber"() {
		expect:
			address.apartmentNumber == '50'
	}

	def "should generate random postalCode"() {
		expect:
			address.postalCode == '71138'
	}

	def "should generate random city"() {
		expect:
			address.city == 'Maxhütte Haidhof'
	}

	def "should return addressLine1 in de locale format"() {
		expect:
			address.addressLine1 == 'Sautterweg 21, 50'
	}

	def "should return addressLine2 in de locale format"() {
		expect:
			address.addressLine2 == '71138 Maxhütte Haidhof'
	}

	def "should return address in de locale format"() {
		expect:
			address.toString() == "Sautterweg 21, 50${System.lineSeparator()}71138 Maxhütte Haidhof"
	}

}
