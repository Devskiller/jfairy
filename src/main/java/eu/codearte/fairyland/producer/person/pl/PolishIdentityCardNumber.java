package eu.codearte.fairyland.producer.person.pl;

import eu.codearte.fairyland.producer.RandomGenerator;
import eu.codearte.fairyland.producer.person.NationalIdentityCardNumber;

import static java.lang.String.format;

/**
 * Polish Identity Card Number
 */
public class PolishIdentityCardNumber implements NationalIdentityCardNumber {

    public static final int[] WEIGHTS = new int[]{7, 3, 1, 0, 7, 3, 1, 7, 3};

    private final RandomGenerator randomGenerator;

    public PolishIdentityCardNumber(RandomGenerator randomGenerator) {
        this.randomGenerator = randomGenerator;
    }

    /**
     * @return identityNumber
     */
    public String identityNumber() {

        int digits = randomGenerator.randomBetween(0, 99999);

        String identification = format("%c%c%c%c%05d", randomLetter(), randomLetter(), randomLetter(), '?', digits);
        int checksum = 0;
        int index = 0;
        for (int weight : WEIGHTS) {
            int value;
            if (index < 3) {
                value = identification.charAt(index) - 'A' + 10;
            } else {
                value = identification.charAt(index) - '0';
            }
            index++;
            checksum += weight * value;
        }
        return identification.replace('?', digit(checksum % 10));

    }

    private char digit(int digit) {
        return (char) (digit + '0');
    }

    private char randomLetter() {
        return randomGenerator.randomBetween('A', 'Z');
    }
}
