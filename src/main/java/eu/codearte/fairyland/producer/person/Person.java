/*
 * Copyright (c) 2013. Codearte
 */

package eu.codearte.fairyland.producer.person;

import eu.codearte.fairyland.producer.RandomDataGenerator;
import eu.codearte.fairyland.producer.RandomGenerator;
import eu.codearte.fairyland.producer.text.StringifyUtil;

import static eu.codearte.fairyland.producer.person.PersonHolder.Sex.*;

public class Person extends PersonProducer {

  private PersonHolder.Sex sex = randomSex();

  public Person(RandomDataGenerator generator, RandomGenerator random, StringifyUtil stringifyUtil1) {
    super(generator, random, stringifyUtil1);
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
