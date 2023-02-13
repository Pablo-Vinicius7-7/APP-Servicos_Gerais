package com.project.generalservices.holder;

import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.project.generalservices.R;

public class ServicosSolicitadosHolder extends RecyclerView.ViewHolder {

    public TextView nomeServico;
    public TextView nomeCliente;
    public TextView endereco;
    public Button btnAceitar;
    public Button btnRecusar;

    public ServicosSolicitadosHolder(View itemView) {
        super(itemView);
        nomeServico = (TextView) itemView.findViewById(R.id.nomeServicoSolicitado);
        nomeCliente = (TextView) itemView.findViewById(R.id.nomeCliente);
        endereco = (TextView) itemView.findViewById(R.id.tvEnderecoSolicitado);
        btnAceitar = (Button) itemView.findViewById(R.id.btnAceitar);
        btnRecusar = (Button) itemView.findViewById(R.id.btnRecusar);
    }
}
