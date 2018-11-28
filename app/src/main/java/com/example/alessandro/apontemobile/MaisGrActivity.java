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

import com.example.alessandro.apontemobile.modelo.Gr;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class MaisGrActivity extends AppCompatActivity {

    private Button cadastrarGr;
    private EditText nomeGrGr, liderGr, apoioGr, diaGr, horarioGr, contatoGr, frequenciaGr, quantidadeGr, logradouroGr,
            numeroGr, bairroGr, cepGr, cidadeGr, inauguracaoGr;
    private TextWatcher contatoMark, cepMask, inauguracaoMask;
    private Spinner ufGr;
    String nomeGr;
    int quantidade;
    double horario;
    String dia;
    String frequencia;
    String lider;
    String apoio;
    int contato;
    Date inauguracao;
    String logradouro;
    int numero;
    String bairro;
    int cep;
    String cidade;
    String uf;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mais_gr);

        cadastrarGr = findViewById(R.id.button_cadastro_gr);

        nomeGrGr = findViewById(R.id.gr_nome);
        liderGr = findViewById(R.id.gr_lider);
        apoioGr = findViewById(R.id.gr_apoio);
        diaGr = findViewById(R.id.gr_dia);
        horarioGr = findViewById(R.id.gr_horario);
        contatoGr = findViewById(R.id.gr_contato);
        frequenciaGr = findViewById(R.id.gr_frequencia);
        quantidadeGr = findViewById(R.id.gr_quantidade);
        logradouroGr = findViewById(R.id.gr_logradouro);
        numeroGr = findViewById(R.id.gr_numero);
        bairroGr = findViewById(R.id.gr_bairro);
        cepGr = findViewById(R.id.gr_cep);
        cidadeGr = findViewById(R.id.gr_cidade);
        inauguracaoGr = findViewById(R.id.gr_inauguracao);

        contatoMark = Mask.insert("(##) #####-####", contatoGr);
        contatoGr.addTextChangedListener(contatoMark);

        cepMask = Mask.insert("##.###-####", cepGr);
        cepGr.addTextChangedListener(cepMask);

        inauguracaoMask = Mask.insert("##/##/####", inauguracaoGr);
        inauguracaoGr.addTextChangedListener(inauguracaoMask);

        ufGr = (Spinner) findViewById(R.id.gr_sp_state);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.states, android.R.layout.simple_spinner_item);
        ufGr.setAdapter(adapter);

        cadastrarGr.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                nomeGr = nomeGrGr.getText().toString();
                quantidade = Integer.parseInt(quantidadeGr.getText().toString());
                horario = Double.parseDouble(horarioGr.getText().toString());
                dia = diaGr.getText().toString();
                frequencia = frequenciaGr.getText().toString();
                lider = liderGr.getText().toString();
                apoio = apoioGr.getText().toString();
                contato = Integer.parseInt(contatoGr.getText().toString());
                inauguracao = null;
                DateFormat formatter = new SimpleDateFormat("MM/dd/yyyy");
                try {
                    inauguracao = (Date)formatter.parse(inauguracaoGr.getText().toString());
                } catch (ParseException e) {
                    e.printStackTrace();
                }
                logradouro = logradouroGr.getText().toString();
                numero = Integer.parseInt(numeroGr.getText().toString());
                bairro = bairroGr.getText().toString();
                cep = Integer.parseInt(cepGr.getText().toString());
                cidade = cidadeGr.getText().toString();
                uf = ufGr.getSelectedItem().toString();

                Gr gr = new Gr(nomeGr,quantidade,horario,dia,frequencia,lider,apoio,contato,inauguracao,logradouro,
                        numero,bairro,cep,cidade,uf);

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
