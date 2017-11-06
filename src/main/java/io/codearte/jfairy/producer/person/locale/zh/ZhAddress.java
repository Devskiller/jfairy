package io.codearte.jfairy.producer.person.locale.zh;

import io.codearte.jfairy.producer.person.Address;
import io.codearte.jfairy.producer.person.locale.AbstractAddress;

import static org.apache.commons.lang3.StringUtils.isNotBlank;
import static org.apache.commons.lang3.SystemUtils.LINE_SEPARATOR;

/**
 * io.codearte.jfairy.producer.person.locale.zh.ZhAddress
 *
 * @author lhfcws
 * @since 2017/3/2
 */
public class ZhAddress extends AbstractAddress {

	private static final String CITY = "市";
	private static final String NUMBER = "号";
	private static final String ROOM = "房";
	private static final String POSTCODE = "邮编";

	public ZhAddress(String streetNumber, String street, String apartmentNumber, String city, String postalCode) {
		super(street, streetNumber, apartmentNumber, postalCode, city);
	}

	@Override
	public String getAddressLine1() {
		String line = city + CITY + street + streetNumber + NUMBER;
		if (apartmentNumber.length() > 0)
			return line + " " + apartmentNumber + ROOM;
		else
			return line;
	}

	@Override
	public String getAddressLine2() {
		return POSTCODE + " " + postalCode;
	}
}
