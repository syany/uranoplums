/*
 * Copyright 2013-2018 the Uranoplums Foundation and the Others.
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
 * $Id: UraYAMLResourceTest.java$
 */
package org.uranoplums.typical.resource;

import static org.junit.Assert.*;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

import org.junit.Before;
import org.junit.Test;

/**
 * UraYAMLResourceTestクラス。<br>
 *
 * @since 2018/03/03
 * @author syany
 */
public class UraYAMLResourceTest {
    UraYAMLResource uraYAMLResource;
    /**
     * 。<br>
     * @throws java.lang.Exception
     */
    @Before
    public void setUp() throws Exception {
        uraYAMLResource = new UraYAMLResource("test_re");
    }

    /**
     * {@link org.uranoplums.typical.resource.UraYAMLResource#UraYAMLResource(java.lang.String)} のためのテスト・メソッド。
     */
    @Test
    public void testUraYAMLResource() {
        String data1 = uraYAMLResource.getResourceString("data1");
        System.out.println("data1:" + data1);
        assertTrue(true);
    }

    @Test
    public void testUraYAMLResource2() {
        String data1 = uraYAMLResource.getResourceString("come");
        System.out.println("come string:" + data1);
        assertTrue(true);
    }

    /**
     * {@link org.uranoplums.typical.resource.AbsUraHierarchyResource#getResourceString(java.lang.String, java.lang.String[])} のためのテスト・メソッド。
     */
    @Test
    public void testGetResourceStringStringStringArray() {
        String[] data2 = uraYAMLResource.getResourceStringArray("data2");
        System.out.println("data4:" + Arrays.toString(data2));
        assertTrue(true);
    }
    @Test
    public void testGetResourceStringStringStringArray2() {
        List<Object> data2 = uraYAMLResource.getResourceList("data2");
        System.out.println("data2:" + data2);
        assertTrue(true);
    }

    @Test
    public void testUraYAMLResource3() {
        Map<Object, Object> data3 = uraYAMLResource.getResourceMap("data3");
        System.out.println("maps are:" + data3);
        assertTrue(true);
    }

}
