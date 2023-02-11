package com.project.generalservices.holder;

import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.project.generalservices.R;

public class ServicoHolder extends RecyclerView.ViewHolder {

    public TextView nomeServico;
    public TextView descricao;
    public TextView valor;
    public Button btnSolicitar;

    public ServicoHolder(View itemView) {
        super(itemView);
        nomeServico = (TextView)  itemView.findViewById(R.id.nomeServico);
        descricao = (TextView) itemView.findViewById(R.id.descricaoServico);
        valor = (TextView) itemView.findViewById(R.id.precoServico);
        btnSolicitar = (Button) itemView.findViewById(R.id.btnSolicitar);
    }
}
