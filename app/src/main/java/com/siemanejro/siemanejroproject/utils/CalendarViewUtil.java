package com.siemanejro.siemanejroproject.utils;

import java.util.Calendar;

public class CalendarViewUtil {

    private static Calendar selectedDate = Calendar.getInstance();

    public static Calendar getSelectedDate() {
        return selectedDate;
    }

    public static void setSelectedDate(Calendar selectedDate) {
        CalendarViewUtil.selectedDate = selectedDate;
    }

    public static void addOneDayToCurrentDate() {
        selectedDate.add(Calendar.DAY_OF_YEAR, 1);
    }

    public static void substractOneDayFromCurrentDay() {
        selectedDate.add(Calendar.DAY_OF_YEAR, -1);
    }
}
