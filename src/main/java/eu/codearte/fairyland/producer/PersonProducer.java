/*
 * Copyright (c) 2013 Codearte
 */
package eu.codearte.fairyland.producer;

import eu.codearte.fairyland.DataMaster;
import eu.codearte.fairyland.producer.person.Person;

import static eu.codearte.fairyland.DataMaster.PERSONAL_EMAIL;
import static eu.codearte.fairyland.producer.person.Person.Sex.female;
import static eu.codearte.fairyland.producer.person.Person.Sex.male;
import static org.apache.commons.lang3.StringUtils.lowerCase;

public class PersonProducer extends FairyProducer {

  public PersonProducer(RandomDataGenerator generator, RandomGenerator random) {
    super(generator, random);
    generate();
  }

  public Person generate() {
    return generate(randomSex());
  }

  private Person.Sex randomSex() {
    return random.trueOrFalse() ? male : female;
  }

  public Person generate(Person.Sex sex) {
    String firstName = generator.getValuesOfType(DataMaster.FIRST_NAME, sex.name());
    String lastName = generator.getValues(DataMaster.LAST_NAME);
    String email = generateEmail(firstName, lastName);
    return new Person(firstName, lastName, email, sex);
  }

  private String generateEmail(String firstName, Object lastName) {
    String temp = "";
    if (random.trueOrFalse()) {
      temp = firstName;
      if (random.trueOrFalse()) {
        temp += ".";
      }
    }
    return lowerCase(temp + lastName + "@" + generator.getValues(PERSONAL_EMAIL));
  }

}
