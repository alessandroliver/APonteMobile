package com.example.alessandro.apontemobile;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextWatcher;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

public class MaisAlunoActivity extends AppCompatActivity {

    private Button maisCurso, cadastrarAluno;
    private EditText celularAluno, nascimentoAluno, alturaAluno, cepAluno;
    private TextWatcher celularMark, nascimentoMask, alturaMask, cepMask;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mais_aluno);

        //maisCurso = findViewById(R.id.button_cursos);

        cadastrarAluno = findViewById(R.id.button_cadastro_aluno);

        Spinner spStates = (Spinner) findViewById(R.id.sp_state);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.states, android.R.layout.simple_spinner_item);
        spStates.setAdapter(adapter);

        Spinner spStates1 = (Spinner) findViewById(R.id.sp_farda);
        ArrayAdapter<CharSequence> adapter1 = ArrayAdapter.createFromResource(this,
                R.array.camisas, android.R.layout.simple_spinner_item);
        spStates1.setAdapter(adapter1);

        celularAluno = findViewById(R.id.formulario_celular);
        nascimentoAluno = findViewById(R.id.formulario_nascimento);
        alturaAluno = findViewById(R.id.formulario_altura);
        cepAluno = findViewById(R.id.formulario_cep);

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
