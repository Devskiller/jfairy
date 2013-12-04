/*
 * Copyright (c) 2013 Codearte and authors
 */

package org.jfairy.producer.person;

import org.joda.time.DateTime;

/**
 * @author mariuszs
 */
public interface NationalIdentificationNumber {

	String generate(DateTime date, Person.Sex sex);

	String generate();
}
