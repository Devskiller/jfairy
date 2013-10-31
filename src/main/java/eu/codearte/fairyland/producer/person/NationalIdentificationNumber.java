/*
 * Copyright (c) 2013 Codearte and authors
 */

package eu.codearte.fairyland.producer.person;

import java.util.GregorianCalendar;

/**
 * @author mariuszs
 */
public interface NationalIdentificationNumber {

    String nationalIdentificationNumber(GregorianCalendar calendar, Sex sex);
}
