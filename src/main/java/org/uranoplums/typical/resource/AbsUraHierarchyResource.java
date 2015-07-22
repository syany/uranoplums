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
 * $Id: AbsUraHierarchyResource.java$
 */
package org.uranoplums.typical.resource;

import java.text.MessageFormat;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import org.uranoplums.typical.util.UraClassUtils;
import org.uranoplums.typical.util.UraObjectUtils;
import org.uranoplums.typical.util.UraStringUtils;

/*
 * 階層付きリソース抽象クラス。<br>
 * 
 * @since 2015/02/21
 * 
 * @author syany
 */
public abstract class AbsUraHierarchyResource extends AbsUraResource<Object> {

    /** シリアルバージョンUID */
    private static final long serialVersionUID = 769481646054058590L;
    private static final Object[] BLANK_ARGS = new Object[0];

    /**
     * コンストラクタ。
     * @param config
     */
    public AbsUraHierarchyResource(String config) {
        super(config);
    }

    /*
     * (非 Javadoc)
     * 
     * @see
     * org.uranoplums.typical.resource.AbsUraResource#getResourceValue(java.
     * util.Locale, java.lang.String, java.lang.Object[])
     */
    @Override
    protected Object _getResourceObject(Locale locale, String key, Object[] args) {
        logger.debug("> getResourceValue({}, {}, {})", locale, key, args);
        Object value = super._getResourceObject(locale, key, args);
        value = getSubResourceValue(locale, value);
        if (value instanceof String) {
            MessageFormat format = null;
            String formatKey = valueKey(locale, key);
            synchronized (formats) {
                format = (MessageFormat) formats.get(formatKey);
                if (format == null) {
                    if (value != null) {
                        format = new MessageFormat((String) escape(value));
                        format.setLocale(locale);
                        formats.put(formatKey, format);
                        value = format.format(args);
                    }
                } else {
                    value = format.format(args);
                }
            }
        } else {
            Object target = null;
            String formatKey = valueKey(locale, key);
            synchronized (formats) {
                target = formats.get(formatKey);
                if (target == null) {
                    if (value != null) {
                        target = escape(value);
                        formats.put(formatKey, target);
                        value = target;
                    }
                } else {
                    value = target;
                }
            }
        }
        logger.debug("< return [{}]", value);
        return value;
    }

    /*
     * (非 Javadoc)
     * 
     * @see
     * org.uranoplums.typical.resource.AbsUraResource#escapeValue(java.lang.
     * Object)
     */
    @Override
    protected Object escapeValue(Object value) {
        logger.trace("> escapeValue({})", value);
        // エスケープ処理対象外ならばそのまま戻る
        if (!isEscape()) {
            logger.trace("< return [{}]", value.toString());
            return value;
        }
        //
        if (value == null) {
            logger.trace("< return [null]");
            return value;
        }
        if (value instanceof String) {
            String source = (String) value;
            // "が１つもなければそのまま戻る
            if (source.indexOf('\"') < 0) {
                logger.debug("< return [{}]", value.toString());
                return value;
            }
            // １文字ずつ確認。"を￥"に変更
            int n = source.length();
            StringBuffer sb = new StringBuffer(n);
            for (int i = 0; i < n; i++) {
                char ch = source.charAt(i);
                if (ch == '\"') {
                    sb.append('\"');
                }
                sb.append(ch);
            }
            logger.trace("< return [{}]", sb.toString());
            return sb.toString();
        }
        return value;
    }

    /**
     * 別のキーを割り当てる<br>
     * 別のキーは$(キー名)で割り当てることができる。<br>
     * 取得時は元のロケールを引き継ぐ。
     * 
     * @param locale
     * @param source
     * @return 取得した値オブジェクト
     */
    protected Object getSubResourceValue(Locale locale, Object source) {
        logger.trace("> getResourceValue({}, {})", locale, source);
        if (source == null) {
            return UraStringUtils.EMPTY;
            // return null;
        }
        if (source instanceof String) {
            String target = (String) source;
            StringBuffer result = new StringBuffer();
            int lastOffset = 0;
            while (true) {
                int findOffset = target.indexOf("$(", lastOffset);
                if (findOffset < 0) {
                    result.append(target.substring(lastOffset));
                    break;
                }
                // ${ がある場合
                int findOffsetLast = findOffset + 2;
                int findLastOffset = target.indexOf(")", findOffsetLast);
                int findStartOffset = target.indexOf("$(", findOffsetLast);
                if (findLastOffset < 0) {
                    result.append(target.substring(lastOffset));
                    break;
                } else if (findStartOffset >= 0
                        && findStartOffset < findLastOffset) {
                    // } があるが、${が先にある場合、とばす。
                    result.append(target.substring(lastOffset, findOffsetLast));
                    lastOffset = findOffset + 2;
                    continue;
                }
                // がある}
                result.append(target.substring(lastOffset, findOffset));
                String key = target.substring(findOffsetLast, findLastOffset);
                String value = (String) _getResourceObject(locale, key,
                        new Object[0]);
                if (value != null) {
                    result.append(value);
                } else {
                    // ${xxx}をそのまま入れる。
                    result.append(target.substring(findOffset,
                            findLastOffset + 1));
                }
                lastOffset = findLastOffset + 1;
            }
            logger.trace("< return [{}]", result.toString());
            return result.toString();
        }
        logger.trace("< return [{}]", source);
        return source;
    }

    /**
     * 。<br>
     * @param locale
     * @param key
     * @param arrayClass
     * @return
     */
    public <T> T[] getResourceArray(Locale locale, String key, T... arrayClass) {
        return getResourceArray(arrayClass, locale, key, BLANK_ARGS);
    }

    /**
     * 。<br>
     * @param key
     * @param arrayClass
     * @return
     */
    public <T> T[] getResourceArray(String key, T... arrayClass) {
        return getResourceArray(arrayClass, this.defaultLocale, key, BLANK_ARGS);
    }

    /**
     * 。<br>
     * @param arrayClass
     * @param locale
     * @param key
     * @param args
     * @return
     */
    public <T> T[] getResourceArray(T[] arrayClass, Locale locale, String key, Object[] args) {
        Object o = _getResourceObject(locale, key, args);
        return UraObjectUtils.castToArray(o, arrayClass);
    }

    /**
     * 。<br>
     * @param arrayClass
     * @param locale
     * @param key
     * @return
     */
    public <T> T[] getResourceArray(T[] arrayClass, Locale locale, String key, String... args) {
        return getResourceArray(arrayClass, locale, key, (Object[]) args);
    }

    /**
     * 。<br>
     * @param arrayClass
     * @param locale
     * @param key
     * @return
     */
    public <T> T[] getResourceArray(T[] arrayClass, String key, String... args) {
        return getResourceArray(arrayClass, this.defaultLocale, key, (Object[]) args);
    }

    /**
     * 。<br>
     * @param locale
     * @param key
     * @param args
     * @return
     */
    @SuppressWarnings ("unchecked")
    public <T> List<T> getResourceList(Locale locale, String key, Object[] args) {
        return getResourceValue(List.class, locale, key, args);
    }

    /**
     * 。<br>
     * @param locale
     * @param key
     * @param args
     * @return
     */
    @SuppressWarnings ("unchecked")
    public <T> List<T> getResourceList(Locale locale, String key, String... args) {
        return getResourceValue(List.class, locale, key, (Object[]) args);
    }

    /**
     * 。<br>
     * @param locale
     * @param key
     * @param args
     * @return
     */
    @SuppressWarnings ("unchecked")
    public <T> List<T> getResourceList(String key, String... args) {
        return getResourceValue(List.class, this.defaultLocale, key, args);
    }

    /**
     * 。<br>
     * @param locale
     * @param key
     * @param args
     * @return
     */
    @SuppressWarnings ("unchecked")
    public <K, V> Map<K, V> getResourceMap(Locale locale, String key, Object[] args) {
        return getResourceValue(Map.class, locale, key, args);
    }

    /**
     * 。<br>
     * @param locale
     * @param key
     * @param args
     * @return
     */
    @SuppressWarnings ("unchecked")
    public <K, V> Map<K, V> getResourceMap(Locale locale, String key, String... args) {
        return getResourceValue(Map.class, locale, key, (Object[]) args);
    }

    /**
     * 。<br>
     * @param locale
     * @param key
     * @param args
     * @return
     */
    @SuppressWarnings ("unchecked")
    public <K, V> Map<K, V> getResourceMap(String key, String... args) {
        return getResourceValue(Map.class, this.defaultLocale, key, (Object[]) args);
    }

    /**
     * 対象キーを指定ロケールで取得する。<br>
     * @param locale
     * @param key
     * @return 対象キーの値
     */
    public Object getResourceObject(Locale locale, String key) {
        return this._getResourceObject(locale, key, BLANK_ARGS);
    }

    /**
     * 。<br>
     * @param locale
     * @param key
     * @param args
     * @return
     */
    public Object getResourceObject(Locale locale, String key, Object[] args) {
        return _getResourceObject(locale, key, args);
    }

    /**
     * 対象キーを指定ロケールで取得する。<br>
     * @param locale
     * @param key
     * @param args
     * @return 対象キーの値
     */
    public Object getResourceObject(Locale locale, String key, String... args) {
        return super._getResourceObject(locale, key, args);
    }

    /**
     * 対象キーをデフォルトロケールで取得する。<br>
     * @param key
     * @return 対象キーの値
     */
    public Object getResourceObject(String key) {
        return this._getResourceObject(this.defaultLocale, key, BLANK_ARGS);
    }

    /**
     * 対象キーをデフォルトロケールで取得する。。<br>
     * @param key
     * @param args
     * @return 対象キーの値
     */
    public Object getResourceObject(String key, String... args) {
        return this._getResourceObject(this.defaultLocale, key, args);
    }

    /**
     * 。<br>
     * @param locale
     * @param key
     * @param args
     * @return
     */
    public Object[] getResourceObjectArray(Locale locale, String key, Object[] args) {
        return getResourceArray(new Object[0], locale, key, args);
    }

    /**
     * 。<br>
     * @param locale
     * @param key
     * @return
     */
    public Object[] getResourceObjectArray(Locale locale, String key, String... args) {
        return getResourceArray(new Object[0], locale, key, (Object[]) args);
    }

    /**
     * 。<br>
     * @param locale
     * @param key
     * @return
     */
    public Object[] getResourceObjectArray(String key, String... args) {
        return getResourceArray(new Object[0], this.defaultLocale, key, new Object[0]);
    }

    /**
     * 。<br>
     * @param locale
     * @param key
     * @param args
     * @return
     */
    public String getResourceString(Locale locale, String key, Object[] args) {
        return getResourceValue(String.class, locale, key, args);
    }

    /**
     * 。<br>
     * @param locale
     * @param key
     * @param args
     * @return
     */
    public String getResourceString(Locale locale, String key, String... args) {
        return getResourceValue(String.class, locale, key, (Object[]) args);
    }

    /**
     * 。<br>
     * @param locale
     * @param key
     * @param args
     * @return
     */
    public String getResourceString(String key, String... args) {
        return getResourceValue(String.class, this.defaultLocale, key, args);
    }

    /**
     * 。<br>
     * @param locale
     * @param key
     * @param args
     * @return
     */
    public String[] getResourceStringArray(Locale locale, String key, Object[] args) {
        return getResourceArray(new String[0], locale, key, args);
    }

    /**
     * 。<br>
     * @param locale
     * @param key
     * @return
     */
    public String[] getResourceStringArray(Locale locale, String key, String... args) {
        return getResourceArray(new String[0], locale, key, (Object[]) args);
    }

    /**
     * 。<br>
     * @param locale
     * @param key
     * @return
     */
    public String[] getResourceStringArray(String key, String... args) {
        return getResourceArray(new String[0], this.defaultLocale, key, new String[0]);
    }

    /**
     * 。<br>
     * @param klass
     * @param locale
     * @param key
     * @param args
     * @return
     */
    public <T> T getResourceValue(Class<T> klass, Locale locale, String key, Object[] args) {
        Object o = _getResourceObject(locale, key, args);
        return UraObjectUtils.cast(o, klass);
    }

    /**
     * 。<br>
     * @param klass
     * @param locale
     * @param key
     * @param args
     * @return
     */
    public <T> T getResourceValue(Class<T> klass, Locale locale, String key, String... args) {
        return getResourceValue(klass, locale, key, (Object[]) args);
    }

    /**
     * 。<br>
     * @param klass
     * @param locale
     * @param key
     * @param args
     * @return
     */
    public <T> T getResourceValue(Class<T> klass, String key, String... args) {
        return getResourceValue(klass, this.defaultLocale, key, (Object[]) args);
    }

    // public <T> T
    /**
     * 。<br>
     * @param locale
     * @param key
     * @param klassArray
     * @return
     */
    public <T> T getResourceValue(Locale locale, String key, T... klassArray) {
        return getResourceValue(UraClassUtils.getArrayClass(klassArray), locale, key, klassArray);
    }

    /**
     * 。<br>
     * @param key
     * @param klassArray
     * @return
     */
    public <T> T getResourceValue(String key, T... klassArray) {
        return getResourceValue(UraClassUtils.getArrayClass(klassArray), this.defaultLocale, key, klassArray);
    }
}
