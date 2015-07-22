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
 * $Id: UraParseRuntimeException.java$
 */
package org.uranoplums.typical.exception;

/**
 * UraParseExceptionクラス。<br>
 * 
 * @since 2015/05/20
 * @author syany
 */
public class UraParseRuntimeException extends UraRuntimeException {

    /**  */
    private static final long serialVersionUID = -1512133655865424237L;
    // ============ privates ============
    /**
     * The zero-based character offset into the string being parsed at which
     * the error was found during parsing.
     * @serial
     */
    private final int errorOffset;

    /**
     * Constructs a ParseException with the specified detail message and
     * offset.
     * A detail message is a String that describes this particular exception.
     * @param s the detail message
     * @param errorOffset the position where the error is found while parsing.
     */
    public UraParseRuntimeException(String s, int errorOffset) {
        super(s);
        this.errorOffset = errorOffset;
    }

    /**
     * Returns the position where the error was found.
     */
    public int getErrorOffset() {
        return errorOffset;
    }
}
