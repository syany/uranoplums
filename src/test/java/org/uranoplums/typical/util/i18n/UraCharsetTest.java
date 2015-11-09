/**
 *
 */
package org.uranoplums.typical.util.i18n;

import static org.junit.Assert.*;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
import java.util.Locale;
import java.util.Map;
import java.util.Map.Entry;
import java.util.TreeMap;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.uranoplums.typical.io.UraFileUtils;
import org.uranoplums.typical.io.UraIOUtils;
import org.uranoplums.typical.io.UraRWUtils;
import org.uranoplums.typical.lang.UraObject;
import org.uranoplums.typical.util.UraClassUtils;
import org.uranoplums.typical.util.UraStringUtils;

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
    public void testgetCharsetList() {
        Map<String, Charset> charMap = new TreeMap<String, Charset>(Charset.availableCharsets());
        System.out.print("対応するCharset[");
        String oldCharName = null;
        for (Entry<String, Charset> entry : charMap.entrySet()) {
            Charset c = entry.getValue();
            String indexS = UraStringUtils.substring(entry.getKey(), 0, 2);
            if (!indexS.equals(oldCharName)) {
                System.out.println();
                oldCharName = indexS;
            }
            System.out.print(entry.getKey() + "(" + c.displayName(Locale.getDefault()) +"), ");
        }
        System.out.println("]");
        assertEquals(true, true);
    }
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
    public void testgetCharsetShiftJIS03() {
        Charset target = UraCharset.ME.getCharset("charset_test_euc.txt");

        assertEquals(target, Charset.forName("Shift_JIS"));
    }

    @Test
    public void testgetCharsetShiftJIS04() {
        InputStream is = UraClassUtils.getCurrentClassLoader().getResourceAsStream("charset_test_euc.txt");

        Charset charset001 = UraCharset.ME.getCharset(is);
        System.out.println("charset_test.txtはキャラセット[" + charset001.displayName()+ "]として読み込みます");

        InputStreamReader isr = new InputStreamReader(is, charset001);
        BufferedReader br = new BufferedReader(isr);
        try {
            while(br.ready()) {
                System.out.println("文章[" + br.readLine() + "]?読めてるかな？");
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            UraRWUtils.closeQuietly(br);
            UraRWUtils.closeQuietly(isr);
            UraIOUtils.closeQuietly(is);
        }

        assertEquals(charset001, Charset.forName("Shift_JIS"));
    }

    @Test
    public void testgetCharsetShiftJIS05() throws IOException {
        File canF = new File(".\\bin");

        //File f = UraRWUtils.newFile(".\\bin\\charset_test.txt", canF.getCanonicalPath());
        File f = UraFileUtils.newFile(".\\bin\\charset_test_euc.txt", canF.getCanonicalPath());
        Charset target = UraCharset.ME.getCharset(f);

        assertEquals(target, Charset.forName("Shift_JIS"));
    }
    @Test
    public void testgetCharsetShiftJIS02() {
        InputStream is = UraClassUtils.getCurrentClassLoader().getResourceAsStream("charset_test.txt");
        byte [] buffer = new byte[42];
        try {
            //is.mark(1024);
            //is.mark(0);
            is = UraIOUtils.getResetableInputStreamIfNot(is);
            int len = is.read(buffer);
            if (len > 0) {
                Charset charset001 = UraCharset.ME.getCharset(buffer);
                String tt = new String(buffer, charset001);
                System.out.println("charset_test.txtはキャラセット[" + charset001.displayName()+ "]として読み込みます。["+tt+"]");
                //is.reset();
                //is.reset();
                UraIOUtils.resetQuietly(is);
                InputStreamReader isr = new InputStreamReader(is, charset001);

                BufferedReader br = new BufferedReader(isr);
                while(br.ready()) {
                    System.out.println("文章[" + br.readLine() + "]?読めてるかな？");
                }
                br.close();
                isr.close();
                is.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        byte[] byteArray02 = "テスト羅将文字列～①".getBytes(UraCharset.SHIFT_JIS);
        Charset target02 = UraCharset.ME.getCharset(byteArray02);
        System.out.println(target02.displayName());

        byte[] byteArray = "テスト羅将文字列～①".getBytes(UraCharset.SHIFT_JIS);
        Charset target = UraCharset.ME.setUpDecoderListLower().getCharset(byteArray);
        System.out.println(target.displayName());


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
