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

import com.example.alessandro.myapplication.modelo.Material;

public class MaterialDAO extends SQLiteOpenHelper {
    public MaterialDAO(Context context) {
        super(context, "Material", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String sql = "CREATE TABLE Materiais (id INTEGER PRIMARY KEY, nomeMaterial TEXT NOT NULL, valor REAL, finalidade TEXT, garantia TEXT, loja TEXT, data_compra TEXT, validade TEXT, tipo TEXT, peso REAL, tamanho REAL, quantidadeMaterial INTEGER)";
        db.execSQL(sql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        String sql = "DROP TABLE IF EXISTS Materiais";
        db.execSQL(sql);
        onCreate(db);
    }

    public void insere(Material material){
        SQLiteDatabase db = getWritableDatabase();

        ContentValues dados = pegaDadosDoMaterial(material);

        db.insert("Materiais", null, dados);

    }

    private ContentValues pegaDadosDoMaterial(Material material){
        ContentValues dados = new ContentValues();
        dados.put("nomeMaterial", material.getNomeMaterial());
        dados.put("valor", material.getValor());
        dados.put("finalidade", material.getFinalidade());
        dados.put("garantia", material.getGarantia());
        dados.put("loja", material.getLoja());
        DateFormat df = new SimpleDateFormat("MM/dd/yyyy");
        Date d1 = material.getData_compra();
        String reportDate = df.format(material.getData_compra());
        String h =reportDate;
        dados.put("data_compra", reportDate);
        dados.put("validade", material.getValidade());
        dados.put("tipo", material.getTipo());
        dados.put("peso", material.getPeso());
        dados.put("tamanho", material.getTamanho());
        dados.put("quantidadeMaterial", material.getQuantidadeMaterial());
        return dados;
    }

    public List<Material> buscaMateriais(){
        String sql = "SELECT * from Materiais;";
        SQLiteDatabase db = getReadableDatabase();
        Cursor c = db.rawQuery(sql, null);

        List<Material> materiais = new ArrayList<>();
        while(c.moveToNext()){
            Material material = new Material();
            material.setId(c.getLong(c.getColumnIndex("id")));
            material.setNomeMaterial(c.getString(c.getColumnIndex("nomeMaterial")));
            material.setValor(c.getDouble(c.getColumnIndex("valor")));
            material.setFinalidade(c.getString(c.getColumnIndex("finalidade")));
            material.setGarantia(c.getString(c.getColumnIndex("garantia")));
            material.setLoja(c.getString(c.getColumnIndex("loja")));

            DateFormat df = new SimpleDateFormat("MM/dd/yyyy");
            Date date = null;
            try {
                date = df.parse(c.getString(c.getColumnIndex("data_compra")));
            } catch (ParseException e) {
                e.printStackTrace();
            }
            Date d2 = date;

            material.setData_compra(date);
            material.setValidade(c.getString(c.getColumnIndex("validade")));
            material.setTipo(c.getString(c.getColumnIndex("tipo")));
            material.setPeso(c.getDouble(c.getColumnIndex("peso")));
            material.setTamanho(c.getDouble(c.getColumnIndex("tamanho")));
            material.setQuantidadeMaterial(c.getInt(c.getColumnIndex("quantidadeMaterial")));
            materiais.add(material);
        }
        c.close();
        return materiais;
    }

    public void deleta(Material material) {
        SQLiteDatabase db = getWritableDatabase();
        String[] params = {material.getId()+ ""};
        db.delete("Materiais", "id = ?", params);
    }

    public void altera(Material material) {
        SQLiteDatabase db = getWritableDatabase();

        ContentValues dados = pegaDadosDoMaterial(material);

        String[] params = {material.getId()+ ""};
        db.update("Materiais", dados, "id = ?", params);
    }

}
