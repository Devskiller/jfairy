
package io.codearte.jfairy.producer.person.locale.es;

import io.codearte.jfairy.producer.person.Address;
import io.codearte.jfairy.producer.person.locale.ContinentalAddress;

import static org.apache.commons.lang3.StringUtils.isNotBlank;
import static org.apache.commons.lang3.SystemUtils.LINE_SEPARATOR;

public class EsAddress extends ContinentalAddress {

	public EsAddress(String street, String streetNumber, String apartmentNumber, String postalCode, String city) {
		super(street, streetNumber, apartmentNumber, postalCode, city);
	}

	@Override
	protected String getStreetNumberSeparator() {
		return ", ";
	}

	@Override
	public String getApartmentMark() {
		return " ";
	}
}

