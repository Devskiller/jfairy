package eu.codearte.fairyland.producer.util;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import static com.google.common.base.Preconditions.checkArgument;

public final class TimeUtil {

  private TimeUtil() {
  }

  public static int yearsBetween(Date fromDate, Date toDate) {

    Calendar from = GregorianCalendar.getInstance();
    from.setTime(fromDate);
    Calendar to = Calendar.getInstance();
    to.setTime(toDate);

    checkArgument(from.before(to), "From date is after to date");

    int age = to.get(Calendar.YEAR) - from.get(Calendar.YEAR);
    if (to.get(Calendar.MONTH) < from.get(Calendar.MONTH)) {
      age--;
    } else if (to.get(Calendar.MONTH) == from.get(Calendar.MONTH)
        && to.get(Calendar.DAY_OF_MONTH) < from.get(Calendar.DAY_OF_MONTH)) {
      age--;
    }

    return age;
  }
}
