/*
 * Copyright 2013-2014 the Uranoplums Foundation and the Others.
 * Copyright 2004-2014 the Seasar Foundation and the Others.
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
 * $Id: UraJaStringUtils.java$
 */
package org.uranoplums.typical.util;

import java.io.UnsupportedEncodingException;
import java.nio.charset.Charset;
import java.util.Iterator;
import java.util.Locale;

import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.StringUtils;
import org.uranoplums.typical.util.i18n.UraCharset;

/**
 * @since 2014/01/23
 * @author syany
 */
public class UraStringUtils {

    /**  */
    public static final String AT_MARK = "@";
    /**  */
    public static final String COMMA = ",";
    /**  */
    public static final String EMPTY = org.apache.commons.lang3.StringUtils.EMPTY;
    /**  */
    public static final String[] EMPTY_STRINGS = ArrayUtils.EMPTY_STRING_ARRAY;
    /**  */
    public static final int INDEX_NOT_FOUND = org.apache.commons.lang3.StringUtils.INDEX_NOT_FOUND;
    /**  */
    public static final String TAB = "\t";
    /**  */
    public static final String W_SPACE = " ";
    /**  */
    public static final String SLASH = "/";
    /**
     * 実行環境のOSで用いられる改行コードを取得する。
     */
    public static final String LINE_SEP = System.getProperty("line.separator");

    /**
     * 。<br>
     * @param str
     * @param maxWidth
     * @return
     */
    public static String abbreviate(String str, int maxWidth) {
        return org.apache.commons.lang3.StringUtils.abbreviate(str, maxWidth);
    }

    public static String abbreviate(String str, int offset, int maxWidth) {
        return org.apache.commons.lang3.StringUtils.abbreviate(str, offset, maxWidth);
    }

    public static String abbreviateMiddle(String str, String middle, int length) {
        return org.apache.commons.lang3.StringUtils.abbreviateMiddle(str, middle, length);
    }

    /**
     * 文字列に、数値を16進数に変換した文字列を追加します。
     * @param buf
     *            追加先の文字列
     * @param i
     *            数値
     */
    public static void appendHex(final StringBuffer buf, final byte i) {
        buf.append(Character.forDigit((i & 0xf0) >> 4, 16));
        buf.append(Character.forDigit((i & 0x0f), 16));
    }

    /**
     * 文字列に、数値を16進数に変換した文字列を追加します。
     * @param buf
     *            追加先の文字列
     * @param i
     *            数値
     */
    public static void appendHex(final StringBuffer buf, final int i) {
        buf.append(Integer.toHexString((i >> 24) & 0xff));
        buf.append(Integer.toHexString((i >> 16) & 0xff));
        buf.append(Integer.toHexString((i >> 8) & 0xff));
        buf.append(Integer.toHexString(i & 0xff));
    }

    public static String appendIfMissing(String str, CharSequence suffix, CharSequence... suffixes)
    {
        return org.apache.commons.lang3.StringUtils.appendIfMissing(str, suffix, suffixes);
    }

    public static String appendIfMissingIgnoreCase(String str, CharSequence suffix, CharSequence... suffixes)
    {
        return org.apache.commons.lang3.StringUtils.appendIfMissingIgnoreCase(str, suffix, suffixes);
    }

    /**
     * _記法をキャメル記法に変換します。
     * @param s
     *            テキスト
     * @return 結果の文字列
     */
    public static String camelize(String s) {
        if (s == null) {
            return null;
        }
        s = s.toLowerCase();
        String[] array = UraStringUtils.split(s, "_");
        if (array.length == 1) {
            return UraStringUtils.capitalize(s);
        }
        StringBuffer buf = new StringBuffer(40);
        for (int i = 0; i < array.length; ++i) {
            buf.append(UraStringUtils.capitalize(array[i]));
        }
        return buf.toString();
    }

    public static String capitalize(String str) {
        return org.apache.commons.lang3.StringUtils.capitalize(str);
    }

    public static String center(String str, int size) {
        return org.apache.commons.lang3.StringUtils.center(str, size);
    }

    public static String center(String str, int size, char padChar) {
        return org.apache.commons.lang3.StringUtils.center(str, size, padChar);
    }

    public static String center(String str, int size, String padChar) {
        return org.apache.commons.lang3.StringUtils.center(str, size, padChar);
    }

    public static String chomp(String str) {
        return org.apache.commons.lang3.StringUtils.chomp(str);
    }

    public static String chomp(String str, String separator) {
        return org.apache.commons.lang3.StringUtils.chomp(str, separator);
    }

    public static String chop(String str) {
        return org.apache.commons.lang3.StringUtils.chop(str);
    }

    public static boolean contains(CharSequence str, char searchChar) {
        return org.apache.commons.lang3.StringUtils.contains(str, searchChar);
    }

    public static boolean contains(CharSequence str, CharSequence searchStr) {
        return org.apache.commons.lang3.StringUtils.contains(str, searchStr);
    }

    public static boolean containsAny(CharSequence str, char[] searchChars) {
        return org.apache.commons.lang3.StringUtils.containsAny(str, searchChars);
    }

    public static boolean containsAny(CharSequence str, CharSequence searchChars) {
        return org.apache.commons.lang3.StringUtils.containsAny(str, searchChars);
    }

    public static boolean containsIgnoreCase(CharSequence str, CharSequence searchStr) {
        return org.apache.commons.lang3.StringUtils.containsIgnoreCase(str, searchStr);
    }

    public static boolean containsNone(CharSequence str, char... searchChars) {
        return org.apache.commons.lang3.StringUtils.containsNone(str, searchChars);
    }

    public static boolean containsNone(CharSequence str, String invalidChars) {
        return org.apache.commons.lang3.StringUtils.containsNone(str, invalidChars);
    }

    public static boolean containsOnly(CharSequence str, char... valid) {
        return org.apache.commons.lang3.StringUtils.containsOnly(str, valid);
    }

    public static boolean containsOnly(CharSequence str, String validChars) {
        return org.apache.commons.lang3.StringUtils.containsOnly(str, validChars);
    }

    public static int countMatches(CharSequence str, CharSequence sub) {
        return org.apache.commons.lang3.StringUtils.countMatches(str, sub);
    }

    /**
     * キャメル記法を_記法に変換します。
     * @param s
     *            テキスト
     * @return 結果の文字列
     */
    public static String decamelize(final String s) {
        if (s == null) {
            return null;
        }
        if (s.length() == 1) {
            return s.toUpperCase();
        }
        StringBuffer buf = new StringBuffer(40);
        int pos = 0;
        for (int i = 1; i < s.length(); ++i) {
            if (Character.isUpperCase(s.charAt(i))) {
                if (buf.length() != 0) {
                    buf.append('_');
                }
                buf.append(s.substring(pos, i).toUpperCase());
                pos = i;
            }
        }
        if (buf.length() != 0) {
            buf.append('_');
        }
        buf.append(s.substring(pos, s.length()).toUpperCase());
        return buf.toString();
    }

    /**
     * JavaBeansの仕様にしたがってデキャピタライズを行ないます。大文字が2つ以上続く場合は、小文字にならないので注意してください。
     * @param name
     *            名前
     * @return 結果の文字列
     */
    public static String decapitalize(final String name) {
        if (isEmpty(name)) {
            return name;
        }
        char chars[] = name.toCharArray();
        if (chars.length >= 2 && Character.isUpperCase(chars[0])
                && Character.isUpperCase(chars[1])) {
            return name;
        }
        chars[0] = Character.toLowerCase(chars[0]);
        return new String(chars);
    }

    public static <T extends CharSequence> T defaultIfBlank(T str, T defaultStr) {
        return org.apache.commons.lang3.StringUtils.defaultIfBlank(str, defaultStr);
    }

    public static <T extends CharSequence> T defaultIfEmpty(T str, T defaultStr) {
        return org.apache.commons.lang3.StringUtils.defaultIfEmpty(str, defaultStr);
    }

    public static String defaultString(String str) {
        return org.apache.commons.lang3.StringUtils.defaultString(str);
    }

    public static String defaultString(String str, String defaultStr) {
        return org.apache.commons.lang3.StringUtils.defaultString(str, defaultStr);
    }

    public static String deleteWhitespace(String str) {
        return org.apache.commons.lang3.StringUtils.deleteWhitespace(str);
    }

    public static String difference(String str1, String str2) {
        return org.apache.commons.lang3.StringUtils.difference(str1, str2);
    }

    public static boolean endsWith(CharSequence str, CharSequence suffix) {
        return org.apache.commons.lang3.StringUtils.endsWith(str, suffix);
    }

    public static boolean endsWithAny(CharSequence string, CharSequence... searchStrings) {
        return org.apache.commons.lang3.StringUtils.endsWithAny(string, searchStrings);
    }

    public static boolean endsWithIgnoreCase(CharSequence str, CharSequence suffix) {
        return org.apache.commons.lang3.StringUtils.endsWithIgnoreCase(str, suffix);
    }

    public static boolean equals(CharSequence str1, CharSequence str2) {
        return org.apache.commons.lang3.StringUtils.equals(str1, str2);
    }

    public static boolean equalsIgnoreCase(CharSequence str1, CharSequence str2) {
        return org.apache.commons.lang3.StringUtils.equalsIgnoreCase(str1, str2);
    }

    /**
     * 指定された文字列のバイト列長を取得する。
     * 第二引数のエンコーディングでバイト列に変換するが、
     * エンコードが指定されていなかった場合はデフォルトのエンコーディングで
     * バイト列に変換を行う。
     * @param value
     *            バイト列長を取得する対象の文字列
     * @param encoding
     *            文字エンコーディング
     * @return バイト列長
     * @throws UnsupportedEncodingException
     *             サポートされていない
     *             エンコーディングを指定したとき発生する例外。
     */
    public static int getByteLength(String value, String encoding)
            throws UnsupportedEncodingException {
        if (value == null || EMPTY.equals(value)) {
            return 0;
        }
        byte[] bytes = null;
        if (encoding == null || EMPTY.equals(encoding)) {
            bytes = value.getBytes();
        } else {
            try {
                bytes = value.getBytes(encoding);
            } catch (UnsupportedEncodingException e) {
                throw e;
            }
        }
        return bytes == null ? 0 : bytes.length;
    }

    /**
     * Calls {@link String#getBytes(Charset)}
     * 
     * @param string
     *            The string to encode (if null, return null).
     * @param charset
     *            The {@link Charset} to encode the {@code String}
     * @return the encoded bytes
     */
    public static byte[] getBytes(final String string, final Charset charset) {
        if (isEmpty(string)) {
            return new byte[0];
        }
        return string.getBytes(charset);
    }

    /**
     * Encodes the given string into a sequence of bytes using the UTF-8 charset, storing the result into a new byte
     * array.
     * 
     * @param string
     *            the String to encode, may be {@code null}
     * @return encoded bytes, or {@code null} if the input string was {@code null}
     * @throws NullPointerException
     *             Thrown if {@link UraCharsets#UTF8} is not initialized, which should never happen since it is
     *             required by the Java platform specification.
     * @since As of 1.7, throws {@link NullPointerException} instead of UnsupportedEncodingException
     * @see <a href="http://download.oracle.com/javase/6/docs/api/java/nio/charset/Charset.html">Standard charsets</a>
     * @see #getBytesUnchecked(String, String)
     */
    public static byte[] getBytesUtf8(final String string) {
        return getBytes(string, UraCharset.UTF8);
    }

    public static String getCommonPrefix(String... strs) {
        return org.apache.commons.lang3.StringUtils.getCommonPrefix(strs);
    }

    public static int getLevenshteinDistance(CharSequence s, CharSequence t) {
        return org.apache.commons.lang3.StringUtils.getLevenshteinDistance(s, t);
    }

    /**
     * HTMLメタ文字列変換。
     * <p>
     * "&lt;"、"&gt;"、"&amp;"、"&quot;"、"&#39;"といった、HTML中に そのまま出力すると問題がある文字を
     * "&amp;lt;"、"&amp;gt;"、"&amp;amp;"、"&amp;quot;"、"&amp;#39;" に変換する。
     * </p>
     * <p>
     * nullが渡された場合はnullを返す。
     * </p
     * @param str
     *            変換する文字列
     * @return 変換後の文字列
     */
    public static String HTMLEscape(String str) {
        if (str == null) {
            return null;
        }
        char[] cbuf = str.toCharArray();
        StringBuilder sbui = new StringBuilder();
        for (int i = 0; i < cbuf.length; i++) {
            if (cbuf[i] == '&') {
                sbui.append("&amp;");
            } else if (cbuf[i] == '<') {
                sbui.append("&lt;");
            } else if (cbuf[i] == '>') {
                sbui.append("&gt;");
            } else if (cbuf[i] == '"') {
                sbui.append("&quot;");
            } else if (cbuf[i] == '\'') {
                sbui.append("&#39;");
            } else {
                sbui.append(cbuf[i]);
            }
        }
        return sbui.toString();
    }

    public static int indexOf(CharSequence str, char searchChar) {
        return org.apache.commons.lang3.StringUtils.indexOf(str, searchChar);
    }

    public static int indexOf(CharSequence str, char searchChar, int startPos) {
        return org.apache.commons.lang3.StringUtils.indexOf(str, searchChar, startPos);
    }

    public static int indexOf(CharSequence str, CharSequence searchStr) {
        return org.apache.commons.lang3.StringUtils.indexOf(str, searchStr);
    }

    public static int indexOf(CharSequence str, CharSequence searchStr, int startPos) {
        return org.apache.commons.lang3.StringUtils.indexOf(str, searchStr, startPos);
    }

    public static int indexOfAny(CharSequence str, char... searchChars) {
        return org.apache.commons.lang3.StringUtils.indexOfAny(str, searchChars);
    }

    public static int indexOfAny(CharSequence str, CharSequence searchChars) {
        return org.apache.commons.lang3.StringUtils.indexOfAny(str, searchChars);
    }

    public static int indexOfAny(CharSequence str, CharSequence... searchStrs) {
        return org.apache.commons.lang3.StringUtils.indexOfAny(str, searchStrs);
    }

    public static int indexOfAnyBut(CharSequence str, char... searchChars) {
        return org.apache.commons.lang3.StringUtils.indexOfAnyBut(str, searchChars);
    }

    public static int indexOfAnyBut(CharSequence str, CharSequence searchChars) {
        return org.apache.commons.lang3.StringUtils.indexOfAnyBut(str, searchChars);
    }

    public static int indexOfDifference(CharSequence... strs) {
        return org.apache.commons.lang3.StringUtils.indexOfDifference(strs);
    }

    public static int indexOfDifference(CharSequence str1, CharSequence str2) {
        return org.apache.commons.lang3.StringUtils.indexOfDifference(str1, str2);
    }

    public static int indexOfIgnoreCase(CharSequence str, CharSequence searchStr) {
        return org.apache.commons.lang3.StringUtils.indexOfIgnoreCase(str, searchStr);
    }

    public static int indexOfIgnoreCase(CharSequence str, CharSequence searchStr, int startPos) {
        return org.apache.commons.lang3.StringUtils.indexOfIgnoreCase(str, searchStr, startPos);
    }

    public static boolean isAllLowerCase(CharSequence str) {
        return org.apache.commons.lang3.StringUtils.isAllLowerCase(str);
    }

    public static boolean isAllUpperCase(CharSequence str) {
        return org.apache.commons.lang3.StringUtils.isAllUpperCase(str);
    }

    public static boolean isAlpha(CharSequence str) {
        return org.apache.commons.lang3.StringUtils.isAlpha(str);
    }

    public static boolean isAlphanumeric(CharSequence str) {
        return org.apache.commons.lang3.StringUtils.isAlphanumeric(str);
    }

    public static boolean isAlphanumericSpace(CharSequence str) {
        return org.apache.commons.lang3.StringUtils.isAlphanumericSpace(str);
    }

    public static boolean isAlphaSpace(CharSequence str) {
        return org.apache.commons.lang3.StringUtils.isAlphaSpace(str);
    }

    public static boolean isAnyBlank(CharSequence str) {
        return org.apache.commons.lang3.StringUtils.isAnyBlank(str);
    }

    public static boolean isAnyEmpty(CharSequence str) {
        return org.apache.commons.lang3.StringUtils.isAnyEmpty(str);
    }

    public static boolean isAsciiPrintable(CharSequence str) {
        return org.apache.commons.lang3.StringUtils.isAsciiPrintable(str);
    }

    public static boolean isBlank(CharSequence str) {
        return org.apache.commons.lang3.StringUtils.isBlank(str);
    }

    /*
     * @see org.apache.commons.lang3.StringUtils.isEmpty()
     */
    public static boolean isEmpty(CharSequence str) {
        return org.apache.commons.lang3.StringUtils.isEmpty(str);
    }

    public static boolean isNoneBlank(CharSequence... str) {
        return org.apache.commons.lang3.StringUtils.isNoneBlank(str);
    }

    public static boolean isNoneEmpty(CharSequence... str) {
        return org.apache.commons.lang3.StringUtils.isNoneEmpty(str);
    }

    public static boolean isNotBlank(CharSequence str) {
        return org.apache.commons.lang3.StringUtils.isNotBlank(str);
    }

    public static boolean isNotEmpty(CharSequence str) {
        return org.apache.commons.lang3.StringUtils.isNotEmpty(str);
    }

    /**
     * 文字列が数値のみで構成されているかどうかを返します。
     * @param s
     *            文字列
     * @return 数値のみで構成されている場合、<code>true</code>
     */
    public static boolean isNumber(final CharSequence s) {
        if (s == null || s.length() == 0) {
            return false;
        }
        int size = s.length();
        for (int i = 0; i < size; i++) {
            char chr = s.charAt(i);
            if (chr < '0' || '9' < chr) {
                return false;
            }
        }
        return true;
    }

    public static boolean isNumeric(CharSequence str) {
        return org.apache.commons.lang3.StringUtils.isNumeric(str);
    }

    public static boolean isNumericSpace(CharSequence str) {
        return org.apache.commons.lang3.StringUtils.isNumericSpace(str);
    }

    public static boolean isWhitespace(CharSequence str) {
        return org.apache.commons.lang3.StringUtils.isWhitespace(str);
    }

    public static String join(byte[] array, char separator) {
        return org.apache.commons.lang3.StringUtils.join(array, separator);
    }

    public static String join(byte[] array, char separator, int startIndex, int endIndex) {
        return org.apache.commons.lang3.StringUtils.join(array, separator, startIndex, endIndex);
    }

    public static String join(char[] array, char separator) {
        return org.apache.commons.lang3.StringUtils.join(array, separator);
    }

    public static String join(char[] array, char separator, int startIndex, int endIndex) {
        return org.apache.commons.lang3.StringUtils.join(array, separator, startIndex, endIndex);
    }

    public static String join(double[] array, char separator) {
        return org.apache.commons.lang3.StringUtils.join(array, separator);
    }

    public static String join(double[] array, char separator, int startIndex, int endIndex) {
        return org.apache.commons.lang3.StringUtils.join(array, separator, startIndex, endIndex);
    }

    public static String join(float[] array, char separator) {
        return org.apache.commons.lang3.StringUtils.join(array, separator);
    }

    public static String join(float[] array, char separator, int startIndex, int endIndex) {
        return org.apache.commons.lang3.StringUtils.join(array, separator, startIndex, endIndex);
    }

    public static String join(int[] array, char separator) {
        return org.apache.commons.lang3.StringUtils.join(array, separator);
    }

    public static String join(int[] array, char separator, int startIndex, int endIndex) {
        return org.apache.commons.lang3.StringUtils.join(array, separator, startIndex, endIndex);
    }

    public static String join(Iterable<?> collection, char separator) {
        return org.apache.commons.lang3.StringUtils.join(collection, separator);
    }

    public static String join(Iterable<?> collection, String separator) {
        return org.apache.commons.lang3.StringUtils.join(collection, separator);
    }

    public static String join(Iterator<?> iterator, char separator) {
        return org.apache.commons.lang3.StringUtils.join(iterator, separator);
    }

    public static String join(Iterator<?> iterator, String separator) {
        return org.apache.commons.lang3.StringUtils.join(iterator, separator);
    }

    public static String join(long[] array, char separator) {
        return org.apache.commons.lang3.StringUtils.join(array, separator);
    }

    public static String join(long[] array, char separator, int startIndex, int endIndex) {
        return org.apache.commons.lang3.StringUtils.join(array, separator, startIndex, endIndex);
    }

    // public static String join(Object... array) {
    // return org.apache.commons.lang3.StringUtils.join(array);
    // }
    public static String join(Object[] array, char separator) {
        return org.apache.commons.lang3.StringUtils.join(array, separator);
    }

    public static String join(Object[] array, char separator, int startIndex, int endIndex) {
        return org.apache.commons.lang3.StringUtils.join(array, separator, startIndex, endIndex);
    }

    public static String join(Object[] array, String separator) {
        return org.apache.commons.lang3.StringUtils.join(array, separator);
    }

    public static String join(Object[] array, String separator, int startIndex, int endIndex) {
        return org.apache.commons.lang3.StringUtils.join(array, separator, startIndex, endIndex);
    }

    public static String join(short[] array, char separator) {
        return org.apache.commons.lang3.StringUtils.join(array, separator);
    }

    public static String join(short[] array, char separator, int startIndex, int endIndex) {
        return org.apache.commons.lang3.StringUtils.join(array, separator, startIndex, endIndex);
    }

    public static <T> String join(T... elements) {
        return org.apache.commons.lang3.StringUtils.join(elements);
    }

    public static int lastIndexOf(CharSequence str, char searchChar) {
        return org.apache.commons.lang3.StringUtils.lastIndexOf(str, searchChar);
    }

    public static int lastIndexOf(CharSequence str, char searchChar, int startPos) {
        return org.apache.commons.lang3.StringUtils.lastIndexOf(str, searchChar, startPos);
    }

    public static int lastIndexOf(CharSequence str, CharSequence searchStr) {
        return org.apache.commons.lang3.StringUtils.lastIndexOf(str, searchStr);
    }

    public static int lastIndexOf(CharSequence str, CharSequence searchStr, int startPos) {
        return org.apache.commons.lang3.StringUtils.lastIndexOf(str, searchStr, startPos);
    }

    public static int lastIndexOfAny(CharSequence str, CharSequence... searchStrs) {
        return org.apache.commons.lang3.StringUtils.lastIndexOfAny(str, searchStrs);
    }

    public static int lastIndexOfIgnoreCase(CharSequence str, CharSequence searchStr) {
        return org.apache.commons.lang3.StringUtils.lastIndexOfIgnoreCase(str, searchStr);
    }

    public static int lastIndexOfIgnoreCase(CharSequence str, CharSequence searchStr, int startPos) {
        return org.apache.commons.lang3.StringUtils.lastIndexOfIgnoreCase(str, searchStr, startPos);
    }

    public static int lastOrdinalIndexOf(CharSequence str, CharSequence searchStr, int ordinal) {
        return org.apache.commons.lang3.StringUtils.lastOrdinalIndexOf(str, searchStr, ordinal);
    }

    public static String left(String str, int len) {
        return org.apache.commons.lang3.StringUtils.left(str, len);
    }

    public static String leftPad(String str, int size) {
        return org.apache.commons.lang3.StringUtils.leftPad(str, size);
    }

    public static String leftPad(String str, int size, char padChar) {
        return org.apache.commons.lang3.StringUtils.leftPad(str, size, padChar);
    }

    public static String leftPad(String str, int size, String padStr) {
        return org.apache.commons.lang3.StringUtils.leftPad(str, size, padStr);
    }

    public static int length(CharSequence str) {
        return org.apache.commons.lang3.StringUtils.length(str);
    }

    public static String lowerCase(String str) {
        return org.apache.commons.lang3.StringUtils.lowerCase(str);
    }

    public static String lowerCase(String str, Locale locale) {
        return org.apache.commons.lang3.StringUtils.lowerCase(str, locale);
    }

    /**
     * 左側の空白を削ります。
     * @param text
     *            テキスト
     * @return 結果の文字列
     */
    public static final String ltrim(final String text) {
        return ltrim(text, null);
    }

    /**
     * 左側の指定した文字列を削ります。
     * @param text
     *            テキスト
     * @param trimText
     *            削るテキスト
     * @return 結果の文字列
     */
    public static final String ltrim(final String text, String trimText) {
        if (text == null) {
            return null;
        }
        if (trimText == null) {
            trimText = " ";
        }
        int pos = 0;
        for (; pos < text.length(); pos++) {
            if (trimText.indexOf(text.charAt(pos)) < 0) {
                break;
            }
        }
        return text.substring(pos);
    }

    public static String mid(String str, int pos, int len) {
        return org.apache.commons.lang3.StringUtils.mid(str, pos, len);
    }

    public static String normalizeSpace(String str) {
        return org.apache.commons.lang3.StringUtils.normalizeSpace(str);
    }

    public static int ordinalIndexOf(CharSequence str, CharSequence searchStr, int ordinal)
    {
        return org.apache.commons.lang3.StringUtils.ordinalIndexOf(str, searchStr, ordinal);
    }

    public static String overlay(String str, String overlay, int start, int end) {
        return org.apache.commons.lang3.StringUtils.overlay(str, overlay, start, end);
    }

    public static String prependIfMissing(String str, CharSequence prefix, CharSequence... prefixes) {
        return org.apache.commons.lang3.StringUtils.prependIfMissing(str, prefix, prefixes);
    }

    public static String prependIfMissingIgnoreCase(String str, CharSequence prefix, CharSequence... prefixes) {
        return org.apache.commons.lang3.StringUtils.prependIfMissingIgnoreCase(str, prefix, prefixes);
    }

    public static String remove(String str, char remove) {
        return org.apache.commons.lang3.StringUtils.remove(str, remove);
    }

    public static String remove(String str, String remove) {
        return org.apache.commons.lang3.StringUtils.remove(str, remove);
    }

    public static String removeEnd(String str, String remove) {
        return org.apache.commons.lang3.StringUtils.removeEnd(str, remove);
    }

    public static String removeEndIgnoreCase(String str, String remove) {
        return org.apache.commons.lang3.StringUtils.removeEndIgnoreCase(str, remove);
    }

    public static String removePattern(String source, String regex) {
        return org.apache.commons.lang3.StringUtils.removePattern(source, regex);
    }

    public static String removeStart(String str, String remove) {
        return org.apache.commons.lang3.StringUtils.removeStart(str, remove);
    }

    public static String removeStartIgnoreCase(String str, String remove) {
        return org.apache.commons.lang3.StringUtils.removeStartIgnoreCase(str, remove);
    }

    /**
     * 。<br>
     * @param ch
     * @param repeat
     * @return
     */
    public static String repeat(char ch, int repeat) {
        return org.apache.commons.lang3.StringUtils.repeat(ch, repeat);
    }

    /**
     * 。<br>
     * @param str
     * @param repeat
     * @return
     */
    public static String repeat(String str, int repeat) {
        return org.apache.commons.lang3.StringUtils.repeat(str, repeat);
    }

    /**
     * 。<br>
     * @param str
     * @param separator
     * @param repeat
     * @return
     */
    public static String repeat(String str, String separator, int repeat) {
        return org.apache.commons.lang3.StringUtils.repeat(str, separator, repeat);
    }

    /**
     * 。<br>
     * @param text
     * @param searchString
     * @param replacement
     * @return
     */
    public static String replace(String text, String searchString, String replacement) {
        return org.apache.commons.lang3.StringUtils.replace(text, searchString, replacement);
    }

    /**
     * 。<br>
     * @param text
     * @param searchString
     * @param replacement
     * @param max
     * @return
     */
    public static String replace(String text, String searchString, String replacement, int max) {
        return org.apache.commons.lang3.StringUtils.replace(text, searchString, replacement, max);
    }

    /**
     * 。<br>
     * @param str
     * @param searchChar
     * @param replaceChar
     * @return
     */
    public static String replaceChars(String str, char searchChar, char replaceChar) {
        return org.apache.commons.lang3.StringUtils.replaceChars(str, searchChar, replaceChar);
    }

    /**
     * 。<br>
     * @param str
     * @param searchChars
     * @param replaceChars
     * @return
     */
    public static String replaceChars(String str, String searchChars, String replaceChars) {
        return org.apache.commons.lang3.StringUtils.replaceChars(str, searchChars, replaceChars);
    }

    /**
     * 。<br>
     * @param text
     * @param searchList
     * @param replacementList
     * @return
     */
    public static String replaceEach(String text, String[] searchList, String... replacementList) {
        return org.apache.commons.lang3.StringUtils.replaceEach(text, searchList, replacementList);
    }

    /**
     * 。<br>
     * @param text
     * @param searchList
     * @param replacementList
     * @return
     */
    public static String replaceEachRepeatedly(String text, String[] searchList, String... replacementList) {
        return org.apache.commons.lang3.StringUtils.replaceEachRepeatedly(text, searchList, replacementList);
    }

    /**
     * 。<br>
     * @param text
     * @param searchString
     * @param replacement
     * @return
     */
    public static String replaceOnce(String text, String searchString, String replacement) {
        return org.apache.commons.lang3.StringUtils.replaceOnce(text, searchString, replacement);
    }

    /**
     * 。<br>
     * @param source
     * @param regex
     * @param replacement
     * @return
     */
    public static String replacePattern(String source, String regex, String replacement) {
        return org.apache.commons.lang3.StringUtils.replacePattern(source, regex, replacement);
    }

    /**
     * 。<br>
     * @param str
     * @return
     */
    public static String reverse(String str) {
        return org.apache.commons.lang3.StringUtils.reverse(str);
    }

    /**
     * 。<br>
     * @param str
     * @param separatorChar
     * @return
     */
    public static String reverseDelimited(String str, char separatorChar) {
        return org.apache.commons.lang3.StringUtils.reverseDelimited(str, separatorChar);
    }

    /**
     * 。<br>
     * @param str
     * @param len
     * @return
     */
    public static String right(String str, int len) {
        return org.apache.commons.lang3.StringUtils.right(str, len);
    }

    /**
     * 。<br>
     * @param str
     * @param size
     * @return
     */
    public static String rightPad(String str, int size) {
        return org.apache.commons.lang3.StringUtils.rightPad(str, size);
    }

    /**
     * 。<br>
     * @param str
     * @param size
     * @param padChar
     * @return
     */
    public static String rightPad(String str, int size, char padChar) {
        return org.apache.commons.lang3.StringUtils.rightPad(str, size, padChar);
    }

    /**
     * 。<br>
     * @param str
     * @param size
     * @param padStr
     * @return
     */
    public static String rightPad(String str, int size, String padStr) {
        return org.apache.commons.lang3.StringUtils.rightPad(str, size, padStr);
    }

    /**
     * 右側の空白を削ります。
     * @param text
     *            テキスト
     * @return 結果の文字列
     */
    public static final String rtrim(final String text) {
        return rtrim(text, null);
    }

    /**
     * 右側の指定した文字列を削ります。
     * @param text
     *            テキスト
     * @param trimText
     *            削る文字列
     * @return 結果の文字列
     */
    public static final String rtrim(final String text, String trimText) {
        if (text == null) {
            return null;
        }
        if (trimText == null) {
            trimText = " ";
        }
        int pos = text.length() - 1;
        for (; pos >= 0; pos--) {
            if (trimText.indexOf(text.charAt(pos)) < 0) {
                break;
            }
        }
        return text.substring(0, pos + 1);
    }

    /**
     * 。<br>
     * @param str
     * @return
     */
    public static String[] split(String str) {
        return org.apache.commons.lang3.StringUtils.split(str);
    }

    /**
     * 。<br>
     * @param str
     * @param separatorChar
     * @return
     */
    public static String[] split(String str, char separatorChar) {
        return org.apache.commons.lang3.StringUtils.split(str, separatorChar);
    }

    /**
     * 。<br>
     * @param str
     * @param separatorChars
     * @return
     */
    public static String[] split(String str, String separatorChars) {
        return org.apache.commons.lang3.StringUtils.split(str, separatorChars);
    }

    /**
     * 。<br>
     * @param str
     * @param separatorChars
     * @param max
     * @return
     */
    public static String[] split(String str, String separatorChars, int max) {
        return org.apache.commons.lang3.StringUtils.split(str, separatorChars, max);
    }

    /**
     * 。<br>
     * @param str
     * @return
     */
    public static String[] splitByCharacterType(String str) {
        return org.apache.commons.lang3.StringUtils.splitByCharacterType(str);
    }

    /**
     * 。<br>
     * @param str
     * @return
     */
    public static String[] splitByCharacterTypeCamelCase(String str) {
        return org.apache.commons.lang3.StringUtils.splitByCharacterTypeCamelCase(str);
    }

    /**
     * 。<br>
     * @param str
     * @param separator
     * @return
     */
    public static String[] splitByWholeSeparator(String str, String separator) {
        return org.apache.commons.lang3.StringUtils.splitByWholeSeparator(str, separator);
    }

    /**
     * 。<br>
     * @param str
     * @param separator
     * @param max
     * @return
     */
    public static String[] splitByWholeSeparator(String str, String separator, int max) {
        return org.apache.commons.lang3.StringUtils.splitByWholeSeparator(str, separator, max);
    }

    /**
     * 。<br>
     * @param str
     * @param separator
     * @return
     */
    public static String[] splitByWholeSeparatorPreserveAllTokens(String str, String separator) {
        return org.apache.commons.lang3.StringUtils.splitByWholeSeparatorPreserveAllTokens(str, separator);
    }

    /**
     * 。<br>
     * @param str
     * @param separator
     * @param max
     * @return
     */
    public static String[] splitByWholeSeparatorPreserveAllTokens(String str, String separator, int max) {
        return org.apache.commons.lang3.StringUtils.splitByWholeSeparatorPreserveAllTokens(str, separator, max);
    }

    /**
     * 。<br>
     * @param str
     * @return
     */
    public static String[] splitPreserveAllTokens(String str) {
        return org.apache.commons.lang3.StringUtils.splitPreserveAllTokens(str);
    }

    /**
     * 。<br>
     * @param str
     * @param separatorChar
     * @return
     */
    public static String[] splitPreserveAllTokens(String str, char separatorChar) {
        return org.apache.commons.lang3.StringUtils.splitPreserveAllTokens(str, separatorChar);
    }

    /**
     * 。<br>
     * @param str
     * @param separatorChars
     * @return
     */
    public static String[] splitPreserveAllTokens(String str, String separatorChars) {
        return org.apache.commons.lang3.StringUtils.splitPreserveAllTokens(str, separatorChars);
    }

    /**
     * 。<br>
     * @param str
     * @param separatorChars
     * @param max
     * @return
     */
    public static String[] splitPreserveAllTokens(String str, String separatorChars, int max) {
        return org.apache.commons.lang3.StringUtils.splitPreserveAllTokens(str, separatorChars, max);
    }

    /**
     * 。<br>
     * @param str
     * @param prefix
     * @return
     */
    public static boolean startsWith(CharSequence str, CharSequence prefix) {
        return org.apache.commons.lang3.StringUtils.startsWith(str, prefix);
    }

    /**
     * 。<br>
     * @param string
     * @param searchStrings
     * @return
     */
    public static boolean startsWithAny(CharSequence string, CharSequence... searchStrings) {
        return org.apache.commons.lang3.StringUtils.startsWithAny(string, searchStrings);
    }

    /**
     * 。<br>
     * @param str
     * @param prefix
     * @return
     */
    public static boolean startsWithIgnoreCase(CharSequence str, CharSequence prefix) {
        return org.apache.commons.lang3.StringUtils.startsWithIgnoreCase(str, prefix);
    }

    /**
     * 。<br>
     * @param str
     * @return
     */
    public static String strip(String str) {
        return org.apache.commons.lang3.StringUtils.strip(str);
    }

    /**
     * 。<br>
     * @param str
     * @param stripChars
     * @return
     */
    public static String strip(String str, String stripChars) {
        return org.apache.commons.lang3.StringUtils.strip(str, stripChars);
    }

    /**
     * 。<br>
     * @param strs
     * @return
     */
    public static String[] stripAll(String... strs) {
        return org.apache.commons.lang3.StringUtils.stripAll(strs);
    }

    /**
     * 。<br>
     * @param strs
     * @param stripChars
     * @return
     */
    public static String[] stripAll(String[] strs, String stripChars) {
        return org.apache.commons.lang3.StringUtils.stripAll(strs, stripChars);
    }

    /**
     * 。<br>
     * @param str
     * @param stripChars
     * @return
     */
    public static String stripEnd(String str, String stripChars) {
        return org.apache.commons.lang3.StringUtils.stripEnd(str, stripChars);
    }

    /**
     * 。<br>
     * @param str
     * @param stripChars
     * @return
     */
    public static String stripStart(String str, String stripChars) {
        return org.apache.commons.lang3.StringUtils.stripStart(str, stripChars);
    }

    /**
     * 。<br>
     * @param str
     * @return
     */
    public static String stripToEmpty(String str) {
        return org.apache.commons.lang3.StringUtils.stripToEmpty(str);
    }

    /**
     * 。<br>
     * @param str
     * @return
     */
    public static String stripToNull(String str) {
        return org.apache.commons.lang3.StringUtils.stripToNull(str);
    }

    /**
     * 。<br>
     * @param str
     * @param start
     * @return
     */
    public static String substring(String str, int start) {
        return org.apache.commons.lang3.StringUtils.substring(str, start);
    }

    /**
     * 。<br>
     * @param str
     * @param start
     * @param end
     * @return
     */
    public static String substring(String str, int start, int end) {
        return org.apache.commons.lang3.StringUtils.substring(str, start, end);
    }

    /**
     * 。<br>
     * @param str
     * @param separator
     * @return
     */
    public static String substringAfter(String str, String separator) {
        return org.apache.commons.lang3.StringUtils.substringAfter(str, separator);
    }

    /**
     * 。<br>
     * @param str
     * @param separator
     * @return
     */
    public static String substringAfterLast(String str, String separator) {
        return org.apache.commons.lang3.StringUtils.substringAfterLast(str, separator);
    }

    /**
     * 。<br>
     * @param str
     * @param separator
     * @return
     */
    public static String substringBefore(String str, String separator) {
        return org.apache.commons.lang3.StringUtils.substringBefore(str, separator);
    }

    /**
     * 。<br>
     * @param str
     * @param separator
     * @return
     */
    public static String substringBeforeLast(String str, String separator) {
        return org.apache.commons.lang3.StringUtils.substringBeforeLast(str, separator);
    }

    /**
     * 。<br>
     * @param str
     * @param tag
     * @return
     */
    public static String substringBetween(String str, String tag) {
        return org.apache.commons.lang3.StringUtils.substringBetween(str, tag);
    }

    /**
     * 。<br>
     * @param str
     * @param open
     * @param close
     * @return
     */
    public static String substringBetween(String str, String open, String close) {
        return org.apache.commons.lang3.StringUtils.substringBetween(str, open, close);
    }

    /**
     * 文字列の最後から指定した文字列で始まっている部分より手前を返します。
     * @param str
     *            文字列
     * @param separator
     *            セパレータ
     * @return 結果の文字列
     */
    public static String substringFromLast(final String str,
            final String separator) {
        if (isEmpty(str) || isEmpty(separator)) {
            return str;
        }
        int pos = str.lastIndexOf(separator);
        if (pos == -1) {
            return str;
        }
        return str.substring(0, pos);
    }

    public static String[] substringsBetween(String str, String open, String close) {
        return org.apache.commons.lang3.StringUtils.substringsBetween(str, open, close);
    }

    /**
     * 文字列の最後から指定した文字列で始まっている部分より後ろを返します。
     * @param str
     *            文字列
     * @param separator
     *            セパレータ
     * @return 結果の文字列
     */
    public static String substringToLast(final String str,
            final String separator) {
        if (isEmpty(str) || isEmpty(separator)) {
            return str;
        }
        int pos = str.lastIndexOf(separator);
        if (pos == -1) {
            return str;
        }
        return str.substring(pos + 1, str.length());
    }

    /**
     * 。<br>
     * @param str
     * @return
     */
    public static String swapCase(String str) {
        return org.apache.commons.lang3.StringUtils.swapCase(str);
    }

    /**
     * 。<br>
     * @param bytes
     * @param charset
     * @return
     */
    public static String toEncodedString(byte[] bytes, Charset charset) {
        return StringUtils.toEncodedString(bytes, charset);
    }

    /**
     * 16進数の文字列に変換します。
     * @param bytes
     *            バイトの配列
     * @return 16進数の文字列
     */
    public static String toHex(final byte... bytes) {
        if (bytes == null) {
            return "";
        }
        StringBuffer sb = new StringBuffer(bytes.length * 2);
        for (int i = 0; i < bytes.length; ++i) {
            appendHex(sb, bytes[i]);
        }
        return sb.toString();
    }

    /**
     * 16進数の文字列に変換します。
     * @param i
     *            int
     * @return 16進数の文字列
     */
    public static String toHex(final int i) {
        StringBuffer buf = new StringBuffer();
        appendHex(buf, i);
        return buf.toString();
    }

    /*
     * tera
     */
    /**
     * バイト配列を16進文字列に変換する。
     * @param byteArray
     *            バイト配列
     * @param delim
     *            デリミタ
     * @return 16進文字列
     */
    public static String toHexString(byte[] byteArray, CharSequence delim) {
        if (delim == null) {
            delim = "";
        }
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < byteArray.length; i++) {
            if (i > 0) {
                sb.append(delim);
            }
            String hex = Integer.toHexString(byteArray[i] & 0x00ff)
                    .toUpperCase();
            if (hex.length() < 2) {
                sb.append("0");
            }
            sb.append(hex);
        }
        return sb.toString();
    }

    /**
     * 。<br>
     * @param str
     * @return
     */
    public static String trim(String str) {
        return org.apache.commons.lang3.StringUtils.trim(str);
    }

    /**
     * プレフィックスを削ります。
     * @param text
     *            テキスト
     * @param prefix
     *            プレフィックス
     * @return 結果の文字列
     */
    public static final String trimPrefix(final String text, final String prefix) {
        if (text == null) {
            return null;
        }
        if (prefix == null) {
            return text;
        }
        if (text.startsWith(prefix)) {
            return text.substring(prefix.length());
        }
        return text;
    }

    /**
     * サフィックスを削ります。
     * @param text
     *            テキスト
     * @param suffix
     *            サフィックス
     * @return 結果の文字列
     */
    public static final String trimSuffix(final String text, final String suffix) {
        if (text == null) {
            return null;
        }
        if (suffix == null) {
            return text;
        }
        if (text.endsWith(suffix)) {
            return text.substring(0, text.length() - suffix.length());
        }
        return text;
    }

    /**
     * 。<br>
     * @param str
     * @return
     */
    public static String trimToEmpty(String str) {
        return org.apache.commons.lang3.StringUtils.trimToEmpty(str);
    }

    /**
     * 。<br>
     * @param str
     * @return
     */
    public static String trimToNull(String str) {
        return org.apache.commons.lang3.StringUtils.trimToNull(str);
    }

    /**
     * 。<br>
     * @param str
     * @return
     */
    public static String uncapitalize(String str) {
        return org.apache.commons.lang3.StringUtils.uncapitalize(str);
    }

    /**
     * 。<br>
     * @param str
     * @return
     */
    public static String upperCase(String str) {
        return org.apache.commons.lang3.StringUtils.upperCase(str);
    }

    /**
     * 。<br>
     * @param str
     * @param locale
     * @return
     */
    public static String upperCase(String str, Locale locale) {
        return org.apache.commons.lang3.StringUtils.upperCase(str, locale);
    }
}
