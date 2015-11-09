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
 * $Id: UraPipeUtils.java$
 */
package org.uranoplums.typical.io;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.Reader;
import java.io.Writer;
import java.util.concurrent.atomic.AtomicLong;

import org.uranoplums.typical.util.UraUtils;


/**
 * UraPipeUtilsクラス。<br>
 *
 * @since 2015/11/19
 * @author syany
 */
public class UraPipeUtils extends UraUtils {

    /**
     * 。<br>
     * @param reader
     * @param writer
     * @return
     * @throws IOException
     */
    public static long copyLarge(final String reader, final String writer) throws IOException {
        // 書き込んだバッファサイズ
        final AtomicLong count = new AtomicLong(0L);
        UraRWUtils.openWriteFile(writer, new UraFileWriter(){
            @Override
            public void write(final Writer writer) throws IOException {
                UraRWUtils.openReadChar(reader, new AbsUraFileCharRead(){
                    @Override
                    public void readCharArray(char[] buffer, String path, int bufferSize, long lineNum) throws IOException {
                        writer.write(buffer, 0, bufferSize);
                        count.getAndAdd(bufferSize);
                    }
                }, null);
            }
        });
        return count.get();
    }
    /**
     * 。<br>
     * @param reader
     * @param writer
     * @return
     * @throws IOException
     */
    public static long copyLarge(final File reader, final File writer) throws IOException {
     // 書き込んだバッファサイズ
        final AtomicLong count = new AtomicLong(0L);
        UraRWUtils.openWriteFile(writer, new UraFileWriter(){
            @Override
            public void write(final Writer writer) throws IOException {
                UraRWUtils.openReadChar(reader, new AbsUraFileCharRead(){
                    @Override
                    public void readCharArray(char[] buffer, String path, int bufferSize, long lineNum) throws IOException {
                        writer.write(buffer, 0, bufferSize);
                        count.getAndAdd(bufferSize);
                    }
                });
            }
        });
        return count.get();
    }

    /**
     * 。<br>
     * @param inputStream
     * @param writer
     * @return
     * @throws IOException
     */
    public static long copyLarge(final InputStream inputStream, final File writer) throws IOException {
        // 書き込んだバッファサイズ
        final AtomicLong count = new AtomicLong(0L);
        UraRWUtils.openWriteFile(writer, new UraOutputStreamer(){
            @Override
            public void write(final OutputStream outputStream) throws IOException {
                UraRWUtils.openReadBuffer(inputStream, new AbsUraFileByteRead() {
                    @Override
                    public void readByteArray(byte[] buffer, String path, int bufferSize, long readCount) throws IOException {
                        outputStream.write(buffer, 0, bufferSize);
                        count.getAndAdd(bufferSize);
                    }
                }, null);
            }
        });
        return count.get();
    }

    /**
     * 。<br>
     * @param inputStream
     * @param writer
     * @return
     * @throws IOException
     */
    public static long copyLarge(final InputStream inputStream, final String writer) throws IOException {
        // 書き込んだバッファサイズ
        final AtomicLong count = new AtomicLong(0L);
        UraRWUtils.openWriteFile(writer, new UraOutputStreamer(){
            @Override
            public void write(final OutputStream outputStream) throws IOException {
                UraRWUtils.openReadBuffer(inputStream, new AbsUraFileByteRead() {
                    @Override
                    public void readByteArray(byte[] buffer, String path, int bufferSize, long readCount) throws IOException {
                        outputStream.write(buffer, 0, bufferSize);
                        count.getAndAdd(bufferSize);
                    }
                }, null);
            }
        });
        return count.get();
    }
    /**
     * 。<br>
     * @param inputStream
     * @param outputStream
     * @return
     * @throws IOException
     */
    public static long copyLarge(InputStream inputStream, final OutputStream outputStream) throws IOException {
        // 書き込んだバッファサイズ
        final AtomicLong count = new AtomicLong(0L);
        UraRWUtils.openReadBuffer(inputStream, new AbsUraFileByteRead() {
            @Override
            public void readByteArray(byte[] buffer, String path, int bufferSize, long readCount) throws IOException {
                outputStream.write(buffer, 0, bufferSize);
                count.getAndAdd(bufferSize);
            }
        }, null);
        return count.longValue();
    }

    /**
     * 。<br>
     * @param reader
     * @param writer
     * @return
     * @throws IOException
     */
    public static long copyLarge(Reader reader, final Writer writer) throws IOException {
        // 書き込んだバッファサイズ
        final AtomicLong count = new AtomicLong(0L);
        UraRWUtils.openReadChar(reader, new AbsUraFileCharRead() {
            @Override
            public void readCharArray(char[] buffer, String path, int bufferSize, long lineNum) throws IOException {
                writer.write(buffer, 0, bufferSize);
                count.getAndAdd(bufferSize);
            }
        }, null);
        return count.longValue();
    }
}
