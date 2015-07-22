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
package org.uranoplums.typical.lang.builder;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.Arrays;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.lang3.ClassUtils;
import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.uranoplums.typical.util.UraStringUtils;

/**
 * 出力制限機能付ReflectionToStringBuilder。<br>
 * @author syany
 *
 */
public class UraToStringBuilder extends ReflectionToStringBuilder {

    /**  */
    private static final ThreadLocal<Pattern> INCLUDE_FIELD_PATTERN = new ThreadLocal<Pattern>() {

        /*
         * (非 Javadoc)
         *
         * @see java.lang.ThreadLocal#initialValue()
         */
        @Override
        protected Pattern initialValue() {
            return Pattern.compile(UraStringUtils.EMPTY);
        }
    };

    /**
     * @param object
     */
    public UraToStringBuilder(Object object) {
        super(object);
    }

    /**
     * @param object
     * @param style
     */
    public UraToStringBuilder(Object object, ToStringStyle style) {
        super(object, style);
    }

    /**
     * @param object
     * @param style
     * @param buffer
     */
    public UraToStringBuilder(Object object, ToStringStyle style,
            StringBuffer buffer) {
        super(object, style, buffer);
    }

    // /**
    // * @param object
    // * @param style
    // * @param buffer
    // * @param reflectUpToClass
    // * @param outputTransients
    // * @param outputStatics
    // */
    // public UraToStringBuilder(Object object, ToStringStyle style,
    // StringBuffer buffer, Class<?> reflectUpToClass,
    // boolean outputTransients, boolean outputStatics) {
    // super(object, style, buffer, reflectUpToClass, outputTransients,
    // outputStatics);
    // }
    /**
     * デフォルトコンストラクタ。<br>
     * @param object
     * @param style
     * @param buffer
     * @param reflectUpToClass
     * @param outputTransients
     * @param outputStatics
     */
    public <T> UraToStringBuilder(T object, ToStringStyle style, StringBuffer buffer, Class<? super T> reflectUpToClass, boolean outputTransients,
            boolean outputStatics) {
        super(object, style, buffer, reflectUpToClass, outputTransients, outputStatics);
    }

    /*
     * (非 Javadoc)
     *
     * @see
     * org.apache.commons.lang.builder.ReflectionToStringBuilder#accept(java
     * .lang.reflect.Field)
     */
    @Override
    protected boolean accept(Field field) {
        if (field.getName().indexOf(ClassUtils.INNER_CLASS_SEPARATOR_CHAR) != -1) {
            // Reject field from inner class.
            return false;
        }
        if (Modifier.isTransient(field.getModifiers())
                && !this.isAppendTransients()) {
            // Reject transient fields.
            return false;
        }
        if (Modifier.isStatic(field.getModifiers()) && !this.isAppendStatics()) {
            // Reject static fields.
            return false;
        }
        /*
         * 追加箇所：追加対象が1つでもある場合は、以下の処理へ
         */
        if (UraStringUtils.isNotEmpty(INCLUDE_FIELD_PATTERN.get().pattern())) {
            boolean result = false;
            // 部分検索
            Matcher mField = INCLUDE_FIELD_PATTERN.get().matcher(field.getName());
            if (mField.find()) {
                // 部分検索に当たる場合、true
                result = true;
            }
            // 且つ、対象外フィールドでないことが条件となる
            if (this.getExcludeFieldNames() != null
                    && Arrays.binarySearch(this.getExcludeFieldNames(),
                            field.getName()) >= 0) {
                // Reject fields from the getExcludeFieldNames list.
                result = false;
            }
            //
            return result;
        }
        // else {
        // 追加対象パターンがなければ、通常ReflectonToStringBuilderと同様の動き
        if (this.excludeFieldNames != null
                && this.getExcludeFieldNames() != null
                && Arrays.binarySearch(this.getExcludeFieldNames(),
                        field.getName()) >= 0) {
            // Reject fields from the getExcludeFieldNames list.
            return false;
        }
        // }
        return true;
    }

    // public void removeIncludeFileName(String regex) {
    // PATTERN_MAP.remove(regex);
    // }
    /**
     *
     * @param regex
     * @return 自インスタンス
     */
    public UraToStringBuilder setIncludeFieldNamesPerttern(String regex) {
        final String patternString = INCLUDE_FIELD_PATTERN.get().pattern();
        if (UraStringUtils.isEmpty(patternString)) {
            INCLUDE_FIELD_PATTERN.set(Pattern.compile(regex));
        } else {
            final StringBuilder sb = new StringBuilder(patternString);
            sb.append("|");
            sb.append(regex);
            INCLUDE_FIELD_PATTERN.set(Pattern.compile(sb.toString()));
        }
        return this;
    }

    /*
     * (非 Javadoc)
     *
     * @see org.apache.commons.lang.builder.ReflectionToStringBuilder#toString()
     */
    @Override
    public String toString() {
        String result = super.toString();
        // 毎toStringごとにremove
        INCLUDE_FIELD_PATTERN.set(null);
        INCLUDE_FIELD_PATTERN.remove();
        return result;
    }
}
