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

import java.io.ObjectStreamException;

import org.slf4j.Logger;
import org.slf4j.Marker;
import org.uranoplums.typical.lang.UraSerialDataObject;

/**
 * UraLogクラス。<br>
 *
 * @since 2015/02/24
 * @author syany
 */
public class UraLog extends UraSerialDataObject implements org.slf4j.Logger {

    /** シリアル・バージョンUID */
    private static final long serialVersionUID = -2492573035178165411L;
    /** 内部ロガーIF：実際のロガーを管理する。 */
    private final Logger logger;

    /**
     * コンストラクタ。<br>
     * @param logger 内部ロガーIF：実際のロガーを管理する。
     */
    public UraLog(Logger logger) {
        super();
        this.logger = logger;
    }

    /**
     * シリアライズ
     * @return ログクラスの再取得
     * @throws ObjectStreamException
     */
    protected Object readResolve() throws ObjectStreamException {
        return UraLoggerFactory.getUraLog(getName());
    }

    /**
     * @param marker
     * @param msg
     * @see org.slf4j.Logger#debug(org.slf4j.Marker, java.lang.String)
     */
    @Override
    public void debug(Marker marker, String msg) {
        logger.debug(marker, msg);
    }

    /**
     * @param marker
     * @param format
     * @param arg
     * @see org.slf4j.Logger#debug(org.slf4j.Marker, java.lang.String, java.lang.Object)
     */
    @Override
    public void debug(Marker marker, String format, Object arg) {
        logger.debug(marker, format, arg);
    }

    /**
     * @param marker
     * @param format
     * @param arguments
     * @see org.slf4j.Logger#debug(org.slf4j.Marker, java.lang.String, java.lang.Object[])
     */
    @Override
    public void debug(Marker marker, String format, Object... arguments) {
        logger.debug(marker, format, arguments);
    }

    /**
     * @param marker
     * @param format
     * @param arg1
     * @param arg2
     * @see org.slf4j.Logger#debug(org.slf4j.Marker, java.lang.String, java.lang.Object, java.lang.Object)
     */
    @Override
    public void debug(Marker marker, String format, Object arg1, Object arg2) {
        logger.debug(marker, format, arg1, arg2);
    }

    /**
     * @param marker
     * @param msg
     * @param t
     * @see org.slf4j.Logger#debug(org.slf4j.Marker, java.lang.String, java.lang.Throwable)
     */
    @Override
    public void debug(Marker marker, String msg, Throwable t) {
        logger.debug(marker, msg, t);
    }

    /**
     * @param msg
     * @see org.slf4j.Logger#debug(java.lang.String)
     */
    @Override
    public void debug(String msg) {
        logger.debug(msg);
    }

    /**
     * @param format
     * @param arg
     * @see org.slf4j.Logger#debug(java.lang.String, java.lang.Object)
     */
    @Override
    public void debug(String format, Object arg) {
        logger.debug(format, arg);
    }

    /**
     * @param format
     * @param arguments
     * @see org.slf4j.Logger#debug(java.lang.String, java.lang.Object[])
     */
    @Override
    public void debug(String format, Object... arguments) {
        logger.debug(format, arguments);
    }

    /**
     * @param format
     * @param arg1
     * @param arg2
     * @see org.slf4j.Logger#debug(java.lang.String, java.lang.Object, java.lang.Object)
     */
    @Override
    public void debug(String format, Object arg1, Object arg2) {
        logger.debug(format, arg1, arg2);
    }

    /**
     * @param msg
     * @param t
     * @see org.slf4j.Logger#debug(java.lang.String, java.lang.Throwable)
     */
    @Override
    public void debug(String msg, Throwable t) {
        logger.debug(msg, t);
    }

    /**
     * @param marker
     * @param msg
     * @see org.slf4j.Logger#error(org.slf4j.Marker, java.lang.String)
     */
    @Override
    public void error(Marker marker, String msg) {
        logger.error(marker, msg);
    }

    /**
     * @param marker
     * @param format
     * @param arg
     * @see org.slf4j.Logger#error(org.slf4j.Marker, java.lang.String, java.lang.Object)
     */
    @Override
    public void error(Marker marker, String format, Object arg) {
        logger.error(marker, format, arg);
    }

    /**
     * @param marker
     * @param format
     * @param arguments
     * @see org.slf4j.Logger#error(org.slf4j.Marker, java.lang.String, java.lang.Object[])
     */
    @Override
    public void error(Marker marker, String format, Object... arguments) {
        logger.error(marker, format, arguments);
    }

    /**
     * @param marker
     * @param format
     * @param arg1
     * @param arg2
     * @see org.slf4j.Logger#error(org.slf4j.Marker, java.lang.String, java.lang.Object, java.lang.Object)
     */
    @Override
    public void error(Marker marker, String format, Object arg1, Object arg2) {
        logger.error(marker, format, arg1, arg2);
    }

    /**
     * @param marker
     * @param msg
     * @param t
     * @see org.slf4j.Logger#error(org.slf4j.Marker, java.lang.String, java.lang.Throwable)
     */
    @Override
    public void error(Marker marker, String msg, Throwable t) {
        logger.error(marker, msg, t);
    }

    /**
     * @param msg
     * @see org.slf4j.Logger#error(java.lang.String)
     */
    @Override
    public void error(String msg) {
        logger.error(msg);
    }

    /**
     * @param format
     * @param arg
     * @see org.slf4j.Logger#error(java.lang.String, java.lang.Object)
     */
    @Override
    public void error(String format, Object arg) {
        logger.error(format, arg);
    }

    /**
     * @param format
     * @param arguments
     * @see org.slf4j.Logger#error(java.lang.String, java.lang.Object[])
     */
    @Override
    public void error(String format, Object... arguments) {
        logger.error(format, arguments);
    }

    /**
     * @param format
     * @param arg1
     * @param arg2
     * @see org.slf4j.Logger#error(java.lang.String, java.lang.Object, java.lang.Object)
     */
    @Override
    public void error(String format, Object arg1, Object arg2) {
        logger.error(format, arg1, arg2);
    }

    /**
     * @param msg
     * @param t
     * @see org.slf4j.Logger#error(java.lang.String, java.lang.Throwable)
     */
    @Override
    public void error(String msg, Throwable t) {
        logger.error(msg, t);
    }

    /**
     * @return ログ名を返却
     * @see org.slf4j.Logger#getName()
     */
    @Override
    public String getName() {
        return logger.getName();
    }

    /**
     * @param marker
     * @param msg
     * @see org.slf4j.Logger#info(org.slf4j.Marker, java.lang.String)
     */
    @Override
    public void info(Marker marker, String msg) {
        logger.info(marker, msg);
    }

    /**
     * @param marker
     * @param format
     * @param arg
     * @see org.slf4j.Logger#info(org.slf4j.Marker, java.lang.String, java.lang.Object)
     */
    @Override
    public void info(Marker marker, String format, Object arg) {
        logger.info(marker, format, arg);
    }

    /**
     * @param marker
     * @param format
     * @param arguments
     * @see org.slf4j.Logger#info(org.slf4j.Marker, java.lang.String, java.lang.Object[])
     */
    @Override
    public void info(Marker marker, String format, Object... arguments) {
        logger.info(marker, format, arguments);
    }

    /**
     * @param marker
     * @param format
     * @param arg1
     * @param arg2
     * @see org.slf4j.Logger#info(org.slf4j.Marker, java.lang.String, java.lang.Object, java.lang.Object)
     */
    @Override
    public void info(Marker marker, String format, Object arg1, Object arg2) {
        logger.info(marker, format, arg1, arg2);
    }

    /**
     * @param marker
     * @param msg
     * @param t
     * @see org.slf4j.Logger#info(org.slf4j.Marker, java.lang.String, java.lang.Throwable)
     */
    @Override
    public void info(Marker marker, String msg, Throwable t) {
        logger.info(marker, msg, t);
    }

    /**
     * @param msg
     * @see org.slf4j.Logger#info(java.lang.String)
     */
    @Override
    public void info(String msg) {
        logger.info(msg);
    }

    /**
     * @param format
     * @param arg
     * @see org.slf4j.Logger#info(java.lang.String, java.lang.Object)
     */
    @Override
    public void info(String format, Object arg) {
        logger.info(format, arg);
    }

    /**
     * @param format
     * @param arguments
     * @see org.slf4j.Logger#info(java.lang.String, java.lang.Object[])
     */
    @Override
    public void info(String format, Object... arguments) {
        logger.info(format, arguments);
    }

    /**
     * @param format
     * @param arg1
     * @param arg2
     * @see org.slf4j.Logger#info(java.lang.String, java.lang.Object, java.lang.Object)
     */
    @Override
    public void info(String format, Object arg1, Object arg2) {
        logger.info(format, arg1, arg2);
    }

    /**
     * @param msg
     * @param t
     * @see org.slf4j.Logger#info(java.lang.String, java.lang.Throwable)
     */
    @Override
    public void info(String msg, Throwable t) {
        logger.info(msg, t);
    }

    /**
     * @return ログ出力可能レベルであればTRUE
     * @see org.slf4j.Logger#isDebugEnabled()
     */
    @Override
    public boolean isDebugEnabled() {
        return logger.isDebugEnabled();
    }

    /**
     * @param marker
     * @return ログ出力可能レベルであればTRUE
     * @see org.slf4j.Logger#isDebugEnabled(org.slf4j.Marker)
     */
    @Override
    public boolean isDebugEnabled(Marker marker) {
        return logger.isDebugEnabled(marker);
    }

    /**
     * @return ログ出力可能レベルであればTRUE
     * @see org.slf4j.Logger#isErrorEnabled()
     */
    @Override
    public boolean isErrorEnabled() {
        return logger.isErrorEnabled();
    }

    /**
     * @param marker
     * @return ログ出力可能レベルであればTRUE
     * @see org.slf4j.Logger#isErrorEnabled(org.slf4j.Marker)
     */
    @Override
    public boolean isErrorEnabled(Marker marker) {
        return logger.isErrorEnabled(marker);
    }

    /**
     * @return ログ出力可能レベルであればTRUE
     * @see org.slf4j.Logger#isInfoEnabled()
     */
    @Override
    public boolean isInfoEnabled() {
        return logger.isInfoEnabled();
    }

    /**
     * @param marker
     * @return ログ出力可能レベルであればTRUE
     * @see org.slf4j.Logger#isInfoEnabled(org.slf4j.Marker)
     */
    @Override
    public boolean isInfoEnabled(Marker marker) {
        return logger.isInfoEnabled(marker);
    }

    /**
     * @return ログ出力可能レベルであればTRUE
     * @see org.slf4j.Logger#isTraceEnabled()
     */
    @Override
    public boolean isTraceEnabled() {
        return logger.isTraceEnabled();
    }

    /**
     * @param marker
     * @return ログ出力可能レベルであればTRUE
     * @see org.slf4j.Logger#isTraceEnabled(org.slf4j.Marker)
     */
    @Override
    public boolean isTraceEnabled(Marker marker) {
        return logger.isTraceEnabled(marker);
    }

    /**
     * @return ログ出力可能レベルであればTRUE
     * @see org.slf4j.Logger#isWarnEnabled()
     */
    @Override
    public boolean isWarnEnabled() {
        return logger.isWarnEnabled();
    }

    /**
     * @param marker
     * @return ログ出力可能レベルであればTRUE
     * @see org.slf4j.Logger#isWarnEnabled(org.slf4j.Marker)
     */
    @Override
    public boolean isWarnEnabled(Marker marker) {
        return logger.isWarnEnabled(marker);
    }

    /**
     * @param marker
     * @param msg
     * @see org.slf4j.Logger#trace(org.slf4j.Marker, java.lang.String)
     */
    @Override
    public void trace(Marker marker, String msg) {
        logger.trace(marker, msg);
    }

    /**
     * @param marker
     * @param format
     * @param arg
     * @see org.slf4j.Logger#trace(org.slf4j.Marker, java.lang.String, java.lang.Object)
     */
    @Override
    public void trace(Marker marker, String format, Object arg) {
        logger.trace(marker, format, arg);
    }

    /**
     * @param marker
     * @param format
     * @param argArray
     * @see org.slf4j.Logger#trace(org.slf4j.Marker, java.lang.String, java.lang.Object[])
     */
    @Override
    public void trace(Marker marker, String format, Object... argArray) {
        logger.trace(marker, format, argArray);
    }

    /**
     * @param marker
     * @param format
     * @param arg1
     * @param arg2
     * @see org.slf4j.Logger#trace(org.slf4j.Marker, java.lang.String, java.lang.Object, java.lang.Object)
     */
    @Override
    public void trace(Marker marker, String format, Object arg1, Object arg2) {
        logger.trace(marker, format, arg1, arg2);
    }

    /**
     * @param marker
     * @param msg
     * @param t
     * @see org.slf4j.Logger#trace(org.slf4j.Marker, java.lang.String, java.lang.Throwable)
     */
    @Override
    public void trace(Marker marker, String msg, Throwable t) {
        logger.trace(marker, msg, t);
    }

    /**
     * @param msg
     * @see org.slf4j.Logger#trace(java.lang.String)
     */
    @Override
    public void trace(String msg) {
        logger.trace(msg);
    }

    /**
     * @param format
     * @param arg
     * @see org.slf4j.Logger#trace(java.lang.String, java.lang.Object)
     */
    @Override
    public void trace(String format, Object arg) {
        logger.trace(format, arg);
    }

    /**
     * @param format
     * @param arguments
     * @see org.slf4j.Logger#trace(java.lang.String, java.lang.Object[])
     */
    @Override
    public void trace(String format, Object... arguments) {
        logger.trace(format, arguments);
    }

    /**
     * @param format
     * @param arg1
     * @param arg2
     * @see org.slf4j.Logger#trace(java.lang.String, java.lang.Object, java.lang.Object)
     */
    @Override
    public void trace(String format, Object arg1, Object arg2) {
        logger.trace(format, arg1, arg2);
    }

    /**
     * @param msg
     * @param t
     * @see org.slf4j.Logger#trace(java.lang.String, java.lang.Throwable)
     */
    @Override
    public void trace(String msg, Throwable t) {
        logger.trace(msg, t);
    }

    /**
     * @param marker
     * @param msg
     * @see org.slf4j.Logger#warn(org.slf4j.Marker, java.lang.String)
     */
    @Override
    public void warn(Marker marker, String msg) {
        logger.warn(marker, msg);
    }

    /**
     * @param marker
     * @param format
     * @param arg
     * @see org.slf4j.Logger#warn(org.slf4j.Marker, java.lang.String, java.lang.Object)
     */
    @Override
    public void warn(Marker marker, String format, Object arg) {
        logger.warn(marker, format, arg);
    }

    /**
     * @param marker
     * @param format
     * @param arguments
     * @see org.slf4j.Logger#warn(org.slf4j.Marker, java.lang.String, java.lang.Object[])
     */
    @Override
    public void warn(Marker marker, String format, Object... arguments) {
        logger.warn(marker, format, arguments);
    }

    /**
     * @param marker
     * @param format
     * @param arg1
     * @param arg2
     * @see org.slf4j.Logger#warn(org.slf4j.Marker, java.lang.String, java.lang.Object, java.lang.Object)
     */
    @Override
    public void warn(Marker marker, String format, Object arg1, Object arg2) {
        logger.warn(marker, format, arg1, arg2);
    }

    /**
     * @param marker
     * @param msg
     * @param t
     * @see org.slf4j.Logger#warn(org.slf4j.Marker, java.lang.String, java.lang.Throwable)
     */
    @Override
    public void warn(Marker marker, String msg, Throwable t) {
        logger.warn(marker, msg, t);
    }

    /**
     * @param msg
     * @see org.slf4j.Logger#warn(java.lang.String)
     */
    @Override
    public void warn(String msg) {
        logger.warn(msg);
    }

    /**
     * @param format
     * @param arg
     * @see org.slf4j.Logger#warn(java.lang.String, java.lang.Object)
     */
    @Override
    public void warn(String format, Object arg) {
        logger.warn(format, arg);
    }

    /**
     * @param format
     * @param arguments
     * @see org.slf4j.Logger#warn(java.lang.String, java.lang.Object[])
     */
    @Override
    public void warn(String format, Object... arguments) {
        logger.warn(format, arguments);
    }

    /**
     * @param format
     * @param arg1
     * @param arg2
     * @see org.slf4j.Logger#warn(java.lang.String, java.lang.Object, java.lang.Object)
     */
    @Override
    public void warn(String format, Object arg1, Object arg2) {
        logger.warn(format, arg1, arg2);
    }

    /**
     * @param msg
     * @param t
     * @see org.slf4j.Logger#warn(java.lang.String, java.lang.Throwable)
     */
    @Override
    public void warn(String msg, Throwable t) {
        logger.warn(msg, t);
    }
}