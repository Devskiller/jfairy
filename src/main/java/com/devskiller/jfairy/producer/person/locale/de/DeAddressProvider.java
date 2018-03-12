package com.devskiller.jfairy.producer.person.locale.de;

import javax.inject.Inject;

import com.devskiller.jfairy.data.DataMaster;
import com.devskiller.jfairy.producer.BaseProducer;
import com.devskiller.jfairy.producer.person.AbstractAddressProvider;

/**
 * @author Roland Weisleder
 */
public class DeAddressProvider extends AbstractAddressProvider {

	@Inject
	public DeAddressProvider(DataMaster dataMaster, BaseProducer baseProducer) {
		super(dataMaster, baseProducer);
	}

	@Override
	public DeAddress get() {
		return new DeAddress(getStreetNumber(), getStreet(), getApartmentNumber(),
			getCity(), getPostalCode());
	}

}
