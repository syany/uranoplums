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

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.io.Reader;
import java.io.StringWriter;
import java.io.Writer;
import java.util.ArrayList;
import java.util.List;

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
    public static final char DIR_SEPARATOR_UNIX = '/';
    /**  */
    public static final char DIR_SEPARATOR_WINDOWS = '\\';
    /**  */
    public static final char DIR_SEPARATOR = File.separatorChar;
    /**  */
    public static final String LINE_SEPARATOR_UNIX = "\n";
    /**  */
    public static final String LINE_SEPARATOR_WINDOWS = "\r\n";
    /**  */
    public static final String LINE_SEPARATOR;

    //protected static final UraLogger LOGGER = UraLoggerFactory.getLogger();

    protected static final UraLogger<String> LOGGER = new UraInnerCodeLog(UraLoggerFactory.getLogger());

    static {
        StringWriter buf = new StringWriter(4);
        PrintWriter out = new PrintWriter(buf);
        out.println();
        LINE_SEPARATOR = buf.toString();
    }
    /**
     * 。<br>
     * @param input
     */
    public static void closeQuietly(Reader input)
    {
        try
        {
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
    public static void closeQuietly(Writer output)
    {
        try
        {
            if (output != null) {
                output.close();
            }
        } catch (IOException ioe) {
            LOGGER.log("URA-E012", ioe);
        }
    }

    /**
     * 。<br>
     * @param input
     */
    public static void closeQuietly(InputStream input)
    {
        try
        {
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
    public static void closeQuietly(OutputStream output)
    {
        try
        {
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

    public static InputStream newInputStream(String pathStr) {
        ClassLoader classLoader = UraClassUtils.getCurrentClassLoader();
        InputStream is = classLoader.getResourceAsStream(pathStr);
        if (is == null) {
            try {
                is = new FileInputStream(pathStr);
            } catch (FileNotFoundException e) {
                LOGGER.log("URA-E010", e);
                UraIOUtils.closeQuietly(is);
            }
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
    public static void resetQuietly(InputStream input)
    {
        try
        {
            if (input != null) {
                input.reset();
            }
        } catch (IOException ioe) {
            LOGGER.log("URA-E013", ioe);
        }
    }

    public static void resetQuietly(Reader input)
    {
        try
        {
            if (input != null) {
                input.reset();
            }
        } catch (IOException ioe) {
            LOGGER.log("URA-E013", ioe);
        }
    }


    public static List<String> readLines(InputStream input)
            throws IOException
    {
        input.markSupported(); // force NPE
        InputStreamReader reader = new InputStreamReader(input);
        return readLines(reader);
    }

    public static List<String> readLines(InputStream input, String encoding)
            throws IOException
    {
        input.markSupported(); // force NPE
        if (encoding == null) {
            return readLines(input);
        }
        InputStreamReader reader = new InputStreamReader(input, encoding);
        return readLines(reader);
    }

    public static List<String> readLines(Reader input)
            throws IOException
    {
        input.markSupported(); // force NPE
        BufferedReader reader = new BufferedReader(input);
        List<String> list = new ArrayList<String>();
        String line = reader.readLine();
        while (line != null)
        {
            list.add(line);
            line = reader.readLine();
        }
        return list;
    }

}
