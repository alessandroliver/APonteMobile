package com.example.alessandro.apontemobile.modelo;

import java.util.Date;

public class Cafe extends Produtos {
    private String sabor;
    private String temperatura;
    private String ingrediente;
    private int validade;
    private int qtd_venda;
    private double peso;

    public Cafe(String nome, double valor_compra, double valor_venda, int quantidade, String loja,
                String local_fabricacao, Date data_compra, String marca, String sabor, String temperatura,
                String ingrediente, int validade, int qtd_venda, double peso) {
        super(nome, valor_compra, valor_venda, quantidade, loja, local_fabricacao, data_compra, marca);
        this.sabor = sabor;
        this.temperatura = temperatura;
        this.ingrediente = ingrediente;
        this.validade = validade;
        this.qtd_venda = qtd_venda;
        this.peso = peso;
    }

    public String getSabor() {
        return sabor;
    }

    public void setSabor(String sabor) {
        this.sabor = sabor;
    }

    public String getTemperatura() {
        return temperatura;
    }

    public void setTemperatura(String temperatura) {
        this.temperatura = temperatura;
    }

    public String getIngrediente() {
        return ingrediente;
    }

    public void setIngrediente(String ingrediente) {
        this.ingrediente = ingrediente;
    }

    public int getValidade() {
        return validade;
    }

    public void setValidade(int validade) {
        this.validade = validade;
    }

    public int getQtd_venda() {
        return qtd_venda;
    }

    public void setQtd_venda(int qtd_venda) {
        this.qtd_venda = qtd_venda;
    }

    public double getPeso() {
        return peso;
    }

    public void setPeso(double peso) {
        this.peso = peso;
    }
}
