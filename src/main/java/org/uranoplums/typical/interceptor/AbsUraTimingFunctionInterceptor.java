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
package org.uranoplums.typical.interceptor;

import org.uranoplums.typical.lang.threadlocal.UraThreadLocalAtomicLong;

/**
 * AbsUraTimingFunctionInterceptorクラス。<br>
 * 
 * @since 2015/02/23
 * @author syany
 * @param <T>
 */
public abstract class AbsUraTimingFunctionInterceptor<T> extends AbsUraFunctionInterceptor<T> {

    /**  */
    private static final UraThreadLocalAtomicLong ENDTIME = new UraThreadLocalAtomicLong();
    /**  */
    private static final UraThreadLocalAtomicLong STARTTIME = new UraThreadLocalAtomicLong();

    /*
     * (非 Javadoc)
     * 
     * @see org.uranoplums.typical.interceptor.AbsUraFunctionInterceptor#finish()
     */
    @Override
    protected void finish() {
        ENDTIME.get().set(System.nanoTime());
    }

    /**
     * 
     * @return
     */
    protected long getDiff() {
        return ENDTIME.getLong() - STARTTIME.getLong();
    }

    /**
     * 。<br>
     * 
     */
    protected void printDiff() {
        System.out.print(" (" + getDiff() + "[ns]) ");
    }

    /*
     * (非 Javadoc)
     * 
     * @see org.uranoplums.typical.interceptor.AbsUraFunctionInterceptor#start()
     */
    @Override
    protected void start() {
        STARTTIME.get().set(System.nanoTime());
    }

    /*
     * (非 Javadoc)
     * 
     * @see org.uranoplums.typical.interceptor.AbsUraFunctionInterceptor#run(org.uranoplums.typical.interceptor.
     * AbsUraFunctionInterceptor)
     */
    @Override
    public T run(T target) {
        try {
            super.run(target);
        } finally {
            STARTTIME.set(null);
            STARTTIME.remove();
            ENDTIME.set(null);
            ENDTIME.remove();
        }
        return target;
    }
}
