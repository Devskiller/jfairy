package io.codearte.jfairy.producer.payment;

import com.google.inject.assistedinject.Assisted;
import io.codearte.jfairy.producer.BaseProducer;
import org.apache.commons.lang3.StringUtils;
import org.iban4j.CountryCode;
import org.iban4j.Iban;
import org.iban4j.bban.BbanEntryType;
import org.iban4j.bban.BbanStructure;
import org.iban4j.bban.BbanStructureEntry;

import javax.inject.Inject;
import java.math.BigInteger;
import java.util.IllegalFormatCodePointException;

/**
 * ALPHA: Under development
 */
public class DefaultIBANProvider implements IBANProvider {

	protected BaseProducer baseProducer;
	protected CountryCode countryCode;
	protected String accountNumber;
	protected String bankCode;
	protected String branchCode;
	protected String nationalCheckDigit;

	@Inject
	public DefaultIBANProvider(BaseProducer baseProducer,

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

	@Override
	public String nationalCheckDigit(String value) {
		if (StringUtils.isBlank(value)) {
			return generateRequiredData(BbanEntryType.national_check_digit);
		}
		return value;
	}

	@Override
	public void fillNationalCheckDigit() {
		if (StringUtils.isBlank(nationalCheckDigit)) {
			nationalCheckDigit = generateRequiredData(BbanEntryType.national_check_digit);
		}
	}

	@Override
	public void fillBranchCode() {
		if (StringUtils.isBlank(branchCode)) {
			branchCode = generateRequiredData(BbanEntryType.branch_code);
		}
	}

	@Override
	public void fillBankCode() {
		if (StringUtils.isBlank(bankCode)) {
			bankCode = generateRequiredData(BbanEntryType.bank_code);
		}
	}

	@Override
	public void fillAccountNumber() {
		if (StringUtils.isBlank(accountNumber)) {
			accountNumber = generateRequiredData(BbanEntryType.account_number);
		}
	}

	@Override
	public void fillCountryCode() {
		if (countryCode == null) {
			countryCode = CountryCode.PL;
		}
	}

	@Override
	public String generateRequiredData(BbanEntryType type) {
		String value = "";
		BbanStructureEntry entry = extractBbanEntry(countryCode, type);
		if (entry != null) {
			int length = entry.getLength();
			value = "" + baseProducer.randomBetween(0L, BigInteger.TEN.pow(length).longValue() - 1);
			value = StringUtils.leftPad(value, length, "0");
		}
		return value;
	}

	@Override
	public void setNationalCheckDigit(String nationalCheckDigit) {
		this.nationalCheckDigit = nationalCheckDigit;
	}

	@Override
	public void setBranchCode(String branchCode) {
		this.branchCode = branchCode;
	}

	@Override
	public void setCountry(String country) {
		this.countryCode = CountryCode.valueOf(country);
	}

	@Override
	public void setAccountNumber(String accountNumber) {
		this.accountNumber = accountNumber;
	}

	@Override
	public void setBankCode(String bankCode) {
		this.bankCode = bankCode;
	}

	protected static BbanStructureEntry extractBbanEntry(final CountryCode countryCode, final BbanEntryType entryType) {

		for (BbanStructureEntry entry : BbanStructure.forCountry(countryCode).getEntries()) {
			if (entry.getEntryType() == entryType) {
				return entry;
			}
		}
		return null;
	}
}
