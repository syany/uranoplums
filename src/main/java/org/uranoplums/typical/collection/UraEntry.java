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
 * $Id: UraEntry.java$
 */
package org.uranoplums.typical.collection;

import java.util.Map.Entry;

/**
 * UraEntryクラス。<br>
 * 
 * @since 2015/04/10
 * @author syany
 * @param <K>
 * @param <V>
 */
public interface UraEntry<K, V> extends Entry<K, V> {

    /**
     * 。<br>
     * @param key
     * @return 取得前のキー
     */
    K setKey(K key);
}
