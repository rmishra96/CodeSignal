package com.javapractice.java.Threading;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.io.UnsupportedEncodingException;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;

public class Encryption {

    private static final String AES_ENCRYPTION_KEY = System.getProperty("app_encryptionkey");

    private static final String CHARSET_NAME= "UTF-8";
    private static byte[] ivf = new byte[16];
   public static String encc(String str) throws UnsupportedEncodingException, NoSuchPaddingException, NoSuchAlgorithmException, InvalidAlgorithmParameterException, InvalidKeyException, IllegalBlockSizeException, BadPaddingException {
            if( ivf == null || ivf.length == 0){
                new SecureRandom().nextBytes(ivf);
            }

            IvParameterSpec iv = new IvParameterSpec(ivf);
            SecretKeySpec skeySpec = new SecretKeySpec(AES_ENCRYPTION_KEY.getBytes(CHARSET_NAME), "AES");

            Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5PADDING");
            cipher.init(Cipher.ENCRYPT_MODE, skeySpec, iv);

            byte[] encryptedBytes = cipher.doFinal(str.getBytes(CHARSET_NAME));
            return base64Encode(encryptedBytes);
   }

    private static String base64Encode(byte[] bytes) {
        return new BASE64Encoder().encode(bytes);
    }

    public static void main(String[] args) {

    }
}
