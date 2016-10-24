package io.codearte.jfairy.producer.person

import io.codearte.jfairy.data.DataMaster
import io.codearte.jfairy.producer.BaseProducer
import spock.lang.Specification

import static io.codearte.jfairy.producer.person.PersonProvider.PERSONAL_EMAIL

class EmailProviderSpec extends Specification {

	DataMaster dataMaster = Mock(DataMaster)
	BaseProducer baseProducer = Mock(BaseProducer)

	def setup() {
		dataMaster.getRandomValue(PERSONAL_EMAIL) >> "mail.com"
	}

	def "should remove spaces from firstName and lastName in email"() {
		given:
			baseProducer.randomBetween(1, 3) >> 1
			EmailProvider emailProvider = new EmailProvider(dataMaster, baseProducer, "Emilie Agneta", "Vojnov Allerstrand");

		when:
			String email = emailProvider.get();

		then:
			email == "emilieagnetavojnovallerstrand@mail.com"
	}

	def "should replace spaces with dots from firstName and lastName in email"() {
		given:
			baseProducer.randomBetween(1, 3) >> 2
			EmailProvider emailProvider = new EmailProvider(dataMaster, baseProducer, "Emilie Agneta", "Vojnov Allerstrand");

		when:
			String email = emailProvider.get();

		then:
			email == "emilie.agneta.vojnov.allerstrand@mail.com"
	}

	def "should replace spaces with dots from lastName in email"() {
		given:
			baseProducer.randomBetween(1, 3) >> 3
			EmailProvider emailProvider = new EmailProvider(dataMaster, baseProducer, "Emilie Agneta", "Vojnov Allerstrand");

		when:
			String email = emailProvider.get();

		then:
			email == "vojnovallerstrand@mail.com"
	}

	def "should strip accents from email"() {
		given:
			baseProducer.randomBetween(1, 3) >> 2
			EmailProvider emailProvider = new EmailProvider(dataMaster, baseProducer, "åäöéáąćęłńśóźż", "åäöéáąćęłńśóźż");

		when:
			String email = emailProvider.get();

		then:
			email == "aaoeaacelnsozz.aaoeaacelnsozz@mail.com"
	}
}
