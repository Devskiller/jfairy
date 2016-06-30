package io.codearte.jfairy.producer.payment;

import org.joda.time.DateTime;

/**
 * @author jkubrynski@gmail.com
 * @since 2013-11-17
 */
public class CreditCard {

	private final String cardVendor;
	private final DateTime expiryDate;

	public CreditCard(String cardVendor, DateTime expiryDate) {
		this.cardVendor = cardVendor;
		this.expiryDate = expiryDate;
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
