package io.codearte.jfairy.producer.person.locale.de;

import io.codearte.jfairy.producer.person.Address;
import io.codearte.jfairy.producer.person.locale.ContinentalAddress;

import static org.apache.commons.lang3.StringUtils.isNotBlank;
import static org.apache.commons.lang3.SystemUtils.LINE_SEPARATOR;

/**
 * @author Roland Weisleder
 */
public class DeAddress extends ContinentalAddress {

	public DeAddress(String streetNumber, String street, String apartmentNumber, String city, String postalCode) {
		super(street, streetNumber, apartmentNumber, postalCode, city);
	}

	@Override
	protected String getApartmentMark() {
		return ", ";
	}
}
