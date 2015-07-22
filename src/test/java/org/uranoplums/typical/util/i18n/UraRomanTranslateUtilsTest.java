/**
 *
 */
package org.uranoplums.typical.util.i18n;

import static org.junit.Assert.*;

import org.atilika.kuromoji.Token;
import org.atilika.kuromoji.Tokenizer;
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

	static Tokenizer tokenizer;
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
        tokenizer = Tokenizer.builder().build();
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

		assertEquals(UraRomanTranslationUtils.kanaToHepburn("あ"), "A");
		assertEquals(UraRomanTranslationUtils.kanaToHepburn("い"), "I");
		assertEquals(UraRomanTranslationUtils.kanaToHepburn("う"), "U");
		assertEquals(UraRomanTranslationUtils.kanaToHepburn("え"), "E");
		assertEquals(UraRomanTranslationUtils.kanaToHepburn("お"), "O");
		assertEquals(UraRomanTranslationUtils.kanaToHepburn("か"), "KA");
		assertEquals(UraRomanTranslationUtils.kanaToHepburn("き"), "KI");
		assertEquals(UraRomanTranslationUtils.kanaToHepburn("く"), "KU");
		assertEquals(UraRomanTranslationUtils.kanaToHepburn("け"), "KE");
		assertEquals(UraRomanTranslationUtils.kanaToHepburn("こ"), "KO");
		assertEquals(UraRomanTranslationUtils.kanaToHepburn("さ"), "SA");
		assertEquals(UraRomanTranslationUtils.kanaToHepburn("し"), "SHI");
		assertEquals(UraRomanTranslationUtils.kanaToHepburn("す"), "SU");
		assertEquals(UraRomanTranslationUtils.kanaToHepburn("せ"), "SE");
		assertEquals(UraRomanTranslationUtils.kanaToHepburn("そ"), "SO");
		assertEquals(UraRomanTranslationUtils.kanaToHepburn("た"), "TA");
		assertEquals(UraRomanTranslationUtils.kanaToHepburn("ち"), "CHI");
		assertEquals(UraRomanTranslationUtils.kanaToHepburn("つ"), "TSU");
		assertEquals(UraRomanTranslationUtils.kanaToHepburn("て"), "TE");
		assertEquals(UraRomanTranslationUtils.kanaToHepburn("と"), "TO");
		assertEquals(UraRomanTranslationUtils.kanaToHepburn("な"), "NA");
		assertEquals(UraRomanTranslationUtils.kanaToHepburn("に"), "NI");
		assertEquals(UraRomanTranslationUtils.kanaToHepburn("ぬ"), "NU");
		assertEquals(UraRomanTranslationUtils.kanaToHepburn("ね"), "NE");
		assertEquals(UraRomanTranslationUtils.kanaToHepburn("の"), "NO");
		assertEquals(UraRomanTranslationUtils.kanaToHepburn("は"), "HA");
		assertEquals(UraRomanTranslationUtils.kanaToHepburn("ひ"), "HI");
		assertEquals(UraRomanTranslationUtils.kanaToHepburn("ふ"), "FU");
		assertEquals(UraRomanTranslationUtils.kanaToHepburn("へ"), "HE");
		assertEquals(UraRomanTranslationUtils.kanaToHepburn("ほ"), "HO");
		assertEquals(UraRomanTranslationUtils.kanaToHepburn("ま"), "MA");
		assertEquals(UraRomanTranslationUtils.kanaToHepburn("み"), "MI");
		assertEquals(UraRomanTranslationUtils.kanaToHepburn("む"), "MU");
		assertEquals(UraRomanTranslationUtils.kanaToHepburn("め"), "ME");
		assertEquals(UraRomanTranslationUtils.kanaToHepburn("も"), "MO");
		assertEquals(UraRomanTranslationUtils.kanaToHepburn("や"), "YA");
		assertEquals(UraRomanTranslationUtils.kanaToHepburn("ゆ"), "YU");
		assertEquals(UraRomanTranslationUtils.kanaToHepburn("よ"), "YO");
		assertEquals(UraRomanTranslationUtils.kanaToHepburn("ら"), "RA");
		assertEquals(UraRomanTranslationUtils.kanaToHepburn("り"), "RI");
		assertEquals(UraRomanTranslationUtils.kanaToHepburn("る"), "RU");
		assertEquals(UraRomanTranslationUtils.kanaToHepburn("れ"), "RE");
		assertEquals(UraRomanTranslationUtils.kanaToHepburn("ろ"), "RO");
		assertEquals(UraRomanTranslationUtils.kanaToHepburn("わ"), "WA");
		assertEquals(UraRomanTranslationUtils.kanaToHepburn("ゐ"), "I");
		assertEquals(UraRomanTranslationUtils.kanaToHepburn("ゑ"), "E");
		assertEquals(UraRomanTranslationUtils.kanaToHepburn("を"), "O");
		assertEquals(UraRomanTranslationUtils.kanaToHepburn("が"), "GA");
		assertEquals(UraRomanTranslationUtils.kanaToHepburn("ぎ"), "GI");
		assertEquals(UraRomanTranslationUtils.kanaToHepburn("ぐ"), "GU");
		assertEquals(UraRomanTranslationUtils.kanaToHepburn("げ"), "GE");
		assertEquals(UraRomanTranslationUtils.kanaToHepburn("ご"), "GO");
		assertEquals(UraRomanTranslationUtils.kanaToHepburn("ざ"), "ZA");
		assertEquals(UraRomanTranslationUtils.kanaToHepburn("じ"), "JI");
		assertEquals(UraRomanTranslationUtils.kanaToHepburn("ず"), "ZU");
		assertEquals(UraRomanTranslationUtils.kanaToHepburn("ぜ"), "ZE");
		assertEquals(UraRomanTranslationUtils.kanaToHepburn("ぞ"), "ZO");
		assertEquals(UraRomanTranslationUtils.kanaToHepburn("だ"), "DA");
		assertEquals(UraRomanTranslationUtils.kanaToHepburn("ぢ"), "JI");
		assertEquals(UraRomanTranslationUtils.kanaToHepburn("づ"), "ZU");
		assertEquals(UraRomanTranslationUtils.kanaToHepburn("で"), "DE");
		assertEquals(UraRomanTranslationUtils.kanaToHepburn("ど"), "DO");
		assertEquals(UraRomanTranslationUtils.kanaToHepburn("ば"), "BA");
		assertEquals(UraRomanTranslationUtils.kanaToHepburn("び"), "BI");
		assertEquals(UraRomanTranslationUtils.kanaToHepburn("ぶ"), "BU");
		assertEquals(UraRomanTranslationUtils.kanaToHepburn("べ"), "BE");
		assertEquals(UraRomanTranslationUtils.kanaToHepburn("ぼ"), "BO");
		assertEquals(UraRomanTranslationUtils.kanaToHepburn("ぱ"), "PA");
		assertEquals(UraRomanTranslationUtils.kanaToHepburn("ぴ"), "PI");
		assertEquals(UraRomanTranslationUtils.kanaToHepburn("ぷ"), "PU");
		assertEquals(UraRomanTranslationUtils.kanaToHepburn("ぺ"), "PE");
		assertEquals(UraRomanTranslationUtils.kanaToHepburn("ぽ"), "PO");
		assertEquals(UraRomanTranslationUtils.kanaToHepburn("ゔ"), "VU");
		assertEquals(UraRomanTranslationUtils.kanaToHepburn("ん"), "N");
		assertEquals(UraRomanTranslationUtils.kanaToHepburn("ぁ"), "A");
		assertEquals(UraRomanTranslationUtils.kanaToHepburn("ぃ"), "I");
		assertEquals(UraRomanTranslationUtils.kanaToHepburn("ぅ"), "U");
		assertEquals(UraRomanTranslationUtils.kanaToHepburn("ぇ"), "E");
		assertEquals(UraRomanTranslationUtils.kanaToHepburn("ぉ"), "O");
		assertEquals(UraRomanTranslationUtils.kanaToHepburn("っ"), "TSU");
		assertEquals(UraRomanTranslationUtils.kanaToHepburn("ゃ"), "YA");
		assertEquals(UraRomanTranslationUtils.kanaToHepburn("ゅ"), "YU");
		assertEquals(UraRomanTranslationUtils.kanaToHepburn("ょ"), "YO");
		assertEquals(UraRomanTranslationUtils.kanaToHepburn("ゎ"), "WA");
		assertEquals(UraRomanTranslationUtils.kanaToHepburn("ゕ"), "KA");
		assertEquals(UraRomanTranslationUtils.kanaToHepburn("ゖ"), "KE");

	}

	@Test
	public final void testTranslateToRoman2Char() {

		assertEquals(UraRomanTranslationUtils.kanaToHepburn("きゃ"), "KYA");
		assertEquals(UraRomanTranslationUtils.kanaToHepburn("きゅ"), "KYU");
		assertEquals(UraRomanTranslationUtils.kanaToHepburn("きょ"), "KYO");
		assertEquals(UraRomanTranslationUtils.kanaToHepburn("しゃ"), "SHA");
		assertEquals(UraRomanTranslationUtils.kanaToHepburn("しゅ"), "SHU");
		assertEquals(UraRomanTranslationUtils.kanaToHepburn("しょ"), "SHO");
		assertEquals(UraRomanTranslationUtils.kanaToHepburn("ちゃ"), "CHA");
		assertEquals(UraRomanTranslationUtils.kanaToHepburn("ちゅ"), "CHU");
		assertEquals(UraRomanTranslationUtils.kanaToHepburn("ちょ"), "CHO");
		assertEquals(UraRomanTranslationUtils.kanaToHepburn("にゃ"), "NYA");
		assertEquals(UraRomanTranslationUtils.kanaToHepburn("にゅ"), "NYU");
		assertEquals(UraRomanTranslationUtils.kanaToHepburn("にょ"), "NYO");
		assertEquals(UraRomanTranslationUtils.kanaToHepburn("ひゃ"), "HYA");
		assertEquals(UraRomanTranslationUtils.kanaToHepburn("ひゅ"), "HYU");
		assertEquals(UraRomanTranslationUtils.kanaToHepburn("ひょ"), "HYO");
		assertEquals(UraRomanTranslationUtils.kanaToHepburn("みゃ"), "MYA");
		assertEquals(UraRomanTranslationUtils.kanaToHepburn("みゅ"), "MYU");
		assertEquals(UraRomanTranslationUtils.kanaToHepburn("みょ"), "MYO");
		assertEquals(UraRomanTranslationUtils.kanaToHepburn("りゃ"), "RYA");
		assertEquals(UraRomanTranslationUtils.kanaToHepburn("りゅ"), "RYU");
		assertEquals(UraRomanTranslationUtils.kanaToHepburn("りょ"), "RYO");
		assertEquals(UraRomanTranslationUtils.kanaToHepburn("ぎゃ"), "GYA");
		assertEquals(UraRomanTranslationUtils.kanaToHepburn("ぎゅ"), "GYU");
		assertEquals(UraRomanTranslationUtils.kanaToHepburn("ぎょ"), "GYO");
		assertEquals(UraRomanTranslationUtils.kanaToHepburn("じゃ"), "JA");
		assertEquals(UraRomanTranslationUtils.kanaToHepburn("じゅ"), "JU");
		assertEquals(UraRomanTranslationUtils.kanaToHepburn("じょ"), "JO");
//		assertEquals(UraRomanTranslationUtils.translateToRoman("でゃ"), "JA");
//		assertEquals(UraRomanTranslationUtils.translateToRoman("でゅ"), "JU");
//		assertEquals(UraRomanTranslationUtils.translateToRoman("でょ"), "JO");
		assertEquals(UraRomanTranslationUtils.kanaToHepburn("びゃ"), "BYA");
		assertEquals(UraRomanTranslationUtils.kanaToHepburn("びゅ"), "BYU");
		assertEquals(UraRomanTranslationUtils.kanaToHepburn("びょ"), "BYO");
		assertEquals(UraRomanTranslationUtils.kanaToHepburn("ぴゃ"), "PYA");
		assertEquals(UraRomanTranslationUtils.kanaToHepburn("ぴゅ"), "PYU");
		assertEquals(UraRomanTranslationUtils.kanaToHepburn("ぴょ"), "PYO");
	}

	@Test
	public final void testTranslateToRomanSepChar() {

		assertEquals(UraRomanTranslationUtils.kanaToHepburn("いぇ"), "IE");
		assertEquals(UraRomanTranslationUtils.kanaToHepburn("うぃ"), "UI");
		assertEquals(UraRomanTranslationUtils.kanaToHepburn("うぇ"), "UE");
		assertEquals(UraRomanTranslationUtils.kanaToHepburn("うぉ"), "UO");
		assertEquals(UraRomanTranslationUtils.kanaToHepburn("ゔぁ"), "VA");
		assertEquals(UraRomanTranslationUtils.kanaToHepburn("ゔぃ"), "VI");
		assertEquals(UraRomanTranslationUtils.kanaToHepburn("ゔぇ"), "VE");
		assertEquals(UraRomanTranslationUtils.kanaToHepburn("ゔぉ"), "VO");
		assertEquals(UraRomanTranslationUtils.kanaToHepburn("くぁ"), "KUA");
		assertEquals(UraRomanTranslationUtils.kanaToHepburn("くぃ"), "KUI");
		assertEquals(UraRomanTranslationUtils.kanaToHepburn("くぇ"), "KUE");
		assertEquals(UraRomanTranslationUtils.kanaToHepburn("くぉ"), "KUO");
		assertEquals(UraRomanTranslationUtils.kanaToHepburn("ぐぁ"), "GUA");
		assertEquals(UraRomanTranslationUtils.kanaToHepburn("ぐぃ"), "GUI");
		assertEquals(UraRomanTranslationUtils.kanaToHepburn("ぐぇ"), "GUE");
		assertEquals(UraRomanTranslationUtils.kanaToHepburn("ぐぉ"), "GUO");
		assertEquals(UraRomanTranslationUtils.kanaToHepburn("じぇ"), "JIE");
		assertEquals(UraRomanTranslationUtils.kanaToHepburn("ちぇ"), "CHIE");
		assertEquals(UraRomanTranslationUtils.kanaToHepburn("つぁ"), "TSUA");
		assertEquals(UraRomanTranslationUtils.kanaToHepburn("つぃ"), "TSUI");
		assertEquals(UraRomanTranslationUtils.kanaToHepburn("つぇ"), "TSUE");
		assertEquals(UraRomanTranslationUtils.kanaToHepburn("つぉ"), "TSUO");
		assertEquals(UraRomanTranslationUtils.kanaToHepburn("てぃ"), "TEI");
		assertEquals(UraRomanTranslationUtils.kanaToHepburn("でぃ"), "DEI");
		assertEquals(UraRomanTranslationUtils.kanaToHepburn("でゅ"), "DEYU");
		assertEquals(UraRomanTranslationUtils.kanaToHepburn("どぅ"), "DOU");
		assertEquals(UraRomanTranslationUtils.kanaToHepburn("ふぁ"), "FUA");
		assertEquals(UraRomanTranslationUtils.kanaToHepburn("ふぃ"), "FUI");
		assertEquals(UraRomanTranslationUtils.kanaToHepburn("ふぇ"), "FUE");
		assertEquals(UraRomanTranslationUtils.kanaToHepburn("ふぉ"), "FUO");
		assertEquals(UraRomanTranslationUtils.kanaToHepburn("ふょ"), "FUYO");
		assertEquals(UraRomanTranslationUtils.kanaToHepburn("おう"), "O");
		assertEquals(UraRomanTranslationUtils.kanaToHepburn("おお"), "O");
	}

	@Test
	public final void testWords() {
		StringBuilder sb = new StringBuilder();
		for(Token t: tokenizer.tokenize("遠足に行く姉さんの笑顔と、母さんのドヤ顔")) {
			String s = UraRomanTranslationUtils.kanaToHepburn((t.getReading() != null)?t.getReading():t.getSurfaceForm(), 0);
			sb.append(UraJaStringUtils.capitalize(s.toLowerCase()));
		}
//		assertEquals(sb.toString(), "");
        assertTrue(true);
	}
	@Test
	public final void testWords2() {
		StringBuilder sb = new StringBuilder();
		//for(int i = 0; i < 10; i++) {
		for(Token t: tokenizer.tokenize("いすゞ、映画を見に行きましょう。証券番号")) {
			String s = UraRomanTranslationUtils.kanaToHepburn((t.getReading() != null)?t.getReading():t.getSurfaceForm(), 0);
			sb.append(UraJaStringUtils.capitalize(s.toLowerCase()));
		}
		//}
//		assertEquals(sb.toString(), "");
        assertTrue(true);
	}
	@Test
	public final void testWords3() {
		StringBuilder sb = new StringBuilder();
		for(Token t: tokenizer.tokenize("すゝめ、、づゝ、ぶゞ漬け映画を見に行きましょう。証券番号")) {
			String s = UraRomanTranslationUtils.kanaToHepburn((t.getReading() != null)?t.getReading():t.getSurfaceForm(), 0);
			sb.append(UraJaStringUtils.capitalize(s.toLowerCase()));
		}
//		assertEquals(sb.toString(), "");
        assertTrue(true);
	}
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
					String s = UraRomanTranslationUtils.kanaToHepburn(t, 0);
					sb.append(UraJaStringUtils.capitalize(s.toLowerCase()));
				}
				//}
			}

		}).run(sb);
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
