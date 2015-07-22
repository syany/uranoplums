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

import org.uranoplums.typical.util.UraStringUtils;

import ch.qos.logback.classic.Level;
import ch.qos.logback.classic.Logger;

/**
 * UraStringCodeLogクラス。<br>
 * 
 * @since 2015/02/24
 * @author syany
 */
public class UraStringCodeLog extends AbsUraCodeLog<String> {

    /** シリアライズ・バージョンID */
    private static final long serialVersionUID = 1684011812281915098L;

    /**
     * @param logger
     */
    public UraStringCodeLog(Logger logger) {
        super(logger);
    }

    /**
     * @param logger
     * @param uraCodeLevelJudge
     */
    public UraStringCodeLog(Logger logger,
            UraCodeLevelJudge<String> uraCodeLevelJudge) {
        super(logger, uraCodeLevelJudge);
    }

    /**
     * @param logger
     * @param uraCodeLevelJudge
     * @param uraCodeMessageBundler
     */
    public UraStringCodeLog(Logger logger,
            UraCodeLevelJudge<String> uraCodeLevelJudge,
            UraCodeMessageBundler<String> uraCodeMessageBundler) {
        super(logger, uraCodeLevelJudge, uraCodeMessageBundler);
    }

    /**
     * @param logger
     * @param uraCodeMessageBundler
     */
    public UraStringCodeLog(Logger logger,
            UraCodeMessageBundler<String> uraCodeMessageBundler) {
        super(logger, uraCodeMessageBundler);
    }

    /**
     * @return
     * @throws ObjectStreamException
     */
    protected Object readResolve() throws ObjectStreamException {
        return UraLoggerFactory.getUraStringCodeLog(getName(),
                uraCodeLevelJudge, uraCodeMessageBundler);
    }

    /*
     * (非 Javadoc)
     * 
     * @see org.uranoplums.typical.log.UraCodeMessageBundler#getMessage(null)
     */
    @Override
    public String getMessage(String source) {
        return source;
    }

    /*
     * (非 Javadoc)
     * 
     * @see org.uranoplums.typical.log.UraCodeLevelJudge#toLevel(null, ch.qos.logback.classic.Level)
     */
    @Override
    public Level toLevel(String source, Level defaultLevel) {
        // デフォルトがnullならばOFF
        if (defaultLevel == null) {
            defaultLevel = Level.OFF;
        }
        // 対象が空ならば、デフォルト返却
        if (UraStringUtils.isEmpty(source)) {
            return defaultLevel;
        }
        char prefix = source.charAt(0);
        switch (prefix) {
            case 'A':
                return Level.ALL;
            case 'T':
                return Level.TRACE;
            case 'D':
                return Level.DEBUG;
            case 'I':
                return Level.INFO;
            case 'W':
                return Level.WARN;
            case 'E':
                return Level.ERROR;
            case 'O':
                return Level.OFF;
            default:
                return defaultLevel;
        }
    }
}
