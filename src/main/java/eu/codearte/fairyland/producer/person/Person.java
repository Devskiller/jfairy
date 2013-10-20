/*
 * Copyright (c) 2013. Codearte
 */

package eu.codearte.fairyland.producer.person;

import eu.codearte.fairyland.producer.RandomDataGenerator;
import eu.codearte.fairyland.producer.RandomGenerator;
import eu.codearte.fairyland.producer.text.FairUtil;

import static eu.codearte.fairyland.producer.person.PersonHolder.Sex.*;

public class Person extends PersonProducer {

  private PersonHolder.Sex sex = randomSex();

  public Person(RandomDataGenerator generator, RandomGenerator random, FairUtil fairUtil1) {
    super(generator, random, fairUtil1);
  }

  @Override
  PersonHolder.Sex getSex() {
    return sex;
  }

  private PersonHolder.Sex randomSex() {
    return random.trueOrFalse() ? male : female;
  }

  public Person male() {
    sex = PersonHolder.Sex.male;
    return this;
  }

  public Person female() {
    sex = PersonHolder.Sex.female;
    return this;
  }

}
