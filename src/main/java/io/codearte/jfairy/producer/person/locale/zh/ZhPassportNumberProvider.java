package io.codearte.jfairy.producer.person.locale.zh;

import io.codearte.jfairy.producer.person.PassportNumberProvider;
import org.apache.commons.lang3.RandomStringUtils;

/**
 * io.codearte.jfairy.producer.person.locale.zh.ZhPassportNumberProvider
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
