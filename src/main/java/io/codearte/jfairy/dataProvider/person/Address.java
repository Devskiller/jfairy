package io.codearte.jfairy.dataProvider.person;

public class Address {

	private final String postalCode;

	private final String city;

	private final String street;

	private final String streetNumber;

	private final String apartmentNumber;

	public Address(String postalCode, String city, String street, String streetNumber, String apartmentNumber) {
		this.postalCode = postalCode;
		this.city = city;
		this.street = street;
		this.streetNumber = streetNumber;
		this.apartmentNumber = apartmentNumber;
	}

	public String getPostalCode() {
		return postalCode;
	}

	public String getCity() {
		return city;
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

	@Override
	public String toString() {
		return postalCode + " " + city;
	}
}
