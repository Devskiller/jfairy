/*
 * Copyright (c) 2013. Codearte
 */

package eu.codearte.fairyland.producer.person;

import eu.codearte.fairyland.producer.RandomDataGenerator;
import eu.codearte.fairyland.producer.RandomGenerator;
import eu.codearte.fairyland.producer.text.FairUtil;

public class Women extends PersonProducer {

  public Women(RandomDataGenerator generator, RandomGenerator random, FairUtil fairUtil1) {
    super(generator, random, fairUtil1);
  }

  @Override
  final Sex getSex() {
    return Sex.female;
  }

}
