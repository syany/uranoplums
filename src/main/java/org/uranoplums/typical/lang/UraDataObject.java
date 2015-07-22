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
package org.uranoplums.typical.lang;

import org.apache.commons.lang3.builder.CompareToBuilder;

/**
 * データオブジェクト向け抽象クラス。<br>
 * データオブジェクトとなり得るBean、Form、Dao、Dxo、Dto等は本フレームワーク内では本クラスを継承し、実装する。
 * 
 * @since 2014/01/21
 * @author syany
 */
public abstract class UraDataObject extends UraObject implements
        Comparable<UraDataObject> {

    /**
     * コンストラクタ
     */
    public UraDataObject() {
        super();
    }

    /*
     * (非 Javadoc)
     * 
     * @see org.uranoplums.typical.UraObject#clone()
     */
    @Override
    public UraDataObject clone() {
        return (UraDataObject) super.clone();
    }

    /**
     * オブジェクトを比較します。
     * 
     * @see java.lang.Comparable#compareTo(java.lang.Object)
     */
    @Override
    public int compareTo(UraDataObject paramT) {
        return CompareToBuilder.reflectionCompare(this, paramT);
    }
}
