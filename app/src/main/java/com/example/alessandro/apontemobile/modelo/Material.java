package com.example.alessandro.apontemobile.modelo;

import java.io.Serializable;
import java.util.Date;

public class Material implements Serializable {
    private Long id;
    private String nomeMaterial;
    private double valor;
    private String finalidade;
    private String garantia;
    private String loja;
    private Date data_compra;
    private String validade;
    private String tipo;
    private double peso;
    private double tamanho;
    private int quantidadeMaterial;

    public Material(Long id, String nomeMaterial, double valor, String finalidade, String garantia, String loja,
                    Date data_compra, String validade, String tipo, double peso, double tamanho, int quantidadeMaterial) {
        this.id = id;
        this.nomeMaterial = nomeMaterial;
        this.valor = valor;
        this.finalidade = finalidade;
        this.garantia = garantia;
        this.loja = loja;
        this.data_compra = data_compra;
        this.validade = validade;
        this.tipo = tipo;
        this.peso = peso;
        this.tamanho = tamanho;
        this.quantidadeMaterial = quantidadeMaterial;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNomeMaterial() {
        return nomeMaterial;
    }

    public void setNomeMaterial(String nome) {
        this.nomeMaterial = nome;
    }

    public double getValor() {
        return valor;
    }

    public void setValor(double valor) {
        this.valor = valor;
    }

    public String getFinalidade() {
        return finalidade;
    }

    public void setFinalidade(String finalidade) {
        this.finalidade = finalidade;
    }

    public String getGarantia() {
        return garantia;
    }

    public void setGarantia(String garantia) {
        this.garantia = garantia;
    }

    public String getLoja() {
        return loja;
    }

    public void setLoja(String loja) {
        this.loja = loja;
    }

    public Date getData_compra() {
        return data_compra;
    }

    public void setData_compra(Date data_compra) {
        this.data_compra = data_compra;
    }

    public String getValidade() {
        return validade;
    }

    public void setValidade(String validade) {
        this.validade = validade;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public double getPeso() {
        return peso;
    }

    public void setPeso(double peso) {
        this.peso = peso;
    }

    public double getTamanho() {
        return tamanho;
    }

    public void setTamanho(double tamanho) {
        this.tamanho = tamanho;
    }

    public double getQuantidadeMaterial() {
        return quantidadeMaterial;
    }

    public void setQuantidadeMaterial(int quantidadeMaterial) {
        this.quantidadeMaterial = quantidadeMaterial;
    }

    @Override
    public String toString() {
        return getId() + " - " + getNomeMaterial();
    }
}
