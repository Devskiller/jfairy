package com.devskiller.jfairy.producer.payment;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * @author jkubrynski@gmail.com
 * @since 2013-11-17
 */
public class CreditCard {

	private final String cardVendor;
	private final String cardNumber;
	private final LocalDateTime expiryDate;

	public CreditCard(String cardVendor, String cardNumber, LocalDateTime expiryDate) {
		this.cardVendor = cardVendor;
		this.cardNumber = cardNumber;
		this.expiryDate = expiryDate;
	}

	public String getVendor() {
		return cardVendor;
	}

	public String getCardNumber() {
		return cardNumber;
	}

	public LocalDateTime getExpiryDate() {
		return expiryDate;
	}

	public String getExpiryDateAsString() {
		return String.format("%02d/%s", expiryDate.getMonthValue(), DateTimeFormatter.ofPattern("uu").format(expiryDate));
	}
}
