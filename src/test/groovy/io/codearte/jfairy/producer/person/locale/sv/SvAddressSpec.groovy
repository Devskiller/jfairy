package io.codearte.jfairy.producer.person.locale.sv

import io.codearte.jfairy.Fairy
import io.codearte.jfairy.producer.person.Address
import spock.lang.Specification

import static org.apache.commons.lang3.SystemUtils.LINE_SEPARATOR

class SvAddressSpec extends Specification {

	private final int SEED = 7
	Fairy fairy;
	Address address

	def setup() {
		fairy = Fairy.builder().withRandom(new Random(SEED)).withLocale(Locale.forLanguageTag("SV")).build()
		address = fairy.person().getAddress()
	}

	def "should generate random street"() {
		expect:
			address.street() == "Trångsund"
	}

	def "should generate random streetNumber"() {
		expect:
			address.streetNumber() == "123"
	}

	def "should generate random apartmentNumber"() {
		expect:
			address.apartmentNumber() == ""
	}

	def "should generate random postalCode"() {
		expect:
			address.getPostalCode() == "152 86"
	}

	def "should generate random city"() {
		expect:
			address.getCity() == "Mölndal"
	}

	def "should return addressLine1 in sv locale format"() {
		expect:
			address.getAddressLine1() == "Trångsund 123"
	}

	def "should return addressLine2 in sv locale format"() {
		expect:
			address.getAddressLine2() == "152 86 Mölndal"
	}

	def "should return address in sv locale format"() {
		expect:
			address.toString() == "Trångsund 123" + LINE_SEPARATOR + "152 86 Mölndal"
	}

}
