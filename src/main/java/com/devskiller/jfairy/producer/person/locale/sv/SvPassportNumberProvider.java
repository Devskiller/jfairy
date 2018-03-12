package com.devskiller.jfairy.producer.person.locale.sv;

import org.apache.commons.lang3.RandomStringUtils;

import com.devskiller.jfairy.producer.person.PassportNumberProvider;

/**
 * Swedish Passport Number (random number implementation)
 */
public class SvPassportNumberProvider implements PassportNumberProvider {

	@Override
	public String get() {
		return RandomStringUtils.randomNumeric(8);
	}
}
