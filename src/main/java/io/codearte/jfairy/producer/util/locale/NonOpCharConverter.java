package io.codearte.jfairy.producer.util.locale;

import io.codearte.jfairy.producer.util.CharConverter;

/**
 * @author Jakub Kubrynski
 */
public class NonOpCharConverter implements CharConverter {

	@Override
	public String romanize(String value) {
		return value;
	}
}
