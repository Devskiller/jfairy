package com.devskiller.jfairy.producer.person.locale.zh;

import org.apache.commons.lang3.RandomStringUtils;

import com.devskiller.jfairy.producer.person.PassportNumberProvider;

/**
 * com.devskiller.jfairy.producer.person.locale.zh.ZhPassportNumberProvider
 *
 * @author lhfcws
 * @since 2017/3/2
 */
public class ZhPassportNumberProvider implements PassportNumberProvider {

	@Override
	public String get() {
		return RandomStringUtils.randomAlphanumeric(9);
	}
}
