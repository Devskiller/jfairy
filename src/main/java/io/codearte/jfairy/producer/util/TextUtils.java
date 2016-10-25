/*
 * Copyright (c) 2013. Codearte
 */
package io.codearte.jfairy.producer.util;

import java.util.List;

import static com.google.common.base.Joiner.on;

public final class TextUtils {

	private TextUtils() {
	}

	public static String joinWithSpace(List<String> result) {
		return on(" ").join(result);
	}

	public static String stripAccents(String s) {
		// Replace polish character ł since bug https://issues.apache.org/jira/browse/LANG-1120
		return org.apache.commons.lang3.StringUtils.stripAccents(s).replaceAll("ł", "l").replaceAll("Ł", "L");
	}

}
