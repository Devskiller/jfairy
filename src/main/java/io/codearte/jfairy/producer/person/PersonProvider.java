package io.codearte.jfairy.producer.person;

import com.google.common.annotations.VisibleForTesting;
import com.google.inject.Provider;
import com.google.inject.assistedinject.Assisted;
import io.codearte.jfairy.data.DataMaster;
import io.codearte.jfairy.producer.BaseProducer;
import io.codearte.jfairy.producer.DateProducer;
import io.codearte.jfairy.producer.TimeProvider;
import io.codearte.jfairy.producer.company.Company;
import io.codearte.jfairy.producer.company.CompanyProvider;
import io.codearte.jfairy.producer.util.CharConverter;
import org.apache.commons.lang3.RandomStringUtils;
import org.joda.time.DateTime;

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
	private final DataMaster dataMaster;

	private final DateProducer dateProducer;
	private final BaseProducer baseProducer;
	private final NationalIdentificationNumberFactory nationalIdentificationNumberFactory;
	private final NationalIdentityCardNumberProvider nationalIdentityCardNumberProvider;
	private final AddressProvider addressProvider;
	private final CompanyProvider companyProvider;
	private final CharConverter charConverter;
	private final TimeProvider timeProvider;
	private final PassportNumberProvider passportNumberProvider;

	@Inject
	public PersonProvider(DataMaster dataMaster,
						  DateProducer dateProducer,
						  BaseProducer baseProducer,
						  NationalIdentificationNumberFactory nationalIdentificationNumberFactory,
						  NationalIdentityCardNumberProvider nationalIdentityCardNumberProvider,
						  AddressProvider addressProvider,
						  CompanyProvider companyProvider,
						  PassportNumberProvider passportNumberProvider,
						  CharConverter charConverter,
						  TimeProvider timeProvider,
						  @Assisted PersonProperties.PersonProperty... personProperties) {

		this.dataMaster = dataMaster;
		this.dateProducer = dateProducer;
		this.baseProducer = baseProducer;
		this.nationalIdentificationNumberFactory = nationalIdentificationNumberFactory;
		this.nationalIdentityCardNumberProvider = nationalIdentityCardNumberProvider;
		this.addressProvider = addressProvider;
		this.passportNumberProvider = passportNumberProvider;
		this.companyProvider = companyProvider;
		this.charConverter = charConverter;
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
			company = companyProvider.get();
		}

		String firstName = dataMaster.getValuesOfType(FIRST_NAME, sex.name());
		String middleName = randomBoolean() ? dataMaster.getValuesOfType(FIRST_NAME, sex.name()) : "";
		String lastName = dataMaster.getValuesOfType(LAST_NAME, sex.name());
		String email = generateEmail(firstName, lastName);
		String username = generateUsername(firstName, lastName);
		if (telephoneNumberFormat == null) {
			telephoneNumberFormat = dataMaster.getRandomValue(TELEPHONE_NUMBER_FORMATS);
		}
		String telephoneNumber = baseProducer.numerify(telephoneNumberFormat);
		if (age == null) {
			age = baseProducer.randomBetween(MIN_AGE, MAX_AGE);
		}
		if (dateOfBirth == null) {
			dateOfBirth = generateDateOfBirth();
		}
		String companyEmail = stripAccents(lowerCase(firstName + '.' + lastName + '@' + company.getDomain()));
		// FIXME: Replace this with baseProducer
		String password = RandomStringUtils.randomAlphanumeric(8);

		return new Person(firstName, middleName, lastName, addressProvider.get(), email,
				username, password, sex, telephoneNumber, dateOfBirth, age,
				nationalIdentityCardNumberProvider.get(), nationalIdentificationNumber(), passportNumberProvider.get(),
				company, companyEmail);
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
		String temp = "";
		if (randomBoolean()) {
			temp = firstName;
			if (randomBoolean()) {
				temp += ".";
			}
		}
		return charConverter.romanize(lowerCase(temp + lastName + '@' + dataMaster.getRandomValue(PERSONAL_EMAIL)));
	}

	public void setSex(Person.Sex sex) {
		this.sex = sex;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public void telephoneNumberFormat(String telephoneFormat) {
		telephoneNumberFormat = telephoneFormat;
	}

	public void setCompany(Company company) {
		this.company = company;
	}

	private String generateUsername(String firstName, String lastName) {
		if (randomBoolean()) {
			return lowerCase(stripAccents(firstName.substring(0, 1) + lastName));
		} else {
			return lowerCase(stripAccents(firstName + lastName.substring(0, 1)));
		}
	}
}


