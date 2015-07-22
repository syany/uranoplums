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

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.uranoplums.typical.lang.builder.UraMultiLineToStringStyle;

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
        final Hoge hoge = new Hoge();
        System.out.println("Dump:");
        System.out.println(hoge.toStringMulti());
        assertTrue(true);
    }
}

class Hoge {
    public String abc = "testMemberABC";
    public HogeList hogeList = new HogeList();
    {
        hogeList.id = "ggg";
        hogeList.list.add("345");
        hogeList.list.add("we3");
        hogeList.list.add("12r");
        hogeList.map.put("rrr", "ptr");
        hogeList.map.put("uio", new String[2]);
        //hogeList.map.put("tyu", new HogeList());
    }
    public String toStringMulti() {
//        return UraToStringBuilder.reflectionToString(this, UraMultiLineToStringStyle.INSTANCE);
        return ToStringBuilder.reflectionToString(this, UraMultiLineToStringStyle.INSTANCE);
    }
}
class HogeList {
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
        return ToStringBuilder.reflectionToString(this, UraMultiLineToStringStyle.INSTANCE);
    }

}