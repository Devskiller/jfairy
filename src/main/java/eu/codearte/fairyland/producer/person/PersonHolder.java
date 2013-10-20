/*
 * Copyright (c) 2013. Codearte
 */

package eu.codearte.fairyland.producer.person;

import java.util.Date;

class PersonHolder {

  private final String firstName;
  private final String lastName;
  private final String email;
  private final Sex sex;
  private final String telephoneNumber;
  private final Date dateOfBirth;
  private final int age;

  enum Sex {
    male, female
  }

  public PersonHolder(String firstName, String lastName, String email, Sex sex, String telephoneNumber, Date dateOfBirth, int age) {
    this.firstName = firstName;
    this.lastName = lastName;
    this.email = email;
    this.sex = sex;
    this.telephoneNumber = telephoneNumber;
    this.dateOfBirth = dateOfBirth;
    this.age = age;
  }

  String firstName() {
    return firstName;
  }

  String lastName() {
    return lastName;
  }

  String email() {
    return email;
  }

  Sex sex() {
    return sex;
  }

  String fullName() {
    return firstName + " " + lastName;
  }

  boolean isMale() {
    return sex == Sex.male;
  }

  boolean isFemale() {
    return sex == Sex.female;
  }

  String telephoneNumber() {
    return telephoneNumber;
  }

  Date dateOfBirth() {
    return dateOfBirth;
  }

  int age() {
    return age;
  }
}
