/*
 * Copyright (c) 2013 Codearte and authors
 */

package eu.codearte.fairyland.producer.person.pl

import eu.codearte.fairyland.producer.BaseProducer
import eu.codearte.fairyland.producer.person.locale.pl.PolishIdentityCardNumber
import eu.codearte.fairyland.producer.util.DateGenerator
import org.joda.time.DateTime
import spock.lang.Specification

import static eu.codearte.fairyland.producer.person.locale.pl.PolishIdentityCardNumber.BEGIN
import static eu.codearte.fairyland.producer.person.locale.pl.PolishIdentityCardNumber.PREFIXES_BY_YEAR

/**
 * @author mariuszs
 * @since 30.10.13.
 */
class PolishIdentityCardNumberSpec extends Specification {

	def baseProducer = Mock(BaseProducer)
	def dateGenerator = Mock(DateGenerator)

	/**
	 * http://en.wikipedia.org/wiki/Polish_identity_card
	 */
	void "should generate proper id number"() {
		def max = (2013 - BEGIN) * PREFIXES_BY_YEAR
		setup:
		baseProducer.randomBetween(max, max + PREFIXES_BY_YEAR) >> 26 // ABA
		baseProducer.randomBetween(0, 99999) >> 0
		when:
		PolishIdentityCardNumber generator = new PolishIdentityCardNumber(dateGenerator, baseProducer)
		def id = generator.generate(DateTime.now())
		then:
		id == "ABA300000"
		generator.isValid(id)
	}
}
