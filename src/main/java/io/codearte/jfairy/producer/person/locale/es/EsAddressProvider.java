package io.codearte.jfairy.producer.person.locale.es;

import io.codearte.jfairy.data.DataMaster;
import io.codearte.jfairy.producer.BaseProducer;
import io.codearte.jfairy.producer.person.AbstractAddressProvider;

import javax.inject.Inject;

public class EsAddressProvider extends AbstractAddressProvider {

	@Inject
	public EsAddressProvider(DataMaster dataMaster, BaseProducer baseProducer) {
		super(dataMaster, baseProducer);
	}

	@Override
	public EsAddress get() {
		return new EsAddress(getStreet(), getStreetNumber(), getApartmentNumber(),
				getPostalCode(), getCity());
	}

}
