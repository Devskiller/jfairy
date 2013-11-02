/*
 * Copyright (c) 2013. Codearte
 */

package eu.codearte.fairyland.producer.util;

import eu.codearte.fairyland.FairyException;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Locale;

import static java.lang.String.format;

public class TimeProvider {

    public GregorianCalendar getGregorianCalendar() {
        return new GregorianCalendar();
    }

    public int getYear() {
        return getGregorianCalendar().get(Calendar.YEAR);
    }

    public Calendar getCalendar(String date) {
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH);
        Date result;
        try {
            result = df.parse(date);
        } catch (ParseException e) {
            throw new FairyException(format("Parsing date %s exception", date), e);
        }
        Calendar calendar = GregorianCalendar.getInstance();
        calendar.setTime(result);
        return calendar;
    }

    public GregorianCalendar getGregorianCalendar(int year, int month, int day) {
        return new GregorianCalendar(year, month - 1, day);
    }

    public Date getDate() {
        return getGregorianCalendar().getTime();
    }

    public Date getDate(int year, int month, int day) {
        return getGregorianCalendar(year, month, day).getTime();
    }

}
