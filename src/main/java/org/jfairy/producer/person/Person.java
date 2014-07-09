package org.jfairy.producer.person;

import org.jfairy.producer.company.Company;
import org.joda.time.DateTime;

import static org.jfairy.producer.person.Person.Sex.FEMALE;
import static org.jfairy.producer.person.Person.Sex.MALE;

public class Person {

	public static enum Sex {
		MALE, FEMALE;
	}

	private final Address address;
	private final String firstName;
	private final String middleName;
	private final String lastName;
	private final String email;
	private final String username;
	private final String password;
	private final Sex sex;
	private final String telephoneNumber;
	private final DateTime dateOfBirth;
	private final Integer age;
	private final Company company;
	private final String companyEmail;
	private final String nationalIdentityCardNumber;
	private final String nationalIdentificationNumber;

	public Person(String firstName, String middleName, String lastName, Address address, String email, String username,
				  String password, Sex sex, String telephoneNumber, DateTime dateOfBirth, Integer age,
				  String nationalIdentityCardNumber, String nationalIdentificationNumber, Company company, String companyEmail) {
		this.nationalIdentityCardNumber = nationalIdentityCardNumber;
		this.address = address;
		this.firstName = firstName;
		this.middleName = middleName;
		this.lastName = lastName;
		this.email = email;
		this.username = username;
		this.password = password;
		this.sex = sex;
		this.telephoneNumber = telephoneNumber;
		this.dateOfBirth = dateOfBirth;
		this.age = age;
		this.nationalIdentificationNumber = nationalIdentificationNumber;
		this.company = company;
		this.companyEmail = companyEmail;
	}

	public String nationalIdentificationNumber() {
		return  nationalIdentificationNumber;
	}

	public String firstName() {
		return firstName;
	}

	public String middleName() {
		return middleName;
	}

	public String lastName() {
		return lastName;
	}

	public String email() {
		return email;
	}

	public String username() {
		return username;
	}

	public String password() {
		return password;
	}

	public String fullName() {
		return firstName + " " + lastName;
	}

	public boolean isMale() {
		return sex == MALE;
	}

	public boolean isFemale() {
		return sex == FEMALE;
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

	public String nationalIdentityCardNumber() {
		return nationalIdentityCardNumber;
	}

	public String companyEmail() {
		return companyEmail;
	}

	public Address getAddress() {
		return address;
	}

	public Company getCompany() {
		return company;
	}
}
