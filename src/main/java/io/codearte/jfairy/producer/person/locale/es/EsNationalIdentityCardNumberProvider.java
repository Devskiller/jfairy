package io.codearte.jfairy.producer.person.locale.es;

import io.codearte.jfairy.producer.person.NationalIdentityCardNumberProvider;
import org.apache.commons.lang3.RandomStringUtils;

import java.util.regex.Pattern;

/**
 * Spanish National Identity Card Number (known as Documento Nacional de Identidad or DNI)
 *
 * @author graux
 * @since 26/04/2015
 * Documento Nacional de Identidad (DNI) Español
 */
public class EsNationalIdentityCardNumberProvider implements NationalIdentityCardNumberProvider {

	private static final String REGEX_DNI = "^\\d{8}([-]?)[A-Z]$";
	private Pattern regexDni;

	public EsNationalIdentityCardNumberProvider() {
		this.regexDni = Pattern.compile(REGEX_DNI);
	}

	@Override
	public String get() {
		return String.format("%s-%s", RandomStringUtils.randomNumeric(8), RandomStringUtils.randomAlphabetic(1).toUpperCase());
	}

	public boolean isValid(String dni) {
		return this.regexDni.matcher(dni).matches();
	}
}
