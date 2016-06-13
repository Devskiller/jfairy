package io.codearte.jfairy.producer.company.locale.sv;

import io.codearte.jfairy.producer.VATIdentificationNumberProvider;

/**
 * Swedish VAT Identification number (known as Momsnr or Momsnummer in Sweden) is not yet supported
 *
 * https://en.wikipedia.org/wiki/VAT_identification_number
 */
public class MomsnrProvider implements VATIdentificationNumberProvider {

	public MomsnrProvider() {
	}

	@Override
	public String get() {
		return "";
	}
}
