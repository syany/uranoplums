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

/**
 * @author syany
 * @param <T>
 * 
 */
public abstract class AbsUraFunctionInterceptor<T> extends UraBaseInterceptor {

    /**
	 *
	 */
    public AbsUraFunctionInterceptor() {
        super();
    }

    abstract protected void callback(T target);

    abstract protected void finish();

    protected void funcFinally() {
        // empty
    }

    protected void funcInit() {
        // Empty
    }

    abstract protected void start();

    /**
     * 。<br>
     * @param target
     * @return インスタンス
     */
    public T run(T target) {
        try {
            funcInit();
            start();
            this.callback(target);
        } finally {
            finish();
            funcFinally();
        }
        return target;
    }
}
