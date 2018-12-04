package com.example.alessandro.myapplication;

import android.content.Context;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Spinner;

import com.example.alessandro.myapplication.dao.FuncionarioDAO;
import com.example.alessandro.myapplication.modelo.Funcionario;
import com.example.alessandro.myapplication.modelo.Voluntario;

import java.text.DateFormat;
import java.text.Format;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class MaisFuncionarioHelper {

    private final EditText campoNome;
    private final EditText campoNaturalidade;
    private final EditText campoCelular;
    private final EditText campoNascimento;
    private final RadioGroup campoSexo;
    private final EditText campoAltura;
    private final EditText campoLogradouro;
    private final EditText campoNumero;
    private final EditText campoBairro;
    private final EditText campoCep;
    private final EditText campoCidade;
    private final Spinner campoUf;
    private final EditText campoCargo;
    private final EditText campoSalario;
    private final EditText campoHoraSemanal;
    private final EditText campoCarteiraTrabalho;
    private final EditText campoPis;
    private final EditText campoCpf;
    private final EditText campoRg;
    private final EditText campoConta;
    private final EditText campoBanco;
    private final EditText campoAgencia;
    private final Spinner campoCamisa;
    private final EditText campoEntrada;
    private final EditText campoPagamento;

    private Funcionario funcionario;

    private Context context;

    public MaisFuncionarioHelper(MaisFuncionarioActivity activity, Context context){
        this.context = context;
        campoNome = (EditText) activity.findViewById(R.id.funcionario_nome);
        campoNaturalidade = (EditText) activity.findViewById(R.id.funcionario_naturalidade);
        campoCelular = (EditText) activity.findViewById(R.id.funcionario_celular);
        campoNascimento = (EditText) activity.findViewById(R.id.funcionario_nascimento);
        campoSexo = (RadioGroup) activity.findViewById(R.id.funcionario_sexo);
        campoAltura = (EditText) activity.findViewById(R.id.funcionario_altura);
        campoLogradouro = (EditText) activity.findViewById(R.id.funcionario_logradouro);
        campoNumero = (EditText) activity.findViewById(R.id.funcionario_numero);
        campoBairro = (EditText) activity.findViewById(R.id.funcionario_bairro);
        campoCep = (EditText) activity.findViewById(R.id.funcionario_cep);
        campoCidade = (EditText) activity.findViewById(R.id.funcionario_cidade);
        campoUf = (Spinner) activity.findViewById(R.id.funcionario_sp_state);
        campoCargo = (EditText) activity.findViewById(R.id.funcionario_cargo);
        campoSalario = (EditText) activity.findViewById(R.id.funcionario_salario);
        campoHoraSemanal = (EditText) activity.findViewById(R.id.funcionario_hora_semanal);
        campoCarteiraTrabalho = (EditText) activity.findViewById(R.id.funcionario_carteira_trabalho);
        campoPis = (EditText) activity.findViewById(R.id.funcionario_pis);
        campoCpf = (EditText) activity.findViewById(R.id.funcionario_cpf);
        campoRg = (EditText) activity.findViewById(R.id.funcionario_rg);
        campoConta = (EditText) activity.findViewById(R.id.funcionario_conta);
        campoBanco = (EditText) activity.findViewById(R.id.funcionario_banco);
        campoAgencia = (EditText) activity.findViewById(R.id.funcionario_agencia);
        campoCamisa = (Spinner) activity.findViewById(R.id.funcionario_sp_camisa);
        campoEntrada = (EditText) activity.findViewById(R.id.funcionario_entrada);
        campoPagamento = (EditText) activity.findViewById(R.id.funcionario_data_pagamento);

        funcionario = new Funcionario();
    }

    public Funcionario getFuncionario() {
        funcionario.setNome(campoNome.getText().toString());
        funcionario.setNaturalidade(campoNaturalidade.getText().toString());
        funcionario.setCelular(Integer.valueOf(campoCelular.getText().toString()));

        DateFormat df = new SimpleDateFormat("MM/dd/yyyy");
        Date date = null;
        try {
            date = df.parse(campoNascimento.getText().toString());
        } catch (ParseException e) {
            e.printStackTrace();
        }
        Date d1 = date;
        funcionario.setNascimento(date);
        String sx = "";
        switch (campoSexo.getCheckedRadioButtonId()) {
            case R.id.funcionarioRadioM:
                sx = "masculino";
                break;
            case R.id.funcionarioRadioF:
                sx = "feminino";
                break;
        }
        funcionario.setSexo(sx);
        funcionario.setAltura(Double.valueOf(campoAltura.getText().toString()));
        funcionario.setLogradouro(campoLogradouro.getText().toString());
        funcionario.setNumero(Integer.valueOf(campoNumero.getText().toString()));
        funcionario.setBairro(campoBairro.getText().toString());
        funcionario.setCep(campoCep.getText().toString());
        funcionario.setCidade(campoCidade.getText().toString());
        funcionario.setUf(campoUf.getSelectedItem().toString());
        funcionario.setCargo(campoCargo.getText().toString());
        funcionario.setSalario(Double.valueOf(campoSalario.getText().toString()));
        funcionario.setHora_semanal(Integer.valueOf(campoHoraSemanal.getText().toString()));
        funcionario.setCarteira_trabalho(Integer.valueOf(campoCarteiraTrabalho.getText().toString()));
        funcionario.setPis(Integer.valueOf(campoPis.getText().toString()));
        funcionario.setCpf(Integer.valueOf(campoCpf.getText().toString()));
        funcionario.setRg(Integer.valueOf(campoRg.getText().toString()));
        funcionario.setConta(Integer.valueOf(campoConta.getText().toString()));
        funcionario.setBanco(campoBanco.getText().toString());
        funcionario.setAgencia(Integer.valueOf(campoAgencia.getText().toString()));
        funcionario.setCamisa(campoCamisa.getSelectedItem().toString());

        DateFormat df1 = new SimpleDateFormat("MM/dd/yyyy");
        Date date1 = null;
        try {
            date1 = df1.parse(campoEntrada.getText().toString());
        } catch (ParseException e) {
            e.printStackTrace();
        }
        Date d12 = date1;
        funcionario.setEntrada(date1);

        DateFormat df2 = new SimpleDateFormat("MM/dd/yyyy");
        Date date2 = null;
        try {
            date2 = df2.parse(campoPagamento.getText().toString());
        } catch (ParseException e) {
            e.printStackTrace();
        }
        Date d10 = date2;
        funcionario.setPagamento(date2);

        return funcionario;
    }

    public void preencheFuncionario(Funcionario funcionario) {
        FuncionarioDAO funcionarioDAO = new FuncionarioDAO(context);
        Funcionario f1 = null;
        List<Funcionario> fs = funcionarioDAO.buscaFuncionario();
        for (Funcionario f2: fs) {
            if(f2.getId() == funcionario.getId()){
                f1= f2;
            }
        }
        campoNome.setText(f1.getNome());
        campoNaturalidade.setText(f1.getNaturalidade());
        campoCelular.setText(f1.getCelular() +"");

        DateFormat df = new SimpleDateFormat("MM/dd/yyyy");
        String reportDate = df.format(f1.getNascimento());
        campoNascimento.setText(reportDate);

        if(f1.getSexo().equals("feminino")){
            campoSexo.check(R.id.funcionarioRadioF);
        } else{
            campoSexo.check(R.id.funcionarioRadioM);

        }
        campoAltura.setText(f1.getAltura() +"");
        campoLogradouro.setText(f1.getLogradouro());
        campoNumero.setText(f1.getNumero() +"");
        campoBairro.setText(f1.getBairro());
        campoCep.setText(f1.getCep());
        campoCidade.setText(f1.getCidade());

        String compareValue = f1.getUf();
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(context, R.array.states, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        if (compareValue != null){
            int spinnerPosition = adapter.getPosition(compareValue);
            campoUf.setSelection(spinnerPosition);
        }

        campoCargo.setText(funcionario.getCargo());
        campoSalario.setText(funcionario.getSalario() +"");
        campoHoraSemanal.setText(funcionario.getHora_semanal() +"");
        campoCarteiraTrabalho.setText(funcionario.getCarteira_trabalho() +"");
        campoPis.setText(funcionario.getPis() +"");
        campoCpf.setText(funcionario.getCpf() +"");
        campoRg.setText(funcionario.getRg() +"");
        campoConta.setText(funcionario.getConta() +"");
        campoBanco.setText(funcionario.getBanco());
        campoAgencia.setText(funcionario.getAgencia() +"");

        String compareValues = funcionario.getCamisa();
        ArrayAdapter<CharSequence> adapters = ArrayAdapter.createFromResource(context, R.array.camisas, android.R.layout.simple_spinner_item);
        adapters.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        if (compareValues != null){
            int spinnerPosition = adapters.getPosition(compareValues);
            campoCamisa.setSelection(spinnerPosition);
        }

        DateFormat df1 = new SimpleDateFormat("MM/dd/yyyy");
        String reportDat = df1.format(funcionario.getEntrada());
        campoEntrada.setText(reportDat);

        DateFormat df2 = new SimpleDateFormat("MM/dd/yyyy");
        String reportD = df2.format(funcionario.getPagamento());
        campoPagamento.setText(reportD);

        this.funcionario = funcionario;
    }


}
