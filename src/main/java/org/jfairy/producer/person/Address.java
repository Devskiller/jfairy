package org.jfairy.producer.person;

import com.google.inject.Inject;
import org.jfairy.data.DataMaster;
import org.jfairy.producer.BaseProducer;

public class Address {

	private static final String POSTAL_CODE_FORMAT = "postal_code";
	private static final String CITY = "city";

	private final BaseProducer baseProducer;

	private final String postalCodeFormat;
	private final String city;
	private final String postalCode;

	@Inject
	public Address(DataMaster dataMaster, BaseProducer baseProducer) {
		this.baseProducer = baseProducer;
		postalCodeFormat = dataMaster.getRandomValue(POSTAL_CODE_FORMAT);
		city = dataMaster.getRandomValue(CITY);
		postalCode = baseProducer.numerify(postalCodeFormat);
	}

	public String postalCode() {
		return postalCode;
	}

	public String city() {
		return city;
	}

	@Override
	public String toString() {
		return postalCode + " " + city;
	}
}

