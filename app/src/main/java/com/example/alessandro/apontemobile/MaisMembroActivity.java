package com.example.alessandro.apontemobile;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

public class MaisMembroActivity extends AppCompatActivity {

    private Button maisGr, cadastrarMembro;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mais_membro);

        maisGr = findViewById(R.id.button_gr);

        cadastrarMembro = findViewById(R.id.button_cadastro_membro);

        Spinner spStatesMembro = (Spinner) findViewById(R.id.membro_sp_state);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.states, android.R.layout.simple_spinner_item);
        spStatesMembro.setAdapter(adapter);

        Spinner spStatesMembro1 = (Spinner) findViewById(R.id.membro_sp_colete);
        ArrayAdapter<CharSequence> adapter1 = ArrayAdapter.createFromResource(this,
                R.array.coletes, android.R.layout.simple_spinner_item);
        spStatesMembro1.setAdapter(adapter1);

        maisGr.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MaisMembroActivity.this, PopGrActivity.class);
                startActivity(intent);
            }
        });

        cadastrarMembro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                alert("Membro cadastrado com sucesso!");
                Intent intent = new Intent(MaisMembroActivity.this, InicioActivity.class);
                startActivity(intent);
            }
        });

    }

    private void alert(String s){
        Toast.makeText(this,s,Toast.LENGTH_LONG).show();
    }

}
