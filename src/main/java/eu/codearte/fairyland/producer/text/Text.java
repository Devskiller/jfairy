/*
 * Copyright (c) 2013 Codearte
 */
package eu.codearte.fairyland.producer.text;

import com.google.common.base.Joiner;
import com.google.common.base.Splitter;
import eu.codearte.fairyland.producer.RandomGenerator;

import java.util.ArrayList;
import java.util.List;

import static eu.codearte.fairyland.TextUtils.joinWithSpace;
import static org.apache.commons.lang3.StringUtils.*;

public class Text {

  private final TextProducer textProducer;
  private final RandomGenerator random;

  private int limit = 0;

  public Text(TextProducer textProducer, RandomGenerator random) {
    this.random = random;
    this.textProducer = textProducer;
  }

  public Text limitedTo(int limit) {
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
    return result(textProducer.getLoremIpsum());
  }

  public String word() {
    return result(word(3));
  }

  public String word(int count) {
    return result(textProducer.cleanWords(count));
  }

  public String sentence() {
    return result(sentence(3));
  }

  public String sentence(int wordCount) {
    String randomWords = textProducer.rawWords(wordCount, 6);
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
    return result(joinWithSpace(sentences(sentenceCount + random.randomBetween(1, 3))));
  }

  public String paragraph() {
    return result(paragraph(3));
  }

}
