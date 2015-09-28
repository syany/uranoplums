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
 * $Id: UraLoggingDiagnosticManager.java$
 */
package org.uranoplums.typical.log;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.MDC;
// import org.apache.log4j.MDC;
// import org.apache.log4j.NDC;
import org.uranoplums.typical.lang.UraObject;

/**
 * ロギング診断マネージャクラス。<br>
 * Loggingクラスに内包されるNDC、MDCを管理する。<br>
 * @since 2014/01/27
 * @author syany
 */
public class UraLoggingDiagnosticManager extends UraObject {

    /** 唯一のロギング診断管理クラス */
    private static final UraLoggingDiagnosticManager INSTANCE = new UraLoggingDiagnosticManager();
    /** MDCキーリスト情報 */
    private static final List<String> MDC_KEY_LIST = new ArrayList<String>();
    /**  */
    public static final String IP_ADDRESS = "IP";
    /**  */
    public static final String METHOD = "METHOD";
    /**  */
    public static final String PATH = "PATH";
    /**  */
    public static final String REQUESTED_ID = "RID";
    /**  */
    public static final String SERVER_INFO = "SRV_INFO";
    /**  */
    public static final String SERVER_NAME = "SRV_NAME";
    /**  */
    public static final String SERVLET_PATH = "SRV_PATH";
    /*
     * keys
     */
    /**  */
    public static final String SESSION_ID = "SID";
    /**  */
    public static final String URI = "URI";
    /**  */
    public static final String URL = "URL";
    /**  */
    public static final String USER_ID = "UID";

    /**
     * コンストラクタ。<br>
     */
    private UraLoggingDiagnosticManager() {
        super();
    }

    /**
     * 唯一のインスタンス取得メソッド。<br>
     * @return ロキング診断マネージャインスタンス
     */
    public static final UraLoggingDiagnosticManager getInstance() {
        return INSTANCE;
    }

    // /**
    // * 内容を全消去します{NDC{@link #clear()}の委譲メソッド。<br>
    // */
    // public void clear() {
    // NDC.clear();
    // }

    /**
     * 対象キーが存在するかチェックする。{MDC{@link #contains(String)}の委譲メソッド。<br>
     * @param key
     *            検査対象キー
     * @return 存在すればtrue/しなければfalse
     */
    public boolean contains(String key) {
        return MDC_KEY_LIST.contains(key);
    }

    // /**
    // * {@link NDC#get()}の委譲メソッド。<br>
    // * @return 現在pushされているNDCない情報
    // */
    // public String get() {
    // return NDC.get();
    // }
    /**
     * {MDC{@link #get(String)}の委譲メソッド。<br>
     * @param key
     *            取得したいキー名
     * @return keyに紐づく値
     */
    public Object get(String key) {
        return MDC.get(key);
    }

    //
    // /**
    // * {@link MDC}の内容を{@link Entry}型で返却します。<br>
    // * @return
    // */
    // @SuppressWarnings("unchecked")
    // public Set<Entry<String, Object>> entrySet() {
    // Set<Entry<String, Object>> resultSet = new HashSet<Entry<String, Object>>(3);
    // MDC.
    // for (final Object obj : MDC.getContext().entrySet()) {
    // resultSet.add((Entry<String, Object>) obj);
    // }
    // return resultSet;
    // }
    /**
     * MDC内の情報有無判定。<br>
     * @return なければtrue/あればfalse
     */
    public boolean isEmpty() {
        return MDC_KEY_LIST.isEmpty();
    }

    // /**
    // * {@link NDC#pop()}の委譲メソッド。<br>
    // * @return 直前pusu情報
    // */
    // public String pop() {
    // return NDC.pop();
    // }
    // /**
    // * {@link NDC#push(String)}の委譲メソッド。<br>
    // * @param message
    // * 追加するメッセージ
    // * @return ロギング診断マネージャインスタンス
    // */
    // public UraLoggingDiagnosticManager push(String message) {
    // return push(message, StringUtils.EMPTY);
    // }
    // /**
    // * {@link NDC#push(String)}の委譲メソッド。<br>
    // * @param message
    // * 追加するメッセージ
    // * @param defaultMessage
    // * メッセージが空であった場合のデフォルト値
    // * @return ロギング診断マネージャインスタンス
    // */
    // public UraLoggingDiagnosticManager push(String message, String defaultMessage) {
    // NDC.push(StringUtils.defaultIfEmpty(message, defaultMessage));
    // return this;
    // }
    /**
     * {@link MDC#put(String, Object)}の委譲メソッド。<br>
     * @param key
     *            追加したいキー名称(以後、%X{key}で使用可能)
     * @param value
     *            キー名称に紐づく内容(以後、%X{key}で設定内容が出力される)
     * @return ロギング診断マネージャインスタンス(自オブジェクト)
     */
    public UraLoggingDiagnosticManager put(String key, Object value) {
        return this.put(key, value, StringUtils.EMPTY);
    }

    /**
     * {@link MDC#put(String, Object)}にデフォルトvalue値を追加したメソッド。<br>
     * @param key
     *            追加したいキー名称(以後、%X{key}で使用可能)
     * @param value
     *            キー名称に紐づく内容(以後、%X{key}で設定内容が出力される)
     * @param defaultValue
     *            valueがnullであった場合に設定されるデフォルト値
     * @return ロギング診断マネージャインスタンス(自オブジェクト)
     */
    public UraLoggingDiagnosticManager put(String key, Object value, Object defaultValue) {
        MDC.put(key, (String) ObjectUtils.defaultIfNull(value, defaultValue));
        if (!MDC_KEY_LIST.contains(key)) {
            MDC_KEY_LIST.add(key);
        }
        return this;
    }

    /**
     * 対称キーを削除する。{MDC{@link #remove(String)}の委譲メソッド。<br>
     * @param key
     *            削除対象キー
     * @return ロギング診断マネージャインスタンス(自オブジェクト)
     */
    public UraLoggingDiagnosticManager remove(String key) {
        if (contains(key)) {
            MDC_KEY_LIST.remove(key);
            MDC.remove(key);
        }
        return this;
    }

    /**
     * 内容のすべてを削除します。{@link MDC}内に含まれるすべての情報を削除します。<br>
     * @return 削除数
     */
    public int removeAll() {
        int result = this.size();
        for (final String key : MDC_KEY_LIST) {
            MDC.remove(key);
        }
        MDC_KEY_LIST.clear();
        return result;
    }

    /**
     * 現在MDCに含まれる情報の数を返却します。{MDC{@link #size()}の委譲メソッド。<br>
     * @return MDC内情報数
     */
    public int size() {
        return MDC_KEY_LIST.size();
    }
}
