
package io.codearte.jfairy.producer.person.locale.sv;

import io.codearte.jfairy.producer.person.locale.ContinentalAddress;

public class SvAddress extends ContinentalAddress {

	public SvAddress(String street, String streetNumber, String apartmentNumber, String postalCode, String city) {
		super(street, streetNumber, apartmentNumber, postalCode, city);
	}

	@Override
	public String getApartmentMark() {
		return " Lgh ";
	}
}
