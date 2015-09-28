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

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.Reader;
import java.nio.charset.Charset;
import java.util.HashSet;
import java.util.Set;

import org.uranoplums.typical.collection.UraSetUtils;
import org.uranoplums.typical.exception.UraIORuntimeException;
import org.uranoplums.typical.log.UraLogger;
import org.uranoplums.typical.log.UraLoggerFactory;
import org.uranoplums.typical.util.UraUtils;
import org.uranoplums.typical.util.i18n.UraCharset;

/**
 * UraFileUtilsクラス。<br>
 *
 * @since 2015/10/31
 * @author syany
 */
public class UraFileUtils extends UraUtils {

    /**  */
    private static final String DUMMY_HOME = "dummy.org";
    /**  */
    protected static final UraLogger<String> LOGGER = new UraInnerCodeLog(UraLoggerFactory.getLogger());

    public static final void openReadLine(File path, UraFileReader fileReader) throws IOException {
        openReadLine(path, null, fileReader);
    }

    public static final void openReadLine(File path, Charset charset, UraFileReader fileReader) throws IOException {
        InputStream inputStream = null;
        Reader reader = null;
        BufferedReader bufferedReader = null;
        try {
            inputStream = openInputStream(path);
            if (charset == null) {
                charset = UraCharset.ME.getCharset(path);
                if (charset == null) {
                    charset = Charset.defaultCharset();
                }
            }
            //
            reader = new InputStreamReader(inputStream, charset);
            bufferedReader = getResetableReader(reader);
            String lineStr;
            while((lineStr = bufferedReader.readLine()) != null){
                fileReader.readLine(lineStr, path.getPath());
            }
        } finally {
            UraIOUtils.closeQuietly(bufferedReader);
            UraIOUtils.closeQuietly(reader);
            UraIOUtils.closeQuietly(inputStream);
        }
    }
    public static FileInputStream openInputStream(File file)
            throws IOException
    {
        if (file.exists()) {
            if (file.isDirectory()) {
                IOException ioe = new IOException();
                LOGGER.log("URA-E014", ioe);
                throw ioe;
            }
            if (!file.canRead()) {
                IOException ioe = new IOException();
                LOGGER.log("URA-E015", ioe);
                throw ioe;
            }
        } else {
            FileNotFoundException fnfe = new FileNotFoundException();
            LOGGER.log("URA-E010", fnfe);
            throw fnfe;
        }
        return new FileInputStream(file);
    }

    public static FileOutputStream openOutputStream(File file)
            throws IOException
    {
        if (file.exists()) {
            if (file.isDirectory()) {
                IOException ioe = new IOException();
                LOGGER.log("URA-E014", ioe);
                throw ioe;
            }
            if (!file.canWrite()) {
                IOException ioe = new IOException();
                LOGGER.log("URA-E016", ioe);
                throw ioe;
            }
        } else {
            File parent = file.getParentFile();
            if ((parent != null) && (!parent.exists()) &&
                    (!parent.mkdirs())) {
                IOException ioe = new IOException();
                LOGGER.log("URA-E017", ioe);
                throw ioe;
            }
        }
        return new FileOutputStream(file);
    }


    @SuppressWarnings ("unchecked")
    public static final <E extends Reader> E getResetableReaderIfNot(Reader reader, int paramInt, E... dummy) {
        if (reader.markSupported()) {
            try {
                reader.mark(0);
                return (E) reader;
            } catch (IOException e) {
                // Unlikely
                LOGGER.log("URA-T999", e);
            }
        }
        return getResetableReader(reader, paramInt, dummy);
    }

    @SuppressWarnings ("unchecked")
    public static final <E extends Reader> E getResetableReader(Reader reader, int paramInt, E... dummy) {
        Reader r = new BufferedReader(reader);
        try {
            if (r.markSupported()) {
                r.mark(0);
            }
        } catch (IOException e) {
            // Unlikely
            LOGGER.log("URA-T999", e);
        }
        return (E) r;
    }
    @SuppressWarnings ("unchecked")
    public static final <E extends Reader> E getResetableReaderIfNot(Reader reader, E... dummy) {
        if (reader.markSupported()) {
            try {
                reader.mark(0);
                return (E) reader;
            } catch (IOException e) {
                // Unlikely
                LOGGER.log("URA-T999", e);
            }
        }
        return getResetableReader(reader, dummy);
    }

    @SuppressWarnings ("unchecked")
    public static final <E extends Reader> E getResetableReader(Reader reader, E... dummy) {
        Reader r = new BufferedReader(reader);
        try {
            if (r.markSupported()) {
                r.mark(0);
            }
        } catch (IOException e) {
            // Unlikely
            LOGGER.log("URA-T999", e);
        }
        return (E) r;
    }

    public static String byteCountToDisplaySize(long size)
    {
        String displaySize;
//        String displaySize;
        if (size / 1073741824L > 0L) {
            displaySize = String.valueOf(size / 1073741824L) + " GB";
        } else {
//            String displaySize;
            if (size / 1048576L > 0L) {
                displaySize = String.valueOf(size / 1048576L) + " MB";
            } else {
//                String displaySize;
                if (size / 1024L > 0L) {
                    displaySize = String.valueOf(size / 1024L) + " KB";
                } else {
                    displaySize = String.valueOf(size) + " bytes";
                }
            }
        }
        return displaySize;
    }

    public static void touch(File file)
            throws IOException
    {
        if (!file.exists()) {
            OutputStream out = openOutputStream(file);
            UraIOUtils.closeQuietly(out);
        }
        boolean success = file.setLastModified(System.currentTimeMillis());
        if (!success) {
            IOException ioe = new IOException();
            LOGGER.log("URA-E018", ioe);
            throw ioe;
        }
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
     * @param includePaths
     * @return
     */
    public static File getCanonicalFile(File cononicalFile, Set<String> includePaths) {
        if (includePaths == null) {
            includePaths = new HashSet<String>(4);
        }
        try {
            //
            includePaths.add(new File(DUMMY_HOME).getCanonicalFile().getParent());
            //
            String cononicalDir = cononicalFile.getCanonicalFile().getParent();
            if (!includePaths.contains(cononicalDir)) {
                throw new UraIORuntimeException();
            }
            return cononicalFile.getCanonicalFile();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
