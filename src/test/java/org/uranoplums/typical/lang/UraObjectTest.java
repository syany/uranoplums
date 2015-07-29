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
 * $Id: UraObjectTest.java$
 */
package org.uranoplums.typical.lang;

import static org.junit.Assert.*;
import static org.uranoplums.typical.collection.factory.UraListFactory.*;
import static org.uranoplums.typical.collection.factory.UraMapFactory.*;

import java.util.List;
import java.util.Map;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.uranoplums.typical.lang.builder.UraMultiLineToStringStyle;
import org.uranoplums.typical.lang.builder.UraToStringBuilder;

/**
 * UraObjectTestクラス。<br>
 *
 * @since 2015/07/09
 * @author syany
 */
public class UraObjectTest {

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
     * {@link org.uranoplums.typical.lang.UraObject#toString()} のためのテスト・メソッド。
     */
    @Test
    public void testToString() {
        final Ken ken = new Ken();
        System.out.println("Dump:");
        System.out.println(ken.toStringMulti());
        assertTrue(true);
    }

    @Test
    public void testIncludeFieldNamesPerttern() {

        final LimitToStringObject obj = new LimitToStringObject();
        String output = new UraToStringBuilder(obj)
            .setIncludeFieldNamesPerttern("i")
            .setExcludeFieldNames("id")
            .toString();
        System.out.println("res: {");
        System.out.println(output);
        System.out.println("}");
        assertTrue(true);
    }
}

class LimitToStringObject {
    int id = 890;
    long max = 60000;
    String limitstr = "制限付きオブジェクト";
    List<String> list = newArrayList();
    {
        list.add("345");
        list.add("we3");
        list.add("12r");
    }
}

class Ken {
    public String abc = "testMemberABC";
    public Aki aki = new Aki();
    {
        aki.id = "ggg";
        aki.list.add("345");
        aki.list.add("we3");
        aki.list.add("12r");
        aki.map.put("rrr", "ptr");
        aki.map.put("uio", new String[2]);
        //hogeList.map.put("tyu", new HogeList());
    }
    public String toStringMulti() {
//        return UraToStringBuilder.reflectionToString(this, UraMultiLineToStringStyle.INSTANCE);
        //return UraToStringBuilder.reflectionToString(this, UraMultiLineToStringStyle.INSTANCE);
        return new UraToStringBuilder(this, UraMultiLineToStringStyle.INSTANCE).toString();
    }
}
class Aki {
    public String id = "12";
    public String name = "hoge name";
    public int age = 32;
    public List<String> list = newArrayList();
    public Map<String, Object> map = newHashMap();
    /* (非 Javadoc)
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        //return UraToStringBuilder.reflectionToString(this, UraMultiLineToStringStyle.INSTANCE);
        return new UraToStringBuilder(this, UraMultiLineToStringStyle.INSTANCE).toString();
    }

}