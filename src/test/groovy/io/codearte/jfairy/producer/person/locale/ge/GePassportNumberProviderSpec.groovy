package io.codearte.jfairy.producer.person.locale.ge

import io.codearte.jfairy.producer.BaseProducer
import io.codearte.jfairy.producer.RandomGenerator
import spock.lang.Specification

class GePassportNumberProviderSpec extends Specification {
	private GePassportNumberProvider provider

	def setup() {
		BaseProducer baseProducer = new BaseProducer(new RandomGenerator())
		provider = new GePassportNumberProvider(baseProducer)
	}

	def isValidPassportNumber(String id) {
		id.matches("\\d{11}")
	}

	def "Should always generate proper passport number"() {
		expect:
		isValidPassportNumber(provider.get())
		where:
		i << (1..100)
	}
}
