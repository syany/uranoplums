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
 * $Id: UraDecoderRuntimeException.java$
 */
package org.uranoplums.typical.exception;

/**
 * UraDecoderExceptionクラス。<br>
 * 
 * @since 2015/06/10
 * @author syany
 */
public class UraDecoderRuntimeException extends UraRuntimeException {

    /**  */
    private static final long serialVersionUID = -1404848073031255162L;

    /**
     * デフォルトコンストラクタ。<br>
     */
    public UraDecoderRuntimeException() {}

    /**
     * デフォルトコンストラクタ。<br>
     * @param message
     */
    public UraDecoderRuntimeException(String message) {
        super(message);
    }

    /**
     * デフォルトコンストラクタ。<br>
     * @param message
     * @param cause
     */
    public UraDecoderRuntimeException(String message, Throwable cause) {
        super(message, cause);
    }

    /**
     * デフォルトコンストラクタ。<br>
     * @param cause
     */
    public UraDecoderRuntimeException(Throwable cause) {
        super(cause);
    }
}
