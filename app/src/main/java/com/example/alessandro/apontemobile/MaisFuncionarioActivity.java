package com.example.alessandro.apontemobile;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

public class MaisFuncionarioActivity extends AppCompatActivity {

    private Button cadastrarFuncionario;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mais_funcionario);

        cadastrarFuncionario = findViewById(R.id.button_cadastro_funcionario);

        Spinner spStatesFuncionario = (Spinner) findViewById(R.id.funcionario_sp_state);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.states, android.R.layout.simple_spinner_item);
        spStatesFuncionario.setAdapter(adapter);

        Spinner spStatesFuncionario1 = (Spinner) findViewById(R.id.funcionario_sp_camisa);
        ArrayAdapter<CharSequence> adapter1 = ArrayAdapter.createFromResource(this,
                R.array.camisas, android.R.layout.simple_spinner_item);
        spStatesFuncionario1.setAdapter(adapter1);

        cadastrarFuncionario.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                alert("Funcionário cadastrado com sucesso!");
                Intent intent = new Intent(MaisFuncionarioActivity.this, InicioActivity.class);
                startActivity(intent);
            }
        });

    }

    private void alert(String s){
        Toast.makeText(this,s,Toast.LENGTH_LONG).show();
    }

}