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
 * $Id: TestDateFormat.java$
 */
package org.uranoplums.typical.util.time;

import java.util.List;
import java.util.Locale;


/**
 * 新規にフォーマットパターンを追加する場合のクラス。<br>
 *
 * @since 2015/07/22
 * @author syany
 */
public class TestDateFormat extends UraDateFormat {

    /**  */
//    private static final long serialVersionUID = 1657494165739636925L;

    /**
     * デフォルトコンストラクタ。<br>
     * @param string
     */
    public TestDateFormat(String string) {
        super(string);
    }

    /*
     * (非 Javadoc)
     *
     * @see org.uranoplums.typical.util.time.UraDateFormat#initialize()
     */
    @Override
    protected void initialize() {
        super.initialize();
        this.optionalFormatMap.put("b", new UraDateFormat.PrinterField() {

            @Override
            public String getFormatField(String pattern, UraCalendar cal, Locale locale) {
                return String.valueOf(cal.getMonth());
            }
        });
        this.optionalParserMap.put("b", new UraDateFormat.NumberParserField(UraCalendar.MONTH, "b") {

            @Override
            protected int modify(int iValue) {
                return iValue - 1;
            }
        });
    }

    /*
     * (非 Javadoc)
     *
     * @see org.uranoplums.typical.util.time.UraDateFormat#visitOptionalPattern(java.util.List)
     */
    @Override
    protected void visitOptionalPattern(List<String> optionalPatternList) {
        // フォーマット許容パターンに、新しいパターン"b"を追加する
        optionalPatternList.add("b");
    }
}
