/*
 * Copyright (c) 2013. Codearte
 */
package eu.codearte.fairyland.producer;

import eu.codearte.fairyland.producer.text.StringifyUtil;

public abstract class FairyProducer {

  protected final RandomDataGenerator generator;
  protected final RandomGenerator random;
  protected final StringifyUtil stringifyUtil;

  public FairyProducer(RandomDataGenerator generator, RandomGenerator random, StringifyUtil stringifyUtil) {
    this.generator = generator;
    this.random = random;
    this.stringifyUtil = stringifyUtil;
  }

}
