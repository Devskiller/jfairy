/*
 * Copyright (c) 2013 Codearte
 */
package eu.codearte.fairyland.producer.person;

import com.google.common.annotations.VisibleForTesting;
import eu.codearte.fairyland.data.DataMaster;
import eu.codearte.fairyland.producer.BaseProducer;
import eu.codearte.fairyland.producer.DateProducer;
import eu.codearte.fairyland.producer.company.Company;
import org.apache.commons.lang3.StringUtils;
import org.joda.time.DateTime;

import javax.inject.Inject;

import static eu.codearte.fairyland.producer.person.Person.Sex.FEMALE;
import static eu.codearte.fairyland.producer.person.Person.Sex.MALE;
import static org.apache.commons.lang3.StringUtils.lowerCase;

public class Person {

	public static final int MIN_AGE = 1;
	public static final int MAX_AGE = 100;

	public static enum Sex {
		MALE, FEMALE
	}

	@VisibleForTesting
	static final String FIRST_NAME = "firstNames";
	@VisibleForTesting
	static final String LAST_NAME = "lastNames";
	@VisibleForTesting
	static final String PERSONAL_EMAIL = "personalEmails";
	@VisibleForTesting
	static final String TELEPHONE_NUMBER_FORMATS = "telephone_number_formats";
	private final DataMaster dataMaster;
	private final DateProducer dateProducer;
	private final BaseProducer baseProducer;
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
	public Person(DataMaster dataMaster, DateProducer dateProducer,
								BaseProducer baseProducer, NationalIdentificationNumber nationalIdentificationNumber,
								NationalIdentityCardNumber nationalIdentityCardNumber, Company company) {
		this.dataMaster = dataMaster;
		this.dateProducer = dateProducer;
		this.baseProducer = baseProducer;
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
			sex = baseProducer.trueOrFalse() ? MALE : FEMALE;
		}
		firstName = dataMaster.getValuesOfType(FIRST_NAME, sex.name());
		lastName = dataMaster.getValuesOfType(LAST_NAME, sex.name());
		email = generateEmail(firstName, lastName);
		if (telephoneNumberFormat == null) {
			telephoneNumberFormat = dataMaster.getRandomValue(TELEPHONE_NUMBER_FORMATS);
		}
		telephoneNumber = baseProducer.numerify(telephoneNumberFormat);
		if (age == null) {
			age = baseProducer.randomBetween(MIN_AGE, MAX_AGE);
		}
		if (dateOfBirth == null) {
			dateOfBirth = dateProducer.randomDateInThePast(age);
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
		if (baseProducer.trueOrFalse()) {
			temp = firstName;
			if (baseProducer.trueOrFalse()) {
				temp += ".";
			}
		}
		return StringUtils.stripAccents(lowerCase(temp + lastName + '@' + dataMaster.getRandomValue(PERSONAL_EMAIL)));
	}

	public String nationalIdentificationNumber() {
		return nationalIdentificationNumber.generate(new DateTime(dateOfBirth), sex);
	}

	public String nationalIdentityCardNumber() {
		return nationalIdentityCardNumber.generate();
	}

	public void setSex(Sex sex) {
		this.sex = sex;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public void setCompany(Company company) {
		this.company = company;
	}

	public String companyEmail() {
		return companyEmail;
	}

}
