package com.devskiller.jfairy.producer.person.locale.br;

import com.devskiller.jfairy.producer.person.locale.AbstractAddress;

import static org.apache.commons.lang3.StringUtils.isNotBlank;

/**
 * @author Nelson Rodrigues da Silva Júnior
 * @since 15.10.2020
 */
public class BrAddress extends AbstractAddress {

	public BrAddress(String streetNumber, String street, String apartmentNumber, String city, String postalCode) {
		super(street, streetNumber, apartmentNumber, postalCode, city);
	}

	@Override
	public String getAddressLine1() {
		return street + ", " + streetNumber
				+ (isNotBlank(apartmentNumber) ? " APT " + apartmentNumber : "");
	}

	@Override
	public String getAddressLine2() {
		return postalCode + " " + city;
	}
}
