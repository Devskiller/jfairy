/*
 * Copyright (c) 2013. Codearte
 */

package eu.codearte.fairyland.producer.util

import eu.codearte.fairyland.producer.RandomGenerator
import spock.lang.Specification

class RandomCalendarSpec extends Specification {

    def randomGenerator = Mock(RandomGenerator);
    def dateProvider = Mock(TimeProvider);
    def randomCalendar = new CalendarGenerator(randomGenerator, dateProvider)

    def "should generate 1 yanuary"() {

        setup:
        dateProvider.getGregorianCalendar() >> new TimeProvider().getCalendar("2013-10-20")
        randomGenerator.randomBetween(1, 100) >> 0
        randomGenerator.randomBetween(1, 365) >> 1

        when:
        def calendar = randomCalendar.randomCalendarInThePast();

        then:
        calendar.get(GregorianCalendar.YEAR) == 2013
        calendar.get(GregorianCalendar.MONTH) + 1 == 1
        calendar.get(GregorianCalendar.DAY_OF_MONTH) == 1

    }

    def "should generate 1 february"() {

        setup:
        dateProvider.getGregorianCalendar() >> new TimeProvider().getCalendar("2013-10-20")
        randomGenerator.randomBetween(1, 100) >> 10
        randomGenerator.randomBetween(1, 365) >> 32

        when:
        def calendar = randomCalendar.randomCalendarInThePast();

        then:
        calendar.get(GregorianCalendar.YEAR) == 2003
        calendar.get(GregorianCalendar.MONTH) + 1 == 2
        calendar.get(GregorianCalendar.DAY_OF_MONTH) == 1
    }

    def "should generate last day of the year"() {

        setup:
        dateProvider.getGregorianCalendar() >> new TimeProvider().getCalendar("2013-10-20")
        randomGenerator.randomBetween(1, 100) >> 20
        randomGenerator.randomBetween(1, 365) >> 365

        when:
        def calendar = randomCalendar.randomCalendarInThePast();

        then:
        calendar.get(GregorianCalendar.YEAR) == 1993
        calendar.get(GregorianCalendar.MONTH) + 1 == 12
        calendar.get(GregorianCalendar.DAY_OF_MONTH) == 31

    }
}
