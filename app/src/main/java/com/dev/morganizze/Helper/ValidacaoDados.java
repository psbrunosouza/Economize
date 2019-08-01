package com.dev.morganizze.Helper;

import android.content.Context;
import android.widget.Toast;

public class ValidacaoDados {

    private Context context;
    private String valor, categoria, descricao, data;

    public ValidacaoDados(String valor, String categoria, String descricao, String data, Context context) {
        this.context = context;
        this.valor = valor;
        this.categoria = categoria;
        this.descricao = descricao;
        this.data = data;
    }

    public boolean validarCampos(){

        if(!valor.isEmpty()){
            if(!data.isEmpty()){
                if(!categoria.isEmpty()){
                    if(!descricao.isEmpty()){
                        return true;
                    }else{
                        Toast.makeText(this.context, "Preencha com a categoria da atividade", Toast.LENGTH_SHORT).show();
                    }
                }else{
                    Toast.makeText(this.context, "Preencha com a categoria da atividade", Toast.LENGTH_SHORT).show();
                }
            }else{
                Toast.makeText(this.context, "Preencha com a data da atividade", Toast.LENGTH_SHORT).show();
            }
        }else{
            Toast.makeText(this.context, "Preencha com o valor da atividade", Toast.LENGTH_SHORT).show();
        }

        return false;
    }
}
