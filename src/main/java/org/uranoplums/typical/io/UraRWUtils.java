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
 * $Id: UraRWUtils.java$
 */
package org.uranoplums.typical.io;

import static org.uranoplums.typical.collection.factory.UraListFactory.*;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.Reader;
import java.io.Writer;
import java.nio.charset.Charset;
import java.util.Collection;
import java.util.List;

import org.apache.commons.lang3.SystemUtils;
import org.uranoplums.typical.log.UraLogger;
import org.uranoplums.typical.log.UraLoggerFactory;
import org.uranoplums.typical.util.UraClassUtils;
import org.uranoplums.typical.util.UraUtils;
import org.uranoplums.typical.util.i18n.UraCharset;

/**
 * UraFileUtilsクラス。<br>
 *
 * @since 2015/10/31
 * @author syany
 */
public class UraRWUtils extends UraUtils {

    /**  */
    public static final char DIR_SEPARATOR = File.separatorChar;
    /**  */
    public static final char DIR_SEPARATOR_UNIX = '/';
    /**  */
    public static final char DIR_SEPARATOR_WINDOWS = '\\';
    /**  */
    public static final String LINE_SEPARATOR = SystemUtils.FILE_SEPARATOR;
    /**  */
    public static final String LINE_SEPARATOR_UNIX = "\n";
    /**  */
    public static final String LINE_SEPARATOR_WINDOWS = "\r\n";

    /**  */
    protected static final UraLogger<String> LOGGER = new UraInnerCodeLog(UraLoggerFactory.getLogger());
//    static {
//        LOGGER = new UraInnerCodeLog(UraLoggerFactory.getLogger());

//        StringWriter buf = new StringWriter(4);
//        PrintWriter out = new PrintWriter(buf);
//        out.println();
//        LINE_SEPARATOR = buf.toString();
//    }

    /**
     * 。<br>
     * @param input
     */
    public static void closeQuietly(Reader input) {
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
    public static void closeQuietly(Writer output) {
        try {
            if (output != null) {
                output.close();
            }
        } catch (IOException ioe) {
            LOGGER.log("URA-E012", ioe);
        }
    }

    public static final <E extends Reader> E getResetableReader(Reader reader, E... dummy) {
        Class<E> clazz = UraClassUtils.getArrayClass(dummy);
        Reader r = new BufferedReader(reader);
        try {
            if (r.markSupported()) {
                r.mark(0);
            }
        } catch (IOException e) {
            // Unlikely
            LOGGER.log("URA-T999", e);
        }
        return clazz.cast(r);
    }

    public static final <E extends Reader> E getResetableReader(Reader reader, int paramInt, E... dummy) {
        Class<E> clazz = UraClassUtils.getArrayClass(dummy);
        Reader r = new BufferedReader(reader);
        try {
            if (r.markSupported()) {
                r.mark(0);
            }
        } catch (IOException e) {
            // Unlikely
            LOGGER.log("URA-T999", e);
        }
        return clazz.cast(r);
    }

    public static final <E extends Reader> E getResetableReaderIfNot(Reader reader, E... dummy) {
        Class<E> clazz = UraClassUtils.getArrayClass(dummy);
        if (reader.markSupported()) {
            try {
                reader.mark(0);
                return clazz.cast(reader);
            } catch (IOException e) {
                // Unlikely
                LOGGER.log("URA-T999", e);
            }
        }
        return getResetableReader(reader, dummy);
    }

    public static final <E extends Reader> E getResetableReaderIfNot(Reader reader, int paramInt, E... dummy) {
        Class<E> clazz = UraClassUtils.getArrayClass(dummy);
        if (reader.markSupported()) {
            try {
                reader.mark(0);
                return clazz.cast(reader);
            } catch (IOException e) {
                // Unlikely
                LOGGER.log("URA-T999", e);
            }
        }
        return getResetableReader(reader, paramInt, dummy);
    }

    /**
     * 。<br>
     * @param path
     * @param fileReader
     * @throws IOException
     */
    public static final void openReadBuffer(File path, UraFileByteReader fileReader) throws IOException {
        InputStream inputStream = null;
        try {
            inputStream = UraIOUtils.openInputStreamQuietly(path);
            openReadBuffer(inputStream, fileReader, path.getPath());
        } finally {
            UraIOUtils.closeQuietly(inputStream);
        }
    }

    /**
     * 。<br>
     * @param path
     * @param fileReader
     * @throws IOException
     */
    public static final void openReadBuffer(InputStream inputStream, UraFileByteReader fileReader, String path) throws IOException {
        int byteSize = fileReader.getBufferSize();
        byte[] buffer = new byte[byteSize];
        long readCount = 1L;
        int bufferSize = -1;
        while (-1 != (bufferSize = inputStream.read(buffer, 0, byteSize))) {
//            if (buffer == null) {
//                break;
//            }
            fileReader.readByteArray(buffer, path, bufferSize, readCount);
            readCount = (readCount == Long.MAX_VALUE) ? 1L : readCount + 1L;
        }
    }

    /**
     * 。<br>
     * @param path
     * @param fileReader
     * @throws IOException
     */
    public static final void openReadBuffer(String path, UraFileByteReader fileReader) throws IOException {
        InputStream inputStream = null;
        try {
            inputStream = UraIOUtils.openInputStreamQuietly(path);
            openReadBuffer(inputStream, fileReader, path);
        } finally {
            UraIOUtils.closeQuietly(inputStream);
        }
    }

    /**
     * 。<br>
     * @param path
     * @param fileReader
     * @param charset
     * @throws IOException
     */
    public static final void openReadChar(File path, UraFileCharReader fileReader, Charset charset) throws IOException {
        InputStream inputStream = null;
        try {
            inputStream = UraIOUtils.openInputStreamQuietly(path);
            if (charset == null) {
                charset = UraCharset.ME.getCharset(path);
                if (charset == null) {
                    charset = Charset.defaultCharset();
                }
            }
            //
            openReadChar(inputStream, fileReader, path.getPath(), charset);
        } finally {
            UraIOUtils.closeQuietly(inputStream);
        }
    }

    /**
     * 。<br>
     * @param path
     * @param fileReader
     * @throws IOException
     */
    public static final void openReadChar(File path, UraFileCharReader fileReader) throws IOException {
        openReadChar(path, fileReader, null);
    }

    public static final void openReadChar(InputStream inputStream, UraFileCharReader fileReader, String path, Charset charset) throws IOException {
        Reader reader = null;
        try {
            if (charset == null) {
                charset = UraCharset.ME.getCharset(path);
                if (charset == null) {
                    charset = Charset.defaultCharset();
                }
            }
            reader = new InputStreamReader(inputStream, charset);
            openReadChar(reader, fileReader, path);
        } finally {
            closeQuietly(reader);
        }
    }

    public static final void openReadChar(InputStream inputStream, UraFileCharReader fileReader, String path) throws IOException {
        openReadChar(inputStream, fileReader, path, null);
    }

    public static final void openReadChar(Reader reader, UraFileCharReader fileReader, String path) throws IOException {
        int charSize = fileReader.getBufferSize();
        char[] buffer = new char[charSize];
        long readCount = 1L;
        int bufferSize = -1;
        while (-1 != (bufferSize = reader.read(buffer, 0, charSize))) {
//            if (buffer == null) {
//                break;
//            }
            fileReader.readCharArray(buffer, path, bufferSize, readCount);
            readCount = (readCount == Long.MAX_VALUE) ? 1L : readCount + 1L;
        }
    }
    public static final void openReadChar(String path, UraFileCharReader fileReader) throws IOException {
        openReadChar(path, fileReader, null);
    }

    public static final void openReadChar(String path, UraFileCharReader fileReader, Charset charset) throws IOException {
        InputStream inputStream = null;
        try {
            inputStream = UraIOUtils.openInputStreamQuietly(path);
            if (charset == null) {
                charset = UraCharset.ME.getCharset(path);
                if (charset == null) {
                    charset = Charset.defaultCharset();
                }
            }
            //
            openReadChar(inputStream, fileReader, path, charset);
        } finally {
            UraIOUtils.closeQuietly(inputStream);
        }
    }

    /**
     * 。<br>
     * @param path
     * @param fileReader
     * @param charset
     * @throws IOException
     */
    public static final void openReadLine(File path, UraFileLineReader fileReader, Charset charset) throws IOException {
        InputStream inputStream = null;
        try {
            inputStream = UraIOUtils.openInputStreamQuietly(path);
            openReadLine(inputStream, fileReader, path.getPath(), charset);
        } finally {
            UraIOUtils.closeQuietly(inputStream);
        }
    }

    /**
     * 。<br>
     * @param path
     * @param fileReader
     * @throws IOException
     */
    public static final void openReadLine(File path, UraFileLineReader fileReader) throws IOException {
        openReadLine(path, fileReader, null);
    }

    public static final void openReadLine(InputStream inputStream, UraFileLineReader fileReader, String path, Charset charset) throws IOException {
        Reader reader = null;
        try {
            if (charset == null) {
                charset = UraCharset.ME.getCharset(path);
                if (charset == null) {
                    charset = Charset.defaultCharset();
                }
            }
            //
            reader = new InputStreamReader(inputStream, charset);
            //
            openReadLine(reader, fileReader, path);
        } finally {
            closeQuietly(reader);
        }
    }

    public static final void openReadLine(InputStream inputStream, UraFileLineReader fileReader, String path) throws IOException {
        openReadLine(inputStream, fileReader, path, null);
    }

    public static final void openReadLine(Reader reader, UraFileLineReader fileReader, String path) throws IOException {
        BufferedReader bufferedReader = null;
        try {
            bufferedReader = getResetableReader(reader);
            String lineStr;
            long lineNum = 1L;
            while ((lineStr = bufferedReader.readLine()) != null) {
                //
                fileReader.readLine(lineStr, path, lineNum);
                //
                lineNum = (lineNum == Long.MAX_VALUE) ? 1L : lineNum + 1L;
            }
        } finally {
            closeQuietly(bufferedReader);
        }
    }

    public static final void openReadLine(String path, UraFileLineReader fileReader) throws IOException {
        openReadLine(path, fileReader, null);
    }
    public static final void openReadLine(String path, UraFileLineReader fileReader, Charset charset) throws IOException {
        InputStream inputStream = null;
        try {
            inputStream = UraIOUtils.openInputStreamQuietly(path);
            openReadLine(inputStream, fileReader, path, charset);
        } finally {
            UraIOUtils.closeQuietly(inputStream);
        }
    }

    public static final void openWriteFile(File outerFile, UraOutputStreamer outputStreamer) throws IOException {
        OutputStream outputStream = null;
        try {
            outputStream = UraIOUtils.openOutputStream(outerFile);
            outputStreamer.write(outputStream);
        } finally {
            UraIOUtils.closeQuietly(outputStream);
        }
    }
    public static final void openWriteFile(File outerFile, UraFileWriter fileWriter) throws IOException {
        openWriteFile(outerFile, fileWriter, null);
    }
    public static final void openWriteFile(File outerFile, UraFileWriter fileWriter, Charset charset) throws IOException {
        OutputStream outputStream = null;
        try {
            outputStream = UraIOUtils.openOutputStream(outerFile);
            openWriteStream(outputStream, fileWriter, charset);
        } finally {
            UraIOUtils.closeQuietly(outputStream);
        }
    }
    public static final void openWriteFile(String path, UraOutputStreamer outputStreamer) throws IOException {
        OutputStream outputStream = null;
        try {
            outputStream = UraIOUtils.openOutputStream(path);
            outputStreamer.write(outputStream);
        } finally {
            UraIOUtils.closeQuietly(outputStream);
        }
    }
    public static final void openWriteFile(String path, UraFileWriter fileWriter) throws IOException {
        openWriteFile(path, fileWriter, null);
    }
    public static final void openWriteFile(String path, UraFileWriter fileWriter, Charset charset) throws IOException {
        OutputStream outputStream = null;
        try {
            outputStream = UraIOUtils.openOutputStream(path);
            openWriteStream(outputStream, fileWriter, charset);
        } finally {
            UraIOUtils.closeQuietly(outputStream);
        }
    }
    public static final void openWriteStream(OutputStream outputStream, UraFileWriter fileWriter) throws IOException {
        openWriteStream(outputStream, fileWriter, null);
    }
    public static final void openWriteStream(OutputStream outputStream, UraFileWriter fileWriter, Charset charset) throws IOException {
        if (charset == null) {
            charset = Charset.defaultCharset();
        }
        Writer writer = null;
        try {
            writer = new OutputStreamWriter(outputStream, charset);
            fileWriter.write(writer);
        } finally {
            closeQuietly(writer);
        }
    }

public static List<String> readLines(File path) {
        return readLines(path, null);
    }
    public static List<String> readLines(File path, Charset charset) {
        final List<String> result = newArrayList();
        try {
            openReadLine(path, new UraFileLineReader(){
                @Override
                public void readLine(String line, String path, long lineNumber) {
                    result.add(line);
                }
            }, charset);
        } catch (IOException ioe) {
            LOGGER.log("URA-T999", ioe);
        }
        return result;
    }
    public static List<String> readLines(InputStream inputStream) {
        return readLines(inputStream, null);
    }
    public static List<String> readLines(InputStream inputStream, Charset charset) {
        final List<String> result = newArrayList();
        try {
            openReadLine(inputStream, new UraFileLineReader(){
                @Override
                public void readLine(String line, String path, long lineNumber) {
                    result.add(line);
                }
            }, null, charset);
        } catch (IOException ioe) {
            LOGGER.log("URA-T999", ioe);
        }
        return result;
    }
    //    public static final void openWrite(Writer writer, UraFileWriter fileWriter) throws IOException {
//        fileWriter.write(writer);
//    }
    public static List<String> readLines(Reader reader) {
        final List<String> result = newArrayList();
        try {
            openReadLine(reader, new UraFileLineReader(){
                @Override
                public void readLine(String line, String path, long lineNumber) {
                    result.add(line);
                }
            }, null);
        } catch (IOException ioe) {
            LOGGER.log("URA-T999", ioe);
        }
        return result;
    }
    public static List<String> readLines(String path) {
        return readLines(path, null);
    }
    public static List<String> readLines(String path, Charset charset) {
        final List<String> result = newArrayList();
        try {
            openReadLine(path, new UraFileLineReader(){
                @Override
                public void readLine(String line, String path, long lineNumber) {
                    result.add(line);
                }
            }, charset);
        } catch (IOException ioe) {
            LOGGER.log("URA-T999", ioe);
        }
        return result;
    }

    public static void resetQuietly(Reader input) {
        try {
            if (input != null) {
                input.reset();
            }
        } catch (IOException ioe) {
            LOGGER.log("URA-E013", ioe);
        }
    }

    public static <E> void writeLine(E line, OutputStream output) throws IOException {
        byte[] lineEndingByte = LINE_SEPARATOR.getBytes(Charset.defaultCharset());

        if (line != null) {
            output.write(line.toString().getBytes(Charset.defaultCharset()));
        }
        output.write(lineEndingByte);
    }
    public static <E> void writeLine(E line, OutputStream output, Charset encoding) throws IOException {
        if (encoding == null) {
            encoding = Charset.defaultCharset();
        }

        byte[] lineEndingByte = LINE_SEPARATOR.getBytes(encoding);

        if (line != null) {
            output.write(line.toString().getBytes(encoding));
        }
        output.write(lineEndingByte);
    }

    public static <E> void writeLine(E line, OutputStream output, String lineEnding) throws IOException {
        byte[] lineEndingByte = (lineEnding != null) ?
                lineEnding.getBytes(Charset.defaultCharset()) :
                    LINE_SEPARATOR.getBytes(Charset.defaultCharset());

        if (line != null) {
            output.write(line.toString().getBytes(Charset.defaultCharset()));
        }
        output.write(lineEndingByte);
    }

    public static <E> void writeLine(E line, OutputStream output, String lineEnding, Charset encoding) throws IOException {

        if (encoding == null) {
            encoding = Charset.defaultCharset();
        }

        byte[] lineEndingByte = (lineEnding != null) ?
                lineEnding.getBytes(encoding) :
                    LINE_SEPARATOR.getBytes(encoding);

        if (line != null) {
            output.write(line.toString().getBytes(encoding));
        }
        output.write(lineEndingByte);
    }

    public static <E> void writeLine(E line, Writer writer) throws IOException {

        if (line != null) {
            writer.write(line.toString());
        }
        writer.write(LINE_SEPARATOR);
    }

    public static <E> void writeLine(E line, Writer writer, String lineEnding) throws IOException {
        if (lineEnding == null) {
            lineEnding = LINE_SEPARATOR;
        }
        if (line != null) {
            writer.write(line.toString());
        }
        writer.write(lineEnding);
    }

    public static <E> void writeLines(Collection<E> lines, OutputStream output, Charset encoding)
            throws IOException {
        writeLines(lines, output, null, encoding);
    }

    public static <E> void writeLines(Collection<E> lines, OutputStream output, String lineEnding)
            throws IOException {
        writeLines(lines, output, lineEnding, Charset.defaultCharset());
    }
    public static <E> void writeLines(Collection<E> lines, OutputStream output, String lineEnding, Charset encoding)
            throws IOException {
        if (lines == null) {
            return;
        }
        //
        if (encoding == null) {
            encoding = Charset.defaultCharset();
        }
        byte[] lineEndingByte = (lineEnding != null) ?
                lineEnding.getBytes(encoding) :
                    LINE_SEPARATOR.getBytes(encoding);
        for (E line: lines) {
            if (line != null) {
                output.write(line.toString().getBytes(encoding));
            }
            output.write(lineEndingByte);
        }
    }
    public static <E> void writeLines(Collection<E> lines, Writer writer, String lineEnding)
            throws IOException {
        if (lines == null) {
            return;
        }
        if (lineEnding == null) {
            lineEnding = LINE_SEPARATOR;
        }

        for (E line: lines) {
            if (line != null) {
                writer.write(line.toString());
            }
            writer.write(lineEnding);
        }
    }
}
