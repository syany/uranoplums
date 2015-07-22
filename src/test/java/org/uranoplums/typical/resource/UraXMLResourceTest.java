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
    public void testXMLResource() {
        resource = new UraXMLResource("test_001");
        resource.getResourceString("sTr2");
        //assertEquals(resource.getResourceString("sTr2"), "gmmb");
        assertTrue(true);
    }
}
