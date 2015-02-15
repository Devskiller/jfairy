package io.codearte.jfairy.producer.person.locale.en;

import io.codearte.jfairy.producer.person.PassportNumberProvider;
import org.apache.commons.lang3.RandomStringUtils;

/**
 * @author omaciaszek
 * @since 15.03.15
 */
public class EnPassportNumberProvider implements PassportNumberProvider {

	@Override
	public String get() {
		return RandomStringUtils.randomAlphanumeric(9);
	}
}
