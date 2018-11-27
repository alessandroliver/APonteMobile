package com.example.alessandro.apontemobile;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.alessandro.apontemobile.modelo.Cafe;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static com.example.alessandro.apontemobile.CafeDBOpenHelper.TABLE_CAFE;

public class CafeDBController {

    private SQLiteDatabase db;
    private CafeDBOpenHelper cafeDB;

    public CafeDBController(Context context){
        cafeDB = new CafeDBOpenHelper(context);
    }

    public List<Cafe> getAllCafe() throws ParseException {
        List<Cafe> cafeList = new ArrayList<Cafe>();
        Cursor cursor = loadItens();
        if (cursor.moveToFirst()){
            do{

                DateFormat df = new SimpleDateFormat("MM/dd/yyyy");
                Date date = df.parse(cursor.getString(6));

                Cafe caf = new Cafe(cursor.getString(0),cursor.getDouble(1),
                        cursor.getDouble(2),cursor.getInt(3),cursor.getString(4),
                        cursor.getString(5),date,cursor.getString(7),cursor.getInt(8),
                        cursor.getString(9),cursor.getString(10),cursor.getString(11),
                        cursor.getString(12),cursor.getDouble(13));

                cafeList.add(caf);
            } while (cursor.moveToNext());
        }
        cursor.close();

        return cafeList;
    }

    public Cursor loadItens(){
        Cursor cursor;
        db = cafeDB.getReadableDatabase();
        cursor = db.query(TABLE_CAFE, cafeDB.COLUNA, null, null, null, null,
                null, null);

        if(cursor!=null && cursor.getCount() > 0){
            cursor.moveToFirst();
        }
        db.close();
        return cursor;
    }

    public String insert(Cafe cafe){
        long check =0;
        SQLiteDatabase db = cafeDB.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put(CafeDBOpenHelper.NOME_PRODUTO, cafe.getNomeProduto());
        values.put(CafeDBOpenHelper.VALOR_COMPRA, cafe.getValor_compra());
        values.put(CafeDBOpenHelper.VALOR_VENDA, cafe.getNomeProduto());
        values.put(CafeDBOpenHelper.QUANTIDADE, cafe.getValor_compra());
        values.put(CafeDBOpenHelper.LOJA, cafe.getNomeProduto());
        values.put(CafeDBOpenHelper.LOCAL_FABRICACAO, cafe.getValor_compra());

        DateFormat df = new SimpleDateFormat("MM/dd/yyyy");
        String reportDate = df.format(cafe.getData_compra());

        values.put(CafeDBOpenHelper.DATA_COMPRA, reportDate);
        values.put(CafeDBOpenHelper.MARCA, cafe.getMarca());
        values.put(CafeDBOpenHelper._ID, cafe.getId());
        values.put(CafeDBOpenHelper.SABOR, cafe.getSabor());
        values.put(CafeDBOpenHelper.TEMPERATURA, cafe.getTemperatura());
        values.put(CafeDBOpenHelper.INGREDIENTE, cafe.getIngrediente());
        values.put(CafeDBOpenHelper.VALIDADE, cafe.getValidade());
        values.put(CafeDBOpenHelper.PESO, cafe.getPeso());

        check = db.insert(TABLE_CAFE, null, values);


        db.close();

        if(check == -1){
            return "Error";
        } else{
            return "DB created";
        }
    }

    public void clearAll(){

        cafeDB.getWritableDatabase().delete(TABLE_CAFE,null,null);

    }

    public int updateCafe(Cafe cafe){
        SQLiteDatabase db = cafeDB.getWritableDatabase();


        ContentValues values = new ContentValues();
        values.put(CafeDBOpenHelper.NOME_PRODUTO, cafe.getNomeProduto());
        values.put(CafeDBOpenHelper.VALOR_COMPRA, cafe.getValor_compra());
        values.put(CafeDBOpenHelper.VALOR_VENDA, cafe.getNomeProduto());
        values.put(CafeDBOpenHelper.QUANTIDADE, cafe.getValor_compra());
        values.put(CafeDBOpenHelper.LOJA, cafe.getNomeProduto());
        values.put(CafeDBOpenHelper.LOCAL_FABRICACAO, cafe.getValor_compra());

        DateFormat df = new SimpleDateFormat("MM/dd/yyyy");
        String reportDate = df.format(cafe.getData_compra());

        values.put(CafeDBOpenHelper.DATA_COMPRA, reportDate);
        values.put(CafeDBOpenHelper.MARCA, cafe.getMarca());
        values.put(CafeDBOpenHelper._ID, cafe.getId());
        values.put(CafeDBOpenHelper.SABOR, cafe.getSabor());
        values.put(CafeDBOpenHelper.TEMPERATURA, cafe.getTemperatura());
        values.put(CafeDBOpenHelper.INGREDIENTE, cafe.getIngrediente());
        values.put(CafeDBOpenHelper.VALIDADE, cafe.getValidade());
        values.put(CafeDBOpenHelper.PESO, cafe.getPeso());


        int update = db.update(TABLE_CAFE, values, CafeDBOpenHelper._ID + " = ?",
                new String[]{String.valueOf(cafe.getId())});
        db.close();
        return update;

    }

}
