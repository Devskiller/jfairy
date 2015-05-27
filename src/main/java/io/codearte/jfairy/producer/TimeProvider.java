/*
 * Copyright (c) 2013. Codearte
 */

package io.codearte.jfairy.producer;

import org.joda.time.DateTime;

import javax.inject.Singleton;

@Singleton
public class TimeProvider {

	public int getCurrentYear() {
		return getCurrentDate().getYear();
	}

	public DateTime getDateForString(String date) {
		return DateTime.parse(date);
	}

	public DateTime getCurrentDate() {
		return DateTime.now();
	}
}
