package com.devskiller.jfairy.producer.person.locale.br;

import javax.inject.Inject;

import com.devskiller.jfairy.data.DataMaster;
import com.devskiller.jfairy.producer.BaseProducer;
import com.devskiller.jfairy.producer.person.AbstractAddressProvider;

/**
 * @author Nelson Rodrigues da Silva Júnior
 * @since 15.10.2020
 */
public class BrAddressProvider extends AbstractAddressProvider {

	@Inject
	public BrAddressProvider(DataMaster dataMaster, BaseProducer baseProducer) {
		super(dataMaster, baseProducer);
	}

	@Override
	public BrAddress get() {
		return new BrAddress(getStreetNumber(), getStreet(), getApartmentNumber(),
				getCity(), getPostalCode());
	}

}
