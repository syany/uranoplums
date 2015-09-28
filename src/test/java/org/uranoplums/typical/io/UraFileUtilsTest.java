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
 * $Id: UraFileUtilsTest.java$
 */
package org.uranoplums.typical.io;

import static org.junit.Assert.*;

import java.io.File;
import java.io.IOException;
import java.util.HashSet;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;


/**
 * UraFileUtilsTestクラス。<br>
 *
 * @since 2015/11/05
 * @author syany
 */
public class UraFileUtilsTest {

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
     * {@link org.uranoplums.typical.io.UraFileUtils#getCanonicalFile(java.lang.String, java.util.HashSet)} のためのテスト・メソッド。
     */
    @Test
    public final void testGetCanonicalFileStringCharsetHashSetOfString() {
        File f = UraFileUtils.getCanonicalFile("..\\.\\test.txt", new HashSet<String>());

        fail("まだ実装されていません"); // TODO
    }
    @Test
    public final void testFileRead() throws IOException {
        File f = new File(".\\bin\\charset_test.txt");
        UraFileUtils.openReadLine(f, null, new UraFileReader() {
            @Override
            public void readLine(String line, String path) {
                System.out.println("P["+path+"] D:" + line);
            }
        });

        fail("まだ実装されていません"); // TODO
    }

    @Test
    public final void testFileReadEuc() throws IOException {
        File f = new File(".\\bin\\charset_test_euc.txt");
        UraFileUtils.openReadLine(f, new UraFileReader() {
            @Override
            public void readLine(String line, String path) {
                System.out.println("P["+path+"] D:" + line);
            }
        });

        fail("まだ実装されていません"); // TODO
    }
    @Test
    public final void testGetCanonicalFileStringCharsetStringArray() {
        File f = UraFileUtils.getCanonicalFile("..\\.\\test.txt");

        fail("まだ実装されていません"); // TODO
    }

    /**
     * {@link org.uranoplums.typical.io.UraFileUtils#getCanonicalFile(java.io.File, java.util.HashSet)} のためのテスト・メソッド。
     */
    @Test
    public final void testGetCanonicalFileFileCharsetHashSetOfString() {
        fail("まだ実装されていません"); // TODO
    }
}
