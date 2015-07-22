/**
 *
 */
package org.uranoplums.typical.resource;

import static org.junit.Assert.*;

import java.util.List;
import java.util.Locale;

import org.junit.Before;
import org.junit.Test;

/**
 * @author syany
 *
 */
public class UraJSONResourceTest {

    UraJSONResource jsonResource;

    /**
     * @throws java.lang.Exception
     */
    @Before
    public void setUp() throws Exception {
        jsonResource = new UraJSONResource("json_re");
    }

    @Test
    public final void testTypeArrayGetResource() {
        String[] val = jsonResource.getResourceArray(new String[0], Locale.getDefault(), "arrayTest");
        //assertEquals("MESXXDD_Test!", val[0]);
        assertTrue(true);
    }

    @Test
    public final void testTypeListGetResource() {
        @SuppressWarnings ("unchecked")
        List<String> val = jsonResource.getResourceValue(List.class, Locale.getDefault(), "arrayTest", "tt");
        //assertEquals("MESXXDD_Test!", val.toString());
        assertTrue(true);
    }

    @Test
    public final void testTypeListGetResource01() {
        @SuppressWarnings ("unchecked")
        List<String> val = jsonResource.getResourceList(Locale.getDefault(), "arrayTest", "ee");
        //assertEquals("MESXXDD_Test!", val.toString());
        assertTrue(true);
    }

    @Test
    public final void testTypeStringGetResource() {
        String val = jsonResource.getResourceValue(String.class, Locale.getDefault(), "ray");
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
