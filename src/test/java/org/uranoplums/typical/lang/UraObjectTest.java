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
import org.apache.commons.lang3.builder.ToStringStyle;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.uranoplums.typical.lang.builder.UraMultiLineToStringStyle;
import org.uranoplums.typical.lang.builder.UraToStringBuilder;

class Aki {

    public int age = 32;
    public String id = "12";
    public List<String> list = newArrayList();
    public Map<String, Object> map = newHashMap();
    public String name = "hoge name";

    /*
     * (非 Javadoc)
     *
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        // return UraToStringBuilder.reflectionToString(this, UraMultiLineToStringStyle.INSTANCE);
        return new UraToStringBuilder(this, UraMultiLineToStringStyle.INSTANCE).toString();
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
        // hogeList.map.put("tyu", new HogeList());
    }

    public String toStringMulti() {
        // return UraToStringBuilder.reflectionToString(this, UraMultiLineToStringStyle.INSTANCE);
        // return UraToStringBuilder.reflectionToString(this, UraMultiLineToStringStyle.INSTANCE);
        return new UraToStringBuilder(this, UraMultiLineToStringStyle.INSTANCE).toString();
    }
}

class PxxSearch {

    int pxxId = 890;
    String pxxLimitstr = "制限付きオブジェクト";
    List<String> pxxList = newArrayList();
    long pxxMax = 60000;
    {
        pxxList.add("345");
        pxxList.add("we3");
        pxxList.add("12r");
    }
}

class ResultInfo extends UraObject {

    public String firstName = "テスト";
    public String lastName = "田中";
    public SubResultInfo sub = new SubResultInfo();
}

class ResultInfo2 {

    public String firstName = "テスト２";
    public String lastName = "中村";
    public SubResultInfo2 sub = new SubResultInfo2();

    @Override
    public String toString() {
        // デフォルトスタイルを一時保管し、マルチスタイルに変更する
        ToStringStyle toStringStyle = ToStringBuilder.getDefaultStyle();
        ToStringBuilder.setDefaultStyle(UraMultiLineToStringStyle.INSTANCE);
        // toStringの実行
        String result = ToStringBuilder.reflectionToString(this);
        // 元に戻す。
        ToStringBuilder.setDefaultStyle(toStringStyle);
        return result;
    }
}

class ScChildSearch extends UraObject {

    String comment = "抜けない。。。";
    int scAge = 98;
}

class ScSearch extends UraObject {

    ScChildSearch scChild = new ScChildSearch();
    int scId = 890;
    String scLimitstr = "制限付きオブジェクト";
    List<String> scList = newArrayList();
    long scMax = 60000;
    {
        scList.add("345");
        scList.add("we3");
        scList.add("12r");
    }
}

class SubResultInfo extends UraObject {

    public String comment = "趣味は散歩です。";
    public int length = 122;
}

class SubResultInfo2 {

    public String comment = "趣味は登山です。";
    public int length = 122;

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }

}

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

    @Test
    public void testIncludeFieldNamesPerttern() {
        final PxxSearch pxxSearch = new PxxSearch();
        String output = new UraToStringBuilder(pxxSearch)
                .setIncludeFieldNamesPerttern("^pxx")
                .setExcludeFieldNames("pxxId")
                .toString();
        System.out.println("res: {");
        System.out.println(output);
        System.out.println("}");
        assertTrue(true);
    }

    @Test
    public void testToMultiString() {
        ResultInfo info = new ResultInfo();
        System.out.println(info.toMultiString());
        assertTrue(true);
    }

    @Test
    public void testToMultiStringFilter() {
        final ScSearch scSearch = new ScSearch();
        String output = scSearch.toMultiStringFilter("^sc");
        System.out.println("res:");
        System.out.println(output);
        System.out.println();
        assertTrue(true);
    }

    @Test
    public void testToMultiStringOther() {
        ResultInfo2 info2 = new ResultInfo2();
        System.out.println(info2.toString());
        assertTrue(true);
    }

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
}