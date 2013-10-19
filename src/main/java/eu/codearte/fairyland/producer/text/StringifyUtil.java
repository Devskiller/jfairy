package eu.codearte.fairyland.producer.text;

import eu.codearte.fairyland.producer.RandomGenerator;

public class StringifyUtil {

  private final RandomGenerator random;

  public StringifyUtil(RandomGenerator random) {
    this.random = random;
  }

  public String letterify(String letterString) {
    return getString(letterString, '?', 'a', 'z');
  }

  public String numerify(String numberString) {
    return getString(numberString, '#', '0', '9');
  }

  public String bothify(String string) {
    return letterify(numerify(string));
  }

  private String getString(String numberString, char hash, char from, char to) {
    String result = "";
    for (char aChar : numberString.toCharArray()) {
      if (aChar == hash) {
        result += (char) (from + random.randomInt(to - from));
      } else {
        result += aChar;
      }
    }
    return result;
  }
}
