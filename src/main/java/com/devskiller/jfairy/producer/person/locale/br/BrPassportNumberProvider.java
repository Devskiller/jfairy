package com.devskiller.jfairy.producer.person.locale.br;

import org.apache.commons.lang3.RandomStringUtils;

import com.devskiller.jfairy.producer.person.PassportNumberProvider;

/**
 * @author Nelson Rodrigues da Silva Júnior
 * @since 15.10.2020
 */
public class BrPassportNumberProvider implements PassportNumberProvider {

	@Override
	public String get() {
		return RandomStringUtils.randomAlphanumeric(9);
	}
}
