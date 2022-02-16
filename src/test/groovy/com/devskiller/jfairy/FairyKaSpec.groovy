package com.devskiller.jfairy

import spock.lang.Specification

import com.devskiller.jfairy.producer.person.Person

class FairyKaSpec extends Specification {

	private final int SEED = 2
	private Locale geLocale = new Locale.Builder().setLanguage("ka").setRegion("ge").setScript("Geor").build()
	private Fairy fairy = Fairy.builder().withRandomSeed(SEED).withLocale(geLocale).build()

	def "Should create Georgian name"() {
		when:
		Person person = fairy.person()
		then:
		person.fullName == 'ბაადურ აბრამიძე'  // Baadur Abramidze
	}

	def "Should create Georgian street"() {
		when:
		Person person = fairy.person()
		then:
		person.address.street == 'კოსტავას' // Kostava Street
	}

	def "Should create Georgian city"() {
		when:
		Person person = fairy.person()
		then:
		person.address.city == 'გორი' // Gori
	}

	def "Should create Georgian ID card"() {
		when:
		Person person = fairy.person()
		then:
		person.nationalIdentityCardNumber == 'Nხ4686471'
	}
}
