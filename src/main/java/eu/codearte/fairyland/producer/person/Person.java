/*
 * Copyright (c) 2013. Codearte
 */

package eu.codearte.fairyland.producer.person;

public class Person {

  private final String firstName;
  private final String lastName;
  private final String email;
  private final Sex sex;

  public enum Sex {
    male, female
  }

  public Person(String firstName, String lastName, String email, Sex sex) {
    this.firstName = firstName;
    this.lastName = lastName;
    this.email = email;
    this.sex = sex;
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
}
