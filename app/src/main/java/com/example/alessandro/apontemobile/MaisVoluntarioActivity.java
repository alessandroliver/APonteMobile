package com.example.alessandro.apontemobile;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

public class MaisVoluntarioActivity extends AppCompatActivity {

    private Button cadastrarVoluntario;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mais_voluntario);

        cadastrarVoluntario = findViewById(R.id.button_cadastro_voluntario);

        Spinner spStatesVoluntario = (Spinner) findViewById(R.id.voluntario_sp_state);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.states, android.R.layout.simple_spinner_item);
        spStatesVoluntario.setAdapter(adapter);

        Spinner spStatesVoluntario1 = (Spinner) findViewById(R.id.voluntario_sp_camisa);
        ArrayAdapter<CharSequence> adapter1 = ArrayAdapter.createFromResource(this,
                R.array.camisas, android.R.layout.simple_spinner_item);
        spStatesVoluntario1.setAdapter(adapter1);

        cadastrarVoluntario.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                alert("Voluntário cadastrado com sucesso!");
                Intent intent = new Intent(MaisVoluntarioActivity.this, InicioActivity.class);
                startActivity(intent);
            }
        });

    }

    private void alert(String s){
        Toast.makeText(this,s,Toast.LENGTH_LONG).show();
    }

}
