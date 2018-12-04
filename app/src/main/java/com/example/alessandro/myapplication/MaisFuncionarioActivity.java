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

import com.example.alessandro.myapplication.dao.FuncionarioDAO;
import com.example.alessandro.myapplication.modelo.Funcionario;

public class MaisFuncionarioActivity extends AppCompatActivity {

    private MaisFuncionarioHelper helper;
    private Spinner ufFuncionario, camisaFuncionario;

    private EditText nascimentoFuncionario, pagamentoFuncionario, entradaFuncionario;
    private TextWatcher dataNMask, dataPMask, dataEMask;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mais_funcionario);

        ufFuncionario = (Spinner) findViewById(R.id.funcionario_sp_state);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.states, android.R.layout.simple_spinner_item);
        ufFuncionario.setAdapter(adapter);

        camisaFuncionario = (Spinner) findViewById(R.id.funcionario_sp_camisa);
        ArrayAdapter<CharSequence> adapter1 = ArrayAdapter.createFromResource(this,
                R.array.camisas, android.R.layout.simple_spinner_item);
        camisaFuncionario.setAdapter(adapter1);

        nascimentoFuncionario = findViewById(R.id.funcionario_nascimento);
        pagamentoFuncionario = findViewById(R.id.funcionario_data_pagamento);
        entradaFuncionario = findViewById(R.id.funcionario_entrada);

        dataNMask = Mask.insert("##/##/####", nascimentoFuncionario);
        nascimentoFuncionario.addTextChangedListener(dataNMask);

        dataPMask = Mask.insert("##/##/####", pagamentoFuncionario);
        pagamentoFuncionario.addTextChangedListener(dataPMask);

        dataEMask = Mask.insert("##/##/####", entradaFuncionario);
        entradaFuncionario.addTextChangedListener(dataEMask);

        helper = new MaisFuncionarioHelper(this, this);

        Intent intent = getIntent();
        Funcionario funcionario = (Funcionario) intent.getSerializableExtra("funcionario");

        if(funcionario != null){
            helper.preencheFuncionario(funcionario);
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
                Funcionario funcionario = helper.getFuncionario();
                FuncionarioDAO dao = new FuncionarioDAO(this);

                if(funcionario.getId() != 0){
                    dao.altera(funcionario);
                }else{
                    dao.insere(funcionario);
                }


                dao.close();
                Toast.makeText(MaisFuncionarioActivity.this, "Funcionario " + funcionario.getNome() + " salvo!",
                        Toast.LENGTH_SHORT).show();
                finish();
                break;
        }
        return super.onOptionsItemSelected(item);
    }

}