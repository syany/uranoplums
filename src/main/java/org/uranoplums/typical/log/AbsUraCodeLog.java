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

import java.util.Iterator;

import org.slf4j.Marker;
import org.uranoplums.typical.lang.UraSerialDataObject;

import ch.qos.logback.classic.Level;
import ch.qos.logback.classic.Logger;
import ch.qos.logback.classic.spi.ILoggingEvent;
import ch.qos.logback.classic.spi.LoggingEvent;
import ch.qos.logback.classic.spi.TurboFilterList;
import ch.qos.logback.core.Appender;
import ch.qos.logback.core.spi.FilterReply;

/**
 * AbsUraCodeLogクラス。<br>
 *
 * @since 2015/02/24
 * @author syany
 * @param <E>
 */
public abstract class AbsUraCodeLog<E extends Object> extends UraSerialDataObject
        implements UraCodeLevelJudge<E>, UraCodeMessageBundler<E>, UraLogger<E> {

    /** シリアライズ・バージョンUID */
    private static final long serialVersionUID = -3056938175664442798L;
    /**  */
    private final Logger logger;
    /***/
    protected UraCodeLevelJudge<E> uraCodeLevelJudge;
    /**  */
    protected UraCodeMessageBundler<E> uraCodeMessageBundler;

    /**
     * @param logger
     */
    public AbsUraCodeLog(Logger logger) {
        super();
        this.logger = logger;
        this.uraCodeLevelJudge = this;
        this.uraCodeMessageBundler = this;
    }

    /**
     * @param logger
     * @param uraCodeLevelJudge
     */
    public AbsUraCodeLog(Logger logger,
            final UraCodeLevelJudge<E> uraCodeLevelJudge) {
        super();
        this.logger = logger;
        this.uraCodeLevelJudge = uraCodeLevelJudge;
        this.uraCodeMessageBundler = this;
    }

    /**
     * @param logger
     * @param uraCodeLevelJudge
     * @param uraCodeMessageBundler
     */
    public AbsUraCodeLog(Logger logger,
            final UraCodeLevelJudge<E> uraCodeLevelJudge,
            final UraCodeMessageBundler<E> uraCodeMessageBundler) {
        super();
        this.logger = logger;
        this.uraCodeLevelJudge = uraCodeLevelJudge;
    }

    /**
     * @param logger
     * @param uraCodeMessageBundler
     */
    public AbsUraCodeLog(Logger logger,
            final UraCodeMessageBundler<E> uraCodeMessageBundler) {
        super();
        this.logger = logger;
        this.uraCodeLevelJudge = this;
        this.uraCodeMessageBundler = uraCodeMessageBundler;
    }

    /**
     * @param localFQCN
     * @param marker
     * @param level
     * @param msg
     * @param params
     * @param t
     */
    private void buildLoggingEventAndAppend(final String localFQCN,
            final Marker marker, final Level level, final String msg,
            final Object[] params, final Throwable t) {
        LoggingEvent le = new LoggingEvent(localFQCN, logger, level, msg, t,
                params);
        le.setMarker(marker);
        callAppenders(le);
    }

    /**
     * The next methods are not merged into one because of the time we gain by
     * not creating a new Object[] with the params. This reduces the cost of not
     * logging by about 20 nanoseconds.
     */
    private void filterAndLog_0_Or3Plus(final Marker marker, final Level level,
            final String msg, final Object[] params, final Throwable t) {
        final FilterReply decision = getTurboFilterChainDecision_0_3OrMore(
                marker, logger, level, msg, params, t);
        if (decision == FilterReply.NEUTRAL) {
            if (logger.getEffectiveLevel().toInt() > level.levelInt) {
                return;
            }
        } else if (decision == FilterReply.DENY) {
            return;
        }
        buildLoggingEventAndAppend(Logger.FQCN, marker, level, msg, params, t);
    }

    /**
     * @param marker
     * @param logger
     * @param level
     * @param format
     * @param params
     * @param t
     * @return
     */
    private FilterReply getTurboFilterChainDecision_0_3OrMore(
            final Marker marker, final Logger logger, final Level level,
            final String format, final Object[] params, final Throwable t) {
        TurboFilterList turboFilterList = logger.getLoggerContext()
                .getTurboFilterList();
        if (turboFilterList.size() == 0) {
            return FilterReply.NEUTRAL;
        }
        return turboFilterList.getTurboFilterChainDecision(marker, logger,
                level, format, params, t);
    }

    /**
     * @param uraCodeLevelJudge
     *            セットする uraCodeLevelJudge
     */
    void setUraCodeLevelJudge(final UraCodeLevelJudge<E> uraCodeLevelJudge) {
        this.uraCodeLevelJudge = uraCodeLevelJudge;
    }

    /**
     * @param uraCodeMessageBundler
     *            セットする uraCodeMessageBundler
     */
    void setUraCodeMessageBundler(
            final UraCodeMessageBundler<E> uraCodeMessageBundler) {
        this.uraCodeMessageBundler = uraCodeMessageBundler;
    }

    /**
     * @param newAppender
     * @see ch.qos.logback.classic.Logger#addAppender(ch.qos.logback.core.Appender)
     */
    @Override
    public void addAppender(Appender<ILoggingEvent> newAppender) {
        logger.addAppender(newAppender);
    }

    /**
     * @param event
     * @see ch.qos.logback.classic.Logger#callAppenders(ch.qos.logback.classic.spi.ILoggingEvent)
     */
    @Override
    public void callAppenders(ILoggingEvent event) {
        logger.callAppenders(event);
    }

    /**
     *
     * @see ch.qos.logback.classic.Logger#detachAndStopAllAppenders()
     */
    @Override
    public void detachAndStopAllAppenders() {
        logger.detachAndStopAllAppenders();
    }

    /**
     * @param appender
     * @return
     * @see ch.qos.logback.classic.Logger#detachAppender(ch.qos.logback.core.Appender)
     */
    @Override
    public boolean detachAppender(Appender<ILoggingEvent> appender) {
        return logger.detachAppender(appender);
    }

    /**
     * @param name
     * @return
     * @see ch.qos.logback.classic.Logger#detachAppender(java.lang.String)
     */
    @Override
    public boolean detachAppender(String name) {
        return logger.detachAppender(name);
    }

    /**
     * @param name
     * @return
     * @see ch.qos.logback.classic.Logger#getAppender(java.lang.String)
     */
    @Override
    public Appender<ILoggingEvent> getAppender(String name) {
        return logger.getAppender(name);
    }

    /**
     * @return
     * @see ch.qos.logback.classic.Logger#getEffectiveLevel()
     */
    @Override
    public Level getEffectiveLevel() {
        return logger.getEffectiveLevel();
    }

    /**
     * @return
     * @see ch.qos.logback.classic.Logger#getLevel()
     */
    @Override
    public Level getLevel() {
        return logger.getLevel();
    }

    /**
     * @return
     * @see ch.qos.logback.classic.Logger#getName()
     */
    @Override
    public String getName() {
        return logger.getName();
    }

    /**
     * @return
     * @see ch.qos.logback.classic.Logger#isAdditive()
     */
    @Override
    public boolean isAdditive() {
        return logger.isAdditive();
    }

    /**
     * @param appender
     * @return
     * @see ch.qos.logback.classic.Logger#isAttached(ch.qos.logback.core.Appender)
     */
    @Override
    public boolean isAttached(Appender<ILoggingEvent> appender) {
        return logger.isAttached(appender);
    }

    /**
     * @return
     * @see ch.qos.logback.classic.Logger#isDebugEnabled()
     */
    @Override
    public boolean isDebugEnabled() {
        return logger.isDebugEnabled();
    }

    /**
     * @param marker
     * @return
     * @see ch.qos.logback.classic.Logger#isDebugEnabled(org.slf4j.Marker)
     */
    @Override
    public boolean isDebugEnabled(Marker marker) {
        return logger.isDebugEnabled(marker);
    }

    /**
     * @param level
     * @return
     * @see ch.qos.logback.classic.Logger#isEnabledFor(ch.qos.logback.classic.Level)
     */
    @Override
    public boolean isEnabledFor(Level level) {
        return logger.isEnabledFor(level);
    }

    /**
     * @param marker
     * @param level
     * @return
     * @see ch.qos.logback.classic.Logger#isEnabledFor(org.slf4j.Marker, ch.qos.logback.classic.Level)
     */
    @Override
    public boolean isEnabledFor(Marker marker, Level level) {
        return logger.isEnabledFor(marker, level);
    }

    /**
     * @return
     * @see ch.qos.logback.classic.Logger#isErrorEnabled()
     */
    @Override
    public boolean isErrorEnabled() {
        return logger.isErrorEnabled();
    }

    /**
     * @param marker
     * @return
     * @see ch.qos.logback.classic.Logger#isErrorEnabled(org.slf4j.Marker)
     */
    @Override
    public boolean isErrorEnabled(Marker marker) {
        return logger.isErrorEnabled(marker);
    }

    /**
     * @return
     * @see ch.qos.logback.classic.Logger#isInfoEnabled()
     */
    @Override
    public boolean isInfoEnabled() {
        return logger.isInfoEnabled();
    }

    /**
     * @param marker
     * @return
     * @see ch.qos.logback.classic.Logger#isInfoEnabled(org.slf4j.Marker)
     */
    @Override
    public boolean isInfoEnabled(Marker marker) {
        return logger.isInfoEnabled(marker);
    }

    /**
     * @return
     * @see ch.qos.logback.classic.Logger#isTraceEnabled()
     */
    @Override
    public boolean isTraceEnabled() {
        return logger.isTraceEnabled();
    }

    /**
     * @param marker
     * @return
     * @see ch.qos.logback.classic.Logger#isTraceEnabled(org.slf4j.Marker)
     */
    @Override
    public boolean isTraceEnabled(Marker marker) {
        return logger.isTraceEnabled(marker);
    }

    /**
     * @return
     * @see ch.qos.logback.classic.Logger#isWarnEnabled()
     */
    @Override
    public boolean isWarnEnabled() {
        return logger.isWarnEnabled();
    }

    /**
     * @param marker
     * @return
     * @see ch.qos.logback.classic.Logger#isWarnEnabled(org.slf4j.Marker)
     */
    @Override
    public boolean isWarnEnabled(Marker marker) {
        return logger.isWarnEnabled(marker);
    }

    /**
     * @return
     * @see ch.qos.logback.classic.Logger#iteratorForAppenders()
     */
    @Override
    public Iterator<Appender<ILoggingEvent>> iteratorForAppenders() {
        return logger.iteratorForAppenders();
    }

    /**
     * @param messageCode
     * @param argArray
     */
    @Override
    public void log(E messageCode, Object... argArray) {
        log(null, null, messageCode, argArray);
    }

    /**
     * @param marker
     * @param messageCode
     * @param argArray
     */
    @Override
    public void log(Marker marker, E messageCode, Object... argArray) {
        log(marker, null, messageCode, argArray);
    }

    /**
     * @param marker
     * @param t
     * @param messageCode
     * @param argArray
     */
    @Override
    public void log(Marker marker, Throwable t, E messageCode,
            Object... argArray) {
        filterAndLog_0_Or3Plus(marker,
                this.uraCodeLevelJudge.toLevel(messageCode, Level.OFF),
                this.uraCodeMessageBundler.getMessage(messageCode), argArray, t);
    }

    /**
     * @param additive
     * @see ch.qos.logback.classic.Logger#setAdditive(boolean)
     */
    @Override
    public void setAdditive(boolean additive) {
        logger.setAdditive(additive);
    }

    /**
     * @param newLevel
     * @see ch.qos.logback.classic.Logger#setLevel(ch.qos.logback.classic.Level)
     */
    @Override
    public void setLevel(Level newLevel) {
        logger.setLevel(newLevel);
    }

    /**
     * @return 文字列
     * @see ch.qos.logback.classic.Logger#toString()
     */
    @Override
    public String toString() {
        return logger.toString();
    }
}
