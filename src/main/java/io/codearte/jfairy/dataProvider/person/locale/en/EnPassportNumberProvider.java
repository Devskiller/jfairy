package io.codearte.jfairy.dataProvider.person.locale.en;

import io.codearte.jfairy.dataProvider.person.PassportNumberProvider;
import org.apache.commons.lang3.RandomStringUtils;

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
