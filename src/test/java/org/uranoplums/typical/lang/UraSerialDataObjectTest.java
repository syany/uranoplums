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
 * $Id: UraSerialDataObjectTest.java$
 */
package org.uranoplums.typical.lang;

import static org.junit.Assert.*;
import static org.uranoplums.typical.collection.factory.UraMapFactory.*;

import java.util.Map;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.uranoplums.typical.log.UraLog;
import org.uranoplums.typical.log.UraLoggerFactory;

/**
 * UraSerialDataObjectTestクラス。<br>
 *
 * @since 2015/08/04
 * @author syany
 */
public class UraSerialDataObjectTest {

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
     * {@link org.uranoplums.typical.lang.UraSerialDataObject#deepClone()} のためのテスト・メソッド。
     */
    @Test
    public void testDeepClone01() {
        CarSerialDataObject car01 = new CarSerialDataObject();
        CarSerialDataObject car02 = car01.deepClone();
        CarSerialDataObject car03 = (CarSerialDataObject) car01.clone();
        // car01のmap内の内容だけ変更
        System.out.println("car01.map.sheet: parper --> wood");
        car01.map.put("sheet", "woods");
        // 比較
        System.out.println("equals[car01 car02]:" + car01.equals(car02) + "\t:car02s sheet[" + car02.map.get("sheet") + "]");
        System.out.println("equals[car01 car03]:" + car01.equals(car03) + "\t:car03s sheet[" + car03.map.get("sheet") + "]");
        assertTrue(!car01.equals(car02));
    }

    @Test
    public void testDeepClone02() {
        CarSerialDataObject car01 = new CarSerialDataObject();
        CarSerialDataObject car02 = car01.deepClone();
        CarSerialDataObject car03 = (CarSerialDataObject) car01.clone();
        car01.child.id = 78;
        System.out.println("equals[car01 car02]:" + car01.equals(car02) + ":car02s child.id[" + car02.child.id + "]");
        System.out.println("equals[car01 car03]:" + car01.equals(car03) + ":car03s child.id[" + car03.child.id + "]");
        assertTrue(!car01.equals(car02));
    }

    @Test
    public void testDeepClone03() {
        CarSerialDataObject car01 = new CarSerialDataObject();
        CarSerialDataObject car02 = car01.deepClone();
        car01.map.put("color", "orange");
        car02.child.name = "tom";
        assertTrue(car01.equals(car02));
    }

    /**
     * {@link org.uranoplums.typical.lang.UraDataObject#compareTo(org.uranoplums.typical.lang.UraDataObject)}
     * のためのテスト・メソッド。
     */
    @Test
    public void testCompareTo() {
        final CarDataObject car01 = new CarDataObject();
        final CarDataObject car02 = new CarDataObject();
        System.out.println("CompareTo[car01 car02]:" + car01.compareTo(car02));
        assertTrue(car01.compareTo(car02) == 0);
    }
}

class CarDataObject extends UraDataObject {

    int id = 3400;
    String name = "ken";
    UraLog log = UraLoggerFactory.getUraLog();
}

class ChildSerialDataObject extends UraSerialDataObject {

    int id = 56;
    String name = "tom";
    UraLog log = UraLoggerFactory.getUraLog();
}

class CarSerialDataObject extends UraSerialDataObject {

    public int no = 500;
    public ChildSerialDataObject child = new ChildSerialDataObject();
    public Map<String, Object> map = newHashMap();
    {
        map.put("sheet", "parper");
        map.put("color", "orange");
    }
}