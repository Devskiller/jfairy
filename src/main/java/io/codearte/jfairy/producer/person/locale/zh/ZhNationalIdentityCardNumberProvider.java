package io.codearte.jfairy.producer.person.locale.zh;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import com.google.inject.Inject;
import io.codearte.jfairy.producer.BaseProducer;
import io.codearte.jfairy.producer.DateProducer;
import io.codearte.jfairy.producer.TimeProvider;
import io.codearte.jfairy.producer.person.NationalIdentityCardNumberProvider;

import static io.codearte.jfairy.producer.util.ZhFairyUtil.CITY_MAX;
import static io.codearte.jfairy.producer.util.ZhFairyUtil.DISTRICT_MAX;
import static io.codearte.jfairy.producer.util.ZhFairyUtil.PROV_LIST;
import static io.codearte.jfairy.producer.util.ZhFairyUtil.getRandomNumStr;

/**
 * Chinese National Identity Card Number, total 18 digits
 *
 * @author Lhfcws
 * @since 27.02.17
 */
public class ZhNationalIdentityCardNumberProvider implements NationalIdentityCardNumberProvider {

	/**
	 * The last 4 digit is an order number from 0001 to 9999
	 */
	private static final int ORDER_MAX = 9999;
	private static DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMdd");

	private final BaseProducer baseProducer;
	private final DateProducer dateProducer;

	@Inject
	public ZhNationalIdentityCardNumberProvider(BaseProducer baseProducer) {
		this.baseProducer = baseProducer;
		this.dateProducer = new DateProducer(baseProducer, new TimeProvider());
	}

	@Override
	public String get() {
		StringBuilder idBuilder = new StringBuilder();
		idBuilder.append(baseProducer.randomElement(PROV_LIST));
		idBuilder.append(getRandomNumStr(baseProducer, CITY_MAX, 2));
		idBuilder.append(getRandomNumStr(baseProducer, DISTRICT_MAX, 2));
		idBuilder.append(getBirthDate());
		idBuilder.append(getRandomNumStr(baseProducer, ORDER_MAX, 4));
		return idBuilder.toString();
	}

	private String getBirthDate() {
		LocalDateTime birthDate = this.dateProducer.randomDateInThePast(50);
		return formatter.format(birthDate);
	}
}

