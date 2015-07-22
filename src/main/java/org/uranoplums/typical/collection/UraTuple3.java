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
 * $Id: UraTuple3.java$
 */
package org.uranoplums.typical.collection;

import org.uranoplums.typical.lang.UraSerialDataObject;

/**
 * UraTuple3クラス。<br>
 * 
 * @since 2015/03/10
 * @author syany
 * @param <T1>
 *            1番目の値の型
 * @param <T2>
 *            2番目の値の型
 * @param <T3>
 *            3番目の値の型
 */
public class UraTuple3<T1, T2, T3> extends UraSerialDataObject {

    /**  */
    private static final long serialVersionUID = -6267191276919584020L;
    /** 1番目の値 */
    protected T1 value1;
    /** 2番目の値 */
    protected T2 value2;
    /** 3番目の値 */
    protected T3 value3;

    /**
     * デフォルトコンストラクタ。<br>
     */
    public UraTuple3() {}

    /**
     * インスタンスを構築します。
     * 
     * @param value1
     *            1番目の値
     * @param value2
     *            2番目の値
     * @param value3
     *            3番目の値
     */
    public UraTuple3(final T1 value1, final T2 value2, final T3 value3) {
        this.value1 = value1;
        this.value2 = value2;
        this.value3 = value3;
    }

    /**
     * 3つの値の組を作成して返します。
     * 
     * @param <T1>
     *            1番目の値の型
     * @param <T2>
     *            2番目の値の型
     * @param <T3>
     *            3番目の値の型
     * @param value1
     *            1番目の値
     * @param value2
     *            2番目の値
     * @param value3
     *            3番目の値
     * @return 3つの値の組
     */
    public static <T1, T2, T3> UraTuple3<T1, T2, T3> tuple3(final T1 value1,
            final T2 value2, final T3 value3) {
        return new UraTuple3<T1, T2, T3>(value1, value2, value3);
    }

    /**
     * 1番目の値を返します。
     * 
     * @return 1番目の値
     */
    public T1 getValue1() {
        return value1;
    }

    /**
     * 2番目の値を返します。
     * 
     * @return 2番目の値
     */
    public T2 getValue2() {
        return value2;
    }

    /**
     * 3番目の値を返します。
     * 
     * @return 3番目の値
     */
    public T3 getValue3() {
        return value3;
    }

    /**
     * 1番目の値を設定します。
     * 
     * @param value1
     *            1番目の値
     */
    public void setValue1(final T1 value1) {
        this.value1 = value1;
    }

    /**
     * 2番目の値を設定します。
     * 
     * @param value2
     *            2番目の値
     */
    public void setValue2(final T2 value2) {
        this.value2 = value2;
    }

    /**
     * 3番目の値を設定します。
     * 
     * @param value3
     *            3番目の値
     */
    public void setValue3(final T3 value3) {
        this.value3 = value3;
    }
}
