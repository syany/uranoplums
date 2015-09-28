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
 * $Id: UraListUtils.java$
 */
package org.uranoplums.typical.collection;

import static org.uranoplums.typical.collection.factory.UraMapFactory.*;

import java.util.Collection;
import java.util.Map;
import java.util.Set;

import org.uranoplums.typical.collection.factory.UraMapFactory.FACTOR;
import org.uranoplums.typical.util.UraUtils;

/**
 * UraListUtilsクラス。<br>
 *
 * @since 2015/11/01
 * @author syany
 */
class UraCollectionUtils extends UraUtils {

    /**  */
    protected static final Object PRESENT = new Object();

    protected static final <E> void _toCollection(Collection<E> source, E... srcArray) {
        source.clear();
        for (final E elm : srcArray) {
            source.add(elm);
        }
    }
    /**
     * ユニークリストを追加する。<br>
     * @param source
     * @param target
     * @return
     */
    public static final <E> Collection<E> addUnique(Collection<E> source, Collection<E> target) {
        int sourceSize = source.size();
        Map<E, Object> map = newLinkedHashMap(sourceSize, FACTOR.NONE);
        for (final E entry : source) {
            map.put(entry, PRESENT);
        }
        target.addAll(map.keySet());
        return target;
    }


    // 重複キー出力

    // ユニークリスト出力
    /**
     * 。<br>
     * @param source
     * @return
     */
    public static final <E> Set<E> getUnique(Collection<E> source) {
        int sourceSize = source.size();
        Map<E, Object> map = newLinkedHashMap(sourceSize, FACTOR.NONE);
        for (final E entry : source) {
            map.put(entry, PRESENT);
        }
        return map.keySet();
    }


    // ユニークリスト出力

    // 重複チェック
    /**
     * 。<br>
     * @param source
     * @return
     */
    public static final <E> boolean isDuplicate(Collection<E> source) {
        return !isUnique(source);
    }

    /**
     * 。<br>
     * @param source
     * @return
     */
    public static final <E> boolean isUnique(Collection<E> source) {
        int sourceSize = source.size();
        Map<E, Object> map = newHashMap(sourceSize, FACTOR.NONE);
        for (final E entry : source) {
            map.put(entry, PRESENT);
        }
        return sourceSize == map.size();
    }
}
