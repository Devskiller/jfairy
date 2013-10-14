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

  private int limit = 0;

  public Text(DataMaster dataMaster) {
    super(dataMaster);
    loremIpsum = dataMaster.getAsOne(DATA);
    words = asList(split(loremIpsum, ' '));
  }

  public Text limit(int limit) {
    this.limit = limit;
    return this;
  }

  public String result(String result) {
    if (limit > 0)
      return left(result, limit);
    else
      return result;
  }

  public String loremIpsum() {
    return result(loremIpsum);
  }

  public String words() {
    return result(words(3));
  }

  public String words(int count) {
    return result(cleanWords(count));
  }

  private List<String> readRawWords(int count) {
    return dataMaster.randomElements(words, count);
  }

  private String rawWords(int count) {
    List<String> result = readRawWords(count);
    return on(" ").join(result);
  }

  private String cleanWords(int count) {
    List<String> result = new ArrayList<>();
    for (String part : readRawWords(count)) {
      result.add(uncapitalize(replaceChars(part, "., ", "")));
    }
    return on(" ").join(result);
  }

  public String sentence() {
    return result(sentence(3));
  }

  public String sentence(int wordCount) {
    String randomWords = rawWords(wordCount + random.nextInt(6));
    List<String> sentences = new ArrayList<>();
    for (String sentence : Splitter.on(". ").split(randomWords)) {
      sentences.add(capitalize(sentence));
    }
    String sentence = capitalize(Joiner.on(". ").join(sentences));
    sentence = removeEnd(sentence, ",");
    if (!endsWith(sentence, ".")) {
      sentence += ".";
    }
    return result(sentence);
  }

  private List<String> sentences(int sentenceCount) {
    List<String> sentences = new ArrayList<String>(sentenceCount);
    for (int i = 0; i < sentenceCount; i++) {
      sentences.add(sentence());
    }
    return sentences;
  }

  public String paragraph(int sentenceCount) {
    return result(on(" ").join(sentences(sentenceCount + random.nextInt(3))));
  }

  public String paragraph() {
    return result(paragraph(3));
  }

}
