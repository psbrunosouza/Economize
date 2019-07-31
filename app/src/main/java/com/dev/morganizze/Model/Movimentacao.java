package com.dev.morganizze.Model;

import com.dev.morganizze.Helper.AutenticacaoFirebase;
import com.google.firebase.database.DatabaseReference;

public class Movimentacao{
    private double valor;
    private String categoria, descricao, tipo;
    private DatabaseReference referencia = AutenticacaoFirebase.databaseReferencia();

    public Movimentacao() {
    }

    public void salvarDados(double valor, String categoria, String descricao, String tipo){
        this.valor = valor;
        this.categoria = categoria;
        this.descricao = descricao;
        this.tipo = tipo;

        referencia.child("receitas")
            .child("idUsuario")//TODO: CRIAR ID USUÁRIO PARA INSERIR AQUI
            .child("052019")//TODO: CRIAR FORMATO DE DATA PADRÃO PARA INSERIR AQUI
            .setValue(this);
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
