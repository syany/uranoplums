/*
 * Copyright 2013-2014 the Uranoplums Foundation and the Others.
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
package org.uranoplums.typical.util.i18n;

import java.text.Normalizer;
import java.text.Normalizer.Form;

import org.uranoplums.typical.util.UraStringUtils;

/**
 * @since 2014/02/07
 * @author syany
 */
public class UraJaStringUtils extends UraStringUtils {

    //
    // /**
    // * 実行環境のOSで用いられる改行コードを取得する。
    // */
    // public static final String LINE_SEP = System.getProperty("line.separator");
    /*
     * 片平仮名変換フラグ：デフォルトは0x02（ヵヶは無変換）
     */
    /** 片平仮名変換フラグ：ヴを無変換 */
    public static final int NO_TRANSLATE_KH_VU = 0x01;
    /** 片平仮名変換フラグ：ヵヶを無変換 */
    public static final int NO_TRANSLATE_KH_KK = 0x02;
    /** 片平仮名変換フラグ：踊り字（Iteration mark）ゝゞ(平) ヽヾ(片)無変換 */
    public static final int NO_TRANSLATE_KH_IM = 0x04;
    /** 片平仮名変換フラグ：ヷを無変換 */
    public static final int NO_TRANSLATE_KH_WA = 0x08;
    /*
     * 片平仮名位置情報
     */
    /** ぁ（0x3041） */
    private static final int HIRA_MIN_INDEX = 12353;
    /** ん（0x3093） */
    private static final int HIRA_MAX_INDEX = 12435;
    /** ゔ（0x3094） */
    private static final int HIRA_VU_INDEX = 12436;
    /** ヵヶ（0x3095） */
    private static final int HIRA_KK_INDEX = 12437;
    /** 踊り字（Iteration mark（0x309d） */
    private static final int HIRA_IM_INDEX = 12445;
    /** ヷ（--） */
    // public static final int HIRA_WA_INDEX = --;
    /** ァ（0x30a1） */
    private static final int KATA_MIN_INDEX = 12449;
    /** ン（0x30f3） */
    private static final int KATA_MAX_INDEX = 12531;
    /** ヴ（0x30f4） */
    private static final int KATA_VU_INDEX = 12532;
    /** ヵヶ（0x30f5） */
    private static final int KATA_KK_INDEX = 12533;
    /** 踊り字（Iteration mark（0x30fd） */
    private static final int KATA_IM_INDEX = 12541;
    /** ヷ（30f7） */
    private static final int KATA_WA_INDEX = 12535;
    /** 'ア' - 'あ'の位置差分 */
    private static final int HK_DIFF = 96;
    /**
     * 全角文字リスト。
     */
    private static final String ZENKAKU_LIST =
            "！”＃＄％＆’（）＊＋，－．／０１２３４"
                    + "５６７８９：；＜＝＞？＠ＡＢＣＤＥＦＧＨ"
                    + "ＩＪＫＬＭＮＯＰＱＲＳＴＵＶＷＸＹＺ［￥"
                    + "］＾＿｀ａｂｃｄｅｆｇｈｉｊｋｌｍｎｏｐ"
                    + "ｑｒｓｔｕｖｗｘｙｚ｛｜｝￣。「」、・"
                    + "ァィゥェォャュョッーアイエオナニヌネノ"
                    + "マミムメモヤユヨラリルレロン゛゜　";
    /**
     * 全角カナリスト(カ、サ、タ、ハ)行とウ。
     */
    private static final String ZENKAKU_KASATAHA_LIST =
            "カキクケコサシスセソタチツテトハヒフヘホウ";
    /**
     * 全角カナリスト(ガ、ザ、ダ、バ)行とヴ。
     */
    private static final String ZENKAKU_GAZADABA_LIST =
            "ガギグゲゴザジズゼゾダヂヅデドバビブベボヴ";
    /**
     * 全角カナ(ワ"[&yen;30f7])。
     */
    private static final Character ZENKAKU_WA_DAKUTEN =
            new Character('\u30f7');
    /**
     * 全角カナ(ヲ"[&yen;30fa])。
     */
    private static final Character ZENKAKU_WO_DAKUTEN =
            new Character('\u30fa');
    /**
     * 全角カナリスト(パ)行。
     */
    private static final String ZENKAKU_PA_LIST = "パピプペポ";
    /**
     * 半角文字リスト。
     */
    private static final String HANKAKU_LIST =
            "!\"#$%&'()*+,-./0123456789:;<=>?@ABCDEFGH"
                    + "IJKLMNOPQRSTUVWXYZ[\\]^_`abcdefghijklmnop"
                    + "qrstuvwxyz{|}~｡｢｣､･ｧｨｩｪｫｬｭｮｯｰｱｲｴｵﾅﾆﾇﾈﾉ"
                    + "ﾏﾐﾑﾒﾓﾔﾕﾖﾗﾘﾙﾚﾛﾝﾞﾟ ";
    /**
     * 半角カナリスト(ｶ､ｻ､ﾀ､ﾊ)行とｳ。
     */
    private static final String HANKAKU_KASATAHA_LIST = "ｶｷｸｹｺｻｼｽｾｿﾀﾁﾂﾃﾄﾊﾋﾌﾍﾎｳ";
    /**
     * 半角カナリスト(ﾊ)行。
     */
    private static final String HANKAKU_HA_LIST = "ﾊﾋﾌﾍﾎ";

    /**
     * 全角文字を半角文字に変換する。
     * このメソッドでは以下の文字を対象とした変換処理を行う。<br>
     * <p>
     * <ul>
     * <li>半角文字リスト</li>
     * <li>半角カナ(ｶ､ｻ､ﾀ､ﾊ)行とｳ</li>
     * <li>半角カナ(ｶﾞ､ｻﾞ､ﾀﾞ､ﾊﾞ)行とｳﾞ</li>
     * <li>半角カナ(ﾊﾟ)行</li>
     * <li>半角カナ(ﾜﾞ､ｦﾞ)</li>
     * </ul>
     * </p>
     * @param c
     *            全角文字
     * @return 半角文字
     */
    private static String getHankakuMoji(char c) {
        int index = 0;
        String value = null;
        index = ZENKAKU_LIST.indexOf(c);
        if (index >= 0) {
            return String.valueOf(HANKAKU_LIST.charAt(index));
        }
        index = ZENKAKU_KASATAHA_LIST.indexOf(c);
        if (index >= 0) {
            // カキクケコサシスセソタチツテトハヒフヘホウ
            return String.valueOf(HANKAKU_KASATAHA_LIST.charAt(index));
        }
        index = ZENKAKU_GAZADABA_LIST.indexOf(c);
        if (index >= 0) {
            // ガギグゲゴザジズゼゾ"ダヂヅデドバビブベボヴ
            value = String.valueOf(HANKAKU_KASATAHA_LIST.charAt(index));
            return value + "ﾞ";
        }
        index = ZENKAKU_PA_LIST.indexOf(c);
        if (index >= 0) {
            // パピプペポ
            value = String.valueOf(HANKAKU_HA_LIST.charAt(index));
            return value + "ﾟ";
        } else if ((new Character(c)).equals(new Character('ワ'))) {
            // ワ
            return "ﾜ";
        } else if ((new Character(c)).equals(new Character('ヲ'))) {
            // ヲ
            return "ｦ";
        } else if ((new Character(c)).equals(ZENKAKU_WA_DAKUTEN)) {
            // ワ"[\u30f7]
            return "ﾜﾞ";
        } else if ((new Character(c)).equals(ZENKAKU_WO_DAKUTEN)) {
            // ヲ"[\u30fa]
            return "ｦﾞ";
        } else {
            // 該当なし
            return null;
        }
    }

    /**
     * 半角文字を全角文字に変換する。
     * 全角カナ(ガ、ザ、ダ、バ)行とヴの変換処理を行う。
     * @param c
     *            半角文字
     * @return 全角文字
     */
    private static String getZenkakuDakuMoji(char c) {
        int index = HANKAKU_KASATAHA_LIST.indexOf(c);
        if (index >= 0) {
            return String.valueOf(ZENKAKU_GAZADABA_LIST.charAt(index));
        }
        return null;
    }

    /**
     * 半角文字を全角文字に変換する。
     * 全角カナ(パ)行の変換処理を行う。
     * @param c
     *            半角文字
     * @return 全角文字
     */
    private static String getZenkakuHandakuMoji(char c) {
        int index = HANKAKU_HA_LIST.indexOf(c);
        if (index >= 0) {
            return String.valueOf(ZENKAKU_PA_LIST.charAt(index));
        }
        return null;
    }

    /**
     * 半角文字を全角文字に変換する。
     * 全角カナ(カ、サ、タ、ハ)行とウの変換処理を行う。
     * @param c
     *            半角文字
     * @return 全角文字
     */
    private static String getZenkakuKasatahaMoji(char c) {
        int index = HANKAKU_KASATAHA_LIST.indexOf(c);
        if (index >= 0) {
            return String.valueOf(ZENKAKU_KASATAHA_LIST.charAt(index));
        }
        return null;
    }

    /**
     * 半角文字を全角文字に変換する。
     * 全角文字リストの変換処理を行う。
     * @param c
     *            半角文字
     * @return 全角文字
     */
    private static String getZenkakuMoji(char c) {
        int index = HANKAKU_LIST.indexOf(c);
        if (index >= 0) {
            return String.valueOf(ZENKAKU_LIST.charAt(index));
        }
        return null;
    }

    /**
     * 半角文字列を全角文字列に変換する。
     * <p>
     * カナ文字に濁点または半濁点が続く場合は、可能な限り１文字に変換する。<br>
     * (例) 'ｶ' + 'ﾞ' =&gt; 'ガ'
     * </p>
     * <p>
     * またこの変換処理では以下の全角文字も変換先文字とされる。
     * </p>
     * <p>
     * <ul>
     * <li>「ヴ」</li>
     * <li>「ワ"」('ワ'に濁点：&yen;u30f7)</li>
     * <li>「ヲ"」('ヲ'に濁点：&yen;30fa)</li>
     * </ul>
     * </p>
     * @param value
     *            半角文字列
     * @return 全角文字列
     */
    public static String hankakuToZenkaku(String value) {
        if (value == null || "".equals(value)) {
            return value;
        }
        char[] chars = value.toCharArray();
        StringBuilder returnValue = new StringBuilder();
        String getValue = null;
        Character nextvalue = null;
        for (int i = 0; i < chars.length; i++) {
            getValue = getZenkakuMoji(chars[i]);
            if (getValue != null) {
                returnValue.append(getValue);
            } else if (i == (chars.length - 1)) {
                // 最後の文字
                getValue = getZenkakuKasatahaMoji(chars[i]);
                if (getValue != null) {
                    // ｶｷｸｹｺｻｼｽｾｿﾀﾁﾂﾃﾄﾊﾋﾌﾍﾎｳ
                    returnValue.append(getValue);
                } else if (new Character(chars[i]).equals(
                        new Character('ﾜ'))) {
                    returnValue.append("ワ");
                } else if (new Character(chars[i]).equals(
                        new Character('ｦ'))) {
                    returnValue.append("ヲ");
                } else {
                    returnValue.append(String.valueOf(chars[i]));
                }
            } else {
                nextvalue = new Character(chars[i + 1]);
                if (nextvalue.equals(new Character('ﾞ'))) {
                    getValue = getZenkakuDakuMoji(chars[i]);
                    if (getValue != null) {
                        // ｶﾞｷﾞｸﾞｹﾞｺﾞｻﾞｼﾞｽﾞｾﾞｿﾞﾀﾞﾁﾞﾂﾞﾃﾞﾄﾞﾊﾞﾋﾞﾌﾞﾍﾞﾎﾞｳﾞ
                        returnValue.append(getValue);
                        i++;
                    } else if (new Character(chars[i]).equals(
                            new Character('ﾜ'))) {
                        // ﾜﾞ
                        returnValue.append(ZENKAKU_WA_DAKUTEN);
                        i++;
                    } else if (new Character(chars[i]).equals(
                            new Character('ｦ'))) {
                        // ｦﾞ
                        returnValue.append(ZENKAKU_WO_DAKUTEN);
                        i++;
                    } else {
                        returnValue.append((String.valueOf(chars[i]) + "゛"));
                        i++;
                    }
                } else if (nextvalue.equals(new Character('ﾟ'))) {
                    getValue = getZenkakuHandakuMoji(chars[i]);
                    if (getValue != null) {
                        // ﾊﾟﾋﾟﾌﾟﾍﾟﾎﾟ
                        returnValue.append(getValue);
                        i++;
                    } else {
                        // ｶﾟｷﾟｸﾟｹﾟｺﾟｻﾟｼﾟｽﾟｾﾟｿﾟﾀﾟﾁﾟﾂﾟﾃﾟﾄﾟｳﾟ
                        getValue = getZenkakuKasatahaMoji(chars[i]);
                        returnValue.append((String.valueOf(getValue) + "゜"));
                        i++;
                    }
                } else {
                    getValue = getZenkakuKasatahaMoji(chars[i]);
                    if (getValue != null) {
                        // ｶｷｸｹｺｻｼｽｾｿﾀﾁﾂﾃﾄﾊﾋﾌﾍﾎｳ
                        returnValue.append(getValue);
                    } else if (new Character(chars[i]).equals(
                            new Character('ﾜ'))) {
                        returnValue.append("ワ");
                    } else if (new Character(chars[i]).equals(
                            new Character('ｦ'))) {
                        returnValue.append("ヲ");
                    } else {
                        returnValue.append(String.valueOf(chars[i]));
                    }
                }
            }
        }
        return returnValue.toString();
    }

    public static String hanToZen(String source) {
        // 空文字、nullならばそのまま返却
        if (isEmpty(source)) {
            return EMPTY;
        }
        return Normalizer.normalize(source, Form.NFKC);
    }

    /**
     * 
     * @param source
     * @return
     */
    public static String hiraToKataTranslate(String source) {
        return hiraToKataTranslate(source, NO_TRANSLATE_KH_KK);
    }

    /**
     * 
     * @param source
     * @param noTranslateFlag
     * @return
     */
    public static String hiraToKataTranslate(String source, int noTranslateFlag) {
        // 空文字、nullならばそのまま返却
        if (isEmpty(source)) {
            return EMPTY;
        }
        // char配列にする
        char[] charArray = source.toCharArray();
        for (int i = 0; i < charArray.length; i++) {
            int cInt = charArray[i];
            if ((cInt >= HIRA_MIN_INDEX && cInt <= HIRA_MAX_INDEX) ||
                    ((noTranslateFlag & NO_TRANSLATE_KH_VU) == 0 && cInt == HIRA_VU_INDEX) ||
                    ((noTranslateFlag & NO_TRANSLATE_KH_KK) == 0 && (cInt >= HIRA_KK_INDEX && cInt <= (HIRA_KK_INDEX + 1))) ||
                    ((noTranslateFlag & NO_TRANSLATE_KH_IM) == 0 && (cInt >= HIRA_IM_INDEX && cInt <= (HIRA_IM_INDEX + 1)))) {
                charArray[i] = (char) (cInt + HK_DIFF);
            }
        }
        // 変換後の文字列を返却
        return String.valueOf(charArray);
    }

    /**
     * 
     * @param source
     * @return
     */
    public static String kataToHiraTranslate(String source) {
        return kataToHiraTranslate(source, NO_TRANSLATE_KH_KK);
    }

    /**
     * 
     * @param source
     * @param noTranslateFlag
     * @return
     */
    public static String kataToHiraTranslate(String source, int noTranslateFlag) {
        // 空文字、nullならばそのまま返却
        if (isEmpty(source)) {
            return EMPTY;
        }
        // char配列にする
        char[] charArray = source.toCharArray();
        for (int i = 0; i < charArray.length; i++) {
            int cInt = charArray[i];
            if ((cInt >= KATA_MIN_INDEX && cInt <= KATA_MAX_INDEX) ||
                    ((noTranslateFlag & NO_TRANSLATE_KH_VU) == 0 && cInt == KATA_VU_INDEX) ||
                    ((noTranslateFlag & NO_TRANSLATE_KH_KK) == 0 && (cInt >= KATA_KK_INDEX && cInt <= (KATA_KK_INDEX + 1))) ||
                    ((noTranslateFlag & NO_TRANSLATE_KH_IM) == 0 && (cInt >= KATA_IM_INDEX && cInt <= (KATA_IM_INDEX + 1)))) {
                charArray[i] = (char) (cInt - HK_DIFF);
            } else if ((noTranslateFlag & NO_TRANSLATE_KH_WA) == 0 && cInt == KATA_WA_INDEX) {
                charArray[i] = (char) HIRA_VU_INDEX;
            }
        }
        // 変換後の文字列を返却
        return String.valueOf(charArray);
    }

    /**
     * 全角文字列を半角文字列に変換する。
     * <p>
     * 濁点または半濁点を持つカナ文字は、２文字に分解される。<br>
     * (例) 'ガ' =&gt; 'ｶ' + 'ﾞ'
     * </p>
     * <p>
     * またこの変換処理では以下の全角文字も変換元文字と受け付ける。
     * </p>
     * <p>
     * <ul>
     * <li>「ヴ」</li>
     * <li>「ワ"」('ワ'に濁点：&yen;u30f7)</li>
     * <li>「ヲ"」('ヲ'に濁点：&yen;30fa)</li>
     * </ul>
     * </p>
     * @param value
     *            全角文字列
     * @return 半角文字列
     */
    public static String zenkakuToHankaku(String value) {
        if (value == null || "".equals(value)) {
            return value;
        }
        char[] chars = value.toCharArray();
        StringBuilder returnValue = new StringBuilder();
        String getValue = null;
        for (int i = 0; i < chars.length; i++) {
            getValue = getHankakuMoji(chars[i]);
            if (getValue != null) {
                returnValue.append(getValue);
            } else {
                returnValue.append(String.valueOf(chars[i]));
            }
        }
        return returnValue.toString();
    }
}
