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
 * $Id: UraMultiLineToStringStyle$
 */
package org.uranoplums.typical.lang.builder;

import java.io.ObjectStreamException;

import org.apache.commons.lang3.SystemUtils;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.uranoplums.typical.lang.threadlocal.UraThreadLocalAtomicInteger;
import org.uranoplums.typical.util.UraStringUtils;

/**
 * ToStrungを見やすく可視化するスタイルクラス。<br>
 * @since 2015/02/22
 * @author syany
 */
public final class UraMultiLineToStringStyle extends ToStringStyle {

    /** コンテンツ終了ブランケット */
    private static final String CONTENT_END = "]";
    /** コンテンツ開始ブランケット */
    private static final String CONTENT_START = "[";
    /** インデント文字列 */
    private static final String INDENT = UraStringUtils.W_SPACE
            + UraStringUtils.W_SPACE;
    /** スレッドセーフな AtomicInteger */
    private static final UraThreadLocalAtomicInteger LEVEL = new UraThreadLocalAtomicInteger();
    /** シリアル・バージョンID */
    private static final long serialVersionUID = 7269733207024802712L;
    /** 唯一のインスタンス */
    public static final UraMultiLineToStringStyle INSTANCE = new UraMultiLineToStringStyle();

    /**
     * デフォルトコンストラクタ
     */
    private UraMultiLineToStringStyle() {
        super();
        this.setContentStart(CONTENT_START);
        this.setFieldSeparator(SystemUtils.LINE_SEPARATOR + INDENT);
        this.setFieldSeparatorAtStart(true);
        this.setContentEnd(SystemUtils.LINE_SEPARATOR + CONTENT_END);
    }

    /**
     * レジストリ削除
     */
    private static void levelUnregister() {
        LEVEL.set(null);
        LEVEL.remove();
    }

    /**
     * デシリアライズ時使用
     * @return 唯一のINSTANCE
     * @throws ObjectStreamException
     */
    private Object readResolve() throws ObjectStreamException {
        return INSTANCE;
    }

    /*
     * (非 Javadoc)
     * 
     * @see
     * org.apache.commons.lang.builder.ToStringStyle#appendContentEnd(java.lang
     * .StringBuffer)
     */
    @Override
    protected void appendContentEnd(StringBuffer buffer) {
        buffer.append(SystemUtils.LINE_SEPARATOR);
        int lv = LEVEL.getInt();
        for (int i = 0; i < lv; i++) {
            buffer.append(INDENT);
        }
        buffer.append(CONTENT_END);
    }

    /*
     * (非 Javadoc)
     * 
     * @see
     * org.apache.commons.lang.builder.ToStringStyle#appendFieldEnd(java.lang
     * .StringBuffer, java.lang.String)
     */
    @Override
    protected void appendFieldEnd(StringBuffer buffer, String fieldName) {
        super.appendFieldEnd(buffer, fieldName);
        LEVEL.decrementAndGet();
    }

    /*
     * (非 Javadoc)
     * 
     * @see
     * org.apache.commons.lang.builder.ToStringStyle#appendFieldStart(java.lang
     * .StringBuffer, java.lang.String)
     */
    @Override
    protected void appendFieldStart(StringBuffer buffer, String fieldName) {
        int lv = LEVEL.getAndIncrement();
        for (int i = 0; i < lv; i++) {
            buffer.append(INDENT);
        }
        super.appendFieldStart(buffer, fieldName);
    }

    /*
     * (非 Javadoc)
     * 
     * @see java.lang.Object#clone()
     */
    @Override
    protected final Object clone() throws CloneNotSupportedException {
        // cloneを防ぐ
        throw new CloneNotSupportedException();
    }

    /*
     * (非 Javadoc)
     * 
     * @see org.apache.commons.lang.builder.ToStringStyle#appendEnd(java.lang.
     * StringBuffer, java.lang.Object)
     */
    @Override
    public void appendEnd(StringBuffer buffer, Object object) {
        super.appendEnd(buffer, object);
        levelUnregister();
    }
}
