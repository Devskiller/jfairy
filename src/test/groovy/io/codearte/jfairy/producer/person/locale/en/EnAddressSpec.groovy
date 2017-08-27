package io.codearte.jfairy.producer.person.locale.en

import io.codearte.jfairy.Fairy
import io.codearte.jfairy.producer.person.Address
import spock.lang.Specification

import static org.apache.commons.lang3.SystemUtils.LINE_SEPARATOR

class EnAddressSpec extends Specification {

	private final int SEED = 7
	private Fairy fairy;
	private Address address

	def setup() {
		fairy = Fairy.builder().withRandomSeed(SEED).withLocale(Locale.forLanguageTag("EN")).build()
		address = fairy.person().address
	}

	def "should generate random street"() {
		expect:
			address.street == "Aster Court"
	}

	def "should generate random streetNumber"() {
		expect:
			address.streetNumber == "157"
	}

    def "should generate random apartmentNumber"() {
        expect:
            address.apartmentNumber == ""
    }

	def "should generate random postalCode"() {
		expect:
			address.postalCode == "86645"
	}

	def "should generate random city"() {
		expect:
		    address.city == "San Francisco"
	}

	def "should return addressLine1 in en locale format"() {
		expect:
			address.addressLine1 == "157 Aster Court"
	}

	def "should return addressLine2 in en locale format"() {
		expect:
			address.addressLine2 == "San Francisco 86645"
	}

	def "should return address in en locale format"() {
		expect:
            address.toString() == "157 Aster Court" + System.lineSeparator() + "San Francisco 86645"
	}

}
