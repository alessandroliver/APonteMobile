package com.example.alessandro.apontemobile;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.alessandro.apontemobile.modelo.Shop;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static com.example.alessandro.apontemobile.ShopDBOpenHelper.TABLE_SHOP;

public class ShopDBController {

    private SQLiteDatabase db;
    private ShopDBOpenHelper shopDB;

    public ShopDBController(Context context){
        shopDB = new ShopDBOpenHelper(context);
    }

    public List<Shop> getAllShop() throws ParseException {
        List<Shop> shopList = new ArrayList<Shop>();
        Cursor cursor = loadItens();
        if (cursor.moveToFirst()){
            do{

                DateFormat df = new SimpleDateFormat("MM/dd/yyyy");
                Date date = df.parse(cursor.getString(6));

                DateFormat dft = new SimpleDateFormat("MM/dd/yyyy");
                Date dat = dft.parse(cursor.getString(11));

                Shop sho = new Shop(cursor.getString(0),cursor.getDouble(1),
                        cursor.getDouble(2),cursor.getInt(3),cursor.getString(4),
                        cursor.getString(5),date,cursor.getString(7),cursor.getInt(8),
                        cursor.getString(9),cursor.getString(10),dat);

                shopList.add(sho);
            } while (cursor.moveToNext());
        }
        cursor.close();

        return shopList;
    }

    public Cursor loadItens(){
        Cursor cursor;
        db = shopDB.getReadableDatabase();
        cursor = db.query(TABLE_SHOP, shopDB.COLUNA, null, null, null, null,
                null, null);

        if(cursor!=null && cursor.getCount() > 0){
            cursor.moveToFirst();
        }
        db.close();
        return cursor;
    }

    public String insert(Shop shop){
        long check =0;
        SQLiteDatabase db = shopDB.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put(ShopDBOpenHelper.NOME_PRODUTO, shop.getNomeProduto());
        values.put(ShopDBOpenHelper.VALOR_COMPRA, shop.getValor_compra());
        values.put(ShopDBOpenHelper.VALOR_VENDA, shop.getNomeProduto());
        values.put(ShopDBOpenHelper.QUANTIDADE, shop.getValor_compra());
        values.put(ShopDBOpenHelper.LOJA, shop.getNomeProduto());
        values.put(ShopDBOpenHelper.LOCAL_FABRICACAO, shop.getValor_compra());

        DateFormat df = new SimpleDateFormat("MM/dd/yyyy");
        String reportDate = df.format(shop.getData_compra());

        values.put(ShopDBOpenHelper.DATA_COMPRA, reportDate);
        values.put(ShopDBOpenHelper.MARCA, shop.getMarca());
        values.put(ShopDBOpenHelper._ID, shop.getId());
        values.put(ShopDBOpenHelper.TIPO, shop.getTipo());
        values.put(ShopDBOpenHelper.COR, shop.getCor());

        DateFormat dft = new SimpleDateFormat("MM/dd/yyyy");
        String reportDat = dft.format(shop.getData_fabricacao());

        values.put(ShopDBOpenHelper.DATA_FABRICACAO, reportDat);

        check = db.insert(TABLE_SHOP, null, values);


        db.close();

        if(check == -1){
            return "Error";
        } else{
            return "DB created";
        }
    }

    public void clearAll(){

        shopDB.getWritableDatabase().delete(TABLE_SHOP,null,null);

    }

    public int updateShop(Shop shop){
        SQLiteDatabase db = shopDB.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put(ShopDBOpenHelper.NOME_PRODUTO, shop.getNomeProduto());
        values.put(ShopDBOpenHelper.VALOR_COMPRA, shop.getValor_compra());
        values.put(ShopDBOpenHelper.VALOR_VENDA, shop.getNomeProduto());
        values.put(ShopDBOpenHelper.QUANTIDADE, shop.getValor_compra());
        values.put(ShopDBOpenHelper.LOJA, shop.getNomeProduto());
        values.put(ShopDBOpenHelper.LOCAL_FABRICACAO, shop.getValor_compra());

        DateFormat df = new SimpleDateFormat("MM/dd/yyyy");
        String reportDate = df.format(shop.getData_compra());

        values.put(ShopDBOpenHelper.DATA_COMPRA, reportDate);
        values.put(ShopDBOpenHelper.MARCA, shop.getMarca());
        values.put(ShopDBOpenHelper._ID, shop.getId());
        values.put(ShopDBOpenHelper.TIPO, shop.getTipo());
        values.put(ShopDBOpenHelper.COR, shop.getCor());

        DateFormat dft = new SimpleDateFormat("MM/dd/yyyy");
        String reportDat = dft.format(shop.getData_fabricacao());

        values.put(ShopDBOpenHelper.DATA_FABRICACAO, reportDat);

        int update = db.update(TABLE_SHOP, values, ShopDBOpenHelper._ID + " = ?",
                new String[]{String.valueOf(shop.getId())});
        db.close();
        return update;

    }

}
