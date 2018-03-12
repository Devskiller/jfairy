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
		address.street == "აგლაძის ქუჩა"
	}

	def "should generate random streetNumber"() {
		expect:
		address.streetNumber == "23"
	}

	def "should generate random apartmentNumber"() {
		expect:
		address.apartmentNumber == "210"
	}

	def "should generate random postalCode"() {
		expect:
		address.postalCode == "8126"
	}

	def "should generate random city"() {
		expect:
		address.city == "ქობულეთი"
 	}

	def "should return addressLine1 in GE locale format"() {
		expect:
		address.addressLine1 == "8126, ქობულეთი"  // ZIP, city
	}

	def "should return addressLine2 in GE locale format"() {
		expect:
		address.addressLine2 == "აგლაძის ქუჩა №23, ბინა 210"  // street & number & appartment
	}

	def "should return address in GE locale format"() {
		expect:
		address.toString() == "8126, ქობულეთი${lineSeparator()}აგლაძის ქუჩა №23, ბინა 210"
	}
}
