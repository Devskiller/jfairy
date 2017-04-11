package io.codearte.jfairy.producer.person.locale.zh;

import com.google.inject.Inject;
import io.codearte.jfairy.producer.BaseProducer;
import io.codearte.jfairy.producer.DateProducer;
import io.codearte.jfairy.producer.TimeProvider;
import io.codearte.jfairy.producer.person.NationalIdentityCardNumberProvider;
import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

import static io.codearte.jfairy.producer.util.ZhFairyUtil.*;

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
		StringBuilder idBuilder = new StringBuilder();
		idBuilder.append(baseProducer.randomElement(PROV_LIST));
		idBuilder.append(getRandomNumStr(baseProducer, CITY_MAX, 2));
		idBuilder.append(getRandomNumStr(baseProducer, DISTRICT_MAX, 2));
		idBuilder.append(getBirthDate());
		idBuilder.append(getRandomNumStr(baseProducer, ORDER_MAX, 4));
		return idBuilder.toString();
	}

	private String getBirthDate() {
		DateTime birthDate = this.dateProducer.randomDateInThePast(50);
		return formatter.print(birthDate);
	}
}

