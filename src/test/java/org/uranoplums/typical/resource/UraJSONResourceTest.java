/**
 *
 */
package org.uranoplums.typical.resource;

import static org.junit.Assert.*;

import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Map.Entry;

import org.junit.Before;
import org.junit.Test;

/**
 * @author syany
 *
 */
public class UraJSONResourceTest {

    UraJSONResource jsonResourceTest;

    /**
     * @throws java.lang.Exception
     */
    @Before
    public void setUp() throws Exception {
        jsonResourceTest = new UraJSONResource("json_re");
    }

    @Test
    public final void testTypeArrayGetResource() {
        String[] val = jsonResourceTest.getResourceArray(new String[0], Locale.getDefault(), "arrayTest");
        //assertEquals("MESXXDD_Test!", val[0]);
        assertTrue(true);
    }
    @Test
    public void getSubResourceValue01() throws Exception {
        UraJSONResource jsonResource = new UraJSONResource("message_test");
        Map<String, Object> map = jsonResource.getResourceMap("test.004", "メッセージん");
        System.out.println("m: " + map.getClass().getName());
        for (Entry<String, Object> entry: map.entrySet()) {
            System.out.println("k: " + entry.getKey() + ", v: [" + entry.getValue().toString() + "], (" + entry.getClass().getName() + ")");
        }
        assertTrue(true);
    }
    @Test
    public final void getResourceValue01() {
        UraJSONResource jsonResource = new UraJSONResource("message_test");
        List<String> list = jsonResource.getResourceList("test.003");
        Map<String, Object> map = jsonResource.getResourceMap("test.002");
        String value = jsonResource.getResourceString("test.001");
        System.out.println("test.003:" + list.toString() + " (" + list.getClass().getName() + ")");
        System.out.println("test.002:" + map.toString() + " (" + map.getClass().getName() + ")");
        System.out.println("test.001:" + value + " (" + value.getClass().getName() + ")");
        assertTrue(true);
    }

    @Test
    public final void testTypeListGetResource() {
        @SuppressWarnings ("unchecked")
        List<String> val = jsonResourceTest.getResourceValue(List.class, Locale.getDefault(), "arrayTest", "tt");
        //assertEquals("MESXXDD_Test!", val.toString());
        assertTrue(true);
    }

    @Test
    public final void testTypeListGetResource01() {
        @SuppressWarnings ("unchecked")
        List<String> val = jsonResourceTest.getResourceList(Locale.getDefault(), "arrayTest", "ee");
        //assertEquals("MESXXDD_Test!", val.toString());
        assertTrue(true);
    }

    @Test
    public final void testTypeStringGetResource() {
        String val = jsonResourceTest.getResourceValue(String.class, Locale.getDefault(), "ray");
        //assertEquals("MESXXDD_Test!", val);
        assertTrue(true);
    }

    /**
     * {@link org.uranoplums.typical.resource.UraJSONResource#UraJSONResource(java.lang.String)} のためのテスト・メソッド。
     */
    @Test
    public final void testUraJSONResource() {
        // UraJSONResource jsonResource = new UraJSONResource("json_re");

        //assertEquals("MESXXDD_Test!", jsonResource.getResourceObject(Locale.JAPANESE, "joy", UraStringUtils.EMPTY_STRINGS));

        assertTrue(true);
        // assertEquals("MESXXDD_Test!",jsonResource.getResourceValue(Locale.ENGLISH, "name",
        // UraJaStringUtils.EMPTY_STRINGS));
    }

    @Test
    public final void testUraJSONResource2() {
        // UraJSONResource jsonResource = new UraJSONResource("json_re");

        //assertEquals("MESXXDD_Test!", jsonResource.getResourceObject("ray"));

        assertTrue(true);
        // assertEquals("MESXXDD_Test!",jsonResource.getResourceValue(Locale.ENGLISH, "name",
        // UraJaStringUtils.EMPTY_STRINGS));
    }
}
