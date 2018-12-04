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
import com.example.alessandro.myapplication.modelo.Aluno;
import com.example.alessandro.myapplication.dao.AlunoDAO;

import java.lang.reflect.Array;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class MaisAlunoActivity extends AppCompatActivity {

    private MaisAlunoHelper helper;
    private Spinner ufAluno, fardaAluno, cursoAluno1, cursoAluno2;
    private TextWatcher nascimentoMask;
    private EditText nascimentoAluno;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mais_aluno);

        nascimentoAluno = findViewById(R.id.formulario_nascimento);

        nascimentoMask = Mask.insert("##/##/####", nascimentoAluno);
        nascimentoAluno.addTextChangedListener(nascimentoMask);

        ufAluno = (Spinner) findViewById(R.id.sp_state);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.states, android.R.layout.simple_spinner_item);
        ufAluno.setAdapter(adapter);

        fardaAluno = (Spinner) findViewById(R.id.sp_farda);
        ArrayAdapter<CharSequence> adapter1 = ArrayAdapter.createFromResource(this,
                R.array.camisas, android.R.layout.simple_spinner_item);
        fardaAluno.setAdapter(adapter1);

        cursoAluno1 = (Spinner) findViewById(R.id.sp_aluno_curso1);
        cursoAluno2 = (Spinner) findViewById(R.id.sp_aluno_curso2);

        CursoDAO cursoDAO = new CursoDAO(this);
        ArrayList<String> cursosManha = new ArrayList<String>();
        ArrayList<String> cursosTarde = new ArrayList<String>();
        List<Curso> cursos = cursoDAO.buscaCursos();
        for (Curso curso:cursos) {
            if (curso.getTurno().equals("Manhã")){
                cursosManha.add(curso.getNomeCurso());
            }else{
                cursosTarde.add(curso.getNomeCurso());
            }

        }

        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, cursosManha);
        ArrayAdapter<String> spinnerArrayAdapter = arrayAdapter;
        spinnerArrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_item);
        ArrayAdapter<String> arrayAdapter1 = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, cursosTarde);
        ArrayAdapter<String> spinnerArrayAdapter1 = arrayAdapter1;
        spinnerArrayAdapter1.setDropDownViewResource(android.R.layout.simple_spinner_item);
        cursoAluno1.setAdapter(spinnerArrayAdapter);
        cursoAluno2.setAdapter(spinnerArrayAdapter1);

        helper = new MaisAlunoHelper(this, this);

        Intent intent = getIntent();
        Aluno aluno = (Aluno) intent.getSerializableExtra("aluno");

        if(aluno != null){
            helper.preencheAluno(aluno);
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
                Aluno aluno = helper.getAluno();
                AlunoDAO dao = new AlunoDAO(this);

                if(aluno.getId() != 0){
                    dao.altera(aluno);
                }else{
                    dao.insere(aluno);
                }


                dao.close();
                Toast.makeText(MaisAlunoActivity.this, "Aluno " + aluno.getNome() + " salvo!",
                        Toast.LENGTH_SHORT).show();
                finish();
                break;
        }
        return super.onOptionsItemSelected(item);
    }

}