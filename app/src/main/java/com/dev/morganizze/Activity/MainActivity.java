package com.dev.morganizze.Activity;

import android.content.Intent;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.CalendarView;
import android.widget.TextView;


import com.dev.morganizze.Adapter.MovimentacaoAdapter;
import com.dev.morganizze.Helper.AutenticacaoFirebase;
import com.dev.morganizze.Helper.Base64Conversor;
import com.dev.morganizze.Helper.DataFormatar;
import com.dev.morganizze.Helper.DecimalFormatar;
import com.dev.morganizze.Model.Movimentacao;
import com.dev.morganizze.Model.Usuario;
import com.dev.morganizze.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.ValueEventListener;
import com.prolificinteractive.materialcalendarview.CalendarDay;
import com.prolificinteractive.materialcalendarview.MaterialCalendarView;
import com.prolificinteractive.materialcalendarview.OnMonthChangedListener;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private FirebaseAuth autenticacao = AutenticacaoFirebase.autenticacaoReferencia();
    private DatabaseReference referencia = AutenticacaoFirebase.databaseReferencia();
    private TextView nomeUsuario, saldo;
    private MaterialCalendarView calendario;
    private RecyclerView listaMovimentacoes;
    private List<Movimentacao> listaMovimentacao = new ArrayList<>();
    private MovimentacaoAdapter adapter;

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        toolbar.setTitle("Economize");
        toolbar.setTitleTextColor(getResources().getColor(android.R.color.white));
        toolbar.setElevation(0);
        setSupportActionBar(toolbar);

        nomeUsuario = findViewById(R.id.nomeUsuario);
        saldo = findViewById(R.id.saldo);
        listaMovimentacoes = findViewById(R.id.listaMovimentacoes);
        calendario = findViewById(R.id.calendario);

        recuperarListaMovimentacao(DataFormatar.dataPostagem(DataFormatar.dataAtual()));
    }

    public void recuperarDados(){
        String idUsuario = Base64Conversor.codificarBase64(autenticacao.getCurrentUser().getEmail());
        DatabaseReference usuarios = referencia.child("usuarios").child(idUsuario);

        usuarios.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                Usuario usuario = dataSnapshot.getValue(Usuario.class);

                String nome = usuario.getNome();
                double despesaTotal = usuario.getDespesaTotal();
                double receitaTotal = usuario.getReceitaTotal();

                nomeUsuario.setText(String.format("Olá, %s", nome));
                saldo.setText(String.format("R$ %s", DecimalFormatar.dinheiroFormatar(receitaTotal - despesaTotal)));
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

    public void irReceita(View view){
        startActivity(new Intent(MainActivity.this, ReceitaActivity.class));
    }

    public void irDespesa(View view){
        startActivity(new Intent(MainActivity.this, DespesasActivity.class));
    }

    public void calendarioConfiguracao(){
        calendario.setOnMonthChangedListener(new OnMonthChangedListener() {
            @Override
            public void onMonthChanged(MaterialCalendarView widget, CalendarDay date) {
                String mes = String.format("%02d", date.getMonth() + 1);
                String ano = String.format("%s",date.getYear());

                String dataSeleciona = String.format("%s", mes + ano);
                recuperarListaMovimentacao(dataSeleciona);
            }
        });
    }

    public void recuperarListaMovimentacao(String data){
        //ADAPTER CUSTOMIZADO
        adapter = new MovimentacaoAdapter(listaMovimentacao, getApplicationContext());

        //GERENCIADOR DE LAYOUT
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());

        //CONFIGURAÇÕES
        listaMovimentacoes.setAdapter(adapter);
        listaMovimentacoes.setLayoutManager(layoutManager);
        listaMovimentacoes.setHasFixedSize(true);

        //-- TODO: RECUPERAR LISTAGEM FIREBASE
        String idUsuario = Base64Conversor.codificarBase64(autenticacao.getCurrentUser().getEmail());
        DatabaseReference movimentacoes = referencia.child("movimentacoes").child(idUsuario).child(data);
        movimentacoes.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                listaMovimentacao.clear();

                for(DataSnapshot dados : dataSnapshot.getChildren()){
                    Movimentacao movimentacao = dados.getValue(Movimentacao.class);
                    listaMovimentacao.add(movimentacao);
                }

                adapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_principal, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch(item.getItemId()){
            case R.id.item_sair:
                autenticacao.signOut();
                finish();
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onStart() {
        super.onStart();
        recuperarDados();
        calendarioConfiguracao();
    }
}
