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
 * $Id: UraCollectionUtilsTest.java$
 */
package org.uranoplums.typical.collection;

import static org.junit.Assert.*;
import static org.uranoplums.typical.collection.factory.UraListFactory.*;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;


/**
 * UraCollectionUtilsTestクラス。<br>
 *
 * @since 2015/11/02
 * @author syany
 */
public class UraCollectionUtilsTest {
    List<String> list;
    List<String> listDuplex;
    List<String> listUnique;

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
        list = newArrayList(16);
        listDuplex = newArrayList(16);
        listUnique = newArrayList(16);
        list.add("acb");
        list.add("acb1");
        list.add("acb2");
        list.add("acb3");
        list.add("acb4");
        list.add("acb5");
        list.add("acb2");
        list.add("acb3");
        list.add("acb4");
        list.add("acb5");
        list.add("acb");
        listDuplex.add("acb2");
        listDuplex.add("acb3");
        listDuplex.add("acb4");
        listDuplex.add("acb5");
        listDuplex.add("acb");
        listUnique.add("acb");
        listUnique.add("acb1");
        listUnique.add("acb2");
        listUnique.add("acb3");
        listUnique.add("acb4");
        listUnique.add("acb5");
    }

    /**
     * 。<br>
     * @throws java.lang.Exception
     */
    @After
    public void tearDown() throws Exception {}

    /**
     * {@link org.uranoplums.typical.collection.UraCollectionUtils#isDuplicate(java.util.Collection)} のためのテスト・メソッド。
     */
    @Test
    public final void testIsDuplicate() {
        assertTrue(UraCollectionUtils.isDuplicate(list));
    }

    /**
     * {@link org.uranoplums.typical.collection.UraCollectionUtils#isUnique(java.util.Collection)} のためのテスト・メソッド。
     */
    @Test
    public final void testIsUnique() {
        assertTrue(UraCollectionUtils.isUnique(listUnique));
    }

    /**
     * {@link org.uranoplums.typical.collection.UraCollectionUtils#getDuplicateKeys(java.util.Collection)} のためのテスト・メソッド。
     */
    @Test
    public final void testGetDuplicateList() {
        assertEquals(UraListUtils.getDuplicateList(list), this.listDuplex);
    }
    @Test
    public final void testGetDuplicateSet() {
        Set<String> actualSet = new HashSet<String>(this.listDuplex);
        assertEquals(UraSetUtils.getDuplicateSet(list), actualSet);
    }

    /**
     * {@link org.uranoplums.typical.collection.UraCollectionUtils#getUnique(java.util.Collection)} のためのテスト・メソッド。
     */
    @Test
    public final void testGetUnique() {
        List<String> exList = newArrayList(UraCollectionUtils.getUnique(list));
        assertEquals(exList, this.listUnique);
    }

    /**
     * {@link org.uranoplums.typical.collection.UraCollectionUtils#getUniqueList(java.util.Collection)} のためのテスト・メソッド。
     */
    @Test
    public final void testGetUniqueList() {
        assertEquals(UraListUtils.getUniqueList(list), this.listUnique);
    }

    /**
     * {@link org.uranoplums.typical.collection.UraCollectionUtils#addUnique(java.util.Collection, java.util.Collection)} のためのテスト・メソッド。
     */
    @Test
    public final void testAddUnique() {
        List<String> exList = newArrayList();
        assertEquals(UraCollectionUtils.addUnique(list, exList), this.listUnique);

    }
    /**
     * {@link org.uranoplums.typical.collection.UraCollectionUtils#addUnique(java.util.Collection, java.util.Collection)} のためのテスト・メソッド。
     */
    @Test
    public final void testAddUnique02() {
        Set<String> exSet = new HashSet<String>();
        Set<String> actualSet = new HashSet<String>(this.listUnique);
        assertEquals(UraCollectionUtils.addUnique(list, exSet), actualSet);

    }
}
