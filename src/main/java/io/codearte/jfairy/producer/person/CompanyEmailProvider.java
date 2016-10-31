package io.codearte.jfairy.producer.person;

import com.google.inject.Provider;
import io.codearte.jfairy.producer.company.Company;
import io.codearte.jfairy.producer.util.TextUtils;

import static org.apache.commons.lang3.StringUtils.lowerCase;

public class CompanyEmailProvider implements Provider<String> {

	private final String firstName;
	private final String lastName;
	private final Company company;

	public CompanyEmailProvider(String firstName, String lastName, Company company) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.company = company;
	}

	@Override
	public String get() {
		return TextUtils.stripAccents(lowerCase(firstName + '.' + lastName + '@' + company.getDomain())).replaceAll(" ", ".");
	}
}
