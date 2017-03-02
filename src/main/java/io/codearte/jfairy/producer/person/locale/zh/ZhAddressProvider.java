package io.codearte.jfairy.producer.person.locale.zh;

import io.codearte.jfairy.data.DataMaster;
import io.codearte.jfairy.producer.BaseProducer;
import io.codearte.jfairy.producer.person.AbstractAddressProvider;
import io.codearte.jfairy.producer.person.Address;

import javax.inject.Inject;

/**
 * io.codearte.jfairy.producer.person.locale.zh.ZhAddressProvider
 *
 * @author lhfcws
 * @since 2017/3/2
 */
public class ZhAddressProvider extends AbstractAddressProvider {
	@Inject
	public ZhAddressProvider(DataMaster dataMaster, BaseProducer baseProducer) {
		super(dataMaster, baseProducer);
	}

	@Override
	public Address get() {
		return new ZhAddress(getStreetNumber(), getStreet(), getApartmentNumber(),
			getCity(), getPostalCode());
	}

	public String getApartmentNumber() {
		if (baseProducer.trueOrFalse()) {
			String floor = String.valueOf(baseProducer.randomInt(38));
			String room = "0" + String.valueOf(baseProducer.randomInt(8));
			return floor + room;
		} else
			return "";
	}
}
