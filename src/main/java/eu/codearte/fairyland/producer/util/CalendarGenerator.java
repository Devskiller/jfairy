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
  private final DateProvider dateProvider;

  public CalendarGenerator(RandomGenerator randomGenerator, DateProvider dateProvider) {
    this.randomGenerator = randomGenerator;
    this.dateProvider = dateProvider;
  }

  public Date randomDateInThePast() {
    return randomCalendarInThePast().getTime();
  }

  public Calendar randomCalendarInThePast() {

    GregorianCalendar calendar = dateProvider.getGregorianCalendar();

    calendar.roll(YEAR, -randomGenerator.randomInt(100));
    calendar.set(DAY_OF_YEAR, 1 + randomGenerator.randomInt(calendar.getActualMaximum(DAY_OF_YEAR) - 1));

    return calendar;
  }

}
