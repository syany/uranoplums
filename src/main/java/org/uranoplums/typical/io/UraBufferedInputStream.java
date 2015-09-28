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
 * $Id: UraBufferedInputStream.java$
 */
package org.uranoplums.typical.io;

import java.io.BufferedInputStream;
import java.io.InputStream;


/**
 * UraBufferedInputStreamクラス。<br>
 *
 * @since 2015/10/30
 * @author syany
 */
public class UraBufferedInputStream extends BufferedInputStream {

    /**
     * コンストラクタ。<br>
     * @param paramInputStream
     */
    public UraBufferedInputStream(InputStream paramInputStream) {
        super(paramInputStream);
    }

    /**
     * コンストラクタ。<br>
     * @param paramInputStream
     * @param paramInt
     */
    public UraBufferedInputStream(InputStream paramInputStream, int paramInt) {
        super(paramInputStream, paramInt);
    }
}
