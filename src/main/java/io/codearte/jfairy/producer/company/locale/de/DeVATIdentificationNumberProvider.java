package io.codearte.jfairy.producer.company.locale.de;

import io.codearte.jfairy.producer.VATIdentificationNumberProvider;

import static org.apache.commons.lang3.RandomStringUtils.randomNumeric;

/**
 * German VAT identification number (Umsatzsteuer-Identifikationsnummer or USt-IdNr.)
 * <p>
 * https://en.wikipedia.org/wiki/VAT_identification_number
 *
 * @author Roland Weisleder
 */
public class DeVATIdentificationNumberProvider implements VATIdentificationNumberProvider {

	@Override
	public String get() {
		return randomNumeric(9);
	}

	public boolean isValid(String vatIdentificationNumber) {
		return vatIdentificationNumber.matches("^[0-9]{9}$");
	}

}
