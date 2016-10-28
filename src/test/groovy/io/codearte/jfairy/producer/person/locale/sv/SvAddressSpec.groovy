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
		address = fairy.person().address
	}

	def "should generate random street"() {
		expect:
			address.street == "Björngårdsgatan"
	}

	def "should generate random streetNumber"() {
		expect:
			address.streetNumber == "79"
	}

	def "should generate random apartmentNumber"() {
		expect:
			address.apartmentNumber == ""
	}

	def "should generate random postalCode"() {
		expect:
			address.postalCode == "286 64"
	}

	def "should generate random city"() {
		expect:
			address.city == "Södertälje"
	}

	def "should return addressLine1 in sv locale format"() {
		expect:
			address.addressLine1 == "Björngårdsgatan 79"
	}

	def "should return addressLine2 in sv locale format"() {
		expect:
			address.addressLine2 == "286 64 Södertälje"
	}

	def "should return address in sv locale format"() {
		expect:
			address.toString() == "Björngårdsgatan 79" + LINE_SEPARATOR + "286 64 Södertälje"
	}

}
