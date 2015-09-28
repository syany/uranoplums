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
 * $Id: UraXMLResourceParseHandler.java$
 */
package org.uranoplums.typical.resource;

import static org.uranoplums.typical.collection.factory.UraListFactory.*;
import static org.uranoplums.typical.collection.factory.UraMapFactory.*;

import java.util.Collection;
import java.util.Deque;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.uranoplums.typical.collection.UraTuple;
import org.uranoplums.typical.collection.factory.UraMapFactory.FACTOR;
import org.uranoplums.typical.exception.UraSystemRuntimeException;
import org.uranoplums.typical.lang.UraObject;
import org.uranoplums.typical.lang.UraObjectCommoner;
import org.uranoplums.typical.lang.builder.UraMultiLineToStringStyle;
import org.uranoplums.typical.lang.builder.UraToStringBuilder;
import org.uranoplums.typical.log.UraLogger;
import org.uranoplums.typical.log.UraLoggerFactory;
import org.uranoplums.typical.util.UraStringUtils;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

/**
 * UraSAXParse2Mapクラス。<br>
 *
 * @since 2015/06/11
 * @author syany
 */
public class UraXMLResourceParseHandler extends DefaultHandler implements UraObjectCommoner {

    /**  */
    protected final UraLogger<String> LOGGER = UraLoggerFactory.getUraStringCodeLog();
    /** 隠れトップ要素 */
    // private static final String ROOT_ELEMENT_REF = "_";
    private static final String ROOT_ELEMENT_DEFAULT = "resources";
    private static final String ATTR_KEY = "key";
    private static final String ATTR_VAL = "value";
    private static final String ATTR_CODE = "typecode";
    // TODO type(クラス指定）出来るように検討する。
    // private static final String ATTR_TYPE = "type";
    private static final String TYPE_MAP = "%";
    private static final String TYPE_LIST = "@";
    private static final String TYPE_STR = "$";
    private static final UraTuple<String, Object> KEY_ELEMENT = new UraTuple<String, Object>(ATTR_KEY, null);
    private static final UraTuple<String, Object> VAL_ELEMENT = new UraTuple<String, Object>(ATTR_VAL, null);
    /**  */
    private static final Map<String, Class<?>> TYPE_CODE = newHashMap(4, FACTOR.NONE);
    static {
        TYPE_CODE.put(TYPE_MAP, java.util.HashMap.class);
        TYPE_CODE.put(TYPE_LIST, java.util.ArrayList.class);
        TYPE_CODE.put(TYPE_STR, java.lang.String.class);
    }
    /** XMLパース結果 */
    private Map<String, Object> parsedMap = newHashMap(20, FACTOR.GREATER);
    /** 現在の要素直下の型連結リスト */
    private Deque<UraTuple<String, Object>> parsedMapRef = newArrayDeque(20);
    /** 現在の要素名 */
    private String elementKey = UraStringUtils.EMPTY;
    /** ルート要素名 */
    private String rootElement = ROOT_ELEMENT_DEFAULT;

    /**
     * デフォルトコンストラクタ。<br>
     */
    public UraXMLResourceParseHandler() {}

    /**
     * 。<br>
     * @param qName
     * @param attrMap
     */
    private void initElementType(final String qName, final Map<String, String> attrMap) {
        final UraTuple<String, Object> parentElement = parsedMapRef.peekFirst();
        final String parentKey = parentElement.getKey();
        Object parentValue = parentElement.getValue();
        if (parentValue instanceof Map) {
            @SuppressWarnings ("unchecked")
            Map<String, Object> param = (Map<String, Object>) parentValue;
            initElement(qName, attrMap, parentKey, param);
        } else if (parentValue instanceof String) {
            String param = (String) parentValue;
            initElement(qName, attrMap, parentKey, param);
        } else if (parentValue instanceof Collection) {
            @SuppressWarnings ("unchecked")
            Collection<Object> param = (Collection<Object>) parentValue;
            initElement(qName, attrMap, parentKey, param);
        } else {
            UraSystemRuntimeException e = new UraSystemRuntimeException(new ClassCastException());
            LOGGER.log("ERROR: {}", e);
            throw e;
        }
    }

    /**
     * 。<br>
     * @param attrMap
     * @return
     */
    protected String addAttrData(final Map<String, String> attrMap) {
        return ObjectUtils.defaultIfNull(attrMap.get(ATTR_VAL), UraStringUtils.EMPTY);
    }

    /**
     * 。<br>
     * @param attrMap
     * @param value
     */
    protected void addAttrData(final Map<String, String> attrMap, final Collection<Object> value) {
        if (attrMap.containsKey(ATTR_VAL)) {
            value.add(attrMap.get(ATTR_VAL));
        }
    }

    /**
     * 。<br>
     * @param attrMap
     * @param value
     */
    protected void addAttrData(final Map<String, String> attrMap, final Map<String, Object> value) {
        if (attrMap.containsKey(ATTR_KEY)) {
            String key = attrMap.get(ATTR_KEY);
            if (UraStringUtils.isNotEmpty(key)) {
                value.put(key, attrMap.get(ATTR_VAL));
            }
        }
    }

    /**
     * Uranoplums向けStringToBuilderオブジェクトを返却します。<br>
     *
     * @param uraToStringBuilder
     * @return UraToStringBuilder
     */
    protected UraToStringBuilder editUraToStringBuilder(
            UraToStringBuilder uraToStringBuilder) {
        return uraToStringBuilder;
    }

    /**
     * 。<br>
     * @param qName
     * @param attrMap
     * @param parentKey
     * @param parentValue
     */
    protected void initElement(final String qName, final Map<String, String> attrMap, final String parentKey, final Collection<Object> parentValue) {
        final String qNameLower = UraStringUtils.lowerCase(qName);
        final String parentKeyLower = UraStringUtils.lowerCase(parentKey);
        if (!parentKeyLower.equals(qNameLower)) {
            if (ATTR_VAL.equals(qNameLower)) {
                UraTuple<String, Object> parentElement = parsedMapRef.peekFirst();
                UraTuple<String, Object> nowElement = new UraTuple<String, Object>(ATTR_VAL, parentElement.getValue());
                parsedMapRef.offerFirst(nowElement);
                return;
            }
            // 型判定
            if (attrMap.containsKey(ATTR_CODE)) {
                String codeType = attrMap.get(ATTR_CODE);
                if (TYPE_MAP.equals(codeType)) {
                    final Map<String, Object> nowValue = newHashMap(8);
                    addAttrData(attrMap, nowValue);
                    UraTuple<String, Object> nowElement = new UraTuple<String, Object>(qName, nowValue);
                    parentValue.add(nowValue);
                    parsedMapRef.offerFirst(nowElement);
                } else if (TYPE_LIST.equals(codeType)) {
                    final List<Object> nowValue = newArrayList(4);
                    addAttrData(attrMap, nowValue);
                    UraTuple<String, Object> nowElement = new UraTuple<String, Object>(qName, nowValue);
                    parentValue.add(nowValue);
                    parsedMapRef.offerFirst(nowElement);
                } else if (TYPE_STR.equals(codeType)) {
                    String nowValue = addAttrData(attrMap);
                    UraTuple<String, Object> nowElement = new UraTuple<String, Object>(qName, nowValue);
                    if (UraStringUtils.isNotEmpty(nowValue)) {
                        parentValue.add(nowValue);
                    }
                    parsedMapRef.offerFirst(nowElement);
                } else {
                    String nowValue = addAttrData(attrMap);
                    UraTuple<String, Object> nowElement = new UraTuple<String, Object>(qName, nowValue);
                    if (UraStringUtils.isNotEmpty(nowValue)) {
                        parentValue.add(nowValue);
                    }
                    parsedMapRef.offerFirst(nowElement);
                }
            } else {
                String nowValue = addAttrData(attrMap);
                UraTuple<String, Object> nowElement = new UraTuple<String, Object>(qName, nowValue);
                if (UraStringUtils.isNotEmpty(nowValue)) {
                    parentValue.add(nowValue);
                }
                parsedMapRef.offerFirst(nowElement);
            }
            return;
        }
        if (ATTR_VAL.equals(qNameLower)) {
            UraTuple<String, Object> nowElement = VAL_ELEMENT;
            parentValue.add(nowElement.getValue());
            parsedMapRef.offerFirst(nowElement);
            return;
        }
        // if (parentValue instanceof Map) {
        // addAttrData(attrMap, (Map<String, Object>) parentValue);
        // } else if (parentValue instanceof Collection) {
        addAttrData(attrMap, parentValue);
        // }
    }

    /**
     * 。<br>
     * @param qName
     * @param attrMap
     * @param parentKey
     * @param parentValue
     */
    protected void initElement(final String qName, final Map<String, String> attrMap, final String parentKey, final Map<String, Object> parentValue) {
        final String qNameLower = UraStringUtils.lowerCase(qName);
        final String parentKeyLower = UraStringUtils.lowerCase(parentKey);
        if (!parentKeyLower.equals(qNameLower)) {
            if (ATTR_KEY.equals(qNameLower)) {
                UraTuple<String, Object> nowElement = KEY_ELEMENT;
                parsedMapRef.offerFirst(nowElement);
                return;
            } else if (ATTR_VAL.equals(qNameLower)) {
                UraTuple<String, Object> parentElement = parsedMapRef.peekFirst();
                UraTuple<String, Object> nowElement = new UraTuple<String, Object>(ATTR_VAL, parentElement.getValue());
                parsedMapRef.offerFirst(nowElement);
                return;
            }
            // 既に存在する場合
            if (parentValue.containsKey(qName)) {
                final Object nowValue = parentValue.get(qName);
                if (nowValue instanceof Map) {
                    @SuppressWarnings ("unchecked")
                    Map<String, Object> nowMap = (Map<String, Object>) nowValue;
                    addAttrData(attrMap, nowMap);
                } else if (nowValue instanceof Collection) {
                    @SuppressWarnings ("unchecked")
                    Collection<Object> nowCollection = (Collection<Object>) nowValue;
                    addAttrData(attrMap, nowCollection);
                }
                UraTuple<String, Object> nowElement = new UraTuple<String, Object>(qName, nowValue);
                parentValue.put(qName, nowValue);
                parsedMapRef.offerFirst(nowElement);
                return;
            }
            // rootは常にMap
            if (this.rootElement.equals(qNameLower)) {
                final Map<String, Object> nowValue = newHashMap(8);
                addAttrData(attrMap, nowValue);
                UraTuple<String, Object> nowElement = new UraTuple<String, Object>(qName, nowValue);
                parentValue.put(qName, nowValue);
                parsedMapRef.offerFirst(nowElement);
                return;
            }
            // 型判定
            if (attrMap.containsKey(ATTR_CODE)) {
                String codeType = attrMap.get(ATTR_CODE);
                if (TYPE_MAP.equals(codeType)) {
                    final Map<String, Object> nowValue = newHashMap(8);
                    addAttrData(attrMap, nowValue);
                    UraTuple<String, Object> nowElement = new UraTuple<String, Object>(qName, nowValue);
                    parentValue.put(qName, nowValue);
                    parsedMapRef.offerFirst(nowElement);
                } else if (TYPE_LIST.equals(codeType)) {
                    final List<Object> nowValue = newArrayList(4);
                    addAttrData(attrMap, nowValue);
                    UraTuple<String, Object> nowElement = new UraTuple<String, Object>(qName, nowValue);
                    parentValue.put(qName, nowValue);
                    parsedMapRef.offerFirst(nowElement);
                } else if (TYPE_STR.equals(codeType)) {
                    String nowValue = addAttrData(attrMap);
                    UraTuple<String, Object> nowElement = new UraTuple<String, Object>(qName, nowValue);
                    parentValue.put(qName, nowValue);
                    parsedMapRef.offerFirst(nowElement);
                } else {
                    String nowValue = addAttrData(attrMap);
                    UraTuple<String, Object> nowElement = new UraTuple<String, Object>(qName, nowValue);
                    if (UraStringUtils.isNotEmpty(nowValue)) {
                        parentValue.put(qName, nowValue);
                    }
                    parsedMapRef.offerFirst(nowElement);
                }
            } else {
                String nowValue = addAttrData(attrMap);
                UraTuple<String, Object> nowElement = new UraTuple<String, Object>(qName, nowValue);
                if (UraStringUtils.isNotEmpty(nowValue)) {
                    parentValue.put(qName, nowValue);
                }
                parsedMapRef.offerFirst(nowElement);
            }
            return;
        }
        if (ATTR_KEY.equals(qNameLower)) {
            UraTuple<String, Object> nowElement = KEY_ELEMENT;
            parentValue.put(qNameLower, nowElement.getValue());
            parsedMapRef.offerFirst(nowElement);
            return;
        } else if (ATTR_VAL.equals(qNameLower)) {
            UraTuple<String, Object> nowElement = VAL_ELEMENT;
            parentValue.put(qNameLower, nowElement.getValue());
            parsedMapRef.offerFirst(nowElement);
            return;
        }
        // if (parentValue != null && parentValue instanceof Map) {
        // Map<String, Object> m = parentValue;
        addAttrData(attrMap, parentValue);
        // } else if (parentValue instanceof Collection) {
        // @SuppressWarnings ("unchecked")
        // Collection<Object> c = (Collection<Object>) parentValue;
        // addAttrData(attrMap, c);
        // }
    }

    /**
     * 。<br>
     * @param qName
     * @param attrMap
     * @param parentKey
     * @param parentValue
     */
    protected void initElement(final String qName, final Map<String, String> attrMap, final String parentKey, final String parentValue) {
        final String qNameLower = UraStringUtils.lowerCase(qName);
        final String parentKeyLower = UraStringUtils.lowerCase(parentKey);
        if (!parentKeyLower.equals(qNameLower)) {
            if (ATTR_VAL.equals(qNameLower)) {
                // Stringの場合は２つ前に戻る。
                this.elementKey = parsedMapRef.pollFirst().getKey();
                UraTuple<String, Object> parentElement = parsedMapRef.peekFirst();
                UraTuple<String, Object> nowElement = new UraTuple<String, Object>(ATTR_VAL, parentElement.getValue());
                parsedMapRef.offerFirst(nowElement);
                return;
            }
        }
        UraSystemRuntimeException e = new UraSystemRuntimeException(new ClassCastException());
        LOGGER.log("DEBUG: {}", e);
        throw e;
    }

    /*
     * (非 Javadoc)
     *
     * @see org.xml.sax.helpers.DefaultHandler#characters(char[], int, int)
     */
    @SuppressWarnings ("unchecked")
    @Override
    public void characters(char[] ch, int start, int length) throws SAXException {
        final UraTuple<String, Object> nowElement = parsedMapRef.peekFirst();
        final String nowKey = nowElement.getKey();
        Object nowValue = nowElement.getValue();
        if (ATTR_KEY.equals(nowKey)) {
            // 入力値の作成
            StringBuilder sb = new StringBuilder();
            length = start + length;
            for (int i = start; i < length; i++) {
                sb.append(ch[i]);
            }
            final String charValue = UraStringUtils.trim(sb.toString());
            if (UraStringUtils.isEmpty(charValue)) {
                return;
            }
            this.elementKey = charValue;
            return;
        } else if (ATTR_VAL.equals(nowKey)) {
            // 入力値の作成
            StringBuilder sb = new StringBuilder();
            length = start + length;
            for (int i = start; i < length; i++) {
                sb.append(ch[i]);
            }
            final String charValue = UraStringUtils.trim(sb.toString());
            if (UraStringUtils.isEmpty(charValue)) {
                return;
            }
            if (nowValue instanceof Map) {
                if (UraStringUtils.isNotEmpty(this.elementKey)) {
                    ((Map<String, Object>) nowValue).put(this.elementKey, charValue);
                    this.elementKey = UraStringUtils.EMPTY;
                }
            } else if (nowValue instanceof List) {
                ((List<String>) nowValue).add(charValue);
            }
            return;
        }
    }

    /**
     * このオブジェクトのクローン(Shallow copy)を作成し、返却します。
     *
     * @return シャローコピーをした自オブジェクト
     * @see java.lang.Object#clone()
     */
    @Override
    public UraObject clone() {
        try {
            return (UraObject) super.clone();
        } catch (CloneNotSupportedException e) {
            // * 必ず入らない例外
            throw new AssertionError();
        }
    }

    /*
     * (非 Javadoc)
     *
     * @see org.xml.sax.helpers.DefaultHandler#endDocument()
     */
    @Override
    public void endDocument() throws SAXException {
        parsedMapRef.clear();
        parsedMapRef = null;
    }

    /*
     * (非 Javadoc)
     *
     * @see org.xml.sax.helpers.DefaultHandler#endElement(java.lang.String, java.lang.String, java.lang.String)
     */
    @Override
    public void endElement(String uri, String localName, String qName) throws SAXException {
        if (UraStringUtils.equals(qName, parsedMapRef.peekFirst().getKey())) {
            this.parsedMapRef.pollFirst();
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
     * @return parsedMap を返却します。
     */
    public final Map<String, Object> getParsedMap() {
        return parsedMap;
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
     * @param rootElement rootElementを設定します。
     */
    public final void setRootElement(String rootElement) {
        this.rootElement = rootElement;
    }

    /*
     * (非 Javadoc)
     *
     * @see org.xml.sax.helpers.DefaultHandler#startDocument()
     */
    @Override
    public void startDocument() throws SAXException {
        // 初期化
        final Map<String, Object> tmpParsedMap = newHashMap(20, FACTOR.GREATER);
        final Deque<UraTuple<String, Object>> tmpParsedMapRef = newArrayDeque(20);
        parsedMap = tmpParsedMap;
        parsedMapRef = tmpParsedMapRef;
        UraTuple<String, Object> root = new UraTuple<String, Object>(rootElement, parsedMap);
        parsedMapRef.offerFirst(root);
    }

    /*
     * (非 Javadoc)
     *
     * @see org.xml.sax.helpers.DefaultHandler#startElement(java.lang.String, java.lang.String, java.lang.String,
     * org.xml.sax.Attributes)
     */
    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
        final Map<String, String> attrMap = newHashMap(8, FACTOR.GREATER);
        int attrLength = attributes.getLength();
        for (int i = 0; i < attrLength; i++) {
            attrMap.put(UraStringUtils.lowerCase(attributes.getQName(i)), attributes.getValue(i));
        }
        this.initElementType(qName, attrMap);
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
     * 本オブジェクト内容を読みやすい形で文字列で返却します。<br>
     * 引数の変数名リストにあるパターンのみ出力します。
     *
     * @param strings 出力対象変数名パターン
     * @return 本オブジェクトフィールド内容の文字列（改行付）
     */
    public String toMultiStringFilter(String... strings) {
        // デフォルトスタイルを一時保管し、マルチスタイルに変更する
        ToStringStyle toStringStyle = ToStringBuilder.getDefaultStyle();
        // ToStringStyle.MULTI_LINE_STYLE
        ToStringBuilder.setDefaultStyle(UraMultiLineToStringStyle.INSTANCE);
        // toStringの実行
        String result = toStringFilter(strings);
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

    /**
     * 本オブジェクト内容を文字列で返却します。<br>
     * 引数の変数名リストにあるパターンのみ出力します。
     *
     * @param strings 出力対象変数名パターン
     * @return 本オブジェクトフィールド内容の文字列
     */
    public String toStringFilter(String... strings) {
        UraToStringBuilder uraToStringBuilder = editUraToStringBuilder(new UraToStringBuilder(
                this));
        for (final String keyword : strings) {
            uraToStringBuilder.setIncludeFieldNamesPerttern(keyword);
        }
        return uraToStringBuilder.toString();
    }
}
