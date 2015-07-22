/*
 * Copyright 2013-2015 the Uranoplums Foundation and the Others,
 * the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.
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
 * $Id: UraEncoder.java$
 */
package org.uranoplums.typical.util.codec;

import org.uranoplums.typical.exception.UraEncoderRuntimeException;

/**
 * UraEncoderクラス。<br>
 * 
 * @since 2015/06/10
 * @author syany
 */
public interface UraEncoder {

    /**
     * Encodes an "Object" and returns the encoded content as an Object. The Objects here may just be
     * <code>byte[]</code> or <code>String</code>s depending on the implementation used.
     * 
     * @param source
     *            An object to encode
     * @return An "encoded" Object
     * @throws EncoderException
     *             An encoder exception is thrown if the encoder experiences a failure condition during the encoding
     *             process.
     */
    Object encode(Object source) throws UraEncoderRuntimeException;
}
