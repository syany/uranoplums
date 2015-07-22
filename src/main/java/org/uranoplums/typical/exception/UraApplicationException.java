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
package org.uranoplums.typical.exception;

/**
 * UraApplicationExceptionクラス。<br>
 * 
 * @since 2015/02/23
 * @author syany
 */
public class UraApplicationException extends UraException {

    /** シリアル・バージョンUID */
    private static final long serialVersionUID = -3711568976581073810L;

    /**
     * デフォルトコンストラクタ。<br>
     * 
     */
    public UraApplicationException() {
        super("Error occurred in application.");
    }

    /**
     * コンストラクタ。<br>
     * @param message
     */
    public UraApplicationException(String message) {
        super(message);
    }

    /**
     * コンストラクタ。<br>
     * @param message
     * @param cause
     */
    public UraApplicationException(String message, Throwable cause) {
        super(message, cause);
    }

    /**
     * コンストラクタ。<br>
     * @param cause
     */
    public UraApplicationException(Throwable cause) {
        super(cause);
    }
}
