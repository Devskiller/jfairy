/*
 * Copyright (c) 2013. Codearte
 */
package eu.codearte.fairyland.producer.text;

import eu.codearte.fairyland.DataMaster;
import eu.codearte.fairyland.producer.FairyProducer;

import java.util.ArrayList;
import java.util.List;

import static eu.codearte.fairyland.TextUtils.joinWithSpace;
import static java.util.Arrays.asList;
import static org.apache.commons.lang3.StringUtils.*;

public class TextProducer extends FairyProducer {

  private static final String DATA = "text";

  private final String loremIpsum;
  private final List<String> words;

  public TextProducer(DataMaster dataMaster) {
    super(dataMaster);
    loremIpsum = dataMaster.getAsOne(DATA);
    words = asList(split(loremIpsum, ' '));
  }

  public String getLoremIpsum() {
    return loremIpsum;
  }

  public String rawWords(int count) {
    List<String> result = readRawWords(count);
    return joinWithSpace(result);
  }

  public String cleanWords(int count) {
    List<String> result = new ArrayList<>();
    for (String part : readRawWords(count)) {
      result.add(uncapitalize(replaceChars(part, "., ", "")));
    }
    return joinWithSpace(result);
  }

  private List<String> readRawWords(int count) {
    return random().randomElements(words, count);
  }


}
