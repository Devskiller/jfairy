package com.devskiller.jfairy.producer.person.locale.en;

import org.apache.commons.lang3.RandomStringUtils;

import com.devskiller.jfairy.producer.person.PassportNumberProvider;

/**
 * @author Olga Maciaszek-Sharma
 * @since 15.03.15
 */
public class EnPassportNumberProvider implements PassportNumberProvider {

	@Override
	public String get() {
		return RandomStringUtils.randomAlphanumeric(9);
	}
}
