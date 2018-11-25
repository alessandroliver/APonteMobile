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

public class MaisFuncionarioActivity extends AppCompatActivity {

    private Button cadastrarFuncionario;
    private EditText celularFuncionario, nascimentoFuncionario, alturaFuncionario, cepFuncionario, rgFuncionario,
            cpfFuncionario, pisFuncionario, contaFuncionario, agenciaFuncionario, pagamentoFuncionario,
            entradaFuncionario;
    private TextWatcher celularMark, dataNMask, alturaMask, cepMask, rgMask, cpfMask, pisMask,contaMask, agenciaMask,
            dataPMask, dataEMask;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mais_funcionario);

        celularFuncionario = findViewById(R.id.funcionario_celular);
        nascimentoFuncionario = findViewById(R.id.funcionario_nascimento);
        alturaFuncionario = findViewById(R.id.funcionario_altura);
        cepFuncionario = findViewById(R.id.funcionario_cep);
        rgFuncionario = findViewById(R.id.funcionario_rg);
        cpfFuncionario = findViewById(R.id.funcionario_cpf);
        pisFuncionario = findViewById(R.id.funcionario_pis);
        contaFuncionario = findViewById(R.id.funcionario_conta);
        agenciaFuncionario = findViewById(R.id.funcionario_agencia);
        pagamentoFuncionario = findViewById(R.id.funcionario_data_pagamento);
        entradaFuncionario = findViewById(R.id.funcionario_entrada);

        celularMark = Mask.insert("(##) #####-####", celularFuncionario);
        celularFuncionario.addTextChangedListener(celularMark);

        dataNMask = Mask.insert("##/##/####", nascimentoFuncionario);
        nascimentoFuncionario.addTextChangedListener(dataNMask);

        alturaMask = Mask.insert("#.##", alturaFuncionario);
        alturaFuncionario.addTextChangedListener(alturaMask);

        cepMask = Mask.insert("##.###-###", cepFuncionario);
        cepFuncionario.addTextChangedListener(cepMask);

        rgMask = Mask.insert("#.###.###", rgFuncionario);
        rgFuncionario.addTextChangedListener(rgMask);

        cpfMask = Mask.insert("###.###.###-##", cpfFuncionario);
        cpfFuncionario.addTextChangedListener(cpfMask);

        pisMask = Mask.insert("###.#####.##-#", pisFuncionario);
        pisFuncionario.addTextChangedListener(pisMask);

        contaMask = Mask.insert("##.###-#", contaFuncionario);
        contaFuncionario.addTextChangedListener(contaMask);

        agenciaMask = Mask.insert("####-#", agenciaFuncionario);
        agenciaFuncionario.addTextChangedListener(agenciaMask);

        dataPMask = Mask.insert("##/##/####", pagamentoFuncionario);
        pagamentoFuncionario.addTextChangedListener(dataPMask);

        dataEMask = Mask.insert("##/##/####", entradaFuncionario);
        entradaFuncionario.addTextChangedListener(dataEMask);

        cadastrarFuncionario = findViewById(R.id.button_cadastro_funcionario);

        Spinner spStatesFuncionario = (Spinner) findViewById(R.id.funcionario_sp_state);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.states, android.R.layout.simple_spinner_item);
        spStatesFuncionario.setAdapter(adapter);

        Spinner spStatesFuncionario1 = (Spinner) findViewById(R.id.funcionario_sp_camisa);
        ArrayAdapter<CharSequence> adapter1 = ArrayAdapter.createFromResource(this,
                R.array.camisas, android.R.layout.simple_spinner_item);
        spStatesFuncionario1.setAdapter(adapter1);

        cadastrarFuncionario.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                alert("Funcionário cadastrado com sucesso!");
                Intent intent = new Intent(MaisFuncionarioActivity.this, InicioActivity.class);
                startActivity(intent);
            }
        });

    }

    private void alert(String s){
        Toast.makeText(this,s,Toast.LENGTH_LONG).show();
    }

}