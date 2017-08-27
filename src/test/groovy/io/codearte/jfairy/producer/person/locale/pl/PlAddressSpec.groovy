package io.codearte.jfairy.producer.person.locale.pl

import io.codearte.jfairy.Fairy
import io.codearte.jfairy.producer.person.Address
import spock.lang.Specification

import static org.apache.commons.lang3.SystemUtils.LINE_SEPARATOR

class PlAddressSpec extends Specification {

	private final int SEED = 7
	private Fairy fairy;
	private Address address

	def setup() {
		fairy = Fairy.builder().withRandomSeed(SEED).withLocale(Locale.forLanguageTag("PL")).build()
		address = fairy.person().address
	}

	def "should generate random street"() {
		expect:
			address.street == "Zespołowa"
	}

	def "should generate random streetNumber"() {
		expect:
			address.streetNumber == "76"
	}

    def "should generate random apartmentNumber"() {
        expect:
            address.apartmentNumber == "19"
    }

	def "should generate random postalCode"() {
		expect:
			address.postalCode == "66-450"
	}

	def "should generate random city"() {
		expect:
		    address.city == "Skaryszew"
	}

	def "should return addressLine1 in pl locale format"() {
		expect:
			address.addressLine1 == "Zespołowa 76, 19"
	}

	def "should return addressLine2 in pl locale format"() {
		expect:
			address.addressLine2 == "66-450 Skaryszew"
	}

	def "should return address in pl locale format"() {
		expect:
			address.toString() == "Zespołowa 76, 19" + System.lineSeparator() + "66-450 Skaryszew"
	}

}
