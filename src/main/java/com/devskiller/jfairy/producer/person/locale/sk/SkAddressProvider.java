package com.devskiller.jfairy.producer.person.locale.sk;

import com.devskiller.jfairy.data.DataMaster;
import com.devskiller.jfairy.producer.BaseProducer;
import com.devskiller.jfairy.producer.person.AbstractAddressProvider;

import javax.inject.Inject;

public class SkAddressProvider extends AbstractAddressProvider {

	@Inject
	public SkAddressProvider(DataMaster dataMaster, BaseProducer baseProducer) {
		super(dataMaster, baseProducer);
	}

	@Override
	public SkAddress get() {
		return new SkAddress(getStreet(), getStreetNumber(), getApartmentNumber(),
				getPostalCode(), getCity());
	}

}
