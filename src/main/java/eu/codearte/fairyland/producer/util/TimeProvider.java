/*
 * Copyright (c) 2013. Codearte
 */

package eu.codearte.fairyland.producer.util;

import org.joda.time.DateTime;

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
