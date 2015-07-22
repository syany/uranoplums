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
 * $Id: UraListFactory.java$
 */
package org.uranoplums.typical.collection.factory;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Comparator;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingDeque;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.DelayQueue;
import java.util.concurrent.Delayed;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.PriorityBlockingQueue;
import java.util.concurrent.SynchronousQueue;

import javax.management.relation.Role;
import javax.management.relation.RoleList;
import javax.management.relation.RoleUnresolved;
import javax.management.relation.RoleUnresolvedList;

import org.apache.commons.collections4.Factory;
import org.apache.commons.collections4.Predicate;
import org.apache.commons.collections4.Transformer;
import org.apache.commons.collections4.list.CursorableLinkedList;
import org.apache.commons.collections4.list.FixedSizeList;
import org.apache.commons.collections4.list.GrowthList;
import org.apache.commons.collections4.list.LazyList;
import org.apache.commons.collections4.list.NodeCachingLinkedList;
import org.apache.commons.collections4.list.PredicatedList;
import org.apache.commons.collections4.list.SetUniqueList;
import org.apache.commons.collections4.list.TransformedList;
import org.apache.commons.collections4.list.TreeList;
import org.uranoplums.typical.lang.UraStaticObject;

/**
 * UraListFactoryクラス。<br>
 * 
 * @since 2015/03/03
 * @author syany
 */
/**
 * UraListFactoryクラス。<br>
 * 
 * @since 2015/03/10
 * @author syany
 */
public class UraListFactory extends UraStaticObject {

    /**
     * 指定された (固定) 容量とデフォルトのアクセスポリシーを持つ ArrayBlockingQueue を作成します。<br>
     * @param capacity このキューの容量
     * @return インスタンス
     * @throws IllegalArgumentException capacity < 1 の場合
     */
    public static final <E> BlockingQueue<E> newArrayBlockingQueue(final int capacity) {
        return new ArrayBlockingQueue<E>(capacity);
    }

    /**
     * 指定された (固定) 容量と指定されたアクセスポリシーを持つ ArrayBlockingQueue を作成します。<br>
     * @param capacity このキューの容量
     * @param fair true の場合、挿入または削除でブロックされたスレッドに対するキューアクセスが FIFO の順序で処理される。false の場合、アクセス順は指定されない。
     * @return インスタンス
     * @throws IllegalArgumentException capacity < 1 の場合
     */
    public static final <E> BlockingQueue<E> newArrayBlockingQueue(final int capacity, final boolean fair) {
        return new ArrayBlockingQueue<E>(capacity, fair);
    }

    /**
     * 指定された (固定) 容量と指定されたアクセスポリシーを持ち、指定されたコレクションの要素を初期状態で含む (要素はコレクションのイテレータのトラバーサル順に追加) ArrayBlockingQueue を作成します。<br>
     * @param capacity このキューの容量
     * @param fair true の場合、挿入または削除でブロックされたスレッドに対するキューアクセスが FIFO の順序で処理される。false の場合、アクセス順は指定されない。
     * @param c 初期状態で含む要素のコレクション
     * @return インスタンス
     * @throws IllegalArgumentException capacity < 1 の場合
     * @throws NullPointerException 指定されたコレクションまたはそのいずれかの要素が null である場合
     */
    public static final <E> BlockingQueue<E> newArrayBlockingQueue(final int capacity, final boolean fair, final Collection<? extends E> c) {
        return new ArrayBlockingQueue<E>(capacity, fair, c);
    }

    /**
     * 。<br>
     * @return インスタンス
     */
    public static final <E> Deque<E> newArrayDeque() {
        return new ArrayDeque<E>();
    }

    /**
     * 。<br>
     * @param c
     * @return インスタンス
     */
    public static final <E> Deque<E> newArrayDeque(final Collection<? extends E> c) {
        return new ArrayDeque<E>(c);
    }

    /**
     * 。<br>
     * @param numElements
     * @return インスタンス
     */
    public static final <E> Deque<E> newArrayDeque(final int numElements) {
        return new ArrayDeque<E>(numElements);
    }

    /**
     * 。<br>
     * @return インスタンス
     */
    public static final <E> List<E> newArrayList() {
        return new ArrayList<E>();
    }

    /**
     * 。<br>
     * @param c
     * @return インスタンス
     */
    public static final <E> List<E> newArrayList(final Collection<? extends E> c) {
        return new ArrayList<E>(c);
    }

    /**
     * 。<br>
     * @param initialCapacity
     * @return インスタンス
     */
    public static final <E> List<E> newArrayList(final int initialCapacity) {
        return new ArrayList<E>(initialCapacity);
    }

    /**
     * 。<br>
     * @return インスタンス
     */
    public static final <E> ConcurrentLinkedQueue<E> newConcurrentLinkedQueue() {
        return new ConcurrentLinkedQueue<E>();
    }

    /**
     * 。<br>
     * @param c
     * @return インスタンス
     */
    public static final <E> ConcurrentLinkedQueue<E> newConcurrentLinkedQueue(final Collection<? extends E> c) {
        return new ConcurrentLinkedQueue<E>(c);
    }

    /**
     * 。<br>
     * @return インスタンス
     */
    public static final <E> List<E> newCopyOnWriteArrayList() {
        return new CopyOnWriteArrayList<E>();
    }

    /**
     * 。<br>
     * @param c
     * @return インスタンス
     */
    public static final <E> List<E> newCopyOnWriteArrayList(final Collection<? extends E> c) {
        return new CopyOnWriteArrayList<E>(c);
    }

    /**
     * 。<br>
     * @param toCopyIn
     * @return インスタンス
     */
    public static final <E> List<E> newCopyOnWriteArrayList(final E[] toCopyIn) {
        return new CopyOnWriteArrayList<E>(toCopyIn);
    }

    /**
     * 。<br>
     * @return インスタンス
     */
    public static final <E> CursorableLinkedList<E> newCursorableLinkedList() {
        return new CursorableLinkedList<E>();
    }

    /**
     * 。<br>
     * @param c
     * @return インスタンス
     */
    public static final <E> CursorableLinkedList<E> newCursorableLinkedList(final Collection<? extends E> c) {
        return new CursorableLinkedList<E>(c);
    }

    /**
     * 。<br>
     * @return インスタンス
     */
    public static final <E extends Delayed> BlockingQueue<E> newDelayQueue() {
        return new DelayQueue<E>();
    }

    /**
     * 。<br>
     * @param c
     * @return インスタンス
     */
    public static final <E extends Delayed> BlockingQueue<E> newDelayQueue(final Collection<? extends E> c) {
        return new DelayQueue<E>(c);
    }

    /**
     * 。<br>
     * @param list
     * @return インスタンス
     */
    public static final <E> FixedSizeList<E> newFixedSizeList(final List<E> list) {
        return FixedSizeList.fixedSizeList(list);
    }

    /**
     * 。<br>
     * @return インスタンス
     */
    public static final <E> GrowthList<E> newGrowthList() {
        return new GrowthList<E>();
    }

    /**
     * 。<br>
     * @param initialSize
     * @return インスタンス
     */
    public static final <E> GrowthList<E> newGrowthList(final int initialSize) {
        return new GrowthList<E>(initialSize);
    }

    /**
     * 。<br>
     * @param list
     * @return インスタンス
     */
    public static final <E> GrowthList<E> newGrowthList(final List<E> list) {
        return GrowthList.growthList(list);
    }

    /**
     * 。<br>
     * @param list
     * @param factory
     * @return インスタンス
     */
    public static final <E> LazyList<E> newLazyList(final List<E> list, final Factory<? extends E> factory) {
        return LazyList.lazyList(list, factory);
    }

    /**
     * 。<br>
     * @return インスタンス
     */
    public static final <E> BlockingDeque<E> newLinkedBlockingDeque() {
        return new LinkedBlockingDeque<E>();
    }

    /**
     * 。<br>
     * @param c
     * @return インスタンス
     */
    public static final <E> BlockingDeque<E> newLinkedBlockingDeque(final Collection<? extends E> c) {
        return new LinkedBlockingDeque<E>(c);
    }

    /**
     * 。<br>
     * @param initialCapacity
     * @return インスタンス
     */
    public static final <E> BlockingDeque<E> newLinkedBlockingDeque(final int initialCapacity) {
        return new LinkedBlockingDeque<E>(initialCapacity);
    }

    /**
     * 。<br>
     * @return インスタンス
     */
    public static final <E> BlockingQueue<E> newLinkedBlockingQueue() {
        return new LinkedBlockingQueue<E>();
    }

    /**
     * 。<br>
     * @param c
     * @return インスタンス
     */
    public static final <E> BlockingQueue<E> newLinkedBlockingQueue(final Collection<? extends E> c) {
        return new LinkedBlockingQueue<E>(c);
    }

    /**
     * 。<br>
     * @param initialCapacity
     * @return インスタンス
     */
    public static final <E> BlockingQueue<E> newLinkedBlockingQueue(final int initialCapacity) {
        return new LinkedBlockingQueue<E>(initialCapacity);
    }

    /**
     * 。<br>
     * @return インスタンス
     */
    public static final <E> List<E> newLinkedList() {
        return new LinkedList<E>();
    }

    /**
     * 。<br>
     * @param c
     * @return インスタンス
     */
    public static final <E> List<E> newLinkedList(final Collection<? extends E> c) {
        return new LinkedList<E>(c);
    }

    /**
     * 。<br>
     * @return インスタンス
     */
    public static final <E> NodeCachingLinkedList<E> newNodeCachingLinkedList() {
        return new NodeCachingLinkedList<E>();
    }

    /**
     * 。<br>
     * @param c
     * @return インスタンス
     */
    public static final <E> NodeCachingLinkedList<E> newNodeCachingLinkedList(final Collection<? extends E> c) {
        return new NodeCachingLinkedList<E>(c);
    }

    /**
     * 。<br>
     * @param initialCapacity
     * @return インスタンス
     */
    public static final <E> NodeCachingLinkedList<E> newNodeCachingLinkedList(final int initialCapacity) {
        return new NodeCachingLinkedList<E>(initialCapacity);
    }

    /**
     * 。<br>
     * @param list
     * @param predicate
     * @return インスタンス
     */
    public static final <T> List<T> newPredicatedList(final List<T> list, final Predicate<? super T> predicate) {
        return PredicatedList.predicatedList(list, predicate);
    }

    /**
     * 。<br>
     * @return インスタンス
     */
    public static final <E> BlockingQueue<E> newPriorityBlockingQueue() {
        return new PriorityBlockingQueue<E>();
    }

    /**
     * 。<br>
     * @param c
     * @return インスタンス
     */
    public static final <E> BlockingQueue<E> newPriorityBlockingQueue(final Collection<? extends E> c) {
        return new PriorityBlockingQueue<E>(c);
    }

    /**
     * 。<br>
     * @param initialCapacity
     * @return インスタンス
     */
    public static final <E> BlockingQueue<E> newPriorityBlockingQueue(final int initialCapacity) {
        return new PriorityBlockingQueue<E>(initialCapacity);
    }

    /**
     * 。<br>
     * @param initialCapacity
     * @param comparator
     * @return インスタンス
     */
    public static final <E> BlockingQueue<E> newPriorityBlockingQueue(final int initialCapacity, final Comparator<? super E> comparator) {
        return new PriorityBlockingQueue<E>(initialCapacity, comparator);
    }

    /**
     * 。<br>
     * @return インスタンス
     */
    public static final List<Object> newRoleList() {
        return new RoleList();
    }

    /**
     * 。<br>
     * @param initialCapacity
     * @return インスタンス
     */
    public static final List<Object> newRoleList(final int initialCapacity) {
        return new RoleList(initialCapacity);
    }

    /**
     * 。<br>
     * @param list
     * @return インスタンス
     */
    public static final List<Object> newRoleList(final List<Role> list) {
        return new RoleList(list);
    }

    /**
     * 。<br>
     * @return インスタンス
     */
    public static final List<Object> newRoleUnresolvedList() {
        return new RoleUnresolvedList();
    }

    /**
     * 。<br>
     * @param initialCapacity
     * @return インスタンス
     */
    public static final List<Object> newRoleUnresolvedList(final int initialCapacity) {
        return new RoleUnresolvedList(initialCapacity);
    }

    /**
     * 。<br>
     * @param list
     * @return インスタンス
     */
    public static final List<Object> newRoleUnresolvedList(final List<RoleUnresolved> list) {
        return new RoleUnresolvedList(list);
    }

    /**
     * 。<br>
     * @param list
     * @return インスタンス
     */
    public static final <E> SetUniqueList<E> newSetUniqueList(final List<E> list) {
        return SetUniqueList.setUniqueList(list);
    }

    /**
     * 。<br>
     * @return インスタンス
     */
    public static final <E> BlockingQueue<E> newSynchronousQueue() {
        return new SynchronousQueue<E>();
    }

    /**
     * 。<br>
     * @param fair
     * @return インスタンス
     */
    public static final <E> BlockingQueue<E> newSynchronousQueue(final boolean fair) {
        return new SynchronousQueue<E>(fair);
    }

    /**
     * 。<br>
     * @param list
     * @param transformer
     * @return インスタンス
     */
    public static final <E> List<E> newTransformedList(final List<E> list,
            final Transformer<? super E, ? extends E> transformer) {
        return TransformedList.transformedList(list, transformer);
    }

    /**
     * 。<br>
     * @param list
     * @param transformer
     * @return インスタンス
     */
    public static final <E> List<E> newTransformingList(final List<E> list,
            final Transformer<? super E, ? extends E> transformer) {
        return TransformedList.transformingList(list, transformer);
    }

    /**
     * 。<br>
     * @return インスタンス
     */
    public static final <E> List<E> newTreeList() {
        return new TreeList<E>();
    }

    /**
     * 。<br>
     * @param c
     * @return インスタンス
     */
    public static final <E> List<E> newTreeList(final Collection<? extends E> c) {
        return new TreeList<E>(c);
    }
}
