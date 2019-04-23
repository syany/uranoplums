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
 * $Id: UraJaStringUtilsTest.java$
 */
package org.uranoplums.typical.util.i18n;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;


/**
 * UraJaStringUtilsTestクラス。<br>
 *
 * @since 2015/12/07
 * @author syany
 */
public class UraJaStringUtilsTest {

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
    public void setUp() throws Exception {}

    /**
     * 。<br>
     * @throws java.lang.Exception
     */
    @After
    public void tearDown() throws Exception {}

    /**
     * {@link org.uranoplums.typical.util.i18n.UraJaStringUtils#hankakuToZenkaku(java.lang.String)} のためのテスト・メソッド。
     */
    @Test
    public final void testHankakuToZenkaku() {
        fail("まだ実装されていません"); // TODO
    }

    /**
     * {@link org.uranoplums.typical.util.i18n.UraJaStringUtils#hanToZen(java.lang.String)} のためのテスト・メソッド。
     */
    @Test
    public final void testHanToZen() {
        fail("まだ実装されていません"); // TODO
    }

    /**
     * {@link org.uranoplums.typical.util.i18n.UraJaStringUtils#hiraToKataTranslate(java.lang.String)} のためのテスト・メソッド。
     */
    @Test
    public final void testHiraToKataTranslateString() {
        fail("まだ実装されていません"); // TODO
    }

    /**
     * {@link org.uranoplums.typical.util.i18n.UraJaStringUtils#hiraToKataTranslate(java.lang.String, int)} のためのテスト・メソッド。
     */
    @Test
    public final void testHiraToKataTranslateStringInt() {
        String src01 = "いすゞ";
        String act01 = UraJaStringUtils.hiraToKataTranslate(src01);
        System.out.println("`"+ src01 +"' のカタカナは `"+ act01 +"' です。");
        String ext01 = "イスヾ";
        assertEquals(ext01, act01);
    }

    @Test
    public final void testHiraToKataTranslateStringInt02() {
        String src = "ゐーぅゔ";
        String act = UraJaStringUtils.hiraToKataTranslate(src);
        String ext = "ヰーゥヴ";
        assertEquals(ext, act);
    }

    /**
     * {@link org.uranoplums.typical.util.i18n.UraJaStringUtils#kataToHiraTranslate(java.lang.String)} のためのテスト・メソッド。
     */
    @Test
    public final void testKataToHiraTranslateString() {
        String src03 = "ヰーゥヴヷヵヶ";
        String act03 = UraJaStringUtils.kataToHiraTranslate(src03, 0);
        System.out.println("特殊な文字`"+ src03 +"' もこのように `"+ act03 +"' ひらがな変換可能です。");
        String ext03 = "ゐーぅゔゔゕゖ";
        assertEquals(ext03, act03);
    }

    @Test
    public final void testKataToHiraTranslateString02() {
        String src02 = "ヰスキィ";
        String act02 = UraJaStringUtils.kataToHiraTranslate(src02);
        System.out.println("`"+ src02 +"' のひらがなは `"+ act02 +"' です。");
        String ext02 = "ゐすきぃ";
        assertEquals(ext02, act02);
    }

    @Test
    public final void testKataToHiraTranslateString03() {
        String src04 = "ヾゥヴヷヵヶ";
        String act04 = UraJaStringUtils.kataToHiraTranslate(src04, UraJaStringUtils.NO_TRANSLATE_KH_WA | UraJaStringUtils.NO_TRANSLATE_KH_IM);
        System.out.println("特殊な文字`"+ src04 +"' はオプションで（ここでは、ヾとヷ）変換を制御します `"+ act04 +"'");
        String ext04 = "ヾぅゔヷゕゖ";
        assertEquals(ext04, act04);
    }
    @Test
    public final void testHiraKataToggle01() {
        String srcTogle = "ヰスキィゐすきぃヾゥヴヷヵヶゞぅゔゔゕゖ";
        String actTogle = UraJaStringUtils.hiraKataToggle(srcTogle);
        System.out.println("`"+ srcTogle +"' のひらがなは `"+ actTogle +"' です。");
        String extTogle = "ゐすきぃヰスキィゞぅゔゔヵヶヾゥヴヴゕゖ";
        assertEquals(extTogle, actTogle);
    }

    /**
     * {@link org.uranoplums.typical.util.i18n.UraJaStringUtils#kataToHiraTranslate(java.lang.String, int)} のためのテスト・メソッド。
     */
    @Test
    public final void testKataToHiraTranslateStringInt() {
        fail("まだ実装されていません"); // TODO
    }

    /**
     * {@link org.uranoplums.typical.util.i18n.UraJaStringUtils#zenkakuToHankaku(java.lang.String)} のためのテスト・メソッド。
     */
    @Test
    public final void testZenkakuToHankaku() {
    	final String srcZen = "ヱビスノウヰスキー";
    	final String actHan = UraJaStringUtils.zenkakuToHankaku(srcZen);
        System.out.println("`"+ srcZen +"' の半角は `"+ actHan +"' です。");
        final String extHan = "ｴﾋﾞｽﾉｳｲｽｷｰ";
        assertEquals(extHan, actHan);
    }
}
