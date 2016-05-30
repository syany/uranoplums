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
 * $Id: UraUtils.java$
 */
package org.uranoplums.typical.util;

import org.uranoplums.typical.lang.UraStaticObject;

/**
 * ユーティリティ基本クラス。<br>
 * @since 2014/01/23
 * @author syany
 */
public abstract class UraUtils extends UraStaticObject implements UraUtilizable {
     /**
     * コンストラクタ
     */
     protected UraUtils() {
         throw new AssertionError();
     }

//     /**
//     * @deprecated
//     * ユーティリティクラスではクローンを作ることができません。<br>
//     * <code>AssertionError</code>を常にスローします。
//     * @throws AssertionError
//     * @see org.uranoplums.typical.lang.UraObject#clone()
//     */
//     @Deprecated
//     @Override
//     public final UraObject clone() {
//     throw new AssertionError();
//     }
}
