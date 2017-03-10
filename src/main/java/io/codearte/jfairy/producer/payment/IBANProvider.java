package io.codearte.jfairy.producer.payment;

import com.google.inject.Provider;
import org.iban4j.bban.BbanStructureEntry;


/**
 * ALPHA: Under development
 */
public interface IBANProvider extends Provider<IBAN> {

	IBAN get();

	String nationalCheckDigit(String value);

	void fillNationalCheckDigit();

	void fillBranchCode();

	void fillBankCode();

	void fillAccountNumber();

	void fillCountryCode();

	String generateRequiredData(BbanStructureEntry.EntryType type);

	void setNationalCheckDigit(String nationalCheckDigit);

	void setBranchCode(String branchCode);

	void setCountry(String country);

	void setAccountNumber(String accountNumber);

	void setBankCode(String bankCode);
}
