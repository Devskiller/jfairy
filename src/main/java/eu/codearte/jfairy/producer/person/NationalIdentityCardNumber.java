package eu.codearte.jfairy.producer.person;

import org.joda.time.DateTime;

public interface NationalIdentityCardNumber {

	String generate(DateTime date);

	String generate();
}
