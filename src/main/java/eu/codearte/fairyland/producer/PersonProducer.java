/*
 * Copyright (c) 2013 Codearte
 */
package eu.codearte.fairyland.producer;

import eu.codearte.fairyland.DataMaster;
import eu.codearte.fairyland.producer.person.Person;

import static eu.codearte.fairyland.DataMaster.PERSONAL_EMAIL;
import static org.apache.commons.lang3.StringUtils.lowerCase;

public class PersonProducer extends FairyProducer {

  public PersonProducer(RandomDataGenerator generator, RandomGenerator random) {
    super(generator, random);
    generate();
  }

  public Person generate() {
    return generate("male");
  }

  public Person generate(String selectedSex) {
    String firstName = generator.getValuesOfType(DataMaster.FIRST_NAME, selectedSex);
    String sex = selectedSex;
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
