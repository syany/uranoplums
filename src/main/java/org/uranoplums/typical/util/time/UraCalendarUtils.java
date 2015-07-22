/*
 * Copyright 2013-2015 the Uranoplums Foundation and the Others.
 * 
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 * http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND,
 * either express or implied. See the License for the specific language
 * governing permissions and limitations under the License.
 * 
 * $Id: UraCalendarUtils.java$
 */
package org.uranoplums.typical.util.time;

import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;

import org.apache.commons.lang3.time.DateUtils;
import org.uranoplums.typical.util.UraUtils;

/**
 * UraCalendarUtilsクラス。<br>
 * 
 * @since 2015/03/30
 * @author syany
 */
public class UraCalendarUtils extends UraUtils {

    /**  */
    private static final Locale JAPANESE_LOCALE = new Locale("ja", "JP", "JP");
    private static final UraCalendar URA_CALENDAR = newUraCalendar();

    /**
     * ミリ秒から暦を取得。<br>
     * @param millis
     * @return
     */
    public static synchronized final int getEra(long millis) {
        URA_CALENDAR.setTimeInMillis(millis);
        return URA_CALENDAR.getEra();
    }

    /**
     * ミリ秒から年を取得。<br>
     * @param millis
     * @return
     */
    public static synchronized final int getYear(long millis) {
        URA_CALENDAR.setTimeInMillis(millis);
        return URA_CALENDAR.getYear();
    }

    /**
     * 。<br>
     * @param calendar
     * @return
     */
    public static final boolean isLeapYear(Calendar calendar) {
        int year = calendar.get(Calendar.YEAR) - 1900;
        return isLeapYear(year);
    }

    /**
     * 。<br>
     * @param yDate
     * @return
     */
    public static final boolean isLeapYear(Date yDate) {
        int year = DateUtils.toCalendar(yDate).get(Calendar.YEAR) - 1900;
        return isLeapYear(year);
    }

    /**
     * 。<br>
     * @param year
     * @return
     */
    public static final boolean isLeapYear(int year) {
        return (year % 4 == 0 && year % 100 != 0) || year % 400 == 0;
    }

    /**
     * 。<br>
     * @return
     */
    public static final Calendar newJapaneseCalendar() {
        return Calendar.getInstance(JAPANESE_LOCALE);
    }

    /**
     * 。<br>
     * @param zone
     * @return
     */
    public static final Calendar newJapaneseCalendar(TimeZone zone) {
        return Calendar.getInstance(zone, JAPANESE_LOCALE);
    }

    /**
     * 。<br>
     * @return
     */
    public static final UraCalendar newUraCalendar() {
        return new UraGregorianCalendar();
    }

    /**
     * 。<br>
     * @param calendar
     * @return
     */
    public static final UraCalendar newUraCalendar(Calendar calendar) {
        return new UraGregorianCalendar(calendar);
    }

    /**
     * 。<br>
     * @param year
     * @param month
     * @param dayOfMonth
     * @return
     */
    public static final UraCalendar newUraCalendar(int year, int month, int dayOfMonth) {
        return new UraGregorianCalendar(year, month, dayOfMonth);
    }

    /**
     * 。<br>
     * @param year
     * @param month
     * @param dayOfMonth
     * @param hourOfDay
     * @param minute
     * @return
     */
    public static final UraCalendar newUraCalendar(int year, int month, int dayOfMonth, int hourOfDay,
            int minute) {
        return new UraGregorianCalendar(year, month, dayOfMonth, hourOfDay, minute);
    }

    /**
     * 。<br>
     * @param year
     * @param month
     * @param dayOfMonth
     * @param hourOfDay
     * @param minute
     * @param second
     * @return
     */
    public static final UraCalendar newUraCalendar(int year, int month, int dayOfMonth, int hourOfDay,
            int minute, int second) {
        return new UraGregorianCalendar(year, month, dayOfMonth, hourOfDay, minute, second);
    }

    /**
     * 。<br>
     * @param aLocale
     * @return
     */
    public static final UraCalendar newUraCalendar(Locale aLocale) {
        return new UraGregorianCalendar(aLocale);
    }

    /**
     * 。<br>
     * @return
     */
    public static final UraCalendar newUraCalendar(long millis) {
        final UraCalendar cal = new UraGregorianCalendar();
        cal.setTimeInMillis(millis);
        return cal;
    }

    /**
     * 。<br>
     * @param zone
     * @return
     */
    public static final UraCalendar newUraCalendar(TimeZone zone) {
        return new UraGregorianCalendar(zone);
    }

    /**
     * 。<br>
     * @param zone
     * @param aLocale
     * @return
     */
    public static final UraCalendar newUraCalendar(TimeZone zone, Locale aLocale) {
        return new UraGregorianCalendar(zone, aLocale);
    }

    /**
     * 。<br>
     * @param millis
     * @return
     */
    public static synchronized final Date toDate(long millis) {
        URA_CALENDAR.setTimeInMillis(millis);
        return URA_CALENDAR.getDate();
    }

    /**
     * 。<br>
     * @param date
     * @return
     */
    public static final UraCalendar toUraCalendar(Date date) {
        UraCalendar uraCalendar = newUraCalendar();
        uraCalendar.setTime(date);
        return uraCalendar;
    }
}
