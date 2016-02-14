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
 * $Id: UraDateFormat.java$
 */
package org.uranoplums.typical.util.time;

import static org.uranoplums.typical.collection.factory.UraListFactory.*;
import static org.uranoplums.typical.collection.factory.UraMapFactory.*;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.lang.ref.SoftReference;
import java.text.FieldPosition;
import java.text.Format;
import java.text.ParsePosition;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.SortedMap;
import java.util.TimeZone;
import java.util.concurrent.ConcurrentMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.uranoplums.typical.collection.UraTuple;
import org.uranoplums.typical.collection.factory.UraMapFactory.FACTOR;
import org.uranoplums.typical.exception.UraParseRuntimeException;
import org.uranoplums.typical.lang.UraObjectCommoner;
import org.uranoplums.typical.lang.builder.UraMultiLineToStringStyle;
import org.uranoplums.typical.lang.builder.UraToStringBuilder;
import org.uranoplums.typical.log.UraLogger;
import org.uranoplums.typical.log.UraLoggerFactory;
import org.uranoplums.typical.util.UraClassUtils;
import org.uranoplums.typical.util.UraStringUtils;

/**
 * UraLightDateFormatクラス。<br>
 *
 * @since 2015/05/07
 * @author syany
 */
public class UraDateFormat extends Format implements UraObjectCommoner {

    /**  */
    protected static final UraLogger<String> LOGGER = UraLoggerFactory.getUraStringCodeLog();
    /**  */
    private static final Pattern BASE_ESC_SQ = Pattern.compile("(\\(|\\)|\\[|\\]|\\-)", Pattern.DOTALL | Pattern.MULTILINE);
    /**  */
    private static final Pattern BASE_Q = Pattern.compile("'(.*)'", Pattern.DOTALL | Pattern.MULTILINE);
    /**  */
    private static final Pattern BASE_BQ = Pattern.compile("''", Pattern.DOTALL | Pattern.MULTILINE);
    /**  */
    private static final String QUOTE = "'";
    /**  */
    private static final String BASE_DATE_FORMAT_PATTERN = "'(?:[^']|'')*'|a|dd?|DD?D?|EE?|F|g|G|hh?|HH?|kk?|KK?|l|LL?|mm?|MM?M?M?|NN?|ss?|SSS|S|u|U|ww?|WW?|XX?X?|yyyy?|yy?|zz?z?z?|ZZ?";
    /**  */
    private static final ConcurrentMap<String, SoftReference<UraDateFormat>> CACHED_INSTANCES;
    /**  */
    private static final ConcurrentMap<Locale, SoftReference<Map<Integer, Pattern>>> CACHED_DISPNME;
    /**  */
    private static final ConcurrentMap<Locale, SoftReference<SortedMap<String, TimeZone>>> CACHED_TIMEZONE;
    /**  */
    private static final char PADDING_NUMBER = '0';
    /**  */
    private static final Map<String, UraDateFormat.PrinterField> PRINTER_FORMAT_MAP;
    /**  */
    private static final Map<String, UraDateFormat.ParserField> PARSER_FORMAT_MAP;
    /**  */
    private static final long serialVersionUID = 8810653824756567612L;
    /**  */
    public static final PrinterField NO_CHANGE_FIELD = new PrinterField() {

        @Override
        public String getFormatField(String pattern, UraCalendar cal, Locale locale) {
            Matcher m1 = BASE_Q.matcher(pattern);
            if (m1.find()) {
                pattern = m1.replaceFirst("$1");
            }
            Matcher m2 = BASE_BQ.matcher(pattern);
            if (m2.find()) {
                pattern = m2.replaceAll(QUOTE);
            }
            return pattern;
        }
    };
    /**  */
    private final List<UraTuple<String, UraDateFormat.PrinterField>> formatPatternList = newArrayList(10);
    /**  */
    private final List<UraTuple<String, UraDateFormat.ParserField>> parserPatternList = newArrayList(10);
    /**  */
    protected Pattern dateFormatPattern;
    /**  */
    protected Locale locale;
    /** 追加用フォーマット */
    protected Map<String, UraDateFormat.PrinterField> optionalFormatMap = newHashMap(4, FACTOR.GREATER);
    /** 追加用パーサ */
    protected Map<String, UraDateFormat.ParserField> optionalParserMap = newHashMap(4, FACTOR.GREATER);
    /**  */
    protected UraCalendar defaultParsedCalendar;

    /**
     * @param defaultParsedCalendar defaultParsedCalendarを設定します。
     * @return
     */
    public final UraDateFormat setDefaultParsedCalendar(UraCalendar defaultParsedCalendar) {
        this.defaultParsedCalendar = defaultParsedCalendar;
        return this;
    }

    {
        defaultParsedCalendar = UraCalendarUtils.newUraCalendar();
        defaultParsedCalendar.clear();
    }
    static {
        //
        CACHED_INSTANCES = newConcurrentHashMap(3);
        CACHED_DISPNME = newConcurrentHashMap(3);
        CACHED_TIMEZONE = newConcurrentHashMap(3);
        //
        PRINTER_FORMAT_MAP = newHashMap(40, FACTOR.GREATER);
        PARSER_FORMAT_MAP = newHashMap(40, FACTOR.GREATER);
        //
        PRINTER_FORMAT_MAP.put("a", new UraDateFormat.PrinterField() {

            @Override
            public String getFormatField(String pattern, UraCalendar cal, Locale locale) {
                return cal.getAmPmDisplayName(locale);
            }
        });
        PRINTER_FORMAT_MAP.put("d", new UraDateFormat.PrinterField() {

            @Override
            public String getFormatField(String pattern, UraCalendar cal, Locale locale) {
                return String.valueOf(cal.getDayOfMonth());
            }
        });
        PRINTER_FORMAT_MAP.put("dd", new UraDateFormat.PrinterField() {

            @Override
            public String getFormatField(String pattern, UraCalendar cal, Locale locale) {
                return UraStringUtils.leftPad(String.valueOf(cal.getDayOfMonth()/* % 100 */), 2, PADDING_NUMBER);
            }
        });
        PRINTER_FORMAT_MAP.put("D", new UraDateFormat.PrinterField() {

            @Override
            public String getFormatField(String pattern, UraCalendar cal, Locale locale) {
                return String.valueOf(cal.getDayOfYear());
            }
        });
        PRINTER_FORMAT_MAP.put("DD", new UraDateFormat.PrinterField() {

            @Override
            public String getFormatField(String pattern, UraCalendar cal, Locale locale) {
                return String.valueOf(cal.getDayOfYear());
            }
        });
        PRINTER_FORMAT_MAP.put("DDD", new UraDateFormat.PrinterField() {

            @Override
            public String getFormatField(String pattern, UraCalendar cal, Locale locale) {
                return UraStringUtils.leftPad(String.valueOf(cal.getDayOfYear()/* % 1000 */), 3, PADDING_NUMBER);
            }
        });
        PRINTER_FORMAT_MAP.put("E", new UraDateFormat.PrinterField() {

            @Override
            public String getFormatField(String pattern, UraCalendar cal, Locale locale) {
                return cal.getDayOfWeekDisplayName(UraCalendar.SHORT, locale);
            }
        });
        PRINTER_FORMAT_MAP.put("EE", new UraDateFormat.PrinterField() {

            @Override
            public String getFormatField(String pattern, UraCalendar cal, Locale locale) {
                return cal.getDayOfWeekDisplayName(UraCalendar.LONG, locale);
            }
        });
        PRINTER_FORMAT_MAP.put("F", new UraDateFormat.PrinterField() {

            @Override
            public String getFormatField(String pattern, UraCalendar cal, Locale locale) {
                return String.valueOf(cal.getDayOfWeekInMonth());
            }
        });
        PRINTER_FORMAT_MAP.put("g", new UraDateFormat.PrinterField() {

            @Override
            public String getFormatField(String pattern, UraCalendar cal, Locale locale) {
                return cal.getLocaleEraDisplayName(locale);
            }
        });
        PRINTER_FORMAT_MAP.put("G", new UraDateFormat.PrinterField() {

            @Override
            public String getFormatField(String pattern, UraCalendar cal, Locale locale) {
                return cal.getEraDisplayName(locale);
            }
        });
        PRINTER_FORMAT_MAP.put("h", new UraDateFormat.PrinterField() {

            @Override
            public String getFormatField(String pattern, UraCalendar cal, Locale locale) {
                int value = cal.getHours();
                if (value == 0) {
                    return String.valueOf(cal.getLeastMaximum(UraCalendar.HOUR) + 1);
                }
                return String.valueOf(value);
            }
        });
        PRINTER_FORMAT_MAP.put("hh", new UraDateFormat.PrinterField() {

            @Override
            public String getFormatField(String pattern, UraCalendar cal, Locale locale) {
                int value = cal.getHours();
                if (value == 0) {
                    return UraStringUtils.leftPad(String.valueOf((cal.getLeastMaximum(UraCalendar.HOUR) + 1)/* % 100 */), 2, PADDING_NUMBER);
                }
                return UraStringUtils.leftPad(String.valueOf(value/* % 100 */), 2, PADDING_NUMBER);
            }
        });
        PRINTER_FORMAT_MAP.put("H", new UraDateFormat.PrinterField() {

            @Override
            public String getFormatField(String pattern, UraCalendar cal, Locale locale) {
                return String.valueOf(cal.getHoursOfDay());
            }
        });
        PRINTER_FORMAT_MAP.put("HH", new UraDateFormat.PrinterField() {

            @Override
            public String getFormatField(String pattern, UraCalendar cal, Locale locale) {
                return UraStringUtils.leftPad(String.valueOf(cal.getHoursOfDay()/* % 100 */), 2, PADDING_NUMBER);
            }
        });
        PRINTER_FORMAT_MAP.put("k", new UraDateFormat.PrinterField() {

            @Override
            public String getFormatField(String pattern, UraCalendar cal, Locale locale) {
                int value = cal.getHoursOfDay();
                if (value == 0) {
                    return String.valueOf(cal.getMaximum(UraCalendar.HOUR_OF_DAY) + 1);
                }
                return String.valueOf(value);
            }
        });
        PRINTER_FORMAT_MAP.put("kk", new UraDateFormat.PrinterField() {

            @Override
            public String getFormatField(String pattern, UraCalendar cal, Locale locale) {
                int value = cal.getHoursOfDay();
                if (value == 0) {
                    return UraStringUtils.leftPad(String.valueOf((cal.getMaximum(UraCalendar.HOUR_OF_DAY) + 1)/* % 100 */), 2, PADDING_NUMBER);
                }
                return UraStringUtils.leftPad(String.valueOf(value/* % 100 */), 2, PADDING_NUMBER);
            }
        });
        PRINTER_FORMAT_MAP.put("K", new UraDateFormat.PrinterField() {

            @Override
            public String getFormatField(String pattern, UraCalendar cal, Locale locale) {
                return String.valueOf(cal.getHours());
            }
        });
        PRINTER_FORMAT_MAP.put("KK", new UraDateFormat.PrinterField() {

            @Override
            public String getFormatField(String pattern, UraCalendar cal, Locale locale) {
                return UraStringUtils.leftPad(String.valueOf(cal.getHours()/* % 100 */), 2, PADDING_NUMBER);
            }
        });
        PRINTER_FORMAT_MAP.put("l", new UraDateFormat.PrinterField() {

            @Override
            public String getFormatField(String pattern, UraCalendar cal, Locale locale) {
                // 1970-01-01T00:00:00.000 からの秒数
                return String.valueOf(cal.getTimeInMillis());
            }
        });
        PRINTER_FORMAT_MAP.put("L", new UraDateFormat.PrinterField() {

            @Override
            public String getFormatField(String pattern, UraCalendar cal, Locale locale) {
                // 1970-01-01T00:00:00.000 からの秒数
                return String.valueOf(cal.getTimeInMillis() / 1000);
            }
        });
        PRINTER_FORMAT_MAP.put("LL", new UraDateFormat.PrinterField() {

            @Override
            public String getFormatField(String pattern, UraCalendar cal, Locale locale) {
                // 1970-01-01T00:00:00.000 からの秒数
                long uTime = cal.getTimeInMillis() / 1000;
                if (uTime >= 0L) {
                    return UraStringUtils.leftPad(String.valueOf(uTime/* % 10000000000L */), 10, PADDING_NUMBER);
                }
                return "-" + UraStringUtils.leftPad(String.valueOf((uTime * -1L)/* % 10000000000L */), 10, PADDING_NUMBER);
            }
        });
        PRINTER_FORMAT_MAP.put("m", new UraDateFormat.PrinterField() {

            @Override
            public String getFormatField(String pattern, UraCalendar cal, Locale locale) {
                return String.valueOf(cal.getMinutes());
            }
        });
        PRINTER_FORMAT_MAP.put("mm", new UraDateFormat.PrinterField() {

            @Override
            public String getFormatField(String pattern, UraCalendar cal, Locale locale) {
                return UraStringUtils.leftPad(String.valueOf(cal.getMinutes()/* % 100 */), 2, PADDING_NUMBER);
            }
        });
        PRINTER_FORMAT_MAP.put("M", new UraDateFormat.PrinterField() {

            @Override
            public String getFormatField(String pattern, UraCalendar cal, Locale locale) {
                return String.valueOf(cal.getMonth() + 1);
            }
        });
        PRINTER_FORMAT_MAP.put("MM", new UraDateFormat.PrinterField() {

            @Override
            public String getFormatField(String pattern, UraCalendar cal, Locale locale) {
                return UraStringUtils.leftPad(String.valueOf((cal.getMonth() + 1)/* % 100 */), 2, PADDING_NUMBER);
            }
        });
        PRINTER_FORMAT_MAP.put("MMM", new UraDateFormat.PrinterField() {

            @Override
            public String getFormatField(String pattern, UraCalendar cal, Locale locale) {
                return cal.getMonthDisplayName(UraCalendar.SHORT, locale);
            }
        });
        PRINTER_FORMAT_MAP.put("MMMM", new UraDateFormat.PrinterField() {

            @Override
            public String getFormatField(String pattern, UraCalendar cal, Locale locale) {
                return cal.getMonthDisplayName(UraCalendar.LONG, locale);
            }
        });
        PRINTER_FORMAT_MAP.put("N", new UraDateFormat.PrinterField() {

            @Override
            public String getFormatField(String pattern, UraCalendar cal, Locale locale) {
                return cal.getLocaleYearDisplayName(locale);
            }
        });
        PRINTER_FORMAT_MAP.put("NN", new UraDateFormat.PrinterField() {

            @Override
            public String getFormatField(String pattern, UraCalendar cal, Locale locale) {
                return UraStringUtils.leftPad(cal.getLocaleYearDisplayName(locale), 2, PADDING_NUMBER);
            }
        });
        PRINTER_FORMAT_MAP.put("s", new UraDateFormat.PrinterField() {

            @Override
            public String getFormatField(String pattern, UraCalendar cal, Locale locale) {
                return String.valueOf(cal.getSeconds());
            }
        });
        PRINTER_FORMAT_MAP.put("ss", new UraDateFormat.PrinterField() {

            @Override
            public String getFormatField(String pattern, UraCalendar cal, Locale locale) {
                return UraStringUtils.leftPad(String.valueOf(cal.getSeconds()/* % 100 */), 2, PADDING_NUMBER);
            }
        });
        PRINTER_FORMAT_MAP.put("S", new UraDateFormat.PrinterField() {

            @Override
            public String getFormatField(String pattern, UraCalendar cal, Locale locale) {
                return String.valueOf(cal.getMilliseconds());
            }
        });
        PRINTER_FORMAT_MAP.put("SSS", new UraDateFormat.PrinterField() {

            @Override
            public String getFormatField(String pattern, UraCalendar cal, Locale locale) {
                return UraStringUtils.leftPad(String.valueOf(cal.getMilliseconds() % 1000), 3, PADDING_NUMBER);
            }
        });
        PRINTER_FORMAT_MAP.put("u", new UraDateFormat.PrinterField() {

            @Override
            public String getFormatField(String pattern, UraCalendar cal, Locale locale) {
                return String.valueOf(cal.getWDay());
            }
        });
        PRINTER_FORMAT_MAP.put("U", new UraDateFormat.PrinterField() {

            @Override
            public String getFormatField(String pattern, UraCalendar cal, Locale locale) {
                int value = cal.getWDay();
                // 日曜:0
                if (value == cal.getMaximum(UraCalendar.DAY_OF_WEEK)) {
                    return String.valueOf(0);
                }
                return String.valueOf(value);
            }
        });
        PRINTER_FORMAT_MAP.put("w", new UraDateFormat.PrinterField() {

            @Override
            public String getFormatField(String pattern, UraCalendar cal, Locale locale) {
                return String.valueOf(cal.getWeekOfYear());
            }
        });
        PRINTER_FORMAT_MAP.put("ww", new UraDateFormat.PrinterField() {

            @Override
            public String getFormatField(String pattern, UraCalendar cal, Locale locale) {
                return UraStringUtils.leftPad(String.valueOf(cal.getWeekOfYear()/* % 100 */), 2, PADDING_NUMBER);
            }
        });
        PRINTER_FORMAT_MAP.put("W", new UraDateFormat.PrinterField() {

            @Override
            public String getFormatField(String pattern, UraCalendar cal, Locale locale) {
                return String.valueOf(cal.getWeekOfMonth());
            }
        });
        PRINTER_FORMAT_MAP.put("WW", new UraDateFormat.PrinterField() {

            @Override
            public String getFormatField(String pattern, UraCalendar cal, Locale locale) {
                return UraStringUtils.leftPad(String.valueOf(cal.getWeekOfMonth()/* % 100 */), 2, PADDING_NUMBER);
            }
        });
        PRINTER_FORMAT_MAP.put("X", new UraDateFormat.PrinterField() {

            @Override
            public String getFormatField(String pattern, UraCalendar cal, Locale locale) {
                int offset = cal.getTimezoneOffset() / 100;
                if (offset >= 0) {
                    return "+" + UraStringUtils.leftPad(String.valueOf(offset % 100), 2, PADDING_NUMBER);
                }
                return "-" + UraStringUtils.leftPad(String.valueOf((offset * -1) % 100), 2, PADDING_NUMBER);
            }
        });
        PRINTER_FORMAT_MAP.put("XX", new UraDateFormat.PrinterField() {

            @Override
            public String getFormatField(String pattern, UraCalendar cal, Locale locale) {
                int offset = cal.getTimezoneOffset();
                if (offset >= 0) {
                    return "+" + UraStringUtils.leftPad(String.valueOf(offset % 10000), 4, PADDING_NUMBER);
                }
                return "-" + UraStringUtils.leftPad(String.valueOf((offset * -1) % 10000), 4, PADDING_NUMBER);
            }
        });
        PRINTER_FORMAT_MAP.put("XXX", new UraDateFormat.PrinterField() {

            @Override
            public String getFormatField(String pattern, UraCalendar cal, Locale locale) {
                int offset = cal.getTimezoneOffset() / 100;
                int dstOffset = cal.getTimezoneOffset() % 100;
                if (offset >= 0) {
                    return "+" + UraStringUtils.leftPad(String.valueOf(offset % 100), 2, PADDING_NUMBER)
                            + ":" + UraStringUtils.leftPad(String.valueOf(dstOffset % 100), 2, PADDING_NUMBER);
                }
                return "-" + UraStringUtils.leftPad(String.valueOf((offset * -1) % 100), 2, PADDING_NUMBER)
                        + ":" + UraStringUtils.leftPad(String.valueOf(dstOffset % 100), 2, PADDING_NUMBER);
            }
        });
        PRINTER_FORMAT_MAP.put("y", new UraDateFormat.PrinterField() {

            @Override
            public String getFormatField(String pattern, UraCalendar cal, Locale locale) {
                return String.valueOf(cal.getYear());
            }
        });
        PRINTER_FORMAT_MAP.put("yy", new UraDateFormat.PrinterField() {

            @Override
            public String getFormatField(String pattern, UraCalendar cal, Locale locale) {
                return UraStringUtils.leftPad(String.valueOf(cal.getYear() % 100), 2, PADDING_NUMBER);
            }
        });
        PRINTER_FORMAT_MAP.put("yyyy", new UraDateFormat.PrinterField() {

            @Override
            public String getFormatField(String pattern, UraCalendar cal, Locale locale) {
                return UraStringUtils.leftPad(String.valueOf(cal.getYear() % 10000), 4, PADDING_NUMBER);
            }
        });
        PRINTER_FORMAT_MAP.put("z", new UraDateFormat.PrinterField() {

            @Override
            public String getFormatField(String pattern, UraCalendar cal, Locale locale) {
                boolean daylight = (cal.getDST() != 0);
                return cal.getTimeZone().getDisplayName(daylight, TimeZone.SHORT, locale);
            }
        });
        PRINTER_FORMAT_MAP.put("zz", new UraDateFormat.PrinterField() {

            @Override
            public String getFormatField(String pattern, UraCalendar cal, Locale locale) {
                boolean daylight = (cal.getDST() != 0);
                return cal.getTimeZone().getDisplayName(daylight, TimeZone.SHORT, locale);
            }
        });
        PRINTER_FORMAT_MAP.put("zzz", new UraDateFormat.PrinterField() {

            @Override
            public String getFormatField(String pattern, UraCalendar cal, Locale locale) {
                boolean daylight = (cal.getDST() != 0);
                return cal.getTimeZone().getDisplayName(daylight, TimeZone.SHORT, locale);
            }
        });
        PRINTER_FORMAT_MAP.put("zzzz", new UraDateFormat.PrinterField() {

            @Override
            public String getFormatField(String pattern, UraCalendar cal, Locale locale) {
                boolean daylight = (cal.getDST() != 0);
                return cal.getTimeZone().getDisplayName(daylight, TimeZone.LONG, locale);
            }
        });
        PRINTER_FORMAT_MAP.put("Z", new UraDateFormat.PrinterField() {

            @Override
            public String getFormatField(String pattern, UraCalendar cal, Locale locale) {
                int offset = cal.getTimezoneOffset();
                if (offset >= 0) {
                    return "+" + UraStringUtils.leftPad(String.valueOf(offset % 10000), 4, PADDING_NUMBER);
                }
                return "-" + UraStringUtils.leftPad(String.valueOf((offset * -1) % 10000), 4, PADDING_NUMBER);
            }
        });
        PRINTER_FORMAT_MAP.put("ZZ", new UraDateFormat.PrinterField() {

            @Override
            public String getFormatField(String pattern, UraCalendar cal, Locale locale) {
                int offset = cal.getTimezoneOffset() / 100;
                int dstOffset = cal.getTimezoneOffset() % 100;
                if (offset >= 0) {
                    return "+" + UraStringUtils.leftPad(String.valueOf(offset % 100), 2, PADDING_NUMBER)
                            + ":" + UraStringUtils.leftPad(String.valueOf(dstOffset % 100), 2, PADDING_NUMBER);
                }
                return "-" + UraStringUtils.leftPad(String.valueOf((offset * -1) % 100), 2, PADDING_NUMBER)
                        + ":" + UraStringUtils.leftPad(String.valueOf(dstOffset % 100), 2, PADDING_NUMBER);
            }
        });
        /*
         *
         */
        PARSER_FORMAT_MAP.put("a", new UraDateFormat.TextParserField(UraCalendar.AM_PM, "a"));
        PARSER_FORMAT_MAP.put("d", new UraDateFormat.NumberParserField(UraCalendar.DAY_OF_MONTH, "d"));
        PARSER_FORMAT_MAP.put("dd", new UraDateFormat.NumberParserField(UraCalendar.DAY_OF_MONTH, "dd"));
        PARSER_FORMAT_MAP.put("D", new UraDateFormat.NumberParserField(UraCalendar.DAY_OF_YEAR, "D"));
        PARSER_FORMAT_MAP.put("DD", new UraDateFormat.NumberParserField(UraCalendar.DAY_OF_YEAR, "DD"));
        PARSER_FORMAT_MAP.put("DDD", new UraDateFormat.NumberParserField(UraCalendar.DAY_OF_YEAR, "DDD"));
        PARSER_FORMAT_MAP.put("E", new UraDateFormat.TextParserField(UraCalendar.DAY_OF_WEEK, "E"));
        PARSER_FORMAT_MAP.put("EE", new UraDateFormat.TextParserField(UraCalendar.DAY_OF_WEEK, "EE"));
        PARSER_FORMAT_MAP.put("F", new UraDateFormat.NumberParserField(UraCalendar.DAY_OF_WEEK_IN_MONTH, "F"));
        PARSER_FORMAT_MAP.put("g", new UraDateFormat.TextParserField(UraCalendar.LOCALE_ERA, "g") {

            @Override
            protected void setCalendar(UraCalendar cal, String val, Locale locale) {
                cal.setLocaleEraDispName(val, locale);
            }
        });
        PARSER_FORMAT_MAP.put("G", new UraDateFormat.TextParserField(UraCalendar.ERA, "G"));
        PARSER_FORMAT_MAP.put("h", new UraDateFormat.HourParserField(UraCalendar.HOUR, "h"));
        PARSER_FORMAT_MAP.put("hh", new UraDateFormat.HourParserField(UraCalendar.HOUR, "hh"));
        PARSER_FORMAT_MAP.put("H", new UraDateFormat.NumberParserField(UraCalendar.HOUR_OF_DAY, "H"));
        PARSER_FORMAT_MAP.put("HH", new UraDateFormat.NumberParserField(UraCalendar.HOUR_OF_DAY, "HH"));
        PARSER_FORMAT_MAP.put("k", new UraDateFormat.HourParserField(UraCalendar.HOUR_OF_DAY, "k"));
        PARSER_FORMAT_MAP.put("kk", new UraDateFormat.HourParserField(UraCalendar.HOUR_OF_DAY, "kk"));
        PARSER_FORMAT_MAP.put("K", new UraDateFormat.NumberParserField(UraCalendar.HOUR, "K"));
        PARSER_FORMAT_MAP.put("KK", new UraDateFormat.NumberParserField(UraCalendar.HOUR, "KK"));
        PARSER_FORMAT_MAP.put("l", new UraDateFormat.NumberParserField(-1, "lllllllllllll") {

            @Override
            protected void setCalendar(UraCalendar cal, String val, Locale locale) {
                cal.setTimeInMillis(Long.parseLong(val));
            }
        });
        PARSER_FORMAT_MAP.put("L", new UraDateFormat.NumberParserField(-1, "L") {

            @Override
            protected void setCalendar(UraCalendar cal, String val, Locale locale) {
                cal.setTimeInMillis(Long.parseLong(val) * 1000);
            }
        });
        PARSER_FORMAT_MAP.put("LL", new UraDateFormat.NumberParserField(-1, "LLLLLLLLLL") {

            @Override
            protected void setCalendar(UraCalendar cal, String val, Locale locale) {
                cal.setTimeInMillis(Long.parseLong(val) * 1000);
            }
        });
        PARSER_FORMAT_MAP.put("m", new UraDateFormat.NumberParserField(UraCalendar.MINUTE, "m"));
        PARSER_FORMAT_MAP.put("mm", new UraDateFormat.NumberParserField(UraCalendar.MINUTE, "mm"));
        PARSER_FORMAT_MAP.put("M", new UraDateFormat.NumberParserField(UraCalendar.MONTH, "M") {

            @Override
            protected int modify(int iValue) {
                return iValue - 1;
            }
        });
        PARSER_FORMAT_MAP.put("MM", new UraDateFormat.NumberParserField(UraCalendar.MONTH, "MM") {

            @Override
            protected int modify(int iValue) {
                return iValue - 1;
            }
        });
        PARSER_FORMAT_MAP.put("MMM", new UraDateFormat.TextParserField(UraCalendar.MONTH, "MMM"));
        PARSER_FORMAT_MAP.put("MMMM", new UraDateFormat.TextParserField(UraCalendar.MONTH, "MMMM"));
        PARSER_FORMAT_MAP.put("N", new UraDateFormat.NumberParserField(UraCalendar.LOCALE_YEAR, "N") {

            @Override
            protected void setCalendar(UraCalendar cal, String val, Locale locale) {
                cal.setLocaleYearDispName(val, locale);
            }
        });
        PARSER_FORMAT_MAP.put("NN", new UraDateFormat.NumberParserField(UraCalendar.LOCALE_YEAR, "NN") {

            @Override
            protected void setCalendar(UraCalendar cal, String val, Locale locale) {
                cal.setLocaleYearDispName(val, locale);
            }
        });
        PARSER_FORMAT_MAP.put("s", new UraDateFormat.NumberParserField(UraCalendar.SECOND, "s"));
        PARSER_FORMAT_MAP.put("ss", new UraDateFormat.NumberParserField(UraCalendar.SECOND, "ss"));
        PARSER_FORMAT_MAP.put("S", new UraDateFormat.NumberParserField(UraCalendar.MILLISECOND, "S"));
        PARSER_FORMAT_MAP.put("SSS", new UraDateFormat.NumberParserField(UraCalendar.MILLISECOND, "SSS"));
        PARSER_FORMAT_MAP.put("u", new UraDateFormat.NumberParserField(UraCalendar.DAY_OF_WEEK, "u"));
        PARSER_FORMAT_MAP.put("U", new UraDateFormat.NumberParserField(UraCalendar.DAY_OF_WEEK, "U") {

            @Override
            protected void setCalendar(UraCalendar cal, String val, Locale locale) {
                int maxValue = cal.getMaximum(UraCalendar.DAY_OF_WEEK);
                int valInt = Integer.parseInt(val);
                if (valInt == 0) {
                    cal.set(field, maxValue);
                } else {
                    cal.set(field, valInt);
                }
            }
        });
        PARSER_FORMAT_MAP.put("w", new UraDateFormat.NumberParserField(UraCalendar.WEEK_OF_YEAR, "w"));
        PARSER_FORMAT_MAP.put("ww", new UraDateFormat.NumberParserField(UraCalendar.WEEK_OF_YEAR, "ww"));
        PARSER_FORMAT_MAP.put("W", new UraDateFormat.NumberParserField(UraCalendar.WEEK_OF_MONTH, "W"));
        PARSER_FORMAT_MAP.put("WW", new UraDateFormat.NumberParserField(UraCalendar.WEEK_OF_MONTH, "WW"));
        PARSER_FORMAT_MAP.put("X", new UraDateFormat.TimeZoneParserField(UraCalendar.ZONE_OFFSET, "X"));
        PARSER_FORMAT_MAP.put("XX", new UraDateFormat.TimeZoneParserField(UraCalendar.ZONE_OFFSET, "XX"));
        PARSER_FORMAT_MAP.put("XXX", new UraDateFormat.TimeZoneParserField(UraCalendar.ZONE_OFFSET, "XXX"));
        PARSER_FORMAT_MAP.put("y", new UraDateFormat.NumberParserField(UraCalendar.YEAR, "y") {

            @Override
            protected void setCalendar(UraCalendar cal, String val, Locale locale) {
                int year = Integer.parseInt(val);
                if (year > 999) {
                    cal.setYear(year);
                } else if (year > 99) {
                    cal.setYear(((cal.getYear() / 1000) * 1000) + year);
                } else if (year > 9) {
                    cal.setYear(((cal.getYear() / 100) * 100) + year);
                } else {
                    cal.setYear(((cal.getYear() / 10) * 10) + year);
                }
            }
        });
        PARSER_FORMAT_MAP.put("yy", new UraDateFormat.NumberParserField(UraCalendar.YEAR, "yy") {

            @Override
            protected void setCalendar(UraCalendar cal, String val, Locale locale) {
                int year = Integer.parseInt(val);
                if (year > 999) {
                    cal.setYear(year);
                } else if (year > 99) {
                    cal.setYear(((cal.getYear() / 1000) * 1000) + year);
                } else if (year > 9) {
                    cal.setYear(((cal.getYear() / 100) * 100) + year);
                } else {
                    cal.setYear(((cal.getYear() / 10) * 10) + year);
                }
            }
        });
        PARSER_FORMAT_MAP.put("yyyy", new UraDateFormat.NumberParserField(UraCalendar.YEAR, "yyyy") {
            //
            // @Override
            // protected void setCalendar(UraCalendar cal, String val, Locale locale) {
            // int year = Integer.parseInt(val);
            // if (year > 999) {
            // cal.setYear(year);
            // } else if (year > 99) {
            // cal.setYear(((cal.getYear() / 1000) * 1000) + year);
            // } else if (year > 9) {
            // cal.setYear(((cal.getYear() / 100) * 100) + year);
            // } else {
            // cal.setYear(((cal.getYear() / 10) * 10) + year);
            // }
            // }
        });
        PARSER_FORMAT_MAP.put("Z", new UraDateFormat.TimeZoneParserField(UraCalendar.ZONE_OFFSET, "Z"));
        PARSER_FORMAT_MAP.put("ZZ", new UraDateFormat.TimeZoneParserField(UraCalendar.ZONE_OFFSET, "ZZ"));
        PARSER_FORMAT_MAP.put("z", new UraDateFormat.TimeZoneParserField(UraCalendar.ZONE_OFFSET, "z"));
        PARSER_FORMAT_MAP.put("zz", new UraDateFormat.TimeZoneParserField(UraCalendar.ZONE_OFFSET, "zz"));
        PARSER_FORMAT_MAP.put("zzz", new UraDateFormat.TimeZoneParserField(UraCalendar.ZONE_OFFSET, "zzz"));
        PARSER_FORMAT_MAP.put("zzzz", new UraDateFormat.TimeZoneParserField(UraCalendar.ZONE_OFFSET, "zzzz"));
    }

    /**
     * デフォルトコンストラクタ。<br>
     */
    public UraDateFormat() {
        this(UraStringUtils.EMPTY, Locale.getDefault());
    }

    /**
     * デフォルトコンストラクタ。<br>
     * @param formatPattern
     */
    public UraDateFormat(String formatPattern) {
        this(formatPattern, Locale.getDefault());
    }

    /**
     * デフォルトコンストラクタ。<br>
     * @param formatPattern
     * @param locale
     */
    public UraDateFormat(String formatPattern, Locale locale) {

        String formatPatternLocale = formatPattern + "@@@" + locale.getDisplayName();
        // Copy values of a cached instance if any.
        SoftReference<UraDateFormat> ref = CACHED_INSTANCES.get(formatPatternLocale);
        UraDateFormat udf;
        if (ref != null && (udf = ref.get()) != null) {
            copyMembers(udf, this);
            return;
        }
        initialize();
        // フォーマットパターンの確立
        List<String> optionalPatternList = newArrayList(3);
        visitOptionalPattern(optionalPatternList);
        if (optionalPatternList.size() > 0) {
            dateFormatPattern = Pattern.compile(BASE_DATE_FORMAT_PATTERN + "|" + UraStringUtils.join(optionalPatternList, "|"), Pattern.DOTALL
                    | Pattern.MULTILINE);
        } else {
            dateFormatPattern = Pattern.compile(BASE_DATE_FORMAT_PATTERN, Pattern.DOTALL | Pattern.MULTILINE);
        }
        this.setFormatPattern(formatPattern);
        this.setLocale(locale);
    }

    /**
     * 。<br>
     * @param regex
     * @param value
     * @param unquote
     * @return
     */
    private static StringBuilder escapeRegex(final StringBuilder regex, final String value, final boolean unquote) {
        regex.append("\\Q");
        for (int i = 0; i < value.length(); ++i) {
            char c = value.charAt(i);
            switch (c) {
                case '\'':
                    if (unquote) {
                        if (++i == value.length()) {
                            return regex;
                        }
                        c = value.charAt(i);
                    }
                    break;
                case '\\':
                    if (++i == value.length()) {
                        break;
                    }
                    /*
                     * If we have found \E, we replace it with \E\\E\Q, i.e. we stop the quoting,
                     * quote the \ in \E, then restart the quoting.
                     *
                     * Otherwise we just output the two characters.
                     * In each case the initial \ needs to be output and the final char is done at the end
                     */
                    regex.append(c); // we always want the original \
                    c = value.charAt(i); // Is it followed by E ?
                    if (c == 'E') { // \E detected
                        regex.append("E\\\\E\\"); // see comment above
                        c = 'Q'; // appended below
                    }
                    break;
                default:
                    break;
            }
            regex.append(c);
        }
        regex.append("\\E");
        return regex;
    }

    private static Map<String, Integer> getDisplayNames(final int field, final UraCalendar cal, final Locale locale) {
        return cal.getDisplayNames(field, UraCalendar.ALL_STYLES, locale);
    }

    /**
     * 。<br>
     * @param field
     * @param cal
     * @param locale
     * @return
     */
    protected static Map<Integer, Pattern> getCachedDispNames(int field, UraCalendar cal, Locale locale) {
        SoftReference<Map<Integer, Pattern>> ref = CACHED_DISPNME.get(locale);
        Map<Integer, Pattern> dfs = null;
        if (ref == null || (dfs = ref.get()) == null) {
            dfs = newHashMap(8);
            ref = new SoftReference<Map<Integer, Pattern>>(dfs);
            SoftReference<Map<Integer, Pattern>> x = CACHED_DISPNME.putIfAbsent(locale, ref);
            if (x != null) {
                Map<Integer, Pattern> y = x.get();
                if (y != null) {
                    dfs = y;
                } else {
                    // Replace the empty SoftReference with ref.
                    CACHED_DISPNME.put(locale, ref);
                }
            }
        }
        return dfs;
    }

    /**
     * 。<br>
     * @param formatPattern
     * @param locale
     * @return 指定日付書式の文字列 xxx
     */
    protected static UraDateFormat getCachedInstance(String formatPattern, Locale locale) {
        String formatPatternLocale = formatPattern + "@@@" + locale.getDisplayName();
        SoftReference<UraDateFormat> ref = CACHED_INSTANCES.get(formatPatternLocale);
        UraDateFormat dfs = null;
        if (ref == null || (dfs = ref.get()) == null) {
            dfs = new UraDateFormat(formatPattern, locale);
            ref = new SoftReference<UraDateFormat>(dfs);
            SoftReference<UraDateFormat> x = CACHED_INSTANCES.putIfAbsent(formatPatternLocale, ref);
            if (x != null) {
                UraDateFormat y = x.get();
                if (y != null) {
                    dfs = y;
                } else {
                    // Replace the empty SoftReference with ref.
                    CACHED_INSTANCES.put(formatPatternLocale, ref);
                }
            }
        }
        return dfs;
    }

    /**
     * 。<br>
     * @param locale
     * @return
     */
    protected static SortedMap<String, TimeZone> getCachedTimeZones(Locale locale) {
        SoftReference<SortedMap<String, TimeZone>> ref = CACHED_TIMEZONE.get(locale);
        SortedMap<String, TimeZone> tzNames = null;
        if (ref == null || (tzNames = ref.get()) == null) {
            tzNames = newTreeMap(String.CASE_INSENSITIVE_ORDER);
            ref = new SoftReference<SortedMap<String, TimeZone>>(tzNames);
            SoftReference<SortedMap<String, TimeZone>> x = CACHED_TIMEZONE.putIfAbsent(locale, ref);
            if (x != null) {
                SortedMap<String, TimeZone> y = x.get();
                if (y != null) {
                    tzNames = y;
                } else {
                    // Replace the empty SoftReference with ref.
                    CACHED_TIMEZONE.put(locale, ref);
                }
            }
        }
        return tzNames;
    }

    /**
     * 。<br>
     * @param formatPattern
     * @return 指定日付書式の文字列 xxx
     */
    public static UraDateFormat getInstance(String formatPattern) {
        return getCachedInstance(formatPattern, Locale.getDefault()).clone();
    }

    /**
     * 。<br>
     * @param formatPattern
     * @param locale
     * @return 指定日付書式の文字列 xxx
     */
    public static UraDateFormat getInstance(String formatPattern, Locale locale) {
        return getCachedInstance(formatPattern, locale).clone();
    }

    /**
     * 。<br>
     * @param formatPattern
     * @return 指定日付書式の文字列 xxx
     */
    public static UraDateFormat getInstanceRef(String formatPattern) {
        return getCachedInstance(formatPattern, Locale.getDefault());
    }

    /**
     * 。<br>
     * @param formatPattern
     * @param locale
     * @return 指定日付書式の文字列 xxx
     */
    public static UraDateFormat getInstanceRef(String formatPattern, Locale locale) {
        return getCachedInstance(formatPattern, locale);
    }

    // Serializing
    // -----------------------------------------------------------------------
    /**
     * Create the object after serialization. This implementation reinitializes the
     * transient properties.
     *
     * @param in ObjectInputStream from which the object is being deserialized.
     * @throws IOException if there is an IO issue.
     * @throws ClassNotFoundException if a class cannot be found.
     */
    private void readObject(final ObjectInputStream in) throws IOException, ClassNotFoundException {
        in.defaultReadObject();
        initialize();
    }

    /**
     * 。<br>
     * @param src
     * @param dst
     */
    protected void copyMembers(UraDateFormat src, UraDateFormat dst) {
        dst.dateFormatPattern = Pattern.compile(
                src.dateFormatPattern.pattern(), Pattern.DOTALL | Pattern.MULTILINE);
        dst.formatPatternList.addAll(src.formatPatternList);
        dst.locale = (Locale) src.locale.clone();
        dst.optionalFormatMap.putAll(src.optionalFormatMap);
        dst.optionalParserMap.putAll(src.optionalParserMap);
    }

    /**
     * Uranoplums向けStringToBuilderオブジェクトを返却します。<br>
     *
     * @param uraToStringBuilder
     * @return 指定日付書式の文字列 UraToStringBuilder
     */
    protected UraToStringBuilder editUraToStringBuilder(
            UraToStringBuilder uraToStringBuilder) {
        return uraToStringBuilder;
    }

    /**
     * 。<br>
     *
     */
    protected void initialize() {}

    /**
     * 。<br>
     * @param formatPattern
     * @return 指定日付書式の文字列 xxx
     */
    protected synchronized UraDateFormat setFormatPattern(String formatPattern) {
        Matcher m = dateFormatPattern.matcher(formatPattern);
        int pos = 0;
        formatPatternList.clear();
        parserPatternList.clear();
        while (m.find(pos)) {
            if (pos < m.start()) {
                String pattern = formatPattern.substring(pos, m.start());
                formatPatternList.add(new UraTuple<String, PrinterField>(pattern, NO_CHANGE_FIELD));
                ParserField pf = new NoParserField(pattern);
                if (parserPatternList.size() > 0) {
                    parserPatternList.get(parserPatternList.size() - 1).getValue().setNextField(pf);
                }
                parserPatternList.add(new UraTuple<String, ParserField>(pattern, pf));
            }
            String group = m.group();
            if (PRINTER_FORMAT_MAP.containsKey(group)) {
                formatPatternList.add(new UraTuple<String, PrinterField>(group, PRINTER_FORMAT_MAP.get(group)));
            } else if (optionalFormatMap.containsKey(group)) {
                // オプショナルパターンがあれば登録
                formatPatternList.add(new UraTuple<String, PrinterField>(group, optionalFormatMap.get(group)));
            } else {
                formatPatternList.add(new UraTuple<String, PrinterField>(group, NO_CHANGE_FIELD));
            }
            ParserField pf;
            if (PARSER_FORMAT_MAP.containsKey(group)) {
                pf = PARSER_FORMAT_MAP.get(group);
            } else if (optionalParserMap.containsKey(group)) {
                // オプショナルパターンがあれば登録
                pf = optionalParserMap.get(group);
            } else {
                pf = new NoParserField(group);
            }
            if (parserPatternList.size() > 0) {
                parserPatternList.get(parserPatternList.size() - 1).getValue().setNextField(pf);
            }
            parserPatternList.add(new UraTuple<String, ParserField>(group, pf));
            pos = m.end();
        }
        if (pos < formatPattern.length()) {
            String pattern = formatPattern.substring(pos);
            formatPatternList.add(new UraTuple<String, PrinterField>(pattern, NO_CHANGE_FIELD));
            ParserField pf = new NoParserField(pattern);
            if (parserPatternList.size() > 0) {
                parserPatternList.get(parserPatternList.size() - 1).getValue().setNextField(pf);
            }
            parserPatternList.add(new UraTuple<String, ParserField>(pattern, pf));
        }
        return this;
    }

    /**
     * 。<br>
     * @param calendar
     * @param toAppendTo
     * @param locale
     * @return 指定日付書式の文字列 xxx
     */
    protected StringBuffer subFormat(UraCalendar calendar, StringBuffer toAppendTo, Locale locale) {
        for (final UraTuple<String, PrinterField> fmt : this.formatPatternList) {
            toAppendTo.append(fmt.getValue().getFormatField(fmt.getKey(), calendar, locale));
        }
        return toAppendTo;
    }

    /**
     * 。<br>
     * @param source
     * @param pos
     * @param locale
     * @return
     */
    protected UraCalendar subParse(String source, ParsePosition pos, Locale locale) {
        UraCalendar parsedCalendar = UraCalendarUtils.newUraCalendar(defaultParsedCalendar.getTimeInMillis());
//        parsedCalendar.clear();
        for (final UraTuple<String, ParserField> parser : this.parserPatternList) {
            parser.getValue().setParseField(source, parsedCalendar, pos, locale);
        }
        return parsedCalendar;
    }

    /**
     * 。<br>
     * @param source
     * @param pos
     * @param locale
     * @return
     */
    protected UraCalendar subParseNoThrow(String source, ParsePosition pos, Locale locale) {
        UraCalendar cal = null;
        try {
            cal = subParse(source, pos, locale);
        } catch (UraParseRuntimeException ex) {
            LOGGER.log("TRACE: source=[{}], errorIndex=[{}], exception=[{}]", source, Integer.valueOf(pos.getErrorIndex()), ex.getMessage(), ex);
            pos.setIndex(0);
        } catch (IllegalArgumentException ex) {
            LOGGER.log("TRACE: source=[{}], errorIndex=[{}], exception=[{}]", source, Integer.valueOf(pos.getErrorIndex()), ex.getMessage(), ex);
            pos.setIndex(0);
        }
        return cal;
    }

    /**
     * 。<br>
     * @param optionalPatternList
     */
    protected void visitOptionalPattern(List<String> optionalPatternList) {}

    /**
     * このオブジェクトのクローン(Shallow copy)を作成し、返却します。
     *
     * @return 指定日付書式の文字列 シャローコピーをした自オブジェクト
     * @see java.lang.Object#clone()
     */
    @Override
    public UraDateFormat clone() {
        // superクラスでCloneNotSupportedExceptionをキャッチ
        return (UraDateFormat) super.clone();
    }

    /**
     * 本オブジェクトと引数オブジェクトが等しいか判定します。<br>
     * 比較には#hashCodeを利用します。
     *
     * @return 指定日付書式の文字列 (等価：true/不等価：false)
     * @see java.lang.Object#equals(java.lang.Object)
     */
    @Override
    public boolean equals(Object paramObject) {
        return EqualsBuilder.reflectionEquals(this, paramObject);
    }

    /**
     * 。<br>
     * @param calendar
     * @return 指定日付書式の文字列
     */
    public String format(Calendar calendar) {
        return this.format(calendar, locale);
    }

    /**
     * 。<br>
     * @param calendar
     * @param locale
     * @return 指定日付書式の文字列
     */
    public String format(Calendar calendar, Locale locale) {
        return this.format(calendar, locale, calendar.getTimeZone());
    }

    /**
     * 。<br>
     * @param calendar
     * @param locale
     * @param tz
     * @return 指定日付書式の文字列
     */
    public String format(Calendar calendar, Locale locale, TimeZone tz) {
        return this.format(calendar, new StringBuffer(), locale, tz).toString();
    }

    /**
     * 。<br>
     * @param calendar
     * @param toAppendTo
     * @return 指定日付書式の文字列
     */
    public StringBuffer format(Calendar calendar, StringBuffer toAppendTo) {
        return this.format(calendar, toAppendTo, locale, calendar.getTimeZone());
    }

    /**
     * 。<br>
     * @param calendar
     * @param toAppendTo
     * @param locale
     * @return 指定日付書式の文字列
     */
    public StringBuffer format(Calendar calendar, StringBuffer toAppendTo, Locale locale) {
        return this.format(calendar, toAppendTo, locale, calendar.getTimeZone());
    }

    /**
     * 。<br>
     * @param calendar
     * @param toAppendTo
     * @param locale
     * @param tz
     * @return 指定日付書式の文字列
     */
    public StringBuffer format(Calendar calendar, StringBuffer toAppendTo, Locale locale, TimeZone tz) {
        UraCalendar cal = UraCalendarUtils.newUraCalendar(calendar);
        cal.setTimeZone(tz);
        return this.subFormat(cal, toAppendTo, locale);
    }

    /**
     * 。<br>
     * @param date
     * @return 指定日付書式の文字列
     */
    public String format(Date date) {
        return this.format(date, locale);
    }

    /**
     * 。<br>
     * @param date
     * @param locale
     * @return 指定日付書式の文字列
     */
    public String format(Date date, Locale locale) {
        return this.format(date, locale, TimeZone.getDefault());
    }

    /**
     * 。<br>
     * @param date
     * @param locale
     * @param tz
     * @return 指定日付書式の文字列
     */
    public String format(Date date, Locale locale, TimeZone tz) {
        return this.format(date, new StringBuffer(), locale, tz).toString();
    }

    /**
     * 。<br>
     * @param date
     * @param toAppendTo
     * @return 指定日付書式の文字列
     */
    public StringBuffer format(Date date, StringBuffer toAppendTo) {
        return this.format(date, toAppendTo, locale);
    }

    /**
     * 。<br>
     * @param date
     * @param toAppendTo
     * @param locale
     * @return 指定日付書式の文字列
     */
    public StringBuffer format(Date date, StringBuffer toAppendTo, Locale locale) {
        return this.format(date, toAppendTo, locale, TimeZone.getDefault());
    }

    /**
     * 。<br>
     * @param date
     * @param toAppendTo
     * @param locale
     * @param tz
     * @return 指定日付書式の文字列 xxx
     */
    public StringBuffer format(Date date, StringBuffer toAppendTo, Locale locale, TimeZone tz) {
        UraCalendar cal = UraCalendarUtils.toUraCalendar(date);
        cal.setTimeZone(tz);
        return this.subFormat(cal, toAppendTo, locale);
    }

    /**
     * 。<br>
     * @param millis
     * @return 指定日付書式の文字列
     */
    public String format(long millis) {
        return this.format(millis, locale);
    }

    /**
     * 。<br>
     * @param millis
     * @param locale
     * @return 指定日付書式の文字列
     */
    public String format(long millis, Locale locale) {
        return this.format(millis, locale, TimeZone.getDefault());
    }

    /**
     * 。<br>
     * @param millis
     * @param locale
     * @param tz
     * @return 指定日付書式の文字列
     */
    public String format(long millis, Locale locale, TimeZone tz) {
        return this.format(millis, new StringBuffer(), locale, tz).toString();
    }

    /**
     * 。<br>
     * @param millis
     * @param toAppendTo
     * @return 指定日付書式の文字列
     */
    public StringBuffer format(long millis, StringBuffer toAppendTo) {
        return this.format(millis, toAppendTo, locale);
    }

    /**
     * 。<br>
     * @param millis
     * @param toAppendTo
     * @param locale
     * @return 指定日付書式の文字列
     */
    public StringBuffer format(long millis, StringBuffer toAppendTo, Locale locale) {
        return this.format(millis, toAppendTo, locale, TimeZone.getDefault());
    }

    /**
     * 。<br>
     * @param millis
     * @param toAppendTo
     * @param locale
     * @param tz
     * @return 指定日付書式の文字列 xxx
     */
    public StringBuffer format(long millis, StringBuffer toAppendTo, Locale locale, TimeZone tz) {
        UraCalendar cal = UraCalendarUtils.newUraCalendar();
        cal.setTimeInMillis(millis);
        cal.setTimeZone(tz);
        return this.subFormat(cal, toAppendTo, locale);
    }

    /*
     * (非 Javadoc)
     *
     * @see java.text.Format#format(java.lang.Object, java.lang.StringBuffer, java.text.FieldPosition)
     */
    @Override
    public StringBuffer format(Object obj, StringBuffer toAppendTo, FieldPosition pos) {
        // return toAppendTo;
        if (obj instanceof Date) {
            return format((Date) obj, toAppendTo, locale);
        } else if (obj instanceof UraCalendar) {
            return format((UraCalendar) obj, toAppendTo, locale);
        } else if (obj instanceof Calendar) {
            return format((Calendar) obj, toAppendTo, locale);
        } else if (obj instanceof Long) {
            return format(((Long) obj).longValue(), toAppendTo, locale);
        } else {
            LOGGER.log("ERROR: source=[{}], exception=[{}]", obj, "Unknown class: " +
                    (obj == null ? "<null>" : obj.getClass().getName()));
            throw new IllegalArgumentException("Unknown class: " +
                    (obj == null ? "<null>" : obj.getClass().getName()));
        }
    }

    /**
     * 。<br>
     * @param calendar
     * @return 指定日付書式の文字列
     */
    public String format(UraCalendar calendar) {
        return this.format(calendar, locale, calendar.getTimeZone());
    }

    /**
     * 。<br>
     * @param calendar
     * @param locale
     * @return 指定日付書式の文字列
     */
    public String format(UraCalendar calendar, Locale locale) {
        return this.format(calendar, locale, calendar.getTimeZone());
    }

    /**
     * 。<br>
     * @param calendar
     * @param locale
     * @param tz
     * @return 指定日付書式の文字列
     */
    public String format(UraCalendar calendar, Locale locale, TimeZone tz) {
        return this.format(calendar, new StringBuffer(), locale, tz).toString();
    }

    /**
     * 。<br>
     * @param calendar
     * @param toAppendTo
     * @return 指定日付書式の文字列
     */
    public StringBuffer format(UraCalendar calendar, StringBuffer toAppendTo) {
        return this.format(calendar, toAppendTo, locale, calendar.getTimeZone());
    }

    /**
     * 。<br>
     * @param calendar
     * @param toAppendTo
     * @param locale
     * @return 指定日付書式の文字列
     */
    public StringBuffer format(UraCalendar calendar, StringBuffer toAppendTo, Locale locale) {
        return this.format(calendar, toAppendTo, locale, calendar.getTimeZone());
    }

    /**
     * 。<br>
     * @param calendar
     * @param toAppendTo
     * @param locale
     * @param tz
     * @return 指定日付書式の文字列 xxx
     */
    public StringBuffer format(UraCalendar calendar, StringBuffer toAppendTo, Locale locale, TimeZone tz) {
        calendar.setTimeZone(tz);
        return this.subFormat(calendar, toAppendTo, locale);
    }

    /**
     * 本オブジェクトのハッシュ値を返却します。
     *
     * @return 指定日付書式の文字列 ハッシュコード(Jakarta Common Lang HashCodeBuilderを使用)
     * @see java.lang.Object#hashCode()
     */
    @Override
    public int hashCode() {
        return HashCodeBuilder.reflectionHashCode(this);
    }

    /**
     * 。<br>
     * @param source
     * @param args
     * @return xxx
     */
    public <E> E parse(String source, E... args) {
        Class<E> t = UraClassUtils.getArrayClass(args);
        if (UraCalendar.class.equals(t)) {
            return t.cast(parseUraCalendar(source));
        } else if (Calendar.class.equals(t)) {
            return t.cast(parseCalendar(source));
        } else if (Date.class.equals(t)) {
            return t.cast(parseDate(source));
        } else if (Long.class.equals(t) || long.class.equals(t)) {
            return t.cast(Long.valueOf(parseTimeInMillis(source)));
        } else if (Object.class.equals(t)) {
            return t.cast(parseUraCalendar(source, locale));
        }
        LOGGER.log("ERROR: source=[{}], exception=[{}]", source, "Unknown cast");
        throw new ClassCastException();
    }

    /**
     * 。<br>
     * @param source
     * @param locale
     * @param args
     * @return xxx
     */
    public <E> E parse(String source, Locale locale, E... args) {
        Class<E> t = UraClassUtils.getArrayClass(args);
        if (UraCalendar.class.equals(t)) {
            return t.cast(parseUraCalendar(source, locale));
        } else if (Calendar.class.equals(t)) {
            return t.cast(parseCalendar(source, locale));
        } else if (Date.class.equals(t)) {
            return t.cast(parseDate(source, locale));
        } else if (Long.class.equals(t) || long.class.equals(t)) {
            return t.cast(Long.valueOf(parseTimeInMillis(source, locale)));
        } else if (Object.class.equals(t)) {
            return t.cast(parseUraCalendar(source, locale));
        }
        LOGGER.log("ERROR: source=[{}], exception=[{}]", source, "Unknown cast");
        throw new ClassCastException();
    }

    /**
     * 。<br>
     * @param source
     * @param pos
     * @param args
     * @return xxx
     */
    public <E> E parse(String source, ParsePosition pos, E... args) {
        Class<E> t = UraClassUtils.getArrayClass(args);
        if (UraCalendar.class.equals(t)) {
            return t.cast(parseUraCalendar(source, pos, locale));
        } else if (Calendar.class.equals(t)) {
            return t.cast(parseCalendar(source, pos, locale));
        } else if (Date.class.equals(t)) {
            return t.cast(parseDate(source, pos, locale));
        } else if (Long.class.equals(t) || long.class.equals(t)) {
            return t.cast(Long.valueOf(parseTimeInMillis(source, pos, locale)));
        } else if (Object.class.equals(t)) {
            return t.cast(parseUraCalendar(source, pos, locale));
        }
        LOGGER.log("ERROR: source=[{}], exception=[{}]", source, "Unknown cast");
        throw new ClassCastException();
    }

    /**
     * 。<br>
     * @param source
     * @param pos
     * @param locale
     * @param args
     * @return xxx
     */
    public <E> E parse(String source, ParsePosition pos, Locale locale, E... args) {
        Class<E> t = UraClassUtils.getArrayClass(args);
        if (UraCalendar.class.equals(t)) {
            return t.cast(parseUraCalendar(source, pos, locale));
        } else if (Calendar.class.equals(t)) {
            return t.cast(parseCalendar(source, pos, locale));
        } else if (Date.class.equals(t)) {
            return t.cast(parseDate(source, pos, locale));
        } else if (Long.class.equals(t) || long.class.equals(t)) {
            return t.cast(Long.valueOf(parseTimeInMillis(source, pos, locale)));
        } else if (Object.class.equals(t)) {
            return t.cast(parseUraCalendar(source, pos, locale));
        }
        LOGGER.log("ERROR: source=[{}], exception=[{}]", source, "Unknown cast");
        throw new ClassCastException();
    }

    /**
     * 。<br>
     * @param source
     * @return xxx
     */
    public Calendar parseCalendar(String source) {
        return parseCalendar(source, locale);
    }

    /**
     * 。<br>
     * @param source
     * @param locale
     * @return xxx
     */
    public Calendar parseCalendar(String source, Locale locale) {
        return subParse(source, new ParsePosition(0), locale).getCalendar();
    }

    /**
     * 。<br>
     * @param source
     * @param pos
     * @return xxx
     */
    public Calendar parseCalendar(String source, ParsePosition pos) {
        return parseCalendar(source, pos, locale);
    }

    /**
     * 。<br>
     * @param source
     * @param pos
     * @param locale
     * @return xxx
     */
    public Calendar parseCalendar(String source, ParsePosition pos, Locale locale) {
        UraCalendar cal = subParseNoThrow(source, pos, locale);
        return (pos.getIndex() != 0) ? cal.getCalendar() : null;
    }

    /**
     * 。<br>
     * @param source
     * @return xxx
     */
    public Date parseDate(String source) {
        return parseDate(source, locale);
    }

    /**
     * 。<br>
     * @param source
     * @param locale
     * @return xxx
     */
    public Date parseDate(String source, Locale locale) {
        return subParse(source, new ParsePosition(0), locale).getTime();
    }

    /**
     * 。<br>
     * @param source
     * @param pos
     * @param locale
     * @return xxx
     */
    public Date parseDate(String source, ParsePosition pos) {
        return parseDate(source, pos, locale);
    }

    /**
     * 。<br>
     * @param source
     * @param pos
     * @param locale
     * @return xxx
     */
    public Date parseDate(String source, ParsePosition pos, Locale locale) {
        UraCalendar cal = subParseNoThrow(source, pos, locale);
        return (pos.getIndex() != 0) ? cal.getTime() : null;
    }

    /*
     * (非 Javadoc)
     *
     * @see java.text.Format#parseObject(java.lang.String, java.text.ParsePosition)
     */
    @Override
    public Object parseObject(String source, ParsePosition pos) {
        return parseDate(source, pos, locale);
    }

    /**
     * 。<br>
     * @param source
     * @return xxx
     */
    public long parseTimeInMillis(String source) {
        return parseTimeInMillis(source, locale);
    }

    /**
     * 。<br>
     * @param source
     * @param locale
     * @return xxx
     */
    public long parseTimeInMillis(String source, Locale locale) {
        return subParse(source, new ParsePosition(0), locale).getTimeInMillis();
    }

    /**
     * 。<br>
     * @param source
     * @param pos
     * @return xxx
     */
    public long parseTimeInMillis(String source, ParsePosition pos) {
        return parseTimeInMillis(source, pos, locale);
    }

    /**
     * 。<br>
     * @param source
     * @param pos
     * @param locale
     * @return xxx
     */
    public long parseTimeInMillis(String source, ParsePosition pos, Locale locale) {
        UraCalendar cal = subParseNoThrow(source, pos, locale);
        return (pos.getIndex() != 0) ? cal.getTimeInMillis() : 0L;
    }

    /**
     * 。<br>
     * @param source
     * @return インスタンス
     */
    public UraCalendar parseUraCalendar(String source) {
        return subParse(source, new ParsePosition(0), locale);
    }

    /**
     * 。<br>
     * @param source
     * @param locale
     * @return インスタンス
     */
    public UraCalendar parseUraCalendar(String source, Locale locale) {
        return subParse(source, new ParsePosition(0), locale);
    }

    /**
     * 。<br>
     * @param source
     * @param pos
     * @return インスタンス
     */
    public UraCalendar parseUraCalendar(String source, ParsePosition pos) {
        return parseUraCalendar(source, pos, locale);
    }

    /**
     * 。<br>
     * @param source
     * @param pos
     * @param locale
     * @return インスタンス
     */
    public UraCalendar parseUraCalendar(String source, ParsePosition pos, Locale locale) {
        UraCalendar cal = subParseNoThrow(source, pos, locale);
        return (pos.getIndex() != 0) ? cal : null;
    }

    /**
     * @param locale localeを設定します。
     */
    public void setLocale(Locale locale) {
        this.locale = locale;
    }

    /**
     * 本オブジェクト内容を読みやすい形で文字列で返却します。<br>
     * 複数フィールドを内包している場合、再帰的に対象オブジェクトのtoStringを呼び出します。
     *
     * @return 指定日付書式の文字列 本オブジェクトフィールド内容の文字列（改行付）
     */
    public String toMultiString() {
        // デフォルトスタイルを一時保管し、マルチスタイルに変更する
        ToStringStyle toStringStyle = ToStringBuilder.getDefaultStyle();
        ToStringBuilder.setDefaultStyle(UraMultiLineToStringStyle.INSTANCE);
        // toStringの実行
        String result = toString();
        // 元に戻す。
        ToStringBuilder.setDefaultStyle(toStringStyle);
        return result;
    }

    /**
     * 本オブジェクト内容を読みやすい形で文字列で返却します。<br>
     * 引数の変数名リストにあるパターンのみ出力します。
     *
     * @param strings 出力対象変数名パターン
     * @return 指定日付書式の文字列 本オブジェクトフィールド内容の文字列（改行付）
     */
    public String toMultiStringFilter(String... strings) {
        // デフォルトスタイルを一時保管し、マルチスタイルに変更する
        ToStringStyle toStringStyle = ToStringBuilder.getDefaultStyle();
        // ToStringStyle.MULTI_LINE_STYLE
        ToStringBuilder.setDefaultStyle(UraMultiLineToStringStyle.INSTANCE);
        // toStringの実行
        String result = toStringFilter(strings);
        // 元に戻す。
        ToStringBuilder.setDefaultStyle(toStringStyle);
        return result;
    }

    /**
     * 本オブジェクト内容を文字列で返却します。<br>
     * 複数フィールドを内包している場合、再帰的に対象オブジェクトのtoStringを呼び出します。
     *
     * @return 指定日付書式の文字列 本オブジェクトフィールド内容の文字列
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        return editUraToStringBuilder(new UraToStringBuilder(this)).toString();
    }

    /**
     * 本オブジェクト内容を文字列で返却します。<br>
     * 引数の変数名リストにあるパターンのみ出力します。
     *
     * @param strings 出力対象変数名パターン
     * @return 本オブジェクトフィールド内容の文字列
     */
    public String toStringFilter(String... strings) {
        UraToStringBuilder uraToStringBuilder = editUraToStringBuilder(new UraToStringBuilder(
                this));
        for (final String keyword : strings) {
            uraToStringBuilder.setIncludeFieldNamesPerttern(keyword);
        }
        return uraToStringBuilder.toString();
    }

    /**
     * ParserFieldクラス。<br>
     *
     * @since 2015/05/19
     * @author syany
     */
    protected interface ParserField {

        /**
         * 。<br>
         * @param parserField
         */
        public void setNextField(final ParserField parserField);

        /**
         * 。<br>
         * @param source
         * @param cal
         * @param pos
         * @param locale
         */
        public void setParseField(String source, UraCalendar cal, ParsePosition pos, Locale locale);
    }

    /**
     * AbsParserFieldクラス。<br>
     *
     * @since 2015/05/21
     * @author syany
     */
    abstract public static class AbsParserField implements ParserField {

        /**  */
        protected final int field;
        /**  */
        private ParserField nextParserField = null;
        /**  */
        protected Pattern parserPattern = null;
        /**  */
        protected final String strategy;

        /**
         * デフォルトコンストラクタ。<br>
         * @param field
         * @param strategy
         */
        public AbsParserField(int field, String strategy) {
            super();
            this.field = field;
            this.strategy = strategy;
        }

        /*
         * (非 Javadoc)
         *
         * @see org.uranoplums.typical.util.time.UraDateFormat.ParserField#nextField()
         */
        protected ParserField getNextField() {
            return nextParserField;
        }

        /**
         * 。<br>
         * @param iValue
         * @return
         */
        protected int modify(final int iValue) {
            return iValue;
        }

        /**
         * 。<br>
         * @param cal
         * @param val
         * @param locale
         */
        protected void setCalendar(UraCalendar cal, String val, Locale locale) {
            cal.set(field, this.modify(Integer.parseInt(val)));
        }

        /*
         * (非 Javadoc)
         *
         * @see
         * org.uranoplums.typical.util.time.UraDateFormat.ParserField#setNextField(org.uranoplums.typical.util.time.
         * UraDateFormat.ParserField)
         */
        @Override
        public void setNextField(final ParserField parserField) {
            nextParserField = parserField;
        }

        /*
         * (非 Javadoc)
         *
         * @see org.uranoplums.typical.util.time.UraDateFormat.ParserField#setParseField(java.lang.String,
         * org.uranoplums.typical.util.time.UraCalendar, java.text.ParsePosition)
         */
        @Override
        public void setParseField(String source, UraCalendar cal, ParsePosition pos, Locale locale) {
            pos.setErrorIndex(pos.getIndex());
            Matcher m = parserPattern.matcher(UraStringUtils.substring(source, pos.getIndex()));
            if (m.find() && m.start() == 0) {
                setCalendar(cal, m.group(), locale);
            } else {
                pos.setIndex(0);
                throw new UraParseRuntimeException("Unparseable date: \"" + source + "\" does not match " + parserPattern.pattern(),
                        pos.getErrorIndex())
                        .addContextValue("source", source)
                        .addContextValue("index", Integer.valueOf(pos.getErrorIndex()))
                        .addContextValue("subString", UraStringUtils.substring(source, pos.getErrorIndex()))
                        .addContextValue("MatcherPattern", parserPattern.pattern());
            }
            pos.setIndex(pos.getIndex() + m.group().length());
        }
    }

    /**
     * HourParserFieldクラス。<br>
     *
     * @since 2015/05/29
     * @author syany
     */
    public static class HourParserField extends NumberParserField {

        /**
         * デフォルトコンストラクタ。<br>
         * @param field
         * @param strategy
         */
        public HourParserField(int field, String strategy) {
            super(field, strategy);
        }

        /*
         * (非 Javadoc)
         *
         * @see
         * org.uranoplums.typical.util.time.UraDateFormat.AbsParserField#setCalendar(org.uranoplums.typical.util.time
         * .UraCalendar, java.lang.String, java.util.Locale)
         */
        @Override
        protected void setCalendar(UraCalendar cal, String val, Locale locale) {
            int value = Integer.parseInt(val);
            boolean isPm = (value / 12 > 0);
            cal.set(field, value % 12);
            if (isPm) {
                cal.setPm();
            } else {
                cal.setAm();
            }
        }
    }

    /**
     * NoParserFieldクラス。<br>
     *
     * @since 2015/05/24
     * @author syany
     */
    public static class NoParserField implements ParserField {

        /**  */
        protected Pattern parserPattern = null;

        /**
         * デフォルトコンストラクタ。<br>
         * @param strategy
         */
        public NoParserField(String strategy) {
            Matcher m1 = BASE_Q.matcher(strategy);
            if (m1.find()) {
                strategy = m1.replaceFirst("$1");
            }
            Matcher m2 = BASE_BQ.matcher(strategy);
            if (m2.find()) {
                strategy = m2.replaceAll(QUOTE);
            }
            Matcher m3 = BASE_ESC_SQ.matcher(strategy);
            if (m3.find()) {
                strategy = m3.replaceAll("\\\\$1");
            }
            parserPattern = Pattern.compile(strategy);
        }

        /*
         * (非 Javadoc)
         *
         * @see
         * org.uranoplums.typical.util.time.UraDateFormat.ParserField#setNextField(org.uranoplums.typical.util.time.
         * UraDateFormat.ParserField)
         */
        @Override
        public void setNextField(ParserField parserField) {}

        /*
         * (非 Javadoc)
         *
         * @see org.uranoplums.typical.util.time.UraDateFormat.ParserField#setParseField(java.lang.String,
         * org.uranoplums.typical.util.time.UraCalendar, java.text.ParsePosition)
         */
        @Override
        public void setParseField(String source, UraCalendar cal, ParsePosition pos, Locale locale) {
            pos.setErrorIndex(pos.getIndex());
            Matcher m = parserPattern.matcher(UraStringUtils.substring(source, pos.getIndex()));
            if (!m.find() || m.start() != 0) {
                pos.setIndex(0);
                throw new UraParseRuntimeException("Unparseable date: \"" + source + "\" does not match " +
                        parserPattern.pattern(),
                        pos.getErrorIndex())
                        .addContextValue("source", source)
                        .addContextValue("index", Integer.valueOf(pos.getErrorIndex()))
                        .addContextValue("subString", UraStringUtils.substring(source, pos.getErrorIndex()))
                        .addContextValue("MatcherPattern", parserPattern.pattern());
            }
            pos.setIndex(pos.getIndex() + m.group().length());
        }
    }

    /**
     * NumberParserFieldクラス。<br>
     *
     * @since 2015/05/21
     * @author syany
     */
    public static class NumberParserField extends AbsParserField {

        /**
         * デフォルトコンストラクタ。<br>
         * @param field
         * @param strategy
         */
        public NumberParserField(int field, String strategy) {
            super(field, strategy);
            this.parserPattern = Pattern.compile("-?\\p{Nd}++");
        }

        /*
         * (非 Javadoc)
         *
         * @see
         * org.uranoplums.typical.util.time.UraDateFormat.AbsParserField#setNextField(org.uranoplums.typical.util.time
         * .UraDateFormat.ParserField)
         */
        @Override
        public void setNextField(ParserField parserField) {
            super.setNextField(parserField);
            if (parserField instanceof NumberParserField) {
                String pattern = "-?\\p{Nd}{" + String.valueOf(strategy.length()) + "}+";
                this.parserPattern = Pattern.compile(pattern);
            } else {
                this.parserPattern = Pattern.compile("-?\\p{Nd}++");
            }
        }
    }

    /**
     * PrinterFieldインタフェース。<br>
     *
     * @since 2015/05/11
     * @author syany
     */
    public interface PrinterField {

        /**
         * 。<br>
         * @param pattern TODO
         * @param cal
         * @param locale
         * @return 指定日付書式の文字列
         */
        public String getFormatField(String pattern, UraCalendar cal, Locale locale);
    }

    /**
     * TextParserFieldクラス。<br>
     *
     * @since 2015/05/24
     * @author syany
     */
    public static class TextParserField extends AbsParserField {

        /**
         * デフォルトコンストラクタ。<br>
         * @param field
         * @param strategy
         */
        public TextParserField(int field, String strategy) {
            super(field, strategy);
        }

        /*
         * (非 Javadoc)
         *
         * @see
         * org.uranoplums.typical.util.time.UraDateFormat.AbsParserField#setCalendar(org.uranoplums.typical.util.time
         * .UraCalendar, java.lang.String)
         */
        @Override
        protected void setCalendar(UraCalendar cal, String val, Locale locale) {
            cal.setDisplayName(val, field, UraCalendar.ALL_STYLES, locale);
        }

        /**
         * 。<br>
         * @param fieldPatternMap
         * @param cal
         * @param locale
         */
        protected void setKeyValue(Map<Integer, Pattern> fieldPatternMap, UraCalendar cal, Locale locale) {
            Map<String, Integer> keyValues = getDisplayNames(field, cal, locale);
            // フィールドの設定がない場合は、作成する。
            StringBuilder regex = new StringBuilder(1024);
            for (final String textKeyValue : keyValues.keySet()) {
                escapeRegex(regex, textKeyValue, false).append('|');
            }
            fieldPatternMap.put(Integer.valueOf(field), Pattern.compile(regex.toString()));
        }

        /*
         * (非 Javadoc)
         *
         * @see org.uranoplums.typical.util.time.UraDateFormat.AbsParserField#setParseField(java.lang.String,
         * org.uranoplums.typical.util.time.UraCalendar, java.text.ParsePosition, java.util.Locale)
         */
        @Override
        public void setParseField(String source, UraCalendar cal, ParsePosition pos, Locale locale) {
            Map<Integer, Pattern> fieldPatternMap = getCachedDispNames(this.field, cal, locale);
            Integer fieldInt = Integer.valueOf(field);
            if (!fieldPatternMap.containsKey(fieldInt)) {
                setKeyValue(fieldPatternMap, cal, locale);
            }
            this.parserPattern = fieldPatternMap.get(fieldInt);
            super.setParseField(source, cal, pos, locale);
        }
    }

    /**
     * TimeZoneParserFieldクラス。<br>
     *
     * @since 2015/05/28
     * @author syany
     */
    public static class TimeZoneParserField extends AbsParserField {

        /**
         * Index of zone id
         */
        private static final int ID = 0;
        /**
         * Index of the long name of zone in standard time
         */
        private static final int LONG_STD = 1;
        /**
         * Index of the short name of zone in standard time
         */
        private static final int SHORT_STD = 2;
        /**
         * Index of the long name of zone in daylight saving time
         */
        private static final int LONG_DST = 3;
        /**
         * Index of the short name of zone in daylight saving time
         */
        private static final int SHORT_DST = 4;

        /**
         * デフォルトコンストラクタ。<br>
         * @param field
         * @param strategy
         */
        public TimeZoneParserField(int field, String strategy) {
            super(field, strategy);
        }

        /*
         * (非 Javadoc)
         *
         * @see
         * org.uranoplums.typical.util.time.UraDateFormat.AbsParserField#setCalendar(org.uranoplums.typical.util.time
         * .UraCalendar, java.lang.String, java.util.Locale)
         */
        @Override
        protected void setCalendar(UraCalendar cal, String value, Locale locale) {
            TimeZone tz;
            if (value.charAt(0) == '+' || value.charAt(0) == '-') {
                tz = TimeZone.getTimeZone("GMT" + value);
            }
            else if (value.startsWith("GMT")) {
                tz = TimeZone.getTimeZone(value);
            }
            else {
                SortedMap<String, TimeZone> tzNames = getCachedTimeZones(locale);
                tz = tzNames.get(value);
                if (tz == null) {
                    throw new IllegalArgumentException(value + " is not a supported timezone name");
                }
            }
            cal.setTimeZone(tz);
        }

        /**
         * 。<br>
         * @param fieldPatternMap
         * @param cal
         * @param locale
         */
        protected void setKeyValue(Map<Integer, Pattern> fieldPatternMap, UraCalendar cal, Locale locale) {
            SortedMap<String, TimeZone> tzNames = getCachedTimeZones(locale);
            if (tzNames.size() == 0) {
                final String[][] zones = UraDateFormatSymbols.getInstance(locale).getZoneStrings();
                for (final String[] zone : zones) {
                    if (zone[ID].startsWith("GMT")) {
                        continue;
                    }
                    final TimeZone tz = TimeZone.getTimeZone(zone[ID]);
                    if (!tzNames.containsKey(zone[LONG_STD])) {
                        tzNames.put(zone[LONG_STD], tz);
                    }
                    if (!tzNames.containsKey(zone[SHORT_STD])) {
                        tzNames.put(zone[SHORT_STD], tz);
                    }
                    if (tz.useDaylightTime()) {
                        if (!tzNames.containsKey(zone[LONG_DST])) {
                            tzNames.put(zone[LONG_DST], tz);
                        }
                        if (!tzNames.containsKey(zone[SHORT_DST])) {
                            tzNames.put(zone[SHORT_DST], tz);
                        }
                    }
                }
            }
            final StringBuilder regex = new StringBuilder(1024);
            regex.append("GMT[+\\-]\\d{0,1}\\d{2}|[+\\-]\\d{2}:?\\d{2}");
            for (final String id : tzNames.keySet()) {
                if (UraStringUtils.isEmpty(id)) {
                    continue;
                }
                regex.append('|');
                escapeRegex(regex, id, false);
            }
            Integer fieldInt = Integer.valueOf(field);
            fieldPatternMap.put(fieldInt, Pattern.compile(regex.toString()));
        }

        /*
         * (非 Javadoc)
         *
         * @see org.uranoplums.typical.util.time.UraDateFormat.AbsParserField#setParseField(java.lang.String,
         * org.uranoplums.typical.util.time.UraCalendar, java.text.ParsePosition, java.util.Locale)
         */
        @Override
        public void setParseField(String source, UraCalendar cal, ParsePosition pos, Locale locale) {
            Map<Integer, Pattern> fieldPatternMap = getCachedDispNames(this.field, cal, locale);
            Integer fieldInt = Integer.valueOf(field);
            if (!fieldPatternMap.containsKey(fieldInt)) {
                setKeyValue(fieldPatternMap, cal, locale);
            }
            this.parserPattern = fieldPatternMap.get(fieldInt);
            super.setParseField(source, cal, pos, locale);
        }
    }
}
