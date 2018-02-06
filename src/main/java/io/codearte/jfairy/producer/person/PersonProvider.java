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
import org.apache.commons.lang3.RandomStringUtils;
import org.joda.time.DateTime;
import org.joda.time.Years;

import javax.inject.Inject;

import static org.apache.commons.lang3.StringUtils.lowerCase;
import static org.apache.commons.lang3.StringUtils.stripAccents;

public interface PersonProvider extends Provider<Person> {

	int MIN_AGE = 1;
	int MAX_AGE = 100;
	@VisibleForTesting
	String FIRST_NAME = "firstNames";
	@VisibleForTesting
	String LAST_NAME = "lastNames";
	@VisibleForTesting
	String PERSONAL_EMAIL = "personalEmails";
	@VisibleForTesting
	String TELEPHONE_NUMBER_FORMATS = "telephone_number_formats";
	@VisibleForTesting
	String JOB_TITLE = "jobTitles";

	@Override
	Person get();

	void generateSex();

	void generateCompany();

	void generateFirstName();

	void generateMiddleName();

	void generateLastName();

	void generateEmail();

	void generateUsername();

	void generateTelephoneNumber();

	void generateAge();

	void generateDateOfBirth();

	void generateCompanyEmail();

	void generatePassword();

	void generateJobTitle();

	void generateNationalIdentityCardNumber();

	void generateNationalIdentificationNumber();

	void generateAddress();

	void generatePassportNumber();

	void setTelephoneNumberFormat(String telephoneFormat);

	void setSex(Person.Sex sex);

	void setAge(int age);

	void setCompany(Company company);

	void setFirstName(String firstName);

	void setMiddleName(String middleName);

	void setLastName(String lastName);

	void setEmail(String email);

	void setUsername(String username);

	void setTelephoneNumber(String telephoneNumber);

	void setDateOfBirth(DateTime dateOfBirth);

	void setPassword(String password);

	void setAddress(Address address);

	void setCompanyEmail(String companyEmail);

	void setNationalIdentityCardNumber(String nationalIdentityCardNumber);

	void setNationalIdentificationNumber(String nationalIdentificationNumber);

	void setPassportNumber(String passportNumber);

	void setJobTitle(String jobTitle);
}


