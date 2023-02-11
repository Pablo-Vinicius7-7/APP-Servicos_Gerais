package com.project.generalservices;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.project.generalservices.helper.DBHelper;

public class CadastroServicoActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro_servico);
        //Iniciando DB
        DBHelper mydb = new DBHelper(CadastroServicoActivity.this);

        Button btnCadastrar = findViewById(R.id.btnCadastrar);

        //Obtendo inputs da tela
        EditText etNomeServico = findViewById(R.id.etNomeServico);
        EditText etDescricao = findViewById(R.id.etDescricaoServico);
        EditText etValor = findViewById(R.id.etValorServico);

        //Dados vindos da parte de cadastro de endereco
        Bundle extras = getIntent().getExtras();
        String nome = extras.getString("Nome");
        String email = extras.getString("Email");
        String senha = extras.getString("Senha");
        String cep = extras.getString("Cep");
        String cidade = extras.getString("Cidade");
        String bairro = extras.getString("Bairro");
        String rua = extras.getString("Rua");
        String numero = extras.getString("Numero");

        //Dados dos inputs
        String nomeServico = etNomeServico.getText().toString();
        String descricao = etDescricao.getText().toString();
        String valor = etValor.getText().toString();

        btnCadastrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                boolean usuario = mydb.cadastrarUsuario(nome, email, senha, 0);

                if (usuario != false) {
                    Integer usuarioId = mydb.getUsuarioIdByEmail(email);
                    boolean insertEndereco = mydb.cadastrarEndereco(usuarioId, cep, cidade, bairro, rua, numero);
                    if (insertEndereco) {
                        boolean insertServico = mydb.cadastrarServico(usuarioId, etNomeServico.getText().toString(), etDescricao.getText().toString(), etValor.getText().toString());
                            if (insertServico) {
                                Intent telaFornecedor = new Intent(CadastroServicoActivity.this, TelaFornecedorActivity.class);
                                telaFornecedor.putExtra("UsuarioId", usuarioId);
                                startActivity(telaFornecedor);
                            }
                        }
                    }
                };
            });
        }
}