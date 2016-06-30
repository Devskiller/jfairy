package io.codearte.jfairy.producer.payment;

import com.google.inject.Provider;
import io.codearte.jfairy.data.DataMaster;
import io.codearte.jfairy.producer.DateProducer;
import org.joda.time.DateTime;
import org.joda.time.Period;

import javax.inject.Inject;

public class CreditCardProvider implements Provider<CreditCard> {

	private static final Period DEFAULT_VALIDITY = Period.months(36);
	private static final String DATA_KEY = "cardVendors";

	private final DataMaster dataMaster;
	private final DateProducer dateProducer;

	@Inject
	public CreditCardProvider(DataMaster dataMaster, DateProducer dateProducer) {
		this.dataMaster = dataMaster;
		this.dateProducer = dateProducer;
	}

	@Override
	public CreditCard get() {
		String cardVendor = dataMaster.getRandomValue(DATA_KEY);
		DateTime expiryDate = dateProducer.randomDateBetweenNowAndFuturePeriod(DEFAULT_VALIDITY);
		return new CreditCard(cardVendor, expiryDate);
	}

}
