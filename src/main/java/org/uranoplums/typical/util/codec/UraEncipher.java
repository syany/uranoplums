/*
 * Copyright 2013-2016 the Uranoplums Foundation and the Others.
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
 * $Id: UraCipher.java$
 */
package org.uranoplums.typical.util.codec;

import java.security.AlgorithmParameters;
import java.security.Key;
import java.security.Provider;

import org.uranoplums.typical.util.codec.UraCipher.AlgorithmType;
import org.uranoplums.typical.util.codec.UraCipher.FeedbackMode;
import org.uranoplums.typical.util.codec.UraCipher.PaddingType;


/**
 * UraEncipherクラス。<br>
 *
 * @since 2016/01/26
 * @author syany
 */
public interface UraEncipher {
    public int getKeySize();
    public Key getKey();
//    public Key getPrivateKey();
    public byte[] getIv();
    public byte[] getEncryptData();
    public FeedbackMode getFeedbackMode();
    public AlgorithmType getAlgorithm();
    public AlgorithmParameters getAlgorithmParameters();
    public Provider getProvider();
    public PaddingType getPadding();
}
