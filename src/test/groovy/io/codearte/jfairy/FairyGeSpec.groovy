package io.codearte.jfairy

import io.codearte.jfairy.producer.person.Person
import spock.lang.Specification

class FairyGeSpec extends Specification {

	private final int SEED = 2
	private Locale geLocale = new Locale.Builder().setLanguage("ka").setRegion("ge").setScript("Geor").build();
	private Fairy fairy = Fairy.builder().withRandomSeed(SEED).withLocale(geLocale).build()

	def "Should create Georgian name"() {
		when:
		Person person = fairy.person();
		then:
		person.fullName == 'ბაადურ აბრამიძე'  // Baadur Abramidze
	}

	def "Should create Georgian street"() {
		when:
		Person person = fairy.person();
		then:
		person.address.street == 'აგლაძის ქუჩა' // Agladze Street
	}

	def "Should create Georgian city"() {
		when:
		Person person = fairy.person();
		then:
		person.address.city == 'საგარეჯო' // Sagarejo
	}

	def "Should create Georgian ID card"() {
		when:
		Person person = fairy.person();
		then:
		person.nationalIdentityCardNumber == '47YR57825'
	}
}
