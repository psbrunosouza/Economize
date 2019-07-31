package com.dev.morganizze.Activity;

import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.TextView;

import com.dev.morganizze.Helper.AutenticacaoFirebase;
import com.dev.morganizze.Helper.ValidacaoDados;
import com.dev.morganizze.Helper.ValidacaoHelper;
import com.dev.morganizze.Model.Movimentacao;
import com.dev.morganizze.R;
import com.google.firebase.database.DatabaseReference;

import studio.carbonylgroup.textfieldboxes.ExtendedEditText;

public class ReceitaActivity extends AppCompatActivity {

    private ExtendedEditText valor_receita, categoria_receita, descricao_receita;
    private TextView totalValor_receita;
    private FloatingActionButton fab_receita;
    private ValidacaoDados validacao;
    private Movimentacao movimentacao = new Movimentacao();

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_receita);
        getSupportActionBar().setElevation(0);
        getSupportActionBar().setTitle("Adicionar Receita");

        valor_receita = findViewById(R.id.valor_receita);
        categoria_receita = findViewById(R.id.categoria_receita);
        descricao_receita = findViewById(R.id.descricao_receita);
        totalValor_receita = findViewById(R.id.totalValor_receita);
        fab_receita = findViewById(R.id.fab_receita);
    }

    public void adicionarReceita(View view){

        String strValor = valor_receita.getText().toString();
        String categoria = categoria_receita.getText().toString();
        String descricao = descricao_receita.getText().toString();
        double valor = Double.parseDouble(strValor);

        validacao = new ValidacaoDados(strValor, categoria, descricao, getApplicationContext());

        if(validacao.validarCampos()){
            movimentacao.salvarDados(valor, categoria, descricao);
        }
    }

    @Override
    protected void onStart() {
        super.onStart();
        //TODO: FUNCIONALIDADE PARA BLOQUEA FAB E LIBERAR SOMENTE QUANDO O USUÁRIO DIGITAR TODOS OS CAMPOS
        //fab_receita.setEnabled(false);
    }
}
