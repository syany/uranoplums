/*
 * Copyright 2013-2016 the Uranoplums Foundation and the Others,
 * the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.
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
 * $Id: AbsUraBaseNCodec.java$
 */
package org.uranoplums.typical.util.codec;

import java.util.Arrays;

import org.uranoplums.typical.exception.UraDecoderRuntimeException;
import org.uranoplums.typical.exception.UraEncoderRuntimeException;
import org.uranoplums.typical.lang.UraObject;
import org.uranoplums.typical.util.UraStringUtils;


/**
 * UraBaseNCodecクラス。<br>
 *
 * @since 2016/01/29
 * @author syany
 */
public abstract class AbsUraBaseNCodec extends UraObject implements UraBinaryDecoder, UraBinaryEncoder {

    static final int EOF = -1;
    public static final int MIME_CHUNK_SIZE = 76;
    public static final int PEM_CHUNK_SIZE = 64;
//    private static final int DEFAULT_BUFFER_RESIZE_FACTOR = 2;
//    private static final int DEFAULT_BUFFER_SIZE = 8192;
    protected static final int MASK_8BITS = 255;
    protected static final byte PAD_DEFAULT = 61;

    static class Context
    {

        int ibitWorkArea;
        long lbitWorkArea;
        byte[] buffer;
        int pos;
        int readPos;
        boolean eof;
        int currentLinePos;
        int modulus;

        Context() {}

        public String toString()
        {
            return String.format(
                    "%s[buffer=%s, currentLinePos=%s, eof=%s, ibitWorkArea=%s, lbitWorkArea=%s, modulus=%s, pos=%s, readPos=%s]",
                    new Object[] {getClass().getSimpleName(), Arrays.toString(this.buffer), Integer.valueOf(this.currentLinePos),
                            Boolean.valueOf(this.eof), Integer.valueOf(this.ibitWorkArea), Long.valueOf(this.lbitWorkArea),
                            Integer.valueOf(this.modulus), Integer.valueOf(this.pos), Integer.valueOf(this.readPos)});
        }
    }

    protected final byte PAD = 61;
    private final int unencodedBlockSize;
    private final int encodedBlockSize;
    protected final int lineLength;
    private final int chunkSeparatorLength;

    protected AbsUraBaseNCodec(int unencodedBlockSize, int encodedBlockSize, int lineLength, int chunkSeparatorLength)
    {
        this.unencodedBlockSize = unencodedBlockSize;
        this.encodedBlockSize = encodedBlockSize;
        boolean useChunking = (lineLength > 0) && (chunkSeparatorLength > 0);
        this.lineLength = (useChunking ? lineLength / encodedBlockSize * encodedBlockSize : 0);
        this.chunkSeparatorLength = chunkSeparatorLength;
    }

    boolean hasData(Context context)
    {
        return context.buffer != null;
    }

    int available(Context context)
    {
        return context.buffer != null ? context.pos - context.readPos : 0;
    }

    protected int getDefaultBufferSize()
    {
        return 8192;
    }

    private byte[] resizeBuffer(Context context)
    {
        if (context.buffer == null)
        {
            context.buffer = new byte[getDefaultBufferSize()];
            context.pos = 0;
            context.readPos = 0;
        }
        else
        {
            byte[] b = new byte[context.buffer.length * 2];
            System.arraycopy(context.buffer, 0, b, 0, context.buffer.length);
            context.buffer = b;
        }
        return context.buffer;
    }

    protected byte[] ensureBufferSize(int size, Context context)
    {
        if ((context.buffer == null) || (context.buffer.length < context.pos + size)) {
            return resizeBuffer(context);
        }
        return context.buffer;
    }

    int readResults(byte[] b, int bPos, int bAvail, Context context)
    {
        if (context.buffer != null)
        {
            int len = Math.min(available(context), bAvail);
            System.arraycopy(context.buffer, context.readPos, b, bPos, len);
            context.readPos += len;
            if (context.readPos >= context.pos) {
                context.buffer = null;
            }
            return len;
        }
        return context.eof ? -1 : 0;
    }

    protected static boolean isWhiteSpace(byte byteToCheck)
    {
        switch (byteToCheck)
        {
            case 9:
            case 10:
            case 13:
            case 32:
                return true;
        }
        return false;
    }

    @Override
    public Object encode(Object obj)
            throws UraEncoderRuntimeException
    {
        if (!(obj instanceof byte[])) {
            throw new UraEncoderRuntimeException("Parameter supplied to Base-N encode is not a byte[]");
        }
        return encode((byte[]) obj);
    }

    public String encodeToString(byte[] pArray)
    {
        return UraStringUtils.newStringUtf8(encode(pArray));
    }

    public String encodeAsString(byte[] pArray)
    {
        return UraStringUtils.newStringUtf8(encode(pArray));
    }

    @Override
    public Object decode(Object obj)
            throws UraDecoderRuntimeException
    {
        if ((obj instanceof byte[])) {
            return decode((byte[]) obj);
        }
        if ((obj instanceof String)) {
            return decode((String) obj);
        }
        throw new UraDecoderRuntimeException("Parameter supplied to Base-N decode is not a byte[] or a String");
    }

    public byte[] decode(String pArray)
    {
        return decode(UraStringUtils.getBytesUtf8(pArray));
    }

    @Override
    public byte[] decode(byte[] pArray)
    {
        if ((pArray == null) || (pArray.length == 0)) {
            return pArray;
        }
        Context context = new Context();
        decode(pArray, 0, pArray.length, context);
        decode(pArray, 0, -1, context);
        byte[] result = new byte[context.pos];
        readResults(result, 0, result.length, context);
        return result;
    }

    @Override
    public byte[] encode(byte[] pArray)
    {
        if ((pArray == null) || (pArray.length == 0)) {
            return pArray;
        }
        Context context = new Context();
        encode(pArray, 0, pArray.length, context);
        encode(pArray, 0, -1, context);
        byte[] buf = new byte[context.pos - context.readPos];
        readResults(buf, 0, buf.length, context);
        return buf;
    }

    abstract void encode(byte[] paramArrayOfByte, int paramInt1, int paramInt2, Context paramContext);

    abstract void decode(byte[] paramArrayOfByte, int paramInt1, int paramInt2, Context paramContext);

    protected abstract boolean isInAlphabet(byte paramByte);

    public boolean isInAlphabet(byte[] arrayOctet, boolean allowWSPad)
    {
        for (int i = 0; i < arrayOctet.length; i++) {
            if ((!isInAlphabet(arrayOctet[i])) && ((!allowWSPad) || ((arrayOctet[i] != 61) && (!isWhiteSpace(arrayOctet[i]))))) {
                return false;
            }
        }
        return true;
    }

    public boolean isInAlphabet(String basen)
    {
        return isInAlphabet(UraStringUtils.getBytesUtf8(basen), true);
    }

    protected boolean containsAlphabetOrPad(byte[] arrayOctet)
    {
        if (arrayOctet == null) {
            return false;
        }
        for (byte element : arrayOctet) {
            if ((61 == element) || (isInAlphabet(element))) {
                return true;
            }
        }
        return false;
    }

    public long getEncodedLength(byte[] pArray)
    {
        long len = (pArray.length + this.unencodedBlockSize - 1) / this.unencodedBlockSize * this.encodedBlockSize;
        if (this.lineLength > 0) {
            len += (len + this.lineLength - 1L) / this.lineLength * this.chunkSeparatorLength;
        }
        return len;
    }
}
