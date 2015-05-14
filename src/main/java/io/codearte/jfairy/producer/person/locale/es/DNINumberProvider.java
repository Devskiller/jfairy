package io.codearte.jfairy.producer.person.locale.es;

import io.codearte.jfairy.producer.person.NationalIdentityCardNumberProvider;
import org.apache.commons.lang3.RandomStringUtils;

import java.util.regex.Pattern;

/**
 * @author graux
 * @since 26/04/2015
 * Documento Nacional de Identidad (DNI) Español
 */
public class DNINumberProvider implements NationalIdentityCardNumberProvider {

	private static final String REGEX_DNI = "^\\d{8}([-]?)[A-Z]$";
	private Pattern regexDni;

	public DNINumberProvider() {
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
