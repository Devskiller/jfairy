/*
 * Copyright (c) 2013 Codearte
 */
package eu.codearte.fairyland.producer.person;

import eu.codearte.fairyland.DataMaster;
import eu.codearte.fairyland.producer.FairyProducer;
import eu.codearte.fairyland.producer.RandomDataGenerator;
import eu.codearte.fairyland.producer.RandomGenerator;
import eu.codearte.fairyland.producer.text.StringifyUtil;

import static eu.codearte.fairyland.DataMaster.PERSONAL_EMAIL;
import static eu.codearte.fairyland.DataMaster.TELEPHONE_NUMBER_FORMATS;
import static org.apache.commons.lang3.StringUtils.lowerCase;

abstract class PersonProducer extends FairyProducer {

  private PersonHolder person;

  private String telephoneNumberFormat = generator.getValues(TELEPHONE_NUMBER_FORMATS);

  public PersonProducer(RandomDataGenerator generator, RandomGenerator random, StringifyUtil stringifyUtil1) {
    super(generator, random, stringifyUtil1);
  }

  public PersonProducer telephoneNumberFormat(String telephoneNumberFormat) {
    this.telephoneNumberFormat = telephoneNumberFormat;
    regeneratePerson();
    return this;
  }

  private void regeneratePerson() {
    person = generatePersonWithSex(getSex());
  }

  abstract PersonHolder.Sex getSex();

  private PersonHolder generatePersonWithSex(PersonHolder.Sex sex) {
    String firstName = generator.getValuesOfType(DataMaster.FIRST_NAME, sex.name());
    String lastName = generator.getValues(DataMaster.LAST_NAME);
    String email = generateEmail(firstName, lastName);
    String telephonNumber = stringifyUtil.numerify(telephoneNumberFormat);
    return new PersonHolder(firstName, lastName, email, sex, telephonNumber);
  }

  public String firstName() {
    checkPerson();
    return person.firstName();
  }

  public String lastName() {
    checkPerson();
    return person.lastName();
  }

  public String email() {
    checkPerson();
    return person.email();
  }

  public String fullName() {
    checkPerson();
    return person.fullName();
  }

  public boolean isMale() {
    checkPerson();
    return person.isMale();
  }

  public boolean isFemale() {
    checkPerson();
    return person.isFemale();
  }

  public String telephoneNumber() {
    checkPerson();
    return person.telephoneNumber();
  }

  private void checkPerson() {
    if (person == null) {
      regeneratePerson();
    }
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
