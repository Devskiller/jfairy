/*
 * Copyright (c) 2013. Codearte
 */
package eu.codearte.fairyland.producer;

public abstract class FairyProducer {

  protected final RandomDataGenerator generator;
  protected final RandomGenerator random;

  public FairyProducer(RandomDataGenerator generator, RandomGenerator random) {
    this.generator = generator;
    this.random = random;
  }

}
