package io.codearte.jfairy.producer.person;

import io.codearte.jfairy.data.DataMaster;
import io.codearte.jfairy.producer.BaseProducer;

public abstract class AbstractAddressProvider implements AddressProvider {

	private static final String POSTAL_CODE_FORMAT = "postal_code";

	private static final String CITY = "city";

	private static final String STREET = "street";

	protected final BaseProducer baseProducer;

	protected final DataMaster dataMaster;

	public AbstractAddressProvider(DataMaster dataMaster, BaseProducer baseProducer) {
		this.baseProducer = baseProducer;
		this.dataMaster = dataMaster;
	}

	public String getCity() {
		return dataMaster.getRandomValue(CITY);
	}

	public String getPostalCode() {
		String postalCodeFormat = dataMaster.getRandomValue(POSTAL_CODE_FORMAT);
		return baseProducer.numerify(postalCodeFormat);
	}

	public String getStreet() {
		return dataMaster.getRandomValue(STREET);
	}

	public String getStreetNumber() {
		return String.valueOf(baseProducer.randomInt(199));
	}

	public String getApartmentNumber() {
		return baseProducer.trueOrFalse() ? String.valueOf(baseProducer.randomInt(350)) : "";
	}

}
