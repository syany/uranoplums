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
 * $Id: UraDateFormatSpeedTest.java$
 */
package org.uranoplums.typical.util.time;

import java.util.Locale;
import java.util.TimeZone;

import org.junit.After;
import org.junit.Before;

/**
 * UraDateFormatSpeedTestクラス。<br>
 *
 * @since 2015/06/07
 * @author syany
 */
public class UraDateFormatSpeedTest {

    UraCalendar cal = UraCalendarUtils.newUraCalendar();
    private final TimeZone defTZ = (TimeZone) TimeZone.getDefault().clone();

    /**
     * デフォルトコンストラクタ。<br>
     */
    public UraDateFormatSpeedTest() {
        Locale.setDefault(Locale.JAPAN);
    }

    @After
    public void after() {
        TimeZone.setDefault(this.defTZ);
        cal.setTimeZone(defTZ);
    }
/*
    @Test
    public void externalTestFotmatFast() {
        long ut = 0L;
        String ff = null;
        for (int i = 0; i < 1000000; i++) {
            // FastDateFormat sdf = FastDateFormat.getInstance("yyyy-MM-dd hh:mm:ss.SSS");
            FastDateFormat sdf = FastDateFormat.getInstance("yyyy-MM-dd");
            ut = RandomUtils.nextLong(0L, 600188400000L);
            ff = sdf.format(new Date(ut));
        }
        System.out.println("[" + ff + "]");
        assertEquals("yyyy-MM-dd hh:mm:ss.SSS", ff);
    }

    @Test
    public void externalTestFotmatSimple() {
        long ut = 0L;
        String ff = null;
        for (int i = 0; i < 1000000; i++) {
            // SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss.SSS");
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            ut = RandomUtils.nextLong(0L, 600188400000L);
            ff = sdf.format(new Date(ut));
        }
        System.out.println("[" + ff + "]");
        assertEquals("yyyy-MM-dd hh:mm:ss.SSS", ff);
    }

    @Test
    public void externalTestFotmatUra() {
        long ut = 0L;
        String ff = null;
        for (int i = 0; i < 1000000; i++) {
            // UraDateFormat sdf = UraDateFormat.getInstanceRef("yyyy-MM-dd hh:mm:ss.SSS");
            UraDateFormat sdf = UraDateFormat.getInstanceRef("yyyy-MM-dd");
            ut = RandomUtils.nextLong(0L, 600188400000L);
            UraCalendar cal = UraCalendarUtils.newUraCalendar();
            cal.setTimeInMillis(ut);
            ff = sdf.format(cal);
        }
        System.out.println("[" + ff + "]");
        assertEquals("yyyy-MM-dd hh:mm:ss.SSS", ff);
    }

    @Test
    public void externalTestParseFast() throws ParseException {
        int year = 0;
        int month = 0;
        int day = 0;
        Date dt = null;
        for (int i = 0; i < 1000000; i++) {
            FastDateFormat sdf = FastDateFormat.getInstance("yyyy-MM-dd");
            year = RandomUtils.nextInt(0, 2050);
            month = RandomUtils.nextInt(0, 11);
            day = RandomUtils.nextInt(1, 28);
            dt = sdf.parse(String.valueOf(year) + "-" + String.valueOf(month) + "-" + String.valueOf(day));
        }
        System.out.println("[" + dt + "]");
        assertEquals(new Date(), dt);
    }

    @Test
    public void externalTestParseSimple() throws ParseException {
        int year = 0;
        int month = 0;
        int day = 0;
        Date dt = null;
        for (int i = 0; i < 1000000; i++) {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            year = RandomUtils.nextInt(0, 2050);
            month = RandomUtils.nextInt(0, 11);
            day = RandomUtils.nextInt(1, 28);
            dt = sdf.parse(String.valueOf(year) + "-" + String.valueOf(month) + "-" + String.valueOf(day));
        }
        System.out.println("[" + dt + "]");
        assertEquals(new Date(), dt);
    }

    @Test
    public void externalTestParseUra() throws ParseException {
        int year = 0;
        int month = 0;
        int day = 0;
        Date dt = null;
        for (int i = 0; i < 1000000; i++) {
            UraDateFormat sdf = UraDateFormat.getInstanceRef("yyyy-MM-dd");
            year = RandomUtils.nextInt(0, 2050);
            month = RandomUtils.nextInt(0, 11);
            day = RandomUtils.nextInt(1, 28);
            dt = sdf.parse(String.valueOf(year) + "-" + String.valueOf(month) + "-" + String.valueOf(day));
        }
        System.out.println("[" + dt + "]");
        assertEquals(new Date(), dt);
    }
*/
    /**
     * 。<br>
     * @throws java.lang.Exception
     */
    @Before
    public void setUp() throws Exception {}
}
