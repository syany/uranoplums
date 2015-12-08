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
 * $Id: UraClassUtils.java$
 */
package org.uranoplums.typical.util;

import java.io.File;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;

import org.uranoplums.typical.log.UraLogger;
import org.uranoplums.typical.log.UraLoggerFactory;

/**
 * UraClassUtilsクラス。<br>
 *
 * @since 2015/04/09
 * @author syany
 */
public class UraClassUtils extends UraUtils {

    /**  */
    protected static final UraLogger<String> LOGGER = UraLoggerFactory.getUraStringCodeLog();

    /**
     * デフォルトコンストラクタ。<br>
     */
    public UraClassUtils() {
        super();
    }

    /**
     * 。<br>
     * @param classLoader
     * @param path
     * @throws MalformedURLException
     * @throws IllegalArgumentException
     * @throws IllegalAccessException
     * @throws InvocationTargetException
     * @throws SecurityException
     * @throws NoSuchMethodException
     */
    public static void addClassPath(ClassLoader classLoader, String path) throws MalformedURLException, IllegalArgumentException,
            IllegalAccessException, InvocationTargetException, SecurityException, NoSuchMethodException {
        if (classLoader instanceof URLClassLoader) {
            // URLClassLoaderであることが前提
            Method method =
                    URLClassLoader.class.getDeclaredMethod("addURL", URL.class);
            method.setAccessible(true);
            // ロードするURLを追加する
            method.invoke(classLoader, new File(path).toURI().toURL());
        }
    }

    /**
     * 対象配列のクラスを返却します。<br>
     * @param classArray 対象配列
     * @return 対象配列のクラス
     */
    @SuppressWarnings ({"unchecked"})
    public static final <E> Class<E> getArrayClass(E classArray) {
        return (Class<E>) classArray.getClass().getComponentType();
    }

    /**
     * 対象配列のクラスを返却します。<br>
     * @param classArray 対象配列
     * @return 対象配列のクラス
     */
    @SuppressWarnings ({"unchecked"})
    public static final <E> Class<E> getArrayClass(E... classArray) {
        return (Class<E>) classArray.getClass().getComponentType();
    }

    /**
     * 配列かどうか。<br>
     * @param o
     * @return
     */
    public static final boolean isArray(Object o){
        return o.getClass().isArray();
    }

    /**
     * プリミティブ型かどうか。<br>
     * @param o
     * @return
     */
    public static final boolean isPrimitive(Object o) {
        return o.getClass().isPrimitive();
    }

    /**
     * プリミティブ型の配列かどうか。<br>
     * @param o
     * @return
     */
    public static final boolean isPrimitiveArray(Object o) {
        if (!isArray(o)) {
            return false;
        }
        return o.getClass().getComponentType().isPrimitive();
    }

    /**
     * クラスローダをカレントスレッドコンテキスト、システムの順に取得を試みます。<br>
     * @return 取得したクラスローダ
     */
    public static final ClassLoader getCurrentClassLoader() {
        ClassLoader classLoader = Thread.currentThread()
                .getContextClassLoader();
        if (classLoader == null) {
            classLoader = ClassLoader.getSystemClassLoader();
        }
        return classLoader;
    }

    /**
     * クラスローダをカレントスレッドコンテキスト、システム、自クラスの順に取得を試みます。<br>
     * @param klass
     * @return 取得したクラスローダ
     */
    public static final <E> ClassLoader getCurrentClassLoader(Class<E> klass) {
        ClassLoader classLoader = Thread.currentThread()
                .getContextClassLoader();
        if (classLoader == null) {
            if (klass != null) {
                classLoader = klass.getClassLoader();
            }
            if (classLoader == null) {
                classLoader = ClassLoader.getSystemClassLoader();
            }
        }
        return classLoader;
    }

    /**
     * 対象が指定したインタフェースを持っていればtrueを返却します。<br>
     * @param source
     * @param target
     * @return sourceにtargetのインタフェースがあればtrue
     */
    public static final <S, E> boolean hasInterface(Class<S> source, Class<E> target) {
        Class<?>[] intarfaceArray = source.getInterfaces();
        for (final Class<?> klass : intarfaceArray) {
            if (hasInterface(klass, target)) {
                return true;
            }
        }
        return source.equals(target);
    }

    /**
     * 対象クラスがロードされているか判定
     * @param klass
     * @return ロードされているならばtrue
     */
    public final boolean isClassAvailable(String klass) {
        if (klass == null) {
            return false;
        }
        ClassLoader classLoader = getCurrentClassLoader(UraClassUtils.class);
        try {
            Class<?> bindingClass = classLoader.loadClass(klass);
            return (bindingClass != null);
        } catch (ClassNotFoundException e) {
            LOGGER.log("TRACE: class[{}] is not available.", klass);
            return false;
        }
    }
}
