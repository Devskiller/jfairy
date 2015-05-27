/*
 * Copyright (c) 2013. Codearte
 */

package io.codearte.jfairy.producer;

import com.google.common.annotations.VisibleForTesting;
import org.joda.time.DateTime;
import org.joda.time.Period;

import javax.inject.Inject;
import javax.inject.Singleton;

import static com.google.common.base.Preconditions.checkArgument;

@Singleton
public class DateProducer {

	@VisibleForTesting
	static final int SECONDS_BEFORE_TO_BE_IN_THE_PAST = 1;

	private final BaseProducer baseProducer;
	private final TimeProvider timeProvider;

	@Inject
	public DateProducer(BaseProducer baseProducer, TimeProvider timeProvider) {
		this.baseProducer = baseProducer;
		this.timeProvider = timeProvider;
	}

	public DateTime randomDateInThePast(int maxYearsEarlier) {
		checkArgument(maxYearsEarlier >= 0, "%s has to be >= 0", maxYearsEarlier);
		DateTime currentDate = timeProvider.getCurrentDate();
		DateTime latestDateInThePast = currentDate.minusSeconds(SECONDS_BEFORE_TO_BE_IN_THE_PAST);
		DateTime maxYearsEarlierDate = currentDate.minusYears(maxYearsEarlier);
		return randomDateBetweenTwoDates(maxYearsEarlierDate, latestDateInThePast);
	}

	public DateTime randomDateBetweenYearAndNow(int fromYear) {
		int actualYear = timeProvider.getCurrentYear();
		return randomDateInThePast(actualYear - fromYear);
	}

	public DateTime randomDateBetweenTwoDates(DateTime from, DateTime to) {
		return new DateTime(baseProducer.randomBetween(from.getMillis(), to.getMillis()));
	}

	public DateTime randomDateBetweenYears(int fromYear, int toYear) {
		checkArgument(fromYear <= toYear, "%s has to be <= %s", fromYear, toYear);
		DateTime fromDate = getDateForFirstDayForGivenYear(fromYear);
		DateTime toDate = getDateForLastDayForGivenYear(toYear);
		return randomDateBetweenTwoDates(fromDate, toDate);
	}

	private DateTime getDateForLastDayForGivenYear(int year) {
		return new DateTime(getDateForFirstDayForGivenYear(year + 1).getMillis() - 1);
	}

	private DateTime getDateForFirstDayForGivenYear(int year) {
		return new DateTime(year, 1, 1, 0, 0);
	}

	public DateTime randomDateBetweenNowAndFuturePeriod(Period futurePeriod) {
		DateTime now = timeProvider.getCurrentDate();
		return new DateTime(baseProducer.randomBetween(now.getMillis(), now.plus(futurePeriod).getMillis()));
	}

	public DateTime randomDateInTheFuture(int years) {
		return randomDateBetweenNowAndFuturePeriod(Period.years(years));
	}

	public DateTime randomDateInTheFuture() {
		return randomDateInTheFuture(100);
	}
}
