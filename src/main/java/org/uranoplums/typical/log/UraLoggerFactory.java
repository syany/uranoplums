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
package org.uranoplums.typical.log;

import org.slf4j.ILoggerFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.uranoplums.typical.lang.UraStaticObject;

/**
 * UraLoggerFactoryクラス。<br>
 *
 * @since 2015/02/24
 * @author syany
 */
public final class UraLoggerFactory extends UraStaticObject {

    /**
     * Return the {@link ILoggerFactory} instance in use.
     * <p/>
     * <p/>
     * ILoggerFactory instance is bound with this class at compile time.
     *
     * @return the ILoggerFactory instance in use
     */
    public static ILoggerFactory getILoggerFactory() {
        return LoggerFactory.getILoggerFactory();
    }

    /**
     * 自動的に、（階層付きの）名前を決めたロガーインスタンスを返却します。<br>
     * 名前は、呼び元のクラス名からパッケージ階層付きのクラス名を文字列として設定します。
     * @return logger
     */
    public static Logger getLogger() {
        return getLogger(2);
    }

    /**
     * Return a logger named corresponding to the class passed as parameter,
     * using the statically bound {@link ILoggerFactory} instance.
     *
     * @param clazz
     *            the returned logger will be named after clazz
     * @return logger
     */
    public static Logger getLogger(Class<?> clazz) {
        return LoggerFactory.getLogger(clazz);
    }

    /**
     * 自動的に、（階層付きの）名前を決めたロガーインスタンスを返却します。<br>
     * 名前は、引数で指定した深さ分前の呼び元のクラス名からパッケージ階層付きのクラス名を文字列として設定します。
     * @param callerDepth 呼び元の深さ
     * @return logger
     */
    public static Logger getLogger(int callerDepth) {
        return getLogger((new Throwable()).getStackTrace()[callerDepth].getClassName());
    }

    /**
     * Return a logger named according to the name parameter using the
     * statically bound {@link ILoggerFactory} instance.
     *
     * @param name
     *            The name of the logger.
     * @return logger
     */
    public static Logger getLogger(String name) {

        return LoggerFactory.getLogger(name);
    }

    /**
     * @return UraLogインスタンス
     */
    public static UraLog getUraLog() {
        return getUraLog(3);
    }

    /**
     * @param clazz クラス
     * @return UraLogインスタンス
     */
    public static UraLog getUraLog(Class<?> clazz) {
        return new UraLog(getLogger(clazz));
    }

    /**
     * @param callerDepth
     * @return UraLogインスタンス
     */
    public static UraLog getUraLog(int callerDepth) {
        return new UraLog(getLogger(callerDepth));
    }

    /**
     * @param name ロガー階層名称
     * @return UraLogインスタンス
     */
    public static UraLog getUraLog(String name) {
        return new UraLog(getLogger(name));
    }

    /**
     * @return UraStringCodeLogインスタンス
     */
    public static UraStringCodeLog getUraStringCodeLog() {
        return getUraStringCodeLog(3);
    }

    /**
     * @param clazz クラス
     * @return UraStringCodeLogインスタンス
     */
    public static UraStringCodeLog getUraStringCodeLog(Class<?> clazz) {
        return new UraStringCodeLog(getLogger(clazz));
    }

    /**
     * @param clazz クラス
     * @param uraCodeLevelJudge レベル判定クラス
     * @param uraCodeMessageBundler メッセージ取得クラス
     * @return UraStringCodeLogインスタンス
     */
    public static UraStringCodeLog getUraStringCodeLog(Class<?> clazz,
            UraCodeLevelJudge<String> uraCodeLevelJudge,
            UraCodeMessageBundler<String> uraCodeMessageBundler) {
        return new UraStringCodeLog(getLogger(clazz), uraCodeLevelJudge, uraCodeMessageBundler);
    }

    /**
     * @param callerDepth 深さ
     * @return UraStringCodeLogインスタンス
     */
    public static UraStringCodeLog getUraStringCodeLog(int callerDepth) {
        return new UraStringCodeLog(getLogger(callerDepth));
    }

    /**
     * @param callerDepth 深さ
     * @param uraCodeLevelJudge レベル判定クラス
     * @param uraCodeMessageBundler メッセージ取得クラス
     * @return UraStringCodeLogインスタンス
     */
    public static UraStringCodeLog getUraStringCodeLog(int callerDepth,
            UraCodeLevelJudge<String> uraCodeLevelJudge,
            UraCodeMessageBundler<String> uraCodeMessageBundler) {
        return new UraStringCodeLog(getLogger(callerDepth), uraCodeLevelJudge, uraCodeMessageBundler);
    }

    /**
     * @param name ロガー階層名称
     * @return UraStringCodeLogインスタンス
     */
    public static UraStringCodeLog getUraStringCodeLog(String name) {
        return new UraStringCodeLog(getLogger(name));
    }

    /**
     * @param name ロガー階層名称
     * @param uraCodeLevelJudge レベル判定クラス
     * @param uraCodeMessageBundler メッセージ取得クラス
     * @return UraStringCodeLogインスタンス
     */
    public static UraStringCodeLog getUraStringCodeLog(String name,
            UraCodeLevelJudge<String> uraCodeLevelJudge,
            UraCodeMessageBundler<String> uraCodeMessageBundler) {
        return new UraStringCodeLog(getLogger(name), uraCodeLevelJudge, uraCodeMessageBundler);
    }

    /**
     * @param uraCodeLevelJudge
     * @param uraCodeMessageBundler
     * @return UraStringCodeLogインスタンス
     */
    public static UraStringCodeLog getUraStringCodeLog(
            UraCodeLevelJudge<String> uraCodeLevelJudge,
            UraCodeMessageBundler<String> uraCodeMessageBundler) {
        return getUraStringCodeLog(3, uraCodeLevelJudge, uraCodeMessageBundler);
    }
}
