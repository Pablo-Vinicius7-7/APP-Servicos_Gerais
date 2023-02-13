package com.project.generalservices.Adapter;

import static android.graphics.Color.GREEN;

import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.recyclerview.widget.RecyclerView;

import com.project.generalservices.LoginActivity;
import com.project.generalservices.R;
import com.project.generalservices.dao.Servico;
import com.project.generalservices.dao.Usuario;
import com.project.generalservices.helper.DBHelper;
import com.project.generalservices.holder.ServicoHolder;

import java.util.List;

public class ServicoAdapter extends RecyclerView.Adapter<ServicoHolder> {
    private  final List<Servico> servicos;
    private Integer usuarioId;


    public ServicoAdapter(List<Servico> servicos, Integer usuarioId) {
        this.servicos = servicos;
        this.usuarioId = usuarioId;
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
        DBHelper myDB = new DBHelper(holder.itemView.getContext());
        Integer fornecedorId = myDB.getServicoIdByNomeServico(servicos.get(position).getNome());
        String nomeServico = servicos.get(position).getNome();

        holder.btnSolicitar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder msgBox = new AlertDialog.Builder(holder.itemView.getContext());
                msgBox.setTitle("Solicitar");
                msgBox.setMessage("Deseja realmente solicitar esse serviço?");
                msgBox.setPositiveButton("Sim", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        String nomeCLiente = myDB.getNomeUsuarioById(usuarioId);
                        String bairro = myDB.getBairro(usuarioId);
                        String rua = myDB.getRua(usuarioId);
                        String numero = myDB.getNumero(usuarioId);
                        String endereco = "Bairro: " + bairro + "\n Rua: " + rua + "\n Número: " + numero;

                        boolean servicoSolicitado = myDB.cadastrarServicoSolicitado(usuarioId,fornecedorId,nomeCLiente,nomeServico,endereco);
                            if (servicoSolicitado) {
                                Toast.makeText(holder.itemView.getContext(), "Servico solicitado com sucesso", Toast.LENGTH_LONG).show();
                                holder.btnSolicitar.setText("Solicitado");
                                holder.btnSolicitar.setBackgroundColor(GREEN);
                            }
                    }
                });
                msgBox.setNegativeButton("Não", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int id) {

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
