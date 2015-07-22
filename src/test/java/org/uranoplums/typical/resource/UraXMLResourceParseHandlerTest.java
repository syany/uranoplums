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
 * $Id: UraXMLResourceParseHandlerTest.java$
 */
package org.uranoplums.typical.resource;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.junit.After;
import org.junit.Before;

/**
 * UraXMLResourceParseHandlerTestクラス。<br>
 *
 * @since 2015/06/15
 * @author syany
 */
public class UraXMLResourceParseHandlerTest {

    private UraXMLResourceParseHandler uraXMLResourceParseHandler;
    private SAXParser parser;
    private static final String TEST_001 = "test_001.xml";

    /**
     * 。<br>
     * @throws java.lang.Exception
     */
    @Before
    public void setUp() throws Exception {
        uraXMLResourceParseHandler = new UraXMLResourceParseHandler();
        parser = SAXParserFactory.newInstance().newSAXParser();
    }

    /**
     * 。<br>
     * @throws java.lang.Exception
     */
    @After
    public void tearDown() throws Exception {}

    /**
     * {@link org.uranoplums.typical.resource.UraXMLResourceParseHandler#UraXMLResourceParseHandler()} のためのテスト・メソッド。
     * @throws IOException
     * @throws SAXException
     */
//    @Test (expected = SAXException.class)
//    public final void testUraXMLResourceParseHandler() throws SAXException {
//        InputStream is = null;
//        ClassLoader classLoader = UraClassUtils.getCurrentClassLoader(this.getClass());
//        is = classLoader.getResourceAsStream(TEST_001);
//        if (is == null) {
//            return;
//        }
//        try {
//            parser.parse(is, uraXMLResourceParseHandler);
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        Map<String, Object> m = uraXMLResourceParseHandler.getParsedMap();
//        //fail("まだ実装されていません" + m.toString()); // TODO
//        assertTrue(true);
//    }
}
