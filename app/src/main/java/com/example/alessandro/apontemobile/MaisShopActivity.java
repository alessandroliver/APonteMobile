package com.example.alessandro.apontemobile;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.alessandro.apontemobile.modelo.Shop;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class MaisShopActivity extends AppCompatActivity {

    private Button cadastrarShop;
    private EditText nomeShop, marcaShop, lojaShop, valorCompraShop, valorVendaShop, localFabricacaoShop, dataCompraShop,
            idShop, tipoShop, quantidadeShop, corShop, dataFabricacaoShop;
    private TextWatcher dataCompraMask, dataFabricacaoMask;
    String nomeProduto;
    double valor_compra;
    double valor_venda;
    int quantidade;
    String loja;
    String local_fabricacao;
    Date data_compra;
    String marca;
    int id;
    String tipo;
    String cor;
    Date data_fabricacao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mais_shop);

        idShop = findViewById(R.id.shop_id);
        nomeShop = findViewById(R.id.shop_nome);
        marcaShop = findViewById(R.id.shop_marca);
        lojaShop = findViewById(R.id.shop_loja);
        valorCompraShop = findViewById(R.id.shop_valor_compra);
        valorVendaShop = findViewById(R.id.shop_valor_venda);
        localFabricacaoShop = findViewById(R.id.shop_local_fabricacao);
        dataCompraShop = findViewById(R.id.shop_data_compra);
        tipoShop = findViewById(R.id.shop_tipo);
        quantidadeShop = findViewById(R.id.shop_quantidade);
        corShop = findViewById(R.id.shop_cor);
        dataFabricacaoShop = findViewById(R.id.shop_data_fabricacao);

        dataCompraMask = Mask.insert("##/##/####", dataCompraShop);
        dataCompraShop.addTextChangedListener(dataCompraMask);

        dataFabricacaoMask = Mask.insert("##/##/####", dataFabricacaoShop);
        dataFabricacaoShop.addTextChangedListener(dataFabricacaoMask);

        cadastrarShop = findViewById(R.id.button_cadastro_shop);

        cadastrarShop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                nomeProduto = nomeShop.getText().toString();
                valor_compra = Double.parseDouble(valorCompraShop.getText().toString());
                valor_venda = Double.parseDouble(valorVendaShop.getText().toString());
                quantidade = Integer.parseInt(quantidadeShop.getText().toString());
                loja = lojaShop.getText().toString();
                local_fabricacao = localFabricacaoShop.getText().toString();
                data_compra = null;
                DateFormat formatter = new SimpleDateFormat("MM/dd/yyyy");
                try {
                    data_compra = (Date)formatter.parse(dataCompraShop.getText().toString());
                } catch (ParseException e) {
                    e.printStackTrace();
                }
                marca = marcaShop.getText().toString();
                id = Integer.parseInt(idShop.getText().toString());
                tipo = tipoShop.getText().toString();
                cor = corShop.getText().toString();
                data_fabricacao = null;
                DateFormat formatter1 = new SimpleDateFormat("MM/dd/yyyy");
                try {
                    data_fabricacao = (Date)formatter1.parse(dataFabricacaoShop.getText().toString());
                } catch (ParseException e) {
                    e.printStackTrace();
                }

                Shop shop = new Shop(nomeProduto,valor_compra,valor_venda,quantidade,loja,local_fabricacao,data_compra,
                        marca,id,tipo,cor,data_fabricacao);

                if (shop.getNomeProduto().equals("") || shop.getValor_compra() == 0 || shop.getValor_venda() == 0 ||
                        shop.getQuantidade() == 0 || shop.getLoja().equals("") || shop.getLocal_fabricacao().equals("") ||
                        shop.getData_compra().equals("") || shop.getMarca().equals("") || shop.getId() == 0 ||
                        shop.getTipo().equals("") || shop.getCor().equals("") || shop.getData_fabricacao().equals("")){
                    alert("Preencha todos os campos.");
                }else {
                    ShopDBController shopDBController = new ShopDBController(MaisShopActivity.this);

                    shopDBController.insert(shop);

                    alert("Produto da Ponte Shop cadastrado com sucesso!");
                    Intent intent = new Intent(MaisShopActivity.this, InicioActivity.class);
                    startActivity(intent);
                }
            }
        });

    }

    private void alert(String s){
        Toast.makeText(this,s,Toast.LENGTH_LONG).show();
    }

}
