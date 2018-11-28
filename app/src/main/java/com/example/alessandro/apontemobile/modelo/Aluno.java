package com.example.alessandro.apontemobile.modelo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;

public class Aluno extends Pessoa implements Serializable {
    private int id;
    private ArrayList<Curso> curso;
    private String matricula;
    private String farda;
    private String responsavel;
    private String serie;
    private String escola;

    public Aluno(String nome, String naturalidade, int celular, Date nascimento, String sexo, double altura,
                 String logradouro, int numero, String bairro, String cep, String cidade, String uf, int id,
                 ArrayList<Curso> curso, String matricula, String farda, String responsavel, String serie,
                 String escola) {
        super(nome, naturalidade, celular, nascimento, sexo, altura, logradouro, numero, bairro, cep, cidade, uf);
        this.id = id;
        this.curso = curso;
        this.matricula = matricula;
        this.farda = farda;
        this.responsavel = responsavel;
        this.serie = serie;
        this.escola = escola;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public ArrayList<Curso> getCurso() {
        return curso;
    }

    public void setCurso(ArrayList<Curso> curso) {
        this.curso = curso;
    }

    public String getMatricula() {
        return matricula;
    }

    public void setMatricula(String matricula) {
        this.matricula = matricula;
    }

    public String getFarda() {
        return farda;
    }

    public void setFarda(String farda) {
        this.farda = farda;
    }

    public String getResponsavel() {
        return responsavel;
    }

    public void setResponsavel(String responsavel) {
        this.responsavel = responsavel;
    }

    public String getSerie() {
        return serie;
    }

    public void setSerie(String serie) {
        this.serie = serie;
    }

    public String getEscola() {
        return escola;
    }

    public void setEscola(String escola) {
        this.escola = escola;
    }

    @Override
    public String toString() {
        return getId() + " - " + getNome();
    }
}
