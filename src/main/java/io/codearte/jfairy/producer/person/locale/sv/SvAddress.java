
package io.codearte.jfairy.producer.person.locale.sv;

import io.codearte.jfairy.producer.person.Address;

import static org.apache.commons.lang3.StringUtils.isNotBlank;
import static org.apache.commons.lang3.SystemUtils.LINE_SEPARATOR;

public class SvAddress implements Address {

	private final String street;

	private final String streetNumber;

	private final String apartmentNumber;

	private final String postalCode;

	private final String city;

	public SvAddress(String street, String streetNumber, String apartmentNumber, String postalCode, String city) {
		this.street = street;
		this.streetNumber = streetNumber;
		this.apartmentNumber = apartmentNumber;
		this.postalCode = postalCode;
		this.city = city;
	}

	public String street() {
		return street;
	}

	public String streetNumber() {
		return streetNumber;
	}

	public String apartmentNumber() {
		return apartmentNumber;
	}

	public String getPostalCode() {
		return postalCode;
	}

	public String getCity() {
		return city;
	}

	public String getAddressLine1() {
		return street + " " + streetNumber
				+ (isNotBlank(apartmentNumber) ? " Lgh " + apartmentNumber : "");
	}

	public String getAddressLine2() {
		return postalCode + " " + city;
	}

	@Override
	public String toString() {
		return getAddressLine1() + LINE_SEPARATOR + getAddressLine2();
	}
}
