package com.example.alessandro.apontemobile.modelo;

import java.io.Serializable;
import java.util.Date;

public class Cafe extends Produtos implements Serializable {
    private int id;
    private String sabor;
    private String temperatura;
    private String ingrediente;
    private String validade;
    private double peso;

    public Cafe(String nomeProduto, double valor_compra, double valor_venda, int quantidade, String loja,
                String local_fabricacao, Date data_compra, String marca, int id, String sabor,
                String temperatura, String ingrediente, String validade, double peso) {
        super(nomeProduto, valor_compra, valor_venda, quantidade, loja, local_fabricacao, data_compra, marca);
        this.id = id;
        this.sabor = sabor;
        this.temperatura = temperatura;
        this.ingrediente = ingrediente;
        this.validade = validade;
        this.peso = peso;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public String getValidade() {
        return validade;
    }

    public void setValidade(String validade) {
        this.validade = validade;
    }

    public double getPeso() {
        return peso;
    }

    public void setPeso(double peso) {
        this.peso = peso;
    }

    @Override
    public String toString() {
        return getId() + " - " + getNomeProduto();
    }
}
