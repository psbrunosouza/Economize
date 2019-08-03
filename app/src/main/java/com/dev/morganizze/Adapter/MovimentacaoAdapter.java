package com.dev.morganizze.Adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.dev.morganizze.Model.Movimentacao;
import com.dev.morganizze.R;

import java.util.ArrayList;
import java.util.List;

public class MovimentacaoAdapter extends RecyclerView.Adapter<MovimentacaoAdapter.mViewHolder> {

    private List<Movimentacao> listaMovimentacoes;
    private Context context;

    public MovimentacaoAdapter(List<Movimentacao> listaMovimentacoes, Context context) {
        this.listaMovimentacoes = listaMovimentacoes;
        this.context = context;
    }

    @NonNull
    @Override
    public mViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.movimentacao, viewGroup, false);
        return new mViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull mViewHolder mViewHolder, int i) {
        Movimentacao movimentacao = listaMovimentacoes.get(i);
        mViewHolder.valor.setText(String.format("R$ %s",movimentacao.getValor()));
        mViewHolder.descricao.setText(movimentacao.getDescricao());
        mViewHolder.categoria.setText(movimentacao.getCategoria());
        mViewHolder.valor.setTextColor(context.getResources().getColor(R.color.colorPrimary));

        if(movimentacao.getTipo().equals("d")){
            mViewHolder.valor.setText(String.format("R$ -%s", movimentacao.getValor()));
            mViewHolder.valor.setTextColor(context.getResources().getColor(R.color.colorAccent));
        }
    }

    @Override
    public int getItemCount() {
        return listaMovimentacoes.size();
    }

    public class mViewHolder extends RecyclerView.ViewHolder {

        private TextView valor, descricao, categoria;

        public mViewHolder(@NonNull View itemView) {
            super(itemView);

            valor = itemView.findViewById(R.id.txt_valor);
            descricao = itemView.findViewById(R.id.txt_descricao);
            categoria = itemView.findViewById(R.id.txt_categoria);
        }
    }
}
