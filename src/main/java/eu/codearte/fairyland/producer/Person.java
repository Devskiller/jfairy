/*
 * Copyright (c) 2013 Codearte
 */
package eu.codearte.fairyland.producer;

import eu.codearte.fairyland.DataMaster;

import static org.apache.commons.lang3.StringUtils.lowerCase;

public class Person extends FairyProducer {

  private final String firstName;
  private final String lastName;
  private final String email;
  private final String sex;

  public enum Sex {
    male, female, both
  }

  public Person(RandomDataGenerator generator, RandomGenerator random) {
    super(generator, random);
    firstName = generator.getValuesOfType(DataMaster.FIRST_NAME, "");
    sex = generator.getTypeOfValue(DataMaster.FIRST_NAME, firstName);
    lastName = generator.getValues(DataMaster.LAST_NAME);
    email = generateEmail();
  }

  private String generateEmail() {
    String temp = "";
    if (random.trueOrFalse()) {
      temp = firstName;
      if (random.trueOrFalse()) {
        temp += ".";
      }
    }
    return lowerCase(temp + lastName + "@" + generator.getValues(DataMaster.PERSONAL_EMAIL));
  }

  public String firstName() {
    return firstName;
  }

  public String lastName() {
    return lastName;
  }

  public String fullName() {
    return firstName + " " + lastName;
  }

  public String email() {
    return email;
  }

  public String sex() {
    return sex;
  }

  public boolean isMale() {
    return sex.equals(Sex.male.name());
  }

  public boolean isFemale() {
    return sex.equals(Sex.female.name());
  }
}
