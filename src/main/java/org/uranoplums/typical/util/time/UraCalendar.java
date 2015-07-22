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
 * $Id: UraCalendar.java$
 */
package org.uranoplums.typical.util.time;

import java.io.Serializable;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

/**
 * UraCalendarクラス。<br>
 * 
 * @since 2015/03/27
 * @author syany
 */
public interface UraCalendar extends RowCalendar, Serializable, Cloneable, Comparable<Calendar> {

    /**  */
    public final static int AM = Calendar.AM;
    public static final int AM_PM = Calendar.AM_PM;
    public static final int APRIL = Calendar.APRIL;
    public static final int AUGUST = Calendar.AUGUST;
    public static final int DATE = Calendar.DATE;
    public static final int DAY_OF_MONTH = Calendar.DAY_OF_MONTH;
    public static final int DAY_OF_WEEK = Calendar.DAY_OF_WEEK;
    public static final int DAY_OF_WEEK_IN_MONTH = Calendar.DAY_OF_WEEK_IN_MONTH;
    public static final int DAY_OF_YEAR = Calendar.DAY_OF_YEAR;
    public static final int DECEMBER = Calendar.DECEMBER;
    public static final int DST_OFFSET = Calendar.DST_OFFSET;
    public static final int ERA = Calendar.ERA;
    public static final int FEBRUARY = Calendar.FEBRUARY;
    public static final int FRIDAY = Calendar.FRIDAY;
    public static final int HOUR = Calendar.HOUR;
    public static final int HOUR_OF_DAY = Calendar.HOUR_OF_DAY;
    public static final int JANUARY = Calendar.JANUARY;
    public static final int JULY = Calendar.JULY;
    public static final int JUNE = Calendar.JUNE;
    public static final int MARCH = Calendar.MARCH;
    public static final int MAY = Calendar.MAY;
    public static final int MILLISECOND = Calendar.MILLISECOND;
    public static final int MINUTE = Calendar.MINUTE;
    public static final int MONDAY = Calendar.MONDAY;
    public static final int MONTH = Calendar.MONTH;
    public static final int NOVEMBER = Calendar.NOVEMBER;
    public static final int OCTOBER = Calendar.OCTOBER;
    public static final int PM = Calendar.PM;
    public static final int SATURDAY = Calendar.SATURDAY;
    public static final int SECOND = Calendar.SECOND;
    public static final int SEPTEMBER = Calendar.SEPTEMBER;
    public static final int SUNDAY = Calendar.SUNDAY;
    public static final int THURSDAY = Calendar.THURSDAY;
    public static final int TUESDAY = Calendar.TUESDAY;
    public static final int UNDECIMBER = Calendar.UNDECIMBER;
    public static final int WEDNESDAY = Calendar.WEDNESDAY;
    public static final int WEEK_OF_MONTH = Calendar.WEEK_OF_MONTH;
    public static final int WEEK_OF_YEAR = Calendar.WEEK_OF_YEAR;
    public static final int YEAR = Calendar.YEAR;
    public static final int ZONE_OFFSET = Calendar.ZONE_OFFSET;
    public static final int ALL_STYLES = Calendar.ALL_STYLES;
    public static final int LONG = Calendar.LONG;
    public static final int SHORT = Calendar.SHORT;
    /**  */
    public static final int LOCALE_ERA = 17;
    public static final int LOCALE_YEAR = 18;
    /**  */
    public static final int FIELD_COUNT = LOCALE_YEAR + 1;
    public static final String[] FIELD_NAME = {
            "ERA", "YEAR", "MONTH", "WEEK_OF_YEAR", "WEEK_OF_MONTH", "DAY_OF_MONTH",
            "DAY_OF_YEAR", "DAY_OF_WEEK", "DAY_OF_WEEK_IN_MONTH", "AM_PM", "HOUR",
            "HOUR_OF_DAY", "MINUTE", "SECOND", "MILLISECOND", "ZONE_OFFSET",
            "DST_OFFSET", "LOCALE_ERA", "LOCALE_YEAR"
    };
    public static final int UNSET = 0;
    public static final int COMPUTED = 1;
    public static final int MINIMUM_USER_STAMP = 2;
    public final static int ERA_MASK = (1 << UraCalendar.ERA);
    public final static int YEAR_MASK = (1 << UraCalendar.YEAR);
    public final static int MONTH_MASK = (1 << UraCalendar.MONTH);
    public final static int WEEK_OF_YEAR_MASK = (1 << UraCalendar.WEEK_OF_YEAR);
    public final static int WEEK_OF_MONTH_MASK = (1 << UraCalendar.WEEK_OF_MONTH);
    public final static int DAY_OF_MONTH_MASK = (1 << UraCalendar.DAY_OF_MONTH);
    public final static int DATE_MASK = DAY_OF_MONTH_MASK;
    public final static int DAY_OF_YEAR_MASK = (1 << UraCalendar.DAY_OF_YEAR);
    public final static int DAY_OF_WEEK_MASK = (1 << UraCalendar.DAY_OF_WEEK);
    public final static int DAY_OF_WEEK_IN_MONTH_MASK = (1 << UraCalendar.DAY_OF_WEEK_IN_MONTH);
    public final static int AM_PM_MASK = (1 << UraCalendar.AM_PM);
    public final static int HOUR_MASK = (1 << UraCalendar.HOUR);
    public final static int HOUR_OF_DAY_MASK = (1 << UraCalendar.HOUR_OF_DAY);
    public final static int MINUTE_MASK = (1 << UraCalendar.MINUTE);
    public final static int SECOND_MASK = (1 << UraCalendar.SECOND);
    public final static int MILLISECOND_MASK = (1 << UraCalendar.MILLISECOND);
    public final static int ZONE_OFFSET_MASK = (1 << UraCalendar.ZONE_OFFSET);
    public final static int DST_OFFSET_MASK = (1 << UraCalendar.DST_OFFSET);
    public final static int LOCALE_ERA_MASK = (1 << LOCALE_ERA);
    public final static int LOCALE_YEAR_MASK = (1 << LOCALE_YEAR);
    public static final int ALL_FIELDS = (1 << FIELD_COUNT) - 1;

    public UraCalendar addAm();

    public UraCalendar addDay(int day);

    public UraCalendar addDayOfWeek(int day);

    public UraCalendar addDayOfWeekInMonth(int day);

    public UraCalendar addDayOfYear(int day);

    public UraCalendar addDST(int offset);

    public UraCalendar addDWeek(int day);

    public UraCalendar addDWeekInMonth(int day);

    public UraCalendar addDYear(int day);

    public UraCalendar addEra(int era);

    public UraCalendar addHours(int hours);

    public UraCalendar addHoursOfDay(int hours);

    public UraCalendar addMilliseconds(int milliseconds);

    public UraCalendar addMinutes(int minutes);

    public UraCalendar addMonth(int month);

    public UraCalendar addPm();

    public UraCalendar addSeconds(int seconds);

    public UraCalendar addWeekOfMonth(int week);

    public UraCalendar addWeekOfYear(int week);

    public UraCalendar addWMonth(int week);

    public UraCalendar addWYear(int week);

    public UraCalendar addYear(int year);

    @Override
    public UraCalendar clone();

    public int compareDate(Calendar calendar);

    public int compareTime(Calendar calendar);

    public String fastFormat(String format);

    public String format(String format);

    public int getAmPm();

    public String getAmPmDisplayName(Locale locale);

    public Date getDate();

    public int getDay();

    public int getDayOfMonth();

    public int getDayOfWeek();

    public String getDayOfWeekDisplayName(int style, Locale locale);

    public int getDayOfWeekInMonth();

    public int getDayOfYear();

    public int getDST();

    public int getDYear();

    public int getEra();

    public String getEraDisplayName(Locale locale);

    public int getHours();

    public int getHoursOfDay();

    public Locale getLocale();

    public String getLocaleEraDisplayName(Locale locale);

    public String getLocaleYearDisplayName(Locale locale);

    public Calendar getMe();

    public int getMilliseconds();

    public int getMinutes();

    public int getMonth();

    public String getMonthDisplayName(int style, Locale locale);

    public int getSeconds();

    public int getTimezoneOffset();

    public int getTimezoneOffsetTime();

    public int getWDay();

    public int getWeekOfMonth();

    public int getWeekOfYear();

    public int getWMonth();

    public int getWYear();

    public int getYear();

    public UraCalendar rollAm();

    public UraCalendar rollDay(int day);

    public UraCalendar rollDayOfWeek(int day);

    public UraCalendar rollDayOfWeekInMonth(int day);

    public UraCalendar rollDayOfYear(int day);

    public UraCalendar rollDST(int offset);

    public UraCalendar rollDWeek(int day);

    public UraCalendar rollDWeekInMonth(int day);

    public UraCalendar rollDYear(int day);

    public UraCalendar rollEra(int era);

    public UraCalendar rollHours(int hours);

    public UraCalendar rollHoursOfDay(int hours);

    public UraCalendar rollMilliseconds(int milliseconds);

    public UraCalendar rollMinutes(int minutes);

    public UraCalendar rollMonth(int month);

    public UraCalendar rollPm();

    public UraCalendar rollSeconds(int seconds);

    public UraCalendar rollWeekOfMonth(int week);

    public UraCalendar rollWeekOfYear(int week);

    public UraCalendar rollWMonth(int week);

    public UraCalendar rollWYear(int week);

    public UraCalendar rollYear(int year);

    public UraCalendar setAm();

    public UraCalendar setAmPmDispName(String name);

    public UraCalendar setAmPmDispName(String name, Locale locale);

    public UraCalendar setDay(int day);

    public UraCalendar setDayOfWeek(int day);

    public UraCalendar setDayOfWeekDispName(String name);

    public UraCalendar setDayOfWeekDispName(String name, Locale locale);

    public UraCalendar setDayOfWeekInMonth(int day);

    public UraCalendar setDayOfYear(int day);

    /**
     * 対象フィールドに文字列に合う値を設定する。<br>
     * @param key
     * @param field
     * @param style
     * @param locale
     * @return インスタンス
     */
    public UraCalendar setDisplayName(String key, int field, int style, Locale locale);

    public UraCalendar setDST(int offset);

    public UraCalendar setDWeek(int day);

    public UraCalendar setDWeekInMonth(int day);

    public UraCalendar setDYear(int day);

    public UraCalendar setEra(int era);

    public UraCalendar setEraDispName(String name);

    public UraCalendar setEraDispName(String name, Locale locale);

    public UraCalendar setHours(int hours);

    public UraCalendar setHoursOfDay(int hours);

    public UraCalendar setLastDayOfMonth();

    public void setLocale(final Locale locale);

    public UraCalendar setLocaleEraDispName(String name);

    public UraCalendar setLocaleEraDispName(String name, Locale locale);

    public UraCalendar setLocaleYearDispName(String name);

    /**
     * ローカル暦の設定。<br>
     * @param name
     * @param locale
     * @return インスタンス
     */
    public UraCalendar setLocaleYearDispName(String name, Locale locale);

    public UraCalendar setMilliseconds(int milliseconds);

    public UraCalendar setMinutes(int minutes);

    public UraCalendar setMonth(int month);

    public UraCalendar setMonthDispName(String name);

    public UraCalendar setMonthDispName(String name, Locale locale);

    /**
     * 現在のシステム日時を設定します。。<br>
     * @return Calendarインスタンス
     */
    public UraCalendar setNow();

    public UraCalendar setPm();

    public UraCalendar setSeconds(int seconds);

    public UraCalendar setStrictAm();

    public UraCalendar setStrictDay(int day);

    public UraCalendar setStrictDayOfWeek(int day);

    public UraCalendar setStrictDayOfWeekInMonth(int day);

    public UraCalendar setStrictDayOfYear(int day);

    public UraCalendar setStrictDST(int offset);

    public UraCalendar setStrictDWeek(int day);

    public UraCalendar setStrictDWeekInMonth(int day);

    public UraCalendar setStrictDYear(int day);

    public UraCalendar setStrictEra(int era);

    public UraCalendar setStrictHours(int hours);

    public UraCalendar setStrictHoursOfDay(int hours);

    public UraCalendar setStrictMilliseconds(int milliseconds);

    public UraCalendar setStrictMinutes(int minutes);

    public UraCalendar setStrictMonth(int month);

    public UraCalendar setStrictPm();

    public UraCalendar setStrictSeconds(int seconds);

    public UraCalendar setStrictWeekOfMonth(int week);

    public UraCalendar setStrictWeekOfYear(int week);

    public UraCalendar setStrictWMonth(int week);

    public UraCalendar setStrictWYear(int week);

    public UraCalendar setStrictYear(int year);

    public UraCalendar setWeekOfMonth(int week);

    public UraCalendar setWeekOfYear(int week);

    public UraCalendar setWMonth(int week);

    public UraCalendar setWYear(int week);

    public UraCalendar setYear(int year);

    public UraCalendar trancateDay();

    public UraCalendar trancateHours();

    public UraCalendar trancateMilliseconds();

    public UraCalendar trancateMinutes();

    public UraCalendar trancateMonth();

    public UraCalendar trancateSeconds();
}
