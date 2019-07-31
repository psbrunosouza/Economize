package com.dev.morganizze.Helper;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class AutenticacaoFirebase {

    private static FirebaseAuth AUTENTICACAO;
    private static DatabaseReference REFERENCIA;

    public static FirebaseAuth autenticacaoReferencia(){
        if(AUTENTICACAO == null){
            return AUTENTICACAO = FirebaseAuth.getInstance();
        }
        return AUTENTICACAO;
    }

    public static DatabaseReference databaseReferencia(){
        if(REFERENCIA == null){
            return REFERENCIA = FirebaseDatabase.getInstance().getReference();
        }
        return REFERENCIA;
    }

}
