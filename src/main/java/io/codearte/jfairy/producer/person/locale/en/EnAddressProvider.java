package io.codearte.jfairy.producer.person.locale.en;

import io.codearte.jfairy.data.DataMaster;
import io.codearte.jfairy.producer.BaseProducer;
import io.codearte.jfairy.producer.person.AbstractAddressProvider;

import javax.inject.Inject;

public class EnAddressProvider extends AbstractAddressProvider {

	@Inject
	public EnAddressProvider(DataMaster dataMaster, BaseProducer baseProducer) {
		super(dataMaster, baseProducer);
	}

	@Override
	public EnAddress get() {
		return new EnAddress(getStreetNumber(), getStreet(), getApartmentNumber(),
				getCity(), getPostalCode());
	}

}
