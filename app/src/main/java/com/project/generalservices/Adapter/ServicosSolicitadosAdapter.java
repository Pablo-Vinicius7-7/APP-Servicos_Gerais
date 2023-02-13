package com.project.generalservices.Adapter;

import static android.graphics.Color.GREEN;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.recyclerview.widget.RecyclerView;

import com.project.generalservices.LoginActivity;
import com.project.generalservices.R;
import com.project.generalservices.dao.ServicoSolicitado;
import com.project.generalservices.helper.DBHelper;
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
        ServicoSolicitado servicoSolicitado = servicoSolicitados.get(position);

        DBHelper myDB = new DBHelper(holder.itemView.getContext());
        long id = servicoSolicitados.get(position).getId();

        holder.btnRecusar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                boolean removerServico = myDB.removerServicoSolicitado(id);
                if(removerServico) {
                    removerServicoSolicitado(servicoSolicitado);
                    Toast.makeText(holder.itemView.getContext(), "Servico recusado", Toast.LENGTH_LONG).show();
                }
            }
        });
        holder.btnAceitar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                holder.btnAceitar.setText("Servico aceito");
                holder.btnAceitar.setBackgroundColor(GREEN);
                holder.btnRecusar.setText("Cancelar");
            }
        });
    }

    public void removerServicoSolicitado(ServicoSolicitado servicoSolicitado){
        int position = servicoSolicitados.indexOf(servicoSolicitado);
        servicoSolicitados.remove(position);
        notifyItemRemoved(position);
    }

    @Override
    public int getItemCount() {
        return servicoSolicitados != null ? servicoSolicitados.size() : 0;
    }
}
