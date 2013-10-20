/*
 * Copyright (c) 2013. Codearte
 */

package eu.codearte.fairyland.producer.util;

import eu.codearte.fairyland.producer.RandomGenerator;

import java.util.Calendar;
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

  public Calendar randomCalendarInThePast() {

    GregorianCalendar calendar = timeProvider.getGregorianCalendar();

    calendar.roll(YEAR, -randomGenerator.randomBetween(0, 100));
    int actualMaximumDay = calendar.getActualMaximum(DAY_OF_YEAR);
    System.out.println(calendar.getTime());
    calendar.set(DAY_OF_YEAR, randomGenerator.randomBetween(1, actualMaximumDay));

    return calendar;
  }

}
