package com.dev.morganizze.Model;

import com.dev.morganizze.Helper.AutenticacaoFirebase;
import com.dev.morganizze.Helper.DataFormatar;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.Exclude;

public class Movimentacao{
    private double valor;
    private String id, categoria, descricao, data ,tipo;
    private DatabaseReference referencia = AutenticacaoFirebase.databaseReferencia();

    public Movimentacao() {
    }

    public void salvarDados(){
        DatabaseReference movimentacao = referencia
                .child("movimentacoes")
                .child(id)
                .child(DataFormatar.dataPostagem(data))
                .push();
        movimentacao.setValue(this);
    }

    @Exclude
    public String getId() {
        return id;
    }
    @Exclude
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

    @Exclude
    public String getData() {
        return data;
    }
    @Exclude
    public void setData(String data) {
        this.data = data;
    }
}
