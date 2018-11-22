package com.example.alessandro.apontemobile.modelo;

import java.util.Date;

public abstract class Produtos {
    private String nomeProduto;
    private double valor_compra;
    private double valor_venda;
    private int quantidade;
    private String loja;
    private String local_fabricacao;
    private Date data_compra;
    private String marca;

    public Produtos(String nomeProduto, double valor_compra, double valor_venda, int quantidade, String loja,
                    String local_fabricacao, Date data_compra, String marca) {
        this.nomeProduto = nomeProduto;
        this.valor_compra = valor_compra;
        this.valor_venda = valor_venda;
        this.quantidade = quantidade;
        this.loja = loja;
        this.local_fabricacao = local_fabricacao;
        this.data_compra = data_compra;
        this.marca = marca;
    }

    public String getNomeProduto() {
        return nomeProduto;
    }

    public void setNomeProduto(String nomeProduto) {
        this.nomeProduto = nomeProduto;
    }

    public double getValor_compra() {
        return valor_compra;
    }

    public void setValor_compra(double valor_compra) {
        this.valor_compra = valor_compra;
    }

    public double getValor_venda() {
        return valor_venda;
    }

    public void setValor_venda(double valor_venda) {
        this.valor_venda = valor_venda;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }

    public String getLoja() {
        return loja;
    }

    public void setLoja(String loja) {
        this.loja = loja;
    }

    public String getLocal_fabricacao() {
        return local_fabricacao;
    }

    public void setLocal_fabricacao(String local_fabricacao) {
        this.local_fabricacao = local_fabricacao;
    }

    public Date getData_compra() {
        return data_compra;
    }

    public void setData_compra(Date data_compra) {
        this.data_compra = data_compra;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }
}
