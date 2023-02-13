package com.project.generalservices;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.project.generalservices.helper.DBHelper;

public class EditarEnderecoActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editar_endereco);

        DBHelper myDB = new DBHelper(this);
        Button btnEditarEndereco = findViewById(R.id.btnEditarEndereco);

        //Obtendo inputs da tela
        EditText etCep = findViewById(R.id.etCep);
        EditText etCidade = findViewById(R.id.etCidade);
        EditText etBairro = findViewById(R.id.etBairro);
        EditText etRua = findViewById(R.id.etRua);
        EditText etNumero = findViewById(R.id.etNumero);

        //Obter dados da outra tela
        Bundle extras = getIntent().getExtras();
        String cep = extras.getString("Cep");
        String cidade = extras.getString("Cidade");
        String bairro = extras.getString("Bairro");
        String rua = extras.getString("Rua");
        String numero = extras.getString("Numero");
        Integer id = extras.getInt("Id");

        etCep.setText(cep);
        etCidade.setText(cidade);
        etBairro.setText(bairro);
        etRua.setText(rua);
        etNumero.setText(numero);

        btnEditarEndereco.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(etCep.getText().toString().isEmpty() || etCidade.getText().toString().isEmpty() || etBairro.getText().toString().isEmpty() || etRua.getText().toString().isEmpty() || etNumero.getText().toString().isEmpty()) {
                    Toast.makeText(EditarEnderecoActivity.this, "É necessário preencher todos os campos!", Toast.LENGTH_LONG).show();

                } else {
                    boolean updateEndereco = myDB.editarEndereco(id,etCep.getText().toString(),etCidade.getText().toString(),
                            etBairro.getText().toString(),etRua.getText().toString(),etNumero.getText().toString());

                    if(updateEndereco) {
                        Toast.makeText(EditarEnderecoActivity.this, "Endereço atualizado com sucesso", Toast.LENGTH_LONG).show();
                    }
                }
            }
        });

    }
}