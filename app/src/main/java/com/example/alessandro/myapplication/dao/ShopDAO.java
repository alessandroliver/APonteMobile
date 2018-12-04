package com.example.alessandro.myapplication.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.support.annotation.NonNull;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.example.alessandro.myapplication.modelo.Shop;

import com.example.alessandro.myapplication.ListaShopActivity;

public class ShopDAO extends SQLiteOpenHelper {

    public ShopDAO(Context context) {
        super(context, "Shop", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String sql = "CREATE TABLE Shops (nomeProduto TEXT, valor_compra REAL, valor_venda REAL, quantidade INTEGER, loja TEXT, local_fabricacao TEXT, data_compra TEXT, marca TEXT, id INTEGER PRIMARY KEY, tipo TEXT, cor TEXT, data_fabricacao TEXT)";
        db.execSQL(sql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        String sql = "DROP TABLE IF EXISTS Shops";
        db.execSQL(sql);
        onCreate(db);
    }

    public void insere(Shop shop) {
        SQLiteDatabase db = getWritableDatabase();

        ContentValues dados = pegaDadosDoShop(shop);

        db.insert("Shops", null, dados);
    }

    @NonNull
    private ContentValues pegaDadosDoShop(Shop shop) {
        ContentValues dados = new ContentValues();
        dados.put("nomeProduto", shop.getNomeProduto());
        dados.put("valor_compra", shop.getValor_compra());
        dados.put("valor_venda", shop.getValor_venda());
        dados.put("quantidade", shop.getQuantidade());
        dados.put("loja", shop.getLoja());
        dados.put("local_fabricacao", shop.getLocal_fabricacao());
        DateFormat df = new SimpleDateFormat("MM/dd/yyyy");
        Date d1 = shop.getData_compra();
        String reportDate = df.format(shop.getData_compra());
        String h =reportDate;
        dados.put("data_compra", reportDate);
        dados.put("marca", shop.getMarca());
        dados.put("tipo", shop.getTipo());
        dados.put("cor", shop.getCor());
        DateFormat dft = new SimpleDateFormat("MM/dd/yyyy");
        Date d1l = shop.getData_fabricacao();
        String reportDat = dft.format(shop.getData_fabricacao());
        String hl =reportDat;
        dados.put("data_fabricacao", reportDat);
        return dados;
    }

    public List<Shop> buscaShop() {
        String sql = "SELECT * from Shops;";
        SQLiteDatabase db = getReadableDatabase();
        Cursor c = db.rawQuery(sql, null);

        List<Shop> shops = new ArrayList<>();
        while(c.moveToNext()){
            Shop shop = new Shop();
            shop.setId(c.getLong(c.getColumnIndex("id")));
            shop.setNomeProduto(c.getString(c.getColumnIndex("nomeProduto")));
            shop.setValor_compra(c.getDouble(c.getColumnIndex("valor_compra")));
            shop.setValor_venda(c.getDouble(c.getColumnIndex("valor_venda")));
            shop.setQuantidade(c.getInt(c.getColumnIndex("quantidade")));
            shop.setLoja(c.getString(c.getColumnIndex("loja")));
            shop.setLocal_fabricacao(c.getString(c.getColumnIndex("local_fabricacao")));

            DateFormat df = new SimpleDateFormat("MM/dd/yyyy");
            Date date = null;
            try {
                date = df.parse(c.getString(c.getColumnIndex("data_compra")));
            } catch (ParseException e) {
                e.printStackTrace();
            }
            Date d2 = date;
            shop.setData_compra(date);
            Date d3 = shop.getData_compra();
            shop.setMarca(c.getString(c.getColumnIndex("marca")));
            shop.setTipo(c.getString(c.getColumnIndex("tipo")));
            shop.setCor(c.getString(c.getColumnIndex("cor")));

            DateFormat dft = new SimpleDateFormat("MM/dd/yyyy");
            Date dat = null;
            try {
                dat = dft.parse(c.getString(c.getColumnIndex("data_fabricacao")));
            } catch (ParseException e) {
                e.printStackTrace();
            }
            Date d22 = dat;
            shop.setData_fabricacao(dat);
            shops.add(shop);
        }
        c.close();
        return shops;
    }

    public void deleta(Shop shop) {
        SQLiteDatabase db = getWritableDatabase();
        String[] params = {shop.getId()+ ""};
        db.delete("Shops", "id = ?", params);
    }

    public void altera(Shop shop) {
        SQLiteDatabase db = getWritableDatabase();

        ContentValues dados = pegaDadosDoShop(shop);

        String[] params = {shop.getId()+ ""};
        db.update("Shops", dados, "id = ?", params);
    }

}
