package eu.codearte.fairyland.producer.person.pl;

import eu.codearte.fairyland.producer.RandomGenerator;
import eu.codearte.fairyland.producer.person.NationalIdentityCardNumber;

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
    public String identityNumber(int year) {

        char[] id = new char[WEIGHTS.length];
        int checksum = 0;
        int index = 0;

        fillPrefix(year, id);

        for (int weight : WEIGHTS) {
            int value = 0;
            if (index < 3) {
                value = id[index] - 'A' + 10;
            } else if (index > 3) {
                char code = randomGenerator.randomBetween('0', '9');
                id[index] = code;
                value = code - '0';
            }
            index++;
            checksum += weight * value;
        }

        id[3] = valueOf(checksum % 10).charAt(0);

        return copyValueOf(id);

    }

    private void fillPrefix(int year, char[] id) {
        int maxPrefix = (year - BEGIN) * PREFIXES_BY_YEAR;
        int range = randomGenerator.randomBetween(maxPrefix, maxPrefix + PREFIXES_BY_YEAR);
        String prefix = AlphaNumberSystem.convertToString(range, 26);
        char[] charArray = leftPad(prefix, 3, 'A').toCharArray();
        arraycopy(charArray, 0, id, 0, charArray.length);
    }

}
