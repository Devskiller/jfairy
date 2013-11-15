/*
 * Copyright (c) 2013. Codearte
 */

package eu.codearte.fairyland.producer.util;

import com.google.common.annotations.VisibleForTesting;
import com.google.common.base.Preconditions;
import org.joda.time.DateTime;

import javax.inject.Inject;
import javax.inject.Singleton;

@Singleton
public class DateGenerator {

	@VisibleForTesting
	static final int SECONDS_BEFORE_TO_BE_IN_THE_PAST = 1;

	private final RandomGenerator randomGenerator;
	private final TimeProvider timeProvider;

	@Inject
	public DateGenerator(RandomGenerator randomGenerator, TimeProvider timeProvider) {
		this.randomGenerator = randomGenerator;
		this.timeProvider = timeProvider;
	}

	public DateTime randomDateInThePast(int maxYearsEarlier) {
		Preconditions.checkArgument(maxYearsEarlier >= 0, "%s has to be >= 0", maxYearsEarlier);
		DateTime currentDate = timeProvider.getCurrentDate();
		DateTime latestDateInThePast = currentDate.minusSeconds(SECONDS_BEFORE_TO_BE_IN_THE_PAST);
		DateTime maxYearsEarlierDate = currentDate.minusYears(maxYearsEarlier);
		return randomDateBetweenTwoDates(maxYearsEarlierDate, latestDateInThePast);
	}

	private DateTime randomDateBetweenTwoDates(DateTime from, DateTime to) {
		return new DateTime(randomGenerator.randomBetween(from.getMillis(), to.getMillis()));
	}

	public DateTime randomDateBetweenYears(int from, int to) {
		Preconditions.checkArgument(from <= to, "%s has to be <= %s", from, to);
		DateTime fromDate = getDateForFirstDayForGivenYear(from);
		DateTime toDate = getDateForLastDayForGivenYear(to);
		return randomDateBetweenTwoDates(fromDate, toDate);
	}

	private DateTime getDateForLastDayForGivenYear(int year) {
		return new DateTime(getDateForFirstDayForGivenYear(year + 1).getMillis() - 1);
	}

	private DateTime getDateForFirstDayForGivenYear(int year) {
		return new DateTime(year, 1, 1, 0, 0);
	}
}
