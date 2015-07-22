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
 * $Id: UraEncoderRuntimeException.java$
 */
package org.uranoplums.typical.exception;

/**
 * UraEncoderExceptionクラス。<br>
 * 
 * @since 2015/06/10
 * @author syany
 */
public class UraEncoderRuntimeException extends UraRuntimeException {

    /**  */
    private static final long serialVersionUID = 3394732630182791222L;

    /**
     * デフォルトコンストラクタ。<br>
     */
    public UraEncoderRuntimeException() {}

    /**
     * デフォルトコンストラクタ。<br>
     * @param message
     */
    public UraEncoderRuntimeException(String message) {
        super(message);
    }

    /**
     * デフォルトコンストラクタ。<br>
     * @param message
     * @param cause
     */
    public UraEncoderRuntimeException(String message, Throwable cause) {
        super(message, cause);
    }

    /**
     * デフォルトコンストラクタ。<br>
     * @param cause
     */
    public UraEncoderRuntimeException(Throwable cause) {
        super(cause);
    }
}
