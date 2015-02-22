/*
 * Copyright (c) 2013 Codearte and authors
 */

package io.codearte.jfairy.producer.person.locale

import io.codearte.jfairy.producer.BaseProducer
import io.codearte.jfairy.producer.DateProducer
import io.codearte.jfairy.producer.person.locale.pl.PolishIdentityCardNumber
import org.joda.time.DateTime
import spock.lang.Specification

import static io.codearte.jfairy.producer.person.locale.pl.PolishIdentityCardNumber.ISSUING_BEGIN
import static io.codearte.jfairy.producer.person.locale.pl.PolishIdentityCardNumber.LETTER_WEIGHT
import static io.codearte.jfairy.producer.person.locale.pl.PolishIdentityCardNumber.MAX_DIGITS_PART_VALUE

/**
 * @author mariuszs
 * @since 30.10.13.
 */
class PolishIdentityCardNumberSpec extends Specification {

	def baseProducer = Mock(BaseProducer)
	def dateGenerator = Mock(DateProducer)

	/**
	 * http://en.wikipedia.org/wiki/Polish_identity_card
	 */
	void "should generate proper id number"() {
		def max = (2013 - ISSUING_BEGIN) * LETTER_WEIGHT
		setup:
			baseProducer.randomBetween(max, max + LETTER_WEIGHT) >> ('A'..'Z').size() // ABA
			baseProducer.randomInt(MAX_DIGITS_PART_VALUE) >> 0
		when:
			PolishIdentityCardNumber generator = new PolishIdentityCardNumber(dateGenerator, baseProducer)
			def id = generator.generate(DateTime.parse("2013-12-12"))
		then:
			id == "ABA300000"
			generator.isValid(id)
	}
}
