package io.codearte.jfairy.producer.person.locale.en;

import io.codearte.jfairy.producer.person.Address;

import static org.apache.commons.lang3.StringUtils.isNotBlank;
import static org.apache.commons.lang3.SystemUtils.LINE_SEPARATOR;

public class EnAddress implements Address {

	private final String streetNumber;

	private final String street;

	private final String apartmentNumber;

	private final String city;

	private final String postalCode;

	public EnAddress(String streetNumber, String street, String apartmentNumber, String city, String postalCode) {
		this.streetNumber = streetNumber;
		this.street = street;
		this.apartmentNumber = apartmentNumber;
		this.city = city;
		this.postalCode = postalCode;
	}

	public String getStreetNumber() {
		return streetNumber;
	}

	public String getStreet() {
		return street;
	}

	public String getApartmentNumber() {
		return apartmentNumber;
	}

	public String getCity() {
		return city;
	}

	public String getPostalCode() {
		return postalCode;
	}

	public String getAddressLine1() {
		return streetNumber + " " + street
				+ (isNotBlank(apartmentNumber) ? " APT " + apartmentNumber : "");
	}

	public String getAddressLine2() {
		return city + " " + postalCode;
	}

	@Override
	public String toString() {
		return getAddressLine1() + LINE_SEPARATOR + getAddressLine2();
	}
}
