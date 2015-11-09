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
 * $Id: UraFileByteReader.java$
 */
package org.uranoplums.typical.io;

import java.io.IOException;


/**
 * UraFileByteReaderクラス。<br>
 *
 * @since 2015/11/10
 * @author syany
 */
public interface UraFileByteReader extends UraFileBuffer {
    public void readByteArray(final byte[] buffer, final String path, final int bufferSize, long readCount) throws IOException;
}
