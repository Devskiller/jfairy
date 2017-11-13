package io.codearte.jfairy.producer.person.locale.ka;

import com.google.inject.Inject;
import io.codearte.jfairy.data.DataMaster;
import io.codearte.jfairy.producer.BaseProducer;
import io.codearte.jfairy.producer.person.AbstractAddressProvider;

public class KaAddressProvider extends AbstractAddressProvider {

	@Inject
	public KaAddressProvider(DataMaster dataMaster, BaseProducer baseProducer) {
		super(dataMaster, baseProducer);
	}

	@Override
	public KaAddress get() {
		return new KaAddress(getStreet(), getStreetNumber(), getApartmentNumber(), getPostalCode(), getCity());
	}
}
