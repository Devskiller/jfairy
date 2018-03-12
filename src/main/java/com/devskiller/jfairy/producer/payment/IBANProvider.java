package com.devskiller.jfairy.producer.payment;

import com.google.inject.Provider;

public interface IBANProvider extends Provider<IBAN> {

	IBAN get();

	void fillCountryCode();

	void setNationalCheckDigit(String nationalCheckDigit);

	void setBranchCode(String branchCode);

	void setCountry(String country);

	void setAccountNumber(String accountNumber);

	void setBankCode(String bankCode);
}
