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
import com.project.generalservices.ServicoItemClienteActivity;

public class adapterServico extends RecyclerView.Adapter<adapterServico.MyViewHolder> {

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View itemLista = LayoutInflater.from(parent.getContext()).inflate(R.layout.servico_item, parent, false);

        return new MyViewHolder(itemLista);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.nome.setText("Mecânico");
        holder.descricao.setText("O mecânico irá consertar seu carro. Ele analisará o erro e após isso consertar");
        holder.preco.setText("220.00");
        holder.solicitar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder msgBox = new AlertDialog.Builder(holder.itemView.getContext());
                msgBox.setTitle("Solicitar");
                msgBox.setMessage("Deseja realmente solicitar esse serviço?");
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

        TextView nome;
        TextView descricao;
        TextView preco;
        Button solicitar;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            nome = itemView.findViewById(R.id.nomeServico);
            descricao = itemView.findViewById(R.id.descricaoServico);
            preco = itemView.findViewById(R.id.precoServico);
            solicitar = itemView.findViewById(R.id.btnSolicitar);

        }
    }
}
