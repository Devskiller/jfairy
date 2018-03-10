/*
 * Copyright (c) 2013. Codearte
 */

package io.codearte.jfairy.producer;

import javax.inject.Singleton;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.chrono.IsoChronology;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.time.format.ResolverStyle;
import java.time.format.SignStyle;

import static java.time.temporal.ChronoField.DAY_OF_MONTH;
import static java.time.temporal.ChronoField.HOUR_OF_DAY;
import static java.time.temporal.ChronoField.MINUTE_OF_HOUR;
import static java.time.temporal.ChronoField.MONTH_OF_YEAR;
import static java.time.temporal.ChronoField.NANO_OF_SECOND;
import static java.time.temporal.ChronoField.SECOND_OF_MINUTE;
import static java.time.temporal.ChronoField.YEAR;

@Singleton
public class TimeProvider {

	public int getCurrentYear() {
		return getCurrentTime().getYear();
	}

	public LocalDateTime getCurrentTime() {
		return LocalDateTime.now();
	}

	public LocalDate getCurrentDate() {
		return LocalDate.now();
	}
}
