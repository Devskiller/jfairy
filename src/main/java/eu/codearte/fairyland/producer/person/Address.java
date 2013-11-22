package eu.codearte.fairyland.producer.person;

import com.google.inject.Inject;
import eu.codearte.fairyland.data.DataMaster;
import eu.codearte.fairyland.producer.BaseProducer;

public class Address {

	private static final String POSTAL_CODE_FORMAT = "postal_code";
	private static final String CITY = "city";

	private final BaseProducer baseProducer;

	private final String postalCodeFormat;
	private final String city;

	@Inject
	public Address(DataMaster dataMaster, BaseProducer baseProducer) {
		this.baseProducer = baseProducer;
		postalCodeFormat = dataMaster.getRandomValue(POSTAL_CODE_FORMAT);
		city = dataMaster.getRandomValue(CITY);
	}

	public String postalCode(){
		return baseProducer.numerify(postalCodeFormat);
	}

	public String city() {
		return city;
	}

}
