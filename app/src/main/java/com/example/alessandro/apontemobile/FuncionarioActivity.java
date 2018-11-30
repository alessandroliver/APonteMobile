package com.example.alessandro.apontemobile;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import com.example.alessandro.apontemobile.modelo.Funcionario;

import java.text.ParseException;
import java.util.List;

public class FuncionarioActivity extends AppCompatActivity {

    private Button maisFuncionario;
    private List<Funcionario> listaFuncionario = null;
    public static String acao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_funcionario);

        try {
            listaFuncionario = new FuncionarioDBController(this).getAllFuncionario();
        } catch (ParseException e) {
            e.printStackTrace();
        }

        Bundle extras = getIntent().getExtras();

        if (extras != null) {
            acao = extras.getString("acaoExtra");
        }

        ListView listafuncionario = findViewById(R.id.lista_funcionarios);
        ArrayAdapter<Funcionario> adapter = new ArrayAdapter<Funcionario>(this, android.R.layout.simple_list_item_1,
                listaFuncionario);
        listafuncionario.setAdapter(adapter);

        maisFuncionario = findViewById(R.id.add_funcionario);

        maisFuncionario.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(FuncionarioActivity.this, MaisFuncionarioActivity.class);
                startActivity(intent);
            }
        });

    }
}
