/*
 * Copyright (c) 2013. Codearte
 */
package eu.codearte.fairyland.producer.text;

import eu.codearte.fairyland.producer.FairyProducer;
import eu.codearte.fairyland.producer.RandomDataGenerator;
import eu.codearte.fairyland.producer.RandomGenerator;
import eu.codearte.fairyland.producer.text.StringifyUtil;

import java.util.ArrayList;
import java.util.List;

import static eu.codearte.fairyland.TextUtils.joinWithSpace;
import static java.util.Arrays.asList;
import static org.apache.commons.lang3.StringUtils.*;

public class TextProducer extends FairyProducer {

  private static final String DATA = "text";

  private final String loremIpsum;
  private final List<String> words;

  public TextProducer(RandomDataGenerator generator, RandomGenerator random, StringifyUtil stringifyUtil1) {
    super(generator, random, stringifyUtil1);
    loremIpsum = generator.getValue(DATA);
    words = asList(split(loremIpsum, ' '));
  }

  public String getLoremIpsum() {
    return loremIpsum;
  }

  public String rawWords(int count, int precision) {
    List<String> result = readRawWords(count, precision);
    return joinWithSpace(result);
  }

  public String cleanWords(int count) {
    List<String> result = new ArrayList<>();
    for (String part : readRawWords(count, 0)) {
      result.add(uncapitalize(replaceChars(part, "., ", "")));
    }
    return joinWithSpace(result);
  }

  private List<String> readRawWords(int count, int precision) {
    return generator.randomElements(words, count + random.randomInt(precision));
  }

}
