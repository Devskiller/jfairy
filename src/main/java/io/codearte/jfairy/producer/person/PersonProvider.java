package io.codearte.jfairy.producer.person;

import com.google.common.annotations.VisibleForTesting;
import com.google.inject.Provider;
import com.google.inject.assistedinject.Assisted;
import io.codearte.jfairy.data.DataMaster;
import io.codearte.jfairy.producer.BaseProducer;
import io.codearte.jfairy.producer.DateProducer;
import io.codearte.jfairy.producer.TimeProvider;
import io.codearte.jfairy.producer.company.Company;
import io.codearte.jfairy.producer.company.CompanyFactory;
import io.codearte.jfairy.producer.company.CompanyProvider;
import org.apache.commons.lang3.RandomStringUtils;
import org.joda.time.DateTime;
import org.joda.time.Years;

import javax.inject.Inject;

import static org.apache.commons.lang3.StringUtils.lowerCase;
import static org.apache.commons.lang3.StringUtils.stripAccents;

public class PersonProvider implements Provider<Person> {

	static final int MIN_AGE = 1;
	static final int MAX_AGE = 100;
	@VisibleForTesting
	static final String FIRST_NAME = "firstNames";
	@VisibleForTesting
	static final String LAST_NAME = "lastNames";
	@VisibleForTesting
	static final String PERSONAL_EMAIL = "personalEmails";
	@VisibleForTesting
	static final String TELEPHONE_NUMBER_FORMATS = "telephone_number_formats";

	private Person.Sex sex;
	private String telephoneNumberFormat;
	private Integer age;
	private DateTime dateOfBirth;
	private Company company;
	private Address address;
	private String firstName;
	private String middleName;
	private String lastName;
	private String email;
	private String username;
	private String telephoneNumber;
	private String password;
	private String companyEmail;
	private String nationalIdentityCardNumber;
	private String nationalIdentificationNumber;
	private String passportNumber;
	private final DataMaster dataMaster;

	private final DateProducer dateProducer;
	private final BaseProducer baseProducer;
	private final NationalIdentificationNumberFactory nationalIdentificationNumberFactory;
	private final NationalIdentityCardNumberProvider nationalIdentityCardNumberProvider;
	private final AddressProvider addressProvider;
	private final CompanyFactory companyFactory;
	private final TimeProvider timeProvider;
	private final PassportNumberProvider passportNumberProvider;

	@Inject
	public PersonProvider(DataMaster dataMaster,
						  DateProducer dateProducer,
						  BaseProducer baseProducer,
						  NationalIdentificationNumberFactory nationalIdentificationNumberFactory,
						  NationalIdentityCardNumberProvider nationalIdentityCardNumberProvider,
						  AddressProvider addressProvider,
						  CompanyFactory companyFactory,
						  PassportNumberProvider passportNumberProvider,
						  TimeProvider timeProvider,
						  @Assisted PersonProperties.PersonProperty... personProperties) {

		this.dataMaster = dataMaster;
		this.dateProducer = dateProducer;
		this.baseProducer = baseProducer;
		this.nationalIdentificationNumberFactory = nationalIdentificationNumberFactory;
		this.nationalIdentityCardNumberProvider = nationalIdentityCardNumberProvider;
		this.addressProvider = addressProvider;
		this.passportNumberProvider = passportNumberProvider;
		this.companyFactory = companyFactory;
		this.timeProvider = timeProvider;

		for (PersonProperties.PersonProperty personProperty : personProperties) {
			personProperty.apply(this, baseProducer);
		}
	}

	@Override
	public Person get() {

		if (sex == null) {
			sex = randomBoolean() ? Person.Sex.MALE : Person.Sex.FEMALE;
		}

		//fixme - should be created only if needed
		if (company == null) {
			company = companyFactory.produceCompany().get();
		}

		if (firstName == null) {
			firstName = dataMaster.getValuesOfType(FIRST_NAME, sex.name());
		}
		if (middleName == null) {
			middleName = randomBoolean() ? dataMaster.getValuesOfType(FIRST_NAME, sex.name()) : "";
		}
		if (lastName == null) {
			lastName = dataMaster.getValuesOfType(LAST_NAME, sex.name());
		}
		if (email == null) {
			email = generateEmail(firstName, lastName);
		}
		if (username == null) {
			username = generateUsername(firstName, lastName);
		}

		if (telephoneNumber == null) {
			if (telephoneNumberFormat == null) {
				telephoneNumberFormat = dataMaster.getRandomValue(TELEPHONE_NUMBER_FORMATS);
			}
			telephoneNumber = baseProducer.numerify(telephoneNumberFormat);
		}

		if (dateOfBirth != null) {
			age = Years.yearsBetween(dateOfBirth, DateTime.now()).getYears();
		}
		if (age == null) {
			age = baseProducer.randomBetween(MIN_AGE, MAX_AGE);
		}
		if (dateOfBirth == null) {
			dateOfBirth = generateDateOfBirth();
		}

		if (companyEmail == null) {
			companyEmail = generateCompanyEmail(firstName, lastName, company);
		}
		if (password == null) {
			// FIXME: Replace this with baseProducer
			password = RandomStringUtils.randomAlphanumeric(8);
		}

		if (nationalIdentityCardNumber == null) {
			nationalIdentityCardNumber = nationalIdentityCardNumberProvider.get();
		}

		if (nationalIdentificationNumber == null) {
			nationalIdentificationNumber = nationalIdentificationNumber();
		}
		if (address == null) {
			address = addressProvider.get();
		}
		if (passportNumber == null) {
			passportNumber = passportNumberProvider.get();
		}
		return new Person(firstName, middleName, lastName, address, email,
				username, password, sex, telephoneNumber, dateOfBirth, age,
				nationalIdentityCardNumber, nationalIdentificationNumber, passportNumber,
				company, companyEmail);
	}

	private String generateCompanyEmail(String firstName, String lastName, Company company) {
		return new CompanyEmailProvider(firstName, lastName, company).get();
	}

	private DateTime generateDateOfBirth() {
		DateTime maxDate = timeProvider.getCurrentDate().minusYears(age);
		DateTime minDate = maxDate.minusYears(1).plusDays(1);
		return dateProducer.randomDateBetweenTwoDates(minDate, maxDate);
	}

	private boolean randomBoolean() {
		return baseProducer.trueOrFalse();
	}

	private String nationalIdentificationNumber() {
		return nationalIdentificationNumberFactory.produceNationalIdentificationNumberProvider(
				NationalIdentificationNumberProperties.dateOfBirth(dateOfBirth),
				NationalIdentificationNumberProperties.sex(sex)).get().getValue();
	}

	private String generateEmail(String firstName, String lastName) {
		EmailProvider emailProvider = new EmailProvider(dataMaster, baseProducer, firstName, lastName);
		return emailProvider.get();
	}

	public void telephoneNumberFormat(String telephoneFormat) {
		telephoneNumberFormat = telephoneFormat;
	}

	private String generateUsername(String firstName, String lastName) {
		if (randomBoolean()) {
			return lowerCase(stripAccents(firstName.substring(0, 1) + lastName));
		} else {
			return lowerCase(stripAccents(firstName + lastName.substring(0, 1)));
		}
	}

	public void setSex(Person.Sex sex) {
		this.sex = sex;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public void setCompany(Company company) {
		this.company = company;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public void setMiddleName(String middleName) {
		this.middleName = middleName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public void setTelephoneNumber(String telephoneNumber) {
		this.telephoneNumber = telephoneNumber;
	}

	public void setDateOfBirth(DateTime dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public void setAddress(Address address) {
		this.address = address;
	}

	public void setCompanyEmail(String companyEmail) {
		this.companyEmail = companyEmail;
	}

	public void setNationalIdentityCardNumber(String nationalIdentityCardNumber) {
		this.nationalIdentityCardNumber = nationalIdentityCardNumber;
	}

	public void setNationalIdentificationNumber(String nationalIdentificationNumber) {
		this.nationalIdentificationNumber = nationalIdentificationNumber;
	}

	public void setPassportNumber(String passportNumber) {
		this.passportNumber = passportNumber;
	}
}


