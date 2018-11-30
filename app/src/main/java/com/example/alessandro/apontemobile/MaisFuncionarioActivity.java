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

import com.example.alessandro.apontemobile.modelo.Funcionario;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class MaisFuncionarioActivity extends AppCompatActivity {

    private Button cadastrarFuncionario;
    private EditText nomeFuncionario, celularFuncionario, nascimentoFuncionario, alturaFuncionario,
            naturalidadeFuncionario, logradouroFuncionario, numeroFuncionario, bairroFuncionario, cepFuncionario,
            cidadeFuncionario, cargoFuncionario, salarioFuncionario, rgFuncionario, cpfFuncionario, carteiraFuncionario,
            pisFuncionario, bancoFuncionario, contaFuncionario, agenciaFuncionario, pagamentoFuncionario,
            entradaFuncionario, horaSemanalFuncionario;
    private TextWatcher celularMark, dataNMask, alturaMask, cepMask, rgMask, cpfMask, pisMask,contaMask, agenciaMask,
            dataPMask, dataEMask;
    private RadioGroup radioSexo;
    private Spinner ufFuncionario, camisaFuncionario;
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
    String cargo;
    double salario;
    int hora_semanal;
    int carteira_trabalho;
    int pis;
    int cpf;
    int rg;
    int conta;
    String banco;
    int agencia;
    String camisa;
    Date entrada;
    Date pagamento;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mais_funcionario);

        radioSexo = findViewById(R.id.funcionario_sexo);

        nomeFuncionario = findViewById(R.id.funcionario_nome);
        celularFuncionario = findViewById(R.id.funcionario_celular);
        nascimentoFuncionario = findViewById(R.id.funcionario_nascimento);
        alturaFuncionario = findViewById(R.id.funcionario_altura);
        naturalidadeFuncionario = findViewById(R.id.funcionario_naturalidade);
        logradouroFuncionario = findViewById(R.id.funcionario_logradouro);
        numeroFuncionario = findViewById(R.id.funcionario_numero);
        bairroFuncionario = findViewById(R.id.funcionario_bairro);
        cepFuncionario = findViewById(R.id.funcionario_cep);
        cidadeFuncionario = findViewById(R.id.funcionario_cidade);
        cargoFuncionario = findViewById(R.id.funcionario_cargo);
        salarioFuncionario = findViewById(R.id.funcionario_salario);
        rgFuncionario = findViewById(R.id.funcionario_rg);
        cpfFuncionario = findViewById(R.id.funcionario_cpf);
        carteiraFuncionario = findViewById(R.id.funcionario_carteira_trabalho);
        pisFuncionario = findViewById(R.id.funcionario_pis);
        bancoFuncionario = findViewById(R.id.funcionario_banco);
        contaFuncionario = findViewById(R.id.funcionario_conta);
        agenciaFuncionario = findViewById(R.id.funcionario_agencia);
        pagamentoFuncionario = findViewById(R.id.funcionario_data_pagamento);
        entradaFuncionario = findViewById(R.id.funcionario_entrada);
        horaSemanalFuncionario = findViewById(R.id.funcionario_hora_semanal);

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

        ufFuncionario = (Spinner) findViewById(R.id.funcionario_sp_state);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.states, android.R.layout.simple_spinner_item);
        ufFuncionario.setAdapter(adapter);

        camisaFuncionario = (Spinner) findViewById(R.id.funcionario_sp_camisa);
        ArrayAdapter<CharSequence> adapter1 = ArrayAdapter.createFromResource(this,
                R.array.camisas, android.R.layout.simple_spinner_item);
        camisaFuncionario.setAdapter(adapter1);

        cadastrarFuncionario.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                nome = nomeFuncionario.getText().toString();
                naturalidade = naturalidadeFuncionario.getText().toString();
                celular = Integer.parseInt(celularFuncionario.getText().toString());
                nascimento = null;
                DateFormat formatter = new SimpleDateFormat("MM/dd/yyyy");
                try {
                    nascimento = (Date)formatter.parse(nascimentoFuncionario.getText().toString());
                } catch (ParseException e) {
                    e.printStackTrace();
                }
                switch (radioSexo.getCheckedRadioButtonId()) {
                    case R.id.funcionarioRadioM:
                        sexo = "masculino";
                        break;
                    case R.id.funcionarioRadioF:
                        sexo = "feminino";
                        break;
                }
                altura = Double.parseDouble(alturaFuncionario.getText().toString());
                logradouro = logradouroFuncionario.getText().toString();
                numero = Integer.parseInt(numeroFuncionario.getText().toString());
                bairro = bairroFuncionario.getText().toString();
                cep = cepFuncionario.getText().toString();
                cidade = cidadeFuncionario.getText().toString();
                uf = ufFuncionario.getSelectedItem().toString();
                cargo = cargoFuncionario.getText().toString();
                salario = Double.parseDouble(salarioFuncionario.getText().toString());
                hora_semanal = Integer.parseInt(horaSemanalFuncionario.getText().toString());
                carteira_trabalho = Integer.parseInt(carteiraFuncionario.getText().toString());
                pis = Integer.parseInt(pisFuncionario.getText().toString());
                cpf = Integer.parseInt(cpfFuncionario.getText().toString());
                rg = Integer.parseInt(rgFuncionario.getText().toString());
                conta = Integer.parseInt(contaFuncionario.getText().toString());
                banco = bancoFuncionario.getText().toString();
                agencia = Integer.parseInt(agenciaFuncionario.getText().toString());
                camisa = camisaFuncionario.getSelectedItem().toString();
                entrada = null;
                DateFormat formatter1 = new SimpleDateFormat("MM/dd/yyyy");
                try {
                    entrada = (Date)formatter1.parse(entradaFuncionario.getText().toString());
                } catch (ParseException e) {
                    e.printStackTrace();
                }
                pagamento = null;
                DateFormat formatter2 = new SimpleDateFormat("MM/dd/yyyy");
                try {
                    pagamento = (Date)formatter2.parse(pagamentoFuncionario.getText().toString());
                } catch (ParseException e) {
                    e.printStackTrace();
                }

                Funcionario funcionario = new Funcionario(nome,naturalidade,celular,nascimento,sexo,altura,logradouro,
                        numero,bairro,cep,cidade,uf,cargo,salario,hora_semanal,carteira_trabalho,pis,cpf,rg,conta,
                        banco,agencia,camisa,entrada,pagamento);

                if (funcionario.getNome().equals("") || funcionario.getNaturalidade().equals("") ||
                        funcionario.getCelular() == 0 || funcionario.getNascimento().equals("") ||
                        funcionario.getSexo().equals("") || funcionario.getAltura() == 0 ||
                        funcionario.getLogradouro().equals("") || funcionario.getNumero() == 0 ||
                        funcionario.getBairro().equals("") || funcionario.getCep().equals("") ||
                        funcionario.getCidade().equals("") || funcionario.getUf().equals("") ||
                        funcionario.getCargo().equals("") || funcionario.getSalario() == 0 ||
                        funcionario.getHora_semanal() == 0 || funcionario.getCarteira_trabalho() == 0 ||
                        funcionario.getPis() == 0 || funcionario.getCpf() == 0 || funcionario.getRg() == 0 ||
                        funcionario.getConta() == 0 || funcionario.getBanco().equals("") || funcionario.getAgencia() == 0
                        || funcionario.getCamisa().equals("") || funcionario.getEntrada().equals("") ||
                        funcionario.getPagamento().equals("")){
                    alert("Preencha todos os campos.");
                } else {
                    FuncionarioDBController funcionarioDBController = new FuncionarioDBController
                            (MaisFuncionarioActivity.this);

                    funcionarioDBController.insert(funcionario);

                    alert("Funcionário cadastrado com sucesso!");
                    Intent intent = new Intent(MaisFuncionarioActivity.this, InicioActivity.class);
                    startActivity(intent);
                }
            }
        });

    }

    private void alert(String s){
        Toast.makeText(this,s,Toast.LENGTH_LONG).show();
    }

}