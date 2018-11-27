package com.example.alessandro.apontemobile;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.alessandro.apontemobile.modelo.Gr;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static com.example.alessandro.apontemobile.GrDBOpenHelper.TABLE_GR;

public class GrDBController {

    private SQLiteDatabase db;
    private GrDBOpenHelper grDB;

    public GrDBController(Context context){
        grDB = new GrDBOpenHelper(context);
    }

    public List<Gr> getAllGr() throws ParseException {
        List<Gr> grList = new ArrayList<Gr>();
        Cursor cursor = loadItens();
        if (cursor.moveToFirst()){
            do{

                DateFormat df = new SimpleDateFormat("MM/dd/yyyy");
                Date date = df.parse(cursor.getString(9));

                Gr grs = new Gr(cursor.getInt(0),cursor.getString(1),
                        cursor.getInt(2),cursor.getDouble(3),cursor.getString(4),
                        cursor.getString(5),cursor.getString(6),cursor.getString(7),
                        cursor.getInt(8),date,cursor.getString(10),
                        cursor.getInt(11),cursor.getString(12), cursor.getInt(13),
                        cursor.getString(14),cursor.getString(15));

                grList.add(grs);
            } while (cursor.moveToNext());
        }
        cursor.close();

        return grList;
    }

    public Cursor loadItens(){
        Cursor cursor;
        db = grDB.getReadableDatabase();
        cursor = db.query(TABLE_GR, grDB.COLUNA, null, null, null, null,
                null, null);

        if(cursor!=null && cursor.getCount() > 0){
            cursor.moveToFirst();
        }
        db.close();
        return cursor;
    }

    public String insert(Gr gr){
        long check =0;
        SQLiteDatabase db = grDB.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put(GrDBOpenHelper._ID, gr.getId());
        values.put(GrDBOpenHelper.NOME_GR, gr.getNomeGr());
        values.put(GrDBOpenHelper.QUANTIDADE, gr.getQuantidade());
        values.put(GrDBOpenHelper.HORARIO, gr.getHorario());
        values.put(GrDBOpenHelper.DIA, gr.getDia());
        values.put(GrDBOpenHelper.FREQUENCIA, gr.getFrequencia());
        values.put(GrDBOpenHelper.LIDER, gr.getLider());
        values.put(GrDBOpenHelper.APOIO, gr.getApoio());
        values.put(GrDBOpenHelper.CONTATO, gr.getContato());

        DateFormat df = new SimpleDateFormat("MM/dd/yyyy");
        String reportDate = df.format(gr.getInauguracao());

        values.put(GrDBOpenHelper.INAUGURACAO, reportDate);
        values.put(GrDBOpenHelper.LOGRADOURO, gr.getLogradouro());
        values.put(GrDBOpenHelper.NUMERO, gr.getNumero());
        values.put(GrDBOpenHelper.BAIRRO, gr.getBairro());
        values.put(GrDBOpenHelper.CEP, gr.getCep());
        values.put(GrDBOpenHelper.CIDADE, gr.getCidade());
        values.put(GrDBOpenHelper.UF, gr.getUf());

        check = db.insert(TABLE_GR, null, values);


        db.close();

        if(check == -1){
            return "Error";
        } else{
            return "DB created";
        }
    }

    public void clearAll(){

        grDB.getWritableDatabase().delete(TABLE_GR,null,null);

    }

    public int updateGr(Gr gr){
        SQLiteDatabase db = grDB.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put(GrDBOpenHelper._ID, gr.getId());
        values.put(GrDBOpenHelper.NOME_GR, gr.getNomeGr());
        values.put(GrDBOpenHelper.QUANTIDADE, gr.getQuantidade());
        values.put(GrDBOpenHelper.HORARIO, gr.getHorario());
        values.put(GrDBOpenHelper.DIA, gr.getDia());
        values.put(GrDBOpenHelper.FREQUENCIA, gr.getFrequencia());
        values.put(GrDBOpenHelper.LIDER, gr.getLider());
        values.put(GrDBOpenHelper.APOIO, gr.getApoio());
        values.put(GrDBOpenHelper.CONTATO, gr.getContato());

        DateFormat df = new SimpleDateFormat("MM/dd/yyyy");
        String reportDate = df.format(gr.getInauguracao());

        values.put(GrDBOpenHelper.INAUGURACAO, reportDate);
        values.put(GrDBOpenHelper.LOGRADOURO, gr.getLogradouro());
        values.put(GrDBOpenHelper.NUMERO, gr.getNumero());
        values.put(GrDBOpenHelper.BAIRRO, gr.getBairro());
        values.put(GrDBOpenHelper.CEP, gr.getCep());
        values.put(GrDBOpenHelper.CIDADE, gr.getCidade());
        values.put(GrDBOpenHelper.UF, gr.getUf());

        int update = db.update(TABLE_GR, values, GrDBOpenHelper._ID + " = ?",
                new String[]{String.valueOf(gr.getId())});
        db.close();
        return update;

    }

}
