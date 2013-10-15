/*
 * Copyright (c) 2013. Codearte
 */

package eu.codearte.fairyland;

import java.util.List;

import static java.util.Collections.shuffle;

public class RandomDataGenerator {

  private final DataMaster dataMaster;
  private final RandomGenerator random;

  public RandomDataGenerator(DataMaster dataMaster, RandomGenerator random) {
    this.dataMaster = dataMaster;
    this.random = random;
  }

  public boolean trueOrFalse(){
    return random.getBoolean();
  }

  public String getData(String key) {
    List<String> elements = dataMaster.getData(key);
    if (elements != null) {
      return random.randomElement(elements);
    }
    throw new IllegalArgumentException("No such key: " + key);
  }

  public List<String> randomElements(List<String> elements, int count) {
    shuffle(elements);
    return elements.subList(0, count % elements.size());
  }
}
