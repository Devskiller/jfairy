/*
 * Copyright (c) 2013. Codearte
 */

package eu.codearte.fairyland.producer;

import eu.codearte.fairyland.DataMaster;

import java.util.List;

public class RandomDataGenerator {

  private final DataMaster data;
  private final RandomGenerator random;

  public RandomDataGenerator(DataMaster data, RandomGenerator random) {
    this.data = data;
    this.random = random;
  }

  public List<String> randomElements(List<String> elements, int count) {
    return random.randomElements(elements, count);
  }

  public String getValues(String key) {
    return random.randomElement(data.getStringList(key));
  }

  public String getValue(String data) {
    return this.data.getString(data);
  }
}
