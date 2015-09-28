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
 * $Id: UraSetUtils.java$
 */
package org.uranoplums.typical.collection;

import static org.uranoplums.typical.collection.factory.UraMapFactory.*;

import java.util.Collection;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import org.uranoplums.typical.collection.factory.UraMapFactory.FACTOR;


/**
 * UraSetUtilsクラス。<br>
 *
 * @since 2015/11/05
 * @author syany
 */
public class UraSetUtils extends UraCollectionUtils {

    public static final <E> Set<E> toSet(E... srcArray) {
        Set<E> set = new HashSet<E>();
        _toCollection(set, srcArray);
        return set;
    }

    /**
     * 。<br>
     * @param source
     * @return
     */
    public static final <E> Set<E> toSet(Collection<E> source) {
        int sourceSize = source.size();
        Map<E, Object> map = newLinkedHashMap(sourceSize, FACTOR.NONE);
        for (final E entry : source) {
            map.put(entry, PRESENT);
        }
        return map.keySet();
    }

    /**
     * 。<br>
     * @param source
     * @return
     */
    public static final <E> Set<E> getDuplicateSet(Collection<E> source) {
        int sourceSize = source.size();
        Map<E, Object> map = newHashMap(sourceSize, FACTOR.NONE);
        Map<E, Object> duplicateMap = newLinkedHashMap(sourceSize - 1, FACTOR.NONE);
        for (final E entry : source) {
            Object o = map.put(entry, PRESENT);
            if (o != null) {
                duplicateMap.put(entry, PRESENT);
            }
        }
        return duplicateMap.keySet();
    }
}
