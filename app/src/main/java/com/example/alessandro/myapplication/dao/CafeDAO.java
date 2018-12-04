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

import com.example.alessandro.myapplication.modelo.Cafe;

import com.example.alessandro.myapplication.ListaCafeActivity;

public class CafeDAO extends SQLiteOpenHelper {

    public CafeDAO(Context context) {
        super(context, "Cafe", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String sql = "CREATE TABLE Cafes (nomeProduto TEXT, valor_compra REAL, valor_venda REAL, quantidade INTEGER, loja TEXT, local_fabricacao TEXT, data_compra TEXT, marca TEXT, id INTEGER PRIMARY KEY, sabor TEXT, temperatura TEXT, ingrediente TEXT, validade TEXT, peso REAL)";
        db.execSQL(sql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        String sql = "DROP TABLE IF EXISTS Cafes";
        db.execSQL(sql);
        onCreate(db);
    }

    public void insere(Cafe cafe) {
        SQLiteDatabase db = getWritableDatabase();

        ContentValues dados = pegaDadosDoCafe(cafe);

        db.insert("Cafes", null, dados);
    }

    @NonNull
    private ContentValues pegaDadosDoCafe(Cafe cafe) {
        ContentValues dados = new ContentValues();
        dados.put("nomeProduto", cafe.getNomeProduto());
        dados.put("valor_compra", cafe.getValor_compra());
        dados.put("valor_venda", cafe.getValor_venda());
        dados.put("quantidade", cafe.getQuantidade());
        dados.put("loja", cafe.getLoja());
        dados.put("local_fabricacao", cafe.getLocal_fabricacao());

        DateFormat df = new SimpleDateFormat("MM/dd/yyyy");
        Date d1 = cafe.getData_compra();
        String reportDate = df.format(cafe.getData_compra());
        String h =reportDate;
        dados.put("data_compra", reportDate);

        dados.put("marca", cafe.getMarca());
        dados.put("sabor", cafe.getSabor());
        dados.put("temperatura", cafe.getTemperatura());
        dados.put("ingrediente", cafe.getIngrediente());
        dados.put("validade", cafe.getValidade());
        dados.put("peso", cafe.getPeso());
        return dados;
    }

    public List<Cafe> buscaCafe() {
        String sql = "SELECT * from Cafes;";
        SQLiteDatabase db = getReadableDatabase();
        Cursor c = db.rawQuery(sql, null);

        List<Cafe> cafes = new ArrayList<>();
        while(c.moveToNext()){
            Cafe cafe = new Cafe();
            cafe.setId(c.getLong(c.getColumnIndex("id")));
            cafe.setNomeProduto(c.getString(c.getColumnIndex("nomeProduto")));
            cafe.setValor_compra(c.getDouble(c.getColumnIndex("valor_compra")));
            cafe.setValor_venda(c.getDouble(c.getColumnIndex("valor_venda")));
            cafe.setQuantidade(c.getInt(c.getColumnIndex("quantidade")));
            cafe.setLoja(c.getString(c.getColumnIndex("loja")));
            cafe.setLocal_fabricacao(c.getString(c.getColumnIndex("local_fabricacao")));

            DateFormat df = new SimpleDateFormat("MM/dd/yyyy");
            Date date = null;
            try {
                date = df.parse(c.getString(c.getColumnIndex("data_compra")));
            } catch (ParseException e) {
                e.printStackTrace();
            }
            Date d2 = date;
            cafe.setData_compra(date);
            Date d3 = cafe.getData_compra();
            cafe.setMarca(c.getString(c.getColumnIndex("marca")));
            cafe.setSabor(c.getString(c.getColumnIndex("sabor")));
            cafe.setTemperatura(c.getString(c.getColumnIndex("temperatura")));
            cafe.setIngrediente(c.getString(c.getColumnIndex("ingrediente")));
            cafe.setValidade(c.getString(c.getColumnIndex("validade")));
            cafe.setPeso(c.getDouble(c.getColumnIndex("peso")));
            cafes.add(cafe);
        }
        c.close();
        return cafes;
    }

    public void deleta(Cafe cafe) {
        SQLiteDatabase db = getWritableDatabase();
        String[] params = {cafe.getId()+ ""};
        db.delete("Cafes", "id = ?", params);
    }

    public void altera(Cafe cafe) {
        SQLiteDatabase db = getWritableDatabase();

        ContentValues dados = pegaDadosDoCafe(cafe);

        String[] params = {cafe.getId()+ ""};
        db.update("Cafes", dados, "id = ?", params);
    }

}
