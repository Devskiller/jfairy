package io.codearte.jfairy.producer.person.locale.en

import io.codearte.jfairy.Fairy
import io.codearte.jfairy.producer.person.Address
import spock.lang.Specification

import static org.apache.commons.lang3.SystemUtils.LINE_SEPARATOR

class EnAddressSpec extends Specification {

	private final int SEED = 7
	def Fairy fairy;
	def Address address

	def setup() {
		fairy = Fairy.builder().withRandom(new Random(SEED)).withLocale(Locale.forLanguageTag("EN")).build()
		address = fairy.person().getAddress()
	}

	def "should generate random street"() {
		expect:
			address.street() == "Ford Street"
	}

	def "should generate random streetNumber"() {
		expect:
			address.streetNumber() == "11"
	}

    def "should generate random apartmentNumber"() {
        expect:
            address.apartmentNumber() == "313"
    }

	def "should generate random postalCode"() {
		expect:
			address.getPostalCode() == "28664"
	}

	def "should generate random city"() {
		expect:
		    address.getCity() == "San Francisco"
	}

	def "should return addressLine1 in sv locale format"() {
		expect:
			address.getAddressLine1() == "11 Ford Street APT 313"
	}

	def "should return addressLine2 in sv locale format"() {
		expect:
			address.getAddressLine2() == "San Francisco 28664"
	}

	def "should return address in sv locale format"() {
		expect:
			address.toString() == "11 Ford Street APT 313" + LINE_SEPARATOR + "San Francisco 28664"
	}

}
