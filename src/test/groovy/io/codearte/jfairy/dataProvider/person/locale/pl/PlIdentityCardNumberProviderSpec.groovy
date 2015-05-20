/*
 * Copyright (c) 2013 Codearte and authors
 */

package io.codearte.jfairy.dataProvider.person.locale.pl

import io.codearte.jfairy.dataProvider.BaseProducer
import io.codearte.jfairy.dataProvider.DateProducer
import org.joda.time.DateTime
import spock.lang.Specification

import static PlIdentityCardNumberProvider.ISSUING_BEGIN
import static PlIdentityCardNumberProvider.LETTER_WEIGHT
import static PlIdentityCardNumberProvider.MAX_DIGITS_PART_VALUE

/**
 * @author mariuszs
 * @since 30.10.13.
 */
class PlIdentityCardNumberProviderSpec extends Specification {

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
			PlIdentityCardNumberProvider generator = new PlIdentityCardNumberProvider(dateGenerator, baseProducer)
			def id = generator.get(DateTime.parse("2013-12-12"))
		then:
			id == "ABA300000"
			generator.isValid(id)
	}
}
