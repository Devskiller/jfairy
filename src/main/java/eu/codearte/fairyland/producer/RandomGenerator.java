/*
 * Copyright (c) 2013. Codearte
 */
package eu.codearte.fairyland.producer;

import java.util.List;
import java.util.Random;

import static java.util.Collections.shuffle;

public class RandomGenerator {

  private final Random random;

  public RandomGenerator(long seed) {
    random = new Random(seed);
  }

  public boolean trueOrFalse() {
    return random.nextBoolean();
  }

  public String randomElement(List<String> elements) {
    return elements.get(random.nextInt(elements.size()));
  }

  public List<String> randomElements(List<String> elements, int count) {
    shuffle(elements, random);
    return elements.subList(0, count % elements.size());
  }

  public int randomInt(int value) {
    return value > 0 ? random.nextInt(value) : 0;
  }
}
