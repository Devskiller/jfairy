package io.codearte.jfairy.producer.company.locale.ka

import io.codearte.jfairy.producer.BaseProducer
import io.codearte.jfairy.producer.RandomGenerator
import spock.lang.Specification

class KaVATIdentificationNumberProviderSpec extends Specification {

	private KaVATIdentificationNumberProvider provider

	def setup() {
		BaseProducer baseProducer = new BaseProducer(new RandomGenerator())
		provider = new KaVATIdentificationNumberProvider(baseProducer)
	}

	private static final Set<String> ALLOWED_PREFIXES = [ '2' ,'4' ].toSet()

	def isVatIdValid(String id) {
		return id.length() == 9 && ALLOWED_PREFIXES.contains( id[ 0..0 ] )
	}

	def "Should always generate proper VAT id number"() {
		expect:
		isVatIdValid(provider.get())
		where:
		i << (1..100)
	}
}
