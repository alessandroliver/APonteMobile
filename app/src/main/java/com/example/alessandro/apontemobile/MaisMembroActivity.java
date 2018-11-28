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

public class MaisMembroActivity extends AppCompatActivity {

    private Button maisGr, cadastrarMembro;
    private EditText nomeMembro, celularMembro, nascimentoMembro, alturaMembro, naturalidadeMembro, logradouroMembro,
            numeroMembro, bairroMembro, cepMembro, cidadeMembro, conversaoMembro, equipeMembro, cargoMembro, tempoMembro;
    private TextWatcher celularMark, nascimentoMask, alturaMask, cepMask;
    private RadioButton radioMMembro, radioFMembro;
    private Spinner ufMembro, coleteMembro, grMembro;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mais_membro);

        //maisGr = findViewById(R.id.button_gr);

        cadastrarMembro = findViewById(R.id.button_cadastro_membro);

        radioMMembro = findViewById(R.id.membroRadioM);
        radioFMembro = findViewById(R.id.membroRadioF);

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

        /*maisGr.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MaisMembroActivity.this, PopGrActivity.class);
                startActivity(intent);
            }
        });*/

        cadastrarMembro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                alert("Membro cadastrado com sucesso!");
                Intent intent = new Intent(MaisMembroActivity.this, InicioActivity.class);
                startActivity(intent);
            }
        });

    }

    private void alert(String s){
        Toast.makeText(this,s,Toast.LENGTH_LONG).show();
    }

}
