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
 * $Id: UraMessageDigestAlgorithms.java$
 */
package org.uranoplums.typical.util.codec;

import org.uranoplums.typical.util.UraUtils;

/**
 * UraMessageDigestAlgorithmsクラス。<br>
 * 
 * @since 2015/06/10
 * @author syany
 */
public class UraMessageDigestAlgorithms extends UraUtils {

    /**
     * The MD2 message digest algorithm defined in RFC 1319.
     */
    public static final String MD2 = "MD2";
    /**
     * The MD5 message digest algorithm defined in RFC 1321.
     */
    public static final String MD5 = "MD5";
    /**
     * The SHA-1 hash algorithm defined in the FIPS PUB 180-2.
     */
    public static final String SHA_1 = "SHA-1";
    /**
     * The SHA-256 hash algorithm defined in the FIPS PUB 180-2.
     */
    public static final String SHA_256 = "SHA-256";
    /**
     * The SHA-384 hash algorithm defined in the FIPS PUB 180-2.
     */
    public static final String SHA_384 = "SHA-384";
    /**
     * The SHA-512 hash algorithm defined in the FIPS PUB 180-2.
     */
    public static final String SHA_512 = "SHA-512";
}
