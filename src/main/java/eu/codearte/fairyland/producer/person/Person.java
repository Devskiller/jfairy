/*
 * Copyright (c) 2013 Codearte
 */
package eu.codearte.fairyland.producer.person;

import eu.codearte.fairyland.producer.Company;
import eu.codearte.fairyland.producer.text.FairyUtil;
import eu.codearte.fairyland.producer.util.RandomDataGenerator;
import eu.codearte.fairyland.producer.util.RandomGenerator;
import org.apache.commons.lang3.StringUtils;
import org.joda.time.DateTime;

import javax.inject.Inject;

import static eu.codearte.fairyland.producer.person.Sex.FEMALE;
import static eu.codearte.fairyland.producer.person.Sex.MALE;
import static org.apache.commons.lang3.StringUtils.lowerCase;

public class Person {

	public static final String FIRST_NAME = "firstNames";
	public static final String LAST_NAME = "lastNames";
	public static final String PERSONAL_EMAIL = "personalEmails";
	public static final String TELEPHONE_NUMBER_FORMATS = "telephone_number_formats";
	private final RandomGenerator random;
	private final RandomDataGenerator generator;
	private final FairyUtil fairyUtil;
	private final NationalIdentificationNumber nationalIdentificationNumber;
	private final NationalIdentityCardNumber nationalIdentityCardNumber;

	private String firstName;
	private String lastName;
	private String email;
	private Sex sex;
	private String telephoneNumber;
	private DateTime dateOfBirth;
	private Integer age;
	private String telephoneNumberFormat;
	private Company company;
	private String companyEmail;

	@Inject
	public Person(RandomGenerator random,
								RandomDataGenerator generator,
								FairyUtil fairyUtil, NationalIdentificationNumber nationalIdentificationNumber,
								NationalIdentityCardNumber nationalIdentityCardNumber, Company company) {
		this.random = random;
		this.generator = generator;
		this.fairyUtil = fairyUtil;
		this.nationalIdentificationNumber = nationalIdentificationNumber;
		this.nationalIdentityCardNumber = nationalIdentityCardNumber;
		//fixme - should be created only if needed
		this.company = company;
	}

	public void telephoneNumberFormat(String telephoneNumberFormat) {
		this.telephoneNumberFormat = telephoneNumberFormat;
	}

	public void generate() {
		if (sex == null) {
			sex = random.trueOrFalse() ? MALE : FEMALE;
		}
		firstName = generator.getValuesOfType(FIRST_NAME, sex.name());
		lastName = generator.getValuesOfType(LAST_NAME, sex.name());
		email = generateEmail(firstName, lastName);
		if (telephoneNumberFormat == null) {
			telephoneNumberFormat = generator.getValues(TELEPHONE_NUMBER_FORMATS);
		}
		telephoneNumber = fairyUtil.numerify(telephoneNumberFormat);
		if (age == null) {
			age = random.randomBetween(1, 100);
		}
		if (dateOfBirth == null) {
			dateOfBirth = generator.randomDateInThePast(age);
		}
		companyEmail = StringUtils.stripAccents(lowerCase(firstName + '.' + lastName + '@' + company.domain()));

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

	private String generateEmail(String firstName, Object lastName) {
		String temp = "";
		if (random.trueOrFalse()) {
			temp = firstName;
			if (random.trueOrFalse()) {
				temp += ".";
			}
		}
		return StringUtils.stripAccents(lowerCase(temp + lastName + '@' + generator.getValues(PERSONAL_EMAIL)));
	}

	public String nationalIdentificationNumber() {
		return nationalIdentificationNumber.generate(new DateTime(dateOfBirth), sex);
	}

	public String nationalIdentityCardNumber() {
		return nationalIdentityCardNumber.generate(generator.randomDateInThePast(10));
	}

	public void setSex(Sex sex) {
		this.sex = sex;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public void setTelephoneNumber(String telephoneNumber) {
		this.telephoneNumber = telephoneNumber;
	}

	public void setCompany(Company company) {
		this.company = company;
	}

	public String companyEmail() {
		return companyEmail;
	}
}
