/**
 *
 */
package org.uranoplums.typical.util;

/**
 * @author syany
 * 
 */
public class UraLoaderUtils extends UraUtils {

    /**
	 *
	 */
    private UraLoaderUtils() {
        super();
    }

    public static Class<?> applicationClass(String className) throws ClassNotFoundException {
        // Look up the class loader to be used
        ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
        if (classLoader == null) {
            classLoader = UraLoaderUtils.class.getClassLoader();
        }
        // Attempt to load the specified class
        return (classLoader.loadClass(className));
    }
}
