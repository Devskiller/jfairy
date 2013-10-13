/*
 * Copyright (c) 2013 Codearte
 */
package eu.codearte.fairyland.producer;

import com.google.common.base.Joiner;
import com.google.common.base.Splitter;
import eu.codearte.fairyland.DataMaster;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import static com.google.common.base.Joiner.on;
import static java.util.Arrays.asList;
import static org.apache.commons.lang3.StringUtils.*;

public class Text extends HookProducer {

  private static final String DATA = "text";
  private static Random random = new Random();

  private final String loremIpsum;
  private final List<String> words;

  public Text(DataMaster dataMaster) {
    super(dataMaster);
    loremIpsum = dataMaster.getAsOne(DATA);
    words = asList(split(loremIpsum, ' '));
  }

  public String loremIpsum() {
    return loremIpsum;
  }

  public String loremIpsum(int length) {
    if (loremIpsum.length() > length) {
      return loremIpsum.substring(0, length);
    } else {
      return loremIpsum;
    }
  }

  public String words() {
    return words(3);
  }

  public String words(int count) {
    return cleanWords(count);
  }

  private List<String> readRawWords(int count) {
    return dataMaster.randomElements(words, count);
  }

  String rawWords(int count) {
    List<String> result = readRawWords(count);
    return on(" ").join(result);
  }

  String cleanWords(int count) {
    List<String> result = new ArrayList<>();
    for (String part : readRawWords(count)) {
      result.add(uncapitalize(replaceChars(part, "., ", "")));
    }
    return on(" ").join(result);
  }

  public String sentence() {
    return sentence(3);
  }

  public String sentence(int wordCount) {
    String randomWords = rawWords(wordCount + random.nextInt(6));
    List<String> sentences = new ArrayList<>();
    for (String sentence : Splitter.on(". ").split(randomWords)) {
      sentences.add(capitalize(sentence));
    }
    String sentence = capitalize(Joiner.on(". ").join(sentences) + ".");
    if (!(endsWith(sentence, ".") || endsWith(sentence, ","))) {
      sentence += ".";
    }
    return sentence;
  }
}
