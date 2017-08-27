package io.codearte.jfairy.producer.company.locale.zh;

import io.codearte.jfairy.producer.BaseProducer;
import io.codearte.jfairy.producer.RandomGenerator;
import io.codearte.jfairy.producer.VATIdentificationNumberProvider;
import org.apache.commons.math3.random.RandomDataGenerator;

import java.util.Random;

import static io.codearte.jfairy.producer.util.ZhFairyUtil.*;

/**
 * io.codearte.jfairy.producer.company.locale.zh.ZhVATIdentificationNumberProvider
 *
 * 6 digit for area location, 9 char (0-9A-Z) for organization code (2 char for business type, 2 char for industry type, 5 char for order number), total 15 digit
 *
 * @author lhfcws
 * @since 2017/3/1
 */
public class ZhVATIdentificationNumberProvider implements VATIdentificationNumberProvider {

	private static BaseProducer baseProducer = new BaseProducer(new RandomGenerator());

	@Override
	public String get() {
		StringBuilder vatBuilder = new StringBuilder();
		vatBuilder.append(baseProducer.randomElement(PROV_LIST));
		vatBuilder.append(getRandomNumStr(baseProducer, CITY_MAX, 2));
		vatBuilder.append(getRandomNumStr(baseProducer, DISTRICT_MAX, 2));
		vatBuilder.append(getChars(9));
		return vatBuilder.toString();
	}

	private char getChar() {
		int rndNum = baseProducer.randomBetween(0, 35);
		if (rndNum < 10) {
			return (char) (49 + rndNum);
		} else {
			return (char) (65 + rndNum - 10);
		}
	}

	private String getChars(int paddingSize) {
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < paddingSize; i++) {
			sb.append(getChar());
		}
		return sb.toString();
	}
}
