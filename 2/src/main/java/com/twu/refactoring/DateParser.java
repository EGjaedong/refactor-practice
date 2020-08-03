package com.twu.refactoring;

import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.TimeZone;

public class DateParser {
    private final String dateAndTimeString;
    private static final HashMap<String, TimeZone> KNOWN_TIME_ZONES = new HashMap<String, TimeZone>();

    static {
        KNOWN_TIME_ZONES.put("UTC", TimeZone.getTimeZone("UTC"));
    }

    /**
     * Takes a date in ISO 8601 format and returns a date
     *
     * @param dateAndTimeString - should be in format ISO 8601 format
     *                          examples -
     *                          2012-06-17 is 17th June 2012 - 00:00 in UTC TimeZone
     *                          2012-06-17TZ is 17th June 2012 - 00:00 in UTC TimeZone
     *                          2012-06-17T15:00Z is 17th June 2012 - 15:00 in UTC TimeZone
     */
    public DateParser(String dateAndTimeString) {
        this.dateAndTimeString = dateAndTimeString;
    }

    public Date parse() {
        int year, month, date, hour, minute;
        year = getYear();
        month = getMonth();
        date = getDate();
        if (dateAndTimeString.charAt(11) == 'Z') {
            hour = 0;
            minute = 0;
        } else {
            hour = getHour();
            minute = getMinute();
        }

        Calendar calendar = Calendar.getInstance();
        calendar.setTimeZone(TimeZone.getTimeZone("UTC"));
        calendar.set(year, month - 1, date, hour, minute, 0);
        calendar.set(Calendar.MILLISECOND, 0);
        return calendar.getTime();
    }

    private int getMinute() {
        return FormatUtil.dateNumberFormat(dateAndTimeString, 14, 16, 0,59, "Minute", 2);
    }

    private int getHour() {
        return FormatUtil.dateNumberFormat(dateAndTimeString, 11, 13, 0,23, "Hour", 2);
    }

    private int getDate() {
        return FormatUtil.dateNumberFormat(dateAndTimeString, 8, 10, 1,31, "Date", 2);
    }

    private int getMonth() {
        return FormatUtil.dateNumberFormat(dateAndTimeString, 5, 7, 1,12, "Month", 2);
    }

    private int getYear() {
        return FormatUtil.dateNumberFormat(dateAndTimeString, 0, 4, 2000,2012, "Year", 4);
    }
}
