/*
 * Copyright (c) 2013. Codearte
 */

package eu.codearte.fairyland.producer.util

import eu.codearte.fairyland.producer.RandomGenerator
import spock.lang.Specification
import spock.lang.Unroll

class RandomCalendarSpec extends Specification {

    def randomGenerator = Mock(RandomGenerator);
    def dateProvider = Mock(DateProvider);
    def randomCalendar = new RandomCalendar(randomGenerator, dateProvider)

    def "should generate 1 yanuary"() {

        setup:
        dateProvider.getGregorianCalendar() >> new DateProvider().getCalendar("2013-10-20")
        randomGenerator.randomInt(100) >> 0
        randomGenerator.randomInt(365) >> 0

        when:
        def calendar = randomCalendar.randomCalendarInThePast();

        then:
        calendar.get(GregorianCalendar.YEAR) == 2013
        calendar.get(GregorianCalendar.MONTH) + 1 == 1
        calendar.get(GregorianCalendar.DAY_OF_MONTH) == 1

    }

    def "should generate 1 february"() {

        setup:
        dateProvider.getGregorianCalendar() >> new DateProvider().getCalendar("2013-10-20")
        randomGenerator.randomInt(100) >> 10
        randomGenerator.randomInt(364) >> 31

        when:
        def calendar = randomCalendar.randomCalendarInThePast();

        then:
        calendar.get(GregorianCalendar.YEAR) == 2003
        calendar.get(GregorianCalendar.MONTH) + 1 == 2
        calendar.get(GregorianCalendar.DAY_OF_MONTH) == 1
    }

    def "should generate last day of the year"() {

        setup:
        dateProvider.getGregorianCalendar() >> new DateProvider().getCalendar("2013-10-20")
        randomGenerator.randomInt(100) >> 20
        randomGenerator.randomInt(364) >> 364

        when:
        def calendar = randomCalendar.randomCalendarInThePast();

        then:
        calendar.get(GregorianCalendar.YEAR) == 1993
        calendar.get(GregorianCalendar.MONTH) + 1 == 12
        calendar.get(GregorianCalendar.DAY_OF_MONTH) == 31

    }
}
