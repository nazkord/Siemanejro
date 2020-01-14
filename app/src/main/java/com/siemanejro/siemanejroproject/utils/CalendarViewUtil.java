package com.siemanejro.siemanejroproject.utils;

import com.siemanejro.siemanejroproject.model.Match;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

public class CalendarViewUtil {

    private static final String DATE_FORMAT = "yyyy-MM-dd";
    private static final SimpleDateFormat formatter = new SimpleDateFormat(DATE_FORMAT);
    private static Calendar selectedDate = Calendar.getInstance();
    private static List<Date> datesWithMatches = new ArrayList<>();

    public static void setDatesWithMatches(List<Match> datesWithMatches) {
        CalendarViewUtil.datesWithMatches = datesWithMatches.stream()
                        .map(match -> match.getUtcDate().substring(0,10))
                        .distinct()
                        .map(CalendarViewUtil::convertToDate)
                        .sorted()
                        .collect(Collectors.toList());
    }

    private static Date convertToDate(String s) {
        try {
            return formatter.parse(s);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return Calendar.getInstance().getTime();
    }

    public static void setSelectedDate(Calendar selectedDate) {
        CalendarViewUtil.selectedDate = selectedDate;
    }

    public static boolean notContainsInDatesWithMatches(Calendar date) {
        return !datesWithMatches.contains(date.getTime());
    }

    public static Calendar getNextClosestAvailableDate() {
        int index = datesWithMatches.indexOf(selectedDate.getTime());
        if(index == -1) {
            return Calendar.getInstance();
        } else if(index == (datesWithMatches.size() - 1)) {
            return selectedDate;
        } else {
            return convertToCalendar(datesWithMatches.get(index + 1));
        }
    }

    public static Calendar getPreviousClosestAvailableDate() {
        int index = datesWithMatches.indexOf(selectedDate.getTime());
        if(index == -1) {
            return Calendar.getInstance();
        } else if(index == 0) {
            return selectedDate;
        } else {
            return convertToCalendar(datesWithMatches.get(index - 1));
        }
    }

    private static Calendar convertToCalendar(Date date) {
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        return c;
    }


}
