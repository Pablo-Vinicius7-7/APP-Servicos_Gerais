package com.project.generalservices;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import com.project.generalservices.Adapter.ServicoAdapter;
import com.project.generalservices.helper.DBHelper;

public class TelaClienteActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tela_cliente);

        Bundle extras = getIntent().getExtras();
        Integer usuarioId = extras.getInt("UsuarioId");

        DBHelper myDB = new DBHelper(TelaClienteActivity.this);
        String cep = myDB.getCep(usuarioId);
        configurarRecycler(cep, usuarioId);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_principal_cliente,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        switch (item.getItemId()) {

            case R.id.btnEditar:

                Bundle extras = getIntent().getExtras();
                Integer usuarioId = extras.getInt("UsuarioId");

                DBHelper myDB = new DBHelper(this);

                //Dados para formulario de edicao
                Integer id = myDB.getEnderecoIdByUsuarioId(usuarioId);
                String cep = myDB.getCep(usuarioId);
                String cidade = myDB.getCidade(usuarioId);
                String bairro = myDB.getBairro(usuarioId);
                String rua = myDB.getRua(usuarioId);
                String numero = myDB.getNumero(usuarioId);

                Intent telaEndereco = new Intent(TelaClienteActivity.this,EditarEnderecoActivity.class);
                telaEndereco.putExtra("Id",id);
                telaEndereco.putExtra("Cep",cep);
                telaEndereco.putExtra("Cidade",cidade);
                telaEndereco.putExtra("Bairro", bairro);
                telaEndereco.putExtra("Rua",rua);
                telaEndereco.putExtra("Numero", numero);
                startActivity(telaEndereco);
                return true;
            case R.id.btnSair:
                finishAffinity();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void configurarRecycler(String cep, Integer usuarioId) {
        // Configurando o gerenciador de layout para ser uma lista.
        RecyclerView recyclerView = findViewById(R.id.recyclerViewServicos);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        // Adiciona o adapter que irá anexar os objetos à lista.
        DBHelper myDB = new DBHelper(this);
        ServicoAdapter adapter = new ServicoAdapter(myDB.getServicosByCep(cep), usuarioId);
        recyclerView.setAdapter(adapter);
        recyclerView.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));
    }

}