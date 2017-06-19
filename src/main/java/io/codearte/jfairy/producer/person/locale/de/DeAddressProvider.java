package io.codearte.jfairy.producer.person.locale.de;

import io.codearte.jfairy.data.DataMaster;
import io.codearte.jfairy.producer.BaseProducer;
import io.codearte.jfairy.producer.person.AbstractAddressProvider;

import javax.inject.Inject;

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
