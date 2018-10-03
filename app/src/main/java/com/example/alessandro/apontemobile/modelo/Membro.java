package com.example.alessandro.apontemobile.modelo;

import java.util.Date;

public class Membro extends Pessoa {
    private Date data_conversao;
    private String equipe;
    private double tempo_ponte;
    private Date data_ponte;
    private String cargo;
    private String gr;

    public Membro(String nome, String naturalidade, int celular, Date nascimento, String sexo, float altura,
                  String logradouro, int numero, String bairro, String cep, String cidade, String uf,
                  Date data_conversao, String equipe, double tempo_ponte, Date data_ponte, String cargo, String gr) {
        super(nome, naturalidade, celular, nascimento, sexo, altura, logradouro, numero, bairro, cep, cidade, uf);
        this.data_conversao = data_conversao;
        this.equipe = equipe;
        this.tempo_ponte = tempo_ponte;
        this.data_ponte = data_ponte;
        this.cargo = cargo;
        this.gr = gr;
    }

    public Date getData_conversao() {
        return data_conversao;
    }

    public void setData_conversao(Date data_conversao) {
        this.data_conversao = data_conversao;
    }

    public String getEquipe() {
        return equipe;
    }

    public void setEquipe(String equipe) {
        this.equipe = equipe;
    }

    public double getTempo_ponte() {
        return tempo_ponte;
    }

    public void setTempo_ponte(double tempo_ponte) {
        this.tempo_ponte = tempo_ponte;
    }

    public Date getData_ponte() {
        return data_ponte;
    }

    public void setData_ponte(Date data_ponte) {
        this.data_ponte = data_ponte;
    }

    public String getCargo() {
        return cargo;
    }

    public void setCargo(String cargo) {
        this.cargo = cargo;
    }

    public String getGr() {
        return gr;
    }

    public void setGr(String gr) {
        this.gr = gr;
    }

}
