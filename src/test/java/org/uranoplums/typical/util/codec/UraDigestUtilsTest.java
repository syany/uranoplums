/*
 * Copyright 2013-2016 the Uranoplums Foundation and the Others.
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
 * $Id: UraDigestUtilsTest.java$
 */
package org.uranoplums.typical.util.codec;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;


/**
 * UraDigestUtilsTestクラス。<br>
 *
 * @since 2016/01/22
 * @author syany
 */
public class UraDigestUtilsTest {

    /**
     * 。<br>
     * @throws java.lang.Exception
     */
    @BeforeClass
    public static void setUpBeforeClass() throws Exception {}

    /**
     * 。<br>
     * @throws java.lang.Exception
     */
    @AfterClass
    public static void tearDownAfterClass() throws Exception {}

    /**
     * 。<br>
     * @throws java.lang.Exception
     */
    @Before
    public void setUp() throws Exception {}

    /**
     * 。<br>
     * @throws java.lang.Exception
     */
    @After
    public void tearDown() throws Exception {}

    /**
     * {@link org.uranoplums.typical.util.codec.UraDigestUtils#getDigest(java.lang.String)} のためのテスト・メソッド。
     */
    @Test
    public final void testGetDigest() {
        fail("まだ実装されていません"); // TODO
    }

    /**
     * {@link org.uranoplums.typical.util.codec.UraDigestUtils#getMd2Digest()} のためのテスト・メソッド。
     */
    @Test
    public final void testGetMd2Digest() {
        fail("まだ実装されていません"); // TODO
    }

    /**
     * {@link org.uranoplums.typical.util.codec.UraDigestUtils#getMd5Digest()} のためのテスト・メソッド。
     */
    @Test
    public final void testGetMd5Digest() {
        fail("まだ実装されていません"); // TODO
    }

    /**
     * {@link org.uranoplums.typical.util.codec.UraDigestUtils#getSha1Digest()} のためのテスト・メソッド。
     */
    @Test
    public final void testGetSha1Digest() {
        fail("まだ実装されていません"); // TODO
    }

    /**
     * {@link org.uranoplums.typical.util.codec.UraDigestUtils#getSha256Digest()} のためのテスト・メソッド。
     */
    @Test
    public final void testGetSha256Digest() {
        fail("まだ実装されていません"); // TODO
    }

    /**
     * {@link org.uranoplums.typical.util.codec.UraDigestUtils#getSha384Digest()} のためのテスト・メソッド。
     */
    @Test
    public final void testGetSha384Digest() {
        fail("まだ実装されていません"); // TODO
    }

    /**
     * {@link org.uranoplums.typical.util.codec.UraDigestUtils#getSha512Digest()} のためのテスト・メソッド。
     */
    @Test
    public final void testGetSha512Digest() {
        fail("まだ実装されていません"); // TODO
    }

    /**
     * {@link org.uranoplums.typical.util.codec.UraDigestUtils#getShaDigest()} のためのテスト・メソッド。
     */
    @Test
    public final void testGetShaDigest() {
        fail("まだ実装されていません"); // TODO
    }

    /**
     * {@link org.uranoplums.typical.util.codec.UraDigestUtils#md2(byte[])} のためのテスト・メソッド。
     */
    @Test
    public final void testMd2ByteArray() {
        fail("まだ実装されていません"); // TODO
    }

    /**
     * {@link org.uranoplums.typical.util.codec.UraDigestUtils#md2(java.io.InputStream)} のためのテスト・メソッド。
     */
    @Test
    public final void testMd2InputStream() {
        fail("まだ実装されていません"); // TODO
    }

    /**
     * {@link org.uranoplums.typical.util.codec.UraDigestUtils#md2(java.lang.String)} のためのテスト・メソッド。
     */
    @Test
    public final void testMd2String() {
        fail("まだ実装されていません"); // TODO
    }

    /**
     * {@link org.uranoplums.typical.util.codec.UraDigestUtils#md2Hex(byte[])} のためのテスト・メソッド。
     */
    @Test
    public final void testMd2HexByteArray() {
        fail("まだ実装されていません"); // TODO
    }

    /**
     * {@link org.uranoplums.typical.util.codec.UraDigestUtils#md2Hex(java.io.InputStream)} のためのテスト・メソッド。
     */
    @Test
    public final void testMd2HexInputStream() {
        fail("まだ実装されていません"); // TODO
    }

    /**
     * {@link org.uranoplums.typical.util.codec.UraDigestUtils#md2Hex(java.lang.String)} のためのテスト・メソッド。
     */
    @Test
    public final void testMd2HexString() {
        fail("まだ実装されていません"); // TODO
    }

    /**
     * {@link org.uranoplums.typical.util.codec.UraDigestUtils#md5(byte[])} のためのテスト・メソッド。
     */
    @Test
    public final void testMd5ByteArray() {
        fail("まだ実装されていません"); // TODO
    }

    /**
     * {@link org.uranoplums.typical.util.codec.UraDigestUtils#md5(java.io.InputStream)} のためのテスト・メソッド。
     */
    @Test
    public final void testMd5InputStream() {
        fail("まだ実装されていません"); // TODO
    }

    /**
     * {@link org.uranoplums.typical.util.codec.UraDigestUtils#md5(java.lang.String)} のためのテスト・メソッド。
     */
    @Test
    public final void testMd5String() {
        String extStr = "Hello uranoplums!";
        String ext = UraDigestUtils.md5Hex(extStr.getBytes());
        byte[] extDigest = UraDigestUtils.md5(extStr.getBytes());
        String extBase64 = UraBase64.encodeBase64String(extDigest);
        System.out.println(extStr + " to md5Hex ["+ext+"]");
        String act = UraDigestUtils.md5Hex(extStr);
        byte[] actDigest = UraDigestUtils.getMd5Digest().digest(extStr.getBytes());
//        String actBase64 = UraBase64.encodeBase64String(actDigest);
        System.out.println(extStr + " to md5 to Base64 ["+extBase64+"]");

        String ext1 = UraDigestUtils.md2Hex(extStr.getBytes());
        System.out.println(extStr + " to md2Hex ["+ext1+"]");
        String act1 = UraDigestUtils.md2Hex(extStr);

        String ext2 = UraDigestUtils.sha256Hex(extStr.getBytes());
        System.out.println(extStr + " to sha256Hex ["+ext2+"]");
        String act2 = UraDigestUtils.sha256Hex(extStr);

        String ext3 = UraDigestUtils.sha512Hex(extStr.getBytes());
        System.out.println(extStr + " to sha512Hex ["+ext3+"]");
        String act3 = UraDigestUtils.sha512Hex(extStr);
        assertEquals(ext, act);
        assertEquals(ext1, act1);
        assertEquals(ext2, act2);
        assertEquals(ext3, act3);
        assertArrayEquals(extDigest, actDigest);
    }

    /**
     * {@link org.uranoplums.typical.util.codec.UraDigestUtils#md5Hex(byte[])} のためのテスト・メソッド。
     */
    @Test
    public final void testMd5HexByteArray() {
        fail("まだ実装されていません"); // TODO
    }

    /**
     * {@link org.uranoplums.typical.util.codec.UraDigestUtils#md5Hex(java.io.InputStream)} のためのテスト・メソッド。
     */
    @Test
    public final void testMd5HexInputStream() {
        fail("まだ実装されていません"); // TODO
    }

    /**
     * {@link org.uranoplums.typical.util.codec.UraDigestUtils#md5Hex(java.lang.String)} のためのテスト・メソッド。
     */
    @Test
    public final void testMd5HexString() {
        fail("まだ実装されていません"); // TODO
    }

    /**
     * {@link org.uranoplums.typical.util.codec.UraDigestUtils#md5Hex(java.lang.String, java.nio.charset.Charset)} のためのテスト・メソッド。
     */
    @Test
    public final void testMd5HexStringCharset() {
        fail("まだ実装されていません"); // TODO
    }

    /**
     * {@link org.uranoplums.typical.util.codec.UraDigestUtils#sha(byte[])} のためのテスト・メソッド。
     */
    @Test
    public final void testShaByteArray() {
        fail("まだ実装されていません"); // TODO
    }

    /**
     * {@link org.uranoplums.typical.util.codec.UraDigestUtils#sha(java.io.InputStream)} のためのテスト・メソッド。
     */
    @Test
    public final void testShaInputStream() {
        fail("まだ実装されていません"); // TODO
    }

    /**
     * {@link org.uranoplums.typical.util.codec.UraDigestUtils#sha(java.lang.String)} のためのテスト・メソッド。
     */
    @Test
    public final void testShaString() {
        fail("まだ実装されていません"); // TODO
    }

    /**
     * {@link org.uranoplums.typical.util.codec.UraDigestUtils#sha1(byte[])} のためのテスト・メソッド。
     */
    @Test
    public final void testSha1ByteArray() {
        fail("まだ実装されていません"); // TODO
    }

    /**
     * {@link org.uranoplums.typical.util.codec.UraDigestUtils#sha1(java.io.InputStream)} のためのテスト・メソッド。
     */
    @Test
    public final void testSha1InputStream() {
        fail("まだ実装されていません"); // TODO
    }

    /**
     * {@link org.uranoplums.typical.util.codec.UraDigestUtils#sha1(java.lang.String)} のためのテスト・メソッド。
     */
    @Test
    public final void testSha1String() {
        fail("まだ実装されていません"); // TODO
    }

    /**
     * {@link org.uranoplums.typical.util.codec.UraDigestUtils#sha1Hex(byte[])} のためのテスト・メソッド。
     */
    @Test
    public final void testSha1HexByteArray() {
        fail("まだ実装されていません"); // TODO
    }

    /**
     * {@link org.uranoplums.typical.util.codec.UraDigestUtils#sha1Hex(java.io.InputStream)} のためのテスト・メソッド。
     */
    @Test
    public final void testSha1HexInputStream() {
        fail("まだ実装されていません"); // TODO
    }

    /**
     * {@link org.uranoplums.typical.util.codec.UraDigestUtils#sha1Hex(java.lang.String)} のためのテスト・メソッド。
     */
    @Test
    public final void testSha1HexString() {
        fail("まだ実装されていません"); // TODO
    }

    /**
     * {@link org.uranoplums.typical.util.codec.UraDigestUtils#sha256(byte[])} のためのテスト・メソッド。
     */
    @Test
    public final void testSha256ByteArray() {
        fail("まだ実装されていません"); // TODO
    }

    /**
     * {@link org.uranoplums.typical.util.codec.UraDigestUtils#sha256(java.io.InputStream)} のためのテスト・メソッド。
     */
    @Test
    public final void testSha256InputStream() {
        fail("まだ実装されていません"); // TODO
    }

    /**
     * {@link org.uranoplums.typical.util.codec.UraDigestUtils#sha256(java.lang.String)} のためのテスト・メソッド。
     */
    @Test
    public final void testSha256String() {
        fail("まだ実装されていません"); // TODO
    }

    /**
     * {@link org.uranoplums.typical.util.codec.UraDigestUtils#sha256Hex(byte[])} のためのテスト・メソッド。
     */
    @Test
    public final void testSha256HexByteArray() {
        fail("まだ実装されていません"); // TODO
    }

    /**
     * {@link org.uranoplums.typical.util.codec.UraDigestUtils#sha256Hex(java.io.InputStream)} のためのテスト・メソッド。
     */
    @Test
    public final void testSha256HexInputStream() {
        fail("まだ実装されていません"); // TODO
    }

    /**
     * {@link org.uranoplums.typical.util.codec.UraDigestUtils#sha256Hex(java.lang.String)} のためのテスト・メソッド。
     */
    @Test
    public final void testSha256HexString() {
        fail("まだ実装されていません"); // TODO
    }

    /**
     * {@link org.uranoplums.typical.util.codec.UraDigestUtils#sha256Hex(java.lang.String, java.nio.charset.Charset)} のためのテスト・メソッド。
     */
    @Test
    public final void testSha256HexStringCharset() {
        fail("まだ実装されていません"); // TODO
    }

    /**
     * {@link org.uranoplums.typical.util.codec.UraDigestUtils#sha384(byte[])} のためのテスト・メソッド。
     */
    @Test
    public final void testSha384ByteArray() {
        fail("まだ実装されていません"); // TODO
    }

    /**
     * {@link org.uranoplums.typical.util.codec.UraDigestUtils#sha384(java.io.InputStream)} のためのテスト・メソッド。
     */
    @Test
    public final void testSha384InputStream() {
        fail("まだ実装されていません"); // TODO
    }

    /**
     * {@link org.uranoplums.typical.util.codec.UraDigestUtils#sha384(java.lang.String)} のためのテスト・メソッド。
     */
    @Test
    public final void testSha384String() {
        fail("まだ実装されていません"); // TODO
    }

    /**
     * {@link org.uranoplums.typical.util.codec.UraDigestUtils#sha384Hex(byte[])} のためのテスト・メソッド。
     */
    @Test
    public final void testSha384HexByteArray() {
        fail("まだ実装されていません"); // TODO
    }

    /**
     * {@link org.uranoplums.typical.util.codec.UraDigestUtils#sha384Hex(java.io.InputStream)} のためのテスト・メソッド。
     */
    @Test
    public final void testSha384HexInputStream() {
        fail("まだ実装されていません"); // TODO
    }

    /**
     * {@link org.uranoplums.typical.util.codec.UraDigestUtils#sha384Hex(java.lang.String)} のためのテスト・メソッド。
     */
    @Test
    public final void testSha384HexString() {
        fail("まだ実装されていません"); // TODO
    }

    /**
     * {@link org.uranoplums.typical.util.codec.UraDigestUtils#sha384Hex(java.lang.String, java.nio.charset.Charset)} のためのテスト・メソッド。
     */
    @Test
    public final void testSha384HexStringCharset() {
        fail("まだ実装されていません"); // TODO
    }

    /**
     * {@link org.uranoplums.typical.util.codec.UraDigestUtils#sha512(byte[])} のためのテスト・メソッド。
     */
    @Test
    public final void testSha512ByteArray() {
        fail("まだ実装されていません"); // TODO
    }

    /**
     * {@link org.uranoplums.typical.util.codec.UraDigestUtils#sha512(java.io.InputStream)} のためのテスト・メソッド。
     */
    @Test
    public final void testSha512InputStream() {
        fail("まだ実装されていません"); // TODO
    }

    /**
     * {@link org.uranoplums.typical.util.codec.UraDigestUtils#sha512(java.lang.String)} のためのテスト・メソッド。
     */
    @Test
    public final void testSha512String() {
        fail("まだ実装されていません"); // TODO
    }

    /**
     * {@link org.uranoplums.typical.util.codec.UraDigestUtils#sha512Hex(byte[])} のためのテスト・メソッド。
     */
    @Test
    public final void testSha512HexByteArray() {
        fail("まだ実装されていません"); // TODO
    }

    /**
     * {@link org.uranoplums.typical.util.codec.UraDigestUtils#sha512Hex(java.io.InputStream)} のためのテスト・メソッド。
     */
    @Test
    public final void testSha512HexInputStream() {
        fail("まだ実装されていません"); // TODO
    }

    /**
     * {@link org.uranoplums.typical.util.codec.UraDigestUtils#sha512Hex(java.lang.String)} のためのテスト・メソッド。
     */
    @Test
    public final void testSha512HexString() {
        fail("まだ実装されていません"); // TODO
    }

    /**
     * {@link org.uranoplums.typical.util.codec.UraDigestUtils#sha512Hex(java.lang.String, java.nio.charset.Charset)} のためのテスト・メソッド。
     */
    @Test
    public final void testSha512HexStringCharset() {
        fail("まだ実装されていません"); // TODO
    }

    /**
     * {@link org.uranoplums.typical.util.codec.UraDigestUtils#shaHex(byte[])} のためのテスト・メソッド。
     */
    @Test
    public final void testShaHexByteArray() {
        fail("まだ実装されていません"); // TODO
    }

    /**
     * {@link org.uranoplums.typical.util.codec.UraDigestUtils#shaHex(java.io.InputStream)} のためのテスト・メソッド。
     */
    @Test
    public final void testShaHexInputStream() {
        fail("まだ実装されていません"); // TODO
    }

    /**
     * {@link org.uranoplums.typical.util.codec.UraDigestUtils#shaHex(java.lang.String)} のためのテスト・メソッド。
     */
    @Test
    public final void testShaHexString() {
        fail("まだ実装されていません"); // TODO
    }

    /**
     * {@link org.uranoplums.typical.util.codec.UraDigestUtils#updateDigest(java.security.MessageDigest, byte[])} のためのテスト・メソッド。
     */
    @Test
    public final void testUpdateDigestMessageDigestByteArray() {
        fail("まだ実装されていません"); // TODO
    }

    /**
     * {@link org.uranoplums.typical.util.codec.UraDigestUtils#updateDigest(java.security.MessageDigest, java.io.InputStream)} のためのテスト・メソッド。
     */
    @Test
    public final void testUpdateDigestMessageDigestInputStream() {
        fail("まだ実装されていません"); // TODO
    }

    /**
     * {@link org.uranoplums.typical.util.codec.UraDigestUtils#updateDigest(java.security.MessageDigest, java.lang.String)} のためのテスト・メソッド。
     */
    @Test
    public final void testUpdateDigestMessageDigestString() {
        fail("まだ実装されていません"); // TODO
    }
}
