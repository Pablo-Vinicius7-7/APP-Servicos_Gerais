package com.project.generalservices;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

public class CadastroActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro);

        EditText etNome = findViewById(R.id.etNome);
        EditText etEmail = findViewById(R.id.etEmail);
        EditText etSenha = findViewById(R.id.etSenha);
        Button btnContinuar = findViewById(R.id.btnContinuar);

        btnContinuar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(etNome.getText().toString().isEmpty() || etEmail.getText().toString().isEmpty() || etSenha.getText().toString().isEmpty()) {
                    Toast.makeText(CadastroActivity.this, "É necessário preencher todos os campos!", Toast.LENGTH_LONG).show();

                } else {
                    Intent telaEndereco = new Intent(CadastroActivity.this,CadastroEnderecoActivity.class);
                    telaEndereco.putExtra("Nome",etNome.getText().toString());
                    telaEndereco.putExtra("Email", etEmail.getText().toString());
                    telaEndereco.putExtra("Senha",etSenha.getText().toString());
                    startActivity(telaEndereco);
                }
            }
        });
    }
}