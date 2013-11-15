/*
 * Copyright (c) 2013 Codearte and authors
 */

package eu.codearte.fairyland.producer.person.pl;

/**
 * @author mariuszs
 * @since 01.11.13.
 */
public final class AlphaNumberSystem {

  static final char[] DIGITS = {
      'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M',
      'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z'
  };

  private AlphaNumberSystem() {
  }

  public static String convertToString(final int numberToConvert, final int base) {
    int number = numberToConvert;
    final char[] buffer = new char[20];
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
