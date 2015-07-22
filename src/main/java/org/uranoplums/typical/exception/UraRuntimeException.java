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
 * $Id: UraRuntimeException.java$
 */
package org.uranoplums.typical.exception;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.apache.commons.lang3.exception.ContextedRuntimeException;
import org.uranoplums.typical.lang.UraObjectCommoner;
import org.uranoplums.typical.lang.builder.UraMultiLineToStringStyle;
import org.uranoplums.typical.lang.builder.UraToStringBuilder;

/**
 * @since 2014/02/12
 * @author syany
 */
public abstract class UraRuntimeException extends ContextedRuntimeException implements
        UraObjectCommoner, UraExcepter {

    /** シリアル・バージョンUID */
    private static final long serialVersionUID = -3029415616622828L;

    /**
     * コンストラクタ
     */
    public UraRuntimeException() {
        super();
    }

    /**
     * コンストラクタ
     * 
     * @param message
     */
    public UraRuntimeException(String message) {
        super(message);
    }

    /**
     * コンストラクタ
     * 
     * @param message
     * @param cause
     */
    public UraRuntimeException(String message, Throwable cause) {
        super(message, cause);
    }

    /**
     * コンストラクタ
     * 
     * @param cause
     */
    public UraRuntimeException(Throwable cause) {
        super(cause);
    }

    /**
     * 
     * @param uraToStringBuilder
     * @return
     */
    protected UraToStringBuilder editUraToStringBuilder(
            UraToStringBuilder uraToStringBuilder) {
        return uraToStringBuilder;
    }

    /**
     * このオブジェクトのクローンを作成し、返却します。
     * 
     * @return 防御的コピーをした自オブジェクト
     * @see java.lang.Object#clone()
     */
    @Override
    public UraRuntimeException clone() {
        try {
            return (UraRuntimeException) super.clone();
        } catch (CloneNotSupportedException e) {
            // WARN 必ず入らない例外
            throw new AssertionError();
        }
    }

    /**
     * 本オブジェクトと引数オブジェクトが等しいか判定します。<br>
     * 比較には#hashCodeを利用します。
     * 
     * @return (等価：true/不等価：false)
     * @see java.lang.Object#equals(java.lang.Object)
     */
    @Override
    public boolean equals(Object paramObject) {
        return EqualsBuilder.reflectionEquals(this, paramObject);
    }

    /**
     * 本オブジェクトのjハッシュ値を返却します。
     * 
     * @return ハッシュコード(Jakarta Common Lang HashCodeBuilderを使用)
     * @see java.lang.Object#hashCode()
     */
    @Override
    public int hashCode() {
        return HashCodeBuilder.reflectionHashCode(this);
    }

    /**
     * 本オブジェクト内容を読みやすい形で文字列で返却します。<br>
     * 複数フィールドを内包している場合、再帰的に対象オブジェクトのtoStringを呼び出します。
     * 
     * @return 本オブジェクトフィールド内容の文字列（改行付）
     */
    public String toMultiString() {
        // デフォルトスタイルを一時保管し、マルチスタイルに変更する
        ToStringStyle toStringStyle = ToStringBuilder.getDefaultStyle();
        ToStringBuilder.setDefaultStyle(UraMultiLineToStringStyle.INSTANCE);
        // toStringの実行
        String result = toString();
        // 元に戻す。
        ToStringBuilder.setDefaultStyle(toStringStyle);
        return result;
    }

    /**
     * 本オブジェクト内容を文字列で返却します。<br>
     * 複数フィールドを内包している場合、再帰的に対象オブジェクトのtoStringを呼び出します。
     * 
     * @return 本オブジェクトフィールド内容の文字列
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        return editUraToStringBuilder(new UraToStringBuilder(this)).toString();
    }
}
