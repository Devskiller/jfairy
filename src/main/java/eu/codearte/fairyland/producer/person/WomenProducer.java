/*
 * Copyright (c) 2013. Codearte
 */

package eu.codearte.fairyland.producer.person;

import eu.codearte.fairyland.producer.RandomDataGenerator;
import eu.codearte.fairyland.producer.RandomGenerator;
import eu.codearte.fairyland.producer.text.StringifyUtil;

public class WomenProducer extends BasePersonProducer {

  public WomenProducer(RandomDataGenerator generator, RandomGenerator random, StringifyUtil stringifyUtil1) {
    super(generator, random, stringifyUtil1);
  }

  @Override
  final Person.Sex getSex() {
    return Person.Sex.female;
  }

}
