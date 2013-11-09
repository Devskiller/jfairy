/*
 * Copyright (c) 2013 Codearte and authors
 */

package eu.codearte.fairyland.producer.person;

import org.joda.time.DateTime;

/**
 * @author mariuszs
 */
public interface NationalIdentificationNumber {

    String generate(DateTime date, Sex sex);
}
