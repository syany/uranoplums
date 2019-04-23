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
 * $Id: UraDateFormatTest.java$
 */
package org.uranoplums.typical.util.time;

import static org.junit.Assert.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

///*
// * 新規にフォーマットパターンを追加する場合のクラス。<br>
// *
// * @since 2015/05/13
// *
// * @author syany
// */
//class TestDateFormat extends UraDateFormat {
//
//    /**  */
//    private static final long serialVersionUID = 1657494165739636925L;
//
//    /**
//     * デフォルトコンストラクタ。<br>
//     * @param string
//     */
//    public TestDateFormat(String string) {
//        super(string);
//    }
//
//    /*
//     * (非 Javadoc)
//     *
//     * @see org.uranoplums.typical.util.time.UraDateFormat#initialize()
//     */
//    @Override
//    protected void initialize() {
//        super.initialize();
//        this.optionalFormatMap.put("b", new UraDateFormat.PrinterField() {
//
//            @Override
//            public String getFormatField(String pattern, UraCalendar cal, Locale locale) {
//                return String.valueOf(cal.getMonth());
//            }
//        });
//        this.optionalParserMap.put("b", new UraDateFormat.NumberParserField(UraCalendar.MONTH, "b") {
//
//            @Override
//            protected int modify(int iValue) {
//                return iValue - 1;
//            }
//        });
//    }
//
//    /*
//     * (非 Javadoc)
//     *
//     * @see org.uranoplums.typical.util.time.UraDateFormat#visitOptionalPattern(java.util.List)
//     */
//    @Override
//    protected void visitOptionalPattern(List<String> optionalPatternList) {
//        // フォーマット許容パターンに、新しいパターン"b"を追加する
//        optionalPatternList.add("b");
//    }
//}

/**
 * UraLightDateFormatTestクラス。<br>
 *
 * @since 2015/05/09
 * @author syany
 */
public class UraDateFormatTest {

    UraCalendar cal = UraCalendarUtils.newUraCalendar();
    private final TimeZone defTZ = (TimeZone) TimeZone.getDefault().clone();

    /**
     * デフォルトコンストラクタ。<br>
     *
     */
    public UraDateFormatTest() {
        Locale.setDefault(Locale.JAPAN);
    }

    @After
    public void after() {
        TimeZone.setDefault(this.defTZ);
        cal.setTimeZone(defTZ);
    }

    @Test
    public void externalTestParseFotmat() {
        UraDateFormat udf = UraDateFormat.getInstance("gN年MMM月dd日'('E')' hh:mm ZZ");
        String testDate = "平成26年11月03日(月) 01:05 +09:00";
        UraCalendar cal = udf.parseUraCalendar(testDate);
        System.out.println(testDate + "--> [" + cal.getDate() + "]");
        String actualDate = udf.format(cal);
        assertEquals(testDate, actualDate);
    }

    @Test
    public void parseTestaPattern() throws ParseException {
        UraDateFormat ldf = UraDateFormat.getInstance("a  a");
        SimpleDateFormat sdf = new SimpleDateFormat("a  a");
        // cal.setMinutes(23);
        // String target = ldf.format(cal);
        Date target = (Date) ldf.parseObject("午前  午後");
        System.out.println("[" + target + "]");
        assertEquals(sdf.parse("午前  午後"), target);
    }

    @Test
    public void parseTestdPattern() throws ParseException {
        UraDateFormat ldf = UraDateFormat.getInstance("d dd");
        UraCalendar cal = UraCalendarUtils.newUraCalendar();
        cal.clear();
        cal.setDay(4);
        Date target = (Date) ldf.parseObject("2 04");
        assertEquals("XXXXTTTT", cal.getTime(), target);
    }

    @Test
    public void parseTestDPattern() throws ParseException {
        UraDateFormat ldf = UraDateFormat.getInstance("D DDD DD");
        SimpleDateFormat sdf = new SimpleDateFormat("D DDD DD");
        // cal.setMinutes(23);
        // String target = ldf.format(cal);
        Date target = (Date) ldf.parseObject("2 012 22");
        System.out.println("[" + target + "]");
        assertEquals(sdf.parse("2 012 22"), target);
    }

    @Test
    public void parseTestDPattern2() throws ParseException {
        UraDateFormat ldf = UraDateFormat.getInstance("ddDD");
        SimpleDateFormat sdf = new SimpleDateFormat("ddDD");
        // cal.setMinutes(23);
        // String target = ldf.format(cal);
        Date target = (Date) ldf.parseObject("0812");
        System.out.println("[" + target + "]");
        assertEquals(sdf.parse("0812"), target);
    }

    @Test
    public void parseTestEPattern() throws ParseException {
        UraDateFormat ldf = UraDateFormat.getInstance("E");
        SimpleDateFormat sdf = new SimpleDateFormat("E");
        // cal.setMinutes(23);
        // String target = ldf.format(cal);
        Date target = (Date) ldf.parseObject("金曜日");
        System.out.println("[" + target + "]");
        assertEquals(sdf.parse("金曜日"), target);
    }

    @Test
    public void parseTestEPattern2() throws ParseException {
        UraDateFormat ldf = UraDateFormat.getInstance("EEあまE");
        SimpleDateFormat sdf = new SimpleDateFormat("EEあまE");
        // cal.setMinutes(23);
        // String target = ldf.format(cal);
        Date target = (Date) ldf.parseObject("水あま木");
        System.out.println("[" + target + "]");
        assertEquals(sdf.parse("水あま木"), target);
    }

    @Test
    public void parseTestExtraPattern() throws ParseException {
        TestDateFormat ldf = new TestDateFormat("b");
        SimpleDateFormat sdf = new SimpleDateFormat("M");
        // String target = ldf.format(cal);
        // cal.setTimeInMillis(-1812186000000L);
        // cal.setHours(8);
        Date target = ldf.parse("12");
        Date expacted = sdf.parse("12");
        System.out.println("[" + target + "]");
        assertEquals(expacted, target);
    }

    @Test
    public void parseTestFPattern() throws ParseException {
        UraDateFormat ldf = UraDateFormat.getInstance("F");
        SimpleDateFormat sdf = new SimpleDateFormat("F");
        // cal.setMinutes(23);
        // String target = ldf.format(cal);
        Date target = (Date) ldf.parseObject("2");
        System.out.println("[" + target + "]");
        assertEquals(sdf.parse("2"), target);
    }

    @Test
    public void parseTestgPattern() throws ParseException {
        UraDateFormat ldf = UraDateFormat.getInstance("g");
        // cal.setMinutes(23);
        // String target = ldf.format(cal);
        Date target = ldf.parse("大正");
        cal.setTimeInMillis(-1812186000000L);
        System.out.println("[" + target + "]");
        assertEquals(cal.getTime(), target);
    }

    @Test
    public void parseTestGPattern() throws ParseException {
        UraDateFormat ldf = UraDateFormat.getInstance("G");
        SimpleDateFormat sdf = new SimpleDateFormat("G");
        // cal.setMinutes(23);
        // String target = ldf.format(cal);
        Date target = (Date) ldf.parseObject("紀元前");
        System.out.println("[" + target + "]");
        assertEquals(sdf.parse("紀元前"), target);
    }

    @Test
    public void parseTesthPattern() throws ParseException {
        UraDateFormat ldf = UraDateFormat.getInstance("hh h");
        SimpleDateFormat sdf = new SimpleDateFormat("hh h");
        // FastDateFormat sdf = FastDateFormat.getInstance("hh h");
        // cal.setMinutes(23);
        // String target = ldf.format(cal);
        Date target = (Date) ldf.parseObject("12 16");
        System.out.println("[" + target + "]");
        assertEquals(sdf.parse("12 16"), target);
    }

    @Test
    public void parseTestHPattern() throws ParseException {
        UraDateFormat ldf = UraDateFormat.getInstance("HH H");
        SimpleDateFormat sdf = new SimpleDateFormat("HH H");
        // FastDateFormat sdf = FastDateFormat.getInstance("hh h");
        // cal.setMinutes(23);
        // String target = ldf.format(cal);
        Date target = (Date) ldf.parseObject("00 24");
        System.out.println("[" + target + "]");
        assertEquals(sdf.parse("00 24"), target);
    }

    @Test
    public void parseTestkPattern() throws ParseException {
        UraDateFormat ldf = UraDateFormat.getInstance("kk k");
        SimpleDateFormat sdf = new SimpleDateFormat("kk k");
        // cal.setMinutes(23);
        // String target = ldf.format(cal);
        Date target = (Date) ldf.parseObject("12 24");
        System.out.println("[" + target + "]");
        assertEquals(sdf.parse("12 24"), target);
    }

    @Test
    public void parseTestKPattern() throws ParseException {
        UraDateFormat ldf = UraDateFormat.getInstance("KK K");
        SimpleDateFormat sdf = new SimpleDateFormat("KK K");
        // cal.setMinutes(23);
        // String target = ldf.format(cal);
        Date target = (Date) ldf.parseObject("12 24");
        System.out.println("[" + target + "]");
        assertEquals(sdf.parse("12 24"), target);
    }

    @Test
    public void parseTestlPattern() throws ParseException {
        UraDateFormat ldf = UraDateFormat.getInstance("lh");
        // SimpleDateFormat sdf = new SimpleDateFormat("KK K");
        // cal.setMinutes(23);
        // String target = ldf.format(cal);
        cal.setTimeInMillis(-1812186000000L);
        cal.setHours(8);
        Date target = ldf.parse("-18121860000008");
        System.out.println("[" + target + "]");
        assertEquals(cal.getTime(), target);
    }

    @Test
    public void parseTestLPattern() throws ParseException {
        UraDateFormat ldf = UraDateFormat.getInstance("L LLh");
        // SimpleDateFormat sdf = new SimpleDateFormat("KK K");
        // cal.setMinutes(23);
        // String target = ldf.format(cal);
        cal.setTimeInMillis(-1812186000000L);
        cal.setHours(8);
        Date target = ldf.parse("6001884000 -18121860008");
        System.out.println("[" + target + "]");
        assertEquals(cal.getTime(), target);
    }

    @Test
    public void parseTestmPattern() throws ParseException {
        UraDateFormat ldf = UraDateFormat.getInstance("m mm");
        SimpleDateFormat sdf = new SimpleDateFormat("m mm");
        // String target = ldf.format(cal);
        // cal.setTimeInMillis(-1812186000000L);
        // cal.setHours(8);
        Date target = ldf.parse("12 56");
        Date expacted = sdf.parse("12 56");
        System.out.println("[" + target + "]");
        assertEquals(expacted, target);
    }

    @Test
    public void parseTestMPattern() throws ParseException {
        UraDateFormat ldf = UraDateFormat.getInstance("M MM MMM MMMM");
        SimpleDateFormat sdf = new SimpleDateFormat("M MM MMM MMMM");
        // String target = ldf.format(cal);
        // cal.setTimeInMillis(-1812186000000L);
        // cal.setHours(8);
        Date target = ldf.parse("12 09 7 8月");
        Date expacted = sdf.parse("12 09 7 8月");
        System.out.println("[" + target + "]");
        assertEquals(expacted, target);
    }

    @Test
    public void parseTestNPattern() throws ParseException {
        UraDateFormat ldf = UraDateFormat.getInstance("gN'年'NNd");
        // SimpleDateFormat sdf = new SimpleDateFormat("gN'年'");
        // String target = ldf.format(cal);
        cal.setTimeInMillis(-1812186000000L);
        cal.addYear(11);
        cal.addYear(-3);
        cal.setDay(2);
        // cal.setHours(8);
        Date target = ldf.parse("大正12年092");
        // Date expacted = sdf.parse("12 09 7 8月");
        Date expacted = cal.getTime();
        System.out.println("[" + target + "]");
        assertEquals(expacted, target);
    }

    @Test
    public void parseTestsPattern() throws ParseException {
        UraDateFormat ldf = UraDateFormat.getInstance("ss s ssd");
        SimpleDateFormat sdf = new SimpleDateFormat("ss s ssd");
        Date target = ldf.parse("22 33 084");
        Date expacted = sdf.parse("22 33 084");
        System.out.println("[" + target + "]");
        assertEquals(expacted, target);
    }

    @Test
    public void parseTestSPattern() throws ParseException {
        UraDateFormat ldf = UraDateFormat.getInstance("S SSSd");
        SimpleDateFormat sdf = new SimpleDateFormat("S SSSd");
        Date target = ldf.parse("42 0084");
        Date expacted = sdf.parse("42 0084");
        System.out.println("[" + target + "]");
        assertEquals(expacted, target);
    }

    @Test
    public void parseTestuPattern() throws ParseException {
        UraDateFormat ldf = UraDateFormat.getInstance("u");
        cal.clear();
        cal.setDayOfWeek(7);
        // Date expacted = sdf.parse("12 09 7 8月");
        Date expacted = cal.getTime();
        Date target = ldf.parse("7");
        System.out.println("[" + target + "]");
        assertEquals(expacted, target);
    }

    @Test
    public void parseTestUPattern() throws ParseException {
        UraDateFormat ldf = UraDateFormat.getInstance("U");
        cal.clear();
        cal.setDayOfWeek(7);
        // Date expacted = sdf.parse("12 09 7 8月");
        Date expacted = cal.getTime();
        Date target = ldf.parse("0");
        System.out.println("[" + target + "]");
        assertEquals(expacted, target);
    }

    @Test
    public void parseTestwPattern() throws ParseException {
        UraDateFormat ldf = UraDateFormat.getInstance("w wwh");
        cal.clear();
        cal.setWeekOfMonth(3);
        cal.setHours(7);
        // Date expacted = sdf.parse("12 09 7 8月");
        Date expacted = cal.getTime();
        Date target = ldf.parse("2 037");
        System.out.println("[" + target + "]");
        assertEquals(expacted, target);
    }

    @Test
    public void parseTestWPattern() throws ParseException {
        UraDateFormat ldf = UraDateFormat.getInstance("W WWh");
        cal.clear();
        cal.setWeekOfYear(17);
        cal.setHours(7);
        // Date expacted = sdf.parse("12 09 7 8月");
        Date expacted = cal.getTime();
        Date target = ldf.parse("22 177");
        System.out.println("[" + target + "]");
        assertEquals(expacted, target);
    }

    @Test
    public void parseTestXPattern() throws ParseException {
        UraDateFormat ldf = UraDateFormat.getInstance("X XX XXXh");
        cal.clear();
        TimeZone tz = TimeZone.getTimeZone(TimeZone.getAvailableIDs()[50]);
        cal.setTimeZone(tz);
        cal.setHours(2);
        // Date expacted = sdf.parse("12 09 7 8月");
        Calendar expacted = cal.getCalendar();
        Calendar target = ldf.parse("+0900 -02:00 " + tz.getDisplayName() + "2");
        System.out.println("[" + target + "]");
        assertEquals(expacted.getTime(), target.getTime());
    }

    @Test
    public void parseTestyPattern() throws ParseException {
        UraDateFormat ldf = UraDateFormat.getInstance("y yy yyyyh");
        SimpleDateFormat sdf = new SimpleDateFormat("y yy yyyyh");
        Date target = ldf.parse("1205 2980 15607");
        Date expacted = sdf.parse("1205 2980 15607");
        System.out.println("[" + target + "]");
        assertEquals(expacted, target);
    }

    @Test
    public void parseTestzPattern() throws ParseException {
        UraDateFormat ldf = UraDateFormat.getInstance("z zz zzzzh");
        cal.clear();
        TimeZone tz = TimeZone.getTimeZone(TimeZone.getAvailableIDs()[100]);
        cal.setTimeZone(tz);
        cal.setHours(2);
        // Date expacted = sdf.parse("12 09 7 8月");
        Calendar expacted = cal.getCalendar();
        Calendar target = ldf.parse("+0900 -02:00 " + tz.getDisplayName() + "2");
        System.out.println("[" + target.getTime() + "]");
        assertEquals(expacted.getTime(), target.getTime());
    }

    @Test
    public void parseTestZPattern() throws ParseException {
        UraDateFormat ldf = UraDateFormat.getInstance("Z ZZ ZZh");
        cal.clear();
        TimeZone tz = TimeZone.getTimeZone(TimeZone.getAvailableIDs()[2]);
        cal.setTimeZone(tz);
        cal.setHours(2);
        // Date expacted = sdf.parse("12 09 7 8月");
        Calendar expacted = cal.getCalendar();
        Calendar target = ldf.parse("+0900 -02:00 " + tz.getDisplayName() + "2");
        System.out.println("[" + target.getTime() + "]");
        assertEquals(expacted.getTime(), target.getTime());
    }

    /**
     * 。<br>
     * @throws java.lang.Exception
     */
    @Before
    public void setUp() throws Exception {}

    @Test
    public void testaPattern() {
        UraDateFormat ldf = UraDateFormat.getInstance("a");
        SimpleDateFormat sdf = new SimpleDateFormat("a", Locale.US);
        cal.setAm();
        String target = ldf.format(cal, new StringBuffer(), Locale.US, TimeZone.getDefault()).toString();
        assertEquals(sdf.format(cal.getDate()), target);
    }

    @Test
    public void testdPattern() {
        UraDateFormat ldf = UraDateFormat.getInstance("dd d");
        cal.setDay(6);
        String target = ldf.format(cal);
        assertEquals("06 6", target);
    }

    @Test
    public void testDPattern() {
        UraDateFormat ldf = UraDateFormat.getInstance("DDD D DD");
        SimpleDateFormat sdf = new SimpleDateFormat("DDD D DD");
        cal.setDayOfYear(123);
        String target = ldf.format(cal);
        assertEquals(sdf.format(cal.getDate()), target);
    }

    @Test
    public void testEPattern() {
        UraDateFormat ldf = UraDateFormat.getInstance("EEE");
        cal.set(2012, 0, 15);
        String target = ldf.format(cal);
        // assertTrue("015 15".equals(target));
        assertEquals("日曜日日", target);
    }

    @Test
    public void testexternalPattern() {
        TestDateFormat ldf = new TestDateFormat("b");
        cal.setMonth(6);
        String targetFmt = ldf.format(cal);
        System.out.println("現在[" + targetFmt + "]です。");
        assertEquals("6", targetFmt);
    }

    @Test
    public void testFPattern() {
        UraDateFormat ldf = UraDateFormat.getInstance("F");
        cal.set(2012, 0, 15);
        String target = ldf.format(cal);
        // assertTrue("015 15".equals(target));
        assertEquals("3", target);
    }

    @Test
    public void testgPattern() {
        UraDateFormat ldf = UraDateFormat.getInstance("g");
        cal.set(2012, 0, 15);
        String target = ldf.format(cal);
        // assertTrue("015 15".equals(target));
        assertEquals("平成", target);
    }

    @Test
    public void testGPattern() {
        UraDateFormat ldf = UraDateFormat.getInstance("G yy");
        cal.set(2012, 0, 15);
        String target = ldf.format(cal);
        // assertTrue("015 15".equals(target));
        assertEquals("西暦 12", target);
    }

    @Test
    public void testhPattern() {
        UraDateFormat ldf = UraDateFormat.getInstance("h hh");
        SimpleDateFormat sdf = new SimpleDateFormat("h hh");
        cal.setHoursOfDay(17);
        String target = ldf.format(cal);
        assertEquals(sdf.format(cal.getDate()), target);
        cal.setHoursOfDay(0);
        target = ldf.format(cal);
        assertEquals(sdf.format(cal.getDate()), target);
    }

    @Test
    public void testHPattern() {
        UraDateFormat ldf = UraDateFormat.getInstance("HH H");
        SimpleDateFormat sdf = new SimpleDateFormat("HH H");
        cal.setHoursOfDay(23);
        String target = ldf.format(cal);
        assertEquals(sdf.format(cal.getDate()), target);
        cal.setHoursOfDay(0);
        target = ldf.format(cal);
        assertEquals(sdf.format(cal.getDate()), target);
    }

    @Test
    public void testkPattern() {
        UraDateFormat ldf = UraDateFormat.getInstance("k kk");
        SimpleDateFormat sdf = new SimpleDateFormat("k kk");
        cal.setHoursOfDay(17);
        String target = ldf.format(cal);
        assertEquals(sdf.format(cal.getDate()), target);
        cal.setHoursOfDay(0);
        target = ldf.format(cal);
        assertEquals(sdf.format(cal.getDate()), target);
    }

    @Test
    public void testKPattern() {
        UraDateFormat ldf = UraDateFormat.getInstance("K KK");
        SimpleDateFormat sdf = new SimpleDateFormat("K KK");
        cal.setHoursOfDay(23);
        String target = ldf.format(cal);
        assertEquals(sdf.format(cal.getDate()), target);
        cal.setHoursOfDay(0);
        target = ldf.format(cal);
        assertEquals(sdf.format(cal.getDate()), target);
    }

    @Test
    public void testlPattern() {
        UraDateFormat ldf = UraDateFormat.getInstance("l");
        // SimpleDateFormat sdf = new SimpleDateFormat("mm m");
        cal.setTimeInMillis(12345);
        String target = ldf.format(cal);
        assertEquals("12345", target);
    }

    @Test
    public void testLPattern() {
        UraDateFormat ldf = UraDateFormat.getInstance("L LL");
        // SimpleDateFormat sdf = new SimpleDateFormat("mm m");
        cal.setTimeInMillis(12345);
        // cal.set(1975, 0, 1);
        String target = ldf.format(cal);
        assertEquals("12 0000000012", target);
    }

    @Test
    public void testmPattern() {
        UraDateFormat ldf = UraDateFormat.getInstance("mm m");
        SimpleDateFormat sdf = new SimpleDateFormat("mm m");
        cal.setMinutes(23);
        String target = ldf.format(cal);
        assertEquals(sdf.format(cal.getDate()), target);
    }

    @Test
    public void testMPattern() {
        UraDateFormat ldf = UraDateFormat.getInstance("M MM MMM MMMM MMMM M");
        SimpleDateFormat sdf = new SimpleDateFormat("M MM MMM MMMM MMMM M");
        cal.setMonth(13);
        String target = ldf.format(cal);
        assertEquals(sdf.format(cal.getDate()), target);
    }

    @Test
    public void testNPattern() {
        UraDateFormat ldf = UraDateFormat.getInstance("gN年");
        // SimpleDateFormat sdf = new SimpleDateFormat("M MM MMM MMMM MMMM M");
        cal.set(1989, 0, 5);
        String target = ldf.format(cal);
        assertEquals("昭和64年", target);
    }

    @Test
    public void testsPattern() {
        UraDateFormat ldf = UraDateFormat.getInstance("ss s");
        SimpleDateFormat sdf = new SimpleDateFormat("ss s");
        cal.setMinutes(23);
        String target = ldf.format(cal);
        assertEquals(sdf.format(cal.getDate()), target);
    }

    @Test
    public void testSPattern() {
        UraDateFormat ldf = UraDateFormat.getInstance("SSS S");
        SimpleDateFormat sdf = new SimpleDateFormat("SSS S");
        cal.setMinutes(23);
        String target = ldf.format(cal);
        assertEquals("XXXXTTTT", sdf.format(cal.getDate()), target);
    }

    @Test
    public void testuPattern() {
        UraDateFormat ldf = UraDateFormat.getInstance("u");
        cal.setDayOfWeek(2);
        String target = ldf.format(cal);
        assertEquals("XXXXTTTT", "2", target);
    }

    @Test
    public void testUPattern() {
        UraDateFormat ldf = UraDateFormat.getInstance("U");
        cal.setDayOfWeek(2);
        String target = ldf.format(cal);
        assertEquals("XXXXTTTT", "2", target);
        cal.setDayOfWeek(7);
        target = ldf.format(cal);
        assertEquals("XXXXTTTT", "0", target);
    }

    @Test
    public void testwPattern() {
        UraDateFormat ldf = UraDateFormat.getInstance("ww w");
        SimpleDateFormat sdf = new SimpleDateFormat("ww w");
        cal.set(2015, 0, 20);
        String target = ldf.format(cal);
        assertEquals("XXXXTTTT", sdf.format(cal.getDate()), target);
    }

    @Test
    public void testWPattern() {
        UraDateFormat ldf = UraDateFormat.getInstance("WW");
        SimpleDateFormat sdf = new SimpleDateFormat("WW");
        cal.set(2015, 0, 20);
        String target = ldf.format(cal.getCalendar());
        assertEquals("XXXXTTTT", sdf.format(cal.getDate()), target);
    }

    @Test
    public void testXPattern() {
        UraDateFormat ldf = UraDateFormat.getInstance("X XX XXX");
        // SimpleDateFormat sdf = new SimpleDateFormat("X");
        cal.set(2015, 0, 20);
        cal.setTimeZone(defTZ);
        String target = ldf.format(cal.getCalendar());
        assertEquals("XXXXTTTT", "+09 +0900 +09:00", target);
        TimeZone.setDefault(TimeZone.getTimeZone("America/Los_Angeles"));
        // SimpleDateFormat sdf2 = new SimpleDateFormat("Z"/* , DateFormatSymbols.getInstance(Locale.US) */);
        cal.setTimeZone(TimeZone.getTimeZone("America/Los_Angeles"));
        target = ldf.format(cal);
        assertEquals("XXXXTTTT", "-08 -0800 -08:00", target);
    }

    @Test
    public void testYPattern() {
        UraDateFormat ldf = UraDateFormat.getInstanceRef("yyyy yy y");
        String target = ldf.format(new Date(-41795686800000L));
        assertEquals("0645 45 645", target);
    }

    @Test
    public void testZextraPatternPattern() {
        UraDateFormat ldf = UraDateFormat.getInstance("hh 'y'' o''clk 'a, ");
        SimpleDateFormat sdf = new SimpleDateFormat("hh 'y'' o''clk 'a, ");
        cal.setMinutes(23);
        String target = ldf.format(cal);
        assertEquals("XXXXTTTT", sdf.format(cal.getDate()), target);
    }

    @Test
    public void testzPattern() {
        UraDateFormat ldf = UraDateFormat.getInstance("z zz zzz zzzz");
        SimpleDateFormat sdf = new SimpleDateFormat("z zz zzz zzzz");
        cal.set(2015, 0, 20);
        String target = ldf.format(cal.getDate());
        assertEquals("XXXXTTTT", sdf.format(cal.getDate()), target);
    }

    @Test
    public void testZPattern() {
        UraDateFormat ldf = UraDateFormat.getInstance("Z");
        SimpleDateFormat sdf = new SimpleDateFormat("Z");
        cal.set(2015, 0, 20);
        String target = ldf.format(cal.getTimeInMillis());
        assertEquals("XXXXTTTT", sdf.format(cal.getDate()), target);
        TimeZone.setDefault(TimeZone.getTimeZone("America/Los_Angeles"));
        SimpleDateFormat sdf2 = new SimpleDateFormat("Z"/* , DateFormatSymbols.getInstance(Locale.US) */);
        cal.setTimeZone(TimeZone.getTimeZone("America/Los_Angeles"));
        target = ldf.format(cal);
        assertEquals("XXXXTTTT", sdf2.format(cal.getDate()), target);
    }

    @Test
    public void externalTestParseFotmatReiwa() {
        UraDateFormat udf = UraDateFormat.getInstance("gN年");
        String testDate = "令和1年";
        UraCalendar cal = udf.parseUraCalendar(testDate);
        System.out.println(testDate + "--> [" + cal.getDate() + "]");
        String actualDate = udf.format(cal);
        assertEquals(testDate, actualDate);
    }
}
