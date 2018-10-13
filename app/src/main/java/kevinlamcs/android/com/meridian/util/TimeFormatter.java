package kevinlamcs.android.com.meridian.util;


import org.joda.time.DateTime;
import org.joda.time.Period;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class TimeFormatter {

    public static final String TIME_PATTERN = AppConstants.TIME_PATTERN;

    public String elapsed(String since) {
        long sinceInMillis = time(since);
        long nowInMillis = now();
        return elapsed(sinceInMillis, nowInMillis);
    }

    public String elapsed(long sinceInMillis, long nowInMillis) {
        Period period = new Period(sinceInMillis, nowInMillis);
        if (moreThanOneDay(period)) {
            DateTime dateTime = new DateTime(sinceInMillis);
            return String.format(AppConstants.DATE_FORMAT, dateTime.getMonthOfYear(), dateTime.getDayOfMonth());
        } else if (moreThanOneHour(period)) {
            return String.format(AppConstants.HOUR_FORMAT, period.getHours());
        } else {
            return String.format(AppConstants.MINUTE_FORMAT, period.getMinutes());
        }
    }

    private boolean moreThanOneDay(Period period) {
        return period.getYears() > 0 || period.getWeeks() > 0 || period.getMonths() > 0 || period.getDays() > 0 || period.getHours() == 24;
    }

    private boolean moreThanOneHour(Period period) {
        return period.getHours() > 0 || period.getMinutes() == 60;
    }

    public long now() {
        return System.currentTimeMillis();
    }

    public long time(String since) {
        SimpleDateFormat dateFormat = new SimpleDateFormat(TIME_PATTERN);
        Date date = new Date();

        try {
            date = dateFormat.parse(since);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return date.getTime();
    }
}
