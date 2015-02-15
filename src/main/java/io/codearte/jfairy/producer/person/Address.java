package io.codearte.jfairy.producer.person;

public class Address {

	private final String postalCode;
	private final String city;

	public Address(String postalCode, String city) {
		this.postalCode = postalCode;
		this.city = city;
	}

	public String getPostalCode() {
		return postalCode;
	}

	public String getCity() {
		return city;
	}

	@Override
	public String toString() {
		return postalCode + " " + city;
	}
}
