package com.devskiller.jfairy.producer.person.locale.sk

import spock.lang.Specification

import com.devskiller.jfairy.Fairy
import com.devskiller.jfairy.producer.person.Address

class SkAddressSpec extends Specification {

	private final int SEED = 8
	private Fairy fairy
	private Address address

	def setup() {
		fairy = Fairy.builder().withRandomSeed(SEED).withLocale(Locale.forLanguageTag("SK")).build()
		address = fairy.person().address
	}

	def "should generate random street"() {
		expect:
			address.street == "Zafírová"
	}

	def "should generate random streetNumber"() {
		expect:
			address.streetNumber == "35"
	}

    def "should generate random apartmentNumber"() {
        expect:
            address.apartmentNumber == "34"
    }

	def "should generate random postalCode"() {
		expect:
			address.postalCode == "839 68"
	}

	def "should generate random city"() {
		expect:
		    address.city == "Žarnovica"
	}

	def "should return addressLine1 in pl locale format"() {
		expect:
			address.addressLine1 == "Zafírová 35, 34"
	}

	def "should return addressLine2 in pl locale format"() {
		expect:
			address.addressLine2 == "839 68 Žarnovica"
	}

	def "should return address in pl locale format"() {
		expect:
			address.toString() == "Zafírová 35, 34" + System.lineSeparator() + "839 68 Žarnovica"
	}

}
