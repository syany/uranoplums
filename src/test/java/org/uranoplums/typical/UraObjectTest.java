/**
 *
 */
package org.uranoplums.typical;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.BeforeClass;

/**
 * @author syany
 *
 */
public class UraObjectTest {

    TUraObject tUraObject = new TUraObject();

    /**
     * @throws java.lang.Exception
     */
    @BeforeClass
    public static void setUpBeforeClass() throws Exception {}

    /**
     * @throws java.lang.Exception
     */
    // @Before
    // protected void setUp() throws Exception {
    // ///super.setUp();
    // tUraObject = new TUraObject();
    // //include(getClass().getName().replace('.', '/') + ".dicon");
    // }
    /**
     * @throws java.lang.Exception
     */
    @AfterClass
    public static void tearDownAfterClass() throws Exception {}

    /**
     * @throws java.lang.Exception
     */
    @After
    public void tearDown() throws Exception {}

//    /**
//     * {@link org.uranoplums.typical.lang.UraObject#toString()} のためのテスト・メソッド。
//     */
//    @Test
//    public final void testToMultiString() throws Exception {
//        String a = tUraObject.toMultiString();
//        System.out.println(tUraObject.toMultiString());
//        assertEquals(a, tUraObject.toString());
//    }
//
//    @Test
//    public final void testToMultiStringFilter() throws Exception {
//        String a = tUraObject.toMultiStringFilter("hoge");
//        System.out.println(tUraObject.toMultiString());
//        assertEquals(a, tUraObject.toMultiString());
//    }
}
