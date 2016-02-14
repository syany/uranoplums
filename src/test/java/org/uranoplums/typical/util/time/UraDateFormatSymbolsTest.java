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
 * $Id: UraDateFormatSymbolsTest.java$
 */
package org.uranoplums.typical.util.time;

import java.text.DateFormatSymbols;
import java.util.Locale;

import org.junit.Before;
import org.junit.Test;
import org.uranoplums.typical.resource.UraJSONResource;

import static org.junit.Assert.*;

/**
 * UraDateFormatSymbolsTestクラス。<br>
 * 
 * @since 2015/04/08
 * @author syany
 */
public class UraDateFormatSymbolsTest {

    UraJSONResource res = new UraJSONResource("org.uranoplums.typical.util.time.date_format_symbols");

    /**
     * 。<br>
     * 
     * @throws java.lang.Exception
     */
    @Before
    public void _setUp() throws Exception {}

    @Test
    public void constructor_01_notNull() throws Exception {
        UraDateFormatSymbols dfs = new UraDateFormatSymbols();
        assertNotEquals(dfs, null);
    }

    @Test
    public void formatPatternTest() throws Exception {
        Locale.setDefault(Locale.UK);
        // iw ヘブライ語に注意”エスケープ必須
        for (Locale locale : Locale.getAvailableLocales()) {
            UraDateFormatSymbols udfs = UraDateFormatSymbols.getUraInstance(locale);
            DateFormatSymbols dfs = DateFormatSymbols.getInstance(locale);
            String localeStr = locale.getDisplayName() + "(" + locale.toString() + ")";
            assertArrayEquals("Era[" + localeStr + "]", udfs.getEras(), dfs.getEras());
            assertArrayEquals("Ampm[" + localeStr + "]", udfs.getAmPmStrings(), dfs.getAmPmStrings());
            assertArrayEquals("Month[" + localeStr + "]", udfs.getMonths(), dfs.getMonths());
            assertArrayEquals("ShortMonth[" + localeStr + "]", udfs.getShortMonths(), dfs.getShortMonths());
            assertArrayEquals("WeekDay[" + localeStr + "]", udfs.getWeekdays(), dfs.getWeekdays());
            assertArrayEquals("ShortWeekDay[" + localeStr + "]", udfs.getShortWeekdays(), dfs.getShortWeekdays());
            int i = 0;
            for (String[] dfsZones : dfs.getZoneStrings()) {
                assertArrayEquals("Zone[" + localeStr + "][" + i + "]", udfs.getZoneStrings()[i], dfsZones);
                i++;
            }
        }
    }

    // @Test
    // public void constructor_01_notNull() throws Exception {
    // UraDateFormatSymbols dfs = new UraDateFormatSymbols();
    // assertNotEquals(dfs, null);
    // }
    @Test
    public void staticGetInstance_02_notNull() throws Exception {
        UraDateFormatSymbols dfs = UraDateFormatSymbols.getUraInstance();
        assertNotEquals(dfs, null);
    }

    @Test
    public void staticGetInstance_03_notNull() throws Exception {
        UraDateFormatSymbols dfs = UraDateFormatSymbols.getUraInstance();
        UraDateFormatSymbols dfs2 = UraDateFormatSymbols.getUraInstance();
        assertEquals(dfs, dfs2);
    }
}
