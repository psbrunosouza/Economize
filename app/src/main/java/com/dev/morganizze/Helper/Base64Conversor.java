package com.dev.morganizze.Helper;

import android.util.Base64;

import java.util.Arrays;

public class Base64Conversor {
    public static String codificarBase64(String texto){
        return Base64.encodeToString(texto.getBytes(), Base64.DEFAULT).replaceAll("(\\n|\\r)", "");
    }

    public static String decodificarBase64(String texto){
        return Arrays.toString(Base64.decode(texto.getBytes(), Base64.DEFAULT));
    }
}
