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
 * $Id: UraGregorianCalendarTest.java$
 */
package org.uranoplums.typical.util.time;

import static org.junit.Assert.*;
import static org.uranoplums.typical.collection.factory.UraListFactory.*;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * UraGregorianCalendarTestクラス。<br>
 *
 * @since 2015/04/23
 * @author syany
 */
public class UraGregorianCalendarTest {

    UraCalendar actualCal;
    Calendar expectedCal;
    Locale locale;

    @After
    public void after() {
        locale = Locale.JAPAN;
    }

    /**
     * 。<br>
     * @throws java.lang.Exception
     */
    @Before
    public void setUp() throws Exception {
        actualCal = UraCalendarUtils.newUraCalendar();
        actualCal.clear();
        expectedCal = Calendar.getInstance();
        expectedCal.clear();
        locale = Locale.JAPAN;
    }

    /**
     * {@link org.uranoplums.typical.util.time.UraGregorianCalendar#getDisplayName(int, int, java.util.Locale)}
     * のためのテスト・メソッド。
     */
    @Test
    public final void testGetDisplayNameIntIntLocale() {
        // Locale.setDefault(Locale.US);
        UraCalendar cal = UraCalendarUtils.newUraCalendar(Locale.ITALIAN);
        Calendar cmCal = Calendar.getInstance(Locale.ITALIAN);
        // try {
        // SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        // FastDateFormat fdf = FastDateFormat.getInstance("yyyy-MM-dd");
        // // sdf.parse(source);
        // Date cc = DateFormat.getDateInstance().parse("1989-01-08");
        // cc.getTime();
        // } catch (ParseException e) {
        // e.printStackTrace();
        // }
        assertEquals(cal.getDisplayName(UraCalendar.MONTH, UraCalendar.SHORT, Locale.ITALY),
                cmCal.getDisplayName(Calendar.MONTH, Calendar.SHORT, Locale.ITALY));
        // fail(cal.getDisplayName(UraCalendar.AUGUST, UraCalendar.SHORT, Locale.ITALY)); // TODO
    }

    @Test
    public final void testGetDisplayNameLocaleEra() {
        // Locale.setDefault(Locale.US);
        UraCalendar cal = UraCalendarUtils.newUraCalendar(2012, 0, 12);
        String localeEra = cal.getDisplayName(UraCalendar.LOCALE_ERA, UraCalendar.LONG, Locale.JAPAN);
        assertEquals(localeEra, "平成");
    }

    @Test
    public final void testGetDisplayNameLocaleEra2() {
        // Locale.setDefault(Locale.US);
        UraCalendar cal = UraCalendarUtils.newUraCalendar(1989, 0, 1);
        String localeEra = cal.getDisplayName(UraCalendar.LOCALE_ERA, UraCalendar.LONG, Locale.JAPAN);
        assertEquals(localeEra, "昭和");
    }

    @Test
    public final void testGetDisplayNameLocaleYear() {
        // Locale.setDefault(Locale.US);
        UraCalendar cal = UraCalendarUtils.newUraCalendar(2012, 0, 12);
        String localeEra = cal.getDisplayName(UraCalendar.LOCALE_YEAR, UraCalendar.LONG, Locale.JAPAN);
        assertEquals(localeEra, "24");
    }

    @Test
    public final void testGetDisplayNameLocaleYear2() {
        // Locale.setDefault(Locale.US);
        UraCalendar cal = UraCalendarUtils.newUraCalendar(1989, 0, 1);
        String localeEra = cal.getDisplayName(UraCalendar.LOCALE_YEAR, UraCalendar.LONG, Locale.JAPAN);
        assertEquals(localeEra, "64");
    }

    // -210835210493042
    @Test
    public final void testGetDisplayNameLocaleYear3() {
        // Locale.setDefault(Locale.US);
        SimpleDateFormat sdf = new SimpleDateFormat();
        UraCalendar cal = UraCalendarUtils.newUraCalendar(1, 0, 1);
        cal.add(UraCalendar.YEAR, -2);
        UraCalendar cal2 = UraCalendarUtils.newUraCalendar(1, 0, 1);
        cal2.setTimeInMillis(-210835210493042L);
        int y = cal2.getYear();
        int y0 = cal.getYear();
        String localeYear = cal.getDisplayName(UraCalendar.LOCALE_YEAR, UraCalendar.LONG, Locale.UK);
        String localeEra = cal.getDisplayName(UraCalendar.LOCALE_ERA, UraCalendar.LONG, Locale.UK);
        assertEquals(localeEra, "BC");
        assertEquals(localeYear, "2");
    }

    @Test
    public void testGetDisplayNames() {
        UraCalendar cal = UraCalendarUtils.newUraCalendar(1, 0, 1);
        Map<String, Integer> m = cal.getDisplayNames(UraCalendar.LOCALE_ERA, UraCalendar.ALL_STYLES, Locale.JAPAN);
        for (Map.Entry<String, Integer> f : m.entrySet()) {
            System.out.println("K[" + f.getKey() + "], V[" + f.getValue() + "]");
        }
        Map<String, Integer> m2 = cal.getDisplayNames(UraCalendar.LOCALE_YEAR, UraCalendar.ALL_STYLES, Locale.JAPAN);
        for (Map.Entry<String, Integer> f : m2.entrySet()) {
            System.out.println("K[" + f.getKey() + "], V[" + f.getValue() + "]");
        }
        // assertEquals("xxx", "");
        assertTrue(true);
    }

    @Test
    public void testSetDisplayNameAmpm() {
        actualCal.setAmPmDispName("午後", locale);
        expectedCal.set(Calendar.AM_PM, Calendar.PM);
        assertEquals(expectedCal.getTimeInMillis(), actualCal.getTimeInMillis());
    }

    @Test
    public void testSetDisplayNameDayW() {
        actualCal.setDayOfWeekDispName("木", locale);
        expectedCal.set(Calendar.DAY_OF_WEEK, Calendar.THURSDAY);
        assertEquals(expectedCal.getTimeInMillis(), actualCal.getTimeInMillis());
    }

    @Test
    public void testSetDisplayNameDayWLong() {
        actualCal.setDayOfWeekDispName("水曜日", locale);
        expectedCal.set(Calendar.DAY_OF_WEEK, Calendar.WEDNESDAY);
        assertEquals(expectedCal.getTimeInMillis(), actualCal.getTimeInMillis());
    }

    @Test
    public void testSetDisplayNameEra() {
        actualCal.setEraDispName("紀元前", locale);
        expectedCal.set(Calendar.ERA, 0);
        assertEquals(expectedCal.getTimeInMillis(), actualCal.getTimeInMillis());
    }

    @Test
    public void testSetDisplayNameLocaleEra() {
        actualCal.setLocaleEraDispName("昭和", locale);
        expectedCal.setTimeInMillis(-1357635600000L);
        assertEquals(expectedCal.getTimeInMillis(), actualCal.getTimeInMillis());
    }

    @Test
    public void testSetDisplayNameLocaleYear() {
        try {
            actualCal.set(UraCalendar.LOCALE_ERA, 2);
        } catch (Exception e) {
            e.printStackTrace();
        }
        actualCal.setLocaleEraDispName("平成", locale);
        actualCal.setLocaleYearDispName("13", locale);
        expectedCal.setTimeInMillis(600188400000L);
        expectedCal.set(Calendar.YEAR, expectedCal.get(Calendar.YEAR) + 12);
        assertEquals(expectedCal.getTimeInMillis(), actualCal.getTimeInMillis());
    }

    @Test
    public void testSetDisplayNameMonth() {
        actualCal.setMonthDispName("3", locale);
        expectedCal.set(Calendar.MONTH, 2);
        assertEquals(expectedCal.getTimeInMillis(), actualCal.getTimeInMillis());
    }

    @Test
    public void testSetDisplayNameMonthLong() {
        actualCal.setMonthDispName("5月", locale);
        expectedCal.set(Calendar.MONTH, 4);
        assertEquals(expectedCal.getTimeInMillis(), actualCal.getTimeInMillis());
    }

    @Test
    public void testClendarPattern01() {
        // 基本の使い方
        UraCalendar cal = UraCalendarUtils.newUraCalendar();
        cal.addYear(-300);
        String localeEra = cal.getLocaleEraDisplayName(Locale.JAPAN);
        String localeYear = cal.getLocaleYearDisplayName(Locale.JAPAN);
        System.out.println("今年の300年前は西暦" + cal.getYear() + "年で、和暦は" + localeEra + localeYear + "年です。");
        System.out.println();
        // 国別に出力したい場合
        cal = UraCalendarUtils.newUraCalendar(2001, 5, 15);
        // テスト的に日本、米国、イタリア、タイのロケールを設定
        List<Locale> localeList = newArrayList();
        localeList.add(Locale.JAPAN);
        localeList.add(Locale.US);
        localeList.add(Locale.ITALY);
        localeList.add(new Locale("th", "TH"));
        // ロケール別出力
        System.out.println("newUraCalendar(2001, 5, 15)");
        for(Locale loc: localeList) {
            String locName = loc.getDisplayName();
            String month = cal.getMonthDisplayName(UraCalendar.SHORT, loc);
            String dayOfWeek = cal.getDayOfWeekDisplayName(UraCalendar.SHORT, loc);
            System.out.println(" [" + cal.getYear() + "-"+ month + "-" + cal.getDayOfMonth() + "(" + dayOfWeek + ")] 国(" + locName + ")の場合");
        }

        assertTrue(true);
    }
}
