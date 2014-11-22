package eu.codearte.jfairy.producer.person;

import com.google.inject.Inject;
import com.google.inject.Provider;
import eu.codearte.jfairy.data.DataMaster;
import eu.codearte.jfairy.producer.BaseProducer;

class AddressProvider implements Provider<Address> {

	private static final String POSTAL_CODE_FORMAT = "postal_code";
	private static final String CITY = "city";

	private final BaseProducer baseProducer;
	private final DataMaster dataMaster;

	@Inject
	public AddressProvider(DataMaster dataMaster, BaseProducer baseProducer) {
		this.baseProducer = baseProducer;
		this.dataMaster = dataMaster;
	}

	@Override
	public Address get() {
		String postalCodeFormat = dataMaster.getRandomValue(POSTAL_CODE_FORMAT);

		String city = dataMaster.getRandomValue(CITY);
		String postalCode = baseProducer.numerify(postalCodeFormat);

		return new Address(postalCode, city);
	}

}
