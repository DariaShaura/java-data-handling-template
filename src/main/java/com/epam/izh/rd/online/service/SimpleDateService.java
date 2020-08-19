package com.epam.izh.rd.online.service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.GregorianCalendar;

public class SimpleDateService implements DateService {

    @Override
    public String parseDate(LocalDate localDate) {
        int monthValue = localDate.getMonthValue();
        String month = String.valueOf(monthValue);

        if (monthValue < 10) {
            month = "0" + month;
        }

        return localDate.getDayOfMonth() + "-" + month + "-" + localDate.getYear();
    }

    @Override
    public LocalDateTime parseString(String string) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");

        return LocalDateTime.parse(string, formatter);
    }

    @Override
    public String convertToCustomFormat(LocalDate localDate, DateTimeFormatter formatter) {
        return localDate.format(formatter);
    }

    @Override
    public long getNextLeapYear() {
        GregorianCalendar calendar = (GregorianCalendar) GregorianCalendar.getInstance();
        int year = calendar.get(calendar.YEAR);

        while (!calendar.isLeapYear(year)) {
            year++;
        }

        return year;
    }

    @Override
    public long getSecondsInYear(int year) {
        int days = 365;
        GregorianCalendar calendar = (GregorianCalendar) GregorianCalendar.getInstance();

        if (calendar.isLeapYear(year)) {
            days = 366;
        }
        return days * 24 * 60 * 60;
    }


}
