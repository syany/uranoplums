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
 * $Id: AbsUraArray.java$
 */
package org.uranoplums.typical.collection;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.lang.reflect.Array;
import java.util.ConcurrentModificationException;

import org.uranoplums.typical.lang.UraSerialDataObject;

/**
 * UraArrayクラス。<br>
 *
 * @since 2015/11/30
 * @author syany
 */
public abstract class AbsUraArray extends UraSerialDataObject {

    /**  */
    private static final long serialVersionUID = 196980963588559035L;
    protected int actualLength;
    protected transient Object elementData;
    protected final Class<?> klass;
    protected transient int modCount = 0;
    protected int size;

    public AbsUraArray(Class<?> klass) {
        this(klass, 3);
    }

    public AbsUraArray(Class<?> klass, int elemSize) {
        if (!klass.isPrimitive()) {
            throw new ClassCastException(klass.getName() + "is not Primitive.");
        }
        this.klass = klass;
        this.elementData = Array.newInstance(this.klass, elemSize);
        this.actualLength = elemSize;
    }

    public AbsUraArray clear() {
        this.modCount += 1;
        this.elementData = Array.newInstance(this.klass, 0);
        this.size = this.actualLength = 0;
        return this;
    }

    /*
     * (非 Javadoc)
     *
     * @see org.uranoplums.typical.lang.UraDataObject#clone()
     */
    @Override
    public AbsUraArray clone() {
        AbsUraArray localArray = (AbsUraArray) super.clone();
        localArray.elementData = this.copyOf(this.size);
        localArray.modCount = 0;
        return localArray;
    }

    protected Object copyOf(int elemSize) {
        Object target = Array.newInstance(this.klass, elemSize);
        System.arraycopy(this.elementData, 0, target, 0, Math.min(this.actualLength, elemSize));
        return target;
    }

    protected Object copyOfRange(int start, int end) {
        return copyOfRange(start, end, this.actualLength);
    }

    protected Object copyOfRange(int start, int end, int length) {
        int i = end - start;
        if (i < 0) {
            throw new IllegalArgumentException(start + " > " + end);
        }
        Object target = Array.newInstance(this.klass, i);
        System.arraycopy(this.elementData, start, target, 0, Math.min(length - start, i));
        return target;
    }

    public void ensureCapacity(int paramInt) {
        this.modCount += 1;
        int i = this.actualLength;
        if (paramInt > i)
        {
            int j = i * 3 / 2 + 1;
            if (j < paramInt) {
                j = paramInt;
            }
            this.elementData = this.copyOf(j);
            this.actualLength = j;
        }
    }

    protected void fastRemove(int index) {
        this.modCount += 1;
        int i = this.size - index - 1;
        if (i > 0) {
            System.arraycopy(this.elementData, index + 1, this.elementData, index, i);
        }
        this.resetElement(--this.size);
    }

    public boolean isEmpty() {
        return this.size == 0;
    }

    protected void RangeCheck(int paramInt) {
        if (paramInt >= this.size) {
            throw new IndexOutOfBoundsException("Index: " + paramInt + ", Size: " + this.size);
        }
    }

    private void readObject(ObjectInputStream paramObjectInputStream)
            throws IOException, ClassNotFoundException {
        paramObjectInputStream.defaultReadObject();
        int i = paramObjectInputStream.readInt();
        this.actualLength = i;
        Object arrayOfObject = this.elementData = Array.newInstance(this.klass, i);
        for (int j = 0; j < this.size; j++) {
            Array.set(arrayOfObject, j, paramObjectInputStream.readObject());
        }
    }

    protected void removeRange(int paramInt1, int paramInt2) {
        this.modCount += 1;
        paramInt2++;
        int i = this.size - paramInt2;
        int removeSize = paramInt2 - paramInt1;
        this.actualLength = this.actualLength - removeSize;
        Object o = Array.newInstance(this.klass, this.actualLength);
        System.arraycopy(this.elementData, 0, o, 0, paramInt1);
        System.arraycopy(this.elementData, paramInt2, o, paramInt1, i);
        this.elementData = o;
        int j = this.size - (paramInt2 - paramInt1);
        while (this.size != j) {
            this.resetElement(--this.size);
        }
        this.resize();
    }

    abstract protected void resetElement(int index);

    protected void resize() {
        this.modCount += 1;
        if (this.size < this.actualLength) {
            Object o = Array.newInstance(this.klass, this.size);
            System.arraycopy(this.elementData, 0, o, 0, this.size);
            this.elementData = o;
            this.actualLength = this.size;
        }
    }

    public int size() {
        return this.size;
    }

    abstract public AbsUraArray sort();

    abstract public AbsUraArray sort(int start, int end);

    /**
     * 。<br>
     * @param paramObjectOutputStream
     * @throws IOException
     */
    private void writeObject(ObjectOutputStream paramObjectOutputStream)
            throws IOException {
        int i = this.modCount;
        paramObjectOutputStream.defaultWriteObject();
        paramObjectOutputStream.writeInt(this.actualLength);
        //
        for (int j = 0; j < this.size; j++) {
            paramObjectOutputStream.writeObject(Array.get(this.elementData, j));
        }
        if (this.modCount != i) {
            throw new ConcurrentModificationException();
        }
    }
}
