package com.dev.morganizze.Model;

import com.dev.morganizze.Helper.AutenticacaoFirebase;
import com.dev.morganizze.Helper.Base64Conversor;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.Exclude;

public class Usuario {
    private String id, nome;
    private double receitaTotal, despesaTotal;
    private DatabaseReference referencia = AutenticacaoFirebase.databaseReferencia();

    public Usuario() {

    }

    public void salvarUsuario(String id, String nome, double receitaTotal, double despesaTotal){
        this.id = id;
        this.nome = nome;
        this.receitaTotal = receitaTotal;
        this.despesaTotal = despesaTotal;

        referencia.child("usuarios")
                .child(id)
                .setValue(this);
    }

    @Exclude
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public double getReceitaTotal() {
        return receitaTotal;
    }

    public void setReceitaTotal(double receitaTotal) {
        this.receitaTotal = receitaTotal;
    }

    public double getDespesaTotal() {
        return despesaTotal;
    }

    public void setDespesaTotal(double despesaTotal) {
        this.despesaTotal = despesaTotal;
    }
}
