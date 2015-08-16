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

import java.io.IOException;
import java.io.InputStream;
import java.util.Locale;
import java.util.Properties;

import org.uranoplums.typical.util.UraStringUtils;

/**
 * UraPropertyResourceクラス。<br>
 *
 * @since 2015/02/25
 * @author syany
 */
public class UraPropertyResource extends AbsUraStringResource {

    /**  */
    private static final long serialVersionUID = 6576578909615600978L;

    /**
     * @param config
     */
    public UraPropertyResource(String config) {
        super(config);
        this.suffix = ".properties";
    }

    /**
     * プロパティファイル内の別のキーを割り当てる<br>
     * 別のキーは${キー名}で割り当てることができる。<br>
     * 取得時は元のロケールを引き継ぐ。
     * @param locale
     * @param source
     * @return
     */
    @Override
    protected String getSubResourceValue(Locale locale, String source) {
        logger.debug("> getResourceValue({}, {})", locale, source);
        if (source == null) {
            return UraStringUtils.EMPTY;
            // return null;
        }
        StringBuffer result = new StringBuffer();
        int lastOffset = 0;
        while (true) {
            int findOffset = source.indexOf("${", lastOffset);
            if (findOffset < 0) {
                result.append(source.substring(lastOffset));
                break;
            }
            // ${ がある場合
            int findOffsetLast = findOffset + 2;
            int findLastOffset = source.indexOf("}", findOffsetLast);
            int findStartOffset = source.indexOf("${", findOffsetLast);
            if (findLastOffset < 0) {
                result.append(source.substring(lastOffset));
                break;
            } else if (findStartOffset >= 0 && findStartOffset < findLastOffset) {
                // } があるが、${が先にある場合、とばす。
                result.append(source.substring(lastOffset, findOffsetLast));
                lastOffset = findOffset + 2;
                continue;
            }
            // がある}
            result.append(source.substring(lastOffset, findOffset));
            String key = source.substring(findOffsetLast, findLastOffset);
            String value = _getResourceObject(locale, key, new Object[0]);
            if (value != null) {
                result.append(value);
            } else {
                // ${xxx}をそのまま入れる。
                result.append(source.substring(findOffset, findLastOffset + 1));
            }
            lastOffset = findLastOffset + 1;
        }
        logger.debug("< return [{}]", result.toString());
        return result.toString();
    }

    /*
     * (非 Javadoc)
     *
     * @see
     * org.uranoplums.typical.resource.AbsUraResource#initLoadResource(java.
     * lang.ClassLoader, java.lang.String, java.lang.String)
     */
    @Override
    protected void initLoadResource(ClassLoader classLoader, String name,
            String localeKey) throws IOException {
        InputStream is = null;
        Properties props = new Properties();
        // クラスパスのトップを探す
        int lastIndex = UraStringUtils.lastIndexOf(name, UraStringUtils.SLASH);
        if (lastIndex >= 0) {
            is = classLoader.getResourceAsStream(UraStringUtils.substring(name, lastIndex + 1));
        }
        if (is == null) {
            // 存在しないor最初からディレクトリトップの場合、そのまま取得
            is = classLoader.getResourceAsStream(name);
        }
        // is = classLoader.getResourceAsStream(name);
        if (is != null) {
            try {
                props.load(is);
            } catch (IOException e) {
                logger.error("loadLocale()", e);
            } finally {
                try {
                    is.close();
                } catch (IOException e) {
                    logger.error("loadLocale()", e);
                }
            }
        }
        logger.trace("  Loading resource completed");
        // Copy the corresponding values into our cache
        if (props.size() < 1) {
            return;
        }
        // synchronized (resources) {
        for (String key : props.stringPropertyNames()) {
            logger.trace("  Saving message key '" + valueKey(localeKey, key));
            resources.put(valueKey(localeKey, key), props.getProperty(key));
        }
        // }
    }
}
