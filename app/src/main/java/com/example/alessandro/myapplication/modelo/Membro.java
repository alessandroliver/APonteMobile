package com.example.alessandro.myapplication.modelo;

import java.io.Serializable;
import java.sql.Array;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Membro extends Pessoa implements Serializable {
    private long id;
    private Date data_conversao;
    private String equipe;
    private String tempo_ponte;
    private String cargo;
    private Gr gr;
    private String colete;

    public Membro(String nome, String naturalidade, int celular, Date nascimento, String sexo, double altura,
                  String logradouro, int numero, String bairro, String cep, String cidade, String uf, long id,
                  Date data_conversao, String equipe, String tempo_ponte, String cargo, Gr gr, String colete) {
        super(nome, naturalidade, celular, nascimento, sexo, altura, logradouro, numero, bairro, cep, cidade, uf);
        this.id = id;
        this.data_conversao = data_conversao;
        this.equipe = equipe;
        this.tempo_ponte = tempo_ponte;
        this.cargo = cargo;
        this.gr = gr;
        this.colete = colete;

    }

    public Membro(){
        super();
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
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

    public String getTempo_ponte() {
        return tempo_ponte;
    }

    public void setTempo_ponte(String tempo_ponte) {
        this.tempo_ponte = tempo_ponte;
    }

    public String getCargo() {
        return cargo;
    }

    public void setCargo(String cargo) {
        this.cargo = cargo;
    }

    public Gr getGr() {
        return gr;
    }

    public void setGr(Gr gr) {
        this.gr = gr;
    }

    public String getColete() {
        return colete;
    }

    public void setColete(String colete) {
        this.colete = colete;
    }

    //coment para apagar
    @Override
    public String toString() {
        return getNome();
    }

}
