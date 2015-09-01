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
 * $Id: UraStringCodeLogTest.java$
 */
package org.uranoplums.typical.log;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;


/**
 * UraStringCodeLogTestクラス。<br>
 *
 * @since 2015/09/01
 * @author syany
 */
public class UraStringCodeLogTest {

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
     * {@link org.uranoplums.typical.log.UraStringCodeLog#UraStringCodeLog(org.slf4j.Logger)} のためのテスト・メソッド。
     */
    @Test
    public void testUraStringCodeLogLogger() {

        // インスタンス化
        UraLogger<String> logger = UraLoggerFactory.getUraStringCodeLog();
        // または以下
        //UraLogger<String> logger = new UraStringCodeLog(UraLoggerFactory.getLogger());
        // ログ出力例
        logger.log("D001 デバッグログ");
        logger.log("E001 エラーログ");
        logger.log("I001 インフォログ");
        logger.log("W001 警告ログ");
    }
}
