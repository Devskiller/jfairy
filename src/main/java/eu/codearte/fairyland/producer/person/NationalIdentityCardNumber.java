/*
 * Copyright (c) 2013 Codearte and authors
 */

package eu.codearte.fairyland.producer.person;

import java.util.GregorianCalendar;

/**
 * @author mariuszs
 * @since 31.10.13.
 */
public interface NationalIdentityCardNumber {

    public String identityNumber(GregorianCalendar calendar);
}
