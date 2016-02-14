/**
 *
 */
package org.uranoplums.typical.util.i18n;

import static org.junit.Assert.*;

//import org.atilika.kuromoji.Token;
//import org.atilika.kuromoji.Tokenizer;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.uranoplums.typical.interceptor.AbsUraTimingFunctionInterceptor;

/**
 * @author syany
 *
 */
public class UraRomanTranslateUtilsTest {

//	static Tokenizer tokenizer;
	static String[] dict = {
		"カァサン, "
		,"1ヶゲツ"
		,"トウキョウ、ストーリー, "
		,"キンガシンネン, "
		,"エイガヲミル、ニィサン, "
		,"オウサマ、ゴキゲンヨウ, "
		,"いすゞ, "
		,"すゝめ, ",
		"づゝ, ",
		"ぶゞ漬け"
	};
	/**
	 * @throws java.lang.Exception
	 */
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
//        tokenizer = Tokenizer.builder().build();
	}

	/**
	 * @throws java.lang.Exception
	 */
	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
	}

	/**
	 * @throws java.lang.Exception
	 */
	@After
	public void tearDown() throws Exception {
	}

	/**
	 * {@link org.uranoplums.typical.util.i18n.UraRomanTranslationUtils#kanaToHepburn2(java.lang.String, int)} のためのテスト・メソッド。
	 */
	@Test
	public final void testTranslateToRoman1Char() {

		assertEquals(UraRomanTranslationUtils.hiraganaToHepburn("あ"), "A");
		assertEquals(UraRomanTranslationUtils.hiraganaToHepburn("い"), "I");
		assertEquals(UraRomanTranslationUtils.hiraganaToHepburn("う"), "U");
		assertEquals(UraRomanTranslationUtils.hiraganaToHepburn("え"), "E");
		assertEquals(UraRomanTranslationUtils.hiraganaToHepburn("お"), "O");
		assertEquals(UraRomanTranslationUtils.hiraganaToHepburn("か"), "KA");
		assertEquals(UraRomanTranslationUtils.hiraganaToHepburn("き"), "KI");
		assertEquals(UraRomanTranslationUtils.hiraganaToHepburn("く"), "KU");
		assertEquals(UraRomanTranslationUtils.hiraganaToHepburn("け"), "KE");
		assertEquals(UraRomanTranslationUtils.hiraganaToHepburn("こ"), "KO");
		assertEquals(UraRomanTranslationUtils.hiraganaToHepburn("さ"), "SA");
		assertEquals(UraRomanTranslationUtils.hiraganaToHepburn("し"), "SHI");
		assertEquals(UraRomanTranslationUtils.hiraganaToHepburn("す"), "SU");
		assertEquals(UraRomanTranslationUtils.hiraganaToHepburn("せ"), "SE");
		assertEquals(UraRomanTranslationUtils.hiraganaToHepburn("そ"), "SO");
		assertEquals(UraRomanTranslationUtils.hiraganaToHepburn("た"), "TA");
		assertEquals(UraRomanTranslationUtils.hiraganaToHepburn("ち"), "CHI");
		assertEquals(UraRomanTranslationUtils.hiraganaToHepburn("つ"), "TSU");
		assertEquals(UraRomanTranslationUtils.hiraganaToHepburn("て"), "TE");
		assertEquals(UraRomanTranslationUtils.hiraganaToHepburn("と"), "TO");
		assertEquals(UraRomanTranslationUtils.hiraganaToHepburn("な"), "NA");
		assertEquals(UraRomanTranslationUtils.hiraganaToHepburn("に"), "NI");
		assertEquals(UraRomanTranslationUtils.hiraganaToHepburn("ぬ"), "NU");
		assertEquals(UraRomanTranslationUtils.hiraganaToHepburn("ね"), "NE");
		assertEquals(UraRomanTranslationUtils.hiraganaToHepburn("の"), "NO");
		assertEquals(UraRomanTranslationUtils.hiraganaToHepburn("は"), "HA");
		assertEquals(UraRomanTranslationUtils.hiraganaToHepburn("ひ"), "HI");
		assertEquals(UraRomanTranslationUtils.hiraganaToHepburn("ふ"), "FU");
		assertEquals(UraRomanTranslationUtils.hiraganaToHepburn("へ"), "HE");
		assertEquals(UraRomanTranslationUtils.hiraganaToHepburn("ほ"), "HO");
		assertEquals(UraRomanTranslationUtils.hiraganaToHepburn("ま"), "MA");
		assertEquals(UraRomanTranslationUtils.hiraganaToHepburn("み"), "MI");
		assertEquals(UraRomanTranslationUtils.hiraganaToHepburn("む"), "MU");
		assertEquals(UraRomanTranslationUtils.hiraganaToHepburn("め"), "ME");
		assertEquals(UraRomanTranslationUtils.hiraganaToHepburn("も"), "MO");
		assertEquals(UraRomanTranslationUtils.hiraganaToHepburn("や"), "YA");
		assertEquals(UraRomanTranslationUtils.hiraganaToHepburn("ゆ"), "YU");
		assertEquals(UraRomanTranslationUtils.hiraganaToHepburn("よ"), "YO");
		assertEquals(UraRomanTranslationUtils.hiraganaToHepburn("ら"), "RA");
		assertEquals(UraRomanTranslationUtils.hiraganaToHepburn("り"), "RI");
		assertEquals(UraRomanTranslationUtils.hiraganaToHepburn("る"), "RU");
		assertEquals(UraRomanTranslationUtils.hiraganaToHepburn("れ"), "RE");
		assertEquals(UraRomanTranslationUtils.hiraganaToHepburn("ろ"), "RO");
		assertEquals(UraRomanTranslationUtils.hiraganaToHepburn("わ"), "WA");
		assertEquals(UraRomanTranslationUtils.hiraganaToHepburn("ゐ"), "I");
		assertEquals(UraRomanTranslationUtils.hiraganaToHepburn("ゑ"), "E");
		assertEquals(UraRomanTranslationUtils.hiraganaToHepburn("を"), "O");
		assertEquals(UraRomanTranslationUtils.hiraganaToHepburn("が"), "GA");
		assertEquals(UraRomanTranslationUtils.hiraganaToHepburn("ぎ"), "GI");
		assertEquals(UraRomanTranslationUtils.hiraganaToHepburn("ぐ"), "GU");
		assertEquals(UraRomanTranslationUtils.hiraganaToHepburn("げ"), "GE");
		assertEquals(UraRomanTranslationUtils.hiraganaToHepburn("ご"), "GO");
		assertEquals(UraRomanTranslationUtils.hiraganaToHepburn("ざ"), "ZA");
		assertEquals(UraRomanTranslationUtils.hiraganaToHepburn("じ"), "JI");
		assertEquals(UraRomanTranslationUtils.hiraganaToHepburn("ず"), "ZU");
		assertEquals(UraRomanTranslationUtils.hiraganaToHepburn("ぜ"), "ZE");
		assertEquals(UraRomanTranslationUtils.hiraganaToHepburn("ぞ"), "ZO");
		assertEquals(UraRomanTranslationUtils.hiraganaToHepburn("だ"), "DA");
		assertEquals(UraRomanTranslationUtils.hiraganaToHepburn("ぢ"), "JI");
		assertEquals(UraRomanTranslationUtils.hiraganaToHepburn("づ"), "ZU");
		assertEquals(UraRomanTranslationUtils.hiraganaToHepburn("で"), "DE");
		assertEquals(UraRomanTranslationUtils.hiraganaToHepburn("ど"), "DO");
		assertEquals(UraRomanTranslationUtils.hiraganaToHepburn("ば"), "BA");
		assertEquals(UraRomanTranslationUtils.hiraganaToHepburn("び"), "BI");
		assertEquals(UraRomanTranslationUtils.hiraganaToHepburn("ぶ"), "BU");
		assertEquals(UraRomanTranslationUtils.hiraganaToHepburn("べ"), "BE");
		assertEquals(UraRomanTranslationUtils.hiraganaToHepburn("ぼ"), "BO");
		assertEquals(UraRomanTranslationUtils.hiraganaToHepburn("ぱ"), "PA");
		assertEquals(UraRomanTranslationUtils.hiraganaToHepburn("ぴ"), "PI");
		assertEquals(UraRomanTranslationUtils.hiraganaToHepburn("ぷ"), "PU");
		assertEquals(UraRomanTranslationUtils.hiraganaToHepburn("ぺ"), "PE");
		assertEquals(UraRomanTranslationUtils.hiraganaToHepburn("ぽ"), "PO");
		assertEquals(UraRomanTranslationUtils.hiraganaToHepburn("ゔ"), "VU");
		assertEquals(UraRomanTranslationUtils.hiraganaToHepburn("ん"), "N");
		assertEquals(UraRomanTranslationUtils.hiraganaToHepburn("ぁ"), "A");
		assertEquals(UraRomanTranslationUtils.hiraganaToHepburn("ぃ"), "I");
		assertEquals(UraRomanTranslationUtils.hiraganaToHepburn("ぅ"), "U");
		assertEquals(UraRomanTranslationUtils.hiraganaToHepburn("ぇ"), "E");
		assertEquals(UraRomanTranslationUtils.hiraganaToHepburn("ぉ"), "O");
		assertEquals(UraRomanTranslationUtils.hiraganaToHepburn("っ"), "TSU");
		assertEquals(UraRomanTranslationUtils.hiraganaToHepburn("ゃ"), "YA");
		assertEquals(UraRomanTranslationUtils.hiraganaToHepburn("ゅ"), "YU");
		assertEquals(UraRomanTranslationUtils.hiraganaToHepburn("ょ"), "YO");
		assertEquals(UraRomanTranslationUtils.hiraganaToHepburn("ゎ"), "WA");
		assertEquals(UraRomanTranslationUtils.hiraganaToHepburn("ゕ"), "KA");
		assertEquals(UraRomanTranslationUtils.hiraganaToHepburn("ゖ"), "KE");

	}

	@Test
	public final void testTranslateToRoman2Char() {

		assertEquals(UraRomanTranslationUtils.hiraganaToHepburn("きゃ"), "KYA");
		assertEquals(UraRomanTranslationUtils.hiraganaToHepburn("きゅ"), "KYU");
		assertEquals(UraRomanTranslationUtils.hiraganaToHepburn("きょ"), "KYO");
		assertEquals(UraRomanTranslationUtils.hiraganaToHepburn("しゃ"), "SHA");
		assertEquals(UraRomanTranslationUtils.hiraganaToHepburn("しゅ"), "SHU");
		assertEquals(UraRomanTranslationUtils.hiraganaToHepburn("しょ"), "SHO");
		assertEquals(UraRomanTranslationUtils.hiraganaToHepburn("ちゃ"), "CHA");
		assertEquals(UraRomanTranslationUtils.hiraganaToHepburn("ちゅ"), "CHU");
		assertEquals(UraRomanTranslationUtils.hiraganaToHepburn("ちょ"), "CHO");
		assertEquals(UraRomanTranslationUtils.hiraganaToHepburn("にゃ"), "NYA");
		assertEquals(UraRomanTranslationUtils.hiraganaToHepburn("にゅ"), "NYU");
		assertEquals(UraRomanTranslationUtils.hiraganaToHepburn("にょ"), "NYO");
		assertEquals(UraRomanTranslationUtils.hiraganaToHepburn("ひゃ"), "HYA");
		assertEquals(UraRomanTranslationUtils.hiraganaToHepburn("ひゅ"), "HYU");
		assertEquals(UraRomanTranslationUtils.hiraganaToHepburn("ひょ"), "HYO");
		assertEquals(UraRomanTranslationUtils.hiraganaToHepburn("みゃ"), "MYA");
		assertEquals(UraRomanTranslationUtils.hiraganaToHepburn("みゅ"), "MYU");
		assertEquals(UraRomanTranslationUtils.hiraganaToHepburn("みょ"), "MYO");
		assertEquals(UraRomanTranslationUtils.hiraganaToHepburn("りゃ"), "RYA");
		assertEquals(UraRomanTranslationUtils.hiraganaToHepburn("りゅ"), "RYU");
		assertEquals(UraRomanTranslationUtils.hiraganaToHepburn("りょ"), "RYO");
		assertEquals(UraRomanTranslationUtils.hiraganaToHepburn("ぎゃ"), "GYA");
		assertEquals(UraRomanTranslationUtils.hiraganaToHepburn("ぎゅ"), "GYU");
		assertEquals(UraRomanTranslationUtils.hiraganaToHepburn("ぎょ"), "GYO");
		assertEquals(UraRomanTranslationUtils.hiraganaToHepburn("じゃ"), "JA");
		assertEquals(UraRomanTranslationUtils.hiraganaToHepburn("じゅ"), "JU");
		assertEquals(UraRomanTranslationUtils.hiraganaToHepburn("じょ"), "JO");
//		assertEquals(UraRomanTranslationUtils.translateToRoman("でゃ"), "JA");
//		assertEquals(UraRomanTranslationUtils.translateToRoman("でゅ"), "JU");
//		assertEquals(UraRomanTranslationUtils.translateToRoman("でょ"), "JO");
		assertEquals(UraRomanTranslationUtils.hiraganaToHepburn("びゃ"), "BYA");
		assertEquals(UraRomanTranslationUtils.hiraganaToHepburn("びゅ"), "BYU");
		assertEquals(UraRomanTranslationUtils.hiraganaToHepburn("びょ"), "BYO");
		assertEquals(UraRomanTranslationUtils.hiraganaToHepburn("ぴゃ"), "PYA");
		assertEquals(UraRomanTranslationUtils.hiraganaToHepburn("ぴゅ"), "PYU");
		assertEquals(UraRomanTranslationUtils.hiraganaToHepburn("ぴょ"), "PYO");
	}

	@Test
	public final void testTranslateToRomanSepChar() {

		assertEquals(UraRomanTranslationUtils.hiraganaToHepburn("いぇ"), "IE");
		assertEquals(UraRomanTranslationUtils.hiraganaToHepburn("うぃ"), "UI");
		assertEquals(UraRomanTranslationUtils.hiraganaToHepburn("うぇ"), "UE");
		assertEquals(UraRomanTranslationUtils.hiraganaToHepburn("うぉ"), "UO");
		assertEquals(UraRomanTranslationUtils.hiraganaToHepburn("ゔぁ"), "VA");
		assertEquals(UraRomanTranslationUtils.hiraganaToHepburn("ゔぃ"), "VI");
		assertEquals(UraRomanTranslationUtils.hiraganaToHepburn("ゔぇ"), "VE");
		assertEquals(UraRomanTranslationUtils.hiraganaToHepburn("ゔぉ"), "VO");
		assertEquals(UraRomanTranslationUtils.hiraganaToHepburn("くぁ"), "KUA");
		assertEquals(UraRomanTranslationUtils.hiraganaToHepburn("くぃ"), "KUI");
		assertEquals(UraRomanTranslationUtils.hiraganaToHepburn("くぇ"), "KUE");
		assertEquals(UraRomanTranslationUtils.hiraganaToHepburn("くぉ"), "KUO");
		assertEquals(UraRomanTranslationUtils.hiraganaToHepburn("ぐぁ"), "GUA");
		assertEquals(UraRomanTranslationUtils.hiraganaToHepburn("ぐぃ"), "GUI");
		assertEquals(UraRomanTranslationUtils.hiraganaToHepburn("ぐぇ"), "GUE");
		assertEquals(UraRomanTranslationUtils.hiraganaToHepburn("ぐぉ"), "GUO");
		assertEquals(UraRomanTranslationUtils.hiraganaToHepburn("じぇ"), "JIE");
		assertEquals(UraRomanTranslationUtils.hiraganaToHepburn("ちぇ"), "CHIE");
		assertEquals(UraRomanTranslationUtils.hiraganaToHepburn("つぁ"), "TSUA");
		assertEquals(UraRomanTranslationUtils.hiraganaToHepburn("つぃ"), "TSUI");
		assertEquals(UraRomanTranslationUtils.hiraganaToHepburn("つぇ"), "TSUE");
		assertEquals(UraRomanTranslationUtils.hiraganaToHepburn("つぉ"), "TSUO");
		assertEquals(UraRomanTranslationUtils.hiraganaToHepburn("てぃ"), "TEI");
		assertEquals(UraRomanTranslationUtils.hiraganaToHepburn("でぃ"), "DEI");
		assertEquals(UraRomanTranslationUtils.hiraganaToHepburn("でゅ"), "DEYU");
		assertEquals(UraRomanTranslationUtils.hiraganaToHepburn("どぅ"), "DOU");
		assertEquals(UraRomanTranslationUtils.hiraganaToHepburn("ふぁ"), "FUA");
		assertEquals(UraRomanTranslationUtils.hiraganaToHepburn("ふぃ"), "FUI");
		assertEquals(UraRomanTranslationUtils.hiraganaToHepburn("ふぇ"), "FUE");
		assertEquals(UraRomanTranslationUtils.hiraganaToHepburn("ふぉ"), "FUO");
		assertEquals(UraRomanTranslationUtils.hiraganaToHepburn("ふょ"), "FUYO");
		assertEquals(UraRomanTranslationUtils.hiraganaToHepburn("おう"), "O");
		assertEquals(UraRomanTranslationUtils.hiraganaToHepburn("おお"), "O");
	}

//	@Test
//	public final void testWords() {
//		StringBuilder sb = new StringBuilder();
//		for(Token t: tokenizer.tokenize("遠足に行く姉さんの笑顔と、母さんのドヤ顔")) {
//			String s = UraRomanTranslationUtils.kanaToHepburn((t.getReading() != null)?t.getReading():t.getSurfaceForm(), 0);
//			sb.append(UraJaStringUtils.capitalize(s.toLowerCase()));
//		}
//        assertTrue(true);
//	}
//	@Test
//	public final void testWords2() {
//		StringBuilder sb = new StringBuilder();
//		for(Token t: tokenizer.tokenize("いすゞ、映画を見に行きましょう。証券番号")) {
//			String s = UraRomanTranslationUtils.kanaToHepburn((t.getReading() != null)?t.getReading():t.getSurfaceForm(), 0);
//			sb.append(UraJaStringUtils.capitalize(s.toLowerCase()));
//		}
//        assertTrue(true);
//	}
//	@Test
//	public final void testWords3() {
//		StringBuilder sb = new StringBuilder();
//		for(Token t: tokenizer.tokenize("すゝめ、、づゝ、ぶゞ漬け映画を見に行きましょう。証券番号")) {
//			String s = UraRomanTranslationUtils.kanaToHepburn((t.getReading() != null)?t.getReading():t.getSurfaceForm(), 0);
//			sb.append(UraJaStringUtils.capitalize(s.toLowerCase()));
//		}
//        assertTrue(true);
//	}
	@Test
	public final void testWords4() {
		StringBuilder sb = new StringBuilder();

		sb = (new AbsUraTimingFunctionInterceptor<StringBuilder>() {

			/* (非 Javadoc)
			 * @see org.uranoplums.typical.interceptor.AbsUraFunctionInterceptor#funcFinally()
			 */
			@Override
			protected void funcFinally() {
				super.funcFinally();
				this.printDiff();
			}

			@Override
			protected void callback(StringBuilder sb) {

				//for(int i = 0; i < 100000; i++) {
//					sb  = new StringBuilder();
				for(String t: dict) {
					String s = UraRomanTranslationUtils.hiraganaToHepburn(t, 0);
					sb.append(UraJaStringUtils.capitalize(s.toLowerCase()));
				}
				//}
			}

		}).run(sb);
		System.out.println("a:"+sb.toString());
//		assertEquals(sb.toString(), "");
        assertTrue(true);
	}
//
//	@Test
//	public final void testWords34() {
//		StringBuilder sb = new StringBuilder();
//		for(int i = 0; i < 100000; i++) {
//			sb  = new StringBuilder();
//		for(String t: dict) {
//			String s = UraRomanTranslationUtils.kanaToHepburn(t, 0);
//			sb.append(UraJaStringUtils.capitalize(s.toLowerCase()));
//		}
//		}
//		assertEquals(sb.toString(), "");
//	}
}
