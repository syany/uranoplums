/*
 * Copyright 2013-2015 the Uranoplums Foundation and the Others.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND,
 * either express or implied. See the License for the specific language
 * governing permissions and limitations under the License.
 *
 * $Id: UraFileUtils.java$
 */
package org.uranoplums.typical.io;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.OutputStream;
import java.net.URL;
import java.util.HashSet;
import java.util.Set;

import org.uranoplums.typical.collection.UraSetUtils;
import org.uranoplums.typical.exception.UraIORuntimeException;
import org.uranoplums.typical.log.UraLogger;
import org.uranoplums.typical.log.UraLoggerFactory;
import org.uranoplums.typical.util.UraUtils;


/**
 * UraFileUtilsクラス。<br>
 *
 * @since 2015/11/11
 * @author syany
 */
public class UraFileUtils extends UraUtils {

    public static final long ONE_KB = 1024L;
    public static final long ONE_MB = 1048576L;
    public static final long ONE_GB = 1073741824L;
    public static final File[] EMPTY_FILE_ARRAY = new File[0];
    /**  */
    protected static final UraLogger<String> LOGGER;
    /**  */
    protected static final Set<String> validPathSet;

    static {
        LOGGER = new UraInnerCodeLog(UraLoggerFactory.getLogger());
        validPathSet = new HashSet<String>(4);
        try {
            validPathSet.add(new File("dummy.org").getCanonicalFile().getParent());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static final void setDefaultValidPath(String path) {
        validPathSet.add(path);
    }
    public static final void removeDefaultValidPath(String path) {
        validPathSet.remove(path);
    }
    public static final void clearDefaultValidPath() {
        validPathSet.clear();
    }
    /**
     * 。<br>
     * @param path
     * @param includePaths
     * @return
     */
    public static File newFile(String path, String... includePaths) {
        return getCanonicalFile(path, UraSetUtils.toSet(includePaths));
    }
    /**
     * 。<br>
     * @param path
     * @param includePaths
     * @return
     */
    public static File getCanonicalFile(String path, String... includePaths) {
        return getCanonicalFile(path, UraSetUtils.toSet(includePaths));
    }

    /**
     * 。<br>
     * @param path
     * @param includePaths
     * @return
     */
    public static File getCanonicalFile(String path, Set<String> includePaths) {
        return getCanonicalFile(new File(path), includePaths);
    }

    /**
     * 。<br>
     * @param cononicalFile
     * @return
     */
    public static File getCanonicalFile(File cononicalFile) {
        return getCanonicalFile(cononicalFile, null);
    }
    /**
     * 。<br>
     * @param cononicalFile
     * @param includePaths
     * @return
     */
    public static File getCanonicalFile(File cononicalFile, Set<String> includePaths) {
        if (includePaths != null) {
            validPathSet.addAll(includePaths);
        }
        try {
            //
            String cononicalDir = cononicalFile.getCanonicalFile().getParent();
            if (!validPathSet.contains(cononicalDir)) {
                throw new UraIORuntimeException();
            }
            return cononicalFile.getCanonicalFile();
        } catch (IOException e) {
            throw new UraIORuntimeException(e);
        }
    }

    /**
     * 。<br>
     * @param size
     * @return
     */
    public static String byteCountToDisplaySize(long size)
    {
        String displaySize;
        if (size / 1073741824L > 0L) {
            displaySize = String.valueOf(size / 1073741824L) + " GB";
        } else {
            if (size / 1048576L > 0L) {
                displaySize = String.valueOf(size / 1048576L) + " MB";
            } else {
                if (size / 1024L > 0L) {
                    displaySize = String.valueOf(size / 1024L) + " KB";
                } else {
                    displaySize = String.valueOf(size) + " bytes";
                }
            }
        }
        return displaySize;
    }
    /**
     * 。<br>
     * @param file
     * @throws IOException
     */
    public static void touch(File file) {
        if (!file.exists()) {
            OutputStream out;
            try {
                out = UraIOUtils.openOutputStream(file);
            } catch (IOException e) {
                UraIORuntimeException ioe = new UraIORuntimeException(e);
                LOGGER.log("URA-E018", ioe);
                throw ioe;
            }
            UraIOUtils.closeQuietly(out);
        }
        boolean success = file.setLastModified(System.currentTimeMillis());
        if (!success) {
            UraIORuntimeException ioe = new UraIORuntimeException();
            LOGGER.log("URA-E018", ioe);
            throw ioe;
        }
    }


    public static File toFile(URL url) {
        if ((url == null) || (!url.getProtocol().equals("file"))) {
            return null;
        }
        String filename = url.getFile().replace('/', File.separatorChar);
        int pos = 0;
        while ((pos = filename.indexOf('%', pos)) >= 0) {
            if (pos + 2 < filename.length())
            {
                String hexStr = filename.substring(pos + 1, pos + 3);
                char ch = (char) Integer.parseInt(hexStr, 16);
                filename = filename.substring(0, pos) + ch + filename.substring(pos + 3);
            }
        }
        return new File(filename);
    }

    public static void forceDelete(File file) throws IOException {
        if (!file.exists()) {
            LOGGER.log("URA-E020", file);
            throw new FileNotFoundException("File does not exist: " + file);
        }
        if (!file.delete()) {
            LOGGER.log("URA-E021", file);
            String message = "Unable to delete file: " + file;
            throw new IOException(message);
        }
    }

}
