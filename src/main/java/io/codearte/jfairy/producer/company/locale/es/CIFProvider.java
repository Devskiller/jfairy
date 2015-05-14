package io.codearte.jfairy.producer.company.locale.es;

import io.codearte.jfairy.producer.VATIdentificationNumberProvider;

import java.util.regex.Pattern;

import static org.apache.commons.lang3.RandomStringUtils.randomAlphabetic;
import static org.apache.commons.lang3.RandomStringUtils.randomAlphanumeric;
import static org.apache.commons.lang3.RandomStringUtils.randomNumeric;

/**
 * @author graux
 * @since 26/04/2015
 * Código de Identificación Fiscal (CIF) Español
 */
public class CIFProvider implements VATIdentificationNumberProvider {

	private static final String REGEX_CIF = "^[A-Z][0-9]{2}[0-9]{5}([KPQSABEH]|[0-9]|[A-Z])$";

	private Pattern regexCif;

	public CIFProvider() {
		this.regexCif = Pattern.compile(REGEX_CIF);
	}

	@Override
	public String get() {
		return String.format("%s%s%s",
				randomAlphabetic(1).toUpperCase(), randomNumeric(7), randomAlphanumeric(1).toUpperCase());
	}

	public boolean isValid(String cif) {
		return this.regexCif.matcher(cif).matches();
	}
}