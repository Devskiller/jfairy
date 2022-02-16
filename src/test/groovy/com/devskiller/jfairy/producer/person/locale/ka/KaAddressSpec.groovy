package com.devskiller.jfairy.producer.person.locale.ka

import spock.lang.Specification

import com.devskiller.jfairy.Fairy
import com.devskiller.jfairy.producer.person.Address

import static java.lang.System.lineSeparator

class KaAddressSpec extends Specification {
	private final int SEED = 7

	private Fairy fairy
	private Address address

	def setup() {
		Locale geLocale = new Locale.Builder().setLanguage("ka").build()
		fairy = Fairy.builder().withRandomSeed(SEED).withLocale(geLocale).build()
		address = fairy.person().address
	}

	def "should generate random street"() {
		expect:
		address.street == "ვაჟა–ფშაველას გამზირი"
	}

	def "should generate random streetNumber"() {
		expect:
		address.streetNumber == "79"
	}

	def "should generate random apartmentNumber"() {
		expect:
		address.apartmentNumber == "87"
	}

	def "should generate random postalCode"() {
		expect:
		address.postalCode == "7681"
	}

	def "should generate random city"() {
		expect:
		address.city == "ზუგდიდი"
 	}

	def "should return addressLine1 in GE locale format"() {
		expect:
		address.addressLine1 == "7681, ზუგდიდი"  // ZIP, city
	}

	def "should return addressLine2 in GE locale format"() {
		expect:
		address.addressLine2 == "ვაჟა–ფშაველას გამზირი №79, ბინა 87"  // street & number & appartment
	}

	def "should return address in GE locale format"() {
		expect:
		address.toString() == "7681, ზუგდიდი${lineSeparator()}ვაჟა–ფშაველას გამზირი №79, ბინა 87"
	}
}
