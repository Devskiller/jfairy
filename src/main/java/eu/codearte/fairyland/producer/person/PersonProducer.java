/*
 * Copyright (c) 2013. Codearte
 */

package eu.codearte.fairyland.producer.person;

import eu.codearte.fairyland.producer.RandomDataGenerator;
import eu.codearte.fairyland.producer.RandomGenerator;
import eu.codearte.fairyland.producer.text.StringifyUtil;

import static eu.codearte.fairyland.producer.person.Person.Sex.*;

public class PersonProducer extends BasePersonProducer {

  private Person.Sex sex = randomSex();

  public PersonProducer(RandomDataGenerator generator, RandomGenerator random, StringifyUtil stringifyUtil1) {
    super(generator, random, stringifyUtil1);
  }

  @Override
  Person.Sex getSex() {
    return sex;
  }

  private Person.Sex randomSex() {
    return random.trueOrFalse() ? male : female;
  }

  public PersonProducer male() {
    sex = Person.Sex.male;
    return this;
  }

  public PersonProducer female() {
    sex = Person.Sex.female;
    return this;
  }

}
