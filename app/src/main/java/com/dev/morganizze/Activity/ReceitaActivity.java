package com.dev.morganizze.Activity;

import android.os.Build;
import android.support.annotation.NonNull;
import android.support.annotation.RequiresApi;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Html;
import android.view.View;
import android.widget.TextView;

import com.dev.morganizze.Helper.AutenticacaoFirebase;
import com.dev.morganizze.Helper.Base64Conversor;
import com.dev.morganizze.Helper.DataFormatar;
import com.dev.morganizze.Helper.ValidacaoDados;
import com.dev.morganizze.Model.Movimentacao;
import com.dev.morganizze.Model.Usuario;
import com.dev.morganizze.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.ValueEventListener;

import studio.carbonylgroup.textfieldboxes.ExtendedEditText;

public class ReceitaActivity extends AppCompatActivity {

    private ExtendedEditText valor_receita, categoria_receita, descricao_receita, data_receita;
    private TextView totalValor_receita;
    private FloatingActionButton fab_receita;
    private ValidacaoDados validacao;
    private double receitaAtualizada, receitaTotal;
    private Movimentacao movimentacao = new Movimentacao();
    private DatabaseReference referencia = AutenticacaoFirebase.databaseReferencia();
    private FirebaseAuth autenticacao = AutenticacaoFirebase.autenticacaoReferencia();

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_receita);
        getSupportActionBar().setElevation(0);
        getSupportActionBar().setTitle(Html.fromHtml("<font color=\"#ffffff\">" + "Adicioanar Despesas" + "</font>"));

        valor_receita = findViewById(R.id.valor_receita);
        categoria_receita = findViewById(R.id.categoria_receita);
        descricao_receita = findViewById(R.id.descricao_receita);
        totalValor_receita = findViewById(R.id.totalValor_receita);
        data_receita = findViewById(R.id.data_receita);
        fab_receita = findViewById(R.id.fab_receita);
    }

    public void adicionarReceita(View view){

        String idUsuario = Base64Conversor.codificarBase64(autenticacao.getCurrentUser().getEmail());
        DatabaseReference usuario = referencia.child("usuarios").child(idUsuario);

        String strValor = valor_receita.getText().toString();
        String categoria = categoria_receita.getText().toString();
        String descricao = descricao_receita.getText().toString();
        String data = data_receita.getText().toString();

        validacao = new ValidacaoDados(strValor, categoria, descricao, data, getApplicationContext());

        if(validacao.validarCampos()){
            String tipo = "r";
            double valor = Double.parseDouble(strValor);
            receitaAtualizada = receitaTotal + valor;
            usuario.child("receitaTotal").setValue(receitaAtualizada);

            movimentacao.setId(idUsuario);
            movimentacao.setValor(valor);
            movimentacao.setCategoria(categoria);
            movimentacao.setDescricao(descricao);
            movimentacao.setData(data);
            movimentacao.setTipo(tipo);

            movimentacao.salvarDados();
            finish();
        }
    }

    public void recuperarDados(){
        String idUsuario = Base64Conversor.codificarBase64(autenticacao.getCurrentUser().getEmail());
        DatabaseReference usuario = referencia.child("usuarios").child(idUsuario);

        usuario.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                Usuario usuarioDados = dataSnapshot.getValue(Usuario.class);
                receitaTotal = usuarioDados.getReceitaTotal();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
        recuperarDados();
        data_receita.setText(DataFormatar.dataAtual());
    }
}
