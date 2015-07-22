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
 * $Id: ElementData.java$
 */
package org.uranoplums.typical.lang;


/**
 * ElementDataAクラス。<br>
 *
 * @since 2015/07/21
 * @author syany
 */
public class ElementDataA extends UraDataObject {
    protected int id = 89;
    protected String name = "熱川";

    /**
     * @param id idを設定します。
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * @param name nameを設定します。
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @param length lengthを設定します。
     */
    public void setLength(double length) {
        this.length = length;
    }
    protected double length = 78.98d;
}
