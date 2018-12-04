package com.example.alessandro.myapplication.modelo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Aluno extends Pessoa implements Serializable {

    private long id;
    private String matricula;
    private String farda;
    private String responsavel;
    private String serie;
    private String escola;
    private List<Curso> curso;

    public Aluno(String nome, String naturalidade, int celular, Date nascimento, String sexo, double altura,
                 String logradouro, int numero, String bairro, String cep, String cidade, String uf, long id,
                 String matricula, String farda, String responsavel, String serie, String escola, ArrayList<Curso> curso) {
        super(nome, naturalidade, celular, nascimento, sexo, altura, logradouro, numero, bairro, cep, cidade, uf);
        this.id = id;
        this.matricula = matricula;
        this.farda = farda;
        this.responsavel = responsavel;
        this.serie = serie;
        this.escola = escola;
        this.curso = curso;
    }

    public Aluno(){
        super();
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
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

    public List<Curso> getCurso() {
        return curso;
    }

    public void setCurso(List<Curso> curso) {
        this.curso = curso;
    }

    @Override
    public String toString() {
        return getId() + " - " + getNome();
    }

}