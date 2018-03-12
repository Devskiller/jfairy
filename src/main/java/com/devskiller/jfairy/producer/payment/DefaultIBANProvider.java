package com.devskiller.jfairy.producer.payment;

import javax.inject.Inject;
import java.util.IllegalFormatCodePointException;

import com.google.common.base.Strings;
import com.google.inject.assistedinject.Assisted;
import org.iban4j.CountryCode;
import org.iban4j.Iban;
import org.iban4j.UnsupportedCountryException;

import com.devskiller.jfairy.data.DataMaster;

/**
 * ALPHA: Under development
 */
public class DefaultIBANProvider implements IBANProvider {

	public static final String DEFAULT_COUNTRY_CODE = "defaultCountryCode";
	protected DataMaster dataMaster;
	protected CountryCode countryCode;
	protected String accountNumber;
	protected String bankCode;
	protected String branchCode;
	protected String nationalCheckDigit;

	@Inject
	public DefaultIBANProvider(DataMaster dataMaster,
	                           @Assisted IBANProperties.Property... properties) {

		this.dataMaster = dataMaster;

		for (IBANProperties.Property property : properties) {
			property.apply(this);
		}
	}

	@Override
	public IBAN get() {
		try {

			fillCountryCode();

			try {

				Iban.Builder builder = new Iban.Builder()
					.countryCode(countryCode)
					.bankCode(bankCode)
					.branchCode(branchCode)
					.nationalCheckDigit(nationalCheckDigit);
				if (!Strings.isNullOrEmpty(accountNumber)) {
					builder.accountNumber(accountNumber);
				}
				Iban iban = builder.buildRandom();

				String identificationNumber = iban.getIdentificationNumber();
				String checkDigit = iban.getCheckDigit();
				String accountType = iban.getAccountType();
				String bban = iban.getBban();
				String ownerAccountType = iban.getOwnerAccountType();
				String ibanNumber = iban.toString();

				return new IBAN(accountNumber, identificationNumber, branchCode, checkDigit,
					accountType, bankCode, bban, countryCode.getName(), nationalCheckDigit,
					ownerAccountType, ibanNumber);
			} catch (UnsupportedCountryException e) {
				return null;
			}
		} catch (IllegalFormatCodePointException e) {
			throw new IllegalArgumentException("Invalid iban " + e.getMessage(), e);
		}
	}

	@Override
	public void fillCountryCode() {
		if (countryCode == null) {
			countryCode = CountryCode.valueOf(dataMaster.getString(DEFAULT_COUNTRY_CODE));
		}
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

}
