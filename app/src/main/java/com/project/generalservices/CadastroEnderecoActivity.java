package com.project.generalservices;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.project.generalservices.dao.Usuario;
import com.project.generalservices.helper.DBHelper;

import java.lang.reflect.Array;

public class CadastroEnderecoActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro_endereco);

        //Iniciando DB
        DBHelper mydb = new DBHelper(CadastroEnderecoActivity.this);

        RadioGroup group = (RadioGroup) findViewById(R.id.RGroup);
        group.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                RadioButton button = (RadioButton) group.findViewById(checkedId);
                String resposta = button.getText().toString();

                //Setando inputs da tela
                Button btnContinuar = findViewById(R.id.btnContinuar);
                EditText etCep = findViewById(R.id.etCep);
                EditText etCidade = findViewById(R.id.etCidade);
                EditText etBairro = findViewById(R.id.etBairro);
                EditText etRua = findViewById(R.id.etRua);
                EditText etNumero = findViewById(R.id.etNumero);

                //Dados vindos da primeira parte do cadastro
                Bundle extras = getIntent().getExtras();
                String nome = extras.getString("Nome");
                String email = extras.getString("Email");
                String senha = extras.getString("Senha");

                //Dados dos inputs
                String cep = etCep.getText().toString();
                String cidade = etCidade.getText().toString();
                String bairro = etBairro.getText().toString();
                String rua = etRua.getText().toString();
                String numero = etNumero.getText().toString();

                if(resposta.equals("Fornecedor")){
                    btnContinuar.setText("Continuar");
                } else if (resposta.equals("Cliente")) {
                    btnContinuar.setText("Cadastrar");
                }
                btnContinuar.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        if(etCep.getText().toString().isEmpty() || etCidade.getText().toString().isEmpty() || etBairro.getText().toString().isEmpty() || etRua.getText().toString().isEmpty() || etNumero.getText().toString().isEmpty()) {
                            Toast.makeText(CadastroEnderecoActivity.this, "É necessário preencher todos os campos!", Toast.LENGTH_LONG).show();

                        } else {
                            if (resposta.equals("Fornecedor")) {
                                Intent intentCadastroServico = new Intent( CadastroEnderecoActivity.this, CadastroServicoActivity.class);
                                intentCadastroServico.putExtra("Cep", etCep.getText().toString());
                                intentCadastroServico.putExtra("Bairro", etBairro.getText().toString());
                                intentCadastroServico.putExtra("Cidade",etCidade.getText().toString());
                                intentCadastroServico.putExtra("Rua", etRua.getText().toString());
                                intentCadastroServico.putExtra("Numero", etNumero.getText().toString());
                                intentCadastroServico.putExtra("Nome",nome);
                                intentCadastroServico.putExtra("Email",email);
                                intentCadastroServico.putExtra("Senha",senha);
                                startActivity(intentCadastroServico);

                            } else if (resposta.equals("Cliente")) {

                                boolean usuario = mydb.cadastrarUsuario(nome,email,senha,1);

                                if (usuario != false) {
                                    Integer usuarioId = mydb.getUsuarioIdByEmail(email);
                                    boolean insertEndereco = mydb.cadastrarEndereco (usuarioId,cep,cidade,bairro,rua,numero);
                                    if (insertEndereco) {
                                        Intent intentTelaCliente = new Intent(CadastroEnderecoActivity.this, TelaClienteActivity.class);
                                        intentTelaCliente.putExtra("UsuarioId",usuarioId);
                                        startActivity(intentTelaCliente);
                                    }
                                }

                            }
                        }
                    }
                });
            }
        });
    }
}