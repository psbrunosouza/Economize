package com.dev.morganizze.Model;

import com.dev.morganizze.Helper.AutenticacaoFirebase;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.Exclude;

public class Movimentacao{
    private double valor;
    private String id, categoria, descricao, tipo;
    private DatabaseReference referencia = AutenticacaoFirebase.databaseReferencia();

    public Movimentacao() {
    }

    public void salvarDados(String id, double valor, String categoria, String descricao, String tipo){
        this.id = id;
        this.valor = valor;
        this.categoria = categoria;
        this.descricao = descricao;
        this.tipo = tipo;

        referencia.child("movimentacoes")
            .child(id)//TODO: CRIAR ID USUÁRIO PARA INSERIR AQUI
            .child("052019")//TODO: CRIAR FORMATO DE DATA PADRÃO PARA INSERIR AQUI
            .setValue(this);
    }

    @Exclude
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public double getValor() {
        return valor;
    }

    public void setValor(double valor) {
        this.valor = valor;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }
}
