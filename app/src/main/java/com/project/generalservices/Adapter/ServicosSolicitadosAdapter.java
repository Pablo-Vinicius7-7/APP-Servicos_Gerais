package com.project.generalservices.Adapter;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.recyclerview.widget.RecyclerView;

import com.project.generalservices.R;
import com.project.generalservices.dao.ServicoSolicitado;
import com.project.generalservices.holder.ServicosSolicitadosHolder;

import java.util.List;

public class ServicosSolicitadosAdapter extends RecyclerView.Adapter<ServicosSolicitadosHolder> {

    private final List<ServicoSolicitado> servicoSolicitados;

    public ServicosSolicitadosAdapter (List<ServicoSolicitado> servicoSolicitados) {
        this.servicoSolicitados = servicoSolicitados;
    }

    @Override
    public ServicosSolicitadosHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ServicosSolicitadosHolder(LayoutInflater.from(parent.getContext())
                .inflate(R.layout.servico_aceitar_recusar, parent, false));
    }

    @Override
    public void onBindViewHolder(ServicosSolicitadosHolder holder, int position) {
        holder.nomeServico.setText(servicoSolicitados.get(position).getNome_servico());
        holder.nomeCliente.setText(servicoSolicitados.get(position).getNome_cliente());
        holder.endereco.setText(servicoSolicitados.get(position).getEndereco());
    }

    @Override
    public int getItemCount() {
        return servicoSolicitados != null ? servicoSolicitados.size() : 0;
    }
}
