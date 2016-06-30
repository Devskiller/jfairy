package io.codearte.jfairy.producer.person.locale.pl

import io.codearte.jfairy.Fairy
import io.codearte.jfairy.producer.person.Address
import spock.lang.Specification

import static org.apache.commons.lang3.SystemUtils.LINE_SEPARATOR

class PlAddressSpec extends Specification {

	private final int SEED = 7
	def Fairy fairy;
	def Address address

	def setup() {
		fairy = Fairy.builder().withRandom(new Random(SEED)).withLocale(Locale.forLanguageTag("PL")).build()
		address = fairy.person().address
	}

	def "should generate random street"() {
		expect:
			address.getStreet() == "Szulborska"
	}

	def "should generate random streetNumber"() {
		expect:
			address.getStreetNumber() == "11"
	}

    def "should generate random apartmentNumber"() {
        expect:
            address.getApartmentNumber() == ""
    }

	def "should generate random postalCode"() {
		expect:
			address.getPostalCode() == "91-528"
	}

	def "should generate random city"() {
		expect:
		    address.getCity() == "Cedzyna"
	}

	def "should return addressLine1 in pl locale format"() {
		expect:
			address.getAddressLine1() == "Szulborska 11"
	}

	def "should return addressLine2 in pl locale format"() {
		expect:
			address.getAddressLine2() == "91-528 Cedzyna"
	}

	def "should return address in pl locale format"() {
		expect:
			address.toString() == "Szulborska 11" + LINE_SEPARATOR + "91-528 Cedzyna"
	}

}
