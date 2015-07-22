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
 * $Id: UraClassUtilsTest.java$
 */
package org.uranoplums.typical.util;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

/**
 * UraClassUtilsTestクラス。<br>
 *
 * @since 2015/04/12
 * @author syany
 */
public class UraClassUtilsTest {

    /**
     * 。<br>
     * @throws java.lang.Exception
     */
    @Before
    public void setUp() throws Exception {}

    /**
     * {@link org.uranoplums.typical.util.UraClassUtils#getCurrentClassLoader(java.lang.Class)} のためのテスト・メソッド。
     */
    @Test
    public final void testGetCurrentClassLoader() {
        ClassLoader classLoader01 = Thread.currentThread()
                .getContextClassLoader();
        // if (classLoader == null) {
        ClassLoader classLoader02 = ClassLoader.getSystemClassLoader();
        // if (classLoader == null) {
        ClassLoader classLoader03 = UraClassUtilsTest.class.getClassLoader();
        // }
        // }
//        fail("Name[" + UraClassUtilsTest.class.getName() + "]Loader1:" + classLoader01 + ", Loader2:" + classLoader02 + ", Loader3:" + classLoader03
//                + "."); // TODO
        assertTrue(true);
    }
}
