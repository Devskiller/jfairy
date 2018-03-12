package com.devskiller.jfairy.producer.person.locale.pl;

import javax.inject.Inject;

import com.devskiller.jfairy.data.DataMaster;
import com.devskiller.jfairy.producer.BaseProducer;
import com.devskiller.jfairy.producer.person.AbstractAddressProvider;

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
