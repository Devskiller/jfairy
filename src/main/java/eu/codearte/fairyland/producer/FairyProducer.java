/*
 * Copyright (c) 2013. Codearte
 */
package eu.codearte.fairyland.producer;

import eu.codearte.fairyland.producer.text.FairUtil;

public abstract class FairyProducer {

  protected final RandomDataGenerator generator;
  protected final RandomGenerator random;
  protected final FairUtil fairUtil;

  protected FairyProducer(RandomDataGenerator generator, RandomGenerator random, FairUtil fairUtil) {
    this.generator = generator;
    this.random = random;
    this.fairUtil = fairUtil;
  }

}
