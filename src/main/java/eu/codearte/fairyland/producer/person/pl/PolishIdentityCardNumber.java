package eu.codearte.fairyland.producer.person.pl;

import eu.codearte.fairyland.producer.RandomGenerator;
import eu.codearte.fairyland.producer.person.NationalIdentityCardNumber;

import java.util.Calendar;
import java.util.GregorianCalendar;

import static com.google.common.base.Preconditions.checkArgument;
import static eu.codearte.fairyland.producer.person.pl.AlphaNumberSystem.convertToString;
import static java.lang.String.copyValueOf;
import static java.lang.String.valueOf;
import static java.lang.System.arraycopy;
import static org.apache.commons.lang3.StringUtils.leftPad;

/**
 * Polish Identity Card Number
 */
public class PolishIdentityCardNumber implements NationalIdentityCardNumber {

    private static final int[] WEIGHTS = new int[]{7, 3, 1, 0, 7, 3, 1, 7, 3};
    public static final int BEGIN = 2000;
    public static final int PREFIXES_BY_YEAR = 45;

    private final RandomGenerator randomGenerator;

    public PolishIdentityCardNumber(RandomGenerator randomGenerator) {
        this.randomGenerator = randomGenerator;
    }

    /**
     * @return identityNumber
     */
    public String identityNumber(GregorianCalendar calendar) {

        checkArgument(calendar.after(new GregorianCalendar(2000, 0, 1)));

        char[] id = new char[WEIGHTS.length];

        fillAlphaPrefix(calendar.get(Calendar.YEAR), id);
        fillDigits(id);

        char checksum = calculateChecksum(id);

        id[3] = checksum;

        return copyValueOf(id);

    }

    public boolean isValid(String id) {
        int checksum = calculateChecksum(id.toCharArray());

        return id.charAt(3) == checksum;
    }

    private char calculateChecksum(char[] id) {
        int index = 0;
        int checksum = 0;

        for (int weight : WEIGHTS) {
            int value = 0;
            if (index < 3) {
                value = id[index] - 'A' + 10;
            } else if (index > 3) {
                value = id[index] - '0';
            }
            index++;
            checksum += weight * value;
        }

        return valueOf(checksum % 10).charAt(0);
    }

    private void fillDigits(char[] id) {
        String num = valueOf(randomGenerator.randomBetween(0, 99999));
        char[] digits = leftPad(num, 5, '0').toCharArray();
        arraycopy(digits, 0, id, 4, digits.length);
    }

    private void fillAlphaPrefix(int year, char[] id) {
        int maxPrefix = (year - BEGIN) * PREFIXES_BY_YEAR;
        int range = randomGenerator.randomBetween(maxPrefix, maxPrefix + PREFIXES_BY_YEAR);
        String prefix = convertToString(range, 26);
        char[] charArray = leftPad(prefix, 3, 'A').toCharArray();
        arraycopy(charArray, 0, id, 0, charArray.length);
    }

}
