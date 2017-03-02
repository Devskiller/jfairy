package io.codearte.jfairy.producer.person.locale.zh;

import com.google.inject.Inject;
import io.codearte.jfairy.producer.BaseProducer;
import io.codearte.jfairy.producer.DateProducer;
import io.codearte.jfairy.producer.TimeProvider;
import io.codearte.jfairy.producer.person.NationalIdentityCardNumberProvider;
import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

import java.lang.management.ManagementFactory;
import java.util.Random;


/**
 * Chinese National Identity Card Number
 *
 * @author Lhfcws
 * @since 27.02.17
 */
public class ZhNationalIdentityCardNumberProvider implements NationalIdentityCardNumberProvider {
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
	private static final int ID_LENGTH = 18;
	/**
	 * Max code of city
	 */
	private static final int CITY_MAX = 30;
	/**
	 * Max code of city district
	 */
	private static final int DISTRICT_MAX = 12;
	/**
	 * The last 4 digit is an order number from 0001 to 9999
	 */
	private static final int ORDER_MAX = 9999;
	private static DateTimeFormatter formatter = DateTimeFormat.forPattern("yyyyMMdd");

	private final BaseProducer baseProducer;
	private final DateProducer dateProducer;

	@Inject
	public ZhNationalIdentityCardNumberProvider(BaseProducer baseProducer) {
		this.baseProducer = baseProducer;
		this.dateProducer = new DateProducer(baseProducer, new TimeProvider());
	}

	@Override
	public String get() {
		StringBuilder sb = new StringBuilder();
		sb.append(baseProducer.randomElement(PROV_LIST));
		sb.append(getRandomNumStr(CITY_MAX, 2));
		sb.append(getRandomNumStr(DISTRICT_MAX, 2));
		sb.append(getBirthDate());
		sb.append(getRandomNumStr(ORDER_MAX, 4));
		return sb.toString();
	}

	private String getRandomNumStr(int max, int padding) {
		int r = baseProducer.randomBetween(1, max);
		String s = "" + r;
		while (s.length() < padding)
			s = "0" + s;
		return s;
	}

	private String getBirthDate() {
		DateTime dateTime = this.dateProducer.randomDateInThePast(50);
		return formatter.print(dateTime);
	}
}

