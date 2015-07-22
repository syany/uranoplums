/*
 * Copyright 2013-2014 the Uranoplums Foundation and the Others.
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
 * $Id: $
 */
package org.uranoplums.typical.collection;

import java.util.LinkedHashMap;
import java.util.Map.Entry;

/**
 * UraLRULimitedLinkedHashMapクラス。<br>
 * 
 * @since 2015/02/23
 * @author syany
 * @param <K>
 * @param <V>
 */
public class UraLRUMap<K, V> extends LinkedHashMap<K, V> {

    /**  */
    private static final int LIMIT_SIZE = 40;
    /**  */
    private static final float LOAD_FACTOR = 0.8f;
    /** 負荷係数【分子C/分母P】 */
    private static final int LOAD_FACTOR_C = 4;
    /**  */
    private static final int LOAD_FACTOR_P = 5;
    /**  */
    private static final long serialVersionUID = 3004599380225946388L;
    /**  */
    private final int limitSize;

    /**
	 *
	 */
    public UraLRUMap() {
        super(LIMIT_SIZE * LOAD_FACTOR_C / LOAD_FACTOR_P, LOAD_FACTOR, true);
        limitSize = LIMIT_SIZE;
    }

    /**
     * @param limitSize
     */
    public UraLRUMap(int limitSize) {
        super(limitSize * LOAD_FACTOR_C / LOAD_FACTOR_P, LOAD_FACTOR, true);
        this.limitSize = limitSize;
    }

    /*
     * (非 Javadoc)
     * 
     * @see java.util.LinkedHashMap#removeEldestEntry(java.util.Map.Entry)
     */
    @Override
    protected boolean removeEldestEntry(Entry<K, V> eldest) {
        return size() > this.limitSize;
    }
}
