package eu.codearte.fairyland.producer;

import eu.codearte.fairyland.producer.util.RandomDataGenerator;
import eu.codearte.fairyland.producer.util.RandomGenerator;
import org.apache.commons.lang3.StringUtils;

import javax.inject.Inject;

/**
 * @author Codearte
 * @since 2013-10-07
 */
public class Company {

	public static final String DOMAIN = "domains";
	public static final String COMPANY_NAME = "companyNames";
	public static final String COMPANY_SUFFIX = "companySuffixes";
	public static final String COMPANY_EMAIL = "companyEmails";
	private final VATIdentificationNumber vatIdentificationNumber;

	private String name;
	private final String domain;
	private final String email;

	@Inject
	public Company(RandomGenerator random, RandomDataGenerator generator, VATIdentificationNumber vatIdentificationNumber) {
		this.vatIdentificationNumber = vatIdentificationNumber;

		name = generator.getValues(COMPANY_NAME);
		if (random.trueOrFalse()) {
			name += " " + generator.getValues(COMPANY_SUFFIX);
		}
		domain = StringUtils.strip(StringUtils.deleteWhitespace(name.toLowerCase()), ".")
				+ "." + generator.getValues(DOMAIN);
		email = generator.getValues(COMPANY_EMAIL);
	}

	public String name() {
		return name;
	}

	public String url() {
		return "http://www." + domain;
	}

	public String email() {
		return email + "@" + domain;
	}

	public String domain() {
		return domain;
	}

	public String vatIdentificationNumber() {
		return vatIdentificationNumber.generate();
	}

}
