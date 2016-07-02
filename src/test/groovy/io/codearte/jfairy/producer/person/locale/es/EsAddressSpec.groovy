package io.codearte.jfairy.producer.person.locale.es

import io.codearte.jfairy.Fairy
import io.codearte.jfairy.producer.person.Address
import spock.lang.Specification

import static org.apache.commons.lang3.SystemUtils.LINE_SEPARATOR

class EsAddressSpec extends Specification {

	private final int SEED = 7
	def Fairy fairy;
	def Address address

	def setup() {
		fairy = Fairy.builder().withRandom(new Random(SEED)).withLocale(Locale.forLanguageTag("ES")).build()
		address = fairy.person().address
	}

	def "should generate random street"() {
		expect:
			address.street() == "Vieja"
	}

	def "should generate random streetNumber"() {
		expect:
			address.streetNumber() == "39"
	}

	def "should generate random apartmentNumber"() {
		expect:
			address.apartmentNumber() == "327"
	}

	def "should generate random postalCode"() {
		expect:
			address.getPostalCode() == "33.915"
	}

	def "should generate random city"() {
		expect:
			address.getCity() == "Cáceres"
	}

	def "should return addressLine1 in es locale format"() {
		expect:
			address.getAddressLine1() == "Vieja, 39 327"
	}

	def "should return addressLine2 in es locale format"() {
		expect:
			address.getAddressLine2() == "33.915 Cáceres"
	}

	def "should return address in es locale format"() {
		expect:
			address.toString() == "Vieja, 39 327" + LINE_SEPARATOR + "33.915 Cáceres"
	}

}
