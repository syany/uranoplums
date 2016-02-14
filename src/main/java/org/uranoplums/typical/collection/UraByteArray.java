/*
 * Copyright 2013-2016 the Uranoplums Foundation and the Others.
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
 * $Id: UraByteArray.java$
 */
package org.uranoplums.typical.collection;

import static org.uranoplums.typical.collection.factory.UraListFactory.*;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;


/**
 * UraByteArrayクラス。<br>
 *
 * @since 2016/01/19
 * @author syany
 */
public class UraByteArray extends AbsUraArray {
    /**  */
    private static final long serialVersionUID = 6700412986924171058L;

    /**
     * 。<br>
     * @param array
     * @return
     */
    public static final List<Byte> asList(byte... array) {
        UraByteArray res = new UraByteArray(array);
        return res.asList();
    }

    /**
     * 。<br>
     * @param list
     * @return
     */
    public static final byte[] toArray(Collection<? extends Byte> list) {
        UraByteArray res = new UraByteArray(list);
        return res.getArray();
    }

    public UraByteArray() {
        super(byte.class);
    }

    public UraByteArray(Collection<? extends Byte> list) {
        super(byte.class, list.size());
        for (byte i : list) {
            this.add(i);
        }
    }

    public UraByteArray(int capacity) {
        super(byte.class, capacity);
    }

    public UraByteArray(byte... array) {
        super(byte.class, array.length);
        this.addAll(array);
    }

    public UraByteArray add(byte value) {
        ensureCapacity(this.size + 1);
        byte[] array = (byte[]) this.elementData;
        array[(this.size++)] = value;
        return this;
    }

    public UraByteArray add(int index, byte value) {
        if ((index > this.size) || (index < 0)) {
            throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + this.size);
        }
        ensureCapacity(this.size + 1);
        System.arraycopy(this.elementData, index, this.elementData, index + 1, this.size - index);
        byte[] array = (byte[]) this.elementData;
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
    public boolean addAll(int paramInt, byte... array) {
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
    public boolean addAll(byte... array) {
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
    public List<Byte> asList() {
        List<Byte> res = newArrayList(this.size);
        for (byte i : this.getArray()) {
            res.add(Byte.valueOf(i));
        }
        return res;
    }

    /*
     * (非 Javadoc)
     *
     * @see org.uranoplums.typical.collection.AbsUraArray#clear()
     */
    @Override
    public UraByteArray clear() {
        return (UraByteArray) super.clear();
    }

    public boolean contains(byte value) {
        return indexOf(value) >= 0;
    }

    public boolean containsAll(byte... array) {
        boolean res = false;
        for (byte i : array) {
            res = this.contains(i);
            if (!res) {
                break;
            }
        }
        return res;
    }

    public byte get(int paramInt) {
        RangeCheck(paramInt);
        byte[] array = (byte[]) this.elementData;
        return array[paramInt];
    }

    public byte[] getArray() {
        return (byte[]) this.copyOfRange(0, this.size);
    }

    public byte[] getArray(int start) {
        return (byte[]) this.copyOfRange(start, this.size);
    }

    public byte[] getArray(int start, int end) {
        return (byte[]) this.copyOfRange(start, end + 1, this.size);
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

    public int indexOf(byte value) {
        int j = 0;
        for (byte i : this.getArray()) {
            if (i == value) {
                return j;
            }
            j++;
        }
        return -1;
    }

    public int lastIndexOf(byte paramObject) {
        int rindex = this.size - 1;
        for (; rindex >= 0; rindex--) {
            if (this.get(rindex) == paramObject) {
                break;
            }
        }
        return rindex;
    }

    public boolean removeAll(byte... array) {
        boolean res = false;
        for (byte i : array) {
            if (res) {
                this.removeValue(i);
            } else {
                res = this.removeValue(i);
            }
        }
        this.resize();
        return res;
    }

    public byte removeIndex(int paramInt) {
        RangeCheck(paramInt);
        byte localObject = this.get(paramInt);
        this.fastRemove(paramInt);
        return localObject;
    }

    public boolean removeValue(byte value) {
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
        Array.setByte(this.elementData, index, Byte.MIN_VALUE);
    }

    public boolean retainAll(byte... array) {
        boolean res = false;
        UraByteArray del = new UraByteArray();
        byte[] selfArray = (byte[]) this.elementData;
        for (byte i : selfArray) {
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

    public byte set(int paramInt, byte paramE) {
        RangeCheck(paramInt);
        byte[] array = (byte[]) this.elementData;
        byte localObject = array[paramInt];
        array[paramInt] = paramE;
        return localObject;
    }

    /*
     * (非 Javadoc)
     *
     * @see org.uranoplums.typical.collection.AbsUraArray#sort()
     */
    @Override
    public UraByteArray sort() {
        byte[] array = this.getArray();
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
    public UraByteArray sort(int start, int end) {
        byte[] array = this.getArray();
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
