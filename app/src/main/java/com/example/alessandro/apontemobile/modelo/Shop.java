package com.example.alessandro.apontemobile.modelo;

import java.io.Serializable;
import java.util.Date;

public class Shop extends Produtos implements Serializable {
    private Long id;
    private String tipo;
    private String cor;
    private Date data_fabricacao;

    public Shop(String nomeProduto, double valor_compra, double valor_venda, int quantidade, String loja,
                String local_fabricacao, Date data_compra, String marca, Long id, String tipo, String cor,
                Date data_fabricacao) {
        super(nomeProduto, valor_compra, valor_venda, quantidade, loja, local_fabricacao, data_compra, marca);
        this.id = id;
        this.tipo = tipo;
        this.cor = cor;
        this.data_fabricacao = data_fabricacao;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
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

    @Override
    public String toString() {
        return getId() + " - " + getNomeProduto();
    }
}
