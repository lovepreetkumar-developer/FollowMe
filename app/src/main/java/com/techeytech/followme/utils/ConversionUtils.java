package com.techeytech.followme.utils;

import android.util.Base64;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

import ru.bullyboo.encoder.Encoder;
import ru.bullyboo.encoder.methods.AES;

/**
 * Created by jatin on 12/19/2017.
 */

public class ConversionUtils {

    public static String encrypt(String text) {
        return Encoder.BuilderAES()
                .message(text)
                .method(AES.Method.AES)
                .key(Constants.ENCRYPT_KEY)
                .encrypt();
    }

    public static String encrypt_with_urlencoding(String text) {
        try {
            return URLEncoder.encode(Encoder.BuilderAES()
                    .message(text)
                    .method(AES.Method.AES)
                    .key(Constants.ENCRYPT_KEY)
                    .encrypt(), "UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            return "";

        }
    }

    public static String encrypt_for_segments(String text) {
        try {
            return URLEncoder.encode(Base64.encodeToString(Encoder.BuilderAES()
                    .message(text)
                    .method(AES.Method.AES)
                    .key(Constants.ENCRYPT_KEY)
                    .encrypt().getBytes(), Base64.DEFAULT), "UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            return "";

        }
    }

    public static String encrypt_for_timezone(String text) {
        try {
            return URLEncoder.encode(Base64.encodeToString(Encoder.BuilderAES()
                    .message(text)
                    .method(AES.Method.AES)
                    .key(Constants.ENCRYPT_KEY)
                    .encrypt().getBytes(), Base64.DEFAULT), "UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            return "";

        }
    }

    public static String encrypt_for_headers(String text) {
        return Base64.encodeToString(Encoder.BuilderAES()
                .message(text)
                .method(AES.Method.AES)
                .key(Constants.ENCRYPT_KEY)
                .encrypt().getBytes(), Base64.DEFAULT).trim();

    }

    public static String encrypt_for_body(String text) {
        return Base64.encodeToString(Encoder.BuilderAES()
                .message(text.trim())
                .method(AES.Method.AES)
                .key(Constants.ENCRYPT_KEY)
                .encrypt().getBytes(), Base64.DEFAULT).trim();

    }

    public static String encryptAes(String text) {
        return Encoder.BuilderAES()
                .message(text)
                .method(AES.Method.AES)
                .key(Constants.ENCRYPT_KEY)
                .encrypt();

    }

    public static String decryptAes(String text) {
        return Encoder.BuilderAES()
                .message(text)
                .method(AES.Method.AES)
                .key(Constants.ENCRYPT_KEY)
                .decrypt();

    }

    public static String encryptBase64(String text) {
        return Base64.encodeToString(text.getBytes(), Base64.DEFAULT).trim();

    }

    public static String decryptBase64(String text) {
        return new String(Base64.decode(text, Base64.DEFAULT));

    }


}
