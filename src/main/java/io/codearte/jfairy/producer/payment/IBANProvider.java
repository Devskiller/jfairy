package io.codearte.jfairy.producer.payment;

import com.google.inject.Provider;
import com.google.inject.assistedinject.Assisted;
import io.codearte.jfairy.producer.BaseProducer;
import org.apache.commons.lang3.StringUtils;
import org.iban4j.CountryCode;
import org.iban4j.Iban;
import org.iban4j.bban.BbanStructure;
import org.iban4j.bban.BbanStructureEntry;

import javax.inject.Inject;
import java.math.BigInteger;
import java.util.IllegalFormatCodePointException;

/**
 * ALPHA: Under development
 */
public class IBANProvider implements Provider<IBAN> {

	private final BaseProducer baseProducer;
	private CountryCode countryCode;
	private String accountNumber;
	private String bankCode;
	private String branchCode;
	private String nationalCheckDigit;

	@Inject
	public IBANProvider(BaseProducer baseProducer,

	                    @Assisted
	                    IBANProperties.Property... properties) {

		this.baseProducer = baseProducer;

		for (IBANProperties.Property property : properties) {
			property.apply(this);
		}
	}

	@Override
	public IBAN get() {
		try {

			fillCountryCode();
			fillAccountNumber();
			fillBankCode();
			fillBranchCode();
			nationalCheckDigit = nationalCheckDigit(nationalCheckDigit);

			Iban iban = new Iban.Builder()
					.countryCode(countryCode)
					.bankCode(bankCode)
					.branchCode(branchCode)
					.nationalCheckDigit(nationalCheckDigit)
					.accountNumber(accountNumber)
					.build();

			String identificationNumber = iban.getIdentificationNumber();
			String checkDigit = iban.getCheckDigit();
			String accountType = iban.getAccountType();
			String bban = iban.getBban();
			String ownerAccountType = iban.getOwnerAccountType();
			String ibanNumber = iban.toString();

			return new IBAN(accountNumber, identificationNumber, branchCode, checkDigit,
					accountType, bankCode, bban, countryCode.getName(), nationalCheckDigit,
					ownerAccountType, ibanNumber);
		} catch (IllegalFormatCodePointException e) {
			throw new IllegalArgumentException("Invalid iban " + e.getMessage(), e);
		}
	}

	private String nationalCheckDigit(String value) {
		if (StringUtils.isBlank(value)) {
			return generateRequiredData(BbanStructureEntry.EntryType.x);
		}
		return value;
	}

	private void fillNationalCheckDigit() {
		if (StringUtils.isBlank(nationalCheckDigit)) {
			nationalCheckDigit = generateRequiredData(BbanStructureEntry.EntryType.x);
		}
	}

	private void fillBranchCode() {
		if (StringUtils.isBlank(branchCode)) {
			branchCode = generateRequiredData(BbanStructureEntry.EntryType.s);
		}
	}

	private void fillBankCode() {
		if (StringUtils.isBlank(bankCode)) {
			bankCode = generateRequiredData(BbanStructureEntry.EntryType.b);
		}
	}

	private void fillAccountNumber() {
		if (StringUtils.isBlank(accountNumber)) {
			accountNumber = generateRequiredData(BbanStructureEntry.EntryType.c);
		}
	}

	private void fillCountryCode() {
		if (countryCode == null) {
			countryCode = CountryCode.PL;
		}
	}

	private static BbanStructureEntry extractBbanEntry(final CountryCode countryCode, final BbanStructureEntry.EntryType entryType) {

		for (BbanStructureEntry entry : BbanStructure.forCountry(countryCode).getEntries()) {
			if (entry.getEntryType() == entryType) {
				return entry;
			}
		}
		return null;
	}

	private String generateRequiredData(BbanStructureEntry.EntryType type) {
		String value = "";
		BbanStructureEntry entry = extractBbanEntry(countryCode, type);
		if (entry != null) {
			int length = entry.getLength();
			value = "" + baseProducer.randomBetween(0L, BigInteger.TEN.pow(length).longValue() - 1);
			value = StringUtils.leftPad(value, length, "0");
		}
		return value;
	}

	public void setNationalCheckDigit(String nationalCheckDigit) {
		this.nationalCheckDigit = nationalCheckDigit;
	}

	public void setBranchCode(String branchCode) {
		this.branchCode = branchCode;
	}

	public void setCountry(String country) {
		this.countryCode = CountryCode.valueOf(country);
	}

	public void setAccountNumber(String accountNumber) {
		this.accountNumber = accountNumber;
	}

	public void setBankCode(String bankCode) {
		this.bankCode = bankCode;
	}
}
