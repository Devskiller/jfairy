package eu.codearte.fairyland.producer.person.pl;

import eu.codearte.fairyland.producer.RandomGenerator;
import eu.codearte.fairyland.producer.person.NationalIdentityCardNumber;

import static java.lang.String.copyValueOf;
import static java.lang.String.valueOf;

/**
 * Polish Identity Card Number
 */
public class PolishIdentityCardNumber implements NationalIdentityCardNumber {

    private static final int[] WEIGHTS = new int[]{7, 3, 1, 0, 7, 3, 1, 7, 3};

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

        for (int weight : WEIGHTS) {
            int value = 0;
            char code = ' ';
            if (index < 3) {
                if (index == 0 && year < 2015) {
                    code = 'A';
                } else if (index == 0 && year < 2025) {
                    code = 'B';
                } else {
                    code = randomGenerator.randomBetween('A', 'Z');
                }
                value = code - 'A' + 10;

            } else if (index > 3) {
                code = randomGenerator.randomBetween('0', '9');
                value = code - '0';
            }
            id[index++] = code;
            checksum += weight * value;
        }

        id[3] = valueOf(checksum % 10).charAt(0);

        return copyValueOf(id);

    }

}
