package io.codearte.jfairy.producer.person.locale.sv;

import io.codearte.jfairy.producer.person.PassportNumberProvider;
import org.apache.commons.lang3.RandomStringUtils;

/**
 * Swedish Passport Number is not yet supported
 */
public class SvPassportNumberProvider implements PassportNumberProvider {

	@Override
	public String get() {
		return "";
	}
}
