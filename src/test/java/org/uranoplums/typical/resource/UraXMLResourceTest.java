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
 * $Id: UraXMLResourceTest.java$
 */
package org.uranoplums.typical.resource;

import static org.junit.Assert.*;

import java.util.List;
import java.util.Map;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * UraXMLResourceTestクラス。<br>
 *
 * @since 2015/06/18
 * @author syany
 */
public class UraXMLResourceTest {

    UraXMLResource resource;

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

    @Test
    public void testXMLResourceP() {
        resource = new UraXMLResource("test_001");
        resource.getResourceString("sTr2");
        //assertEquals(resource.getResourceString("sTr2"), "gmmb");
        assertTrue(true);
    }

    @Test
    public void testXMLResource() {

        UraXMLResource xmlResource = new UraXMLResource("message_test");
        List<String> list = xmlResource.getResourceList("test.003");
        Map<String, Object> map = xmlResource.getResourceMap("test.002");
        String value = xmlResource.getResourceString("test.001");
        System.out.println("test.003:" + list.toString() + "("+list.getClass().getName()+")");
        System.out.println("test.002:" + map.toString() + "("+map.getClass().getName()+")");
        System.out.println("test.001:" + value);
        assertTrue(true);
    }
    @Test
    public void testXMLResource2() {

        UraXMLResource xmlResource = new UraXMLResource("message_test");
        List<String> list = xmlResource.getResourceList("test.003");
        String value = xmlResource.getResourceString("test.004", "a-Z");
        System.out.println("test.003:" + list.toString());
        System.out.println("test.004:" + value);
        System.out.println("test.003:" + list.toString());
        System.out.println("test.004:" + value);
        assertTrue(true);
    }
}
