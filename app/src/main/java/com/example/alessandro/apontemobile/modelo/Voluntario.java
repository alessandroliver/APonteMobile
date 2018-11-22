package com.example.alessandro.apontemobile.modelo;

import java.io.Serializable;
import java.util.Date;

public class Voluntario extends Pessoa implements Serializable {
    private Long id;
    private String funcao;
    private double horario_pegar;
    private double horario_largar;
    private double hora_semanal;
    private String tamanho_camisa;
    private int cpf;
    private int rg;
    private String area;


    public Voluntario(String nome, String naturalidade, int celular, Date nascimento, String sexo, float altura,
                      String logradouro, int numero, String bairro, String cep, String cidade, String uf, Long id,
                      String funcao, double horario_pegar, double horario_largar, double hora_semanal,
                      String tamanho_camisa, int cpf, int rg, String area) {
        super(nome, naturalidade, celular, nascimento, sexo, altura, logradouro, numero, bairro, cep, cidade, uf);
        this.id = id;
        this.funcao = funcao;
        this.horario_pegar = horario_pegar;
        this.horario_largar = horario_largar;
        this.hora_semanal = hora_semanal;
        this.tamanho_camisa = tamanho_camisa;
        this.cpf = cpf;
        this.rg = rg;
        this.area = area;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFuncao() {
        return funcao;
    }

    public void setFuncao(String funcao) {
        this.funcao = funcao;
    }

    public double getHorario_pegar() {
        return horario_pegar;
    }

    public void setHorario_pegar(double horario_pegar) {
        this.horario_pegar = horario_pegar;
    }

    public double getHorario_largar() {
        return horario_largar;
    }

    public void setHorario_largar(double horario_largar) {
        this.horario_largar = horario_largar;
    }

    public double getHora_semanal() {
        return hora_semanal;
    }

    public void setHora_semanal(double hora_semanal) {
        this.hora_semanal = hora_semanal;
    }

    public String getTamanho_camisa() {
        return tamanho_camisa;
    }

    public void setTamanho_camisa(String tamanho_camisa) {
        this.tamanho_camisa = tamanho_camisa;
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

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    @Override
    public String toString() {
        return getId() + " - " + getNome();
    }
}
