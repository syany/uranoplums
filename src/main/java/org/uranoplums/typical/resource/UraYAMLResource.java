/*
 * Copyright 2013-2018 the Uranoplums Foundation and the Others.
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
 * $Id: UraYAMLResource.java$
 */
package org.uranoplums.typical.resource;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Map;

import org.uranoplums.typical.util.UraStringUtils;
import org.yaml.snakeyaml.Yaml;

import com.google.gson.JsonIOException;
import com.google.gson.JsonSyntaxException;

/**
 * UraYAMLResourceクラス。<br>
 *
 * @since 2018/03/02
 * @author syany
 */
public class UraYAMLResource extends AbsUraHierarchyResource {

    /**
     * デフォルトコンストラクタ。<br>
     * @param config
     */
    public UraYAMLResource(String config) {
        super(config);
        this.suffix = ".yaml";
    }

    /* (非 Javadoc)
     * @see org.uranoplums.typical.resource.AbsUraResource#initLoadResource(java.lang.ClassLoader, java.lang.String, java.lang.String)
     */
    @Override
    protected void initLoadResource(ClassLoader classLoader, String name, String localeKey) throws IOException {

        // ストリーム初期設定
        InputStreamReader isr = null;
        InputStream is = null;
        // クラスパスのトップを探す
        int lastIndex = UraStringUtils.lastIndexOf(name, UraStringUtils.SLASH);
        if (lastIndex >= 0) {
            is = classLoader.getResourceAsStream(UraStringUtils.substring(name, lastIndex + 1));
        }
        if (is == null) {
            // 存在しないor最初からディレクトリトップの場合、そのまま取得
            is = classLoader.getResourceAsStream(name);
            if (is == null) {
                return;
            }
        }
        try {
            isr = new InputStreamReader(is, this.defaultCharset);
            // JSON解析
            // Mapタイプで取得
            Yaml yaml = new Yaml();
//            Type HASH_MAP_TYPE = new HashMap<String, Object>() {
//            }.getClass().getGenericSuperclass();
            @SuppressWarnings("unchecked")
            Map<String, Object> jsonMap = yaml.loadAs(isr, Map.class);
//            Gson gson = new Gson();
//            Type mapType = new TypeToken<Map<String, Object>>() {}.getType();
//            Map<String, Object> jsonMap = gson.fromJson(isr, mapType);
            if (jsonMap.size() < 1) {
                return;
            }
            // immutable
            //jsonMap = UraCollectionUtils.deepUnmodifiableMap(jsonMap);
            // ロケール別にリソースマップへput
            for (final Map.Entry<String, Object> entry : jsonMap.entrySet()) {
                String key = entry.getKey();
                logger.trace("  Saving message key '"
                        + valueKey(localeKey, key));
                resources.put(valueKey(localeKey, key), entry.getValue());
            }
        } catch (JsonSyntaxException e) {
            throw new IOException(e);
        } catch (JsonIOException e) {
            throw new IOException(e);
        } finally {
            try {
                if (isr != null) {
                    isr.close();
                }
            } finally {
                if (is != null) {
                    is.close();
                }
            }
        }
    }

}
