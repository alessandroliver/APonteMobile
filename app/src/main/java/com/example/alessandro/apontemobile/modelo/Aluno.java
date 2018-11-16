package com.example.alessandro.apontemobile.modelo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;

public class Aluno extends Pessoa implements Serializable {
    private Long id;
    private ArrayList<Curso> curso;
    private int matricula;
    private String farda;
    private String responsavel;
    private String serie;
    private String escola;
    private Date data_matricula;

    public Aluno(String nome, String naturalidade, int celular, Date nascimento, String sexo, float altura,
                 String logradouro, int numero, String bairro, String cep, String cidade, String uf, Long id,
                 ArrayList<Curso> curso, int matricula, String farda, String responsavel, String serie,
                 String escola, Date data_matricula) {
        super(nome, naturalidade, celular, nascimento, sexo, altura, logradouro, numero, bairro, cep, cidade, uf);
        this.id = id;
        this.curso = curso;
        this.matricula = matricula;
        this.farda = farda;
        this.responsavel = responsavel;
        this.serie = serie;
        this.escola = escola;
        this.data_matricula = data_matricula;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public ArrayList<Curso> getCurso() {
        return curso;
    }

    public void setCurso(ArrayList<Curso> curso) {
        this.curso = curso;
    }

    public int getMatricula() {
        return matricula;
    }

    public void setMatricula(int matricula) {
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

    public Date getData_matricula() {
        return data_matricula;
    }

    public void setData_matricula(Date data_matricula) {
        this.data_matricula = data_matricula;
    }

    @Override
    public String toString() {
        return getId() + " - " + getNome();
    }
}
