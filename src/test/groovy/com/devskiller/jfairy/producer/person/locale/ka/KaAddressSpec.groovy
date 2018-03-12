package com.devskiller.jfairy.producer.person.locale.ka

import spock.lang.Specification

import com.devskiller.jfairy.Fairy
import com.devskiller.jfairy.producer.person.Address

import static java.lang.System.lineSeparator

class KaAddressSpec extends Specification {
	private final int SEED = 8

	private Fairy fairy
	private Address address

	def setup() {
		Locale geLocale = new Locale.Builder().setLanguage("ka").build()
		fairy = Fairy.builder().withRandomSeed(SEED).withLocale(geLocale).build()
		address = fairy.person().address
	}

	def "should generate random street"() {
		expect:
		address.street == "გორკის ქუჩა"  // Gorki Street
	}

	def "should generate random streetNumber"() {
		expect:
		address.streetNumber == "184"
	}

	def "should generate random apartmentNumber"() {
		expect:
		address.apartmentNumber == "329"
	}

	def "should generate random postalCode"() {
		expect:
		address.postalCode == "1260"
	}

	def "should generate random city"() {
		expect:
		address.city == "რუსთავი"  // Rustavi
 	}

	def "should return addressLine1 in GE locale format"() {
		expect:
		address.addressLine1 == "1260, რუსთავი"  // ZIP, city
	}

	def "should return addressLine2 in GE locale format"() {
		expect:
		address.addressLine2 == "გორკის ქუჩა №184, ბინა 329"  // street & number & appartment
	}

	def "should return address in GE locale format"() {
		expect:
		address.toString() == "1260, რუსთავი${lineSeparator()}გორკის ქუჩა №184, ბინა 329"
	}
}
