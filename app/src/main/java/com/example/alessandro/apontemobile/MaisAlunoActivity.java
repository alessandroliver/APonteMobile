package com.example.alessandro.apontemobile;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextWatcher;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.alessandro.apontemobile.modelo.Aluno;

public class MaisAlunoActivity extends AppCompatActivity {

    private Button maisCurso, cadastrarAluno;
    private EditText nomeAluno, celularAluno, nascimentoAluno, alturaAluno, naturalidadeAluno, responsavelAluno,
            logradouroAluno, numeroAluno, bairroAluno, cepAluno, matriculaAluno, escolaAluno, serieAluno;
    private TextWatcher celularMark, nascimentoMask, alturaMask, cepMask;
    private RadioButton  radioMAluno, radioFAluno;
    private Spinner estadoAluno, cursoAluno, fardaAluno;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mais_aluno);

        //maisCurso = findViewById(R.id.button_cursos);

        cadastrarAluno = findViewById(R.id.button_cadastro_aluno);

        estadoAluno = (Spinner) findViewById(R.id.sp_state);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.states, android.R.layout.simple_spinner_item);
        estadoAluno.setAdapter(adapter);
        cursoAluno = (Spinner) findViewById(R.id.sp_aluno_curso);
        fardaAluno = (Spinner) findViewById(R.id.sp_farda);
        ArrayAdapter<CharSequence> adapter1 = ArrayAdapter.createFromResource(this,
                R.array.camisas, android.R.layout.simple_spinner_item);
        fardaAluno.setAdapter(adapter1);

        radioMAluno = findViewById(R.id.radioM);
        radioFAluno = findViewById(R.id.radioF);

        nomeAluno = findViewById(R.id.formulario_nome);
        celularAluno = findViewById(R.id.formulario_celular);
        nascimentoAluno = findViewById(R.id.formulario_nascimento);
        alturaAluno = findViewById(R.id.formulario_altura);
        naturalidadeAluno = findViewById(R.id.formulario_naturalidade);
        responsavelAluno = findViewById(R.id.formulario_responsavel);
        logradouroAluno = findViewById(R.id.formulario_logradouro);
        numeroAluno = findViewById(R.id.formulario_numero);
        bairroAluno = findViewById(R.id.formulario_bairro);
        cepAluno = findViewById(R.id.formulario_cep);
        matriculaAluno = findViewById(R.id.formulario_matricula);
        escolaAluno = findViewById(R.id.formulario_escola);
        serieAluno = findViewById(R.id.formulario_serie);

        celularMark = Mask.insert("(##) #####-####", celularAluno);
        celularAluno.addTextChangedListener(celularMark);

        nascimentoMask = Mask.insert("##/##/####", nascimentoAluno);
        nascimentoAluno.addTextChangedListener(nascimentoMask);

        alturaMask = Mask.insert("#.##", alturaAluno);
        alturaAluno.addTextChangedListener(alturaMask);

        cepMask = Mask.insert("##.###-###", cepAluno);
        cepAluno.addTextChangedListener(cepMask);

        /*maisCurso.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MaisAlunoActivity.this, PopActivity.class);
                startActivity(intent);
            }
        });*/

        cadastrarAluno.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Aluno aluno = new Aluno();
                alert("Aluno cadastrado com sucesso!");
                Intent intent = new Intent(MaisAlunoActivity.this, InicioActivity.class);
                startActivity(intent);
            }
        });

    }

    private void alert(String s){
        Toast.makeText(this,s,Toast.LENGTH_LONG).show();
    }

}
