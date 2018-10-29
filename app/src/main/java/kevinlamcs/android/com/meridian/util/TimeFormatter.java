package kevinlamcs.android.com.meridian.util;


import android.os.Build;

import org.joda.time.DateTime;
import org.joda.time.Period;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import static kevinlamcs.android.com.meridian.util.AppConstants.TIME_PATTERN_ISO;
import static kevinlamcs.android.com.meridian.util.AppConstants.TIME_PATTERN_RFC;

public class TimeFormatter {

    public static final String TIME_PATTERN = TIME_PATTERN_ISO;

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
        String pattern;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            pattern = TIME_PATTERN_ISO;
        } else {
            pattern = TIME_PATTERN_RFC;
            since = TextUtil.removeLast(":", since);
        }
        SimpleDateFormat dateFormat = new SimpleDateFormat(pattern);
        Date date = new Date();

        try {
            date = dateFormat.parse(since);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return date.getTime();
    }
}
