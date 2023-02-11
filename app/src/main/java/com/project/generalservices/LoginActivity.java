package com.project.generalservices;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.project.generalservices.helper.DBHelper;

public class LoginActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_login);
        DBHelper mydb = new DBHelper(LoginActivity.this);

        EditText etEmail = findViewById(R.id.etEmail);
        EditText etSenha = findViewById(R.id.etSenha);

        Button btnCadastrar = findViewById(R.id.btnCadastrar);
        Button btnEntrar = findViewById(R.id.btnEntrar);

        btnCadastrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentCadastro = new Intent( LoginActivity.this, CadastroActivity.class);
                startActivity(intentCadastro);
            }
        });

        btnEntrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //Validar campos
                if(etEmail.getText().toString().isEmpty()) {
                    etEmail.setError("Campo obrigatório!");
                }else if(etSenha.getText().toString().isEmpty()) {
                    etSenha.setError("Campo obrigatório!");
                }

                Boolean login = mydb.validarLoginSenha(etEmail.getText().toString(),
                        etSenha.getText().toString());

                if(login){

                    Integer idUsuario = mydb.getUsuarioIdByEmail(etEmail.getText().toString());
                    Integer tipoUsuario = mydb.getTipoUsuarioByEmail(etEmail.getText().toString());
                    Toast.makeText(LoginActivity.this, "Login com sucesso", Toast.LENGTH_LONG).show();

                        if (tipoUsuario == 0){
                            Intent telaFornecedor = new Intent(LoginActivity.this,TelaFornecedorActivity.class);
                            telaFornecedor.putExtra("UsuarioId", idUsuario);
                            startActivity(telaFornecedor);
                        } else if (tipoUsuario == 1) {
                            Intent telaCliente = new Intent(LoginActivity.this, TelaClienteActivity.class);
                            telaCliente.putExtra("UsuarioId", idUsuario);
                            startActivity(telaCliente);
                        }
                }else {
                    Toast.makeText(LoginActivity.this, "Login incorreto", Toast.LENGTH_LONG).show();
                }

            }
        });
    }
}