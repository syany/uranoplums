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
 * $Id: AbsUraResource.java$
 */
package org.uranoplums.typical.resource;

import static org.uranoplums.typical.collection.factory.UraMapFactory.*;

import java.io.IOException;
import java.nio.charset.Charset;
import java.util.Locale;
import java.util.concurrent.ConcurrentMap;

import org.slf4j.Logger;
import org.uranoplums.typical.lang.UraSerialDataObject;
import org.uranoplums.typical.log.UraLoggerFactory;
import org.uranoplums.typical.util.UraClassUtils;
import org.uranoplums.typical.util.UraStringUtils;
import org.uranoplums.typical.util.i18n.UraCharset;

/**
 * リソース操作抽象クラス。<br>
 * @since 2015/02/21
 * @author syany
 * @param <E>
 */
public abstract class AbsUraResource<E> extends UraSerialDataObject {

    /** シリアル・バージョンUID */
    private static final long serialVersionUID = -4934114159956513672L;
    /**  */
    private final boolean escape = true;
    /**  */
    protected String config = null;
    /**  */
    protected Charset defaultCharset = UraCharset.UTF8;
    /**  */
    protected Locale defaultLocale = Locale.getDefault();
    /** 表示 */
    protected final ConcurrentMap<String, Object> formats = newConcurrentHashMap();
    /**  */
    protected final ConcurrentMap<String, String> locales = newConcurrentHashMap();
    /**  */
    protected final Logger logger = UraLoggerFactory.getUraLog(this.getClass());
    /**  */
    protected final ConcurrentMap<String, E> resources = newConcurrentHashMap();
    /**
     * ロケールのキーが見つからない場合<code>null</code>を返却します。falseであれば相当の文字列を返却します。
     */
    protected boolean returnNull = false;
    /**  */
    protected String suffix = "";

    /**
     * @param config
     */
    public AbsUraResource(String config) {
        this.config = config;
    }

    /**
     * @param locale
     * @param key
     * @return
     */
    protected E _getResourceObject(Locale locale, String key) {
        logger.debug("> getMessage({},{})", locale, key);
        String localeKey = localeKey(locale);
        String originalKey = valueKey(localeKey, key);
        String valueKey = null;
        E value = null;
        int underscore = 0;
        boolean addIt = false; // Add if not found under the original key
        while (true) {
            initLoadLocale(localeKey);
            // Check if we have this key for the current locale key
            valueKey = valueKey(localeKey, key);
            // synchronized (resources) {
            value = resources.get(valueKey);
            if (value != null) {
                if (addIt) {
                    resources.put(originalKey, value);
                }
                logger.debug("< return [{}]", value);
                return value;
            }
            // }
            // Strip trailing modifiers to try a more general locale key
            addIt = true;
            underscore = localeKey.lastIndexOf("_");
            if (underscore < 0) {
                break;
            }
            localeKey = localeKey.substring(0, underscore);
        }
        // Try the default locale if the current locale is different
        if (!defaultLocale.equals(locale)) {
            value = getValueForLocale(key, localeKey(defaultLocale),
                    originalKey);
        }
        if (value == null) {
            value = getValueForLocale(key, UraStringUtils.EMPTY, originalKey);
        }
        logger.debug("< return [{}]", value);
        return value;
    }

    /**
     * @param locale
     * @param key
     * @param args
     * @return keyから値を返却します。
     */
    protected E _getResourceObject(Locale locale, String key, Object args[]) {
        logger.trace("> getResourceValue({}, {})", locale, key);
        if (locale == null) {
            locale = defaultLocale;
        }
        E value = _getResourceObject(locale, key);
        logger.trace("< return [{}]", value);
        return value;
    }

    protected E escape(E value) {
        if (!isEscape()) {
            return value;
        }
        return escapeValue(value);
    }

    abstract protected E escapeValue(E value);

    protected E getValueForLocale(String key, String localeKey,
            String originalKey) {
        logger.trace("> getValueForLocale({}, {}, {})", key, localeKey,
                originalKey);
        E value = null;
        String valueKey = valueKey(localeKey, key);
        initLoadLocale(localeKey);
        // synchronized (resources) {
        value = resources.get(valueKey);
        if (value != null) {
            resources.put(originalKey, value);
        }
        // }
        logger.trace("< return [{}]", value);
        return value;
    }

    protected synchronized void initLoadLocale(String localeKey) {
        logger.trace("> loadLocale({})", localeKey);
        // Have we already attempted to load messages for this locale?
        if (locales.putIfAbsent(localeKey, localeKey) != null) {
            return;
        }
        // locales.put(localeKey, localeKey);
        // Set up to load the property resource for this locale key, if we can
        String name = config.replace('.', '/');
        if (localeKey.length() > 0) {
            name += "_" + localeKey;
        }
        name += suffix;
        // Load the specified property resource
        logger.trace("  Loading resource '{}'", name);
        ClassLoader classLoader = UraClassUtils.getCurrentClassLoader(this.getClass());
        try {
            initLoadResource(classLoader, name, localeKey);
        } catch (IOException e) {
            logger.error("[E] Loading resource error. [{}] {}", name
                    , e.toString());
        }
        logger.trace("< return");
    }

    abstract protected void initLoadResource(ClassLoader classLoader,
            String name, String localeKey) throws IOException;

    protected String localeKey(Locale locale) {
        return (locale == null) ? "" : locale.toString();
    }

    protected String valueKey(Locale locale, String key) {
        return (localeKey(locale) + "." + key);
    }

    protected String valueKey(String localeKey, String key) {
        return (localeKey + "." + key);
    }

    /**
     * @return escape
     */
    boolean isEscape() {
        return escape;
    }

    /**
     * 存在確認します。
     *
     * @param locale
     * @param key
     * @return 存在する場合はtrue
     */
    public boolean isExists(Locale locale, String key) {
        return (valueKey(locale, key) != null);
    }

    /**
     * @param defaultCharset
     *            セットする defaultCharset
     * @return 自クラス返却
     */
    public AbsUraResource<E> setDefaultCharset(Charset defaultCharset) {
        this.defaultCharset = defaultCharset;
        return this;
    }

    /**
     * リソースファイルに対するデフォルトロケールを設定します。
     * @param defaultLocale
     *            セットする defaultLocale
     * @return 自クラス返却
     */
    public AbsUraResource<E> setDefaultLocale(Locale defaultLocale) {
        this.defaultLocale = defaultLocale;
        return this;
    }

    /**
     * @param returnNull returnNullを設定します。
     */
    public void setReturnNull(boolean returnNull) {
        this.returnNull = returnNull;
    }
}
