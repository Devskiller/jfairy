/*
 * Copyright (c) 2013 Codearte and authors
 */

package org.jfairy.producer.person;

import org.joda.time.DateTime;

/**
 * @author mariuszs
 * @since 31.10.13.
 */
public interface NationalIdentityCardNumber {

	String generate(DateTime date);

	String generate();
}
