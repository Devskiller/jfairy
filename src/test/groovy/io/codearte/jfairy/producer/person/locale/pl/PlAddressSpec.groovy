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
		fairy = Fairy.builder().withRandom(new Random(SEED)).withLocale(Locale.forLanguageTag("PL")).build()
		address = fairy.person().address
	}

	def "should generate random street"() {
		expect:
			address.street == "Kwarciana"
	}

	def "should generate random streetNumber"() {
		expect:
			address.streetNumber == "92"
	}

    def "should generate random apartmentNumber"() {
        expect:
            address.apartmentNumber == ""
    }

	def "should generate random postalCode"() {
		expect:
			address.postalCode == "64-503"
	}

	def "should generate random city"() {
		expect:
		    address.city == "Milicz"
	}

	def "should return addressLine1 in pl locale format"() {
		expect:
			address.addressLine1 == "Kwarciana 92"
	}

	def "should return addressLine2 in pl locale format"() {
		expect:
			address.addressLine2 == "64-503 Milicz"
	}

	def "should return address in pl locale format"() {
		expect:
			address.toString() == "Kwarciana 92" + LINE_SEPARATOR + "64-503 Milicz"
	}

}
