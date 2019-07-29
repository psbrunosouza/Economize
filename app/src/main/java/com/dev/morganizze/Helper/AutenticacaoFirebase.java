package com.dev.morganizze.Helper;

import com.google.firebase.auth.FirebaseAuth;

public class AutenticacaoFirebase {

    private static FirebaseAuth AUTENTICACAO;

    public static FirebaseAuth autenticacaoReferencia(){
        if(AUTENTICACAO == null){
            return AUTENTICACAO = FirebaseAuth.getInstance();
        }
        return AUTENTICACAO;
    }
}
