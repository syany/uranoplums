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
 * $Id: UraCipherTest.java$
 */
package org.uranoplums.typical.util.codec;

import static org.junit.Assert.*;

import java.security.interfaces.RSAPublicKey;
import java.util.List;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.uranoplums.typical.util.codec.UraCipher.AlgorithmType;
import org.uranoplums.typical.util.codec.UraCipher.RSAKeyPair;
import org.uranoplums.typical.util.i18n.UraCharset;


/**
 * UraCipherTestクラス。<br>
 *
 * @since 2016/01/28
 * @author syany
 */
public class UraCipherTest {

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

//    /**
//     * {@link org.uranoplums.typical.util.codec.UraCipher.EncryptBuilder#key(java.lang.Object)} のためのテスト・メソッド。
//     */
//    @Test
//    public final void testKey() {
//        UraCipher.newEncryptBuilder("AAA".getBytes(UraCharset.UTF8)).AES().CBCFeedback().PKCS5Padding().preBuild();
//
//
//        fail("まだ実装されていません"); // TODO
//    }
//    @Test
//    public final void testKey2() {
//        UraEncipher re = UraCipher.newEncryptBuilder("AAA".getBytes(UraCharset.UTF8)).AES().CBCFeedback().PKCS5Padding().build();
//
//        fail("まだ実装されていません["+ re.toString()); // TODO
//    }
    @Test
    public final void testKey3() {
        String ext = "Wellcome to uranoplums";
        byte[] extByte = ext.getBytes(UraCharset.UTF8);
        UraEncipher re = UraCipher.newEncryptBuilder(extByte).build();
        byte[] actByte = UraCipher.newDecryptBuilder(re.getEncryptData()).setIv(re.getIv()).key(re.getKey()).decrypt();
        String act = new String(actByte, UraCharset.UTF8);
        assertEquals(ext, act);
//        fail("まだ実装されていません["+ act + "]"); // TODO
    }
    @Test
    public final void testKey4() {
        String ext = "Wellcome to uranoplums";
        byte[] extByte = ext.getBytes(UraCharset.UTF8);
        UraEncipher re = UraCipher.newEncryptBuilder(extByte).build();
        String encStr = UraBase64.encodeBase64String(re.getEncryptData());
        String keyStr = UraBase64.encodeBase64String(re.getKey().getEncoded());
        String ivStr = UraBase64.encodeBase64String(re.getIv());
        System.out.println("ext["+ext+"] is ["+encStr+"](key:"+keyStr+", iv:"+ivStr+")("+re.getKeySize()+"[byte])");
        byte[] encByte = UraBase64.decodeBase64(encStr);
        byte[] keyByte = UraBase64.decodeBase64(keyStr);
        byte[] ivByte = UraBase64.decodeBase64(ivStr);
        byte[] actByte = UraCipher.newDecryptBuilder(encByte).setIv(ivByte).key(keyByte).decrypt();
        String act = new String(actByte, UraCharset.UTF8);
        assertEquals(ext, act);
//        fail("まだ実装されていません["+ act + "]"); // TODO
    }
    @Test
    public final void testKey5() {
        String ext = "Wellcome to uranoplums";
        byte[] extByte = ext.getBytes(UraCharset.UTF8);
        UraEncipher re = UraCipher.newEncryptBuilder(extByte).algorithm(AlgorithmType.BLOWFISH).build();
        String encStr = UraBase64.encodeBase64String(re.getEncryptData());
        String keyStr = UraBase64.encodeBase64String(re.getKey().getEncoded());
        String ivStr = UraBase64.encodeBase64String(re.getIv());
        System.out.println("ext["+ext+"] is ["+encStr+"](key:"+keyStr+", iv:"+ivStr+")("+re.toString()+")");
        byte[] encByte = UraBase64.decodeBase64(encStr);
        byte[] keyByte = UraBase64.decodeBase64(keyStr);
        byte[] ivByte = UraBase64.decodeBase64(ivStr);
        byte[] actByte = UraCipher.newDecryptBuilder(encByte).algorithm(AlgorithmType.BLOWFISH).setIv(ivByte).key(keyByte).decrypt();
        String act = new String(actByte, UraCharset.UTF8);
        assertEquals(ext, act);
//        fail("まだ実装されていません["+ act + "]"); // TODO
    }
    @Test
    public void testRSA01() throws Exception {
        String ext = "Wellcome to uranoplums";
        RSAKeyPair pair = UraCipher.makeRandomRSAKeyPair(2048);
//        List<String> keys = UraBase64.encodeBase64StringList(pair.getPublicModulusByte(), pair.getPublicExponentByte());
        byte[] mKey = pair.getPublicModulusByte();
        byte[] eKey = pair.getPublicExponentByte();
        RSAPublicKey rsaPKey = UraCipher.generateRSAPublicKey(mKey, eKey);

//        UraEncipher encipher = UraCipher.newEncryptBuilder(ext.getBytes()).key(rsaPKey).RSA().feedbackMode(FeedbackMode.ECB).paddingType(PaddingType.PKCS1).build();
//        byte[] actByte = UraCipher.newDecryptBuilder(encipher.getEncryptData()).key(pair.getPrivateKey()).RSA().feedbackMode(FeedbackMode.ECB).paddingType(PaddingType.PKCS1).decrypt();
        UraEncipher encipher = UraCipher.newEncryptBuilder(ext.getBytes()).key(rsaPKey).RSA().build();
        byte[] actByte = UraCipher.newDecryptBuilder(encipher.getEncryptData()).key(pair.getPrivateKey()).RSA().decrypt();
        String act = new String(actByte);
        System.out.println(ext + " is decipher ["+ act+"]");
        assertEquals(ext, act);
    }
    @Test
    public void testRSA02() throws Exception {
        String ext = "Wellcome to uranoplums";
        RSAKeyPair pair = UraCipher.makeRandomRSAKeyPair(2048);
        final List<String> keyStrList = UraBase64.encodeBase64StringList(pair.getPublicModulusByte(), pair.getPublicExponentByte());
//        byte[] mKey = pair.getPublicModulusByte();
//        byte[] eKey = pair.getPublicExponentByte();
        final List<byte[]> bKey = UraBase64.decodeBase64List(keyStrList.get(0), keyStrList.get(1));
        RSAPublicKey rsaPKey = UraCipher.generateRSAPublicKey(bKey.toArray(new byte[0][]));
//        byte[] keyByte = UraBase64.decodeBase64(keyStr);

        UraEncipher encipher = UraCipher.newEncryptBuilder(ext.getBytes()).key(rsaPKey).RSA().build();

        String encStr = UraBase64.encodeBase64String(encipher.getEncryptData());
        System.out.println("ext["+ext+"] is ["+encStr+"](key:"+keyStrList+")");

        byte[] actByte = UraCipher.newDecryptBuilder(encipher.getEncryptData()).key(pair.getPrivateKey()).RSA().decrypt();
        String act = new String(actByte);
        System.out.println(ext + " is decipher ["+ act+"]");
        assertEquals(ext, act);
    }
}
