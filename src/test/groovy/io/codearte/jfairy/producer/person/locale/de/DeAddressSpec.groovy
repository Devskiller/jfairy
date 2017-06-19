package io.codearte.jfairy.producer.person.locale.de

import io.codearte.jfairy.Fairy
import io.codearte.jfairy.producer.person.Address
import spock.lang.Specification

import static org.apache.commons.lang3.SystemUtils.LINE_SEPARATOR

class DeAddressSpec extends Specification {

	private final int SEED = 7
	private Fairy fairy;
	private Address address

	def setup() {
		fairy = Fairy.builder().withRandom(new Random(SEED)).withLocale(Locale.GERMAN).build()
		address = fairy.person().address
	}

	def "should generate random street"() {
		expect:
			address.street == 'Messelweg'
	}

	def "should generate random streetNumber"() {
		expect:
			address.streetNumber == '176'
	}

	def "should generate random apartmentNumber"() {
		expect:
			address.apartmentNumber == ''
	}

	def "should generate random postalCode"() {
		expect:
			address.postalCode == '15286'
	}

	def "should generate random city"() {
		expect:
			address.city == 'Schlitz'
	}

	def "should return addressLine1 in de locale format"() {
		expect:
			address.addressLine1 == 'Messelweg 176'
	}

	def "should return addressLine2 in de locale format"() {
		expect:
			address.addressLine2 == '15286 Schlitz'
	}

	def "should return address in de locale format"() {
		expect:
			address.toString() == "Messelweg 176${LINE_SEPARATOR}15286 Schlitz"
	}

}
