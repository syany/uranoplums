/**
 *
 */
package org.uranoplums.typical.util.i18n;

import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;

import org.uranoplums.typical.util.UraObjectUtils;

/**
 * エンコードクラス（日本語）。<br>
 * @author syany
 * 
 */
public enum UraCharset {
    /**  */
    ME;

    /**
     * デコードリスト
     */
    private final List<Charset> DECODER_LIST;
    /** ISO-2022-JP */
    public static final Charset JIS = Charset.forName("ISO-2022-JP");
    /**  */
    public static final Charset ISO_2022_JP_2 = Charset.forName("ISO-2022-JP-2");
    /**  */
    public static final Charset EUC_JP_LINUX = Charset.forName("x-euc-jp-linux");
    /**  */
    public static final Charset EUC_JP_OPEN = Charset.forName("x-eucJP-Open");
    /**  */
    public static final Charset EUC_JP = Charset.forName("EUC-JP");
    /**  */
    public static final Charset SHIFT_JIS = Charset.forName("Shift_JIS");
    /**  */
    public static final Charset WINDOWS_31J = Charset.forName("windows-31j");
    /**  */
    public static final Charset UTF8 = Charset.forName("UTF-8");
    /**  */
    public static final Charset UTF16BE = Charset.forName("UTF-16BE");
    /**  */
    public static final Charset UTF32 = Charset.forName("UTF-32");
    /**  */
    public static final Charset LATIN_1 = Charset.forName("ISO-8859-1");
    /**  */
    public static final Charset ASCII = Charset.forName("US-ASCII");
    {
        DECODER_LIST = new ArrayList<Charset>(9);
        setUpDecoderListGreater();
    }

    /**
     * キャラセット判定。<br>
     * @param byteArray 対象バイト配列
     * @return 引きあたったキャラセット（なければデフォルト）
     */
    public final Charset getCharset(byte[] byteArray) {
        Charset result = Charset.defaultCharset();
        for (Charset c : DECODER_LIST) {
            if (isCharset(byteArray, c)) {
                result = c;
                break;
            }
        }
        return result;
    }

    /**
     * 対象がJISセットか確認する。
     * @param source 対象バイト文字列
     * @return 対象文字セットであればtrue
     */
    public final boolean isASCII(byte[] source) {
        return isCharset(source, ASCII);
    }

    /**
     * 対象のキャラセットかどうかを確認する。<br>
     * @param source
     * @param target
     * @return 対象キャラセットであればtrue
     */
    public final boolean isCharset(byte[] source, Charset target) {
        CharBuffer cb = target.decode(ByteBuffer.wrap(source));
        return UraObjectUtils.equals(target.encode(cb), ByteBuffer.wrap(source));
    }

    /**
     * 対象がJISセットか確認する。
     * @param source 対象バイト文字列
     * @return 対象文字セットであればtrue
     */
    public final boolean isEUC_JP(byte[] source) {
        return isCharset(source, EUC_JP);
    }

    /**
     * 対象がJISセットか確認する。
     * @param source 対象バイト文字列
     * @return 対象文字セットであればtrue
     */
    public final boolean isEUC_JP_LINUX(byte[] source) {
        return isCharset(source, EUC_JP_LINUX);
    }

    /**
     * 対象がJISセットか確認する。
     * @param source 対象バイト文字列
     * @return 対象文字セットであればtrue
     */
    public final boolean isEUC_JP_OPEN(byte[] source) {
        return isCharset(source, EUC_JP_OPEN);
    }

    /**
     * 対象がJISセットか確認する。
     * @param source 対象バイト文字列
     * @return 対象文字セットであればtrue
     */
    public final boolean isISO_2022_JP_2(byte[] source) {
        return isCharset(source, ISO_2022_JP_2);
    }

    /**
     * 対象がJISセットか確認する。
     * @param source 対象バイト文字列
     * @return 対象文字セットであればtrue
     */
    public final boolean isJIS(byte[] source) {
        return isCharset(source, JIS);
    }

    /**
     * 対象がJISセットか確認する。
     * @param source 対象バイト文字列
     * @return 対象文字セットであればtrue
     */
    public final boolean isLATIN_1(byte[] source) {
        return isCharset(source, LATIN_1);
    }

    /**
     * 対象がJISセットか確認する。
     * @param source 対象バイト文字列
     * @return 対象文字セットであればtrue
     */
    public final boolean isSHIFT_JIS(byte[] source) {
        return isCharset(source, SHIFT_JIS);
    }

    /**
     * 対象がJISセットか確認する。
     * @param source 対象バイト文字列
     * @return 対象文字セットであればtrue
     */
    public final boolean isUTF16BE(byte[] source) {
        return isCharset(source, UTF16BE);
    }

    /**
     * 対象がJISセットか確認する。
     * @param source 対象バイト文字列
     * @return 対象文字セットであればtrue
     */
    public final boolean isUTF32(byte[] source) {
        return isCharset(source, UTF32);
    }

    /**
     * 対象がJISセットか確認する。
     * @param source 対象バイト文字列
     * @return 対象文字セットであればtrue
     */
    public final boolean isUTF8(byte[] source) {
        return isCharset(source, UTF8);
    }

    /**
     * 対象がJISセットか確認する。
     * @param source 対象バイト文字列
     * @return 対象文字セットであればtrue
     */
    public final boolean isWINDOWS_31J(byte[] source) {
        return isCharset(source, WINDOWS_31J);
    }

    /**
     * オリジナルデコーダー順序設定
     * @param charset
     * @return インスタンスを返却します。
     */
    public final UraCharset setUpDecoderList(Charset... charset) {
        if (charset.length <= 0) {
            return this;
        }
        for (Charset c : charset) {
            DECODER_LIST.add(c);
        }
        return this;
    }

    /**
     * JIS, EUC-JP, Shift_JIS, Unicodeの内最も使われていそうな文字セットを返す。<br>
     * @return インスタンスを返却します。
     */
    public final UraCharset setUpDecoderListDefault() {
        DECODER_LIST.clear();
        DECODER_LIST.add(JIS);
        DECODER_LIST.add(EUC_JP);
        DECODER_LIST.add(WINDOWS_31J);
        DECODER_LIST.add(UTF8);
        return this;
    }

    /**
     * JIS, EUC-JP, Shift_JIS, Unicodeの内最も広い文字セット範囲を持つ文字セットを返す。<br>
     * UnicodeはUTF-16BE（USC-2)。UTF-32でない。
     * @return インスタンスを返却します。
     */
    public final UraCharset setUpDecoderListGreater() {
        DECODER_LIST.clear();
        DECODER_LIST.add(ISO_2022_JP_2);
        DECODER_LIST.add(EUC_JP_OPEN);
        DECODER_LIST.add(WINDOWS_31J);
        DECODER_LIST.add(UTF16BE);
        return this;
    }

    /**
     * JIS, EUC-JP, Shift_JIS, Unicodeを亜種も含め検出対象とする。
     * @return インスタンスを返却します。
     */
    public final UraCharset setUpDecoderListLower() {
        DECODER_LIST.clear();
        DECODER_LIST.add(JIS);
        DECODER_LIST.add(ISO_2022_JP_2);
        DECODER_LIST.add(EUC_JP_LINUX);
        DECODER_LIST.add(EUC_JP);
        DECODER_LIST.add(EUC_JP_OPEN);
        DECODER_LIST.add(SHIFT_JIS);
        DECODER_LIST.add(WINDOWS_31J);
        DECODER_LIST.add(UTF8);
        DECODER_LIST.add(UTF16BE);
        return this;
    }
}
