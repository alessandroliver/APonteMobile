package com.example.alessandro.apontemobile;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import com.example.alessandro.apontemobile.modelo.Cafe;
import com.example.alessandro.apontemobile.modelo.Shop;

import java.text.ParseException;
import java.util.List;

public class ShopActivity extends AppCompatActivity {

    private Button maisShop;
    private List<Shop> listaShop = null;
    public static String acao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shop);

        try {
            listaShop = new ShopDBController(this).getAllShop();
        } catch (ParseException e) {
            e.printStackTrace();
        }

        Bundle extras = getIntent().getExtras();

        if (extras != null) {
            acao = extras.getString("acaoExtra");
        }

        ListView listashop = findViewById(R.id.lista_cafes);
        ArrayAdapter<Shop> adapter = new ArrayAdapter<Shop>(this, android.R.layout.simple_list_item_1, listaShop);
        listashop.setAdapter(adapter);

        maisShop = findViewById(R.id.add_shop);

        maisShop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ShopActivity.this, MaisShopActivity.class);
                startActivity(intent);
            }
        });

    }
}
