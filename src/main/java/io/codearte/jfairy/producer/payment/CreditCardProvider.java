package io.codearte.jfairy.producer.payment;

import javax.inject.Inject;
import java.time.LocalDateTime;
import java.time.Period;

import com.google.inject.Provider;
import io.codearte.jfairy.data.DataMaster;
import io.codearte.jfairy.producer.DateProducer;

public class CreditCardProvider implements Provider<CreditCard> {

	private static final Period DEFAULT_VALIDITY = Period.ofMonths(36);
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
		LocalDateTime expiryDate = dateProducer.randomDateBetweenNowAndFuturePeriod(DEFAULT_VALIDITY);
		return new CreditCard(cardVendor, expiryDate);
	}

}
