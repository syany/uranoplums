/**
 *
 */
package org.uranoplums.typical.collection.factory;

import static org.junit.Assert.*;
import static org.uranoplums.typical.collection.factory.UraMapFactory.*;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.uranoplums.typical.collection.factory.UraMapFactory.FACTOR;

// import org.uranoplums.typical.collection.factory.UraMapFactory.FACTOR;
/**
 * 。<br>
 * @author syany
 *
 */
public class UraHashMapFactoriesTest {

    /**
     * @throws java.lang.Exception
     */
    @BeforeClass
    public static void setUpBeforeClass() throws Exception {}

    /**
     * @throws java.lang.Exception
     */
    @AfterClass
    public static void tearDownAfterClass() throws Exception {}

    /**
     * 。<br>
     *
     */
    @Test
    public void newConcurrentHashMapTest() {
        // Map<String, Object> chm = newConcurrentHashMap();
        Map<String, Object> chm = newConcurrentHashMap(4, FACTOR.GREATER);
        assertTrue("newHashMapTest", chm instanceof ConcurrentHashMap);
    }

    /**
     * 。<br>
     *
     */
    @Test
    public void newConcurrentHashMapTest2() {
        Map<String, Object> chm = newConcurrentHashMap(4);
        assertEquals(chm.size(), 0);
    }

    /**
     * 。<br>
     */
    @Test
    public void newHashMapTest() {
        Map<String, Object> hm = newHashMap();
        assertTrue("newHashMapTest", hm instanceof HashMap);
        // assertEquals(hm instanceof HashMap, Boolean.TRUE);
    }

    /**
     * @throws java.lang.Exception
     */
    @Before
    public void setUp() throws Exception {}

    /**
     * @throws java.lang.Exception
     */
    @After
    public void tearDown() throws Exception {}
}
