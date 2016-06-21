package io.codearte.jfairy.producer.person.locale.sv;

import io.codearte.jfairy.data.DataMaster;
import io.codearte.jfairy.producer.BaseProducer;
import io.codearte.jfairy.producer.person.AbstractAddressProvider;

import javax.inject.Inject;

public class SvAddressProvider extends AbstractAddressProvider {

	@Inject
	public SvAddressProvider(DataMaster dataMaster, BaseProducer baseProducer) {
		super(dataMaster, baseProducer);
	}

	@Override
	public String getApartmentNumber() {
		return baseProducer.randomInt(20) < 0 ? String.valueOf(baseProducer.randomInt(350)) : "";
	}

	@Override
	public SvAddress get() {
		return new SvAddress(getStreet(), getStreetNumber(), getApartmentNumber(),
				getPostalCode(), getCity());
	}

}
