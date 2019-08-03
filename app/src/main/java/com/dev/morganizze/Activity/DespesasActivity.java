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

public class DespesasActivity extends AppCompatActivity {
    private ExtendedEditText valor_despesa, categoria_despesa, descricao_despesa, data_despesa;
    private TextView totalValor_despesa;
    private FloatingActionButton fab_despesa;
    private ValidacaoDados validacao;
    private double despesaAtualizada, despesaTotal;
    private Movimentacao movimentacao = new Movimentacao();
    private DatabaseReference referencia = AutenticacaoFirebase.databaseReferencia();
    private FirebaseAuth autenticacao = AutenticacaoFirebase.autenticacaoReferencia();

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_despesas);
        getSupportActionBar().setElevation(0);
        getSupportActionBar().setTitle(Html.fromHtml("<font color=\"#ffffff\">" + "Adicioanar Despesas" + "</font>"));

        valor_despesa = findViewById(R.id.valor_despesa);
        categoria_despesa = findViewById(R.id.categoria_despesa);
        descricao_despesa = findViewById(R.id.descricao_despesa);
        totalValor_despesa = findViewById(R.id.totalValor_despesa);
        data_despesa = findViewById(R.id.data_despesa);
        fab_despesa = findViewById(R.id.fab_despesa);
    }

    public void adicionarDespesa(View view){
        String idUsuario = Base64Conversor.codificarBase64(autenticacao.getCurrentUser().getEmail());
        DatabaseReference usuario = referencia.child("usuarios").child(idUsuario);

        String strValor = valor_despesa.getText().toString();
        String categoria = categoria_despesa.getText().toString();
        String descricao = descricao_despesa.getText().toString();
        String data = data_despesa.getText().toString();

        validacao = new ValidacaoDados(strValor, categoria, descricao, data, getApplicationContext());

        if(validacao.validarCampos()){
            String tipo = "d";
            double valor = Double.parseDouble(strValor);
            despesaAtualizada = despesaTotal + valor;

            usuario.child("despesaTotal").setValue(despesaAtualizada);
            movimentacao.salvarDados(idUsuario, valor, categoria, descricao, tipo, data);
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
                despesaTotal = usuarioDados.getDespesaTotal();
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
        data_despesa.setText(DataFormatar.dataAtual());
    }
}
