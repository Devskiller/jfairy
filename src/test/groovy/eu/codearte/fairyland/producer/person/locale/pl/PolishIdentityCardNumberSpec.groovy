/*
 * Copyright (c) 2013 Codearte and authors
 */

package eu.codearte.fairyland.producer.person.locale.pl

import eu.codearte.fairyland.producer.RandomProducer
import org.joda.time.DateTime
import spock.lang.Specification

import static eu.codearte.fairyland.producer.person.locale.pl.PolishIdentityCardNumber.*

/**
 * @author mariuszs
 * @since 30.10.13.
 */
class PolishIdentityCardNumberSpec extends Specification {

	def baseProducer = Mock(RandomProducer)

	/**
	 * http://en.wikipedia.org/wiki/Polish_identity_card
	 */
	void "should generate proper id number"() {
		def max = (2013 - ISSUING_BEGIN) * LETTER_WEIGHT
		setup:
		baseProducer.randomBetween(max, max + LETTER_WEIGHT) >> ('A'..'Z').size() // ABA
		baseProducer.randomWithMax(MAX_DIGITS_PART_VALUE) >> 0
		when:
		PolishIdentityCardNumber generator = new PolishIdentityCardNumber(baseProducer)
		def id = generator.generate(DateTime.parse("2013-12-12"))
		then:
		id == "ABA300000"
		generator.isValid(id)
	}
}
