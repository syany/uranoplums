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
 * $Id: UraTuple.java$
 */
package org.uranoplums.typical.collection;

import org.uranoplums.typical.lang.UraSerialDataObject;

/**
 * UraTupleクラス。<br>
 * 
 * @since 2015/03/10
 * @author syany
 * @param <K>
 * @param <V>
 */
public class UraTuple<K, V> extends UraSerialDataObject implements UraEntry<K, V> {

    /**  */
    private static final long serialVersionUID = -3733716283360294963L;
    /**  */
    private K key;
    /**  */
    private V value;

    /**
     * デフォルトコンストラクタ。<br>
     */
    public UraTuple() {}

    /**
     * デフォルトコンストラクタ。<br>
     * @param key
     * @param value
     */
    public UraTuple(final K key, final V value) {
        this.key = key;
        this.value = value;
    }

    /**
     * @return key を返却します。
     */
    @Override
    public final K getKey() {
        return key;
    }

    /**
     * @return value を返却します。
     */
    @Override
    public final V getValue() {
        return value;
    }

    /**
     * @param key keyを設定します。
     * @return
     */
    @Override
    public final K setKey(final K key) {
        K oldKey = this.key;
        this.key = key;
        return oldKey;
    }

    /**
     * @param value valueを設定します。
     * @return
     */
    @Override
    public final V setValue(final V value) {
        V oldValue = this.value;
        this.value = value;
        return oldValue;
    }
}
