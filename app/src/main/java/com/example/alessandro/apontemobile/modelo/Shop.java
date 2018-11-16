package com.example.alessandro.apontemobile.modelo;

import java.io.Serializable;
import java.util.Date;

public class Shop extends Produtos implements Serializable {
    private Long id;
    private int qtd_venda;
    private String cor;
    private Date data_fabricacao;
    private String finalidade;

    public Shop(String nome, double valor_compra, double valor_venda, int quantidade, String loja,
                String local_fabricacao, Date data_compra, String marca, Long id, int qtd_venda,
                String cor, Date data_fabricacao, String finalidade) {
        super(nome, valor_compra, valor_venda, quantidade, loja, local_fabricacao, data_compra, marca);
        this.id = id;
        this.qtd_venda = qtd_venda;
        this.cor = cor;
        this.data_fabricacao = data_fabricacao;
        this.finalidade = finalidade;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getQtd_venda() {
        return qtd_venda;
    }

    public void setQtd_venda(int qtd_venda) {
        this.qtd_venda = qtd_venda;
    }

    public String getCor() {
        return cor;
    }

    public void setCor(String cor) {
        this.cor = cor;
    }

    public Date getData_fabricacao() {
        return data_fabricacao;
    }

    public void setData_fabricacao(Date data_fabricacao) {
        this.data_fabricacao = data_fabricacao;
    }

    public String getFinalidade() {
        return finalidade;
    }

    public void setFinalidade(String finalidade) {
        this.finalidade = finalidade;
    }

    @Override
    public String toString() {
        return getId() + " - " + getNome();
    }
}
