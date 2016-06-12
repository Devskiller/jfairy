package io.codearte.jfairy.producer.person.locale.sv;

import com.google.inject.Inject;
import com.google.inject.assistedinject.Assisted;
import io.codearte.jfairy.producer.BaseProducer;
import io.codearte.jfairy.producer.DateProducer;
import io.codearte.jfairy.producer.VATIdentificationNumberProvider;
import io.codearte.jfairy.producer.person.NationalIdentityCardNumberProvider;
import io.codearte.jfairy.producer.person.Person;
import org.joda.time.DateTime;

import static java.lang.String.format;

/**
 * Personal Identity Number (known as Personnummer in sweden)
 * https://en.wikipedia.org/wiki/Personal_identity_number_(Sweden)
 * <p>
 * E.g.
 * 870508-5853
 * <p>
 * The left part of the hyphen consists of the date of birth and the right part consist of
 * three random numbers + one check digit.
 */
public class PersonalIdentityNumberProvider implements NationalIdentityCardNumberProvider, VATIdentificationNumberProvider {

    private static final int PERSONAL_IDENTITY_NUMBER_LENGTH = 10;
    private static final int VALIDITY_IN_YEARS = 10;


    private static final int[] WEIGHTS = {2, 1, 2, 1, 2, 1, 2, 1, 2};
    private static final int MAX_SERIAL_NUMBER = 99;
    private static final int TEN = 10;

    private static final int[] SEX_FIELDS = {0, 2, 4, 6, 8};

    private final BaseProducer baseProducer;
    private final DateProducer dateProducer;
    private DateTime issueDate;
    private Person.Sex sex;

    @Inject
    public PersonalIdentityNumberProvider(DateProducer dateProducer, BaseProducer baseProducer,
                                          @Assisted PersonalIdentityNumberProperties.Property... properties) {
        this.dateProducer = dateProducer;
        this.baseProducer = baseProducer;

        with(properties);
    }

    public void with(PersonalIdentityNumberProperties.Property[] properties) {
        for (PersonalIdentityNumberProperties.Property property : properties) {
            property.apply(this);
        }
    }

    @Override
    public String get() {

        if (issueDate == null) {
            issueDate = dateProducer.randomDateInThePast(VALIDITY_IN_YEARS);
        }
        if (sex == null) {
            sex = baseProducer.trueOrFalse() ? Person.Sex.MALE : Person.Sex.FEMALE;
        }

        return generate();
    }

    private String generate() {
        int year = issueDate.getYearOfCentury();
        int month = issueDate.getMonthOfYear();
        int day = issueDate.getDayOfMonth();
        int serialNumber = baseProducer.randomInt(MAX_SERIAL_NUMBER);
        int sexCode = calculateSexCode(sex);

        String personalIdentityNumber = format("%02d%02d%02-d%02d%d", year, month, day, serialNumber, sexCode);

        return personalIdentityNumber + calculateChecksum(personalIdentityNumber);
    }

    public void setIssueDate(DateTime issueDate) {
        this.issueDate = issueDate;
    }

    public void setSex(Person.Sex sex) {
        this.sex = sex;
    }

    /**
     * @param personalIdentityNumber Personal Identity Number
     * @return personalIdentityNumber validity
     */
    public static boolean isValid(String personalIdentityNumber) {
        int size = personalIdentityNumber.length();
        if (size != PERSONAL_IDENTITY_NUMBER_LENGTH) {
            return false;
        }

        int checksum = Integer.valueOf(personalIdentityNumber.substring(size - 1));
        int checkDigit = calculateChecksum(personalIdentityNumber);

        return checkDigit == checksum;

    }

    private int calculateSexCode(Person.Sex sex) {
        return SEX_FIELDS[baseProducer.randomInt(SEX_FIELDS.length - 1)] + (sex == Person.Sex.MALE ? 1 : 0);
    }

    private static int calculateChecksum(String personalIdentityNumber) {
        String personalIdentityNumberWithoutHyphen = personalIdentityNumber.replace("-", "");
        int sum = 0;
        int i = 0;
        for (int weight : WEIGHTS) {
            int digit = Character.digit(personalIdentityNumberWithoutHyphen.charAt(i++), 10);
            int product = digit * weight;
            sum += product / 10 + product % 10;
        }

        int checksum = 10 - (sum % 10);

        if (0 == checksum) {
            return 0;
        }

        return TEN - checksum;
    }

}

