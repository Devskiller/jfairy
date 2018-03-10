package io.codearte.jfairy.producer.payment;

import java.time.LocalDateTime;

/**
 * @author jkubrynski@gmail.com
 * @since 2013-11-17
 */
public class CreditCard {

	private final String cardVendor;
	private final LocalDateTime expiryDate;

	public CreditCard(String cardVendor, LocalDateTime expiryDate) {
		this.cardVendor = cardVendor;
		this.expiryDate = expiryDate;
	}

	public String getVendor() {
		return cardVendor;
	}

	public LocalDateTime getExpiryDate() {
		return expiryDate;
	}

	public String getExpiryDateAsString() {
		return String.format("%02d/%02d", expiryDate.getMonthValue(), expiryDate.getYear());
	}
}
