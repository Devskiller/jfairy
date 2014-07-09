package org.jfairy.producer.person;

import com.google.common.annotations.VisibleForTesting;
import com.google.inject.assistedinject.Assisted;
import org.apache.commons.lang3.RandomStringUtils;
import org.jfairy.data.DataMaster;
import org.jfairy.producer.BaseProducer;
import org.jfairy.producer.DateProducer;
import org.jfairy.producer.company.Company;
import org.joda.time.DateTime;

import javax.inject.Inject;

import static org.apache.commons.lang3.StringUtils.lowerCase;
import static org.apache.commons.lang3.StringUtils.stripAccents;
import static org.jfairy.producer.person.Person.Sex.FEMALE;
import static org.jfairy.producer.person.Person.Sex.MALE;

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
	private final AddressProvider addressProvider;

	private String firstName;
	private String middleName;
	private String lastName;
	private String email;
	private String username;
	private String password;
	private Sex sex;
	private String telephoneNumber;
	private DateTime dateOfBirth;
	private Integer age;
	private String telephoneNumberFormat;
	private Company company;
	private String companyEmail;

	// FIXME: Too many parameters
	@Inject
	public Person(DataMaster dataMaster,
				  DateProducer dateProducer,
				  BaseProducer baseProducer,
				  NationalIdentificationNumber nationalIdentificationNumber,
				  NationalIdentityCardNumber nationalIdentityCardNumber,
				  AddressProvider addressProvider,
				  Company company,

				  @Assisted PersonProperties.PersonProperty... personProperties) {

		this.dataMaster = dataMaster;
		this.dateProducer = dateProducer;
		this.baseProducer = baseProducer;
		this.nationalIdentificationNumber = nationalIdentificationNumber;
		this.nationalIdentityCardNumber = nationalIdentityCardNumber;
		this.addressProvider = addressProvider;
		//fixme - should be created only if needed
		this.company = company;
		for (PersonProperties.PersonProperty personProperty : personProperties) {
			personProperty.apply(this);
		}
		generate();
	}

	public void telephoneNumberFormat(String telephoneNumberFormat) {
		this.telephoneNumberFormat = telephoneNumberFormat;
	}

	private void generate() {
		if (sex == null) {
			sex = randomBoolean() ? MALE : FEMALE;
		}
		firstName = dataMaster.getValuesOfType(FIRST_NAME, sex.name());
		middleName = randomBoolean() ? dataMaster.getValuesOfType(FIRST_NAME, sex.name()) : "";
		lastName = dataMaster.getValuesOfType(LAST_NAME, sex.name());
		email = generateEmail(firstName, lastName);
		username = generateUsername(firstName, lastName);
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
		companyEmail = stripAccents(lowerCase(firstName + '.' + lastName + '@' + company.domain()));
		// FIXME: Replace this with baseProducer
		password = RandomStringUtils.randomAlphanumeric(8);
	}

	private boolean randomBoolean() {
		return baseProducer.trueOrFalse();
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

	private String generateUsername(String firstName, String lastName) {
		if (randomBoolean()) {
			return lowerCase(stripAccents(firstName.substring(0, 1) + lastName));
		} else {
			return lowerCase(stripAccents(firstName + lastName.substring(0, 1)));
		}
	}

	private String generateEmail(String firstName, String lastName) {
		String temp = "";
		if (randomBoolean()) {
			temp = firstName;
			if (randomBoolean()) {
				temp += ".";
			}
		}
		return stripAccents(lowerCase(temp + lastName + '@' + dataMaster.getRandomValue(PERSONAL_EMAIL)));
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

	public Address getAddress() {
		return addressProvider.get();
	}

}
