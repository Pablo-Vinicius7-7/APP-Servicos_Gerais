package com.project.generalservices.Adapter;

import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.recyclerview.widget.RecyclerView;

import com.project.generalservices.R;

public class adapterFornecedorServico extends RecyclerView.Adapter<adapterFornecedorServico.MyViewHolder> {

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View itemLista = LayoutInflater.from(parent.getContext()).inflate(R.layout.servico_aceitar_recusar, parent, false);

        return new MyViewHolder(itemLista);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.nomeServico.setText("Encanador");
        holder.nomeCliente.setText("Fulano de tal");
        holder.endereco.setText("Rua Vasco da Gama, nº 10");
        holder.aceitar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder msgBox = new AlertDialog.Builder(holder.itemView.getContext());
                msgBox.setTitle("Confimar");
                msgBox.setMessage("Deseja realmente confirmar esse serviço?");
                msgBox.setPositiveButton("Sim", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        //Colocar os comandos ao confirmar a solicitação
                        Toast.makeText(holder.itemView.getContext(), "Clicou em sim", Toast.LENGTH_SHORT);

                    }
                });
                msgBox.setNegativeButton("Não", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Toast.makeText(holder.itemView.getContext(), "Clicou em Não", Toast.LENGTH_SHORT);
                    }
                });
                msgBox.show();
            }
        });

    }

    @Override
    public int getItemCount() {
        return 5;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{

        TextView nomeServico;
        TextView nomeCliente;
        TextView endereco;
        Button aceitar;
        Button recusar;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            nomeServico = itemView.findViewById(R.id.nomeServico);
            nomeCliente = itemView.findViewById(R.id.nomeCliente);
            endereco = itemView.findViewById(R.id.tvEndereco);
            aceitar = itemView.findViewById(R.id.btnAceitar);
            recusar = itemView.findViewById(R.id.btnRecusar);
        }
    }
}
