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
 * $Id: UraRomanTranslationUtilsTest.java$
 */
package org.uranoplums.typical.util.i18n;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;


/**
 * UraRomanTranslationUtilsTestクラス。<br>
 *
 * @since 2015/12/10
 * @author syany
 */
public class UraRomanTranslationUtilsTest {

    static String[] dict = {
        "カァサン, "
        ,"1ヶゲツ"
        ,"トウキョウ、ストーリー, "
        ,"キンガシンネン, "
        ,"エイガヲミル、ニィサン, "
        ,"オウサマ、ゴキゲンヨウ, "
        ,"いすゞ, "
        ,"すゝめ, ",
        "づゝ, ",
        "ぶゞ漬け"
    };

    static final String hira = "しんねんに、おとぉさんとおかぁさんとで、いすゞトラックに乗った。とってもっちっちゃい。";
    static final String kata = "シンネンニ、オトォサントオカァサントデ、イスヾトラックニ乗ッタ。トッテモッチッチャイ。";
    static final String hepH = "SHINENI、OTOSANTOKASANTODE、ISUZUトラックNI乗TTA。TOTTEMOTCHITSUCHAI。";
    static final String hepK = "SHINENI、OTOSANTOKASANTODE、ISUZUTORAKKU乗TTA。TOTTEMOTCHITSUCHAI。";
    StringBuilder sb;
    /**
     * 。<br>
     * @throws java.lang.Exception
     */
    @BeforeClass
    public static void setUpBeforeClass() throws Exception {}

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
        sb = new StringBuilder();
    }

    /**
     * 。<br>
     * @throws java.lang.Exception
     */
    @After
    public void tearDown() throws Exception {}

    /**
     * {@link org.uranoplums.typical.util.i18n.UraRomanTranslationUtils#isInvalidWord(char)} のためのテスト・メソッド。
     */
    @Test
    public final void testIsInvalidWord() {
        fail("まだ実装されていません"); // TODO
    }

    /**
     * {@link org.uranoplums.typical.util.i18n.UraRomanTranslationUtils#isValidRange(char)} のためのテスト・メソッド。
     */
    @Test
    public final void testIsValidRange() {
        fail("まだ実装されていません"); // TODO
    }

    /**
     * {@link org.uranoplums.typical.util.i18n.UraRomanTranslationUtils#hiraganaToHepburn(java.lang.String)} のためのテスト・メソッド。
     */
    @Test
    public final void testKanaToHepburnString() {
        for(String t: dict) {
            String s = UraRomanTranslationUtils.katakanaToHepburn(t, 0);
            sb.append(UraJaStringUtils.capitalize(s.toLowerCase()));
        }
        System.out.println("testKanaToHepburnString:"+sb.toString());
        fail("まだ実装されていません"); // TODO
    }

    /**
     * {@link org.uranoplums.typical.util.i18n.UraRomanTranslationUtils#hiraganaToHepburn(java.lang.String, int)} のためのテスト・メソッド。
     */
    @Test
    public final void testKanaToHepburnStringInt() {
        String roman = UraRomanTranslationUtils.katakanaToHepburn(kata, 0);
        System.out.println("testKanaToHepburnStringInt:" + roman);
        assertEquals(hepK, roman);
    }
    @Test
    public final void testhiraToHepburnStringInt() {

        String roman = UraRomanTranslationUtils.hiraganaToHepburn(hira);
        System.out.println("ひらがなをローマ字変換の前は: " + hira);
        System.out.println("ひらがなをローマ字変換すると: " + roman);
        assertEquals(hepH, roman);
    }

    /**
     * {@link org.uranoplums.typical.util.i18n.UraRomanTranslationUtils#hiraganaToKunrei(java.lang.String, int)} のためのテスト・メソッド。
     */
    @Test
    public final void testKanaToKunrei() {
        for(String t: dict) {
            String s = UraRomanTranslationUtils.katakanaToKunrei(t, 0);
            sb.append(UraJaStringUtils.capitalize(s.toLowerCase()));
        }
        System.out.println("testKanaToKunrei:"+sb.toString());
        fail("まだ実装されていません"); // TODO
    }
}
