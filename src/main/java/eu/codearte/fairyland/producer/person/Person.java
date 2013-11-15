/*
 * Copyright (c) 2013 Codearte
 */
package eu.codearte.fairyland.producer.person;

import eu.codearte.fairyland.DataMaster;
import eu.codearte.fairyland.producer.text.FairUtil;
import eu.codearte.fairyland.producer.util.RandomDataGenerator;
import eu.codearte.fairyland.producer.util.RandomGenerator;
import org.apache.commons.lang3.StringUtils;
import org.joda.time.DateTime;

import javax.inject.Inject;

import static eu.codearte.fairyland.DataMaster.PERSONAL_EMAIL;
import static eu.codearte.fairyland.DataMaster.TELEPHONE_NUMBER_FORMATS;
import static eu.codearte.fairyland.producer.person.Sex.female;
import static eu.codearte.fairyland.producer.person.Sex.male;
import static org.apache.commons.lang3.StringUtils.lowerCase;

public class Person {

  private final RandomGenerator random;
  private final RandomDataGenerator generator;
  private final FairUtil fairUtil;
  private final NationalIdentificationNumber nationalIdentificationNumber;
  private final NationalIdentityCardNumber nationalIdentityCardNumber;

  private String firstName;
  private String lastName;
  private String email;
  private Sex sex;
  private String telephoneNumber;
  private DateTime dateOfBirth;
  private int age;

  private final Config config;

  @Inject
  public Person(RandomGenerator random,
                RandomDataGenerator generator,
                FairUtil fairUtil, NationalIdentificationNumber nationalIdentificationNumber, NationalIdentityCardNumber nationalIdentityCardNumber) {
    this.random = random;
    this.generator = generator;
    this.fairUtil = fairUtil;
    this.nationalIdentificationNumber = nationalIdentificationNumber;
    this.nationalIdentityCardNumber = nationalIdentityCardNumber;

    this.config = new Config(random);
    config.applyTelephoneNumberFormat(generator.getValues(TELEPHONE_NUMBER_FORMATS));

    generatePerson();
  }

  public Person male() {
    config.applySex(Sex.male);
    generatePerson();
    return this;
  }

  public Person female() {
    config.applySex(Sex.female);
    generatePerson();
    return this;
  }

  public Person telephoneNumberFormat(String telephoneNumberFormat) {
    config.applyTelephoneNumberFormat(telephoneNumberFormat);
    generatePerson();
    return this;
  }

  private void generatePerson() {
    firstName = generator.getValuesOfType(DataMaster.FIRST_NAME, config.sex().name());
    lastName = generator.getValuesOfType(DataMaster.LAST_NAME, config.sex().name());
    email = generateEmail(firstName, lastName);
    telephoneNumber = fairUtil.numerify(config.getTelephoneNumberFormat());
    dateOfBirth = generator.randomDateInThePast(100);
    age = fairUtil.age(dateOfBirth);
    sex = config.sex();
  }

  public String firstName() {
    return firstName;
  }

  public String lastName() {
    return lastName;
  }

  public String email() {
    return email;
  }

  public String fullName() {
    return firstName + " " + lastName;
  }

  public boolean isMale() {
    return sex == male;
  }

  public boolean isFemale() {
    return sex == female;
  }

  public String telephoneNumber() {
    return telephoneNumber;
  }

  public DateTime dateOfBirth() {
    return dateOfBirth;
  }


  public int age() {
    return age;
  }

  private String generateEmail(String firstName, Object lastName) {
    String temp = "";
    if (random.trueOrFalse()) {
      temp = firstName;
      if (random.trueOrFalse()) {
        temp += ".";
      }
    }
    return StringUtils.stripAccents(lowerCase(temp + lastName + "@" + generator.getValues(PERSONAL_EMAIL)));
  }

  public String nationalIdentificationNumber() {
    return nationalIdentificationNumber.generate(new DateTime(dateOfBirth), config.sex());
  }

  public String nationalIdentityCardNumber() {
    return nationalIdentityCardNumber.generate(generator.randomDateInThePast(10));
  }

}
