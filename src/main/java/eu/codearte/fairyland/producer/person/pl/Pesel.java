package eu.codearte.fairyland.producer.person.pl;

import eu.codearte.fairyland.producer.RandomGenerator;
import eu.codearte.fairyland.producer.person.NationalIdentificationNumber;
import eu.codearte.fairyland.producer.person.Sex;
import org.joda.time.DateTime;

import static java.lang.Integer.valueOf;
import static java.lang.String.format;

/**
 * PESEL - Polish Powszechny Elektroniczny System Ewidencji Ludności,
 * Universal Electronic System for Registration of the Population)
 * <p/>
 * More info: http://en.wikipedia.org/wiki/PESEL
 */
public class Pesel implements NationalIdentificationNumber {

    public static final int[] WEIGHTS = new int[]{1, 3, 7, 9, 1, 3, 7, 9, 1, 3};

    private final RandomGenerator randomGenerator;

    public Pesel(RandomGenerator randomGenerator) {
        this.randomGenerator = randomGenerator;
    }

    @Override
    public String generate(DateTime date, Sex sex) {

        int year = date.getYearOfCentury();
        int month = calculateMonth(date.getMonthOfYear(), date.getYear());
        int day = date.getDayOfMonth();
        int serialNumber = randomGenerator.randomBetween(0, 999);
        int sexCode = calculateSexCode(sex);

        String pesel = format("%02d%02d%02d%03d%d", year, month, day, serialNumber, sexCode);

        return pesel + calculateChecksum(pesel);
    }

    /**
     * @param pesel
     * @return
     */
    public static boolean isValid(String pesel) {
        int size = pesel.length();
        if (size != 11) {
            return false;
        }

        int checksum = valueOf(pesel.substring(size - 1));
        int checkDigit = calculateChecksum(pesel);

        return checkDigit == checksum;

    }

    private int calculateMonth(int month, int year) {
        if (year >= 1800 && year < 1900) {
            month += 80;
        } else if (year >= 2000 && year < 2100) {
            month += 20;
        } else if (year > 2100 && year < 2200) {
            month += 40;
        } else if (year > 2200 && year < 2300) {
            month += 60;
        }
        return month;
    }

    private int calculateSexCode(Sex sex) {
        return randomGenerator.randomBetween(0, 4) * 2 + (sex == Sex.male ? 1 : 0);
    }

    private static int calculateChecksum(String pesel) {
        int sum = 0, checksum;
        int i = 0;
        for (int weight : WEIGHTS) {
            int digit = (int) pesel.charAt(i);
            sum += digit * weight;
        }
        checksum = 10 - (sum % 10);
        return checksum % 10;
    }

}
