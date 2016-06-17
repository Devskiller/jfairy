package io.codearte.jfairy.producer.person.locale.sv;

import io.codearte.jfairy.producer.person.PassportNumberProvider;
import org.apache.commons.lang3.RandomStringUtils;

/**
 * Swedish Passport Number (random number implementation)
 */
public class SvPassportNumberProvider implements PassportNumberProvider {

	@Override
	public String get() {
		return RandomStringUtils.randomNumeric(8);
	}
}
