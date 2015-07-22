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
 * $Id: UraLogger.java$
 */
package org.uranoplums.typical.log;

import java.util.Iterator;

import org.slf4j.Marker;

import ch.qos.logback.classic.Level;
import ch.qos.logback.classic.spi.ILoggingEvent;
import ch.qos.logback.core.Appender;

/**
 * UraLoggerクラス。<br>
 * 
 * @since 2015/04/13
 * @author syany
 * @param <E>
 */
public interface UraLogger<E> {

    /**
     * @param newAppender
     * @see ch.qos.logback.classic.Logger#addAppender(ch.qos.logback.core.Appender)
     */
    void addAppender(Appender<ILoggingEvent> newAppender);

    /**
     * @param event
     * @see ch.qos.logback.classic.Logger#callAppenders(ch.qos.logback.classic.spi.ILoggingEvent)
     */
    void callAppenders(ILoggingEvent event);

    /**
     * 
     * @see ch.qos.logback.classic.Logger#detachAndStopAllAppenders()
     */
    void detachAndStopAllAppenders();

    /**
     * @param appender
     * @return
     * @see ch.qos.logback.classic.Logger#detachAppender(ch.qos.logback.core.Appender)
     */
    boolean detachAppender(Appender<ILoggingEvent> appender);

    /**
     * @param name
     * @return
     * @see ch.qos.logback.classic.Logger#detachAppender(java.lang.String)
     */
    boolean detachAppender(String name);

    /**
     * @param name
     * @return
     * @see ch.qos.logback.classic.Logger#getAppender(java.lang.String)
     */
    Appender<ILoggingEvent> getAppender(String name);

    /**
     * @return
     * @see ch.qos.logback.classic.Logger#getEffectiveLevel()
     */
    Level getEffectiveLevel();

    /**
     * @return
     * @see ch.qos.logback.classic.Logger#getLevel()
     */
    Level getLevel();

    /**
     * @return
     * @see ch.qos.logback.classic.Logger#getName()
     */
    String getName();

    /**
     * @return
     * @see ch.qos.logback.classic.Logger#isAdditive()
     */
    boolean isAdditive();

    /**
     * @param appender
     * @return
     * @see ch.qos.logback.classic.Logger#isAttached(ch.qos.logback.core.Appender)
     */
    boolean isAttached(Appender<ILoggingEvent> appender);

    /**
     * @return
     * @see ch.qos.logback.classic.Logger#isDebugEnabled()
     */
    boolean isDebugEnabled();

    /**
     * @param marker
     * @return
     * @see ch.qos.logback.classic.Logger#isDebugEnabled(org.slf4j.Marker)
     */
    boolean isDebugEnabled(Marker marker);

    /**
     * @param level
     * @return
     * @see ch.qos.logback.classic.Logger#isEnabledFor(ch.qos.logback.classic.Level)
     */
    boolean isEnabledFor(Level level);

    /**
     * @param marker
     * @param level
     * @return
     * @see ch.qos.logback.classic.Logger#isEnabledFor(org.slf4j.Marker, ch.qos.logback.classic.Level)
     */
    boolean isEnabledFor(Marker marker, Level level);

    /**
     * @return
     * @see ch.qos.logback.classic.Logger#isErrorEnabled()
     */
    boolean isErrorEnabled();

    /**
     * @param marker
     * @return
     * @see ch.qos.logback.classic.Logger#isErrorEnabled(org.slf4j.Marker)
     */
    boolean isErrorEnabled(Marker marker);

    /**
     * @return
     * @see ch.qos.logback.classic.Logger#isInfoEnabled()
     */
    boolean isInfoEnabled();

    /**
     * @param marker
     * @return
     * @see ch.qos.logback.classic.Logger#isInfoEnabled(org.slf4j.Marker)
     */
    boolean isInfoEnabled(Marker marker);

    /**
     * @return
     * @see ch.qos.logback.classic.Logger#isTraceEnabled()
     */
    boolean isTraceEnabled();

    /**
     * @param marker
     * @return
     * @see ch.qos.logback.classic.Logger#isTraceEnabled(org.slf4j.Marker)
     */
    boolean isTraceEnabled(Marker marker);

    /**
     * @return
     * @see ch.qos.logback.classic.Logger#isWarnEnabled()
     */
    boolean isWarnEnabled();

    /**
     * @param marker
     * @return
     * @see ch.qos.logback.classic.Logger#isWarnEnabled(org.slf4j.Marker)
     */
    boolean isWarnEnabled(Marker marker);

    /**
     * @return
     * @see ch.qos.logback.classic.Logger#iteratorForAppenders()
     */
    Iterator<Appender<ILoggingEvent>> iteratorForAppenders();

    /**
     * @param messageCode
     * @param argArray
     */
    void log(E messageCode, Object... argArray);

    /**
     * @param marker
     * @param messageCode
     * @param argArray
     */
    void log(Marker marker, E messageCode, Object... argArray);

    /**
     * @param marker
     * @param t
     * @param messageCode
     * @param argArray
     */
    void log(Marker marker, Throwable t, E messageCode,
            Object... argArray);

    /**
     * @param additive
     * @see ch.qos.logback.classic.Logger#setAdditive(boolean)
     */
    void setAdditive(boolean additive);

    /**
     * @param newLevel
     * @see ch.qos.logback.classic.Logger#setLevel(ch.qos.logback.classic.Level)
     */
    void setLevel(Level newLevel);
}
