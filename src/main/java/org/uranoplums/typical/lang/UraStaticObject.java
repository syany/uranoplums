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
 * $Id: UraStaticObject.java$
 */
package org.uranoplums.typical.lang;

import java.io.ObjectStreamException;

/**
 * UraStaticObjectクラス。<br>
 * 
 * @since 2015/02/24
 * @author syany
 */
public class UraStaticObject extends UraObject {

    /**
     * デフォルトコンストラクタ。<br>
     */
    protected UraStaticObject() {
        throw new AssertionError();
    }

    /**
     * @throws ObjectStreamException <code>AssertionError</code>を常にスローします。
     * @deprecated
     *             デシリアライズも出来ません。<br>
     *             <code>AssertionError</code>を常にスローします。
     * @throws AssertionError
     */
    @Deprecated
    private final Object readResolve() throws ObjectStreamException {
        throw new AssertionError();
    }

    /**
     * @deprecated
     *             ユーティリティクラスではクローンを作ることができません。<br>
     *             <code>AssertionError</code>を常にスローします。
     * @throws AssertionError
     * @see org.uranoplums.typical.lang.UraObject#clone()
     */
    @Deprecated
    @Override
    public final UraObject clone() {
        throw new AssertionError();
    }

    /**
     * @param paramT
     * @return <code>AssertionError</code>を常にスローします。
     * @deprecated
     *             オブジェクトを比較出来ません。<br>
     *             <code>AssertionError</code>を常にスローします。
     * @throws AssertionError
     * @see java.lang.Comparable#compareTo(java.lang.Object)
     */
    @Deprecated
    public final int compareTo(Object paramT) {
        throw new AssertionError();
    }
}
