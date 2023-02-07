package com.project.generalservices;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.project.generalservices.Adapter.adapterServico;

public class TelaClienteActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tela_cliente);


        RecyclerView listaServicos = findViewById(R.id.recyclerViewServicos);
        listaServicos.setHasFixedSize(true);

        adapterServico adapter = new adapterServico();

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
        listaServicos.setLayoutManager(layoutManager);
        listaServicos.setAdapter(adapter);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.menu_principal,menu);
        return super.onCreateOptionsMenu(menu);
    }
}