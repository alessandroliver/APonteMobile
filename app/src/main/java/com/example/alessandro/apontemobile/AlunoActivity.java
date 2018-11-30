package com.example.alessandro.apontemobile;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import com.example.alessandro.apontemobile.modelo.Aluno;

import java.text.ParseException;
import java.util.List;

public class AlunoActivity extends AppCompatActivity {

    private Button maisAluno;
    private List<Aluno> listaAluno = null;
    public static String acao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_aluno);

        try {
            listaAluno = new AlunoDBController(this).getAllAluno();
        } catch (ParseException e) {
            e.printStackTrace();
        }

        Bundle extras = getIntent().getExtras();

        if (extras != null) {
            acao = extras.getString("acaoExtra");
        }

        ListView listaaluno = findViewById(R.id.lista_alunos);
        ArrayAdapter<Aluno> adapter = new ArrayAdapter<Aluno>(this, android.R.layout.simple_list_item_1,
                listaAluno);
        listaaluno.setAdapter(adapter);

        maisAluno = findViewById(R.id.add_aluno);

        maisAluno.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AlunoActivity.this, MaisAlunoActivity.class);
                startActivity(intent);
            }
        });

    }
}
