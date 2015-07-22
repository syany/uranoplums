/**
 *
 */
package org.uranoplums.typical.log;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;

/**
 * @author syany
 *
 */
public class UraLoggerFactoryTest {

	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
	}

	/**
	 * {@link org.uranoplums.typical.log.UraLoggerFactory#getLogger()} のためのテスト・メソッド。
	 */
	@Test
	public final void testGetLogger() {
		Logger logger = UraLoggerFactory.getLogger();
		logger.debug("メッセージてすと");
        assertTrue(true);
	}

	/**
	 * {@link org.uranoplums.typical.log.UraLoggerFactory#getLogger(int)} のためのテスト・メソッド。
	 */
	@Test
	public final void testGetLoggerInt() {
		Logger logger = UraLoggerFactory.getLogger(1);
		logger.warn("わーん");
        assertTrue(true);
	}

	/**
	 * {@link org.uranoplums.typical.log.UraLoggerFactory#getLogger(java.lang.String)} のためのテスト・メソッド。
	 */
	@Test
	public final void testGetLoggerString() {
		Logger logger = UraLoggerFactory.getLogger("GGG.ii");
		logger.warn("わーんuyuy");
        assertTrue(true);
	}

	/**
	 * {@link org.uranoplums.typical.log.UraLoggerFactory#getLogger(java.lang.Class)} のためのテスト・メソッド。
	 */
	@Test
	public final void testGetLoggerClassOfQ() {
		Logger logger = UraLoggerFactory.getLogger(this.getClass());
		logger.warn("わーんuyuy");
        assertTrue(true);
	}

	/**
	 * {@link org.uranoplums.typical.log.UraLoggerFactory#getILoggerFactory()} のためのテスト・メソッド。
	 */
	@Test
	public final void testGetILoggerFactory() {
        assertTrue(true);
	}

	/**
	 * {@link org.uranoplums.typical.log.UraLoggerFactory#getUraStringCodeLog(int)} のためのテスト・メソッド。
	 */
	@Test
	public final void testGetUraStringCodeLogInt() {
		UraStringCodeLog logger = UraLoggerFactory.getUraStringCodeLog(2);
		logger.log("O わーぉ");
        assertTrue(true);
	}

	/**
	 * {@link org.uranoplums.typical.log.UraLoggerFactory#getUraStringCodeLog()} のためのテスト・メソッド。
	 */
	@Test
	public final void testGetUraStringCodeLog() {
		UraStringCodeLog logger = UraLoggerFactory.getUraStringCodeLog();
		logger.log("I い{}んふ{}ぉ", "OK", "NG");

        assertTrue(true);
	}
}
