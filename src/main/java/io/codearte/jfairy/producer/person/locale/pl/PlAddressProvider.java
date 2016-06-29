package io.codearte.jfairy.producer.person.locale.pl;

import io.codearte.jfairy.data.DataMaster;
import io.codearte.jfairy.producer.BaseProducer;
import io.codearte.jfairy.producer.person.AbstractAddressProvider;

import javax.inject.Inject;

public class PlAddressProvider extends AbstractAddressProvider {

	@Inject
	public PlAddressProvider(DataMaster dataMaster, BaseProducer baseProducer) {
		super(dataMaster, baseProducer);
	}

	@Override
	public PlAddress get() {
		return new PlAddress(getStreet(), getStreetNumber(), getApartmentNumber(),
				getPostalCode(), getCity());
	}

}
