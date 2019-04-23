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
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.uranoplums.typical.util.i18n.UraLocale;

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
        cal = UraCalendarUtils.newUraCalendar(2015, 5, 15);
        // テスト的に日本、米国、イタリア、タイのロケールを設定
        List<Locale> localeList = newArrayList();
        localeList.add(UraLocale.japanese());
        localeList.add(UraLocale.US);
        localeList.add(UraLocale.italian("IT"));
        localeList.add(UraLocale.thai());
        // ロケール別出力
        System.out.println("newUraCalendar(2001, 5, 15)");
        for (Locale loc : localeList) {
            String locName = loc.getDisplayName();
            String month = cal.getMonthDisplayName(UraCalendar.SHORT, loc);
            String dayOfWeek = cal.getDayOfWeekDisplayName(UraCalendar.SHORT, loc);
            String localeEra2 = cal.getLocaleEraDisplayName(loc);
            String localeYear2 = cal.getLocaleYearDisplayName(loc);
            System.out.println(" [" + cal.getYear() + "-" + month + "-" + cal.getDayOfMonth() + "(" + dayOfWeek + ")][" + localeEra2 + localeYear2
                    + "] 国(" + locName + ")の場合");
        }
        assertTrue(true);
    }

    @Test
    public void testBuddhistCalendar02() {
        UraCalendar cal = UraCalendarUtils.newUraCalendar();
        cal.setNow();
        cal.addYear(-2015);
        Locale buddhistLocale = UraLocale.thai("TH");
        Calendar bCal = Calendar.getInstance(buddhistLocale);
        bCal.setTime(cal.getTime());
        System.out.println("西暦:[" + cal.getYear() + "], 仏暦:[" + bCal.getDisplayName(Calendar.ERA, Calendar.SHORT, buddhistLocale)
                + bCal.get(Calendar.YEAR) + "]はローカル暦では[" + cal.getLocaleEraDisplayName(buddhistLocale) + cal.getLocaleYearDisplayName(buddhistLocale)
                + "]です");
    }

    @Test
    public void testBuddhistCalendar() {
        UraCalendar cal = UraCalendarUtils.newUraCalendar(1, 0, 1);
        cal.clear();
        cal.set(1, 0, 1);
        // cal.addYear(-543);
        // System.out.println("0[" + cal.getYear() + "-" + cal.getMonthDisplayName(UraCalendar.SHORT, Locale.JAPAN) +
        // "]");
        // cal.setTimeInMillis(-39140787600000L);//-79303136400000
        cal.setTimeInMillis(-79303136400000L);
        System.out.println("c[" + cal.getEraDisplayName(Locale.UK) + "], y[" + cal.getYear() + "],[" + cal.getTimeInMillis() + "]@" + cal.toString());
        Calendar gCal = Calendar.getInstance();
        gCal.clear();
        gCal.set(1, 0, 1);
        String gEra = gCal.getDisplayName(Calendar.ERA, Calendar.LONG, Locale.UK);
        int gYear = gCal.get(Calendar.YEAR);
        System.out.println("c[" + gEra + "], y[" + gYear + "]");
        // gCal.add(Calendar.YEAR, -543);
        // gCal.setTimeInMillis(-39140787600000L);
        gCal.setTimeInMillis(-79303136400000L);
        gEra = gCal.getDisplayName(Calendar.ERA, Calendar.LONG, Locale.UK);
        gYear = gCal.get(Calendar.YEAR);
        System.out.println("c[" + gEra + "], y[" + gYear + "],[" + gCal.getTimeInMillis() + "]@" + gCal.toString());
        Locale buddhistLocale = UraLocale.thai("TH");
        Calendar bCal = Calendar.getInstance(buddhistLocale);
        String era = bCal.getDisplayName(Calendar.ERA, Calendar.LONG, buddhistLocale);
        int year = bCal.get(Calendar.YEAR);
        System.out.println("now[" + era + " " + year + "]");
        assertTrue(true);
    }

    @Test
    public void testCalendarPattern03() {
        UraCalendar cal = UraCalendarUtils.newUraCalendar(0L);
        UraDateFormat udf = UraDateFormat.getInstance("yyyy-MM-dd aKK:mm:ss.SSS(EE)");
        System.out.println("変更前:" + udf.format(cal));
        // 編集
        cal.addMilliseconds(555);
        cal.setHoursOfDay(15).setDay(18);
        System.out.println("変更中:" + udf.format(cal));
        cal.rollMinutes(62).rollHoursOfDay(14);
        // cal.rollMinutes(62).rollHours(14); // <-- これは、AM/PM変わらない
        cal.setStrictMilliseconds(10137).setStrictDayOfWeek(0);
        System.out.println("変更中:" + udf.format(cal));
        cal.trancateDay().addMonth(-13);
        System.out.println("変更後:" + udf.format(cal));
        assertTrue(true);
    }

    @Test
    public void testCalendarPattern04() {
        UraCalendar cal = UraCalendarUtils.newUraCalendar(0L);

        UraCalendar calb = UraCalendarUtils.newUraCalendar(0L);

        cal.compareDate(calb.getCalendar());
        assertTrue(true);
    }

    @Test
    public void testInstance01() {
        Calendar ext = Calendar.getInstance();
        UraCalendar c = UraCalendarUtils.newUraCalendar(ext);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss.SSS Z");
        String extStr = sdf.format(ext.getTime());
        String actStr = c.format("yyyy-MM-dd hh:mm:ss.SSS Z");
        assertEquals(extStr, actStr);
    }

    @Test
    public void testInstance02() {
        Calendar ext = Calendar.getInstance(UraLocale.FRENCH);
        UraCalendar c = new UraGregorianCalendar(ext, UraLocale.FRENCH);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss.SSS Z");
        String extStr = sdf.format(ext.getTime());
        String actStr = c.format("yyyy-MM-dd hh:mm:ss.SSS Z");
        assertEquals(extStr, actStr);
    }

    @Test
    public void testInstance03() {
        Calendar ext = new GregorianCalendar(1956, 6, 23);
        UraCalendar c = new UraGregorianCalendar(1956, 6, 23);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss.SSS Z");
        String extStr = sdf.format(ext.getTime());
        String actStr = c.format("yyyy-MM-dd hh:mm:ss.SSS Z");
        assertEquals(extStr, actStr);
    }

    @Test
    public void testInstance04() {
        // 基本の使い方
        UraCalendar cal = UraCalendarUtils.newUraCalendar(2019,3,30,0,0,0);
        //cal.set(2019,3,30,0,0,0);
        String localeEra = cal.getLocaleEraDisplayName(Locale.JAPAN);
        String localeYear = cal.getLocaleYearDisplayName(Locale.JAPAN);
        System.out.println("西暦" + cal.getYear() + "年の"+ cal.format("MM/dd") +"の和暦は" + localeEra + localeYear + "年です。");
        System.out.println();
        cal.addDay(1);
        localeEra = cal.getLocaleEraDisplayName(Locale.JAPAN);
        localeYear = cal.getLocaleYearDisplayName(Locale.JAPAN);
        System.out.println("西暦" + cal.getYear() + "年の"+ cal.format("MM/dd") +"の和暦は" + localeEra + localeYear + "年です。");
        System.out.println();
        assertTrue(true);
    }
}
