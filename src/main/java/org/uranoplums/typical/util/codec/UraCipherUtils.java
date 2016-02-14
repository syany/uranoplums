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
 * $Id: UraCipherUtils.java$
 */
package org.uranoplums.typical.util.codec;

import static org.uranoplums.typical.collection.factory.UraListFactory.*;

import java.security.NoSuchAlgorithmException;
import java.security.Security;
import java.security.spec.AlgorithmParameterSpec;
import java.util.List;
import java.util.Set;

import javax.crypto.Cipher;

import org.uranoplums.typical.util.UraUtils;

/**
 * UraCipherUtilsクラス。<br>
 *
 * @since 2016/01/25
 * @author syany
 */
public class UraCipherUtils extends UraUtils {
    public static final String CIPHER = "Cipher";
    public static final List<String> getCipherList() {
        List<String> result = newArrayList(4);
        Set<String> names = Security.getAlgorithms(CIPHER);
        try {
            for (final String name : names) {
                final StringBuilder sb = new StringBuilder(4);
                sb.append(name);
                sb.append("(max:");
                int bit = Cipher.getMaxAllowedKeyLength(name);
                sb.append(String.format("%d", bit));
                sb.append("[bit])");
                result.add(sb.toString());
            }
        } catch (NoSuchAlgorithmException e) {
            // ありえない
            e.printStackTrace();
        }
        return result;
    }
    public static final List<String> getCipherListDetail() {
        List<String> result = newArrayList(4);
        Set<String> names = Security.getAlgorithms(CIPHER);
        try {
            for (final String name : names) {
                final StringBuilder sb = new StringBuilder(4);
                sb.append(name);
                sb.append("(");
                AlgorithmParameterSpec spec = Cipher.getMaxAllowedParameterSpec(name);
                //int bit = Cipher.getMaxAllowedKeyLength(name);
                if (spec != null)
                sb.append(spec.toString());
                sb.append(")");
                result.add(sb.toString());
            }
        } catch (NoSuchAlgorithmException e) {
            // ありえない
            e.printStackTrace();
        }
        return result;
    }
}
