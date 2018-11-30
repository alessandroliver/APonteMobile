package com.example.alessandro.apontemobile;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import com.example.alessandro.apontemobile.modelo.Curso;

import java.text.ParseException;
import java.util.List;

public class CursoActivity extends AppCompatActivity {

    private Button maisCurso;
    private List<Curso> listaCurso = null;
    public static String acao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_curso);

        try {
            listaCurso = new CursoDBController(this).getAllCurso();
        } catch (ParseException e) {
            e.printStackTrace();
        }

        Bundle extras = getIntent().getExtras();

        if (extras != null) {
            acao = extras.getString("acaoExtra");
        }

        ListView listacurso = findViewById(R.id.lista_materiais);
        ArrayAdapter<Curso> adapter = new ArrayAdapter<Curso>(this, android.R.layout.simple_list_item_1, listaCurso);
        listacurso.setAdapter(adapter);

        maisCurso = findViewById(R.id.add_curso);

        maisCurso.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(CursoActivity.this, MaisCursoActivity.class);
                startActivity(intent);
            }
        });

    }
}
