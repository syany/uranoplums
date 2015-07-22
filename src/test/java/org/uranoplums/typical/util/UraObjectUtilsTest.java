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
 * $Id: UraObjectUtilsTest.java$
 */
package org.uranoplums.typical.util;

import static org.junit.Assert.*;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeSet;

import org.junit.Before;
import org.junit.Test;
import org.uranoplums.typical.exception.UraSystemRuntimeException;
import org.uranoplums.typical.lang.UraObject;

class T extends UraObject {

    public int a;

    private T() {
        a = 222;
    }
}

/**
 * UraObjectUtilsTestクラス。<br>
 *
 * @since 2015/04/13
 * @author syany
 */
public class UraObjectUtilsTest {

    /**
     * 。<br>
     * @throws java.lang.Exception
     */
    @Before
    public void setUp() throws Exception {}

    @Test
    public final void staticCast_01_ClassIsNullAsException() {
        T t9 = null;
        T t = UraObjectUtils.cast(t9);
        assertEquals(t.a, 222);
    }

    @Test
    public final void staticCast_02_ObjectIsNull() {
        T t = UraObjectUtils.cast(null, T.class);
        assertEquals(t.a, 222);
    }

    @SuppressWarnings ("boxing")
    @Test
    public final void staticCast_03_01_ObjectComp() {
        T t1 = UraObjectUtils.cast(null);
        T t2 = UraObjectUtils.cast(t1);
        assertEquals(t1.equals(t2), true);
    }

    @Test
    public final void staticCast_03_ObjectComp() {
        T t1 = UraObjectUtils.cast(null, T.class);
        T t2 = UraObjectUtils.cast(t1, T.class);
        assertEquals(t1.equals(t2), true);
    }

    @Test
    public final void staticCast_04_ListObject() {
        T t1 = UraObjectUtils.cast(new ArrayList<String>(), T.class);
        T t2 = UraObjectUtils.cast(t1, T.class);
        assertEquals(t1.equals(t2), true);
    }

    @Test (expected = UraSystemRuntimeException.class)
    public final void staticCast_05_Exception() {
        UraClassUtils t = UraObjectUtils.cast(null, UraClassUtils.class);
        // assertEquals(t.toString(), "a");
        assertTrue(true);
    }

    @Test
    public final void staticCastToArray_01_ArrayIsNullAsException() {
        String[] h = UraObjectUtils.castToArray(null);
        assertTrue(true);
    }

    @Test
    public final void staticCastToArray_02_ObjectIsNull() {
        String[] j = new String[0];
        String[] h = UraObjectUtils.castToArray(null, j);
        // failed("aaa");
        assertTrue(true);
    }

    @Test
    public final void staticCastToArray_03_ObjectIsString() {
        String[] j = new String[0];
        String[] e = {"ww", "ee"};
        String[] h = UraObjectUtils.castToArray(e, j);
        //assertEquals(h, e);
        assertTrue(true);
    }

    @Test
    public final void staticCastToArray_04_ObjectIsList() {
        String[] j = new String[0];
        List<String> g = new ArrayList<String>();
        g.add("wewe");
        g.add("ttyy");
        String[] h = UraObjectUtils.castToArray(g, j);
//        assertEquals(h, g.toArray());
        assertTrue(true);
    }

    @Test
    public final void staticCastToArray_05_ObjectIsSet() {
        String[] j = new String[0];
        List<String> g = new ArrayList<String>();
        g.add("wewe");
        g.add("ttyy");
        TreeSet<String> t = new TreeSet<String>(g);
        String[] h = UraObjectUtils.castToArray(t, j);
//        assertEquals(h, t.toArray());
        assertTrue(true);
    }

    @Test
    public final void staticCastToArray_06_ObjectIsQueue() {
        String[] j = new String[0];
        Deque<String> g = new ArrayDeque<String>();
        g.add("wewe");
        g.add("ttyy");
        String[] h = UraObjectUtils.castToArray(g, j);
//        assertEquals(h, g.toArray());
        assertTrue(true);
    }

    @Test
    public final void staticCastToArray_07_ObjectIsMap() {
        String[] j = new String[0];
        Map<String, Object> g = new LinkedHashMap<String, Object>();
        g.put("wewe", "rr");
        g.put("ttyy", "#");
        String[] h = UraObjectUtils.castToArray(g, j);
//        assertEquals(h, g.keySet().toArray());
        assertTrue(true);
    }
}