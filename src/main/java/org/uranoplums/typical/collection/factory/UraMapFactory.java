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
package org.uranoplums.typical.collection.factory;

import java.util.Collection;
import java.util.Comparator;
import java.util.EnumMap;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.SortedMap;
import java.util.TreeMap;
import java.util.WeakHashMap;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.ConcurrentNavigableMap;
import java.util.concurrent.ConcurrentSkipListMap;

import org.apache.commons.collections4.BidiMap;
import org.apache.commons.collections4.Factory;
import org.apache.commons.collections4.IterableMap;
import org.apache.commons.collections4.MultiMap;
import org.apache.commons.collections4.OrderedBidiMap;
import org.apache.commons.collections4.Predicate;
import org.apache.commons.collections4.SortedBidiMap;
import org.apache.commons.collections4.Transformer;
import org.apache.commons.collections4.bidimap.DualHashBidiMap;
import org.apache.commons.collections4.bidimap.DualLinkedHashBidiMap;
import org.apache.commons.collections4.bidimap.DualTreeBidiMap;
import org.apache.commons.collections4.bidimap.TreeBidiMap;
import org.apache.commons.collections4.keyvalue.MultiKey;
import org.apache.commons.collections4.map.AbstractHashedMap;
import org.apache.commons.collections4.map.AbstractReferenceMap.ReferenceStrength;
import org.apache.commons.collections4.map.CaseInsensitiveMap;
import org.apache.commons.collections4.map.DefaultedMap;
import org.apache.commons.collections4.map.FixedSizeMap;
import org.apache.commons.collections4.map.FixedSizeSortedMap;
import org.apache.commons.collections4.map.Flat3Map;
import org.apache.commons.collections4.map.LazyMap;
import org.apache.commons.collections4.map.LazySortedMap;
import org.apache.commons.collections4.map.MultiKeyMap;
import org.apache.commons.collections4.map.MultiValueMap;
import org.apache.commons.collections4.map.PredicatedMap;
import org.apache.commons.collections4.map.PredicatedSortedMap;
import org.apache.commons.collections4.map.ReferenceMap;
import org.apache.commons.collections4.map.StaticBucketMap;
import org.apache.commons.collections4.map.TransformedMap;
import org.apache.commons.collections4.map.TransformedSortedMap;
import org.uranoplums.typical.collection.UraFIFOMap;
import org.uranoplums.typical.collection.UraLRUMap;
import org.uranoplums.typical.lang.UraStaticObject;

/**
 * UraHashMapFactoriesクラス。<br>
 * 
 * @since 2015/02/24
 * @author syany
 */
public final class UraMapFactory extends UraStaticObject {

    /**  */
    private static final int NORMAL_SIZE = 12;
    /**  */
    private static final float NORMAL_FACTOR = 0.75F;

    private static float getFactor(FACTOR factor) {
        float result = NORMAL_FACTOR;
        switch (factor) {
            case NONE:
                result = 1.0F;
                break;
            case LESS:
                result = 0.6F;
                break;
            case NORMAL:
                result = NORMAL_FACTOR;
                break;
            case GREATER:
                result = 0.8F;
                break;
            default:
                result = NORMAL_FACTOR;
        }
        return result;
    }

    private static int getInitSize(int size, FACTOR factor) {
        int result = NORMAL_SIZE * 4 / 3;
        switch (factor) {
            case NONE:
                result = size;
                break;
            case LESS:
                result = (size * 5) / 3;
                break;
            case NORMAL:
                result = (size * 4) / 3;
                break;
            case GREATER:
                result = (size * 5) / 2;
                break;
            default:
                result = (size * 4) / 3;
        }
        return result;
    }

    /**
     * 。<br>
     * @return インスタンス
     */
    public static final <K, V> IterableMap<K, V> newCaseInsensitiveMap() {
        return new CaseInsensitiveMap<K, V>();
    }

    /**
     * 。<br>
     * @param factor
     * @return インスタンス
     */
    public static final <K, V> IterableMap<K, V> newCaseInsensitiveMap(FACTOR factor) {
        return new CaseInsensitiveMap<K, V>(getInitSize(NORMAL_SIZE, factor), getFactor(factor));
    }

    /**
     * 。<br>
     * @param size
     * @return インスタンス
     */
    public static final <K, V> IterableMap<K, V> newCaseInsensitiveMap(int size) {
        return new CaseInsensitiveMap<K, V>(getInitSize(size, FACTOR.NORMAL), getFactor(FACTOR.NORMAL));
    }

    /**
     * 。<br>
     * @param size
     * @param factor
     * @return インスタンス
     */
    public static final <K, V> IterableMap<K, V> newCaseInsensitiveMap(int size, FACTOR factor) {
        return new CaseInsensitiveMap<K, V>(getInitSize(size, factor), getFactor(factor));
    }

    /**
     * 。<br>
     * @param m
     * @return インスタンス
     */
    public static final <K, V> IterableMap<K, V> newCaseInsensitiveMap(Map<? extends K, ? extends V> m) {
        return new CaseInsensitiveMap<K, V>(m);
    }

    /**
     * 同期HashMap。<br>
     * @return インスタンス
     */
    public static final <K, V> ConcurrentMap<K, V> newConcurrentHashMap() {
        return new ConcurrentHashMap<K, V>();
    }

    /**
     * 。<br>
     * @param factor
     * @return インスタンス
     */
    public static final <K, V> ConcurrentMap<K, V> newConcurrentHashMap(FACTOR factor) {
        return new ConcurrentHashMap<K, V>(getInitSize(NORMAL_SIZE, factor), getFactor(factor));
    }

    /**
     * 。<br>
     * @param size
     * @return インスタンス
     */
    public static final <K, V> ConcurrentMap<K, V> newConcurrentHashMap(int size) {
        return new ConcurrentHashMap<K, V>(getInitSize(size, FACTOR.NORMAL), getFactor(FACTOR.NORMAL));
    }

    /**
     * 。<br>
     * @param size
     * @param factor
     * @return インスタンス
     */
    public static final <K, V> ConcurrentMap<K, V> newConcurrentHashMap(int size, FACTOR factor) {
        return new ConcurrentHashMap<K, V>(getInitSize(size, factor), getFactor(factor));
    }

    /**
     * 。<br>
     * @param m
     * @return インスタンス
     */
    public static final <K, V> ConcurrentMap<K, V> newConcurrentHashMap(Map<? extends K, ? extends V> m) {
        return new ConcurrentHashMap<K, V>(m);
    }

    /**
     * 同期SkipHashMap。<br>
     * @return インスタンス
     */
    public static final <K, V> ConcurrentNavigableMap<K, V> newConcurrentSkipListMap() {
        return new ConcurrentSkipListMap<K, V>();
    }

    /**
     * 。<br>
     * @param m
     * @return インスタンス
     */
    public static final <K, V> ConcurrentNavigableMap<K, V> newConcurrentSkipListMap(Map<? extends K, ? extends V> m) {
        return new ConcurrentSkipListMap<K, V>(m);
    }

    /**
     * 。<br>
     * @param map
     * @param defaultValue
     * @return インスタンス
     */
    public static final <K, V> DefaultedMap<K, V> newDefaultedMap(final Map<K, V> map, final V defaultValue) {
        return DefaultedMap.defaultedMap(map, defaultValue);
    }

    /**
     * 。<br>
     * @param defaultValueTransformer
     * @return インスタンス
     */
    public static final <K, V> DefaultedMap<K, V> newDefaultedMap(final Transformer<? super K, ? extends V> defaultValueTransformer) {
        final DefaultedMap<K, V> defaultedMap = new DefaultedMap<K, V>(defaultValueTransformer);
        return defaultedMap;
    }

    /**
     * 。<br>
     * @param defaultValue
     * @return インスタンス
     */
    public static final <K, V> DefaultedMap<K, V> newDefaultedMap(final V defaultValue) {
        final DefaultedMap<K, V> defaultedMap = new DefaultedMap<K, V>(defaultValue);
        return defaultedMap;
    }

    /**
     * 。<br>
     * @return インスタンス
     */
    public static final <K, V> BidiMap<K, V> newDualHashBidiMap() {
        return new DualHashBidiMap<K, V>();
    }

    /**
     * 。<br>
     * @param m
     * @return インスタンス
     */
    public static final <K, V> BidiMap<K, V> newDualHashBidiMap(final Map<? extends K, ? extends V> m) {
        return new DualHashBidiMap<K, V>(m);
    }

    /**
     * 。<br>
     * @return インスタンス
     */
    public static final <K, V> BidiMap<K, V> newDualLinkedHashBidiMap() {
        return new DualLinkedHashBidiMap<K, V>();
    }

    /**
     * 。<br>
     * @param m
     * @return インスタンス
     */
    public static final <K, V> BidiMap<K, V> newDualLinkedHashBidiMap(final Map<? extends K, ? extends V> m) {
        return new DualLinkedHashBidiMap<K, V>(m);
    }

    /**
     * 。<br>
     * @return インスタンス
     */
    public static final <K, V> SortedBidiMap<K, V> newDualTreeBidiMap() {
        return new DualTreeBidiMap<K, V>();
    }

    /**
     * 。<br>
     * @param m
     * @return インスタンス
     */
    public static final <K, V> SortedBidiMap<K, V> newDualTreeBidiMap(final Map<? extends K, ? extends V> m) {
        return new DualTreeBidiMap<K, V>(m);
    }

    /**
     * 列挙HashMap。<br>
     * @param type
     * @return インスタンス
     */
    public static final <K extends Enum<K>, V> Map<K, V> newEnumMap(final Class<K> type) {
        return new EnumMap<K, V>(type);
    }

    /**
     * 列挙HashMap。<br>
     * @param m
     * @return インスタンス
     */
    public static final <K extends Enum<K>, V> Map<K, V> newEnumMap(final EnumMap<K, ? extends V> m) {
        return new EnumMap<K, V>(m);
    }

    /**
     * 列挙HashMap。<br>
     * @param m
     * @return インスタンス
     */
    public static final <K extends Enum<K>, V> Map<K, V> newEnumMap(final Map<K, ? extends V> m) {
        return new EnumMap<K, V>(m);
    }

    /**
     * 。<br>
     * @return インスタンス
     */
    public static final <V, K> Map<K, V> newFIFOMap() {
        return new UraFIFOMap<K, V>();
    }

    /**
     * 。<br>
     * @param limitSize
     * @return インスタンス
     */
    public static final <V, K> Map<K, V> newFIFOMap(final int limitSize) {
        return new UraFIFOMap<K, V>(limitSize);
    }

    /**
     * 。<br>
     * @param m
     * @return インスタンス
     */
    public static final <K, V> FixedSizeMap<? extends K, ? extends V> newFixedSizeMap(final Map<? extends K, ? extends V> m) {
        return FixedSizeMap.fixedSizeMap(m);
    }

    /**
     * 。<br>
     * @param m
     * @return インスタンス
     */
    public static final <K, V> FixedSizeSortedMap<? extends K, ? extends V> newFixedSizeSortedMap(final SortedMap<? extends K, ? extends V> m) {
        return FixedSizeSortedMap.fixedSizeSortedMap(m);
    }

    /**
     * 。<br>
     * @param factor
     * @return インスタンス
     */
    public static final <K, V> IterableMap<K, V> newFlat3Map() {
        return new Flat3Map<K, V>();
    }

    /**
     * 。<br>
     * @param m
     * @return インスタンス
     */
    public static final <K, V> IterableMap<K, V> newFlat3Map(final Map<? extends K, ? extends V> m) {
        return new Flat3Map<K, V>(m);
    }

    /**
     * 。<br>
     * @return インスタンス
     */
    public static final <V, K> Map<K, V> newHashMap() {
        return new HashMap<K, V>();
    }

    /**
     * 。<br>
     * @param factor
     * @return インスタンス
     */
    public static final <V, K> Map<K, V> newHashMap(final FACTOR factor) {
        return new HashMap<K, V>(getInitSize(NORMAL_SIZE, factor), getFactor(factor));
    }

    /**
     * 。<br>
     * @param size
     * @return インスタンス
     */
    public static final <V, K> Map<K, V> newHashMap(final int size) {
        return new HashMap<K, V>(getInitSize(size, FACTOR.NORMAL), getFactor(FACTOR.NORMAL));
    }

    /**
     * 。<br>
     * @param size
     * @param factor
     * @return インスタンス
     */
    public static final <V, K> Map<K, V> newHashMap(final int size, final FACTOR factor) {
        return new HashMap<K, V>(getInitSize(size, factor), getFactor(factor));
    }

    /**
     * 。<br>
     * @param m
     * @return インスタンス
     */
    public static final <K, V> Map<K, V> newHashMap(final Map<? extends K, ? extends V> m) {
        return new HashMap<K, V>(m);
    }

    /**
     * 。<br>
     * @param map
     * @param factory
     * @return インスタンス
     */
    public static final <K, V> IterableMap<K, V> newLazyMap(final Map<K, V> map, final Factory<? extends V> factory) {
        return LazyMap.lazyMap(map, factory);
    }

    /**
     * 。<br>
     * @param map
     * @param factory
     * @return インスタンス
     */
    public static final <K, V> IterableMap<K, V> newLazyMap(final Map<K, V> map, final Transformer<? super K, ? extends V> factory) {
        return LazyMap.lazyMap(map, factory);
    }

    /**
     * 。<br>
     * @param map
     * @param factory
     * @return インスタンス
     */
    public static final <K, V> SortedMap<K, V> newLazySortedMap(final SortedMap<K, V> map, final Factory<? extends V> factory) {
        return LazySortedMap.lazySortedMap(map, factory);
    }

    /**
     * 。<br>
     * @param map
     * @param factory
     * @return インスタンス
     */
    public static final <K, V> SortedMap<K, V> newLazySortedMap(final SortedMap<K, V> map, final Transformer<? super K, ? extends V> factory) {
        return LazySortedMap.lazySortedMap(map, factory);
    }

    /**
     * 。<br>
     * @return インスタンス
     */
    public static final <V, K> Map<K, V> newLinkedHashMap() {
        return new LinkedHashMap<K, V>();
    }

    /**
     * 。<br>
     * @param factor
     * @return インスタンス
     */
    public static final <V, K> Map<K, V> newLinkedHashMap(final FACTOR factor) {
        return new LinkedHashMap<K, V>(getInitSize(NORMAL_SIZE, factor), getFactor(factor));
    }

    /**
     * 。<br>
     * @param size
     * @return インスタンス
     */
    public static final <V, K> Map<K, V> newLinkedHashMap(final int size) {
        return new LinkedHashMap<K, V>(getInitSize(size, FACTOR.NORMAL), getFactor(FACTOR.NORMAL));
    }

    /**
     * 。<br>
     * @param size
     * @param factor
     * @return インスタンス
     */
    public static final <V, K> Map<K, V> newLinkedHashMap(final int size, final FACTOR factor) {
        return new LinkedHashMap<K, V>(getInitSize(size, factor), getFactor(factor));
    }

    /**
     * 。<br>
     * @param m
     * @return インスタンス
     */
    public static final <K, V> Map<K, V> newLinkedHashMap(final Map<? extends K, ? extends V> m) {
        return new LinkedHashMap<K, V>(m);
    }

    /**
     * 。<br>
     * @return インスタンス
     */
    public static final <V, K> Map<K, V> newLRUMap() {
        return new UraLRUMap<K, V>();
    }

    /**
     * 。<br>
     * @param limitSize
     * @return インスタンス UraLRULimitedLinkedHashMap型インスタンス
     */
    public static final <V, K> Map<K, V> newLRUMap(final int limitSize) {
        return new UraLRUMap<K, V>(limitSize);
    }

    /**
     * 。<br>
     * @return インスタンス
     */
    public static final <V, K> MultiKeyMap<K, V> newMultiKeyMap() {
        return new MultiKeyMap<K, V>();
    }

    /**
     * 。<br>
     * @param map
     * @return インスタンス
     */
    public static final <V, K> MultiKeyMap<K, V> newMultiKeyMap(final AbstractHashedMap<MultiKey<? extends K>, V> map) {
        return MultiKeyMap.multiKeyMap(map);
    }

    /**
     * 。<br>
     * @return インスタンス
     */
    public static final <V, K> MultiMap<K, V> newMultiValueMap() {
        return new MultiValueMap<K, V>();
    }

    /**
     * 。<br>
     * @param map
     * @param collectionClass
     * @return インスタンス
     */
    public static final <V, K, C extends Collection<V>> MultiMap<K, V> newMultiValueMap(final Map<K, ? super C> map,
            final Class<C> collectionClass) {
        return MultiValueMap.multiValueMap(map, collectionClass);
    }

    /**
     * 。<br>
     * @param map
     * @param collectionFactory
     * @return インスタンス
     */
    public static final <V, K, C extends Collection<V>> MultiMap<K, V> newMultiValueMap(final Map<K, ? super C> map,
            final Factory<C> collectionFactory) {
        return MultiValueMap.multiValueMap(map, collectionFactory);
    }

    /**
     * 。<br>
     * @param map
     * @return インスタンス
     */
    public static final <V, K> MultiMap<K, V> newMultiValueMap(final Map<K, ? super Collection<V>> map) {
        return MultiValueMap.multiValueMap(map);
    }

    /**
     * 。<br>
     * @param map
     * @param keyPredicate
     * @param valuePredicate
     * @return インスタンス
     */
    public static final <V, K> PredicatedMap<K, V> newPredicatedMap(final Map<K, V> map,
            final Predicate<? super K> keyPredicate,
            final Predicate<? super V> valuePredicate) {
        return PredicatedMap.predicatedMap(map, keyPredicate, valuePredicate);
    }

    /**
     * 。<br>
     * @param map
     * @param keyPredicate
     * @param valuePredicate
     * @return インスタンス
     */
    public static final <V, K> PredicatedSortedMap<K, V> newPredicatedSortedMap(final SortedMap<K, V> map,
            final Predicate<? super K> keyPredicate, final Predicate<? super V> valuePredicate) {
        return PredicatedSortedMap.predicatedSortedMap(map, keyPredicate, valuePredicate);
    }

    /**
     * 。<br>
     * @return インスタンス
     */
    public static final <V, K> ReferenceMap<K, V> newReferenceMap() {
        return new ReferenceMap<K, V>();
    }

    /**
     * 。<br>
     * @param keyType
     * @param valueType
     * @return インスタンス
     */
    public static final <V, K> ReferenceMap<K, V> newReferenceMap(final ReferenceStrength keyType, final ReferenceStrength valueType) {
        return new ReferenceMap<K, V>(keyType, valueType);
    }

    /**
     * 。<br>
     * @param keyType
     * @param valueType
     * @param purgeValues
     * @return インスタンス
     */
    public static final <V, K> ReferenceMap<K, V> newReferenceMap(final ReferenceStrength keyType, final ReferenceStrength valueType,
            final boolean purgeValues) {
        return new ReferenceMap<K, V>(keyType, valueType, purgeValues);
    }

    /**
     * 。<br>
     * @param keyType
     * @param valueType
     * @param capacity
     * @param loadFactor
     * @return インスタンス
     */
    public static final <V, K> ReferenceMap<K, V> newReferenceMap(final ReferenceStrength keyType, final ReferenceStrength valueType,
            final int capacity,
            final float loadFactor) {
        return new ReferenceMap<K, V>(keyType, valueType, capacity, loadFactor, false);
    }

    /**
     * 。<br>
     * @param keyType
     * @param valueType
     * @param capacity
     * @param loadFactor
     * @param purgeValues
     * @return インスタンス
     */
    public static final <V, K> ReferenceMap<K, V> newReferenceMap(final ReferenceStrength keyType, final ReferenceStrength valueType,
            final int capacity,
            final float loadFactor, final boolean purgeValues) {
        return new ReferenceMap<K, V>(keyType, valueType, capacity, loadFactor, purgeValues);
    }

    /**
     * 。<br>
     * @return インスタンス
     */
    public static final <V, K> IterableMap<K, V> newStaticBucketMap() {
        return new StaticBucketMap<K, V>();
    }

    /**
     * 。<br>
     * @param numBuckets
     * @param size
     * @return インスタンス
     */
    public static final <V, K> IterableMap<K, V> newStaticBucketMap(final int numBuckets) {
        return new StaticBucketMap<K, V>(numBuckets);
    }

    /**
     * 。<br>
     * @param map
     * @param keyTransformer
     * @param valueTransformer
     * @param factor
     * @return インスタンス
     */
    public static final <K, V> TransformedMap<K, V> newTransformedMap(final Map<K, V> map,
            final Transformer<? super K, ? extends K> keyTransformer,
            final Transformer<? super V, ? extends V> valueTransformer) {
        return TransformedMap.transformedMap(map, keyTransformer, valueTransformer);
    }

    /**
     * 。<br>
     * @param map
     * @param keyTransformer
     * @param valueTransformer
     * @return インスタンス
     */
    public static final <K, V> TransformedSortedMap<K, V> newTransformedSortedMap(final SortedMap<K, V> map,
            final Transformer<? super K, ? extends K> keyTransformer,
            final Transformer<? super V, ? extends V> valueTransformer) {
        return TransformedSortedMap.transformedSortedMap(map, keyTransformer, valueTransformer);
    }

    /**
     * 。<br>
     * @param map
     * @param keyTransformer
     * @param valueTransformer
     * @param factor
     * @return インスタンス
     */
    public static final <K, V> TransformedMap<K, V> newTransformingMap(final Map<K, V> map,
            final Transformer<? super K, ? extends K> keyTransformer,
            final Transformer<? super V, ? extends V> valueTransformer) {
        return TransformedMap.transformingMap(map, keyTransformer, valueTransformer);
    }

    /**
     * 。<br>
     * @param map
     * @param keyTransformer
     * @param valueTransformer
     * @return インスタンス
     */
    public static final <K, V> TransformedSortedMap<K, V> newTransformingSortedMap(final SortedMap<K, V> map,
            final Transformer<? super K, ? extends K> keyTransformer,
            final Transformer<? super V, ? extends V> valueTransformer) {
        return TransformedSortedMap.transformingSortedMap(map, keyTransformer, valueTransformer);
    }

    /**
     * t
     * 。<br>
     * @return インスタンス
     */
    public static final <K extends Comparable<K>, V extends Comparable<V>> OrderedBidiMap<K, V> newTreeBidiMap() {
        return new TreeBidiMap<K, V>();
    }

    /**
     * 。<br>
     * @param m
     * @return インスタンス
     */
    public static final <K extends Comparable<K>, V extends Comparable<V>> OrderedBidiMap<K, V> newTreeBidiMap(final Map<? extends K, ? extends V> m) {
        return new TreeBidiMap<K, V>(m);
    }

    /**
     * キーの自然順序付けを使って、新しい空のツリーマップを構築します。<br>
     * @return インスタンス インスタンス
     */
    public static final <K, V> SortedMap<K, V> newTreeMap() {
        return new TreeMap<K, V>();
    }

    /**
     * 指定されたコンパレータに従って順序付けされた、新しい空のツリーマップを作成します。<br>
     * @param comparator
     * @return インスタンス インスタンス
     */
    public static final <K, V> SortedMap<K, V> newTreeMap(final Comparator<? super K> comparator) {
        return new TreeMap<K, V>(comparator);
    }

    /**
     * 指定されたマップと同じマッピングを持ち、そのキーの自然順序付けに従って順序付けされた新しいツリーマップを作成します。。<br>
     * @param m マッピングがこのマップに配置されるマップ
     * @return インスタンス インスタンス
     */
    public static final <K, V> SortedMap<K, V> newTreeMap(final Map<? extends K, ? extends V> m) {
        return new TreeMap<K, V>(m);
    }

    /**
     * 指定されたソートマップと同じマッピングを持ち、同じ順序付けを使用する新しいツリーマップを作成します。<br>
     * @param m マッピングがこのマップに配置され、コンパレータがこのマップのソートに使用される、ソートされたマップ
     * @return インスタンス インスタンス
     */
    public static final <K, V> SortedMap<K, V> newTreeMap(final SortedMap<K, ? extends V> m) {
        return new TreeMap<K, V>(m);
    }

    /**
     * 弱いHashMap。<br>
     * @return インスタンス
     */
    public static final <K, V> Map<K, V> newWeakHashMap() {
        return new WeakHashMap<K, V>();
    }

    /**
     * 。<br>
     * @param factor
     * @return インスタンス
     */
    public static final <K, V> Map<K, V> newWeakHashMap(final FACTOR factor) {
        return new WeakHashMap<K, V>(getInitSize(NORMAL_SIZE, factor), getFactor(factor));
    }

    /**
     * 。<br>
     * @param size
     * @return インスタンス
     */
    public static final <K, V> Map<K, V> newWeakHashMap(final int size) {
        return new WeakHashMap<K, V>(getInitSize(size, FACTOR.NORMAL), getFactor(FACTOR.NORMAL));
    }

    /**
     * 。<br>
     * @param size
     * @param factor
     * @return インスタンス
     */
    public static final <K, V> Map<K, V> newWeakHashMap(final int size, final FACTOR factor) {
        return new WeakHashMap<K, V>(getInitSize(size, factor), getFactor(factor));
    }

    /**
     * 。<br>
     * @param m
     * @return インスタンス
     */
    public static final <K, V> Map<K, V> newWeakHashMap(final Map<? extends K, ? extends V> m) {
        return new WeakHashMap<K, V>(m);
    }

    /**
     * 初期因子列挙型。<br>
     * 対象Mapの利用方法により４種類を提供
     * 
     * @since 2015/02/23
     * @author syany
     */
    public static enum FACTOR {
        /** 因子を使用しない。（サイズを超えたタイミングで拡張。初回追加後、読み込み専用とする場合） */
        NONE,
        /** 標準より早い因子。（頻繁に多く要素が追加[putやadd]が発生する場合に利用） */
        LESS,
        /** 標準因子（0.75F。要素追加時に、最大数の３／４を超えた段階で最大サイズを拡張する。） */
        NORMAL,
        /** 標準よりも遅い因子。（あまり要素追加が発生せず、追加する数も少ない場合） */
        GREATER
    }
}
