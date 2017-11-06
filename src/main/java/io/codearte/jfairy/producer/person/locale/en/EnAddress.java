package io.codearte.jfairy.producer.person.locale.en;

import io.codearte.jfairy.producer.person.Address;
import io.codearte.jfairy.producer.person.locale.AbstractAddress;

import static org.apache.commons.lang3.StringUtils.isNotBlank;
import static org.apache.commons.lang3.SystemUtils.LINE_SEPARATOR;

public class EnAddress extends AbstractAddress {

	public EnAddress(String streetNumber, String street, String apartmentNumber, String city, String postalCode) {
		super(street, streetNumber, apartmentNumber, postalCode, city);
	}

	@Override
	public String getAddressLine1() {
		return streetNumber + " " + street
				+ (isNotBlank(apartmentNumber) ? " APT " + apartmentNumber : "");
	}

	@Override
	public String getAddressLine2() {
		return city + " " + postalCode;
	}
}
