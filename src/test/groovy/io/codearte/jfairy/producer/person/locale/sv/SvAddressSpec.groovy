package io.codearte.jfairy.producer.person.locale.sv

import io.codearte.jfairy.Fairy
import io.codearte.jfairy.producer.person.Address
import spock.lang.Specification

import static org.apache.commons.lang3.SystemUtils.LINE_SEPARATOR

class SvAddressSpec extends Specification {

	private final int SEED = 7
	def Fairy fairy;
	def Address address

	def setup() {
		fairy = Fairy.builder().withRandom(new Random(SEED)).withLocale(Locale.forLanguageTag("SV")).build()
		address = fairy.person().getAddress()
	}

	def "should generate random street"() {
		expect:
			address.street() == "Götgatan"
	}

	def "should generate random streetNumber"() {
		expect:
			address.streetNumber() == "73"
	}

	def "should generate random apartmentNumber"() {
		expect:
			address.apartmentNumber() == ""
	}

	def "should generate random postalCode"() {
		expect:
			address.getPostalCode() == "528 66"
	}

	def "should generate random city"() {
		expect:
			address.getCity() == "Kristianstad"
	}

	def "should return addressLine1 in sv locale format"() {
		expect:
			address.getAddressLine1() == "Götgatan 73"
	}

	def "should return addressLine2 in sv locale format"() {
		expect:
			address.getAddressLine2() == "528 66 Kristianstad"
	}

	def "should return address in sv locale format"() {
		expect:
			address.toString() == "Götgatan 73" + LINE_SEPARATOR + "528 66 Kristianstad"
	}

}
