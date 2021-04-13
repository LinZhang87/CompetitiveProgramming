package misc;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

/**
 * @author lzhang
 * @since 2/22/20
 */

/*
Write a program to count the number of days between two dates.

The two dates are given as strings, their format is YYYY-MM-DD.
 */

public class DaysDifferenceBetweenTwoDates {
    private static final int DAYS_OF_A_YEAR= 365;
    private int[] daysInMonth = {31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};

    public int daysDiffChronoUnit(String date1, String date2) {
        return (int) Math.abs(ChronoUnit.DAYS.between(LocalDate.parse(date1), LocalDate.parse(date2)));
    }

    public int daysBetweenDates(String date1, String date2) {
        String[] s1 = date1.split("-");
        String[] s2 = date2.split("-");
        int[] d1 = new int[3];
        int[] d2 = new int[3];
        for(int i = 0; i < 3; i++) {
            d1[i] = Integer.parseInt(s1[i]);
            d2[i] = Integer.parseInt(s2[i]);
        }

        int n1 = d1[0] * DAYS_OF_A_YEAR + d1[2];
        for(int i = 0; i < d1[1] - 1; i++) {
            n1 += daysInMonth[i];
        }
        n1 += countLeapYearsBeforeDate(d1[0], d1[1]);

        int n2 = d2[0] * DAYS_OF_A_YEAR + d2[2];
        for(int i = 0; i < d2[1] - 1; i++) {
            n2 += daysInMonth[i];
        }
        n2 += countLeapYearsBeforeDate(d2[0], d2[1]);

        return Math.abs(n2 - n1);
    }

    private int countLeapYearsBeforeDate(int year, int month) {
        if(month <= 2) {
            year--;
        }
        return year / 4 - year / 100 + year / 400;
    }

    /*
        1. year is multiple of 400;
        Or
        2. year is multiple of 4 but not multiple of 100.
     */
    public boolean isLeapYear(int year) {
        if(year % 400 == 0 || year % 4 == 0 && year % 100 != 0) {
            return true;
        }
        return false;
    }
}
