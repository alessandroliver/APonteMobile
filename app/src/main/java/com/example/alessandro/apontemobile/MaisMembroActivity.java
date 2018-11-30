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

import com.example.alessandro.apontemobile.modelo.Gr;
import com.example.alessandro.apontemobile.modelo.Membro;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class MaisMembroActivity extends AppCompatActivity {

    private Button maisGr, cadastrarMembro;
    private EditText nomeMembro, celularMembro, nascimentoMembro, alturaMembro, naturalidadeMembro, logradouroMembro,
            numeroMembro, bairroMembro, cepMembro, cidadeMembro, conversaoMembro, equipeMembro, cargoMembro, tempoMembro;
    private TextWatcher celularMark, nascimentoMask, alturaMask, cepMask;
    private RadioGroup sexoMembro;
    private Spinner ufMembro, coleteMembro, grMembro;
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
    Date data_conversao;
    String equipe;
    String tempo_ponte;
    String cargo;
    Gr gr;
    String colete;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mais_membro);

        cadastrarMembro = findViewById(R.id.button_cadastro_membro);

        sexoMembro = findViewById(R.id.membro_sexo);

        ufMembro = (Spinner) findViewById(R.id.membro_sp_state);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.states, android.R.layout.simple_spinner_item);
        ufMembro.setAdapter(adapter);

        coleteMembro = (Spinner) findViewById(R.id.membro_sp_colete);
        ArrayAdapter<CharSequence> adapter1 = ArrayAdapter.createFromResource(this,
                R.array.coletes, android.R.layout.simple_spinner_item);
        coleteMembro.setAdapter(adapter1);

        grMembro = (Spinner) findViewById(R.id.sp_membro_gr);

        nomeMembro = findViewById(R.id.membro_nome);
        celularMembro = findViewById(R.id.membro_celular);
        nascimentoMembro = findViewById(R.id.membro_nascimento);
        alturaMembro = findViewById(R.id.membro_altura);
        naturalidadeMembro = findViewById(R.id.membro_naturalidade);
        logradouroMembro = findViewById(R.id.membro_logradouro);
        numeroMembro = findViewById(R.id.membro_numero);
        bairroMembro = findViewById(R.id.membro_bairro);
        cepMembro = findViewById(R.id.membro_cep);
        cidadeMembro = findViewById(R.id.membro_cidade);
        conversaoMembro = findViewById(R.id.membro_data_conversao);
        equipeMembro = findViewById(R.id.membro_equipe);
        cargoMembro = findViewById(R.id.membro_cargo);
        tempoMembro = findViewById(R.id.membro_tempo_ponte);

        celularMark = Mask.insert("(##) #####-####", celularMembro);
        celularMembro.addTextChangedListener(celularMark);

        nascimentoMask = Mask.insert("##/##/####", nascimentoMembro);
        nascimentoMembro.addTextChangedListener(nascimentoMask);

        alturaMask = Mask.insert("#.##", alturaMembro);
        alturaMembro.addTextChangedListener(alturaMask);

        cepMask = Mask.insert("##.###-###", cepMembro);
        cepMembro.addTextChangedListener(cepMask);

        nascimentoMask = Mask.insert("##/##/####", conversaoMembro);
        conversaoMembro.addTextChangedListener(nascimentoMask);

        cadastrarMembro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                nome = nomeMembro.getText().toString();
                naturalidade = naturalidadeMembro.getText().toString();
                celular = Integer.parseInt(celularMembro.getText().toString());
                nascimento = null;
                DateFormat formatter = new SimpleDateFormat("MM/dd/yyyy");
                try {
                    nascimento = (Date)formatter.parse(nascimentoMembro.getText().toString());
                } catch (ParseException e) {
                    e.printStackTrace();
                }
                switch (sexoMembro.getCheckedRadioButtonId()) {
                    case R.id.membroRadioM:
                        sexo = "masculino";
                        break;
                    case R.id.membroRadioF:
                        sexo = "feminino";
                        break;
                }
                altura = Double.parseDouble(alturaMembro.getText().toString());
                logradouro = logradouroMembro.getText().toString();
                numero = Integer.parseInt(numeroMembro.getText().toString());
                bairro = bairroMembro.getText().toString();
                cep = cepMembro.getText().toString();
                cidade = cidadeMembro.getText().toString();
                uf = ufMembro.getSelectedItem().toString();
                data_conversao = null;
                DateFormat formatter1 = new SimpleDateFormat("MM/dd/yyyy");
                try {
                    data_conversao = (Date)formatter1.parse(conversaoMembro.getText().toString());
                } catch (ParseException e) {
                    e.printStackTrace();
                }
                equipe = equipeMembro.getText().toString();
                tempo_ponte = tempoMembro.getText().toString();
                cargo = cargoMembro.getText().toString();
                Gr gr = null;
                try {
                    gr = new GrDBController(MaisMembroActivity.this).getGr(grMembro.getSelectedItem().toString());
                } catch (ParseException e) {
                    e.printStackTrace();
                }
                colete = coleteMembro.getSelectedItem().toString();
                Membro membro = new Membro(nome,naturalidade,celular,nascimento,sexo,altura,logradouro,numero,bairro,cep,
                        cidade,uf,data_conversao,equipe,tempo_ponte,cargo,gr,colete);

                if (membro.getNome().equals("") || membro.getNaturalidade().equals("") || membro.getCelular() == 0 ||
                        membro.getNascimento().equals("") || membro.getSexo().equals("") || membro.getAltura() == 0 ||
                        membro.getLogradouro().equals("") || membro.getNumero() == 0 || membro.getBairro().equals("") ||
                        membro.getCep().equals("") || membro.getCidade().equals("") || membro.getUf().equals("") ||
                        membro.getData_conversao().equals("") || membro.getEquipe().equals("") ||
                        membro.getTempo_ponte().equals("") || membro.getCargo().equals("") || membro.getGr().equals("")
                        || membro.getColete().equals("")){
                    alert("Preencha todos os campos.");
                }else {
                    MembroDBController membroDBController = new MembroDBController(MaisMembroActivity.this);

                    membroDBController.insert(membro);

                    alert("Membro cadastrado com sucesso!");
                    Intent intent = new Intent(MaisMembroActivity.this, InicioActivity.class);
                    startActivity(intent);
                }
            }
        });

    }

    private void alert(String s){
        Toast.makeText(this,s,Toast.LENGTH_LONG).show();
    }

}
