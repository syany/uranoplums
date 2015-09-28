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
 * $Id: UraInnerCodeLog.java$
 */
package org.uranoplums.typical.log;

import java.util.Locale;

import org.slf4j.Logger;
import org.uranoplums.typical.resource.UraJSONResource;
import org.uranoplums.typical.util.UraStringUtils;

import ch.qos.logback.classic.Level;

/**
 * UraInnerCodeLogクラス。<br>
 *
 * @since 2015/11/03
 * @author syany
 */
class UraInnerCodeLog extends UraStringCodeLog {
    /**  */
    private static final long serialVersionUID = 6986999092674939564L;
    /**  */
    private static final String PREFIX = UraInnerCodeStatic.CODE_PREFIX;
    /**  */
    private UraJSONResource messageResource = new UraJSONResource(UraInnerCodeStatic.RESOURCE_PATH);

    public UraInnerCodeLog(Logger logger) {
        super(logger);
    }

    @Override
    public Level toLevel(String source, Level defaultLevel) {
        // 対象が空ならば、デフォルト返却
        if (UraStringUtils.isEmpty(source)) {
            return defaultLevel;
        }
        int offset = 0;
        String target = null;
        if ((offset = UraStringUtils.indexOf(source, PREFIX)) != -1) {
            target = UraStringUtils.substring(source, offset + PREFIX.length());
        } else {
            target = source;
        }
        return super.toLevel(target, defaultLevel);
    }

    @Override
    public String getMessage(String source, Object... argArray) {
        StringBuilder sb = new StringBuilder(3);
        sb.append(source);
        sb.append(UraStringUtils.W_SPACE);
        sb.append(this.messageResource.getResourceString(Locale.getDefault(), source, argArray));
        return sb.toString();
    }

}
