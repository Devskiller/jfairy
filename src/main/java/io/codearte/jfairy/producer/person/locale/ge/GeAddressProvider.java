package io.codearte.jfairy.producer.person.locale.ge;

import com.google.inject.Inject;
import io.codearte.jfairy.data.DataMaster;
import io.codearte.jfairy.producer.BaseProducer;
import io.codearte.jfairy.producer.person.AbstractAddressProvider;

public class GeAddressProvider extends AbstractAddressProvider {

	@Inject
	public GeAddressProvider(DataMaster dataMaster, BaseProducer baseProducer) {
		super(dataMaster, baseProducer);
	}

	@Override
	public GeAddress get() {
		return new GeAddress(getStreet(), getStreetNumber(), getApartmentNumber(), getPostalCode(), getCity());
	}
}
