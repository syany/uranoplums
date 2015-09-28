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
 * $Id: UraLocaleTest.java$
 */
package org.uranoplums.typical.util.i18n;

import static org.junit.Assert.*;

import java.util.Locale;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.uranoplums.typical.util.time.UraCalendar;
import org.uranoplums.typical.util.time.UraCalendarUtils;


/**
 * UraLocaleTestクラス。<br>
 *
 * @since 2015/10/07
 * @author syany
 */
public class UraLocaleTest {

    UraCalendar cal;

    /**
     * 。<br>
     * @throws java.lang.Exception
     */
    @BeforeClass
    public static void setUpBeforeClass() throws Exception {
        Locale.setDefault(Locale.UK);
    }

    /**
     * 。<br>
     * @throws java.lang.Exception
     */
    @AfterClass
    public static void tearDownAfterClass() throws Exception {}

    /**
     * 。<br>
     * @throws java.lang.Exception
     */
    @Before
    public void setUp() throws Exception {
        cal = UraCalendarUtils.newUraCalendar();
        cal.clear();
        cal.setNow().trancateHours();
    }

    /**
     * 。<br>
     * @throws java.lang.Exception
     */
    @After
    public void tearDown() throws Exception {}

    /**
     * {@link org.uranoplums.typical.util.i18n.UraLocale#arabic(java.lang.String[])} のためのテスト・メソッド。
     */
    @Test
    public final void testArabic() {
        Locale loc = UraLocale.arabic("MA");
        cal.setLocale(loc);
        System.out.println("Name["+loc.getDisplayName()+"],Cal["+cal.format("yyyy.MMM.dd E (gN)")+"]");
    }

    /**
     * {@link org.uranoplums.typical.util.i18n.UraLocale#czech(java.lang.String[])} のためのテスト・メソッド。
     */
    @Test
    public final void testCzech() {
        Locale loc = UraLocale.czech();
        cal.setLocale(loc);
        System.out.println("Name["+loc.getDisplayName()+"],Cal["+cal.format("yyyy.MMM.dd E (gN)")+"]");
        fail("まだ実装されていません"); // TODO
    }

    /**
     * {@link org.uranoplums.typical.util.i18n.UraLocale#japanese(java.lang.String[])} のためのテスト・メソッド。
     */
    @Test
    public final void testJapanese() {
        Locale loc = UraLocale.japanese();
        cal.addYear(-650);
        cal.setLocale(loc);
        System.out.println("Name["+loc.getDisplayName()+"],Cal["+cal.format("yyyy.MMM.dd E (gN)")+"]");
        loc = UraLocale.japanese("JP");
        cal.setLocale(loc);
        System.out.println("Name["+loc.getDisplayName()+"],Cal["+cal.format("yyyy.MMM.dd E (gN)")+"]");
        loc = UraLocale.japanese("JP", "JP");
        cal.setLocale(loc);
        System.out.println("Name["+loc.getDisplayName()+"],Cal["+cal.format("yyyy.MMM.dd E (gN)")+"]");
        fail("まだ実装されていません"); // TODO
    }

    /**
     * {@link org.uranoplums.typical.util.i18n.UraLocale#thai(java.lang.String[])} のためのテスト・メソッド。
     */
    @Test
    public final void testThai() {
        Locale loc = UraLocale.thai();
        cal.setLocale(loc);
        System.out.println("Name["+loc.getDisplayName()+"],Cal["+cal.format("yyyy.MMM.dd E (gN)")+"]");
        fail("まだ実装されていません"); // TODO
    }

    @Test
    public final void testUnknown() {
        Locale loc = UraLocale.japanese("JP", "Hira");
        cal.setLocale(loc);
        System.out.println("Name["+loc.getDisplayName()+"],Cal["+cal.format("yyyy.MMM.dd E (gN)")+"]");

        loc = UraLocale.japanese("KR");
        cal.setLocale(loc);
        System.out.println("Name["+loc.getDisplayName()+"],Cal["+cal.format("yyyy.MMM.dd E (gN)")+"]");

        fail("まだ実装されていません"); // TODO
    }
}
