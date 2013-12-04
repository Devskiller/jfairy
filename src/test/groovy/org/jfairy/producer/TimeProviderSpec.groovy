/*
 * Copyright (c) 2013. Codearte
 */

package org.jfairy.producer

import spock.lang.Specification
import spock.lang.Unroll

class TimeProviderSpec extends Specification {

	@Unroll
	def "should parse date #date"() {
		setup:
		TimeProvider dateProvider = new TimeProvider();
		expect:
		def readDate = dateProvider.getDateForString(date)
		readDate.getYear() == year
		readDate.getMonthOfYear() == month
		readDate.getDayOfMonth() == day
		where:
		date         | year | month | day
		"1900-12-31" | 1900 | 12    | 31
		"2011-12-21" | 2011 | 12    | 21
		"2011-1-1"   | 2011 | 1     | 1
	}
}
