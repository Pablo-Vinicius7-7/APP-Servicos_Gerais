package com.project.generalservices;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;

public class CadastroEnderecoActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro_endereco);

        RadioGroup group = (RadioGroup) findViewById(R.id.RGroup);
        group.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                RadioButton button = (RadioButton) group.findViewById(checkedId);
                String resposta = button.getText().toString();

                Button btnContinuar = findViewById(R.id.btnContinuar);

                if(resposta.equals("Fornecedor")){
                    btnContinuar.setText("Continuar");
                } else if (resposta.equals("Cliente")) {
                    btnContinuar.setText("Cadastrar");
                }
                btnContinuar.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (resposta.equals("Fornecedor")) {
                            Intent intentCadastroServico = new Intent( CadastroEnderecoActivity.this, CadastroServicoActivity.class);
                            startActivity(intentCadastroServico);

                        } else if (resposta.equals("Cliente")) {
                            Intent intentTelaCliente = new Intent(CadastroEnderecoActivity.this, TelaClienteActivity.class);
                            startActivity(intentTelaCliente);
                        }
                    }
                });
            }
        });
    }
}