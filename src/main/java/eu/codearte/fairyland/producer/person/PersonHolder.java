/*
 * Copyright (c) 2013. Codearte
 */

package eu.codearte.fairyland.producer.person;

import org.joda.time.DateTime;

class PersonHolder {

  private final String firstName;
  private final String lastName;
  private final String email;
  private final Sex sex;
  private final String telephoneNumber;
  private final DateTime dateOfBirth;
  private final int age;

    public PersonHolder(String firstName, String lastName, String email, Sex sex, String telephoneNumber, DateTime dateOfBirth, int age) {
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

  DateTime dateOfBirth() {
    return dateOfBirth;
  }

  int age() {
    return age;
  }
}
