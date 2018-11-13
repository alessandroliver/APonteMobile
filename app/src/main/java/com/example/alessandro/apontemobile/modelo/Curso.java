package com.example.alessandro.apontemobile.modelo;

import java.util.Date;
import java.util.List;

public class Curso {
    private String nome;
    private Date inicio;
    private Date fim;
    private int carga_horaria;
    private String professor;
    private String dia;
    private double pegar;
    private double largar;
    private String sala;
    private List<Aluno> aluno;

    public Curso(String nome, Date inicio, Date fim, int carga_horaria, String professor, String dia, double pegar,
                 double largar, String sala, List<Aluno> aluno) {
        this.nome = nome;
        this.inicio = inicio;
        this.fim = fim;
        this.carga_horaria = carga_horaria;
        this.professor = professor;
        this.dia = dia;
        this.pegar = pegar;
        this.largar = largar;
        this.sala = sala;
        this.aluno = aluno;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Date getInicio() {
        return inicio;
    }

    public void setInicio(Date inicio) {
        this.inicio = inicio;
    }

    public Date getFim() {
        return fim;
    }

    public void setFim(Date fim) {
        this.fim = fim;
    }

    public int getCarga_horaria() {
        return carga_horaria;
    }

    public void setCarga_horaria(int carga_horaria) {
        this.carga_horaria = carga_horaria;
    }

    public String getProfessor() {
        return professor;
    }

    public void setProfessor(String professor) {
        this.professor = professor;
    }

    public String getDia() {
        return dia;
    }

    public void setDia(String dia) {
        this.dia = dia;
    }

    public double getPegar() {
        return pegar;
    }

    public void setPegar(double pegar) {
        this.pegar = pegar;
    }

    public double getLargar() {
        return largar;
    }

    public void setLargar(double largar) {
        this.largar = largar;
    }

    public String getSala() {
        return sala;
    }

    public void setSala(String sala) {
        this.sala = sala;
    }

    public List<Aluno> getAluno() {
        return aluno;
    }

    public void setAluno(List<Aluno> aluno) {
        this.aluno = aluno;
    }

}
