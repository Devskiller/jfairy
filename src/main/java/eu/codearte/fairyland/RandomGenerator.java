/*
 * Copyright (c) 2013. Codearte
 */
package eu.codearte.fairyland;

import java.util.List;
import java.util.Random;

import static java.util.Collections.shuffle;

public class RandomGenerator {
  private static final int SEED = 1761283695;

  public static Random random = new Random(SEED);

  public boolean getBoolean() {
    return RandomGenerator.random.nextBoolean();
  }

  public String randomElement(List<String> elements) {
    return elements.get(RandomGenerator.random.nextInt(elements.size()));
  }

  public List<String> randomElements(List<String> elements, int count) {
    shuffle(elements);
    return elements.subList(0, count % elements.size());
  }
}
