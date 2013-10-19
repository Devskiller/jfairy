/*
 * Copyright (c) 2013. Codearte
 */

package eu.codearte.fairyland.producer.person;

import eu.codearte.fairyland.producer.RandomDataGenerator;
import eu.codearte.fairyland.producer.RandomGenerator;
import eu.codearte.fairyland.producer.text.StringifyUtil;

public class Men extends PersonProducer {

  public Men(RandomDataGenerator generator, RandomGenerator random, StringifyUtil stringifyUtil1) {
    super(generator, random, stringifyUtil1);
  }

  @Override
  final PersonHolder.Sex getSex() {
    return PersonHolder.Sex.male;
  }
}
