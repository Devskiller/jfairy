package com.devskiller.jfairy.producer.person.locale.es;

import org.apache.commons.lang3.RandomStringUtils;

import com.devskiller.jfairy.producer.person.PassportNumberProvider;

/**
 * @author graux
 * @since 26/04/2015
 */
public class EsPassportNumberProvider implements PassportNumberProvider {

	@Override
	public String get() {
		return RandomStringUtils.randomAlphanumeric(9);
	}
}
