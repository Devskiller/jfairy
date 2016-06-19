package io.codearte.jfairy.producer.person;

import com.google.inject.Provider;
import org.joda.time.DateTime;


public interface NationalIdentificationNumberProvider extends Provider<NationalIdentificationNumber> {

	NationalIdentificationNumber get();

	void setIssueDate(DateTime dateOfBirth);

	void setSex(Person.Sex sex);
}
