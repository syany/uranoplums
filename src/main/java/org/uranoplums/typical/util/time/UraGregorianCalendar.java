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
 * $Id: UraGregorianCalendar.java$
 */
package org.uranoplums.typical.util.time;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Locale;
import java.util.Map;
import java.util.TimeZone;

import org.apache.commons.lang3.time.DateUtils;
import org.apache.commons.lang3.time.FastDateFormat;

import static org.uranoplums.typical.collection.factory.UraMapFactory.*;

/**
 * UraGregorianCalendarクラス。<br>
 * 
 * @since 2015/03/30
 * @author syany
 */
public class UraGregorianCalendar extends GregorianCalendar implements UraCalendar {

    /**  */
    private static final long serialVersionUID = 1500886621576030197L;
    /**  */
    private Locale locale;

    /**
     * デフォルトコンストラクタ。<br>
     * @param calendar
     */
    public UraGregorianCalendar() {
        super();
    }

    /**
     * デフォルトコンストラクタ。<br>
     * @param calendar
     */
    public UraGregorianCalendar(Calendar calendar) {
        super();
        this.setTimeInMillis(calendar.getTimeInMillis());
    }

    /**
     * デフォルトコンストラクタ。<br>
     * @param calendar
     * @param aLocale
     */
    public UraGregorianCalendar(Calendar calendar, Locale aLocale) {
        super(aLocale);
        this.setTimeInMillis(calendar.getTimeInMillis());
    }

    /**
     * デフォルトコンストラクタ。<br>
     * @param calendar
     * @param zone
     */
    public UraGregorianCalendar(Calendar calendar, TimeZone zone) {
        super(zone);
        this.setTimeInMillis(calendar.getTimeInMillis());
    }

    /**
     * デフォルトコンストラクタ。<br>
     * @param calendar
     * @param zone
     * @param aLocale
     */
    public UraGregorianCalendar(Calendar calendar, TimeZone zone, Locale aLocale) {
        super(zone, aLocale);
        this.setTimeInMillis(calendar.getTimeInMillis());
    }

    /**
     * デフォルトコンストラクタ。<br>
     * @param year
     * @param month
     * @param dayOfMonth
     */
    public UraGregorianCalendar(int year, int month, int dayOfMonth) {
        super(year, month, dayOfMonth);
    }

    /**
     * デフォルトコンストラクタ。<br>
     * @param year
     * @param month
     * @param dayOfMonth
     * @param hourOfDay
     * @param minute
     */
    public UraGregorianCalendar(int year, int month, int dayOfMonth, int hourOfDay, int minute) {
        super(year, month, dayOfMonth, hourOfDay, minute);
    }

    /**
     * デフォルトコンストラクタ。<br>
     * @param year
     * @param month
     * @param dayOfMonth
     * @param hourOfDay
     * @param minute
     * @param second
     */
    public UraGregorianCalendar(int year, int month, int dayOfMonth, int hourOfDay, int minute, int second) {
        super(year, month, dayOfMonth, hourOfDay, minute, second);
    }

    /**
     * デフォルトコンストラクタ。<br>
     * @param year
     * @param month
     * @param dayOfMonth
     * @param hourOfDay
     * @param minute
     * @param second
     * @param millis
     */
    public UraGregorianCalendar(int year, int month, int dayOfMonth, int hourOfDay, int minute, int second, int millis) {
        super(year, month, dayOfMonth, hourOfDay, minute, second);
        this.setMilliseconds(millis);
    }

    /**
     * デフォルトコンストラクタ。<br>
     * @param aLocale
     */
    public UraGregorianCalendar(Locale aLocale) {
        super(aLocale);
    }

    /**
     * デフォルトコンストラクタ。<br>
     * @param zone
     */
    public UraGregorianCalendar(TimeZone zone) {
        super(zone);
    }

    /**
     * デフォルトコンストラクタ。<br>
     * @param zone
     * @param aLocale
     */
    public UraGregorianCalendar(TimeZone zone, Locale aLocale) {
        super(zone, aLocale);
    }

    /**
     * Returns whether the specified <code>field</code> is on in the <code>fieldMask</code>.
     */
    protected static final boolean isFieldSet(int fieldMask, int field) {
        return (fieldMask & (1 << field)) != 0;
    }

    private Map<String, Integer> getDisplayNamesImpl(int field, int style, Locale locale) {
        UraDateFormatSymbols symbols = UraDateFormatSymbols.getUraInstance(locale);
        String[] strings = getFieldStrings(field, style, symbols);
        // Locale系対応
        if (strings == null) {
            strings = getDisplayLocaleNames(field, style, locale, symbols);
        }
        if (strings != null) {
            Map<String, Integer> names = newHashMap();
            for (int i = 0; i < strings.length; i++) {
                if (strings[i].length() == 0) {
                    continue;
                }
                names.put(strings[i], Integer.valueOf(i));
            }
            return names;
        }
        return null;
    }

    /**
     * 。<br>
     * @param field
     * @param style
     * @param symbols
     * @return
     */
    protected String[] getFieldStrings(int field, int style, UraDateFormatSymbols symbols) {
        String[] strings = null;
        switch (field) {
            case UraCalendar.ERA:
                strings = symbols.getEras();
                break;
            case UraCalendar.MONTH:
                strings = (style == UraCalendar.LONG) ? symbols.getMonths() : symbols.getShortMonths();
                break;
            case UraCalendar.DAY_OF_WEEK:
                strings = (style == UraCalendar.LONG) ? symbols.getWeekdays() : symbols.getShortWeekdays();
                break;
            case UraCalendar.AM_PM:
                strings = symbols.getAmPmStrings();
                break;
            // case UraCalendar.LOCALE_ERA:
            // strings = symbols.getLocaleEra();
            // break;
            // case UraCalendar.LOCALE_YEAR:
            // long[] localeYearArray = symbols.getLocaleYear();
            // int max = localeYearArray.length;
            // strings = new String[max];
            // for (int i = 0; i < max; i++) {
            // strings[i] = String.valueOf(localeYearArray[i]);
            // }
            // //strings = symbols.getLocaleYear();
            // break;
            default:
                break;
        }
        return strings;
    }

    /**
     * 。<br>
     * @param field
     * @param style
     * @param minStyle
     * @param maxStyle
     * @param locale
     * @param fieldMask
     * @return
     */
    protected boolean isDisplayNameParams(int field, int style, int minStyle, int maxStyle,
            Locale locale, int fieldMask) {
        // if (field < 0 || field >= fields.length ||
        // style < minStyle || style > maxStyle) {
        if (field < 0 || field >= UraCalendar.FIELD_COUNT ||
                style < minStyle || style > maxStyle) {
            throw new IllegalArgumentException();
        }
        if (locale == null) {
            throw new NullPointerException();
        }
        return isFieldSet(fieldMask, field);
    }

    @Override
    public UraCalendar addAm() {
        this.add(UraCalendar.AM_PM, UraCalendar.AM);
        return this;
    }

    @Override
    public UraCalendar addDay(int day) {
        this.add(UraCalendar.DAY_OF_MONTH, day);
        return this;
    }

    @Override
    public UraCalendar addDayOfWeek(int day) {
        this.add(UraCalendar.DAY_OF_WEEK, day);
        return this;
    }

    @Override
    public UraCalendar addDayOfWeekInMonth(int day) {
        this.add(UraCalendar.DAY_OF_WEEK_IN_MONTH, day);
        return this;
    }

    @Override
    public UraCalendar addDayOfYear(int day) {
        this.add(UraCalendar.DAY_OF_YEAR, day);
        return this;
    }

    @Override
    public UraCalendar addDST(int offset) {
        this.add(UraCalendar.DST_OFFSET, offset);
        return this;
    }

    @Override
    public UraCalendar addDWeek(int day) {
        return addDayOfWeek(day);
    }

    @Override
    public UraCalendar addDWeekInMonth(int day) {
        return addDayOfWeekInMonth(day);
    }

    @Override
    public UraCalendar addDYear(int day) {
        return addDayOfYear(day);
    }

    @Override
    public UraCalendar addEra(int era) {
        this.add(UraCalendar.ERA, era);
        return this;
    }

    @Override
    public UraCalendar addHours(int hours) {
        this.add(UraCalendar.HOUR, hours);
        return this;
    }

    @Override
    public UraCalendar addHoursOfDay(int hours) {
        this.add(UraCalendar.HOUR_OF_DAY, hours);
        return this;
    }

    @Override
    public UraCalendar addMilliseconds(int milliseconds) {
        this.add(UraCalendar.MILLISECOND, milliseconds);
        return this;
    }

    @Override
    public UraCalendar addMinutes(int minutes) {
        this.add(UraCalendar.MINUTE, minutes);
        return this;
    }

    @Override
    public UraCalendar addMonth(int month) {
        this.add(UraCalendar.MONTH, month);
        return this;
    }

    @Override
    public UraCalendar addPm() {
        this.add(UraCalendar.AM_PM, UraCalendar.PM);
        return this;
    }

    @Override
    public UraCalendar addSeconds(int seconds) {
        this.add(UraCalendar.SECOND, seconds);
        return this;
    }

    @Override
    public UraCalendar addWeekOfMonth(int week) {
        this.add(UraCalendar.WEEK_OF_MONTH, week);
        return this;
    }

    @Override
    public UraCalendar addWeekOfYear(int week) {
        this.add(UraCalendar.WEEK_OF_YEAR, week);
        return this;
    }

    @Override
    public UraCalendar addWMonth(int week) {
        return addWeekOfMonth(week);
    }

    @Override
    public UraCalendar addWYear(int week) {
        return addWeekOfYear(week);
    }

    @Override
    public UraCalendar addYear(int year) {
        this.add(UraCalendar.YEAR, year);
        return this;
    }

    @Override
    public UraCalendar clone() {
        UraCalendar calendar = (UraCalendar) super.clone();
        calendar.setLocale(locale);
        return calendar;
    }

    @Override
    public int compareDate(Calendar calendar) {
        return DateUtils.truncatedCompareTo(this, calendar, UraCalendar.DAY_OF_MONTH);
    }

    @Override
    public int compareTime(Calendar calendar) {
        return DateUtils.truncatedCompareTo(this, calendar, UraCalendar.MILLISECOND);
    }

    @Override
    public String fastFormat(String format) {
        return FastDateFormat.getInstance(format, this.getTimeZone(), locale).format(this);
    }

    @Override
    public String format(String format) {
        return UraDateFormat.getInstance(format).format(this.getCalendar());
    }

    @Override
    public int getAmPm() {
        return this.get(UraCalendar.AM_PM);
    }

    /*
     * (非 Javadoc)
     * 
     * @see org.uranoplums.typical.util.time.UraCalendar#getAmPmDisplayName(java.util.Locale)
     */
    @Override
    public String getAmPmDisplayName(Locale locale) {
        return this.getDisplayName(UraCalendar.AM_PM, UraCalendar.LONG, locale);
    }

    @Override
    public Calendar getCalendar() {
        return this;
    }

    @Override
    public Date getDate() {
        return this.getTime();
    }

    @Override
    public int getDay() {
        return getDayOfMonth();
    }

    @Override
    public int getDayOfMonth() {
        return this.get(UraCalendar.DAY_OF_MONTH);
    }

    @Override
    public int getDayOfWeek() {
        return this.get(UraCalendar.DAY_OF_WEEK);
    }

    /*
     * (非 Javadoc)
     * 
     * @see org.uranoplums.typical.util.time.UraCalendar#getDayOfWeekDisplayName(int, java.util.Locale)
     */
    @Override
    public String getDayOfWeekDisplayName(int style, Locale locale) {
        return this.getDisplayName(UraCalendar.DAY_OF_WEEK, style, locale);
    }

    @Override
    public int getDayOfWeekInMonth() {
        return this.get(UraCalendar.DAY_OF_WEEK_IN_MONTH);
    }

    @Override
    public int getDayOfYear() {
        return this.get(UraCalendar.DAY_OF_YEAR);
    }

    /**
     * 。<br>
     * @param field
     * @param style
     * @param locale
     * @param symbols
     * @return 取得したローカル文字列
     */
    public String getDisplayLocaleName(int field, int style, Locale locale, UraDateFormatSymbols symbols) {
        long[] localeDateArray = symbols.getLocaleYear();
        long source = this.getTimeInMillis();
        String result = null;
        switch (field) {
            case UraCalendar.LOCALE_ERA: {
                try {
                    for (int i = localeDateArray.length - 1; i >= 0; i--) {
                        if (source >= localeDateArray[i]) {
                            result = symbols.getLocaleEra()[i];
                            break;
                        }
                    }
                } catch (Exception ex) {
                    // Array
                }
                break;
            }
            case UraCalendar.LOCALE_YEAR: {
                for (int i = localeDateArray.length - 1; i >= 0; i--) {
                    //
                    if (source >= localeDateArray[i]) {
                        if (UraCalendarUtils.getEra(localeDateArray[i]) > 0) {
                            int targetYear = UraCalendarUtils.getYear(localeDateArray[i]) - 1;
                            int sourceYear = this.get(UraCalendar.YEAR);
                            result = String.valueOf(sourceYear - targetYear);
                        } else {
                            // 紀元前（BC）の場合の一時対応
                            result = String.valueOf(this.get(UraCalendar.YEAR));
                        }
                        break;
                    }
                }
                break;
            }
            default:
                break;
        }
        return result;
    }

    /**
     * 。<br>
     * @param field
     * @param style
     * @param locale
     * @param symbols
     * @return ローカル暦など配列
     */
    public String[] getDisplayLocaleNames(int field, int style, Locale locale, UraDateFormatSymbols symbols) {
        String[] strings = null;
        switch (field) {
            case UraCalendar.LOCALE_ERA:
                strings = symbols.getLocaleEra();
                break;
            case UraCalendar.LOCALE_YEAR:
                long[] localeYearArray = symbols.getLocaleYear();
                int max = localeYearArray.length;
                strings = new String[max];
                for (int i = 0; i < max; i++) {
                    strings[i] = String.valueOf(localeYearArray[i]);
                }
                break;
            default:
                break;
        }
        return strings;
    }

    /**
     * Returns the string representation of the calendar <code>field</code> value in the given <code>style</code> and
     * <code>locale</code>. If no string representation is
     * applicable, <code>null</code> is returned. This method calls {@link Calendar#get(int) get(field)} to get the
     * calendar <code>field</code> value if the string representation is
     * applicable to the given calendar <code>field</code>.
     * 
     * <p>
     * For example, if this <code>Calendar</code> is a <code>GregorianCalendar</code> and its date is 2005-01-01, then
     * the string representation of the {@link #MONTH} field would be "January" in the long style in an English locale
     * or "Jan" in the short style. However, no string representation would be available for the {@link #DAY_OF_MONTH}
     * field, and this method would return <code>null</code>.
     * 
     * <p>
     * The default implementation supports the calendar fields for which a {@link UraDateFormatSymbols} has names in the
     * given <code>locale</code>.
     * 
     * @param field
     *            the calendar field for which the string representation
     *            is returned
     * @param style
     *            the style applied to the string representation; one of {@link #SHORT} or {@link #LONG}.
     * @param locale
     *            the locale for the string representation
     * @return the string representation of the given <code>field</code> in the given <code>style</code>, or
     *         <code>null</code> if no string representation is
     *         applicable.
     * @exception IllegalArgumentException
     *                if <code>field</code> or <code>style</code> is invalid,
     *                or if this <code>Calendar</code> is non-lenient and any
     *                of the calendar fields have invalid values
     * @exception NullPointerException
     *                if <code>locale</code> is null
     * @since 1.6
     */
    @Override
    public String getDisplayName(int field, int style, Locale locale) {
        if (!isDisplayNameParams(field, style, UraCalendar.ALL_STYLES, UraCalendar.LONG, locale,
                UraCalendar.ERA_MASK | UraCalendar.MONTH_MASK | UraCalendar.DAY_OF_WEEK_MASK | UraCalendar.AM_PM_MASK | UraCalendar.LOCALE_ERA_MASK
                        | UraCalendar.LOCALE_YEAR_MASK)) {
            return null;
        }
        UraDateFormatSymbols symbols = UraDateFormatSymbols.getUraInstance(locale);
        String[] strings = getFieldStrings(field, style, symbols);
        if (strings != null) {
            int fieldValue = get(field);
            if (fieldValue < strings.length) {
                return strings[fieldValue];
            }
        }
        String result = getDisplayLocaleName(field, style, locale, symbols);
        return result;
    }

    /**
     * Returns a <code>Map</code> containing all names of the calendar <code>field</code> in the given
     * <code>style</code> and <code>locale</code> and their corresponding field values. For
     * example, if this <code>Calendar</code> is a {@link GregorianCalendar}, the returned map would contain "Jan" to
     * {@link #JANUARY}, "Feb" to {@link #FEBRUARY}, and so on, in the {@linkplain #SHORT short} style in an English
     * locale.
     * 
     * <p>
     * The values of other calendar fields may be taken into account to determine a set of display names. For example,
     * if this <code>Calendar</code> is a lunisolar calendar system and the year value given by the {@link #YEAR} field
     * has a leap month, this method would return month names containing the leap month name, and month names are mapped
     * to their values specific for the year.
     * 
     * <p>
     * The default implementation supports display names contained in a {@link UraDateFormatSymbols}. For example, if
     * <code>field</code> is {@link #MONTH} and <code>style</code> is {@link #ALL_STYLES}, this method returns a
     * <code>Map</code> containing all strings returned by {@link UraDateFormatSymbols#getShortMonths()} and
     * {@link UraDateFormatSymbols#getMonths()}.
     * 
     * @param field
     *            the calendar field for which the display names are returned
     * @param style
     *            the style applied to the display names; one of {@link #SHORT}, {@link #LONG}, or {@link #ALL_STYLES}.
     * @param locale
     *            the locale for the display names
     * @return a <code>Map</code> containing all display names in <code>style</code> and <code>locale</code> and their
     *         field values, or <code>null</code> if no display names
     *         are defined for <code>field</code>
     * @exception IllegalArgumentException
     *                if <code>field</code> or <code>style</code> is invalid,
     *                or if this <code>Calendar</code> is non-lenient and any
     *                of the calendar fields have invalid values
     * @exception NullPointerException
     *                if <code>locale</code> is null
     * @since 1.6
     */
    @Override
    public Map<String, Integer> getDisplayNames(int field, int style, Locale locale) {
        if (!isDisplayNameParams(field, style, UraCalendar.ALL_STYLES, UraCalendar.LONG, locale,
                UraCalendar.ERA_MASK | UraCalendar.MONTH_MASK | UraCalendar.DAY_OF_WEEK_MASK | UraCalendar.AM_PM_MASK | UraCalendar.LOCALE_ERA_MASK
                        | UraCalendar.LOCALE_YEAR_MASK)) {
            return null;
        }
        // ALL_STYLES
        if (style == UraCalendar.ALL_STYLES) {
            Map<String, Integer> shortNames = getDisplayNamesImpl(field, UraCalendar.SHORT, locale);
            if (field == UraCalendar.ERA || field == UraCalendar.AM_PM) {
                return shortNames;
            }
            Map<String, Integer> longNames = getDisplayNamesImpl(field, UraCalendar.LONG, locale);
            if (shortNames == null) {
                return longNames;
            }
            if (longNames != null) {
                shortNames.putAll(longNames);
            }
            return shortNames;
        }
        // SHORT or LONG
        return getDisplayNamesImpl(field, style, locale);
    }

    @Override
    public int getDST() {
        return this.get(UraCalendar.DST_OFFSET);
    }

    @Override
    public int getDYear() {
        return getDayOfYear();
    }

    @Override
    public int getEra() {
        return this.get(UraCalendar.ERA);
    }

    /*
     * (非 Javadoc)
     * 
     * @see org.uranoplums.typical.util.time.UraCalendar#getEraDisplayName(java.util.Locale)
     */
    @Override
    public String getEraDisplayName(Locale locale) {
        return this.getDisplayName(UraCalendar.ERA, UraCalendar.LONG, locale);
    }

    @Override
    public int getHours() {
        return this.get(UraCalendar.HOUR);
    }

    @Override
    public int getHoursOfDay() {
        return this.get(UraCalendar.HOUR_OF_DAY);
    }

    @Override
    public Locale getLocale() {
        return locale;
    }

    /*
     * (非 Javadoc)
     * 
     * @see org.uranoplums.typical.util.time.UraCalendar#getLocaleEraDisplayName(java.util.Locale)
     */
    @Override
    public String getLocaleEraDisplayName(Locale locale) {
        return this.getDisplayName(UraCalendar.LOCALE_ERA, UraCalendar.LONG, locale);
    }

    /*
     * (非 Javadoc)
     * 
     * @see org.uranoplums.typical.util.time.UraCalendar#getLocaleYearDisplayName(java.util.Locale)
     */
    @Override
    public String getLocaleYearDisplayName(Locale locale) {
        return this.getDisplayName(UraCalendar.LOCALE_YEAR, UraCalendar.LONG, locale);
    }

    @Override
    public Calendar getMe() {
        return this;
    }

    @Override
    public int getMilliseconds() {
        return this.get(UraCalendar.MILLISECOND);
    }

    @Override
    public int getMinutes() {
        return this.get(UraCalendar.MINUTE);
    }

    @Override
    public int getMonth() {
        return this.get(UraCalendar.MONTH);
    }

    /*
     * (非 Javadoc)
     * 
     * @see org.uranoplums.typical.util.time.UraCalendar#getMonthDisplayName(int, java.util.Locale)
     */
    @Override
    public String getMonthDisplayName(int style, Locale locale) {
        return this.getDisplayName(UraCalendar.MONTH, style, locale);
    }

    @Override
    public int getSeconds() {
        return this.get(UraCalendar.SECOND);
    }

    @Override
    public int getTimezoneOffset() {
        return this.getTimezoneOffsetTime() / 36000;
    }

    @Override
    public int getTimezoneOffsetTime() {
        return this.get(UraCalendar.ZONE_OFFSET) + this.get(UraCalendar.DST_OFFSET);
    }

    @Override
    public int getWDay() {
        return getDayOfWeek();
    }

    @Override
    public int getWeekOfMonth() {
        return this.get(UraCalendar.WEEK_OF_MONTH);
    }

    @Override
    public int getWeekOfYear() {
        return this.get(UraCalendar.WEEK_OF_YEAR);
    }

    @Override
    public int getWMonth() {
        return getWeekOfMonth();
    }

    @Override
    public int getWYear() {
        return getWeekOfYear();
    }

    @Override
    public int getYear() {
        return this.get(UraCalendar.YEAR);
    }

    @Override
    public UraCalendar rollAm() {
        this.roll(UraCalendar.AM_PM, UraCalendar.AM);
        return this;
    }

    @Override
    public UraCalendar rollDay(int day) {
        this.roll(UraCalendar.DAY_OF_MONTH, day);
        return this;
    }

    @Override
    public UraCalendar rollDayOfWeek(int day) {
        this.roll(UraCalendar.DAY_OF_WEEK, day);
        return this;
    }

    @Override
    public UraCalendar rollDayOfWeekInMonth(int day) {
        this.roll(UraCalendar.DAY_OF_WEEK_IN_MONTH, day);
        return this;
    }

    @Override
    public UraCalendar rollDayOfYear(int day) {
        this.roll(UraCalendar.DAY_OF_YEAR, day);
        return this;
    }

    @Override
    public UraCalendar rollDST(int offset) {
        this.roll(UraCalendar.DST_OFFSET, offset);
        return this;
    }

    @Override
    public UraCalendar rollDWeek(int day) {
        return rollDayOfWeek(day);
    }

    @Override
    public UraCalendar rollDWeekInMonth(int day) {
        return rollDayOfWeekInMonth(day);
    }

    @Override
    public UraCalendar rollDYear(int day) {
        return rollDayOfYear(day);
    }

    @Override
    public UraCalendar rollEra(int era) {
        this.roll(UraCalendar.ERA, era);
        return this;
    }

    @Override
    public UraCalendar rollHours(int hours) {
        this.roll(UraCalendar.HOUR, hours);
        return this;
    }

    @Override
    public UraCalendar rollHoursOfDay(int hours) {
        this.roll(UraCalendar.HOUR_OF_DAY, hours);
        return this;
    }

    @Override
    public UraCalendar rollMilliseconds(int milliseconds) {
        this.roll(UraCalendar.MILLISECOND, milliseconds);
        return this;
    }

    @Override
    public UraCalendar rollMinutes(int minutes) {
        this.roll(UraCalendar.MINUTE, minutes);
        return this;
    }

    @Override
    public UraCalendar rollMonth(int month) {
        this.roll(UraCalendar.MONTH, month);
        return this;
    }

    @Override
    public UraCalendar rollPm() {
        this.roll(UraCalendar.AM_PM, UraCalendar.PM);
        return this;
    }

    @Override
    public UraCalendar rollSeconds(int seconds) {
        this.roll(UraCalendar.SECOND, seconds);
        return this;
    }

    @Override
    public UraCalendar rollWeekOfMonth(int week) {
        this.roll(UraCalendar.WEEK_OF_MONTH, week);
        return this;
    }

    @Override
    public UraCalendar rollWeekOfYear(int week) {
        this.roll(UraCalendar.WEEK_OF_YEAR, week);
        return this;
    }

    @Override
    public UraCalendar rollWMonth(int week) {
        return rollWeekOfMonth(week);
    }

    @Override
    public UraCalendar rollWYear(int week) {
        return rollWeekOfYear(week);
    }

    @Override
    public UraCalendar rollYear(int year) {
        this.roll(UraCalendar.YEAR, year);
        return this;
    }

    /*
     * (非 Javadoc)
     * 
     * @see java.util.Calendar#set(int, int)
     */
    @Override
    public void set(int field, int value) {
        // Locale set
        super.set(field, value);
    }

    @Override
    public UraCalendar setAm() {
        this.set(UraCalendar.AM_PM, UraCalendar.AM);
        return this;
    }

    @Override
    public UraCalendar setAmPmDispName(String name) {
        return setAmPmDispName(name, locale);
    }

    @Override
    public UraCalendar setAmPmDispName(String name, Locale locale) {
        return this.setDisplayName(name, UraCalendar.AM_PM, UraCalendar.ALL_STYLES, locale);
    }

    @Override
    public UraCalendar setDay(int day) {
        this.set(UraCalendar.DAY_OF_MONTH, day);
        return this;
    }

    @Override
    public UraCalendar setDayOfWeek(int day) {
        this.set(UraCalendar.DAY_OF_WEEK, day);
        return this;
    }

    @Override
    public UraCalendar setDayOfWeekDispName(String name) {
        return setDayOfWeekDispName(name, locale);
    }

    @Override
    public UraCalendar setDayOfWeekDispName(String name, Locale locale) {
        return this.setDisplayName(name, UraCalendar.DAY_OF_WEEK, UraCalendar.ALL_STYLES, locale);
    }

    @Override
    public UraCalendar setDayOfWeekInMonth(int day) {
        this.set(UraCalendar.DAY_OF_WEEK_IN_MONTH, day);
        return this;
    }

    @Override
    public UraCalendar setDayOfYear(int day) {
        this.set(UraCalendar.DAY_OF_YEAR, day);
        return this;
    }

    @Override
    public UraCalendar setDisplayName(String key, int field, int style, Locale locale) {
        Map<String, Integer> displayNames = this.getDisplayNames(field, style, locale);
        if (displayNames.containsKey(key)) {
            this.set(field, displayNames.get(key).intValue());
        } else {
            // TODO log
        }
        return this;
    }

    @Override
    public UraCalendar setDST(int offset) {
        this.set(UraCalendar.DST_OFFSET, offset);
        return this;
    }

    @Override
    public UraCalendar setDWeek(int day) {
        return setDayOfWeek(day);
    }

    @Override
    public UraCalendar setDWeekInMonth(int day) {
        return setDayOfWeekInMonth(day);
    }

    @Override
    public UraCalendar setDYear(int day) {
        return setDayOfYear(day);
    }

    @Override
    public UraCalendar setEra(int era) {
        this.set(UraCalendar.ERA, era);
        return this;
    }

    @Override
    public UraCalendar setEraDispName(String name) {
        return setEraDispName(name, locale);
    }

    @Override
    public UraCalendar setEraDispName(String name, Locale locale) {
        return this.setDisplayName(name, UraCalendar.ERA, UraCalendar.ALL_STYLES, locale);
    }

    @Override
    public UraCalendar setHours(int hours) {
        this.set(UraCalendar.HOUR, hours);
        return this;
    }

    @Override
    public UraCalendar setHoursOfDay(int hours) {
        this.set(UraCalendar.HOUR_OF_DAY, hours);
        return this;
    }

    @Override
    public UraCalendar setLastDayOfMonth() {
        this.set(UraCalendar.DAY_OF_MONTH, 1);
        this.add(UraCalendar.MONTH, 1);
        this.add(UraCalendar.DAY_OF_MONTH, -1);
        return this;
    }

    /**
     * @param locale localeを設定します。
     */
    @Override
    public final void setLocale(final Locale locale) {
        this.locale = locale;
    }

    @Override
    public UraCalendar setLocaleEraDispName(String name) {
        return setLocaleEraDispName(name, locale);
    }

    @Override
    public UraCalendar setLocaleEraDispName(String name, Locale locale) {
        Map<String, Integer> localeEras = this.getDisplayNames(UraCalendar.LOCALE_ERA, UraCalendar.ALL_STYLES, locale);
        long[] eraFirstTimes = UraDateFormatSymbols.getCachedInstance(locale).getLocaleYear();
        if (localeEras.containsKey(name)) {
            this.setTimeInMillis(eraFirstTimes[localeEras.get(name).intValue()]);
        } else {
            // TODO
        }
        return this;
    }

    @Override
    public UraCalendar setLocaleYearDispName(String name) {
        return setLocaleYearDispName(name, locale);
    }

    @Override
    public UraCalendar setLocaleYearDispName(String name, Locale locale) {
        Map<String, Integer> localeEras = this.getDisplayNames(UraCalendar.LOCALE_ERA, UraCalendar.ALL_STYLES, locale);
        int thisLocaleEraIndex = localeEras.get(getLocaleEraDisplayName(locale)).intValue();
        long[] eraFirstTimes = UraDateFormatSymbols.getCachedInstance(locale).getLocaleYear();
        int localeYear = Integer.parseInt(name);
        this.setTimeInMillis(eraFirstTimes[thisLocaleEraIndex]);
        return this.setYear(this.getYear() + localeYear - 1);
    }

    @Override
    public UraCalendar setMilliseconds(int milliseconds) {
        this.set(UraCalendar.MILLISECOND, milliseconds);
        return this;
    }

    @Override
    public UraCalendar setMinutes(int minutes) {
        this.set(UraCalendar.MINUTE, minutes);
        return this;
    }

    @Override
    public UraCalendar setMonth(int month) {
        this.set(UraCalendar.MONTH, month);
        return this;
    }

    @Override
    public UraCalendar setMonthDispName(String name) {
        return setMonthDispName(name, locale);
    }

    @Override
    public UraCalendar setMonthDispName(String name, Locale locale) {
        return this.setDisplayName(name, UraCalendar.MONTH, UraCalendar.ALL_STYLES, locale);
    }

    @Override
    public UraCalendar setNow() {
        this.setTimeInMillis(System.currentTimeMillis());
        return this;
    }

    @Override
    public UraCalendar setPm() {
        this.set(UraCalendar.AM_PM, UraCalendar.PM);
        return this;
    }

    @Override
    public UraCalendar setSeconds(int seconds) {
        this.set(UraCalendar.SECOND, seconds);
        return this;
    }

    /**
     * 厳密設定。<br>
     * @param field
     * @param amount
     * @return インスタンス
     */
    public UraCalendar setStrict(int field, int amount) {
        int value = this.get(field);
        this.roll(field, (value - amount) * -1);
        return this;
    }

    @Override
    public UraCalendar setStrictAm() {
        return this.setStrict(UraCalendar.AM_PM, UraCalendar.AM);
    }

    @Override
    public UraCalendar setStrictDay(int day) {
        return this.setStrict(UraCalendar.DAY_OF_MONTH, day);
    }

    @Override
    public UraCalendar setStrictDayOfWeek(int day) {
        return this.setStrict(UraCalendar.DAY_OF_WEEK, day);
    }

    @Override
    public UraCalendar setStrictDayOfWeekInMonth(int day) {
        return this.setStrict(UraCalendar.DAY_OF_WEEK_IN_MONTH, day);
    }

    @Override
    public UraCalendar setStrictDayOfYear(int day) {
        return this.setStrict(UraCalendar.DAY_OF_YEAR, day);
    }

    @Override
    public UraCalendar setStrictDST(int offset) {
        return this.setStrict(UraCalendar.DST_OFFSET, offset);
    }

    @Override
    public UraCalendar setStrictDWeek(int day) {
        return setStrictDayOfWeek(day);
    }

    @Override
    public UraCalendar setStrictDWeekInMonth(int day) {
        return setStrictDayOfWeekInMonth(day);
    }

    @Override
    public UraCalendar setStrictDYear(int day) {
        return setStrictDayOfYear(day);
    }

    @Override
    public UraCalendar setStrictEra(int era) {
        return this.setStrict(UraCalendar.ERA, era);
    }

    @Override
    public UraCalendar setStrictHours(int hours) {
        return this.setStrict(UraCalendar.HOUR, hours);
    }

    @Override
    public UraCalendar setStrictHoursOfDay(int hours) {
        return this.setStrict(UraCalendar.HOUR_OF_DAY, hours);
    }

    @Override
    public UraCalendar setStrictMilliseconds(int milliseconds) {
        return this.setStrict(UraCalendar.MILLISECOND, milliseconds);
    }

    @Override
    public UraCalendar setStrictMinutes(int minutes) {
        return this.setStrict(UraCalendar.MINUTE, minutes);
    }

    @Override
    public UraCalendar setStrictMonth(int month) {
        return this.setStrict(UraCalendar.MONTH, month);
    }

    @Override
    public UraCalendar setStrictPm() {
        return this.setStrict(UraCalendar.AM_PM, UraCalendar.PM);
    }

    @Override
    public UraCalendar setStrictSeconds(int seconds) {
        return this.setStrict(UraCalendar.SECOND, seconds);
    }

    @Override
    public UraCalendar setStrictWeekOfMonth(int week) {
        return this.setStrict(UraCalendar.WEEK_OF_MONTH, week);
    }

    @Override
    public UraCalendar setStrictWeekOfYear(int week) {
        return this.setStrict(UraCalendar.WEEK_OF_YEAR, week);
    }

    @Override
    public UraCalendar setStrictWMonth(int week) {
        return setStrictWeekOfMonth(week);
    }

    @Override
    public UraCalendar setStrictWYear(int week) {
        return setStrictWeekOfYear(week);
    }

    @Override
    public UraCalendar setStrictYear(int year) {
        return this.setStrict(UraCalendar.YEAR, year);
    }

    @Override
    public UraCalendar setWeekOfMonth(int week) {
        this.set(UraCalendar.WEEK_OF_MONTH, week);
        return this;
    }

    @Override
    public UraCalendar setWeekOfYear(int week) {
        this.set(UraCalendar.WEEK_OF_YEAR, week);
        return this;
    }

    @Override
    public UraCalendar setWMonth(int week) {
        return setWeekOfMonth(week);
    }

    @Override
    public UraCalendar setWYear(int week) {
        return setWeekOfYear(week);
    }

    @Override
    public UraCalendar setYear(int year) {
        this.set(UraCalendar.YEAR, year);
        return this;
    }

    @Override
    public UraCalendar trancateDay() {
        this.setTime(DateUtils.truncate(this, UraCalendar.DAY_OF_MONTH).getTime());
        return this;
    }

    @Override
    public UraCalendar trancateHours() {
        this.setTime(DateUtils.truncate(this, UraCalendar.HOUR_OF_DAY).getTime());
        return this;
    }

    @Override
    public UraCalendar trancateMilliseconds() {
        this.setTime(DateUtils.truncate(this, UraCalendar.MILLISECOND).getTime());
        return this;
    }

    @Override
    public UraCalendar trancateMinutes() {
        this.setTime(DateUtils.truncate(this, UraCalendar.MINUTE).getTime());
        return this;
    }

    @Override
    public UraCalendar trancateMonth() {
        this.setTime(DateUtils.truncate(this, UraCalendar.MONTH).getTime());
        return this;
    }

    // final void setUnnormalized() {
    // areFieldsSet = areAllFieldsSet = false;
    // }
    @Override
    public UraCalendar trancateSeconds() {
        this.setTime(DateUtils.truncate(this, UraCalendar.SECOND).getTime());
        return this;
    }
}
