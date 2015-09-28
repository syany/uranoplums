/**
 *
 */
package org.uranoplums.typical.util.i18n;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.uranoplums.typical.exception.UraIORuntimeException;
import org.uranoplums.typical.io.UraFileUtils;
import org.uranoplums.typical.io.UraIOUtils;
import org.uranoplums.typical.util.UraObjectUtils;
import org.uranoplums.typical.util.UraStringUtils;

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
    /** */
    private static final int MAX_BYTE = 2048;
    /** */
    private static final int INIT_BYTE = 32;
    /** ISO-2022-JP */
    public static final Charset JIS;
    /**  */
    public static final Charset ISO_2022_JP_2;
    /**  */
    public static final Charset EUC_JP_LINUX;
    /**  */
    public static final Charset EUC_JP_OPEN;
    /**  */
    public static final Charset EUC_JP;
    /**  */
    public static final Charset SHIFT_JIS;
    /**  */
    public static final Charset WINDOWS_31J;
    /**  */
    public static final Charset UTF8;
    /**  */
    public static final Charset UTF16BE;
    /**  */
    public static final Charset UTF32;
    /**  */
    public static final Charset LATIN_1;
    /**  */
    public static final Charset ASCII;

    /** ISO-2022-JP */
    private final Charset _jis = Charset.forName("ISO-2022-JP");
    /**  */
    private final Charset _iso2022Jp2 = Charset.forName("ISO-2022-JP-2");
    /**  */
    private final Charset _eucJpLinux = Charset.forName("x-euc-jp-linux");
    /**  */
    private final Charset _eucJpOpen = Charset.forName("x-eucJP-Open");
    /**  */
    private final Charset _eucJp = Charset.forName("EUC-JP");
    /**  */
    private final Charset _shiftJis = Charset.forName("Shift_JIS");
    /**  */
    private final Charset _windows31j = Charset.forName("windows-31j");
    /**  */
    private final Charset _utf8 = Charset.forName("UTF-8");
    /**  */
    private final Charset _utf16be = Charset.forName("UTF-16BE");
    /**  */
    private final Charset _utf32 = Charset.forName("UTF-32");
    /**  */
    private final Charset _latin1 = Charset.forName("ISO-8859-1");
    /**  */
    private final Charset _ascii = Charset.forName("US-ASCII");
    static {
        JIS = ME._jis;
        ISO_2022_JP_2 = ME._iso2022Jp2;
        EUC_JP_LINUX = ME._eucJpLinux;
        EUC_JP_OPEN = ME._eucJpOpen;
        EUC_JP = ME._eucJp;
        SHIFT_JIS= ME._shiftJis;
        WINDOWS_31J = ME._windows31j;
        UTF8 = ME._utf8;
        UTF16BE =ME._utf16be;
        UTF32 =ME._utf32;
        LATIN_1 =ME._latin1;
        ASCII =ME._ascii;
    }
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
        return getCharset(byteArray, Charset.defaultCharset());
    }

    /**
     * キャラセット判定。<br>
     * @param byteArray 対象バイト配列
     * @param defaultCharset デフォルトキャラセット
     * @return 引きあたったキャラセット（なければ引数のキャラセット）
     */
    public final Charset getCharset(byte[] byteArray, Charset defaultCharset) {
        Charset result = defaultCharset;
        for (final Charset c : DECODER_LIST) {
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
     * @param charsetList
     * @return インスタンスを返却します。
     */
    public final UraCharset setUpDecoderList(Charset... charsetList) {
        if (charsetList.length <= 0) {
            return this;
        }
        DECODER_LIST.clear();
        for (final Charset c : charsetList) {
            addDecoderList(c);
        }
        return this;
    }

    /**
     * オリジナルデコーダー順序追加
     * @param charset
     * @return インスタンスを返却します。
     */
    public final UraCharset addDecoderList(Charset charset) {
        if (charset == null) {
            throw new NullPointerException("param1: charset");
        }
        DECODER_LIST.add(charset);
        return this;
    }

    /**
     * オリジナルデコーダー順序設定
     * @param charsetStrList
     * @return インスタンスを返却します。
     */
    public final UraCharset setUpDecoderList(String... charsetStrList) {
        if (charsetStrList.length <= 0) {
            return this;
        }
        DECODER_LIST.clear();
        for (final String c : charsetStrList) {
            addDecoderList(c);
        }
        return this;
    }

    /**
     * オリジナルデコーダー順序追加
     * @param charsetStr
     * @return インスタンスを返却します。
     */
    public final UraCharset addDecoderList(String charsetStr) {
        if (UraStringUtils.isEmpty(charsetStr)) {
            throw new NullPointerException("param1: charsetStr");
        }
        Charset c = Charset.forName(charsetStr); // UnsupportedCharsetException if no lookup
        DECODER_LIST.add(c);
        return this;
    }
    /**
     * JIS, EUC-JP, Shift_JIS, Unicodeの内最も使われていそうな文字セットを返す。<br>
     * @return インスタンスを返却します。
     */
    public final UraCharset setUpDecoderListDefault() {
        DECODER_LIST.clear();
        DECODER_LIST.add(_jis);
        DECODER_LIST.add(_eucJp);
        DECODER_LIST.add(_windows31j);
        DECODER_LIST.add(_utf8);
        return this;
    }

    /**
     * JIS, EUC-JP, Shift_JIS, Unicodeの内最も広い文字セット範囲を持つ文字セットを返す。<br>
     * UnicodeはUTF-16BE（USC-2)。UTF-32でない。
     * @return インスタンスを返却します。
     */
    public final UraCharset setUpDecoderListGreater() {
        DECODER_LIST.clear();
        DECODER_LIST.add(_iso2022Jp2);
        DECODER_LIST.add(_eucJpOpen);
        DECODER_LIST.add(_windows31j);
        DECODER_LIST.add(_utf16be);
        return this;
    }

    /**
     * JIS, EUC-JP, Shift_JIS, Unicodeを亜種も含め検出対象とする。
     * @return インスタンスを返却します。
     */
    public final UraCharset setUpDecoderListLower() {
        DECODER_LIST.clear();
        DECODER_LIST.add(_jis);
        DECODER_LIST.add(_iso2022Jp2);
        DECODER_LIST.add(_eucJpLinux);
        DECODER_LIST.add(_eucJp);
        DECODER_LIST.add(_eucJpOpen);
        DECODER_LIST.add(_shiftJis);
        DECODER_LIST.add(_windows31j);
        DECODER_LIST.add(_utf8);
        DECODER_LIST.add(_utf16be);
        return this;
    }
    /**
     * 対象ファイルのキャラセットを判定する。<br>
     * パスは、クラスパス内のファイル、フルファイルパスをそれぞれ指定できます。
     * @param pathStr ファイルパス
     * @return 取得したキャラセット
     */
    public Charset getCharset(final String pathStr) {
        InputStream is = null;
        Charset result = null;
        try {
            is = UraIOUtils.newInputStream(pathStr);
            if (is == null) {
                return null;
            }
            result = getCharset(is);
        } finally {
            UraIOUtils.closeQuietly(is);
        }
        return result;
    }
    /**
     * キャラセットを取得。inputStreamが更新されます。<br>
     * @param pathInputStream
     * @return
     * @throws UraIORuntimeException
     */
    protected Charset getCharset(final InputStream pathInputStream) {
        if (pathInputStream == null) {
            return null;
        }
        InputStream sourceInputStream = UraIOUtils.getResetableInputStreamIfNot(pathInputStream);
        byte[] oldBuffer = null;
        int byteNum = -1;
        Charset result = null;
        try {
            for (int readLen = INIT_BYTE; readLen <= MAX_BYTE; readLen = readLen * 2){
                byte[] buffer = new byte[MAX_BYTE];
                byteNum = sourceInputStream.read(buffer, 0, readLen);
                if (byteNum == -1 || Arrays.equals(buffer, oldBuffer)) {
                    break;
                }
                result = getCharset(buffer, null);
                if (result != null) {
                    break;
                }
                oldBuffer = buffer;
            }
        } catch (IOException e) {
            throw new UraIORuntimeException(e);
        }finally {
            UraIOUtils.resetQuietly(sourceInputStream);
        }
        return result;
    }

    public Charset getCharset(final File pathFile) {
        if (pathFile == null) {
            return null;
        }
        InputStream sourceInputStream = null;
        Charset result = null;
        try {
            sourceInputStream = UraFileUtils.openInputStream(pathFile);
            result = getCharset(sourceInputStream);
        } catch (IOException e) {
            throw new UraIORuntimeException(e);
        }finally {
            if (sourceInputStream != null) {
                UraIOUtils.closeQuietly(sourceInputStream);
            }
        }
        return result;
    }
}
