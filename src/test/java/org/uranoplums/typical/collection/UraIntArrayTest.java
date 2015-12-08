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
 * $Id: UraIntArrayTest.java$
 */
package org.uranoplums.typical.collection;

import static org.junit.Assert.*;
import static org.uranoplums.typical.collection.factory.UraListFactory.*;

import java.util.Arrays;
import java.util.List;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;


/**
 * UraIntArrayTestクラス。<br>
 *
 * @since 2015/12/02
 * @author syany
 */
public class UraIntArrayTest {

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
     * {@link org.uranoplums.typical.collection.UraIntArray#hashCode()} のためのテスト・メソッド。
     */
    @Test
    public final void testHashCode() {
        int[] ext = new int[] {2, 56, 56};
        UraIntArray a = new UraIntArray(ext);
        assertEquals("Hash", Arrays.hashCode(ext), a.hashCode());
    }

    /**
     * {@link org.uranoplums.typical.collection.UraIntArray#equals(java.lang.Object)} のためのテスト・メソッド。
     */
    @Test
    public final void testEqualsObject() {
        int[] ext = new int[] {2, 56, 56};
        UraIntArray a = new UraIntArray(ext);
        assertTrue(a.equals(a.clone()));
    }

    /**
     * {@link org.uranoplums.typical.collection.UraIntArray#toString()} のためのテスト・メソッド。
     */
    @Test
    public final void testToString() {
        int[] ext = new int[] {2, 56, 56};
        UraIntArray a = new UraIntArray(ext);
        System.out.println("array:"+a.toString()+", size:"+a.size());
        assertEquals(Arrays.toString(ext), a.toString());
    }

    /**
     * {@link org.uranoplums.typical.collection.UraIntArray#sort(int, int)} のためのテスト・メソッド。
     */
    @Test
    public final void testSortIntInt() {
        int[] ext = new int[] {2, 56, 56, 32, 14, 0, 409};
        int[] ext2 = new int[] {2, 14, 32, 56, 56, 0, 409};
        UraIntArray a = new UraIntArray(ext);
        a.sort(1, 4);
        System.out.println("array:"+a.toString()+", size:"+a.size());
        assertArrayEquals(ext2, a.getArray());
    }

    /**
     * {@link org.uranoplums.typical.collection.UraIntArray#sort()} のためのテスト・メソッド。
     */
    @Test
    public final void testSort() {
        int[] ext = new int[] {2, 56, 56, 32, 14, 0, 409};
        int[] ext2 = new int[] {0, 2, 14, 32, 56, 56, 409};
        UraIntArray a = new UraIntArray(ext);
        a.sort();
        System.out.println("array:"+a.toString()+", size:"+a.size());
        assertArrayEquals(ext2, a.getArray());
    }

    /**
     * {@link org.uranoplums.typical.collection.UraIntArray#resetElement(int)} のためのテスト・メソッド。
     */
    @Test
    public final void testResetElement() {
        int[] ext = new int[] {2, 56, 56, 32, 14, 0, 409};
        int[] ext2 = new int[] {2, 0, 56, 0, 14, 0, 409};
        UraIntArray a = new UraIntArray(ext);
        a.resetElement(1);
        a.resetElement(3);
        System.out.println("array:"+a.toString()+", size:"+a.size());
        assertArrayEquals(ext2, a.getArray());
    }

    /**
     * {@link org.uranoplums.typical.collection.UraIntArray#UraIntArray()} のためのテスト・メソッド。
     */
    @Test
    public final void testUraIntArray() {
        UraIntArray a = new UraIntArray();
        System.out.println("array:"+a.toString()+", size:"+a.size());
        assertTrue(a != null);
    }

    /**
     * {@link org.uranoplums.typical.collection.UraIntArray#UraIntArray(int)} のためのテスト・メソッド。
     */
    @Test
    public final void testUraIntArrayInt() {
        UraIntArray a = new UraIntArray(23);
        System.out.println("array:"+a.toString()+", size:"+a.size()+", actLength:"+a.actualLength);
        assertEquals(23, a.actualLength);
    }

    /**
     * {@link org.uranoplums.typical.collection.UraIntArray#UraIntArray(int[])} のためのテスト・メソッド。
     */
    @Test
    public final void testUraIntArrayIntArray() {
        int[] ext = new int[] {2, 56, 56, 32, 14, 0, 409};
        UraIntArray a = new UraIntArray(ext);
        System.out.println("array:"+a.toString()+", size:"+a.size());
        assertArrayEquals(ext, a.getArray());
    }

    /**
     * {@link org.uranoplums.typical.collection.UraIntArray#UraIntArray(java.util.Collection)} のためのテスト・メソッド。
     */
    @Test
    public final void testUraIntArrayCollectionOfQextendsInteger() {
        List<Integer> l = newArrayList();
        l.add(0);
        l.add(8);
        l.add(77);
//        int[] ext = new int[] {0, 8, 77};
        UraIntArray a = new UraIntArray(l);
        System.out.println("array:"+a.toString()+", size:"+a.size());
        assertArrayEquals(UraIntArray.toArray(l), a.getArray());
    }

//    /**
//     * {@link org.uranoplums.typical.collection.UraIntArray#binarySearch(int)} のためのテスト・メソッド。
//     */
//    @Test
//    public final void testBinarySearchInt() {
//        int[] ext = new int[] {2, 56, 56, 32, 14, 0, 409};
//        UraIntArray a = new UraIntArray(ext);
//        System.out.println("array:"+a.toString()+", size:"+a.size() + ", binarySearch:"+ Arrays.binarySearch((int[])a.sort(), 14));
//        assertEquals(4, a.binarySearch(14));
//    }
//
//    /**
//     * {@link org.uranoplums.typical.collection.UraIntArray#binarySearch(int, int)} のためのテスト・メソッド。
//     */
//    @Test
//    public final void testBinarySearchIntInt() {
//        fail("まだ実装されていません"); // TODO
//    }
//
//    /**
//     * {@link org.uranoplums.typical.collection.UraIntArray#binarySearch(int, int, int)} のためのテスト・メソッド。
//     */
//    @Test
//    public final void testBinarySearchIntIntInt() {
//        fail("まだ実装されていません"); // TODO
//    }

    /**
     * {@link org.uranoplums.typical.collection.UraIntArray#indexOf(int)} のためのテスト・メソッド。
     */
    @Test
    public final void testIndexOf() {
      int[] ext = new int[] {2, 56, 56, 32, 14, 0, 409};
      UraIntArray a = new UraIntArray(ext);
      System.out.println("array:"+a.toString()+", size:"+a.size());
      assertEquals(4, a.indexOf(14));
    }

    /**
     * {@link org.uranoplums.typical.collection.UraIntArray#contains(int)} のためのテスト・メソッド。
     */
    @Test
    public final void testContains() {
        int[] ext = new int[] {2, 56, 57, 45, 89};
        UraIntArray a = new UraIntArray(ext);
        System.out.println("string:["+a.toString()+"], size["+a.size()+"]");
        assertTrue(a.contains(57));
    }

    /**
     * {@link org.uranoplums.typical.collection.UraIntArray#containsAll(int[])} のためのテスト・メソッド。
     */
    @Test
    public final void testContainsAll() {
        int[] ext = new int[] {2, 56, 56, 32, 14, 0, 409};
        int[] ext2 = new int[] {2, 32};
        UraIntArray a = new UraIntArray(ext);
        System.out.println("array:"+a.toString()+", size:"+a.size());
        assertEquals(true, a.containsAll(ext2));
    }

    /**
     * {@link org.uranoplums.typical.collection.UraIntArray#getArray()} のためのテスト・メソッド。
     */
    @Test
    public final void testGetArray() {
        int[] ext = new int[] {2, 56, 57, 45, 89};
        UraIntArray a = new UraIntArray(ext);
        System.out.println("string:["+a.getArray().toString()+"], size["+a.getArray().length+"]");
        assertArrayEquals(ext, a.getArray());
    }

    /**
     * {@link org.uranoplums.typical.collection.UraIntArray#getArray(int)} のためのテスト・メソッド。
     */
    @Test
    public final void testGetArrayInt() {
        int[] ext = new int[] {2, 56, 57, 45, 89};
        int[] ext2 = new int[] { 57, 45, 89};
        UraIntArray a = new UraIntArray(ext);
        System.out.println("string:["+a.getArray(2).toString()+"], size["+a.getArray(2).length+"]");
        assertArrayEquals(ext2, a.getArray(2));
    }

    /**
     * {@link org.uranoplums.typical.collection.UraIntArray#getArray(int, int)} のためのテスト・メソッド。
     */
    @Test
    public final void testGetArrayIntInt() {
        int[] ext = new int[] {2, 56, 57, 45, 89};
        int[] ext2 = new int[] { 57, 45};
        UraIntArray a = new UraIntArray(ext);
        System.out.println("string:["+a.getArray(2,3).toString()+"], size["+a.getArray(2, 3).length+"]");
        assertArrayEquals(ext2, a.getArray(2, 3));
    }

    /**
     * {@link org.uranoplums.typical.collection.UraIntArray#add(int)} のためのテスト・メソッド。
     */
    @Test
    public final void testAddInt() {
        int[] ext = new int[] {2, 56, 56};
        UraIntArray a = new UraIntArray(0);
        for(int i: ext) {
            a.add(i);
        }
        System.out.println("string:["+a.toString()+"], size["+a.size()+"]");
        assertArrayEquals(ext, a.getArray());
    }

    /**
     * {@link org.uranoplums.typical.collection.UraIntArray#add(int, int)} のためのテスト・メソッド。
     */
    @Test
    public final void testAddIntInt() {
        int[] ext = new int[] {2, 56, 57};
        UraIntArray a = new UraIntArray(1);
        a.add(ext[1]);
        a.add(1, ext[2]);
        a.add(0, ext[0]);
        System.out.println("string:["+a.toString()+"], size["+a.size()+"]");
        assertArrayEquals(ext, a.getArray());
    }

    /**
     * {@link org.uranoplums.typical.collection.UraIntArray#get(int)} のためのテスト・メソッド。
     */
    @Test
    public final void testGet() {
        int[] ext = new int[] {2, 56, 56, 78};
        UraIntArray a = new UraIntArray(ext);
        System.out.println("string:["+a.toString()+"], size["+a.size()+"]");
        assertEquals(78, a.get(3));
    }

    /**
     * {@link org.uranoplums.typical.collection.UraIntArray#set(int, int)} のためのテスト・メソッド。
     */
    @Test
    public final void testSet() {
        int[] ext = new int[] {2, 56, 56, 78};
        int chg = 2000;
        int[] ext2 = new int[] {2, 56, chg, 78};
        UraIntArray a = new UraIntArray(ext);
        a.set(2, chg);
        System.out.println("string:["+a.toString()+"], size["+a.size()+"]");
        assertArrayEquals(ext2, a.getArray());
    }

    /**
     * {@link org.uranoplums.typical.collection.UraIntArray#removeIndex(int)} のためのテスト・メソッド。
     */
    @Test
    public final void testRemoveIndex() {
        int[] ext = new int[] {2, 56, 56, 78};
        int[] ext2 = new int[] {2, 56, 78};
        UraIntArray a = new UraIntArray(ext);
        a.removeIndex(2);
        System.out.println("string:["+a.toString()+"], size["+a.size()+"]");
        assertArrayEquals(ext2, a.getArray());
    }

    /**
     * {@link org.uranoplums.typical.collection.UraIntArray#removeValue(int)} のためのテスト・メソッド。
     */
    @Test
    public final void testRemoveValue() {
        int[] ext = new int[] {2, 56, 56, 78};
        int[] ext2 = new int[] {2, 78};
        UraIntArray a = new UraIntArray(ext);
        a.removeValue(ext[1]);
        System.out.println("string:["+a.toString()+"], size["+a.size()+"]");
        assertArrayEquals(ext2, a.getArray());
    }

    /**
     * {@link org.uranoplums.typical.collection.UraIntArray#removeAll(int[])} のためのテスト・メソッド。
     */
    @Test
    public final void testRemoveAll() {
        int[] ext = new int[] {2, 56, 56, 78, 987};
        int[] ext2 = new int[] {2, 78};
        int[] ext3 = new int[] {56, 987};
        UraIntArray a = new UraIntArray(ext);
        a.removeAll(ext3);
        System.out.println("string:["+a.toString()+"], size["+a.size()+"]");
        assertArrayEquals(ext2, a.getArray());
    }

    /**
     * {@link org.uranoplums.typical.collection.UraIntArray#retainAll(int[])} のためのテスト・メソッド。
     */
    @Test
    public final void testRetainAll() {
        int[] ext = new int[] {2, 56, 57, 78};
        int[] ext1 = new int[] {2, 78};
        UraIntArray a = new UraIntArray(ext);
        a.retainAll(ext1);
        System.out.println("string:["+a.toString()+"], size["+a.size()+"]");
        assertArrayEquals(ext1, a.getArray());
    }

    /**
     * {@link org.uranoplums.typical.collection.UraIntArray#lastIndexOf(int)} のためのテスト・メソッド。
     */
    @Test
    public final void testLastIndexOf() {
        int[] ext = new int[] {2, 56, 56, 32, 14, 56, 409};
        UraIntArray a = new UraIntArray(ext);
        System.out.println("array:"+a.toString()+", size:"+a.size());
        assertEquals(5, a.lastIndexOf(56));
    }

    /**
     * {@link org.uranoplums.typical.collection.UraIntArray#addAll(int[])} のためのテスト・メソッド。
     */
    @Test
    public final void testAddAllIntArray() {
        int[] ext = new int[] {2, 56, 57};
        UraIntArray a = new UraIntArray(0);
        a.addAll(ext);
        System.out.println("string:["+a.toString()+"], size["+a.size()+"]");
        assertArrayEquals(ext, a.getArray());
    }

    /**
     * {@link org.uranoplums.typical.collection.UraIntArray#addAll(int, int[])} のためのテスト・メソッド。
     */
    @Test
    public final void testAddAllIntIntArray() {
        int[] ext = new int[] {2, 56, 57};
        int[] ext1 = new int[] {56, 57};
        int[] ext2 = new int[] {2};
        UraIntArray a = new UraIntArray(0);
        a.addAll(ext2);
        a.addAll(1, ext1);
        System.out.println("string:["+a.toString()+"], size["+a.size()+"]");
        assertArrayEquals(ext, a.getArray());
    }
    @Test
    public final void testAddAllIntIntArray2() {
        int[] ext = new int[] {2, 56, 57};
        int[] ext1 = new int[] {56, 57};
        int[] ext2 = new int[] {2};
        UraIntArray a = new UraIntArray(0);
        a.addAll(ext1);
        a.addAll(0, ext2);
        System.out.println("string:["+a.toString()+"], size["+a.size()+"]");
        assertArrayEquals(ext, a.getArray());
    }

    /**
     * {@link org.uranoplums.typical.collection.UraIntArray#asList()} のためのテスト・メソッド。
     */
    @Test
    public final void testAsList() {
//        int[] ext = new int[] {2, 56, 0, 409};
        List<Integer> l = newArrayList();
        l.add(2);
        l.add(56);
        l.add(0);
        l.add(409);
        UraIntArray a = new UraIntArray(l);
        System.out.println("array:"+a.toString()+", size:"+a.size());
        assertEquals(l, a.asList());
    }

    /**
     * {@link org.uranoplums.typical.collection.UraIntArray#toArray(java.util.Collection)} のためのテスト・メソッド。
     */
    @Test
    public final void testToArray() {
        int[] ext = new int[] {2, 56, 0, 409};
        List<Integer> l = newArrayList();
        l.add(2);
        l.add(56);
        l.add(0);
        l.add(409);
        assertArrayEquals(ext, UraIntArray.toArray(l));
    }

    /**
     * {@link org.uranoplums.typical.collection.UraIntArray#asList(int[])} のためのテスト・メソッド。
     */
    @Test
    public final void testAsListIntArray() {
        int[] ext = new int[] {2, 56, 0, 409};
        List<Integer> l = newArrayList();
        l.add(2);
        l.add(56);
        l.add(0);
        l.add(409);
        assertEquals(l, UraIntArray.asList(ext));
    }

    /**
     * {@link org.uranoplums.typical.collection.AbsUraArray#clone()} のためのテスト・メソッド。
     */
    @Test
    public final void testClone() {
        int[] ext = new int[] {2, 56, 57, 45, 89};
        UraIntArray a = new UraIntArray(ext);
        System.out.println("clone:" + a.clone());
        assertEquals(a, a.clone());
    }

    /**
     * {@link org.uranoplums.typical.collection.AbsUraArray#isEmpty()} のためのテスト・メソッド。
     */
    @Test
    public final void testIsEmptyTrue() {
        UraIntArray a = new UraIntArray(100);
        assertTrue(a.isEmpty());
    }
    @Test
    public final void testIsEmptyFalse() {
        UraIntArray a = new UraIntArray(100);
        a.add(0);
        assertFalse(a.isEmpty());
    }

    /**
     * {@link org.uranoplums.typical.collection.AbsUraArray#size()} のためのテスト・メソッド。
     */
    @Test
    public final void testSize() {
        UraIntArray a = new UraIntArray(100);
        a.add(0);
        assertEquals(1, a.size());
    }

    @Test
    public final void testSize02() {
        UraIntArray a = new UraIntArray(100);
//        a.add(0);
        assertEquals(0, a.size());
    }

    /**
     * {@link org.uranoplums.typical.collection.AbsUraArray#ensureCapacity(int)} のためのテスト・メソッド。
     */
    @Test
    public final void testEnsureCapacity() {
        UraIntArray a = new UraIntArray(2);
        a.ensureCapacity(a.size+1);
        assertEquals(2, a.actualLength);
    }

    @Test
    public final void testEnsureCapacity01() {
        UraIntArray a = new UraIntArray(0);
        a.ensureCapacity(a.size+1);
        assertEquals(1, a.actualLength);
    }

    /**
     * {@link org.uranoplums.typical.collection.AbsUraArray#copyOf(int)} のためのテスト・メソッド。
     */
    @Test
    public final void testCopyOf() {
        UraIntArray a = new UraIntArray(9);
        a.add(0).add(12).add(34).add(89);
        int[] k = (int[]) a.elementData;
        int[] h = (int[]) a.copyOf(a.actualLength);
        System.out.println("d:" + Arrays.toString(k) + ", t:" + Arrays.toString(h));
        assertArrayEquals(h, k);
    }

    /**
     * {@link org.uranoplums.typical.collection.AbsUraArray#copyOfRange(int, int)} のためのテスト・メソッド。
     */
    @Test
    public final void testCopyOfRangeIntInt() {
        int[] ext = new int[] {2, 56, 57, 45, 89};
        UraIntArray a = new UraIntArray(9);
        a.addAll(ext);
        int[] act = Arrays.copyOfRange((int[]) a.elementData, 3, a.actualLength);
        int[] ext2 = (int[]) a.copyOfRange(3, a.actualLength);
        System.out.println("d:" + Arrays.toString(ext2) + ", t:" + Arrays.toString(act));
        assertArrayEquals(ext2, act);
    }

    /**
     * {@link org.uranoplums.typical.collection.AbsUraArray#copyOfRange(int, int, int)} のためのテスト・メソッド。
     */
    @Test
    public final void testCopyOfRangeIntIntInt() {
        int[] ext = new int[] {2, 56, 57, 45, 89};
        UraIntArray a = new UraIntArray(9);
        a.addAll(ext);
        int[] act = Arrays.copyOfRange((int[]) a.elementData, 2, 4);
        int[] ext2 = (int[]) a.copyOfRange(2, 4, a.actualLength);
        System.out.println("d:" + Arrays.toString(ext2) + ", t:" + Arrays.toString(act));
        assertArrayEquals(ext2, act);
    }

    /**
     * {@link org.uranoplums.typical.collection.AbsUraArray#clear()} のためのテスト・メソッド。
     */
    @Test
    public final void testClear() {
        int[] ext = new int[] {2, 56, 57, 45, 89};
        UraIntArray a = new UraIntArray(ext);
        int[] ext2 = new int[0];
        assertArrayEquals(ext2, a.clear().getArray());
    }

    /**
     * {@link org.uranoplums.typical.collection.AbsUraArray#fastRemove(int)} のためのテスト・メソッド。
     */
    @Test
    public final void testFastRemove() {
        int[] ext = new int[] {2, 56, 57, 45, 89};
        int[] ext2 = new int[] {2, 56, 45, 89};
        UraIntArray a = new UraIntArray(ext);
        a.fastRemove(2);
        assertArrayEquals(ext2, a.getArray());
    }

    /**
     * {@link org.uranoplums.typical.collection.AbsUraArray#removeRange(int, int)} のためのテスト・メソッド。
     */
    @Test
    public final void testRemoveRange() {
        int[] ext = new int[] {2, 56, 57, 45, 89};
        int[] ext2 = new int[] {2, 56, 89};
        UraIntArray a = new UraIntArray(7);
        a.addAll(ext);
        a.removeRange(2, 3);
        assertArrayEquals(ext2, a.getArray());
    }

    @Test
    public final void testSerializeCopy() {
        int[] ext = new int[] {2, 56, 57, 45, 89};
        UraIntArray a = new UraIntArray(ext);
        a.removeIndex(1);
        a.removeValue(56);
        UraIntArray b = a.deepClone();
        assertEquals(a, b);
    }

    @Test
    public final void testCompareTo() {
        int[] ext = new int[] {2, 56, 57, 45, 89};
        UraIntArray a = new UraIntArray(ext);
        a.removeIndex(1);
        a.removeValue(56);
        System.out.println("String:" + a.toMultiStringFilter("a"));
        UraIntArray b = a.deepClone();
        assertEquals(0, a.compareTo(b));
    }
//    /**
//     * {@link org.uranoplums.typical.collection.AbsUraArray#RangeCheck(int)} のためのテスト・メソッド。
//     */
//    @Test
//    public final void testRangeCheck() {
//        fail("まだ実装されていません"); // TODO
//    }
}
