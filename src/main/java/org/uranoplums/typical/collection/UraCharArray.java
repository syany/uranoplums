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
 * $Id: UraCharArray.java$
 */
package org.uranoplums.typical.collection;

import static org.uranoplums.typical.collection.factory.UraListFactory.*;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;


/**
 * UraCharArrayクラス。<br>
 *
 * @since 2015/12/07
 * @author syany
 */
public class UraCharArray extends AbsUraArray {
    /**  */
    private static final long serialVersionUID = 4416395593038843896L;

    /**
     * 。<br>
     * @param array
     * @return
     */
    public static final List<Character> asList(char... array) {
        UraCharArray res = new UraCharArray(array);
        return res.asList();
    }

    /**
     * 。<br>
     * @param list
     * @return
     */
    public static final char[] toArray(Collection<? extends Character> list) {
        UraCharArray res = new UraCharArray(list);
        return res.getArray();
    }

    public UraCharArray() {
        super(char.class);
    }

    public UraCharArray(Collection<? extends Character> list) {
        super(char.class, list.size());
        for (char i : list) {
            this.add(i);
        }
    }

    public UraCharArray(int capacity) {
        super(char.class, capacity);
    }

    public UraCharArray(char... array) {
        super(char.class, array.length);
        this.addAll(array);
    }

    public UraCharArray add(char value) {
        ensureCapacity(this.size + 1);
        char[] array = (char[]) this.elementData;
        array[(this.size++)] = value;
        return this;
    }

    public UraCharArray add(int index, char value) {
        if ((index > this.size) || (index < 0)) {
            throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + this.size);
        }
        ensureCapacity(this.size + 1);
        System.arraycopy(this.elementData, index, this.elementData, index + 1, this.size - index);
        char[] array = (char[]) this.elementData;
        array[index] = value;
        this.size += 1;
        return this;
    }

    /**
     * 。<br>
     * @param paramInt
     * @param array
     * @return
     */
    public boolean addAll(int paramInt, char... array) {
        if ((paramInt > this.size) || (paramInt < 0)) {
            throw new IndexOutOfBoundsException("Index: " + paramInt + ", Size: " + this.size);
        }
        int i = array.length;
        ensureCapacity(this.size + i);
        int j = this.size - paramInt;
        if (j > 0) {
            System.arraycopy(this.elementData, paramInt, this.elementData, paramInt + i, j);
        }
        System.arraycopy(array, 0, this.elementData, paramInt, i);
        this.size += i;
        return i != 0;
    }

    /**
     * 。<br>
     * @param array
     * @return
     */
    public boolean addAll(char... array) {
        int i = array.length;
        ensureCapacity(this.size + i);
        System.arraycopy(array, 0, this.elementData, this.size, i);
        this.size += i;
        return i != 0;
    }

    /**
     * 。<br>
     * @return
     */
    public List<Character> asList() {
        List<Character> res = newArrayList(this.size);
        for (char i : this.getArray()) {
            res.add(Character.valueOf(i));
        }
        return res;
    }

    /*
     * (非 Javadoc)
     *
     * @see org.uranoplums.typical.collection.AbsUraArray#clear()
     */
    @Override
    public UraCharArray clear() {
        return (UraCharArray) super.clear();
    }

    public boolean contains(char value) {
        return indexOf(value) >= 0;
    }

    public boolean containsAll(char... array) {
        boolean res = false;
        for (char i : array) {
            res = this.contains(i);
            if (!res) {
                break;
            }
        }
        return res;
    }

    public char get(int paramInt) {
        RangeCheck(paramInt);
        char[] array = (char[]) this.elementData;
        return array[paramInt];
    }

    public char[] getArray() {
        return (char[]) this.copyOfRange(0, this.size);
    }

    public char[] getArray(int start) {
        return (char[]) this.copyOfRange(start, this.size);
    }

    public char[] getArray(int start, int end) {
        return (char[]) this.copyOfRange(start, end + 1, this.size);
    }

    /*
     * (非 Javadoc)
     *
     * @see org.uranoplums.typical.lang.UraObject#hashCode()
     */
    @Override
    public int hashCode() {
        return Arrays.hashCode(this.getArray());
    }

    public int indexOf(char value) {
        int j = 0;
        for (char i : this.getArray()) {
            if (i == value) {
                return j;
            }
            j++;
        }
        return -1;
    }

    public int lastIndexOf(char paramObject) {
        int rindex = this.size - 1;
        for (; rindex >= 0; rindex--) {
            if (this.get(rindex) == paramObject) {
                break;
            }
        }
        return rindex;
    }

    public boolean removeAll(char... array) {
        boolean res = false;
        for (char i : array) {
            if (res) {
                this.removeValue(i);
            } else {
                res = this.removeValue(i);
            }
        }
        this.resize();
        return res;
    }

    public char removeIndex(int paramInt) {
        RangeCheck(paramInt);
        char localObject = this.get(paramInt);
        this.fastRemove(paramInt);
        return localObject;
    }

    public boolean removeValue(char value) {
        boolean res = false;
        for (int i = this.indexOf(value); i >= 0; i = this.indexOf(value)) {
            fastRemove(i);
            res = true;
        }
        this.resize();
        return res;
    }

    /*
     * (非 Javadoc)
     *
     * @see org.uranoplums.typical.collection.AbsUraArray#resetElement(int)
     */
    @Override
    protected void resetElement(int index) {
        Array.setChar(this.elementData, index, Character.MIN_VALUE);
    }

    public boolean retainAll(char... array) {
        boolean res = false;
        UraCharArray del = new UraCharArray();
        char[] selfArray = (char[]) this.elementData;
        for (char i : selfArray) {
            boolean t = false;
            for (int j : array) {
                if (i == j) {
                    t = true;
                    break;
                }
            }
            if (!t) {
                del.add(i);
            }
        }
        if (!del.isEmpty()) {
            this.removeAll(del.getArray());
            res = true;
        }
        return res;
    }

    public char set(int paramInt, char paramE) {
        RangeCheck(paramInt);
        char[] array = (char[]) this.elementData;
        char localObject = array[paramInt];
        array[paramInt] = paramE;
        return localObject;
    }

    /*
     * (非 Javadoc)
     *
     * @see org.uranoplums.typical.collection.AbsUraArray#sort()
     */
    @Override
    public UraCharArray sort() {
        char[] array = this.getArray();
        Arrays.sort(array);
        System.arraycopy(array, 0, this.elementData, 0, array.length);
        return this;
    }

    /*
     * (非 Javadoc)
     *
     * @see org.uranoplums.typical.collection.AbsUraArray#sort(int, int)
     */
    @Override
    public UraCharArray sort(int start, int end) {
        char[] array = this.getArray();
        Arrays.sort(array, start, end + 1);
        System.arraycopy(array, 0, this.elementData, 0, array.length);
        return this;
    }

    /*
     * (非 Javadoc)
     *
     * @see org.uranoplums.typical.lang.UraObject#toString()
     */
    @Override
    public String toString() {
        return Arrays.toString(this.getArray());
    }
}
