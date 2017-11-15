package io.codearte.jfairy.producer.person.locale.ka;

import io.codearte.jfairy.producer.person.locale.AbstractAddress;

import static org.apache.commons.lang3.StringUtils.isNotBlank;

public class KaAddress extends AbstractAddress {
	public KaAddress(String street, String streetNumber, String apartmentNumber, String postalCode, String city) {
		super(street, streetNumber, apartmentNumber, postalCode, city);
	}

	@Override
	public String getAddressLine1() {
		return postalCode + ", " + city;
	}

	@Override
	public String getAddressLine2() {
		return street + " №" + streetNumber + (isNotBlank(apartmentNumber) ? ", ბინა " + apartmentNumber : "");
	}
}
