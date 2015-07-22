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
package org.uranoplums.typical.resource;

import java.text.MessageFormat;
import java.util.Locale;

/**
 * 文字列リソース操作抽象クラス。<br>
 * 
 * @since 2015/02/25
 * @author syany
 */
abstract public class AbsUraStringResource extends AbsUraResource<String> {

    /**  */
    private static final long serialVersionUID = -1094150736410571776L;

    /**
     * コンストラクタ。<br>
     * @param config
     */
    public AbsUraStringResource(String config) {
        super(config);
    }

    /*
     * (非 Javadoc)
     * 
     * @see org.uranoplums.typical.resource.AbsUraResource#getResourceValue(java.util.Locale, java.lang.String,
     * java.lang.Object[])
     */
    @Override
    protected String _getResourceObject(Locale locale, String key, Object[] args) {
        logger.debug("> getResourceValue({}, {}, {})", locale, key, args);
        String value = super._getResourceObject(locale, key, args);
        value = getSubResourceValue(locale, value);
        MessageFormat format = null;
        String formatKey = valueKey(locale, key);
        synchronized (formats) {
            format = (MessageFormat) formats.get(formatKey);
            if (format == null) {
                if (value != null) {
                    format = new MessageFormat(escape(value));
                    format.setLocale(locale);
                    formats.put(formatKey, format);
                    value = format.format(args);
                }
            } else {
                value = format.format(args);
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
    protected String escapeValue(String value) {
        logger.debug("> escapeValue({})", value);
        // エスケープ処理対象外ならばそのまま戻る
        if (!isEscape()) {
            logger.debug("< return [{}]", value);
            return value;
        }
        // 引数がnullもしくは’が１つもなければそのまま戻る
        if ((value == null) || (value.indexOf('\'') < 0)) {
            logger.debug("< return [{}]", value);
            return value;
        }
        // １文字ずつ確認。’を￥’に変更
        int n = value.length();
        StringBuffer sb = new StringBuffer(n);
        for (int i = 0; i < n; i++) {
            char ch = value.charAt(i);
            if (ch == '\'') {
                sb.append('\'');
            }
            sb.append(ch);
        }
        logger.debug("< return [{}]", value);
        return sb.toString();
    }

    /**
     * 別のキーを割り当てる<br>
     * 別のキーは${キー名}で割り当てることができる。<br>
     * 取得時は元のロケールを引き継ぐ。
     * @param locale
     * @param source
     * @return
     */
    protected String getSubResourceValue(Locale locale, String source) {
        logger.debug("> getResourceValue({}, {})", locale, source);
        logger.debug("< return [{}]", source);
        return source;
    }

    /**
     * 。<br>
     * @param locale
     * @param key
     * @return
     */
    public String getResourceValue(Locale locale, String key) {
        return this._getResourceObject(locale, key, new Object[0]);
    }

    /**
     * 。<br>
     * @param locale
     * @param key
     * @param args
     * @return
     */
    public String getResourceValue(Locale locale, String key, Object[] args) {
        return _getResourceObject(locale, key, args);
    }

    /**
     * 。<br>
     * @param locale
     * @param key
     * @param args
     * @return
     */
    public String getResourceValue(Locale locale, String key, String... args) {
        return super._getResourceObject(locale, key, args);
    }

    /**
     * 。<br>
     * @param key
     * @return
     */
    public String getResourceValue(String key) {
        return this._getResourceObject(this.defaultLocale, key, new Object[0]);
    }

    /**
     * 。<br>
     * @param key
     * @param args
     * @return
     */
    public String getResourceValue(String key, String... args) {
        return this.getResourceValue(this.defaultLocale, key, args);
    }
}
