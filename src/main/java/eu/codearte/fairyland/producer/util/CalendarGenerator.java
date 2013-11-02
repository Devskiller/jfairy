/*
 * Copyright (c) 2013. Codearte
 */

package eu.codearte.fairyland.producer.util;

import eu.codearte.fairyland.producer.RandomGenerator;

import java.util.Date;
import java.util.GregorianCalendar;

import static java.util.Calendar.DAY_OF_YEAR;
import static java.util.Calendar.YEAR;

public class CalendarGenerator {

    private final RandomGenerator randomGenerator;
    private final TimeProvider timeProvider;

    public CalendarGenerator(RandomGenerator randomGenerator, TimeProvider timeProvider) {
        this.randomGenerator = randomGenerator;
        this.timeProvider = timeProvider;
    }

    public Date randomDateInThePast() {
        return randomCalendarInThePast().getTime();
    }

    public GregorianCalendar randomCalendarInThePast() {
        return randomCalendarInThePast(100);
    }

    public GregorianCalendar randomCalendarInThePast(int maxYear) {

        GregorianCalendar calendar = timeProvider.getGregorianCalendar();

        calendar.roll(YEAR, -randomGenerator.randomBetween(1, maxYear));

        fillDay(calendar);

        return calendar;
    }

    public GregorianCalendar randomCalendarBetweenYears(int from, int to) {

        GregorianCalendar calendar = timeProvider.getGregorianCalendar();

        calendar.set(YEAR, randomGenerator.randomBetween(from, to));

        fillDay(calendar);

        return calendar;
    }

    private void fillDay(GregorianCalendar calendar) {
        int maximumDay = calendar.getActualMaximum(DAY_OF_YEAR);
        calendar.set(DAY_OF_YEAR, randomGenerator.randomBetween(1, maximumDay));
    }

}
