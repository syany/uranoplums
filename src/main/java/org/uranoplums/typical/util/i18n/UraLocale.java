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
 * $Id: UraLocale.java$
 */
package org.uranoplums.typical.util.i18n;

import java.util.Locale;

import org.uranoplums.typical.util.UraStringUtils;
import org.uranoplums.typical.util.UraUtils;


/**
 * UraLocaleクラス。<br>
 *
 * @since 2015/10/02
 * @author syany
 */
public class UraLocale extends UraUtils{
    public static final Locale ENGLISH = Locale.ENGLISH;
    public static final Locale FRENCH = Locale.FRENCH;
    public static final Locale GERMAN = Locale.GERMAN;
    public static final Locale ITALIAN = Locale.ITALIAN;
    public static final Locale JAPANESE = Locale.JAPANESE;
    public static final Locale KOREAN = Locale.KOREAN;
    public static final Locale CHINESE = Locale.CHINESE;
    public static final Locale SIMPLIFIED_CHINESE = Locale.SIMPLIFIED_CHINESE;
    public static final Locale TRADITIONAL_CHINESE = Locale.TRADITIONAL_CHINESE;
    public static final Locale FRANCE = Locale.FRANCE;
    public static final Locale GERMANY = Locale.GERMANY;
    public static final Locale ITALY = Locale.ITALY;
    public static final Locale JAPAN = Locale.JAPAN;
    public static final Locale KOREA = Locale.KOREA;
    public static final Locale CHINA = Locale.CHINA;
    public static final Locale PRC = Locale.PRC;
    public static final Locale TAIWAN = Locale.TAIWAN;
    public static final Locale UK = Locale.UK;
    public static final Locale US = Locale.US;
    public static final Locale CANADA = Locale.CANADA;
    public static final Locale CANADA_FRENCH = Locale.CANADA_FRENCH;

    private static final Locale getLocale(String language, String country, String variant) {
        return new Locale(language, country, variant);
    }

    private static final Locale getLocale(String language, String ...params) {
        int size = params.length;
        if (size > 1) {
            return getLocale(language, params[0], params[1]);
        } else if (size > 0) {
            return getLocale(language, params[0], UraStringUtils.EMPTY);
        }
        return getLocale(language, UraStringUtils.EMPTY, UraStringUtils.EMPTY);
    }

    public static final Locale getDefault() {
        return Locale.getDefault();
    }

    public static final String[] getISOCountries() {
        return Locale.getISOCountries();
    }

    public static final String[] getISOLanguages() {
        return Locale.getISOLanguages();
    }

    public static final Locale[] getAvailableLocales() {
        return Locale.getAvailableLocales();
    }

    public static final void setDefault(Locale loc) {
        Locale.setDefault(loc);
    }

    public static final Locale arabic(String ...params) {
        return getLocale("ar", params);
    }

    public static final Locale belarusian(String ...params) {
        return getLocale("be", params);
    }

    public static final Locale bulgarian(String ...params) {
        return getLocale("bg", params);
    }

    public static final Locale catalan(String ...params) {
        return getLocale("ca", params);
    }

    public static final Locale czech(String ...params) {
        return getLocale("cs", params);
    }

    public static final Locale danish(String ...params) {
        return getLocale("da", params);
    }

    public static final Locale german(String ...params) {
        return getLocale("de", params);
    }

    public static final Locale greek(String ...params) {
        return getLocale("el", params);
    }

    public static final Locale english(String ...params) {
        return getLocale("en", params);
    }

    public static final Locale spanish(String ...params) {
        return getLocale("es", params);
    }

    public static final Locale estonian(String ...params) {
        return getLocale("et", params);
    }

    public static final Locale finnish(String ...params) {
        return getLocale("fi", params);
    }

    public static final Locale french(String ...params) {
        return getLocale("fr", params);
    }

    public static final Locale irish(String ...params) {
        return getLocale("ga", params);
    }

    public static final Locale india() {
        return getLocale("hi", "IN");
    }

    public static final Locale croatian(String ...params) {
        return getLocale("hr", params);
    }

    public static final Locale hungarian(String ...params) {
        return getLocale("hu", params);
    }

    public static final Locale indonesian(String ...params) {
        return getLocale("in", params);
    }

    public static final Locale icelandic(String ...params) {
        return getLocale("is", params);
    }

    public static final Locale italian(String ...params) {
        return getLocale("it", params);
    }

    public static final Locale iebrew(String ...params) {
        return getLocale("iw", params);
    }

    public static final Locale japanese(String ...params) {
        return getLocale("ja", params);
    }

    public static final Locale korean(String ...params) {
        return getLocale("ko", params);
    }

    public static final Locale lithuanian(String ...params) {
        return getLocale("lt", params);
    }

    public static final Locale latvian(String ...params) {
        return getLocale("lv", params);
    }

    public static final Locale macedonian(String ...params) {
        return getLocale("mk", params);
    }

    public static final Locale malay(String ...params) {
        return getLocale("ms", params);
    }

    public static final Locale maltese(String ...params) {
        return getLocale("mt", params);
    }

    public static final Locale dutch(String ...params) {
        return getLocale("nl", params);
    }

    public static final Locale norwegian(String ...params) {
        return getLocale("no", params);
    }

    public static final Locale polish(String ...params) {
        return getLocale("pl", params);
    }

    public static final Locale portuguese(String ...params) {
        return getLocale("pt", params);
    }

    public static final Locale romanian(String ...params) {
        return getLocale("ro", params);
    }

    public static final Locale russian(String ...params) {
        return getLocale("ru", params);
    }

    public static final Locale slovak(String ...params) {
        return getLocale("sk", params);
    }

    public static final Locale slovenian(String ...params) {
        return getLocale("sl", params);
    }

    public static final Locale albanian(String ...params) {
        return getLocale("sq", params);
    }

    public static final Locale serbian(String ...params) {
        return getLocale("sr", params);
    }

    public static final Locale swedish(String ...params) {
        return getLocale("sv", params);
    }

    public static final Locale thai(String ...params) {
        return getLocale("th", params);
    }

    public static final Locale turkish(String ...params) {
        return getLocale("tr", params);
    }

    public static final Locale ukrainian(String ...params) {
        return getLocale("uk", params);
    }

    public static final Locale vietnamese(String ...params) {
        return getLocale("vi", params);
    }

    public static final Locale chinese(String ...params) {
        return getLocale("zh", params);
    }
}
