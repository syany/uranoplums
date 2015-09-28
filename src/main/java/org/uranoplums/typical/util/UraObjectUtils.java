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
 * $Id: UraObjectUtils.java$
 */
package org.uranoplums.typical.util;

import java.lang.reflect.Constructor;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.lang.reflect.TypeVariable;
import java.util.Collection;
import java.util.Map;
import java.util.Stack;

import org.apache.commons.lang3.ObjectUtils;
import org.uranoplums.typical.exception.UraSystemRuntimeException;
import org.uranoplums.typical.log.UraLogger;
import org.uranoplums.typical.log.UraLoggerFactory;

/**
 * UraObjectUtilsクラス。<br>
 *
 * @since 2015/04/10
 * @author syany
 */
public class UraObjectUtils extends ObjectUtils {

    protected static final UraLogger<String> LOGGER = UraLoggerFactory.getUraStringCodeLog();

    /**
     * デフォルトコンストラクタ。<br>
     */
    protected UraObjectUtils() {
        throw new AssertionError();
    }

    /**
     * 型パラメータの具象型取得の実装。再帰処理される。
     * @param clazz 現在の走査対象型
     * @param targetTypeName 現在の走査対象のジェネリクス型パラメータ名
     * @param stack 現在の走査対象型以下の継承階層が積まれたStack
     * @return 該当型パラメータの具現化された型
     */
    @SuppressWarnings ("unchecked")
    private static <T> Class<T> getGenericTypeImpl(Class<?> clazz,
            String targetTypeName, Stack<Class<?>> stack) {
        TypeVariable<? extends Class<?>>[] superGenTypeAray = clazz.getSuperclass().getTypeParameters();
        // 走査対象の型パラメータの名称(Tなど)から宣言のインデックスを取得
        int index = 0;
        boolean existFlag = false;
        for (final TypeVariable<? extends Class<?>> type : superGenTypeAray) {
            if (targetTypeName.equals(type.getName())) {
                existFlag = true;
                break;
            }
            index++;
        }
        if (!existFlag) {
            throw new IllegalArgumentException(
                    targetTypeName + "に合致するジェネリクス型パラメータがみつかりません");
        }
        // 走査対象の型パラメータが何型とされているのかを取得
        ParameterizedType type = (ParameterizedType) clazz.getGenericSuperclass();
        Type y = type.getActualTypeArguments()[index];
        // 具象型で継承されている場合
        if (y instanceof Class) {
            return (Class<T>) y;
        }
        // ジェネリックパラメータの場合
        if (y instanceof TypeVariable) {
            TypeVariable<Class<?>> tv = (TypeVariable<Class<?>>) y;
            // 再帰して同名の型パラメータを継承階層を下りながら解決を試みる
            Class<?> sub = stack.pop();
            return getGenericTypeImpl(sub, tv.getName(), stack);
        }
        // ジェネリック型パラメータを持つ型の場合
        if (y instanceof ParameterizedType) {
            ParameterizedType pt = (ParameterizedType) y;
            return (Class<T>) pt.getRawType();
        }
        throw new IllegalArgumentException("予期せぬ型 : "
                + y.toString() + " (" + y.getClass() + ")");
    }

    /**
     * 。<br>
     * @param source
     * @param klass
     * @return
     */
    public static final <E> E cast(Object source, Class<E> klass) {
        // @SuppressWarnings ("unchecked")
        // Class<E> klass = (Class<E>) type.getClass();
        if (source == null || !klass.isInstance(source)) {
            ClassLoader cl = UraClassUtils.getCurrentClassLoader(klass);
            try {
                @SuppressWarnings ("unchecked")
                Class<E> clz = (Class<E>) cl.loadClass(klass.getName());
                Constructor<E> co = clz.getDeclaredConstructor();
                co.setAccessible(true);
                return co.newInstance();
            } catch (Exception e) {
                UraSystemRuntimeException se = new UraSystemRuntimeException(e);
                LOGGER.log("ERROR: klass=[{}], source=[{}], exception=[{}]", klass, source, se.getLocalizedMessage(), se);
                throw se;
            }
        }
        return klass.cast(source);
    }

    /**
     * 。<br>
     * @param source
     * @param types
     * @return
     */
    public static final <E> E cast(Object source, E... types) {
        @SuppressWarnings ("unchecked")
        Class<E> klass = (Class<E>) types.getClass().getComponentType();
        return cast(source, klass);
    }

    /**
     * gg
     * 。<br>
     * @param source
     * @param targetArray
     * @return
     */
    public static final <E> E[] castToArray(Object source, E... targetArray) {
        try {
            @SuppressWarnings ("unchecked")
            Class<E[]> klass = (Class<E[]>) targetArray.getClass();
            if (source == null) {
                return targetArray;
            } else if (klass.isInstance(source)) {
                return klass.cast(source);
            } else if (source instanceof Collection) {
                @SuppressWarnings ("unchecked")
                Collection<E> c = Collection.class.cast(source);
                return c.toArray(targetArray);
            } else if (source instanceof Map) {
                @SuppressWarnings ("unchecked")
                Map<E, Object> m = Map.class.cast(source);
                return m.keySet().toArray(targetArray);
            }
        } catch (Exception e) {
            UraSystemRuntimeException se = new UraSystemRuntimeException(e);
            LOGGER.log("ERROR: arrayClass=[{}], source=[{}], exception=[{}]", targetArray, source, se.getLocalizedMessage(), se);
            throw se;
        }
        return targetArray;
    }

    /**
     * オブジェクト同士を確認します。<br>
     * 第一引数のequalsを利用します。
     * @param src
     * @param dst
     * @return
     */
    public static boolean equals(Object src, Object dst) {
        if (src == null) {
            return dst == null;
        }
        return src.equals(dst);
    }

    /**
     * 渡された型から継承階層を登って、
     * 指定の親の型の指定の名前のジェネリクス型パラメータが
     * 継承の過程で何型で具現化されているかを走査して返す。
     * @param clazz 走査開始する型
     * @param targetClass 走査する対象のジェネリクス型パラメータを持つ型。
     *            走査開始型の親である必要がある。
     * @param targetTypeName 何型で具現化されたを確認したい型パラメータのプレースホルダ名
     * @return 具現化された型
     */
    public static <T> Class<T> getGenericType(
            Class<?> clazz, Class<?> targetClass,
            String targetTypeName) {
        if (!targetClass.isAssignableFrom(clazz)) {
            throw new IllegalArgumentException(
                    "型" + clazz.getName() + "は、型"
                            + targetClass.getName() + "を継承していません");
        }
        Stack<Class<?>> stack = new Stack<Class<?>>();
        while (!targetClass.equals(clazz.getSuperclass())) {
            stack.push(clazz);
            clazz = clazz.getSuperclass();
        }
        return getGenericTypeImpl(clazz, targetTypeName, stack);
    }
}
