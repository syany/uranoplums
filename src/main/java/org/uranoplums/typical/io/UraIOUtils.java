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
 * $Id: UraIOUtils.java$
 */
package org.uranoplums.typical.io;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.Writer;

import org.uranoplums.typical.exception.UraIORuntimeException;
import org.uranoplums.typical.log.UraLogger;
import org.uranoplums.typical.log.UraLoggerFactory;
import org.uranoplums.typical.util.UraClassUtils;
import org.uranoplums.typical.util.UraUtils;


/**
 * UraIOUtilsクラス。<br>
 *
 * @since 2015/10/30
 * @author syany
 */
public class UraIOUtils extends UraUtils {

    /**  */
    public static final String DUMMY_FILE = "dmf";

    //protected static final UraLogger LOGGER = UraLoggerFactory.getLogger();

    protected static final UraLogger<String> LOGGER = new UraInnerCodeLog(UraLoggerFactory.getLogger());

    /**
     * 。<br>
     * @param input
     */
    public static void closeQuietly(InputStream input) {
        try {
            if (input != null) {
                input.close();
            }
        } catch (IOException ioe) {
            LOGGER.log("URA-E012", ioe);
        }
    }

    /**
     * 。<br>
     * @param output
     */
    public static void closeQuietly(OutputStream output) {
        try {
            if (output != null) {
                output.close();
            }
        } catch (IOException ioe) {
            LOGGER.log("URA-E012", ioe);
        }
    }

    public static InputStream getResetableInputStreamIfNot(InputStream paramInputStream) {
        if (paramInputStream.markSupported()) {
            // まだ１バイトも読み込まれていない場合に限り、リセットを可能とする。
            paramInputStream.mark(0);
            return paramInputStream;
        }
        return getResetableInputStream(paramInputStream);
    }

    public static InputStream getResetableInputStreamIfNot(InputStream paramInputStream, int paramInt) {
        if (paramInputStream.markSupported()) {
            // まだ１バイトも読み込まれていない場合に限り、リセットを可能とする。
            paramInputStream.mark(0);
            return paramInputStream;
        }
        return getResetableInputStream(paramInputStream, paramInt);
    }
    /**
     * 。<br>
     * @param paramInputStream
     * @return
     */
    public static InputStream getResetableInputStream(InputStream paramInputStream) {
        paramInputStream.markSupported(); // force NPE
        InputStream is = new UraBufferedInputStream(paramInputStream);
        is.mark(0);
        return is;
    }
    /**
     * 。<br>
     * @param paramInputStream
     * @param paramInt
     * @return
     */
    public static InputStream getResetableInputStream(InputStream paramInputStream, int paramInt) {
        paramInputStream.markSupported(); // force NPE
        InputStream is = new UraBufferedInputStream(paramInputStream, paramInt);
        is.mark(0);
        return is;
    }

    public static InputStream openInputStreamQuietly(String pathStr) {
        ClassLoader classLoader = UraClassUtils.getCurrentClassLoader();
        InputStream is = classLoader.getResourceAsStream(pathStr);
        if (is == null) {
            is =openInputStreamQuietly(new File(pathStr));
        }
        return is;
    }
    public static InputStream openInputStreamQuietly(File file) {
        InputStream is = null;
        try {
            File canonicalFile = UraFileUtils.getCanonicalFile(file);
            is = openInputStream(canonicalFile);
        } catch (UraIORuntimeException ioe) {
            LOGGER.log("URA-E019", ioe);
            ClassLoader classLoader = UraClassUtils.getCurrentClassLoader();
            is = classLoader.getResourceAsStream(DUMMY_FILE);
        } catch (Exception e) {
            ClassLoader classLoader = UraClassUtils.getCurrentClassLoader();
            is = classLoader.getResourceAsStream(DUMMY_FILE);
        }
        return is;
    }
    /**
     * 。<br>
     * @param paramArrayOfByte
     * @return
     */
    public static InputStream getResetableInputStream(byte[] paramArrayOfByte) {
        @SuppressWarnings ("unused")
        int l = paramArrayOfByte.length; // force NPE
        InputStream is = new UraByteArrayInputStream(paramArrayOfByte);
        is.mark(0);
        return is;
    }
    /**
     * 。<br>
     * @param paramArrayOfByte
     * @param paramInt1
     * @param paramInt2
     * @return
     */
    public static InputStream getResetableInputStream(byte[] paramArrayOfByte, int paramInt1, int paramInt2) {
        @SuppressWarnings ("unused")
        int l = paramArrayOfByte.length; // force NPE
        InputStream is = new UraByteArrayInputStream(paramArrayOfByte, paramInt1, paramInt2);
        is.mark(0);
        return is;
    }

    public static void resetQuietly(InputStream input) {
        try {
            if (input != null) {
                input.reset();
            }
        } catch (IOException ioe) {
            LOGGER.log("URA-E013", ioe);
        }
    }

    public static FileInputStream openInputStream(File file)
            throws IOException {
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


    public static FileOutputStream openOutputStreamQuietly(String file) {
        return openOutputStreamQuietly(UraFileUtils.getCanonicalFile(file));
    }
    /**
     * 。<br>
     * @param file
     * @return
     */
    public static FileOutputStream openOutputStreamQuietly(File file) {
        FileOutputStream os = null;
        try {
            File canonicalFile = UraFileUtils.getCanonicalFile(file);
            os = openOutputStream(canonicalFile);
        } catch (UraIORuntimeException ioe) {
            LOGGER.log("URA-E019", ioe);
        } catch (Exception e) {
            LOGGER.log("URA-T999", e);
        }
        return os;
    }


    public static FileOutputStream openOutputStream(String file)
            throws IOException {
        return openOutputStream(UraFileUtils.getCanonicalFile(file));
    }

    public static FileOutputStream openOutputStream(File file)
            throws IOException {
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


    public static InputStream toInputStream(String input) {
        byte[] bytes = input.getBytes();
        return new ByteArrayInputStream(bytes);
    }

    public static InputStream toInputStream(String input, String encoding)
            throws IOException {
        byte[] bytes = encoding != null ? input.getBytes(encoding) : input.getBytes();
        return new ByteArrayInputStream(bytes);
    }

    public static void write(byte[] data, OutputStream output)
            throws IOException {

        if (data != null) {
            output.write(data);
        }
    }

    public static void write(byte[] data, Writer output)
            throws IOException {
        if (data != null) {
            output.write(new String(data));
        }
    }

    public static void write(byte[] data, Writer output, String encoding)
            throws IOException {
        if (data != null) {
            if (encoding == null) {
                write(data, output);
            } else {
                output.write(new String(data, encoding));
            }
        }
    }

    public static void write(char[] data, Writer output)
            throws IOException {
        if (data != null) {
            output.write(data);
        }
    }

    public static void write(char[] data, OutputStream output)
            throws IOException {
        if (data != null) {
            output.write(new String(data).getBytes());
        }
    }

    public static void write(char[] data, OutputStream output, String encoding)
            throws IOException {
        if (data != null) {
            if (encoding == null) {
                write(data, output);
            } else {
                output.write(new String(data).getBytes(encoding));
            }
        }
    }

    public static void write(String data, Writer output)
            throws IOException {
        if (data != null) {
            output.write(data);
        }
    }

    public static void write(String data, OutputStream output)
            throws IOException {
        if (data != null) {
            output.write(data.getBytes());
        }
    }

    public static void write(String data, OutputStream output, String encoding)
            throws IOException {
        if (data != null) {
            if (encoding == null) {
                write(data, output);
            } else {
                output.write(data.getBytes(encoding));
            }
        }
    }

    public static void write(StringBuffer data, Writer output)
            throws IOException {
        if (data != null) {
            output.write(data.toString());
        }
    }

    public static void write(StringBuffer data, OutputStream output)
            throws IOException {
        if (data != null) {
            output.write(data.toString().getBytes());
        }
    }

    public static void write(StringBuffer data, OutputStream output, String encoding)
            throws IOException {
        if (data != null) {
            if (encoding == null) {
                write(data, output);
            } else {
                output.write(data.toString().getBytes(encoding));
            }
        }
    }

}
