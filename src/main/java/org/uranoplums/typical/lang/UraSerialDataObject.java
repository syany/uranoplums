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

import java.io.Serializable;

import org.apache.commons.lang3.SerializationUtils;

/**
 * データオブジェクト向けクラス。<br>
 * データオブジェクトでシリアル化が必要な場合に使用する。
 * 
 * @since 2014/01/21
 * @author syany
 */
public class UraSerialDataObject extends UraDataObject implements Serializable {

    /** シリアル・バージョンUID */
    private static final long serialVersionUID = -6765885462623432457L;

    /**
     * デフォルトコンストラクタ。<br>
     * 
     */
    public UraSerialDataObject() {
        super();
    }

    /**
     * ディープコピーます。<br>
     * 
     * @return ディープコピーを返却します。
     */
    public UraSerialDataObject deepClone() {
        return SerializationUtils.clone(this);
    }
}
