package com.project.generalservices.Adapter;

import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.recyclerview.widget.RecyclerView;

import com.project.generalservices.R;
import com.project.generalservices.dao.Servico;
import com.project.generalservices.holder.ServicoHolder;

import java.util.List;

public class ServicoAdapter extends RecyclerView.Adapter<ServicoHolder> {
    private  final List<Servico> servicos;


    public ServicoAdapter(List<Servico> servicos) {
        this.servicos = servicos;
    }

    @Override
    public ServicoHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ServicoHolder(LayoutInflater.from(parent.getContext())
                .inflate(R.layout.servico_item, parent, false));
    }

    @Override
    public void onBindViewHolder(ServicoHolder holder, int position) {
        holder.nomeServico.setText(servicos.get(position).getNome());
        holder.descricao.setText(servicos.get(position).getDescricao());
        holder.valor.setText(servicos.get(position).getValor());
        holder.btnSolicitar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder msgBox = new AlertDialog.Builder(holder.itemView.getContext());
                msgBox.setTitle("Solicitar");
                msgBox.setMessage("Deseja realmente solicitar esse serviço?");
                msgBox.setPositiveButton("Sim", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        //Colocar os comandos ao confirmar a solicitação

                    }
                });
                msgBox.setNegativeButton("Não", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });
                msgBox.show();
            }
        });
    }

    @Override
    public int getItemCount() {
        return servicos != null ? servicos.size() : 0;
    }

}
