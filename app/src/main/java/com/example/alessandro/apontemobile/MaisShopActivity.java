package com.example.alessandro.apontemobile;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MaisShopActivity extends AppCompatActivity {

    private Button cadastrarShop;
    private EditText dataCompraShop, dataFabricacaoShop;
    private TextWatcher dataCompraMask, dataFabricacaoMask;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mais_shop);

        dataCompraShop = findViewById(R.id.shop_data_compra);
        dataFabricacaoShop = findViewById(R.id.shop_data_fabricacao);

        dataCompraMask = Mask.insert("##/##/####", dataCompraShop);
        dataCompraShop.addTextChangedListener(dataCompraMask);

        dataFabricacaoMask = Mask.insert("##/##/####", dataFabricacaoShop);
        dataFabricacaoShop.addTextChangedListener(dataFabricacaoMask);

        cadastrarShop = findViewById(R.id.button_cadastro_shop);

        cadastrarShop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                alert("Produto da Ponte Shop cadastrado com sucesso!");
                Intent intent = new Intent(MaisShopActivity.this, InicioActivity.class);
                startActivity(intent);
            }
        });

    }

    private void alert(String s){
        Toast.makeText(this,s,Toast.LENGTH_LONG).show();
    }

}
