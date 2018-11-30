package com.example.alessandro.apontemobile;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.alessandro.apontemobile.modelo.Material;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static com.example.alessandro.apontemobile.MaterialDBOpenHelper.TABLE_MATERIAL;

public class MaterialDBController {

    private SQLiteDatabase db;
    private MaterialDBOpenHelper materialDB;

    public MaterialDBController(Context context){
        materialDB = new MaterialDBOpenHelper(context);
    }

    public List<Material> getAllMaterial() throws ParseException {
        List<Material> materialList = new ArrayList<Material>();
        Cursor cursor = loadItens();
        if (cursor.moveToFirst()){
            do{

                DateFormat df = new SimpleDateFormat("MM/dd/yyyy");
                Date date = df.parse(cursor.getString(6));

                Material mat = new Material(cursor.getInt(0),cursor.getString(1),
                        cursor.getDouble(2),cursor.getString(3),cursor.getString(4),
                        cursor.getString(5),date,cursor.getString(7),
                        cursor.getString(8),cursor.getDouble(9),cursor.getDouble(10),
                        cursor.getInt(11));

                materialList.add(mat);
            } while (cursor.moveToNext());
        }
        cursor.close();

        return materialList;
    }

    public Cursor loadItens(){
        Cursor cursor;
        db = materialDB.getReadableDatabase();
        cursor = db.query(TABLE_MATERIAL, materialDB.COLUNA, null, null, null, null,
                null, null);

        if(cursor!=null && cursor.getCount() > 0){
            cursor.moveToFirst();
        }
        db.close();
        return cursor;
    }

    public String insert(Material material){
        long check =0;
        SQLiteDatabase db = materialDB.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put(MaterialDBOpenHelper._ID, material.getId());
        values.put(MaterialDBOpenHelper.NOME_MATERIAL, material.getNomeMaterial());
        values.put(MaterialDBOpenHelper.VALOR, material.getValor());
        values.put(MaterialDBOpenHelper.FINALIDADE, material.getFinalidade());
        values.put(MaterialDBOpenHelper.GARANTIA, material.getGarantia());
        values.put(MaterialDBOpenHelper.LOJA, material.getLoja());

        DateFormat df = new SimpleDateFormat("MM/dd/yyyy");
        String reportDate = df.format(material.getData_compra());

        values.put(MaterialDBOpenHelper.DATA_COMPRA, reportDate);
        values.put(MaterialDBOpenHelper.VALIDADE, material.getValidade());
        values.put(MaterialDBOpenHelper.TIPO, material.getTipo());
        values.put(MaterialDBOpenHelper.PESO, material.getPeso());
        values.put(MaterialDBOpenHelper.TAMANHO, material.getTamanho());
        values.put(MaterialDBOpenHelper.QUANTIDADE_MATERIAL, material.getQuantidadeMaterial());

        check = db.insert(TABLE_MATERIAL, null, values);


        db.close();

        if(check == -1){
            return "Error";
        } else{
            return "DB created";
        }
    }

    public void clearAll(){

        materialDB.getWritableDatabase().delete(TABLE_MATERIAL,null,null);

    }

    public int updateMaterial(Material material){
        SQLiteDatabase db = materialDB.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put(MaterialDBOpenHelper._ID, material.getId());
        values.put(MaterialDBOpenHelper.NOME_MATERIAL, material.getNomeMaterial());
        values.put(MaterialDBOpenHelper.VALOR, material.getValor());
        values.put(MaterialDBOpenHelper.FINALIDADE, material.getFinalidade());
        values.put(MaterialDBOpenHelper.GARANTIA, material.getGarantia());
        values.put(MaterialDBOpenHelper.LOJA, material.getLoja());

        DateFormat df = new SimpleDateFormat("MM/dd/yyyy");
        String reportDate = df.format(material.getData_compra());

        values.put(MaterialDBOpenHelper.DATA_COMPRA, reportDate);
        values.put(MaterialDBOpenHelper.VALIDADE, material.getValidade());
        values.put(MaterialDBOpenHelper.TIPO, material.getTipo());
        values.put(MaterialDBOpenHelper.PESO, material.getPeso());
        values.put(MaterialDBOpenHelper.TAMANHO, material.getTamanho());
        values.put(MaterialDBOpenHelper.QUANTIDADE_MATERIAL, material.getQuantidadeMaterial());

        int update = db.update(TABLE_MATERIAL, values, MaterialDBOpenHelper._ID + " = ?",
                new String[]{String.valueOf(material.getId())});
        db.close();
        return update;

    }
}
