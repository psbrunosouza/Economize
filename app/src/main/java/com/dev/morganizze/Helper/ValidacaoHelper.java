package com.dev.morganizze.Helper;

import android.content.Context;
import android.widget.Toast;

import studio.carbonylgroup.textfieldboxes.ExtendedEditText;

public class ValidacaoHelper {

    private ExtendedEditText txt_nome, txt_email, txt_senha;
    private String nome, email, senha;
    private Context context;

    public ValidacaoHelper() {
    }

    //LOGIN
    public ValidacaoHelper(String email, String senha, Context context) {
        this.email = email;
        this.senha = senha;
        this.context = context;
    }

    //CADASTRO
    public ValidacaoHelper(String nome, String email, String senha, Context context) {
        this.nome = nome;
        this.email = email;
        this.senha = senha;
        this.context = context;
    }

    public void chamarIds(ExtendedEditText email, ExtendedEditText senha){
        this.txt_email = email;
        this.txt_senha = senha;
    }

    public void chamarIds(ExtendedEditText nome, ExtendedEditText email, ExtendedEditText senha){
        this.txt_nome = nome;
        this.txt_email = email;
        this.txt_senha = senha;
    }

    public boolean validarCadastro(){
        if(!nome.isEmpty()){
            if(!email.isEmpty()){
                if(!senha.isEmpty()){
                    return true;
                }else{
                    txt_senha.setError("Preencha com a sua senha");
                }
            }else{
                txt_email.setError("Preencha com o seu e-mail");
            }
        }else{
            txt_nome.setError("Preencha com seu nome de usuário");
        }
        return false;
    }

    public boolean validarAcesso(){
        if(!email.isEmpty()){
            if(!senha.isEmpty()){
                return true;
            }else{
                txt_senha.setError("Digite sua senha de usuário");
            }
        }else{
            txt_email.setError("Digite um endereço de e-mail");
        }

        return false;
    }


}
