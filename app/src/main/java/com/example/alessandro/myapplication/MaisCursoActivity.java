package com.example.alessandro.myapplication;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextWatcher;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.alessandro.myapplication.dao.CursoDAO;
import com.example.alessandro.myapplication.modelo.Curso;

public class MaisCursoActivity extends AppCompatActivity {

    private MaisCursoHelper helper;
    private Spinner turnoCurso;
    private TextWatcher inicioMask, fimMask;
    private EditText inicioCurso, fimCurso;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mais_curso);

        inicioCurso = findViewById(R.id.curso_inicio);

        inicioMask = Mask.insert("##/##/####", inicioCurso);
        inicioCurso.addTextChangedListener(inicioMask);

        fimCurso = findViewById(R.id.curso_fim);

        fimMask = Mask.insert("##/##/####", fimCurso);
        fimCurso.addTextChangedListener(fimMask);

        helper = new MaisCursoHelper(this, this);

        turnoCurso = (Spinner) findViewById(R.id.sp_curso_turno);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.turnos, android.R.layout.simple_spinner_item);
        turnoCurso.setAdapter(adapter);

        Intent intent = getIntent();
        Curso curso = (Curso) intent.getSerializableExtra("curso");

        if(curso != null){
            helper.preencheCurso(curso);
        }

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_formulario, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.menu_formulario_ok:
                Curso curso = helper.getCurso();
                CursoDAO dao = new CursoDAO(this);

                if(curso.getId() != 0){
                    dao.altera(curso);
                }else{
                    dao.insere(curso);
                }


                dao.close();
                Toast.makeText(MaisCursoActivity.this, "Curso " + curso.getNomeCurso() + " salvo!",
                        Toast.LENGTH_SHORT).show();
                finish();
                break;
        }
        return super.onOptionsItemSelected(item);
    }

}