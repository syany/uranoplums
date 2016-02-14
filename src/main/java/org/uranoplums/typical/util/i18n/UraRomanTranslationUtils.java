/**
 *
 */
package org.uranoplums.typical.util.i18n;

import static org.uranoplums.typical.collection.factory.UraMapFactory.*;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Pattern;

import org.uranoplums.typical.util.UraUtils;

/**
 * ローマ字（ヘボン式）変換します。<br>
 * @author syany
 *
 */
public class UraRomanTranslationUtils extends UraUtils {

    /** 踊り字対応有無 */
    public static final int NO_TRANSLATE_IM = 0x01;
    /** 長音対応有無 */
    public static final int NO_TRANSLATE_CHOUON = 0x02;
    /** キャシュ数 */
    private static final int CACHE_MAX_SIZE = 48;
    /** 2文字変換マップ */
    private static final Map<String, char[]> HIRA2HEPBURN_MAP2;
    /** 2文字変換マップ */
    private static final Map<String, char[]> HIRA2KUNREI_MAP2;
    /**  */
    private static final Map<String, String> CACHE_HEPBURN_MAP;
    /**  */
    private static final Map<String, String> CACHE_KUNREI_MAP;
    /** 2文字変換対象文字パターン（拗音） */
    private static final Pattern TWO_CHAR_PATTERN;
    /** 撥音変換（N→M）対象パターン */
    private static final Pattern HATSUON_PATTERN;
    /** 濁音変換可能パターン(か、さ、た、は) */
    private static final Pattern BEFORE_DAKUON_PATTERN;
    /** 濁音変換可能パターン(が、ざ、だ、ば) */
    private static final Pattern AFTER_DAKUON_PATTERN;
    /** 変換対象開始文字（=ぁ） */
    private static final char BASE_CHAR = 'ぁ';
    /** 変換対象終了文字（=ゖ） */
    private static final char END_CHAR = 'ゖ';
    /** 撥音文字（=ん） */
    private static final char HATSUON = 'ん';
    /** 撥音変換後文字（→M） */
    private static final char HATSUON_M = 'M';
    /** 促音文字（=っ） */
    private static final char SOKUON = 'っ';
    /** 促音文字（=C） */
    private static final char SOKUON_C = 'C';
    /** 促音文字（=H） */
    private static final char SOKUON_H = 'H';
    /** 促音文字（=T） */
    private static final char SOKUON_T = 'T';
    /** 長音記号（=ー） */
    private static final char CHOUON = 'ー';
    /** 長音対象（=O） */
    private static final char CHOUON_O = 'O';
    /** 長音対象（=U） */
    private static final char CHOUON_U = 'U';
    /** 踊り字（平仮名通常） */
    private static final char IM_NORMAL = 'ゝ';
    /** 踊り字（平仮名濁音） */
    private static final char IM_DAKU = 'ゞ';
    /** 1文字変換マップ */
    private static final char[][] HIRA_HEPBURN_MAP = { {'A'} // ぁ
            , {'A'} // あ
            , {'I'} // ぃ
            , {'I'} // い
            , {'U'} // ぅ
            , {'U'} // う
            , {'E'} // ぇ
            , {'E'} // え
            , {'O'} // ぉ
            , {'O'} // お
            , {'K', 'A'} // か
            , {'G', 'A'} // が
            , {'K', 'I'} // き
            , {'G', 'I'} // ぎ
            , {'K', 'U'} // く
            , {'G', 'U'} // ぐ
            , {'K', 'E'} // け
            , {'G', 'E'} // げ
            , {'K', 'O'} // こ
            , {'G', 'O'} // ご
            , {'S', 'A'} // さ
            , {'Z', 'A'} // ざ
            , {'S', 'H', 'I'} // し
            , {'J', 'I'} // じ
            , {'S', 'U'} // す
            , {'Z', 'U'} // ず
            , {'S', 'E'} // せ
            , {'Z', 'E'} // ぜ
            , {'S', 'O'} // そ
            , {'Z', 'O'} // ぞ
            , {'T', 'A'} // た
            , {'D', 'A'} // だ
            , {'C', 'H', 'I'} // ち
            , {'J', 'I'} // ぢ
            , {'T', 'S', 'U'} // っ
            , {'T', 'S', 'U'} // つ
            , {'Z', 'U'} // づ
            , {'T', 'E'} // て
            , {'D', 'E'} // で
            , {'T', 'O'} // と
            , {'D', 'O'} // ど
            , {'N', 'A'} // な
            , {'N', 'I'} // に
            , {'N', 'U'} // ぬ
            , {'N', 'E'} // ね
            , {'N', 'O'} // の
            , {'H', 'A'} // は
            , {'B', 'A'} // ば
            , {'P', 'A'} // ぱ
            , {'H', 'I'} // ひ
            , {'B', 'I'} // び
            , {'P', 'I'} // ぴ
            , {'F', 'U'} // ふ
            , {'B', 'U'} // ぶ
            , {'P', 'U'} // ぷ
            , {'H', 'E'} // へ
            , {'B', 'E'} // べ
            , {'P', 'E'} // ぺ
            , {'H', 'O'} // ほ
            , {'B', 'O'} // ぼ
            , {'P', 'O'} // ぽ
            , {'M', 'A'} // ま
            , {'M', 'I'} // み
            , {'M', 'U'} // む
            , {'M', 'E'} // め
            , {'M', 'O'} // も
            , {'Y', 'A'} // ゃ
            , {'Y', 'A'} // や
            , {'Y', 'U'} // ゅ
            , {'Y', 'U'} // ゆ
            , {'Y', 'O'} // ょ
            , {'Y', 'O'} // よ
            , {'R', 'A'} // ら
            , {'R', 'I'} // り
            , {'R', 'U'} // る
            , {'R', 'E'} // れ
            , {'R', 'O'} // ろ
            , {'W', 'A'} // ゎ
            , {'W', 'A'} // わ
            , {'I'} // ゐ
            , {'E'} // ゑ
            , {'O'} // を
            , {'N'} // ん
            , {'V', 'U'} // ゔ
            , {'K', 'A'} // ゕ
            , {'K', 'E'} // ゖ
    };
    // 訓令式
    /** 1文字変換マップ */
    private static final char[][] HIRA_KUNREI_MAP = { {'A'} // ぁ
            , {'A'} // あ
            , {'I'} // ぃ
            , {'I'} // い
            , {'U'} // ぅ
            , {'U'} // う
            , {'E'} // ぇ
            , {'E'} // え
            , {'O'} // ぉ
            , {'O'} // お
            , {'K', 'A'} // か
            , {'G', 'A'} // が
            , {'K', 'I'} // き
            , {'G', 'I'} // ぎ
            , {'K', 'U'} // く
            , {'G', 'U'} // ぐ
            , {'K', 'E'} // け
            , {'G', 'E'} // げ
            , {'K', 'O'} // こ
            , {'G', 'O'} // ご
            , {'S', 'A'} // さ
            , {'Z', 'A'} // ざ
            , {'S', 'I'} // し
            , {'Z', 'I'} // じ
            , {'S', 'U'} // す
            , {'Z', 'U'} // ず
            , {'S', 'E'} // せ
            , {'Z', 'E'} // ぜ
            , {'S', 'O'} // そ
            , {'Z', 'O'} // ぞ
            , {'T', 'A'} // た
            , {'D', 'A'} // だ
            , {'T', 'I'} // ち
            , {'Z', 'I'} // ぢ
            , {'T', 'U'} // っ
            , {'T', 'U'} // つ
            , {'Z', 'U'} // づ
            , {'T', 'E'} // て
            , {'D', 'E'} // で
            , {'T', 'O'} // と
            , {'D', 'O'} // ど
            , {'N', 'A'} // な
            , {'N', 'I'} // に
            , {'N', 'U'} // ぬ
            , {'N', 'E'} // ね
            , {'N', 'O'} // の
            , {'H', 'A'} // は
            , {'B', 'A'} // ば
            , {'P', 'A'} // ぱ
            , {'H', 'I'} // ひ
            , {'B', 'I'} // び
            , {'P', 'I'} // ぴ
            , {'H', 'U'} // ふ
            , {'B', 'U'} // ぶ
            , {'P', 'U'} // ぷ
            , {'H', 'E'} // へ
            , {'B', 'E'} // べ
            , {'P', 'E'} // ぺ
            , {'H', 'O'} // ほ
            , {'B', 'O'} // ぼ
            , {'P', 'O'} // ぽ
            , {'M', 'A'} // ま
            , {'M', 'I'} // み
            , {'M', 'U'} // む
            , {'M', 'E'} // め
            , {'M', 'O'} // も
            , {'Y', 'A'} // ゃ
            , {'Y', 'A'} // や
            , {'Y', 'U'} // ゅ
            , {'Y', 'U'} // ゆ
            , {'Y', 'O'} // ょ
            , {'Y', 'O'} // よ
            , {'R', 'A'} // ら
            , {'R', 'I'} // り
            , {'R', 'U'} // る
            , {'R', 'E'} // れ
            , {'R', 'O'} // ろ
            , {'W', 'A'} // ゎ
            , {'W', 'A'} // わ
            , {'I'} // ゐ
            , {'E'} // ゑ
            , {'O'} // を
            , {'N'} // ん
            , {'V', 'U'} // ゔ
            , {'K', 'A'} // ゕ
            , {'K', 'E'} // ゖ
    };
    static {
        // 2文字2文字目パターン
        TWO_CHAR_PATTERN = Pattern.compile("ぁ|ぃ|ぅ|ぇ|ぉ|ゃ|ゅ|ょ|ゎ", Pattern.DOTALL
                | Pattern.MULTILINE);
        // 撥音。Nの次の文字がPMBはN->M
        HATSUON_PATTERN = Pattern.compile("[BMP]", Pattern.DOTALL
                | Pattern.MULTILINE);
        //
        BEFORE_DAKUON_PATTERN = Pattern.compile(
                "か|き|く|け|こ|さ|し|す|せ|そ|た|ち|つ|て|と|は|ひ|ふ|へ|ほ", Pattern.DOTALL
                        | Pattern.MULTILINE);
        AFTER_DAKUON_PATTERN = Pattern.compile(
                "が|ぎ|ぐ|げ|ご|ざ|じ|ず|ぜ|ぞ|だ|ぢ|づ|で|ど|ば|び|ぶ|べ|ぼ", Pattern.DOTALL
                        | Pattern.MULTILINE);
        // キャッシュマップの生成
        CACHE_HEPBURN_MAP = newLRUMap(CACHE_MAX_SIZE);
        CACHE_KUNREI_MAP = newLRUMap(CACHE_MAX_SIZE);
        // 2文字パターン
        Map<String, char[]> tmpMap = new HashMap<String, char[]>(64 * 4 / 3) {

            /** シリアル・バージョンID */
            private static final long serialVersionUID = 317862428057375161L;
            {
                put("きゃ", "KYA".toCharArray());
                put("きゅ", "KYU".toCharArray());
                put("きょ", "KYO".toCharArray());
                put("しゃ", "SHA".toCharArray());
                put("しゅ", "SHU".toCharArray());
                put("しょ", "SHO".toCharArray());
                put("ちゃ", "CHA".toCharArray());
                put("ちゅ", "CHU".toCharArray());
                put("ちょ", "CHO".toCharArray());
                put("にゃ", "NYA".toCharArray());
                put("にゅ", "NYU".toCharArray());
                put("にょ", "NYO".toCharArray());
                put("ひゃ", "HYA".toCharArray());
                put("ひゅ", "HYU".toCharArray());
                put("ひょ", "HYO".toCharArray());
                put("みゃ", "MYA".toCharArray());
                put("みゅ", "MYU".toCharArray());
                put("みょ", "MYO".toCharArray());
                put("りゃ", "RYA".toCharArray());
                put("りゅ", "RYU".toCharArray());
                put("りょ", "RYO".toCharArray());
                put("ぎゃ", "GYA".toCharArray());
                put("ぎゅ", "GYU".toCharArray());
                put("ぎょ", "GYO".toCharArray());
                put("じゃ", "JA".toCharArray());
                put("じゅ", "JU".toCharArray());
                put("じょ", "JO".toCharArray());
                // put("でゃ", "JA".toCharArray());
                // put("でゅ", "JU".toCharArray());
                // put("でょ", "JO".toCharArray());
                put("びゃ", "BYA".toCharArray());
                put("びゅ", "BYU".toCharArray());
                put("びょ", "BYO".toCharArray());
                put("ぴゃ", "PYA".toCharArray());
                put("ぴゅ", "PYU".toCharArray());
                put("ぴょ", "PYO".toCharArray());
                put("いぇ", "IE".toCharArray());
                put("うぃ", "UI".toCharArray());
                put("うぇ", "UE".toCharArray());
                put("うぉ", "UO".toCharArray());
                put("ゔぁ", "VA".toCharArray());
                put("ゔぃ", "VI".toCharArray());
                put("ゔぇ", "VE".toCharArray());
                put("ゔぉ", "VO".toCharArray());
                put("くぁ", "KUA".toCharArray());
                put("くぃ", "KUI".toCharArray());
                put("くぇ", "KUE".toCharArray());
                put("くぉ", "KUO".toCharArray());
                put("ぐぁ", "GUA".toCharArray());
                put("ぐぃ", "GUI".toCharArray());
                put("ぐぇ", "GUE".toCharArray());
                put("ぐぉ", "GUO".toCharArray());
                put("じぇ", "JIE".toCharArray());
                put("ちぇ", "CHIE".toCharArray());
                put("つぁ", "TSUA".toCharArray());
                put("つぃ", "TSUI".toCharArray());
                put("つぇ", "TSUE".toCharArray());
                put("つぉ", "TSUO".toCharArray());
                put("てぃ", "TEI".toCharArray());
                put("でぃ", "DEI".toCharArray());
                put("でゅ", "DEYU".toCharArray());
                put("どぅ", "DOU".toCharArray());
                put("ふぁ", "FUA".toCharArray());
                put("ふぃ", "FUI".toCharArray());
                put("ふぇ", "FUE".toCharArray());
                put("ふぉ", "FUO".toCharArray());
                put("ふょ", "FUYO".toCharArray());
            }
        };
        // 変更不可
        HIRA2HEPBURN_MAP2 = Collections.unmodifiableMap(tmpMap);
        // 2文字パターン
        Map<String, char[]> tmpMap2 = new HashMap<String, char[]>(36 * 4 / 3) {

            /** シリアル・バージョンID */
            private static final long serialVersionUID = -528313897553606075L;
            {
                put("きゃ", "KYA".toCharArray());
                put("きゅ", "KYU".toCharArray());
                put("きょ", "KYO".toCharArray());
                put("しゃ", "SYA".toCharArray());
                put("しゅ", "SYU".toCharArray());
                put("しょ", "SYO".toCharArray());
                put("ちゃ", "TYA".toCharArray());
                put("ちゅ", "TYU".toCharArray());
                put("ちょ", "TYO".toCharArray());
                put("にゃ", "NYA".toCharArray());
                put("にゅ", "NYU".toCharArray());
                put("にょ", "NYO".toCharArray());
                put("ひゃ", "HYA".toCharArray());
                put("ひゅ", "HYU".toCharArray());
                put("ひょ", "HYO".toCharArray());
                put("みゃ", "MYA".toCharArray());
                put("みゅ", "MYU".toCharArray());
                put("みょ", "MYO".toCharArray());
                put("りゃ", "RYA".toCharArray());
                put("りゅ", "RYU".toCharArray());
                put("りょ", "RYO".toCharArray());
                put("ぎゃ", "GYA".toCharArray());
                put("ぎゅ", "GYU".toCharArray());
                put("ぎょ", "GYO".toCharArray());
                put("じゃ", "ZYA".toCharArray());
                put("じゅ", "ZYU".toCharArray());
                put("じょ", "ZYO".toCharArray());
                put("でゃ", "ZYA".toCharArray());
                put("でゅ", "ZYU".toCharArray());
                put("でょ", "ZYO".toCharArray());
                put("びゃ", "BYA".toCharArray());
                put("びゅ", "BYU".toCharArray());
                put("びょ", "BYO".toCharArray());
                put("ぴゃ", "PYA".toCharArray());
                put("ぴゅ", "PYU".toCharArray());
                put("ぴょ", "PYO".toCharArray());
            }
        };
        // 変更不可
        HIRA2KUNREI_MAP2 = Collections.unmodifiableMap(tmpMap2);
    }

    /**
     *
     * @param source
     * @return
     */
    protected static final boolean isInvalidWord(char source) {
        boolean invalidFlag = false;
        switch (source) {
            case CHOUON:
                invalidFlag = true;
                break;
            default:
                break;
        }
        return invalidFlag;
    }

    /**
     *
     * @param source
     * @return 対象文字ならtrue
     */
    public static final boolean isValidRange(char source) {
        return (BASE_CHAR <= source && END_CHAR >= source);
    }

    public static String katakanaToHepburn(String source, int noTranslateHiragana, int noTranslateRoman) {
        String sourceHira = UraJaStringUtils.kataToHiraTranslate(source, noTranslateHiragana);
        return hiraganaToHepburn(sourceHira, noTranslateRoman);
    }

    public static String katakanaToHepburn(String source, int noTranslateRoman) {
        String sourceHira = UraJaStringUtils.kataToHiraTranslate(source);
        return hiraganaToHepburn(sourceHira, noTranslateRoman);
    }

    public static String katakanaToHepburn(String source) {
        String sourceHira = UraJaStringUtils.kataToHiraTranslate(source);
        return hiraganaToHepburn(sourceHira);
    }

    /**
     *
     * @param source
     * @return 変換後のローマ字
     */
    public static String hiraganaToHepburn(String source) {
        return hiraganaToHepburn(source, 0);
    }

    /**
     *
     * @param source
     * @param noTranslateRoman
     * @return 変換後のローマ字
     */
    public static String hiraganaToHepburn(String source, int noTranslateRoman) {
        // 引数validation
        if (UraJaStringUtils.isEmpty(source)) {
            return UraJaStringUtils.EMPTY;
        }
        // キャッシュがあれば即時返却
        if (CACHE_HEPBURN_MAP.containsKey(source)) {
            return CACHE_HEPBURN_MAP.get(source);
        }
        // 初期化
        StringBuilder result = new StringBuilder();
        // 処理開始
        String sourceHira = source;//UraJaStringUtils.kataToHiraTranslate(source, 0);
        // サロゲートを無視した長さ
        int maxLen = sourceHira.length();
        // 1字前情報
        char[] oldChar = null;
        // 1文字ごとに判定
        for (int i = 0; i < maxLen; i++) {
            // 判定文字
            char[] nowChar = null;
            char targetChar = sourceHira.charAt(i);
            // 不要単語の削除
            if (isInvalidWord(targetChar)) {
                continue;
            }
            if (((noTranslateRoman & NO_TRANSLATE_IM) == 0) && (i > 0)) {
                // 踊り字対応
                if (targetChar == IM_NORMAL
                        && AFTER_DAKUON_PATTERN.matcher(
                                sourceHira.substring(i - 1, i)).matches()) {
                    // 踊り字（平仮名繰り返し）は１文字前が濁音であれば１字前の文字 - 1を設定し濁音でなくす。
                    targetChar = (char) (sourceHira.charAt(i - 1) - 1);
                } else if (targetChar == IM_NORMAL) {
                    // 踊り字（平仮名繰り返し）で１字前が濁音でない場合１文字前をコピー
                    targetChar = sourceHira.charAt(i - 1);
                } else if (targetChar == IM_DAKU
                        && BEFORE_DAKUON_PATTERN.matcher(
                                sourceHira.substring(i - 1, i)).matches()) {
                    // 踊り字（平仮名繰り返し（濁点））は１文字前が濁音でない場合１字前 + 1を設定し濁音にする
                    targetChar = (char) (sourceHira.charAt(i - 1) + 1);
                } else if (targetChar == IM_DAKU
                        && AFTER_DAKUON_PATTERN.matcher(
                                sourceHira.substring(i - 1, i)).matches()) {
                    // 踊り字（平仮名繰り返し（濁点））は１文字前が濁音の場合、１文字前をコピー
                    targetChar = sourceHira.charAt(i - 1);
                }
            }
            // 対象範囲外
            if (!isValidRange(targetChar)) {
                // 範囲外の文字はそのまま設定
                char[] noRoungeWord = {targetChar};
                nowChar = noRoungeWord;
            } else {
                // 範囲内の場合、ローマ字変換を行う
                int nextIndex = i + 1;
                if (nextIndex < maxLen) {
                    // 次の文字がある場合、複数文字変換を試みる
                    if (TWO_CHAR_PATTERN.matcher(
                            sourceHira.substring(nextIndex, nextIndex + 1))
                            .matches()) {
                        // マップから2文字変換
                        nowChar = HIRA2HEPBURN_MAP2.get(sourceHira.subSequence(
                                i, nextIndex + 1));
                    }
                }
                // 複数文字変換を行っていない場合
                if (nowChar == null || nowChar.length == 0) {
                    // 通常1文字変換を行う。
                    // 指定範囲内であれば、マップ通りに並んでいるため、開始文字'ぁ'で差し引けば
                    // 必ず、対象文字が引き当たる。
                    nowChar = HIRA_HEPBURN_MAP[targetChar - BASE_CHAR];
                } else {
                    i++;
                }
                // 撥・促・長音判定
                if (i > 0 && nowChar != null && oldChar != null
                        && oldChar.length > 0) {
                    char prefChar = sourceHira.charAt(i - 1);
                    if (prefChar == HATSUON
                            && HATSUON_PATTERN.matcher(
                                    String.valueOf(nowChar[0])).matches()) {
                        // 撥音判定
                        // 1字前の文字を基準として、次を考える
                        // 現在値の先頭がMに変更すべきなのであれば、NをMに変換する
                        oldChar[oldChar.length - 1] = HATSUON_M;
                    } else if (prefChar == SOKUON) {
                        // 促音判定
                        // 1字前の文字を基準として、次を考える
                        // 1字前、現在値が変換範囲内であり
                        // 且つ現在の変換結果がCHならば1字前をTへ変換
                        if (nowChar.length > 1 && nowChar[0] == SOKUON_C
                                && nowChar[1] == SOKUON_H) {
                            char[] t = {SOKUON_T};
                            oldChar = t;
                        } else {
                            // 以外は現在値の先頭文字を設定
                            char[] t = {nowChar[0]};
                            oldChar = t;
                        }
                    } else if (((noTranslateRoman & NO_TRANSLATE_CHOUON) == 0)
                            && (oldChar[oldChar.length - 1] == nowChar[0] || (oldChar[oldChar.length - 1] == CHOUON_O && nowChar[0] == CHOUON_U))) {
                        // 長音判定
                        // 同じ母音がかぶった場合、もしくは0とUの場合、現在値の先頭を削除した状態で再登録する。
                        char[] t = new char[nowChar.length - 1];
                        System.arraycopy(nowChar, 1, t, 0, t.length);
                        nowChar = t;
                    }
                }
            }
            // 書き込み
            if (oldChar != null && oldChar.length > 0) {
                result.append(oldChar);
            }
            oldChar = (nowChar != null) ? nowChar.clone() : null;
        }
        // 最後
        if (oldChar != null && oldChar.length > 0) {
            result.append(oldChar);
        }
        String resultString = result.toString();
        // キャッシュ設定
        CACHE_HEPBURN_MAP.put(source, resultString);
        return resultString;
        // return result.toString();
    }

    public static String katakanaToKunrei(String source, int noTranslateHiragana, int noTranslateRoman) {
        String sourceHira = UraJaStringUtils.kataToHiraTranslate(source, noTranslateHiragana);
        return hiraganaToKunrei(sourceHira, noTranslateRoman);
    }

    public static String katakanaToKunrei(String source, int noTranslateRoman) {
        String sourceHira = UraJaStringUtils.kataToHiraTranslate(source);
        return hiraganaToKunrei(sourceHira, noTranslateRoman);
    }

    public static String katakanaToKunrei(String source) {
        String sourceHira = UraJaStringUtils.kataToHiraTranslate(source);
        return hiraganaToKunrei(sourceHira);
    }

    public static String hiraganaToKunrei(String source) {
        return hiraganaToKunrei(source, 0);
    }

    /**
     * 。<br>
     * @param source
     * @param noTranslateFlag
     * @return 訓令式
     */
    public static String hiraganaToKunrei(String source, int noTranslateFlag) {
        // 引数validation
        if (UraJaStringUtils.isEmpty(source)) {
            return UraJaStringUtils.EMPTY;
        }
        // キャッシュがあれば即時返却
        if (CACHE_KUNREI_MAP.containsKey(source)) {
            return CACHE_KUNREI_MAP.get(source);
        }
        // 初期化
        StringBuilder result = new StringBuilder();
        // 処理開始
        String sourceHira = source;//UraJaStringUtils.kataToHiraTranslate(source, 0);
        // サロゲートを無視した長さ
        int maxLen = sourceHira.length();
        // 1字前情報
        char[] oldChar = null;
        // 1文字ごとに判定
        for (int i = 0; i < maxLen; i++) {
            // 判定文字
            char[] nowChar = null;
            char targetChar = sourceHira.charAt(i);
            // 不要単語の削除
            if (isInvalidWord(targetChar)) {
                continue;
            }
            if (((noTranslateFlag & NO_TRANSLATE_IM) == 0) && (i > 0)) {
                // 踊り字対応
                if (targetChar == IM_NORMAL
                        && AFTER_DAKUON_PATTERN.matcher(
                                sourceHira.substring(i - 1, i)).matches()) {
                    // 踊り字（平仮名繰り返し）は１文字前が濁音であれば１字前の文字 - 1を設定し濁音でなくす。
                    targetChar = (char) (sourceHira.charAt(i - 1) - 1);
                } else if (targetChar == IM_NORMAL) {
                    // 踊り字（平仮名繰り返し）で１字前が濁音でない場合１文字前をコピー
                    targetChar = sourceHira.charAt(i - 1);
                } else if (targetChar == IM_DAKU
                        && BEFORE_DAKUON_PATTERN.matcher(
                                sourceHira.substring(i - 1, i)).matches()) {
                    // 踊り字（平仮名繰り返し（濁点））は１文字前が濁音でない場合１字前 + 1を設定し濁音にする
                    targetChar = (char) (sourceHira.charAt(i - 1) + 1);
                } else if (targetChar == IM_DAKU
                        && AFTER_DAKUON_PATTERN.matcher(
                                sourceHira.substring(i - 1, i)).matches()) {
                    // 踊り字（平仮名繰り返し（濁点））は１文字前が濁音の場合、１文字前をコピー
                    targetChar = sourceHira.charAt(i - 1);
                }
            }
            // 対象範囲外
            if (!isValidRange(targetChar)) {
                // 範囲外の文字はそのまま設定
                char[] noRoungeWord = {targetChar};
                nowChar = noRoungeWord;
            } else {
                // 範囲内の場合、ローマ字変換を行う
                int nextIndex = i + 1;
                if (nextIndex < maxLen) {
                    // 次の文字がある場合、複数文字変換を試みる
                    if (TWO_CHAR_PATTERN.matcher(
                            sourceHira.substring(nextIndex, nextIndex + 1))
                            .matches()) {
                        // マップから2文字変換
                        nowChar = HIRA2KUNREI_MAP2.get(sourceHira.subSequence(
                                i, nextIndex + 1));
                    }
                }
                // 複数文字変換を行っていない場合
                if (nowChar == null || nowChar.length == 0) {
                    // 通常1文字変換を行う。
                    // 指定範囲内であれば、マップ通りに並んでいるため、開始文字'ぁ'で差し引けば
                    // 必ず、対象文字が引き当たる。
                    nowChar = HIRA_KUNREI_MAP[targetChar - BASE_CHAR];
                } else {
                    i++;
                }
                // 撥・促・長音判定
                if (i > 0 && nowChar != null && oldChar != null
                        && oldChar.length > 0) {
                    char prefChar = sourceHira.charAt(i - 1);
                    if (prefChar == HATSUON
                            && HATSUON_PATTERN.matcher(
                                    String.valueOf(nowChar[0])).matches()) {
                        // 撥音判定
                        // 1字前の文字を基準として、次を考える
                        // 現在値の先頭がMに変更すべきなのであれば、NをMに変換する
                        oldChar[oldChar.length - 1] = HATSUON_M;
                    } else if (prefChar == SOKUON) {
                        // 促音判定
                        // 1字前の文字を基準として、次を考える
                        // 1字前、現在値が変換範囲内であり
                        // 且つ現在の変換結果がCHならば1字前をTへ変換
                        if (nowChar.length > 1 && nowChar[0] == SOKUON_C
                                && nowChar[1] == SOKUON_H) {
                            char[] t = {SOKUON_T};
                            oldChar = t;
                        } else {
                            // 以外は現在値の先頭文字を設定
                            char[] t = {nowChar[0]};
                            oldChar = t;
                        }
                    } else if (((noTranslateFlag & NO_TRANSLATE_CHOUON) == 0)
                            && (oldChar[oldChar.length - 1] == nowChar[0] || (oldChar[oldChar.length - 1] == CHOUON_O && nowChar[0] == CHOUON_U))) {
                        // 長音判定
                        // 同じ母音がかぶった場合、もしくは0とUの場合、現在値の先頭を削除した状態で再登録する。
                        char[] t = new char[nowChar.length - 1];
                        System.arraycopy(nowChar, 1, t, 0, t.length);
                        nowChar = t;
                    }
                }
            }
            // 書き込み
            if (oldChar != null && oldChar.length > 0) {
                result.append(oldChar);
            }
            oldChar = (nowChar != null) ? nowChar.clone() : null;
        }
        // 最後
        if (oldChar != null && oldChar.length > 0) {
            result.append(oldChar);
        }
        String resultString = result.toString();
        // キャッシュ設定
        CACHE_KUNREI_MAP.put(source, resultString);
        return resultString;
    }
}
