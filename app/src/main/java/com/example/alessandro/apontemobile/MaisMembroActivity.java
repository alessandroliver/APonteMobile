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

public class MaisMembroActivity extends AppCompatActivity {

    private Button maisGr, cadastrarMembro;
    private EditText celularMembro, nascimentoMembro, alturaMembro, cepMembro, conversaoMembro;
    private TextWatcher celularMark, nascimentoMask, alturaMask, cepMask;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mais_membro);

        //maisGr = findViewById(R.id.button_gr);

        cadastrarMembro = findViewById(R.id.button_cadastro_membro);

        Spinner spStatesMembro = (Spinner) findViewById(R.id.membro_sp_state);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.states, android.R.layout.simple_spinner_item);
        spStatesMembro.setAdapter(adapter);

        Spinner spStatesMembro1 = (Spinner) findViewById(R.id.membro_sp_colete);
        ArrayAdapter<CharSequence> adapter1 = ArrayAdapter.createFromResource(this,
                R.array.coletes, android.R.layout.simple_spinner_item);
        spStatesMembro1.setAdapter(adapter1);

        EditText celularMembro = findViewById(R.id.membro_celular);
        EditText nascimentoMembro = findViewById(R.id.membro_nascimento);
        EditText alturaMembro = findViewById(R.id.membro_altura);
        EditText cepMembro = findViewById(R.id.membro_cep);
        EditText conversaoMembro = findViewById(R.id.membro_data_conversao);

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
