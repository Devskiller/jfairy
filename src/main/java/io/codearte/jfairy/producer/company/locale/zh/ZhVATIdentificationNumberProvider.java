package io.codearte.jfairy.producer.company.locale.zh;

import io.codearte.jfairy.producer.BaseProducer;
import io.codearte.jfairy.producer.VATIdentificationNumberProvider;

import java.util.Random;

/**
 * io.codearte.jfairy.producer.company.locale.zh.ZhVATIdentificationNumberProvider
 *
 * 6 digit for area location, 9 char (0-9A-Z) for organization code (2 char for business type, 2 char for industry type, 5 char for order number), total 15 digit
 *
 * @author lhfcws
 * @since 2017/3/1
 */
public class ZhVATIdentificationNumberProvider implements VATIdentificationNumberProvider {

	private static BaseProducer baseProducer = new BaseProducer(new Random());

	/**
	 * Codes of China provinces
	 */
	private static final String[] PROV_LIST = {
		"11", 	// Beijing
		"12",	// Tianjin
		"13",	// Hebei
		"14",	// Shanxi
		"15",	// Neimenggu
		"21",	// Liaoning
		"22",	// Jilin
		"23",	// Heilongjiang
		"31",	// Shanghai
		"32",	// Jiangsu
		"33",	// Zhejiang
		"34",	// Anhui
		"35",	// Fujian
		"36",	// Jiangxi
		"41",	// Henan
		"42",	// Hubei
		"43",	// Hunan
		"44",	// Guangdong
		"45",	// Guangxi
		"46",	// Hainan
		"50",	// Chongqing
		"51",	// Sichuan
		"52",	// Guizhou
		"53",	// Yunnan
		"54",	// Xizang
		"61",	// Shanxi
		"62",	// Gansu
		"63",	// Qinghai
		"64",	// Ningxia
		"65",	// Xinjiang
		"71",	// Taiwan
		"81",	// Hong Kong
		"82",	// Macau
	};

	/**
	 * Total length of Chinese ID
	 */
	private static final int ID_LENGTH = 15;
	/**
	 * Max code of city
	 */
	private static final int CITY_MAX = 30;
	/**
	 * Max code of city district
	 */
	private static final int DISTRICT_MAX = 12;

	@Override
	public String get() {
		StringBuilder sb = new StringBuilder();
		sb.append(baseProducer.randomElement(PROV_LIST));
		sb.append(getRandomNumStr(CITY_MAX, 2));
		sb.append(getRandomNumStr(DISTRICT_MAX, 2));
		sb.append(getChars(9));
		return sb.toString();
	}

	private char getChar() {
		int r = baseProducer.randomBetween(0, 35);
		if (r < 10)
			return (char)(49 + r);
		else
			return (char)(65 + r - 10);
	}

	private String getChars(int padding) {
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < padding; i++) {
			sb.append(getChar());
		}
		return sb.toString();
	}

	private String getRandomNumStr(int max, int padding) {
		int r = baseProducer.randomBetween(1, max);
		String s = "" + r;
		while (s.length() < padding)
			s = "0" + s;
		return s;
	}
}
