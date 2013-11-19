package eu.codearte.fairyland.producer.payment;

import eu.codearte.fairyland.data.DataMaster;
import eu.codearte.fairyland.producer.DateProducer;
import org.joda.time.DateTime;
import org.joda.time.Period;

import javax.inject.Inject;

/**
 * @author jkubrynski@gmail.com
 * @since 2013-11-17
 */
public class CreditCardProducer {

	private static final Period DEFAULT_VALIDITY = Period.months(36);
	private static final String DATA_KEY = "cardVendors";

	private final DataMaster dataMaster;
	private final DateProducer dateProducer;
	private String cardVendor;
	private DateTime expiryDate;

	@Inject
	public CreditCardProducer(DataMaster dataMaster, DateProducer dateProducer) {
		this.dataMaster = dataMaster;
		this.dateProducer = dateProducer;
		generate();
	}

	public final void generate() {
		cardVendor = dataMaster.getRandomValue(DATA_KEY);
		expiryDate = dateProducer.randomDateBetweenNowAndFuturePeriod(DEFAULT_VALIDITY);
	}

	public String vendor() {
		return cardVendor;
	}

	public DateTime expiryDate() {
		return expiryDate;
	}

	public String expiryDateAsString() {
		return String.format("%02d/%02d", expiryDate.getMonthOfYear(), expiryDate.getYearOfCentury());
	}
}
