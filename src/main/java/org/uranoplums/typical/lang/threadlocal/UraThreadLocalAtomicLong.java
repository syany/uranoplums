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
 * $Id: UraThreadLocalAtomicLong.java$
 */
package org.uranoplums.typical.lang.threadlocal;

import java.util.concurrent.atomic.AtomicLong;

/**
 * ローカル同期Longクラス。<br>
 * 
 * @since 2014/02/22
 * @author syany
 */
public class UraThreadLocalAtomicLong extends ThreadLocal<AtomicLong> {

    /*
     * (非 Javadoc)
     * 
     * @see java.lang.ThreadLocal#initialValue()
     */
    @Override
    protected AtomicLong initialValue() {
        return new AtomicLong(0L);
    }

    /**
     * 1マイナスします。<br>
     * 
     * @return マイナス後の値を返却します。
     */
    public long decrement() {
        return this.decrementAndGet();
    }

    /**
     * 1マイナス後の情報を返却します。<br>
     * 
     * @return 1マイナス後の情報を返却します。
     */
    public long decrementAndGet() {
        return this.get().decrementAndGet();
    }

    /**
     * 取得した後1マイナスします。<br>
     * 
     * @return マイナス前の値を返却。
     */
    public long getAndDecrement() {
        return this.get().getAndDecrement();
    }

    /**
     * 取得した後1プラスします。。<br>
     * 
     * @return プラス前の値を返却
     */
    public long getAndIncrement() {
        return this.get().getAndIncrement();
    }

    /**
     * longプリミティブ型で取得します。<br>
     * 
     * @return 現在値をプリミティブlong型で返却します。
     */
    public long getLong() {
        return this.get().longValue();
    }

    /**
     * 1プラスします。<br>
     * 
     * @return プラス後の値を返却します。
     */
    public long increment() {
        return this.incrementAndGet();
    }

    /**
     * 1プラス後の情報を返却します。<br>
     * 
     * @return 1プラス後の情報を返却します。
     */
    public long incrementAndGet() {
        return this.get().incrementAndGet();
    }
}
