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

import org.uranoplums.typical.lang.UraObject;

/**
 * クラス。<br>
 * @since 2015/02/23
 * @author syany
 */
public abstract class AbsUraLogicHandler<T> extends UraObject implements UraLogicHandler<T> {

    T target;

    /**
	 *
	 */
    public AbsUraLogicHandler(T target) {
        this.target = target;
    }

    @Override
    public final void callback(T target) {
        callbackLogic(target);
    }

    abstract public void callbackLogic(T target);
}
