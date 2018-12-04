package com.example.alessandro.myapplication.modelo;

import java.io.Serializable;
import java.util.Date;

public class Funcionario extends Pessoa implements Serializable {
    private long id;
    private String cargo;
    private double salario;
    private int hora_semanal;
    private int carteira_trabalho;
    private int pis;
    private int cpf;
    private int rg;
    private int conta;
    private String banco;
    private int agencia;
    private String camisa;
    private Date entrada;
    private Date pagamento;

    public Funcionario(String nome, String naturalidade, int celular, Date nascimento, String sexo, double altura,
                       String logradouro, int numero, String bairro, String cep, String cidade, String uf, long id,
                       String cargo, double salario, int hora_semanal, int carteira_trabalho, int pis, int cpf,
                       int rg, int conta, String banco, int agencia, String camisa, Date entrada, Date pagamento) {
        super(nome, naturalidade, celular, nascimento, sexo, altura, logradouro, numero, bairro, cep, cidade, uf);
        this.id = id;
        this.cargo = cargo;
        this.salario = salario;
        this.hora_semanal = hora_semanal;
        this.carteira_trabalho = carteira_trabalho;
        this.pis = pis;
        this.cpf = cpf;
        this.rg = rg;
        this.conta = conta;
        this.banco = banco;
        this.agencia = agencia;
        this.camisa = camisa;
        this.entrada = entrada;
        this.pagamento = pagamento;
    }

    public Funcionario(){
        super();
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getCargo() {
        return cargo;
    }

    public void setCargo(String cargo) {
        this.cargo = cargo;
    }

    public double getSalario() {
        return salario;
    }

    public void setSalario(double salario) {
        this.salario = salario;
    }

    public int getHora_semanal() {
        return hora_semanal;
    }

    public void setHora_semanal(int hora_semanal) {
        this.hora_semanal = hora_semanal;
    }

    public int getCarteira_trabalho() {
        return carteira_trabalho;
    }

    public void setCarteira_trabalho(int carteira_trabalho) {
        this.carteira_trabalho = carteira_trabalho;
    }

    public int getPis() {
        return pis;
    }

    public void setPis(int pis) {
        this.pis = pis;
    }

    public int getCpf() {
        return cpf;
    }

    public void setCpf(int cpf) {
        this.cpf = cpf;
    }

    public int getRg() {
        return rg;
    }

    public void setRg(int rg) {
        this.rg = rg;
    }

    public int getConta() {
        return conta;
    }

    public void setConta(int conta) {
        this.conta = conta;
    }

    public String getBanco() {
        return banco;
    }

    public void setBanco(String banco) {
        this.banco = banco;
    }

    public int getAgencia() {
        return agencia;
    }

    public void setAgencia(int agencia) {
        this.agencia = agencia;
    }

    public String getCamisa() {
        return camisa;
    }

    public void setCamisa(String camisa) {
        this.camisa = camisa;
    }

    public Date getEntrada() {
        return entrada;
    }

    public void setEntrada(Date entrada) {
        this.entrada = entrada;
    }

    public Date getPagamento() {
        return pagamento;
    }

    public void setPagamento(Date pagamento) {
        this.pagamento = pagamento;
    }

    @Override
    public String toString() {
        return getId() + " - " + getNome();
    }
}
