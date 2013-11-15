package eu.codearte.fairyland.producer.text;

import eu.codearte.fairyland.producer.util.RandomGenerator;
import eu.codearte.fairyland.producer.util.TimeProvider;
import org.joda.time.DateTime;
import org.joda.time.Years;

import javax.inject.Inject;
import javax.inject.Singleton;

@Singleton
public class FairUtil {

  private final RandomGenerator random;

  private final TimeProvider timeProvider;

  @Inject
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

  public int age(DateTime dateOfBirth) {
    return Years.yearsBetween(dateOfBirth, timeProvider.getCurrentDate()).getYears();
  }

  private String replaceSymbolWithCharsFromTo(String string, char symbol, char from, char to) {
    StringBuilder result = new StringBuilder();
    for (char aChar : string.toCharArray()) {
      if (aChar == symbol) {
        result.append(random.randomBetween(from, to));
      } else {
        result.append(aChar);
      }
    }
    return result.toString();
  }
}
