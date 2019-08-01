package com.dev.morganizze.Helper;

import java.text.SimpleDateFormat;

public class DataFormatar {

    public static String dataAtual(){
        long data = System.currentTimeMillis();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy");
        return simpleDateFormat.format(data);
    }

    public static String dataPostagem(String data){
        String[] dataFormatada = data.split("/");
        String dia = dataFormatada[0];
        String mes = dataFormatada[1];
        String ano = dataFormatada[2];

        String mesAno = mes + ano;
        return mesAno;
    }
}
