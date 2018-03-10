package io.codearte.jfairy.producer.person;

import java.time.LocalDate;
import java.time.LocalDateTime;

import com.google.inject.Provider;

public interface NationalIdentificationNumberProvider extends Provider<NationalIdentificationNumber> {

	NationalIdentificationNumber get();

	void setIssueDate(LocalDate dateOfBirth);

	void setSex(Person.Sex sex);
}
