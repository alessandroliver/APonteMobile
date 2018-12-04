package com.example.alessandro.myapplication;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextWatcher;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.alessandro.myapplication.modelo.Shop;

import com.example.alessandro.myapplication.dao.ShopDAO;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class MaisShopActivity extends AppCompatActivity {

    private MaisShopHelper helper;
    private TextWatcher compraMask, fabricacaoMask;
    private EditText compraShop, fabricacaoShop;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mais_shop);

        compraShop = findViewById(R.id.shop_data_compra);

        compraMask = Mask.insert("##/##/####", compraShop);
        compraShop.addTextChangedListener(compraMask);

        fabricacaoShop = findViewById(R.id.shop_data_fabricacao);

        fabricacaoMask = Mask.insert("##/##/####", fabricacaoShop);
        fabricacaoShop.addTextChangedListener(fabricacaoMask);

        helper = new MaisShopHelper(this, this);

        Intent intent = getIntent();
        Shop shop = (Shop) intent.getSerializableExtra("shop");

        if(shop != null){
            helper.preencheShop(shop);
        }

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_formulario, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.menu_formulario_ok:
                Shop shop = helper.getShop();
                ShopDAO dao = new ShopDAO(this);

                if(shop.getId() != 0){
                    dao.altera(shop);
                }else{
                    dao.insere(shop);
                }


                dao.close();
                Toast.makeText(MaisShopActivity.this, "Shop " + shop.getNomeProduto() + " salvo!",
                        Toast.LENGTH_SHORT).show();
                finish();
                break;
        }
        return super.onOptionsItemSelected(item);
    }

}