package io.codearte.jfairy.producer.company.locale.en;

import com.google.common.collect.Sets;
import com.google.inject.Inject;
import io.codearte.jfairy.producer.BaseProducer;
import io.codearte.jfairy.producer.VATIdentificationNumberProvider;

import java.util.Set;

import static java.lang.String.valueOf;
import static java.lang.System.arraycopy;
import static org.apache.commons.lang3.StringUtils.leftPad;

/**
 * @author Olga Maciaszek-Sharma
 * @since 21.03.15
 */
public class EmployerIdentificationNumberProvider implements VATIdentificationNumberProvider {

	private static final int EIN_LENGTH = 10;
	private static final int HYPHEN_INDEX = 2;
	private static final int SERIAL_NUMBER_LENGTH = 7;
	private static final int SERIAL_NUMBER_INDEX = 3;
	private static final int AREA_NUMBER_LENGTH = 2;

	private BaseProducer baseProducer;
	private static Set<Integer> excludedNumbers = Sets.newHashSet(7, 8, 9, 17, 18, 19, 28, 29, 41, 47, 49, 69, 70, 79, 89, 96, 97);

	@Inject
	public EmployerIdentificationNumberProvider(BaseProducer baseProducer) {
		this.baseProducer = baseProducer;
	}

	@Override
	public String get() {
		char[] ein = new char[EIN_LENGTH];

		fillHyphen(ein);

		fillAreaNumber(ein);

		fillSerialNumber(ein);

		return valueOf(ein);
	}

	private void fillSerialNumber(char[] ein) {
		String number = valueOf(baseProducer.randomBetween(1, 9999));
		char[] digits = leftPad(number, SERIAL_NUMBER_LENGTH, "0").toCharArray();
		arraycopy(digits, 0, ein, SERIAL_NUMBER_INDEX, digits.length);
	}


	private void fillAreaNumber(char[] ein) {
		Integer number;
		do {
			number = baseProducer.randomBetween(0, 99);
		} while (excludedNumbers.contains(number));
		char[] digits = leftPad(number.toString(), AREA_NUMBER_LENGTH, "0").toCharArray();
		arraycopy(digits, 0, ein, 0, digits.length);

	}

	private void fillHyphen(char[] ein) {
		ein[HYPHEN_INDEX] = '-';
	}
}
