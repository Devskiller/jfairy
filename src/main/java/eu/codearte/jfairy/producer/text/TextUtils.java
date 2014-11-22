/*
 * Copyright (c) 2013. Codearte
 */
package eu.codearte.jfairy.producer.text;

import java.util.List;

import static com.google.common.base.Joiner.on;

public final class TextUtils {

	private TextUtils() {
	}

	public static String joinWithSpace(List<String> result) {
		return on(" ").join(result);
	}

}
