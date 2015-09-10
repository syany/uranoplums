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
 * $Id: AbsUraCodeLogTest.java$
 */
package org.uranoplums.typical.log;

import static org.junit.Assert.*;

import java.util.Locale;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.slf4j.Logger;
import org.uranoplums.typical.resource.UraJSONResource;
import org.uranoplums.typical.util.UraStringUtils;

import ch.qos.logback.classic.Level;


/**
 * AbsUraCodeLogTestクラス。<br>
 *
 * @since 2015/08/24
 * @author syany
 */
public class AbsUraCodeLogTest {

    /**
     * 。<br>
     * @throws java.lang.Exception
     */
    @BeforeClass
    public static void setUpBeforeClass() throws Exception {}

    /**
     * 。<br>
     * @throws java.lang.Exception
     */
    @AfterClass
    public static void tearDownAfterClass() throws Exception {}

    /**
     * 。<br>
     * @throws java.lang.Exception
     */
    @Before
    public void setUp() throws Exception {}

    /**
     * 。<br>
     * @throws java.lang.Exception
     */
    @After
    public void tearDown() throws Exception {}

    /**
     * {@link org.uranoplums.typical.log.AbsUraCodeLog#AbsUraCodeLog(ch.qos.logback.classic.Logger)} のためのテスト・メソッド。
     */
    @Test
    public void testAbsUraCodeLogLogger() {
        // インスタンス化
        UraLogger<String> logger = new AutoLevelLog(UraLoggerFactory.getLogger());
        int id = 234;
        // ログ出力例
        logger.log("DBG デバッグ向けログです。[ID:{}]", id);

        assertTrue(true);
    }

    @Test
    public void testAbsUraMessageLogLogger() {
        // インスタンス化
        UraLogger<String> logger = new CodeMessageLog(UraLoggerFactory.getLogger());
        int id = 234;
        // ログ出力例
        logger.log("DBG001", id);
        logger.log("ERR002", logger.toString());
        // 存在しないコード値
        logger.log("ERR001", id);

        assertTrue(true);
    }

    @Test
    public void testLoggerMDC() {
        // スレッドID をTIDというキー名で MDC経由で追加する。
        UraLoggingDiagnosticManager.getInstance().put("TID",
                String.format("%d", Thread.currentThread().getId()),
                "-- No Thread ID --");
        // インスタンス化
        UraLogger<String> logger = new CodeMessageLog(UraLoggerFactory.getLogger());
        int id = 234;
        // ログ出力例
        logger.log("DBG001", id);
        logger.log("ERR002", logger.toString());
        // MDCで追加しているものを削除
        UraLoggingDiagnosticManager.getInstance().removeAll();

        assertTrue(true);
    }
}

class AutoLevelLog extends AbsUraCodeLog<String> {

    /**  */
    private static final long serialVersionUID = -8825156045711516414L;

    /**
     * デフォルトコンストラクタ。<br>
     * @param logger
     */
    public AutoLevelLog(org.slf4j.Logger logger) {
        super(logger);
    }

    /* (非 Javadoc)
     * @see org.uranoplums.typical.log.UraCodeLevelJudge#toLevel(java.lang.Object, ch.qos.logback.classic.Level)
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

        // 先頭３文字比較
        String target = UraStringUtils.substring(source, 0, 3);
        if (UraStringUtils.equals(target, "ERR")) {
            return Level.ERROR;
        } else if (UraStringUtils.equals(target, "DBG")) {
            return Level.DEBUG;
        }
        return defaultLevel;
    }

    /* (非 Javadoc)
     * @see org.uranoplums.typical.log.UraCodeMessageBundler#getMessage(java.lang.Object)
     */
    @Override
    public String getMessage(String source, Object... argArray) {
        return source;
    }

}



class CodeMessageLog extends AbsUraCodeLog<String> {

    /**  */
    private static final long serialVersionUID = -4107111410081750179L;

    /**
     * デフォルトコンストラクタ。<br>
     * @param logger
     */
    public CodeMessageLog(Logger logger) {
        super(logger);
    }

    private UraJSONResource messageResource = new UraJSONResource("message");

    /* (非 Javadoc)
     * @see org.uranoplums.typical.log.UraCodeLevelJudge#toLevel(java.lang.Object, ch.qos.logback.classic.Level)
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

        // 先頭３文字比較
        String target = UraStringUtils.substring(source, 0, 3);
        if (UraStringUtils.equals(target, "ERR")) {
            return Level.ERROR;
        } else if (UraStringUtils.equals(target, "DBG")) {
            return Level.DEBUG;
        }
        return defaultLevel;
    }

    /* (非 Javadoc)
     * @see org.uranoplums.typical.log.UraCodeMessageBundler#getMessage(java.lang.Object)
     */
    @Override
    public String getMessage(String source, Object... argArray) {
        return this.messageResource.getResourceString(Locale.getDefault(), source, argArray);
    }

}
