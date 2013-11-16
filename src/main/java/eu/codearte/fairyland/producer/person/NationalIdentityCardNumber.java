/*
 * Copyright (c) 2013 Codearte and authors
 */

package eu.codearte.fairyland.producer.person;

import org.joda.time.DateTime;

/**
 * @author mariuszs
 * @since 31.10.13.
 */
public interface NationalIdentityCardNumber {

	public String generate(DateTime date);

	String generate();
}
