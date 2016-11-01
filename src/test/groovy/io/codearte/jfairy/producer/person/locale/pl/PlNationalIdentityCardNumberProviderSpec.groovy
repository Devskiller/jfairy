/*
 * Copyright (c) 2013 Codearte and authors
 */

package io.codearte.jfairy.producer.person.locale.pl

import io.codearte.jfairy.producer.BaseProducer
import io.codearte.jfairy.producer.DateProducer
import org.joda.time.DateTime
import spock.lang.Specification

import static PlNationalIdentityCardNumberProvider.ISSUING_BEGIN
import static PlNationalIdentityCardNumberProvider.LETTER_WEIGHT
import static PlNationalIdentityCardNumberProvider.MAX_DIGITS_PART_VALUE

/**
 * @author mariuszs
 * @since 30.10.13.
 */
class PlNationalIdentityCardNumberProviderSpec extends Specification {

	private BaseProducer baseProducer = Mock(BaseProducer)
	private DateProducer dateGenerator = Mock(DateProducer)

	/**
	 * http://en.wikipedia.org/wiki/Polish_identity_card
	 */
	void "should generate proper id number"() {
		int max = (2013 - ISSUING_BEGIN) * LETTER_WEIGHT
		setup:
			baseProducer.randomBetween(max, max + LETTER_WEIGHT) >> ('A'..'Z').size() // ABA
			baseProducer.randomInt(MAX_DIGITS_PART_VALUE) >> 0
		when:
			PlNationalIdentityCardNumberProvider generator = new PlNationalIdentityCardNumberProvider(dateGenerator, baseProducer)
			String id = generator.get(DateTime.parse("2013-12-12"))
		then:
			id == "ABA300000"
			generator.isValid(id)
	}
}
