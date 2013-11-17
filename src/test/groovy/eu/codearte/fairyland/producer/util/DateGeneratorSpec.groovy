/*
 * Copyright (c) 2013 Codearte and authors
 */
package eu.codearte.fairyland.producer.util

import eu.codearte.fairyland.producer.BaseProducer
import org.joda.time.DateTime
import spock.lang.Specification
import spock.lang.Unroll

class DateGeneratorSpec extends Specification {

	private static final MAX_YEARS_IN_THE_PAST = 5

	private static final CURRENT_DATE = DateTime.parse("2013-11-09T01:16:00")
	private static final LATEST_DATE_IN_THE_PAST = DateTime.parse("2013-11-09T01:15:59")
	private static final SOME_DATE_IN_THE_PAST = DateTime.parse("2011-01-20T12:32:12")
	private static final FIVE_YEARS_EARLIER_DATE = DateTime.parse("2008-11-09T01:16:00")

	private static final LATEST_DATE_IN_THE_PAST_IN_MILLIS = LATEST_DATE_IN_THE_PAST.getMillis()
	private static final SOME_DATE_IN_THE_PAST_IN_MILLIS = SOME_DATE_IN_THE_PAST.getMillis()
	private static final FIVE_YEARS_EARLIER_DATE_IN_MILLIS = FIVE_YEARS_EARLIER_DATE.getMillis()

	private baseProducer = Mock(BaseProducer)
	private timeProviderMock = Mock(TimeProvider)
	private DateGenerator sut = new DateGenerator(baseProducer, timeProviderMock)

	def setup() {
		timeProviderMock.getCurrentDate() >> CURRENT_DATE
	}

	def "should generate date in the past"() {
		given:
		baseProducer.randomBetween(FIVE_YEARS_EARLIER_DATE_IN_MILLIS, LATEST_DATE_IN_THE_PAST_IN_MILLIS) >>
				SOME_DATE_IN_THE_PAST_IN_MILLIS
		when:
		def dateInThePast = sut.randomDateInThePast(MAX_YEARS_IN_THE_PAST)
		then:
		dateInThePast < CURRENT_DATE
		dateInThePast > FIVE_YEARS_EARLIER_DATE
		dateInThePast == SOME_DATE_IN_THE_PAST
	}

	def "should be able to reach minimum date for date in the past"() {
		given:
		baseProducer.randomBetween(FIVE_YEARS_EARLIER_DATE_IN_MILLIS, LATEST_DATE_IN_THE_PAST_IN_MILLIS) >>
				FIVE_YEARS_EARLIER_DATE_IN_MILLIS
		when:
		def dateInThePast = sut.randomDateInThePast(MAX_YEARS_IN_THE_PAST)
		then:
		dateInThePast == FIVE_YEARS_EARLIER_DATE
	}

	def "maximum date should be before now with defined offset for date in the past"() {
		given:
		baseProducer.randomBetween(FIVE_YEARS_EARLIER_DATE_IN_MILLIS, LATEST_DATE_IN_THE_PAST_IN_MILLIS) >>
				LATEST_DATE_IN_THE_PAST_IN_MILLIS
		when:
		def dateInThePast = sut.randomDateInThePast(MAX_YEARS_IN_THE_PAST)
		then:
		dateInThePast == LATEST_DATE_IN_THE_PAST
	}

	@Unroll def "should generate date between years #fromYear - #toYear"() {
		given:
		baseProducer.randomBetween(_, _) >> {args -> (args[1] + args[0]) / 2}
		expect:
		sut.randomDateBetweenYears(fromYear, toYear) == expectedDate
		where:
		fromYear | toYear || expectedDate
		2009     | 2010   || new DateTime("2009-12-31T23:59:59.999")
		2010     | 2010   || new DateTime("2010-07-02T12:59:59.999")
		2015     | 2020   || new DateTime("2017-12-31T23:59:59.999")
	}
}
