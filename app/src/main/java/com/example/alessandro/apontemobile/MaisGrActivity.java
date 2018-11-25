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

public class MaisGrActivity extends AppCompatActivity {

    private Button cadastrarGr;
    private EditText contatoGr, cepGr, inauguracaoGr;
    private TextWatcher contatoMark, cepMask, inauguracaoMask;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mais_gr);

        cadastrarGr = findViewById(R.id.button_cadastro_gr);

        contatoGr = findViewById(R.id.gr_contato);
        cepGr = findViewById(R.id.gr_cep);
        inauguracaoGr = findViewById(R.id.gr_inauguracao);

        contatoMark = Mask.insert("(##) #####-####", contatoGr);
        contatoGr.addTextChangedListener(contatoMark);

        cepMask = Mask.insert("##.###-####", cepGr);
        cepGr.addTextChangedListener(cepMask);

        inauguracaoMask = Mask.insert("##/##/####", inauguracaoGr);
        inauguracaoGr.addTextChangedListener(inauguracaoMask);

        Spinner spStatesGr = (Spinner) findViewById(R.id.gr_sp_state);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.states, android.R.layout.simple_spinner_item);
        spStatesGr.setAdapter(adapter);

        cadastrarGr.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                alert("GR cadastrado com sucesso!");
                Intent intent = new Intent(MaisGrActivity.this, InicioActivity.class);
                startActivity(intent);
            }
        });

    }

    private void alert(String s){
        Toast.makeText(this,s,Toast.LENGTH_LONG).show();
    }

}
