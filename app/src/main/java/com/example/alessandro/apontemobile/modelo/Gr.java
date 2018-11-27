package com.example.alessandro.apontemobile.modelo;

import java.io.Serializable;
import java.util.Date;

public class Gr implements Serializable {
    private int id;
    private String nomeGr;
    private int quantidade;
    private double horario;
    private String dia;
    private String frequencia;
    private String lider;
    private String apoio;
    private int contato;
    private Date inauguracao;
    private String logradouro;
    private int numero;
    private String bairro;
    private int cep;
    private String cidade;
    private String uf;

    public Gr(int id, String nomeGr, int quantidade, double horario, String dia, String frequencia, String lider,
              String apoio, int contato, Date inauguracao, String logradouro, int numero, String bairro, int cep,
              String cidade, String uf) {
        this.id = id;
        this.nomeGr = nomeGr;
        this.quantidade = quantidade;
        this.horario = horario;
        this.dia = dia;
        this.frequencia = frequencia;
        this.lider = lider;
        this.apoio = apoio;
        this.contato = contato;
        this.inauguracao = inauguracao;
        this.logradouro = logradouro;
        this.numero = numero;
        this.bairro = bairro;
        this.cep = cep;
        this.cidade = cidade;
        this.uf = uf;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNomeGr() {
        return nomeGr;
    }

    public void setNomeGr(String nomeGr) {
        this.nomeGr = nomeGr;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }

    public double getHorario() {
        return horario;
    }

    public void setHorario(double horario) {
        this.horario = horario;
    }

    public String getDia() {
        return dia;
    }

    public void setDia(String dia) {
        this.dia = dia;
    }

    public String getFrequencia() {
        return frequencia;
    }

    public void setFrequencia(String frequencia) {
        this.frequencia = frequencia;
    }

    public String getLider() {
        return lider;
    }

    public void setLider(String lider) {
        this.lider = lider;
    }

    public String getApoio() {
        return apoio;
    }

    public void setApoio(String apoio) {
        this.apoio = apoio;
    }

    public int getContato() {
        return contato;
    }

    public void setContato(int contato) {
        this.contato = contato;
    }

    public Date getInauguracao() {
        return inauguracao;
    }

    public void setInauguracao(Date inauguracao) {
        this.inauguracao = inauguracao;
    }

    public String getLogradouro() {
        return logradouro;
    }

    public void setLogradouro(String logradouro) {
        this.logradouro = logradouro;
    }

    public int getNumero() {
        return numero;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }

    public String getBairro() {
        return bairro;
    }

    public void setBairro(String bairro) {
        this.bairro = bairro;
    }

    public int getCep() {
        return cep;
    }

    public void setCep(int cep) {
        this.cep = cep;
    }

    public String getCidade() {
        return cidade;
    }

    public void setCidade(String cidade) {
        this.cidade = cidade;
    }

    public String getUf() {
        return uf;
    }

    public void setUf(String uf) {
        this.uf = uf;
    }

    @Override
    public String toString() {
        return getId() + " - " + getNomeGr();
    }
}