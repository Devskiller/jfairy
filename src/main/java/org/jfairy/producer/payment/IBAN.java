package org.jfairy.producer.payment;

import org.apache.commons.lang3.StringUtils;
import org.iban4j.CountryCode;
import org.iban4j.Iban;
import org.iban4j.bban.BbanStructure;
import org.iban4j.bban.BbanStructureEntry;
import org.jfairy.producer.BaseProducer;

import javax.inject.Inject;
import java.math.BigInteger;

/**
 * ALPHA: Under development
 */
public class IBAN {

    private final BaseProducer baseProducer;

    private String accountNumber;
    private String identificationNumber;
    private String branchCode;
    private String checkDigit;
    private String accountType;
    private String bankCode;
    private String bban;
    private CountryCode countryCode;
    private String nationalCheckDigit;
    private String ownerAccountType;
    private String ibanNumber;

    @Inject
    public IBAN(BaseProducer baseProducer) {
        this.baseProducer = baseProducer;
        generate();
    }

    private static BbanStructureEntry extractBbanEntry(final CountryCode countryCode, final BbanStructureEntry.EntryType entryType) {

        for (BbanStructureEntry entry : BbanStructure.forCountry(countryCode).getEntries()) {
            if (entry.getEntryType() == entryType) {
                return entry;
            }
        }
        return null;
    }

    public final void generate() {
        try {

            if (countryCode == null) {
                countryCode = CountryCode.PL;
            }

            if (StringUtils.isBlank(accountNumber)) {
                accountNumber = generateRequiredData(BbanStructureEntry.EntryType.c);
            }
            if (StringUtils.isBlank(bankCode)) {
                bankCode = generateRequiredData(BbanStructureEntry.EntryType.b);
            }
            if (StringUtils.isBlank(branchCode)) {
                branchCode = generateRequiredData(BbanStructureEntry.EntryType.s);
            }
            if (StringUtils.isBlank(nationalCheckDigit)) {
                nationalCheckDigit = generateRequiredData(BbanStructureEntry.EntryType.x);
            }

            Iban iban = new Iban.Builder()
                .countryCode(CountryCode.PL)
                .bankCode(bankCode)
                .branchCode(branchCode)
                .nationalCheckDigit(nationalCheckDigit)
                .accountNumber(accountNumber)
                .build();

            identificationNumber = iban.getIdentificationNumber();
            branchCode = iban.getBranchCode();
            checkDigit = iban.getCheckDigit();
            accountType = iban.getAccountType();
            bankCode = iban.getBankCode();
            bban = iban.getBban();
            countryCode = iban.getCountryCode();
            nationalCheckDigit = iban.getNationalCheckDigit();
            ownerAccountType = iban.getOwnerAccountType();
            accountNumber = iban.getAccountNumber();
            ibanNumber = iban.toString();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

    }

    private String generateRequiredData(BbanStructureEntry.EntryType type) {
        String value = "";
        BbanStructureEntry entry = extractBbanEntry(countryCode, type);
        if (entry != null) {
            int length = entry.getLength();
            value = "" + baseProducer.randomBetween(0L, BigInteger.TEN.pow(length).longValue() - 1);
            value = StringUtils.leftPad(value, length, "0");
//            System.out.println(type.toString() + "/len" + length + " -> " + value);
        }
        return value;
    }

    public void setNationalCheckDigit(String nationalCheckDigit) {
        this.nationalCheckDigit = nationalCheckDigit;
        generate();
    }

    public void setBranchCode(String branchCode) {
        this.branchCode = branchCode;
        generate();
    }

    public String getIban() {
        return ibanNumber;
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
        generate();
    }

    public void setBankCode(String bankCode) {
        this.bankCode = bankCode;
        generate();
    }

    public void setCountryCode(String countryCode) {
        this.countryCode = CountryCode.valueOf(countryCode);
        generate();
    }

    public String iban() {
        return accountNumber;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public String getIdentificationNumber() {
        return identificationNumber;
    }

    public String getBranchCode() {
        return branchCode;
    }

    public String getCheckDigit() {
        return checkDigit;
    }

    public String getAccountType() {
        return accountType;
    }

    public String getBankCode() {
        return bankCode;
    }

    public String getBban() {
        return bban;
    }

    public String getCountryCode() {
        return countryCode.getName();
    }

    public String getNationalCheckDigit() {
        return nationalCheckDigit;
    }

    public String getOwnerAccountType() {
        return ownerAccountType;
    }
}
