/**
 *
 */
package org.uranoplums.typical.util.i18n;

import static org.junit.Assert.*;

import java.nio.charset.Charset;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.uranoplums.typical.lang.UraObject;

/**
 * 。<br>
 * @author syany
 *
 */
public class UraCharsetTest extends UraObject {

	/**
	 * @throws java.lang.Exception
	 */
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
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

//	@Test
//	public void testgetCharsetASCII() {
//		byte[] byteArray = "test strings, @098".getBytes(UraCharset.ASCII);
//		Charset target = UraCharset.ME.setUpDecoderListLower().getCharset(byteArray);
//
//		assertEquals(target, Charset.forName("US-ASCII"));
//	}
//	@Test
//	public void testgetCharsetLATIN_1() {
//		byte[] byteArray = "テスト羅将文字列～①".getBytes(UraCharset.LATIN_1);
//		Charset target = UraCharset.ME.setUpDecoderListLower().getCharset(byteArray);
//
//		assertEquals(target, Charset.forName("ISO-8859-1"));
//	}
	@Test
	public void testgetCharsetJIS() {
		byte[] byteArray = "テスト羅将文字列～①".getBytes(UraCharset.JIS);
		Charset target = UraCharset.ME.setUpDecoderListLower().getCharset(byteArray);

		assertEquals(target, Charset.forName("ISO-2022-JP"));
	}
	@Test
	public void testgetCharsetISO_2022_JP_2() {
		byte[] byteArray = "テスト羅将文字列～①".getBytes(UraCharset.ISO_2022_JP_2);
		Charset target = UraCharset.ME.setUpDecoderListLower().getCharset(byteArray);

		assertEquals(target, Charset.forName("ISO-2022-JP-2"));
	}

	@Test
	public void testgetCharsetEUC_JP_LINUX() {
		byte[] byteArray = "テスト羅将文字列～①".getBytes(UraCharset.EUC_JP_LINUX);
		Charset target = UraCharset.ME.setUpDecoderListLower().getCharset(byteArray);

		assertEquals(target, Charset.forName("x-euc-jp-linux"));
	}
	@Test
	public void testgetCharsetEUCJP() {
		byte[] byteArray = "テスト羅将文字列～①".getBytes(UraCharset.EUC_JP);
		Charset target = UraCharset.ME.setUpDecoderListLower().getCharset(byteArray);

		assertEquals(target, Charset.forName("EUC-JP"));
	}
	@Test
	public void testgetCharsetEUC_JP_OPEN() {
		byte[] byteArray = "テスト羅将文字列～①".getBytes(UraCharset.EUC_JP_OPEN);
		Charset target = UraCharset.ME.setUpDecoderListLower().getCharset(byteArray);

		assertEquals(target, Charset.forName("x-eucJP-Open"));
	}
	@Test
	public void testgetCharsetShiftJIS() {
		byte[] byteArray = "テスト羅将文字列～①".getBytes(UraCharset.SHIFT_JIS);
		Charset target = UraCharset.ME.setUpDecoderListLower().getCharset(byteArray);

		assertEquals(target, Charset.forName("Shift_JIS"));
	}
	@Test
	public void testgetCharsetWINDOWS_31J() {
		byte[] byteArray = "テスト羅将文字列～①".getBytes(UraCharset.WINDOWS_31J);
		Charset target = UraCharset.ME.setUpDecoderListLower().getCharset(byteArray);

		assertEquals(target, Charset.forName("windows-31j"));
	}
	@Test
	public void testgetCharsetUTF8() {
		byte[] byteArray = "テスト羅将文字列～①".getBytes(UraCharset.UTF8);
		Charset target = UraCharset.ME.setUpDecoderListLower().getCharset(byteArray);

		assertEquals(target, Charset.forName("UTF-8"));
	}
	@Test
	public void testgetCharsetUTF16BE() {
		byte[] byteArray = "テスト羅将文字列～①".getBytes(UraCharset.UTF16BE);
		Charset target = UraCharset.ME.setUpDecoderListLower().getCharset(byteArray);

		assertEquals(target, Charset.forName("UTF-16BE"));
	}

//	@Test
//	public void testgetCharsetUTF32() {
//		byte[] byteArray = "テスト羅将文字列～①ДⅢ".getBytes(UraCharset.UTF32);
//		Charset target = UraCharset.ME.setUpDecoderListLower().getCharset(byteArray);
//
//		assertEquals(target, Charset.forName("UTF-32"));
//	}


	@Test
	public void testgetCharsetJISGreaterIsISO_2022_JP_2() {
		byte[] byteArray = "テスト羅将文字列～①".getBytes(UraCharset.JIS);
		Charset target = UraCharset.ME.setUpDecoderListGreater().getCharset(byteArray);

		assertEquals(target, Charset.forName("ISO-2022-JP-2"));
	}
	@Test
	public void testgetCharsetISO_2022_JP_2Greater() {
		byte[] byteArray = "テスト羅将文字列～①".getBytes(UraCharset.ISO_2022_JP_2);
		Charset target = UraCharset.ME.setUpDecoderListGreater().getCharset(byteArray);

		assertEquals(target, Charset.forName("ISO-2022-JP-2"));
	}

	@Test
	public void testgetCharsetEUC_JP_LINUXGreaterIsEUC_JP_OPEN() {
		byte[] byteArray = "テスト羅将文字列～①".getBytes(UraCharset.EUC_JP_LINUX);
		Charset target = UraCharset.ME.setUpDecoderListGreater().getCharset(byteArray);

		assertEquals(target, Charset.forName("x-eucJP-Open"));
	}
	@Test
	public void testgetCharsetEUCJPGreaterIsEUC_JP_OPEN() {
		byte[] byteArray = "テスト羅将文字列～①".getBytes(UraCharset.EUC_JP);
		Charset target = UraCharset.ME.setUpDecoderListGreater().getCharset(byteArray);

		assertEquals(target, Charset.forName("x-eucJP-Open"));
	}
	@Test
	public void testgetCharsetEUC_JP_OPENGreater() {
		byte[] byteArray = "テスト羅将文字列～①".getBytes(UraCharset.EUC_JP_OPEN);
		Charset target = UraCharset.ME.setUpDecoderListGreater().getCharset(byteArray);

		assertEquals(target, Charset.forName("x-eucJP-Open"));
	}
	@Test
	public void testgetCharsetShiftJISGreaterIsWINDOWS_31() {
		byte[] byteArray = "テスト羅将文字列～①".getBytes(UraCharset.SHIFT_JIS);
		Charset target = UraCharset.ME.setUpDecoderListGreater().getCharset(byteArray);

		assertEquals(target, Charset.forName("windows-31j"));
	}
	@Test
	public void testgetCharsetWINDOWS_31JGreater() {
		byte[] byteArray = "テスト羅将文字列～①".getBytes(UraCharset.WINDOWS_31J);
		Charset target = UraCharset.ME.setUpDecoderListGreater().getCharset(byteArray);

		assertEquals(target, Charset.forName("windows-31j"));
	}
	@Test
	public void testgetCharsetUTF8GreaterIsUTF16BE() {
		byte[] byteArray = "テスト羅将文字列～①".getBytes(UraCharset.UTF8);
		Charset target = UraCharset.ME.setUpDecoderListGreater().getCharset(byteArray);

		assertEquals(target, Charset.forName("UTF-16BE"));
	}
	@Test
	public void testgetCharsetUTF16BEGreater() {
		byte[] byteArray = "テスト羅将文字列～①".getBytes(UraCharset.UTF16BE);
		Charset target = UraCharset.ME.setUpDecoderListGreater().getCharset(byteArray);

		assertEquals(target, Charset.forName("UTF-16BE"));
	}
	@Test
	public void testgetCharsetUTF32GreaterIsUTF_16BE() {
		byte[] byteArray = "テスト羅将文字列～①".getBytes(UraCharset.UTF32);
		Charset target = UraCharset.ME.setUpDecoderListGreater().getCharset(byteArray);

		assertEquals(target, Charset.forName("UTF-16BE"));
	}
}
