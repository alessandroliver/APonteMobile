package com.example.alessandro.myapplication;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.example.alessandro.myapplication.dao.ShopDAO;
import com.example.alessandro.myapplication.modelo.Shop;

import java.text.ParseException;
import java.util.List;

public class ListaShopActivity extends AppCompatActivity {

    private ListView listaShop;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_shop);

        listaShop = (ListView) findViewById(R.id.lista_shops);

        listaShop.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
                Shop shop = (Shop) listaShop.getItemAtPosition(position);
                Intent intentVaiProFormulario = new Intent(ListaShopActivity.this, MaisShopActivity.class);
                intentVaiProFormulario.putExtra("shop", shop);
                startActivity(intentVaiProFormulario);
            }
        });

        Button novoCafe = (Button) findViewById(R.id.add_shop);
        novoCafe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intentVaiProFormulario = new Intent(ListaShopActivity.this, MaisShopActivity.class);
                startActivity(intentVaiProFormulario);
            }
        });

        registerForContextMenu(listaShop);

    }

    @Override
    protected void onResume() {
        super.onResume();
        carregaLista();
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, final ContextMenu.ContextMenuInfo menuInfo) {
        MenuItem deletar = menu.add("Deletar");
        deletar.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem menuItem) {
                AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) menuInfo;
                Shop shop = (Shop) listaShop.getItemAtPosition(info.position);
                Toast.makeText(ListaShopActivity.this, "Deletar o shop " + shop.getNomeProduto(),
                        Toast.LENGTH_SHORT).show();

                ShopDAO dao = new ShopDAO(ListaShopActivity.this);
                dao.deleta(shop);
                dao.close();

                carregaLista();
                return false;
            }
        });
    }

    private void carregaLista() {
        ShopDAO dao = new ShopDAO(this);
        List<Shop> shops = dao.buscaShop();
        dao.close();

        ArrayAdapter<Shop> adapter = new ArrayAdapter<Shop>(this, android.R.layout.simple_list_item_1, shops);
        listaShop.setAdapter(adapter);
    }

}
