/*
 * Copyright (c) 2013. Codearte
 */

package eu.codearte.fairyland.producer.util

import spock.lang.Specification
import spock.lang.Unroll

class DateProviderSpec extends Specification {

    @Unroll
    def "should parse date"() {

        setup:
        DateProvider dateProvider = new DateProvider();

        expect:
        def calendar = dateProvider.getCalendar(date);
        calendar.get(GregorianCalendar.YEAR) == year
        calendar.get(GregorianCalendar.MONTH) + 1 == month
        calendar.get(GregorianCalendar.DAY_OF_MONTH) == day

        where:
        date         | year | month | day
        "1900-12-31" | 1900 | 12    | 31
        "2011-12-21" | 2011 | 12    | 21
        "2011-1-1"   | 2011 | 1     | 1

    }
}
