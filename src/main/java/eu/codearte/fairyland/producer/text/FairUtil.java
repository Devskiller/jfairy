package eu.codearte.fairyland.producer.text;

import eu.codearte.fairyland.producer.RandomGenerator;
import eu.codearte.fairyland.producer.util.TimeProvider;
import eu.codearte.fairyland.producer.util.TimeUtil;

import java.util.Date;

public class FairUtil {

  private final RandomGenerator random;

  private final TimeProvider timeProvider;

  public FairUtil(RandomGenerator random, TimeProvider timeProvider) {
    this.random = random;
    this.timeProvider = timeProvider;
  }

  public String letterify(String letterString) {
    return replaceSymbolWithCharsFromTo(letterString, '?', 'a', 'z');
  }

  public String numerify(String numberString) {
    return replaceSymbolWithCharsFromTo(numberString, '#', '0', '9');
  }

  public String bothify(String string) {
    return letterify(numerify(string));
  }

  public int age(Date dateOfBirth) {
    return TimeUtil.yearsBetween(dateOfBirth, timeProvider.getGregorianCalendar().getTime());
  }

  private String replaceSymbolWithCharsFromTo(String string, char symbol, char from, char to) {
    String result = "";
    for (char aChar : string.toCharArray()) {
      if (aChar == symbol) {
        result += (char) (random.randomBetween((int) from, (int) to));
      } else {
        result += aChar;
      }
    }
    return result;
  }
}
