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
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.alessandro.apontemobile.modelo.Aluno;
import com.example.alessandro.apontemobile.modelo.Curso;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class MaisAlunoActivity extends AppCompatActivity {

    private Button maisCurso, cadastrarAluno;
    private EditText nomeAluno, celularAluno, nascimentoAluno, alturaAluno, naturalidadeAluno, responsavelAluno,
            logradouroAluno, numeroAluno, bairroAluno, cepAluno, cidadeAluno, matriculaAluno, escolaAluno, serieAluno;
    private TextWatcher celularMark, nascimentoMask, alturaMask, cepMask;
    private RadioGroup sexoAluno;
    private Spinner estadoAluno, cursoAluno, fardaAluno;
    String nome;
    String naturalidade;
    int celular;
    Date nascimento;
    String sexo;
    double altura;
    String logradouro;
    int numero;
    String bairro;
    String cep;
    String cidade;
    String uf;
    int id;
    ArrayList<Curso> curso;
    String matricula;
    String farda;
    String responsavel;
    String serie;
    String escola;

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
        //cursoAluno = (Spinner) findViewById(R.id.sp_aluno_curso);
        fardaAluno = (Spinner) findViewById(R.id.sp_farda);
        ArrayAdapter<CharSequence> adapter1 = ArrayAdapter.createFromResource(this,
                R.array.camisas, android.R.layout.simple_spinner_item);
        fardaAluno.setAdapter(adapter1);

        sexoAluno = findViewById(R.id.formulario_sexo);

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
        cidadeAluno = findViewById(R.id.formulario_cidade);
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
                nome = nomeAluno.getText().toString();
                naturalidade = naturalidadeAluno.getText().toString();
                celular = Integer.parseInt(celularAluno.getText().toString());
                nascimento = null;
                DateFormat formatter1 = new SimpleDateFormat("MM/dd/yyyy");
                try {
                    nascimento = (Date)formatter1.parse(nascimentoAluno.getText().toString());
                } catch (ParseException e) {
                    e.printStackTrace();
                }
                switch (sexoAluno.getCheckedRadioButtonId()) {
                    case R.id.radioM:
                        sexo = "masculino";
                        break;
                    case R.id.radioF:
                        sexo = "feminino";
                        break;
                }
                altura = Double.parseDouble(alturaAluno.getText().toString());
                logradouro = logradouroAluno.getText().toString();
                numero = Integer.parseInt(numeroAluno.getText().toString());
                bairro = bairroAluno.getText().toString();
                cep = cepAluno.getText().toString();
                cidade = cidadeAluno.getText().toString();
                uf = estadoAluno.getSelectedItem().toString();
                //curso
                matricula = matriculaAluno.getText().toString();
                farda = fardaAluno.getSelectedItem().toString();
                responsavel = responsavelAluno.getText().toString();
                serie = serieAluno.getText().toString();
                escola = escolaAluno.getText().toString();

                Aluno aluno = new Aluno(nome,naturalidade,celular,nascimento,sexo,altura,logradouro,numero,bairro,cep,
                        cidade,uf,/*curso*/,matricula,farda,responsavel,serie,escola);

                if (aluno.getNome().equals("") || aluno.getNaturalidade().equals("") || aluno.getCelular() == 0 ||
                        aluno.getNascimento().equals("") || aluno.getSexo().equals("") || aluno.getAltura() == 0 ||
                        aluno.getLogradouro().equals("") || aluno.getNumero() == 0 || aluno.getBairro().equals("") ||
                        aluno.getCep().equals("") || aluno.getCidade().equals("") || aluno.getUf().equals("") ||
                        aluno.getCurso().equals("") || aluno.getMatricula().equals("") || aluno.getFarda().equals("") ||
                        aluno.getResponsavel().equals("") || aluno.getSerie().equals("") || aluno.getEscola().equals("")){
                    alert("Preencha todos os campos.");
                } else {
                    AlunoDBController alunoDBController = new AlunoDBController(MaisAlunoActivity.this);

                    alunoDBController.insert(aluno);

                    alert("Aluno cadastrado com sucesso!");
                    Intent intent = new Intent(MaisAlunoActivity.this, InicioActivity.class);
                    startActivity(intent);
                }
            }
        });

    }

    private void alert(String s){
        Toast.makeText(this,s,Toast.LENGTH_LONG).show();
    }

}
