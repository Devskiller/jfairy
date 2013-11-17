package eu.codearte.fairyland.producer.person;

import com.google.inject.Inject;
import eu.codearte.fairyland.data.DataMaster;
import eu.codearte.fairyland.producer.BaseProducer;

public class Address {

	private static final String POSTAL_CODE_FORMAT = "postal_code";

	private final DataMaster dataMaster;
	private final BaseProducer baseProducer;

	private String postalCodeFormat;

	@Inject
	public Address(DataMaster dataMaster, BaseProducer baseProducer) {
		this.dataMaster = dataMaster;
		this.baseProducer = baseProducer;

		postalCodeFormat = this.dataMaster.getRandomValue(POSTAL_CODE_FORMAT);

	}

	public String getPostalCode(){
		return baseProducer.numerify(postalCodeFormat);
	}

}
