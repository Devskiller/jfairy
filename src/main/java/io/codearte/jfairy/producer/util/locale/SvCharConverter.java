package io.codearte.jfairy.producer.util.locale;

import io.codearte.jfairy.producer.util.CharConverter;
import org.apache.commons.lang3.StringUtils;

public class SvCharConverter implements CharConverter {

	private static final String[] SWEDISH_CHARS = {"å", "ä", "ö", "é", "á"};
	private static final String[] LATIN_CHARS = {"a", "a", "o", "e", "a"};

	@Override
	public String romanize(String value) {
		return StringUtils.replaceEachRepeatedly(value, SWEDISH_CHARS, LATIN_CHARS);
	}
}
