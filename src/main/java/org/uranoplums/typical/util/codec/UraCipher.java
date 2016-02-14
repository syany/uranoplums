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
 * $Id: UraCipher.java$
 */
package org.uranoplums.typical.util.codec;

import java.math.BigInteger;
import java.security.AlgorithmParameters;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.Key;
import java.security.KeyFactory;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.security.Provider;
import java.security.SecureRandom;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.RSAPublicKeySpec;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.KeyGenerator;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

import org.uranoplums.typical.exception.UraSystemRuntimeException;
import org.uranoplums.typical.lang.UraSerialDataObject;
import org.uranoplums.typical.util.i18n.UraCharset;


/**
 * UraCipherクラス。<br>
 *
 * @since 2016/01/27
 * @author syany
 */
public class UraCipher extends UraSerialDataObject implements UraEncipher {

    /**  */
    private static final long serialVersionUID = 1079890080987135353L;
    private static final String PADDING_SFX = "Padding";
    protected final int keySize;
    protected final Key key;
//    protected final Key privateKey;
    protected final byte[] iv;
    protected final byte[] encryptData;
    protected final FeedbackMode feedbackMode;
    protected final AlgorithmType algorithm;
    protected final AlgorithmParameters algorithmParameters;
    protected final Provider provider;
    protected final PaddingType paddingType;

    /**
     * @return keySize を返却します。
     */
    @Override
    public final int getKeySize() {
        return keySize;
    }

    /**
     * @return key を返却します。
     */
    @Override
    public final Key getKey() {
        return key;
    }

    /**
     * @return iv を返却します。
     */
    @Override
    public final byte[] getIv() {
        return iv;
    }

    /**
     * @return encryptData を返却します。
     */
    @Override
    public final byte[] getEncryptData() {
        return encryptData;
    }

    /**
     * @return feedbackMode を返却します。
     */
    @Override
    public final FeedbackMode getFeedbackMode() {
        return feedbackMode;
    }

    /**
     * @return algorithm を返却します。
     */
    @Override
    public final AlgorithmType getAlgorithm() {
        return algorithm;
    }

    /**
     * @return algorithmParameters を返却します。
     */
    @Override
    public final AlgorithmParameters getAlgorithmParameters() {
        return algorithmParameters;
    }

    /**
     * @return provider を返却します。
     */
    @Override
    public final Provider getProvider() {
        return provider;
    }

    /**
     * @return padding を返却します。
     */
    @Override
    public final PaddingType getPadding() {
        return paddingType;
    }
//
//    /**
//     * @return privateKey を返却します。
//     */
//    @Override
//    public Key getPrivateKey() {
//        return privateKey;
//    }

    /**
     * UraCipherBuilderImplクラス。<br>
     *
     * @since 2016/01/30
     * @author syany
     */
    public static class UraCipherBuilderImpl<E> implements UraCipherBuilder<E>{
        protected Mode mode = null;
        protected AlgorithmType algorithm = AlgorithmType.AES;
        protected FeedbackMode feedbackMode = FeedbackMode.CBC;
        protected PaddingType paddingType = PaddingType.PKCS5;
        protected Key key = null;
        protected int keySize = 256;
//        protected Key privateKey = null;
        protected byte[] iv = new byte[0];
        protected final byte[] source;
        /**
         * デフォルトコンストラクタ。<br>
         * @param source
         */
        private UraCipherBuilderImpl(final byte[] source) {
            super();
            this.source = source;
        }
        /* (非 Javadoc)
         * @see org.uranoplums.typical.util.codec.UraCipherBuilder#AES()
         */
        @Override
        public final E AES() {
            return algorithm(AlgorithmType.AES);
        }

        /* (非 Javadoc)
         * @see org.uranoplums.typical.util.codec.UraCipherBuilder#DES()
         */
        @Override
        public final E DES() {
            return algorithm(AlgorithmType.DES);
        }

        /* (非 Javadoc)
         * @see org.uranoplums.typical.util.codec.UraCipherBuilder#RSA()
         */
        @Override
        public final E RSA() {
            return algorithm(AlgorithmType.RSA);
        }

        /* (非 Javadoc)
         * @see org.uranoplums.typical.util.codec.UraCipherBuilder#algorithm(org.uranoplums.typical.util.codec.UraCipher.AlgorithmType)
         */
        @SuppressWarnings ("unchecked")
        @Override
        public final E algorithm(AlgorithmType algorithmType) {
            algorithm = algorithmType;
            switch (algorithm) {
                case RSA:
                    this.keySize = 2048;
                    feedbackMode(FeedbackMode.ECB);
                    paddingType(PaddingType.PKCS1);
                    break;
                default:
                    break;
            }
            return (E) this;
        }

        /* (非 Javadoc)
         * @see org.uranoplums.typical.util.codec.UraCipherBuilder#CBCFeedback()
         */
        @Override
        public final E CBCFeedback() {
            return feedbackMode(FeedbackMode.CBC);
        }

        /* (非 Javadoc)
         * @see org.uranoplums.typical.util.codec.UraCipherBuilder#feedbackMode(org.uranoplums.typical.util.codec.UraCipher.FeedbackMode)
         */
        @SuppressWarnings ("unchecked")
        @Override
        public final E feedbackMode(FeedbackMode feedbackMode) {
            this.feedbackMode = feedbackMode;
            return (E) this;
        }

        /* (非 Javadoc)
         * @see org.uranoplums.typical.util.codec.UraCipherBuilder#PKCS5Padding()
         */
        @Override
        public final E PKCS5Padding() {
            return paddingType(PaddingType.PKCS5);
        }

        /* (非 Javadoc)
         * @see org.uranoplums.typical.util.codec.UraCipherBuilder#paddingType(org.uranoplums.typical.util.codec.UraCipher.PaddingType)
         */
        @SuppressWarnings ("unchecked")
        @Override
        public final E paddingType(PaddingType paddingType) {
            this.paddingType = paddingType;
            return (E) this;
        }

        /* (非 Javadoc)
         * @see org.uranoplums.typical.util.codec.UraCipherBuilder#key(java.lang.Object)
         */
        @SuppressWarnings ("unchecked")
        @Override
        public E key(Object key) {
            if (AlgorithmType.RSA.equals(this.algorithm)) {
                this.key = null;
                return (E) this;
            }
            if (key instanceof Key) {
                // Key クラスはそのまま追加
                this.key = Key.class.cast(key);
            } else if (key instanceof byte[]) {
                // byte[] はSecretKeySpecでKeyクラス変換
                byte[] keyByte = byte[].class.cast(key);
                this.key = new SecretKeySpec(keyByte, this.algorithm.name());
            } else if (key instanceof String) {
                // String はutf-8文字セットでバイトコード化後、Key変換
                byte[] keyByte = String.class.cast(key).getBytes(UraCharset.UTF8);
                this.key = new SecretKeySpec(keyByte, this.algorithm.name());
            } else {
                this.key = null;
            }
            return (E) this;
        }
        /**
         * アルゴリズムパラメータの生成。<br>
         * @return
         */
        protected final String getAlgorithmParamStr() {
            StringBuilder sb = new StringBuilder();
            sb.append(this.algorithm);
            if (this.feedbackMode != null) {
                sb.append("/");
                sb.append(this.feedbackMode.name());
                if (this.paddingType != null) {
                    sb.append("/");
                    sb.append(this.paddingType.name());
                    sb.append(PADDING_SFX);
                }
            }
            return sb.toString();
        }
    }

    public static class DecryptBuilder extends UraCipherBuilderImpl<DecryptBuilder>{

        /**
         * デフォルトコンストラクタ。<br>
         * @param source
         */
        protected DecryptBuilder(final byte[] source) {
            super(source);
            mode = Mode.DECRYPT;
        }
        /**
         * 。<br>
         * @param iv
         * @return
         */
        public final DecryptBuilder setIv(final byte[] iv) {
            this.iv = iv;
            return this;
        }
        /**
         * 。<br>
         * @return
         */
        public final byte[] decrypt() {
            try {
                Cipher cipher = Cipher.getInstance(getAlgorithmParamStr());

                if (this.iv != null && this.iv.length > 0) {
                    IvParameterSpec ivSpec = new IvParameterSpec(this.iv);
                    cipher.init(this.mode.index(), key, ivSpec);
                } else {
                    cipher.init(this.mode.index(), key);
                }
                return cipher.doFinal(this.source);
            } catch (NoSuchAlgorithmException e) {
                // new
                throw new UraSystemRuntimeException(e);
            } catch (NoSuchPaddingException e) {
                // new
                throw new UraSystemRuntimeException(e);
            } catch (InvalidKeyException e) {
                // init
                throw new UraSystemRuntimeException(e);
            } catch (IllegalBlockSizeException e) {
                // final
                throw new UraSystemRuntimeException(e);
            } catch (BadPaddingException e) {
                // final
                throw new UraSystemRuntimeException(e);
            } catch (InvalidAlgorithmParameterException e) {
                // iv
                throw new UraSystemRuntimeException(e);
            }
        }
    }
    public static class EncryptBuilder extends UraCipherBuilderImpl<EncryptBuilder>{
        protected static final String RND_KEY_ALG = "SHA1PRNG";
        protected Mode mode = Mode.ENCRYPT;
        protected byte[] encryptData = new byte[0];
        protected AlgorithmParameters algorithmParameters;
        protected Provider provider;
        /**
         * デフォルトコンストラクタ。<br>
         * @param source
         */
        protected EncryptBuilder(final byte[] source) {
            super(source);
            mode = Mode.ENCRYPT;
        }

        public EncryptBuilder keySize(int keySize) {
            this.keySize = keySize;
            return this;
        }
//        /* (非 Javadoc)
//         * @see org.uranoplums.typical.util.codec.UraCipherBuilder#key(java.lang.Object)
//         */
//        @Override
//        public EncryptBuilder key(Object key) {
//
//            if (key instanceof Key) {
//                this.key = Key.class.cast(key);
//            } else if (key instanceof byte[]) {
//                byte[] keyByte = byte[].class.cast(key);
//                this.key = new SecretKeySpec(keyByte, this.algorithm.name());
//            } else if (key instanceof String) {
//                byte[] keyByte = String.class.cast(key).getBytes(UraCharset.UTF8);
//                this.key = new SecretKeySpec(keyByte, this.algorithm.name());
//            } else {
//                this.key = null;
//            }
//            return this;
//        }
        /**
         * 現在のJavaランタイムによって設定可能なキーサイズを返却。<br>
         * @return
         */
        protected int getAllowedSize() {
            try {
                int max = Cipher.getMaxAllowedKeyLength(this.algorithm.name());
                int size = this.keySize;
                while(size > max) {
                    size /= 2;
                }
                this.keySize = size;
            } catch (NoSuchAlgorithmException e) {
                // ありえない例外
                throw new UraSystemRuntimeException(e);
            }
            return this.keySize;
        }
        /**
         * キーをランダム生成する。<br>
         * @return
         */
        protected Key newRandomKey() {
            try {
//                if (AlgorithmType.RSA.equals(this.algorithm)) {
//                    KeyPairGenerator generator = KeyPairGenerator.getInstance(AlgorithmType.RSA.name());
//                    SecureRandom random = SecureRandom.getInstance(RND_KEY_ALG);
//                    generator.initialize(getAllowedSize(), random);
//                    KeyPair keyPair = generator.genKeyPair();
//                    this.privateKey = keyPair.getPrivate();
//                    return keyPair.getPublic();
//                } else {
                    KeyGenerator generator = KeyGenerator.getInstance(this.algorithm.name());
                    SecureRandom random = SecureRandom.getInstance(RND_KEY_ALG);
                    generator.init(getAllowedSize(), random);
                    return generator.generateKey();
//                }
            } catch (NoSuchAlgorithmException e) {
                // ありえない例外
                throw new UraSystemRuntimeException(e);
            }
        }
        /**
         * 。<br>
         *
         */
        public void preBuild() {
            System.out.println(this.mode + " ing...["+getAlgorithmParamStr()+"]");
        }
        /**
         * 。<br>
         * @return
         */
        public UraEncipher build() {
            try {
                Cipher cipher = Cipher.getInstance(getAlgorithmParamStr());
                if (this.key == null) {
                    this.key = newRandomKey();
                }
                cipher.init(mode.index(), key);
                this.iv = cipher.getIV();
                this.algorithmParameters = cipher.getParameters();
                this.provider = cipher.getProvider();
                this.encryptData = cipher.doFinal(source);
            } catch (NoSuchAlgorithmException e) {
                // new
                throw new UraSystemRuntimeException(e);
            } catch (NoSuchPaddingException e) {
                // new
                throw new UraSystemRuntimeException(e);
            } catch (InvalidKeyException e) {
                // init
                throw new UraSystemRuntimeException(e);
            } catch (IllegalBlockSizeException e) {
                // final
                throw new UraSystemRuntimeException(e);
            } catch (BadPaddingException e) {
                // final
                throw new UraSystemRuntimeException(e);
            }
            return new UraCipher(this);
        }
    }
    /**
     * 暗号化ビルダー。<br>
     * @param source
     * @return
     */
    public static final EncryptBuilder newEncryptBuilder(final byte[] source) {
        return new EncryptBuilder(source);
    }
    /**
     * 復号化ビルダー。<br>
     * @param source
     * @return
     */
    public static final DecryptBuilder newDecryptBuilder(final byte[] source) {
        return new DecryptBuilder(source);
    }
    /**
     * RSA。<br>
     * @param size
     * @return
     */
    public static final RSAKeyPair makeRandomRSAKeyPair(final int size) {
        try {
            final KeyPairGenerator generator = KeyPairGenerator.getInstance(AlgorithmType.RSA.name());
            final SecureRandom random = SecureRandom.getInstance(EncryptBuilder.RND_KEY_ALG);
            generator.initialize(size, random);
            return new RSAKeyPair(generator.genKeyPair());
        } catch (NoSuchAlgorithmException e) {
            throw new UraSystemRuntimeException(e);
        }
    }

    /**
     * 。<br>
     * @param modulus
     * @param publicExponent
     * @return
     */
    public static final RSAPublicKey generateRSAPublicKey(final byte[]... src /*final byte[] modulus, final byte[] publicExponent*/) {
        try {
            KeyFactory keyFactory = KeyFactory.getInstance(AlgorithmType.RSA.name());
            BigInteger mByte = new BigInteger(src[0]);
            BigInteger pEByte = new BigInteger(src[1]);
            RSAPublicKeySpec rsaPublicKeySpec = new RSAPublicKeySpec(mByte, pEByte);
            return RSAPublicKey.class.cast(keyFactory.generatePublic(rsaPublicKeySpec));
        } catch (NoSuchAlgorithmException e) {
            throw new UraSystemRuntimeException(e);
        } catch (InvalidKeySpecException e) {
            throw new UraSystemRuntimeException(e);
        }
    }
    /**
     * デフォルトのコンストラクタ。<br>
     * @param builder 暗号化ビルダクラス
     */
    private UraCipher(EncryptBuilder builder) {
        super();
        this.keySize = builder.keySize;
        this.key = builder.key;
        this.iv = builder.iv;
        this.encryptData = builder.encryptData;
        this.feedbackMode = builder.feedbackMode;
        this.algorithm = builder.algorithm;
        this.algorithmParameters = builder.algorithmParameters;
        this.provider = builder.provider;
        this.paddingType = builder.paddingType;
//        this.privateKey = builder.privateKey;
    }

    /**
     * Modeクラス。<br>
     *
     * @since 2016/01/30
     * @author syany
     */
    public static enum Mode {
        ENCRYPT(Cipher.ENCRYPT_MODE),
        DECRYPT(Cipher.DECRYPT_MODE),
        WRAP(Cipher.WRAP_MODE),
        UNWRAP(Cipher.UNWRAP_MODE);
        /**  */
        protected final int mode;
        /**
         * @return mode を返却します。
         */
        public final int index() {
            return mode;
        }
        /**
         * コンストラクタ。<br>
         * @param mode
         */
        private Mode(final int mode) {
            this.mode = mode;
        }
    }
    /**
     * AlgorithmTypeクラス。<br>
     *
     * @since 2016/01/30
     * @author syany
     */
    public static enum AlgorithmType {
        AES,
        AESWRAP,
        ARCFOUR,
        BLOWFISH,
        DES,
        DESEDE,
        DESEDEWRAP,
        PBEWITHMD5ANDDES,
        PBEWITHMD5ANDTRIPLEDES,
        PBEWITHSHA1ANDDESEDE,
        PBEWITHSHA1ANDRC2_40,
        RC2,
        RSA;
    }

    /**
     * FeedbackModeクラス。<br>
     *
     * @since 2016/01/30
     * @author syany
     */
    public static enum FeedbackMode {
        CBC,
        CFB,
        OFB,
        ECB;
    }
    /**
     * PaddingTypeクラス。<br>
     *
     * @since 2016/01/30
     * @author syany
     */
    public static enum PaddingType {
        PKCS5,
        PKCS1,
        ISO10126,
        SSL3,
        No;
    }
    /**
     * UraCipherBuilderインタフェース。<br>
     *
     * @since 2016/01/30
     * @author syany
     */
    public static interface UraCipherBuilder<E> {
        public E AES();
        public E DES();
        public E RSA();
        public E algorithm(final AlgorithmType algorithmType);
        public E CBCFeedback();
        public E feedbackMode(final FeedbackMode feedbackMode);
        public E PKCS5Padding();
        public E paddingType(final PaddingType paddingType);
        public E key(final Object key);
    }

    public static final class RSAKeyPair extends UraSerialDataObject {
        /**  */
        private static final long serialVersionUID = -8924829722832667205L;
        private final KeyPair pair;

        /**
         * デフォルトコンストラクタ。<br>
         * @param pair
         */
        private RSAKeyPair(final KeyPair pair) {
            super();
            this.pair = pair;
        }
        public final RSAPublicKey getPublicKey() {
            return RSAPublicKey.class.cast(pair.getPublic());
        }
        public final RSAPrivateKey getPrivateKey() {
            return RSAPrivateKey.class.cast(pair.getPrivate());
        }
        public final byte[] getPublicModulusByte() {
            return getPublicKey().getModulus().toByteArray();
        }
        public final byte[] getPublicExponentByte() {
            return getPublicKey().getPublicExponent().toByteArray();
        }
    }
}
