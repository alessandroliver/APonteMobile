package com.example.alessandro.apontemobile;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.alessandro.apontemobile.modelo.Curso;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class MaisCursoActivity extends AppCompatActivity {

    private Button cadastrarCurso;
    private EditText nomeCursoCurso, professorCurso, dataInicioCurso, dataFimCurso, diaCurso, salaCurso, pegarCurso,
            largarCurso, cargaHorariaCurso;
    private TextWatcher dataMask;
    String nomeCurso;
    Date inicio;
    Date fim;
    int carga_horaria;
    String professor;
    String dia;
    double pegar;
    double largar;
    String sala;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mais_curso);

        nomeCursoCurso = findViewById(R.id.curso_nome);
        professorCurso = findViewById(R.id.curso_professor);
        dataInicioCurso = findViewById(R.id.curso_inicio);
        dataFimCurso = findViewById(R.id.curso_fim);
        diaCurso = findViewById(R.id.curso_dia);
        salaCurso = findViewById(R.id.curso_sala);
        pegarCurso = findViewById(R.id.curso_pegar);
        largarCurso = findViewById(R.id.curso_largar);
        cargaHorariaCurso = findViewById(R.id.curso_carga_horaria);

        dataMask = Mask.insert("##/##/####", dataInicioCurso);
        dataInicioCurso.addTextChangedListener(dataMask);

        dataMask = Mask.insert("##/##/####", dataFimCurso);
        dataFimCurso.addTextChangedListener(dataMask);

        cadastrarCurso = findViewById(R.id.button_cadastro_curso);

        cadastrarCurso.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                nomeCurso = nomeCursoCurso.getText().toString();
                inicio = null;
                DateFormat formatter = new SimpleDateFormat("MM/dd/yyyy");
                try {
                    inicio = (Date)formatter.parse(dataInicioCurso.getText().toString());
                } catch (ParseException e) {
                    e.printStackTrace();
                }
                fim = null;
                DateFormat formatter1 = new SimpleDateFormat("MM/dd/yyyy");
                try {
                    fim = (Date)formatter1.parse(dataFimCurso.getText().toString());
                } catch (ParseException e) {
                    e.printStackTrace();
                }
                carga_horaria = Integer.parseInt(cargaHorariaCurso.getText().toString());
                professor = professorCurso.getText().toString();
                dia = diaCurso.getText().toString();
                pegar = Double.parseDouble(pegarCurso.getText().toString());
                largar = Double.parseDouble(largarCurso.getText().toString());
                sala = salaCurso.getText().toString();

                Curso curso = new Curso(nomeCurso,inicio,fim,carga_horaria,professor,dia,pegar,largar,sala);

                alert("Curso cadastrado com sucesso!");
                Intent intent = new Intent(MaisCursoActivity.this, InicioActivity.class);
                startActivity(intent);
            }
        });

    }

    private void alert(String s){
        Toast.makeText(this,s,Toast.LENGTH_LONG).show();
    }

}
