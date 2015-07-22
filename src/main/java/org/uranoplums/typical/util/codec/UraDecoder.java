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
 * $Id: UraDecoder.java$
 */
package org.uranoplums.typical.util.codec;

import org.uranoplums.typical.exception.UraDecoderRuntimeException;

/**
 * UraDecoderクラス。<br>
 * 
 * @since 2015/06/10
 * @author syany
 */
public interface UraDecoder {

    /**
     * Decodes an "encoded" Object and returns a "decoded" Object. Note that the implementation of this interface will
     * try to cast the Object parameter to the specific type expected by a particular Decoder implementation. If a
     * {@link ClassCastException} occurs this decode method will throw a DecoderException.
     * 
     * @param source
     *            the object to decode
     * @return a 'decoded" object
     * @throws DecoderException
     *             a decoder exception can be thrown for any number of reasons. Some good candidates are that the
     *             parameter passed to this method is null, a param cannot be cast to the appropriate type for a
     *             specific encoder.
     */
    Object decode(Object source) throws UraDecoderRuntimeException;
}
