package io.codearte.jfairy.producer.util.locale;

import io.codearte.jfairy.producer.util.CharConverter;
import org.apache.commons.lang3.StringUtils;

/**
 * @author Jakub Kubrynski
 */
public class PlCharConverter implements CharConverter {

	private static final String[] POLISH_CHARS = {"ą", "ć", "ę", "ł", "ń",  "ś", "ó", "ź", "ż"};
	private static final String[] LATIN_CHARS = {"a", "c", "e", "l", "n", "s", "o", "z", "z"};

	@Override
	public String romanize(String value) {
		return StringUtils.replaceEachRepeatedly(value, POLISH_CHARS, LATIN_CHARS);
	}
}
