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
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.SortedMap;
import java.util.SortedSet;

import org.apache.commons.collections4.comparators.ComparatorChain;
import org.uranoplums.typical.collection.factory.UraMapFactory.FACTOR;
import org.uranoplums.typical.util.UraUtils;

/**
 * UraCollectionUtilsクラス。<br>
 *
 * @since 2015/11/01
 * @author syany
 */
public class UraCollectionUtils extends UraUtils {

    /** 空のオブジェクト */
    protected static final Object PRESENT = new Object();

    /**
     * 対象の配列を対象のコレクションオブジェクトに追加します。<br>
     * @param source
     * @param srcArray
     */
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
    /**
     * UnmodifiableChangeクラス。<br>
     *
     * @since 2016/01/15
     * @author syany
     */
    public static abstract interface UraUnmodifiableChange {
        /**
         * 。<br>
         * @param source
         * @return
         */
        public abstract <E> E transfer(E source);
    }
    /**  */
    private static final UraUnmodifiableChange DEFAULT_CHANGE = new UraUnmodifiableChange() {
        @Override
        public <E> E transfer(E source) {
            return source;
        }

    };

    @SuppressWarnings ("unchecked")
    protected static <E> E _deepUnmodifiableObject(E source, UraUnmodifiableChange uraUnmodifiableChange) {
        if (source instanceof SortedMap){
            source = (E) deepUnmodifiableSortedMap(SortedMap.class.cast(source), uraUnmodifiableChange);
        } else if (source instanceof Map) {
            source = (E) deepUnmodifiableMap(Map.class.cast(source), uraUnmodifiableChange);
        } else if (source instanceof List) {
            source = (E) deepUnmodifiableList(List.class.cast(source), uraUnmodifiableChange);
        } else if (source instanceof SortedSet) {
            source = (E) deepUnmodifiableSortedSet(SortedSet.class.cast(source), uraUnmodifiableChange);
        } else if (source instanceof Set) {
            source = (E) deepUnmodifiableSet(Set.class.cast(source), uraUnmodifiableChange);
        } else if (source instanceof Collection) {
            source = (E) deepUnmodifiableCollection(Collection.class.cast(source), uraUnmodifiableChange);
        } else {
            source = uraUnmodifiableChange.transfer(source);
        }
        return source;
    }
    public static <K, V> Map<K, V> deepUnmodifiableMap(Map<K, V> source) {
        return deepUnmodifiableMap(source, DEFAULT_CHANGE);
    }
    public static <K, V> Map<K, V> deepUnmodifiableMap(Map<K, V> source, UraUnmodifiableChange uraUnmodifiableChange) {
        for(final K key : source.keySet()) {
            source.put(key, _deepUnmodifiableObject(source.get(key), uraUnmodifiableChange));
        }
        return Collections.unmodifiableMap(source);
    }
    public static <K, V> SortedMap<K, V> deepUnmodifiableSortedMap(SortedMap<K, V> source) {
        return deepUnmodifiableSortedMap(source, DEFAULT_CHANGE);
    }
    public static <K, V> SortedMap<K, V> deepUnmodifiableSortedMap(SortedMap<K, V> source, UraUnmodifiableChange uraUnmodifiableChange) {
        for(final K key : source.keySet()) {
            source.put(key, _deepUnmodifiableObject(source.get(key), uraUnmodifiableChange));
        }
        return Collections.unmodifiableSortedMap(source);
    }
    public static <E> Set<E> deepUnmodifiableSet(Set<E> source) {
        return deepUnmodifiableSet(source, DEFAULT_CHANGE);
    }
    public static <E> Set<E> deepUnmodifiableSet(Set<E> source, UraUnmodifiableChange uraUnmodifiableChange) {
        Set<E> target = Collections.synchronizedSet(source);
        for (final E value : source) {
            target.add(_deepUnmodifiableObject(value, uraUnmodifiableChange));
        }
        return Collections.unmodifiableSet(target);
    }
    public static <E> SortedSet<E> deepUnmodifiableSortedSet(SortedSet<E> source) {
        return deepUnmodifiableSortedSet(source, DEFAULT_CHANGE);
    }
    public static <E> SortedSet<E> deepUnmodifiableSortedSet(SortedSet<E> source, UraUnmodifiableChange uraUnmodifiableChange) {
        SortedSet<E> target = Collections.synchronizedSortedSet(source);
        for (final E value : source) {
            target.add(_deepUnmodifiableObject(value, uraUnmodifiableChange));
        }
        return Collections.unmodifiableSortedSet(target);
    }
    public static <E> List<E> deepUnmodifiableList(List<E> source) {
        return deepUnmodifiableList(source, DEFAULT_CHANGE);
    }
    public static <E> List<E> deepUnmodifiableList(List<E> source, UraUnmodifiableChange uraUnmodifiableChange) {
        int max = source.size();
        for (int index = 0; index < max; index++) {
            source.set(index, _deepUnmodifiableObject(source.get(index), uraUnmodifiableChange));
        }
        return Collections.unmodifiableList(source);
    }
    public static <E> Collection<E> deepUnmodifiableCollection(Collection<E> source) {
        return deepUnmodifiableCollection(source, DEFAULT_CHANGE);
    }
    public static <E> Collection<E> deepUnmodifiableCollection(Collection<E> source, UraUnmodifiableChange uraUnmodifiableChange) {
        Collection<E> target = Collections.synchronizedCollection(source);
        for (final E value : source) {
            target.add(_deepUnmodifiableObject(value, uraUnmodifiableChange));
        }
        return Collections.unmodifiableCollection(target);
    }
    /**
     * 。<br>
     * @param list
     * @param comparators
     */
    public static final <E> void chainSort(List<E> list, Comparator<Object>... comparators) {
        ComparatorChain<Object> cChain = new ComparatorChain<Object>();
        for (final Comparator<Object> c : comparators) {
            cChain.addComparator(c);
        }
        Collections.sort(list, cChain);
    }
}
