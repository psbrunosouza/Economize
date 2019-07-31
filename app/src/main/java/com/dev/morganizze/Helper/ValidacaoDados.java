package com.dev.morganizze.Helper;

import android.content.Context;
import android.widget.Toast;

public class ValidacaoDados {

    private Context context;
    private String valor, categoria, descricao;

    public ValidacaoDados(String valor, String categoria, String descricao, Context context) {
        this.context = context;
        this.valor = valor;
        this.categoria = categoria;
        this.descricao = descricao;
    }

    public boolean validarCampos(){

        if(!valor.isEmpty()){
            if(!categoria.isEmpty()){
                if(!descricao.isEmpty()){
                    return true;
                }else{
                    Toast.makeText(this.context, "Digite a descrição da receita", Toast.LENGTH_SHORT).show();
                }
            }else{
                Toast.makeText(this.context, "Digite a categoria da receita", Toast.LENGTH_SHORT).show();
            }
        }else{
            Toast.makeText(this.context, "Digite o valor da receita", Toast.LENGTH_SHORT).show();
        }

        return false;
    }
}
