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

import static org.uranoplums.typical.collection.factory.UraListFactory.*;

import java.util.Collection;
import java.util.List;


/**
 * UraListUtilsクラス。<br>
 *
 * @since 2015/11/05
 * @author syany
 */
public class UraListUtils extends UraCollectionUtils {

    public static final <E> List<E> toList(E... srcArray) {
        List<E> list = newArrayList();
        _toCollection(list, srcArray);
        return list;
    }

    // ユニークリスト出力
    /**
     * 。<br>
     * @param source
     * @return
     */
    public static final <E> List<E> getUniqueList(Collection<E> source) {
        return newArrayList(UraSetUtils.toSet(source));
    }

    /**
     * 。<br>
     * @param source
     * @return
     */
    public static final <E> List<E> getDuplicateList(Collection<E> source) {
        return newArrayList(UraSetUtils.getDuplicateSet(source));
    }
}
