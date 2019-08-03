package com.dev.morganizze.Helper;

import java.text.DecimalFormat;

public class DecimalFormatar {

    public static String dinheiroFormatar(double valor){
        DecimalFormat decimalFormat = new DecimalFormat("0.##");
        return decimalFormat.format(valor);
    }

}
