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

  enum Sex {
    male, female
  }

  public PersonHolder(String firstName, String lastName, String email, Sex sex, String telephoneNumber, Date dateOfBirth) {
    this.firstName = firstName;
    this.lastName = lastName;
    this.email = email;
    this.sex = sex;
    this.telephoneNumber = telephoneNumber;
    this.dateOfBirth = dateOfBirth;
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

  public Sex sex() {
    return sex;
  }

  public String fullName() {
    return firstName + " " + lastName;
  }

  public boolean isMale() {
    return sex == Sex.male;
  }

  public boolean isFemale() {
    return sex == Sex.female;
  }

  public String telephoneNumber() {
    return telephoneNumber;
  }

  Date dateOfBirth() {
    return dateOfBirth;
  }
}
