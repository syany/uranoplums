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
 * $Id: UraDataObjectTest.java$
 */
package org.uranoplums.typical.lang;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;


/**
 * UraDataObjectTestクラス。<br>
 *
 * @since 2015/07/21
 * @author syany
 */
public class UraDataObjectTest {

    ElementDataA source;

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
        source = new ElementDataA();
    }

    /**
     * 。<br>
     * @throws java.lang.Exception
     */
    @After
    public void tearDown() throws Exception {}

    /**
     * {@link org.uranoplums.typical.lang.UraDataObject#clone()} のためのテスト・メソッド。
     */
    @Test
    public void testClone1() {
        ElementDataA target = (ElementDataA) source.clone();
        assertEquals(source, target);
    }
    @Test
    public void testClone2() {
        ElementDataA target = (ElementDataA) source.clone();
        assertEquals(source.compareTo(target), 0);
    }

    /**
     * {@link org.uranoplums.typical.lang.UraDataObject#compareTo(org.uranoplums.typical.lang.UraDataObject)} のためのテスト・メソッド。
     */
    @Test
    public void testCompareTo() {
        ElementDataA target = (ElementDataA) source.clone();
        target.setId(-999);
        assertNotEquals(source.compareTo(target), 0);
    }
}
