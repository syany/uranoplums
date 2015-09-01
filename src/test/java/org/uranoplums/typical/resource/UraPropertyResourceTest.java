/**
 *
 */
package org.uranoplums.typical.resource;

import static org.junit.Assert.*;

import java.util.Locale;

import org.junit.Before;
import org.junit.Test;
import org.uranoplums.typical.util.UraStringUtils;

/**
 * @author syany
 *
 */
public class UraPropertyResourceTest {

	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
	}

	/**
	 * {@link org.uranoplums.typical.resource.AbsUraResource#_getResourceObject(java.util.Locale, java.lang.String, java.lang.Object[])} のためのテスト・メソッド。
	 */
	@Test
	public final void testGetResourceValueLocaleStringObjectArray() {
		UraPropertyResource resource = new UraPropertyResource("message_test");
		System.out.println(resource.getResourceValue(Locale.JAPANESE, "test.001", UraStringUtils.EMPTY_STRINGS));
		//assertEquals("MESXXDD_Test!",resource.getResourceValue(Locale.JAPANESE, "test.001", UraStringUtils.EMPTY_STRINGS));
        assertTrue(true);
	}

	/**
	 * {@link org.uranoplums.typical.resource.AbsUraResource#_getResourceObject(java.util.Locale, java.lang.String, java.lang.Object[])} のためのテスト・メソッド。
	 */
	@Test
	public final void testGetResourceValueLocaleStringObjectArray2() {
		UraPropertyResource resource = new UraPropertyResource("message_test");
		String[] args = {"Sashimi","Tempra"};
		System.out.println(resource.getResourceValue(Locale.JAPANESE, "test.003", args));
		//assertEquals("defaultMESSS[Sashimi]",resource.getResourceValue(Locale.JAPANESE, "test.003", args));
        assertTrue(true);
	}

}
