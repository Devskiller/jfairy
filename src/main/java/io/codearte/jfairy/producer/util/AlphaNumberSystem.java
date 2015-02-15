package io.codearte.jfairy.producer.util;

public final class AlphaNumberSystem {

	private static final char[] DIGITS = {
			'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M',
			'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z'
	};

	private AlphaNumberSystem() {
	}

	public static String convertToString(final int numberToConvert, final int base) {
		int number = numberToConvert;
		final char[] buffer = new char[(numberToConvert / DIGITS.length) + 1];
		int charPosition = buffer.length - 1;

		if (number == 0) {
			buffer[charPosition--] = DIGITS[number];
		} else {
			while (number > 0) {
				buffer[charPosition--] = DIGITS[number % base];
				number /= base;
			}
		}

		return new String(buffer, charPosition + 1, buffer.length - charPosition - 1);
	}

}
