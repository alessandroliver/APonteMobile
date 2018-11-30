package com.example.alessandro.apontemobile;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.alessandro.apontemobile.modelo.Gr;

import java.lang.reflect.Array;
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
                Date date = df.parse(cursor.getString(8));

                Gr grs = new Gr(cursor.getString(0),cursor.getInt(1),
                        cursor.getDouble(2),cursor.getString(3),cursor.getString(4),
                        cursor.getString(5),cursor.getString(6),cursor.getInt(7),
                        date,cursor.getString(9),cursor.getInt(10),
                        cursor.getString(11), cursor.getInt(12),cursor.getString(13),
                        cursor.getString(14));

                grList.add(grs);
            } while (cursor.moveToNext());
        }
        cursor.close();

        return grList;
    }

    public Gr getGr(String nomeGR) throws ParseException {
        Gr gr = null;
        Cursor cursor = loadItens();
        if (cursor.moveToFirst()){
            do{

                if (cursor.getString(0).equals(nomeGR)) {

                    DateFormat df = new SimpleDateFormat("MM/dd/yyyy");
                    Date date = df.parse(cursor.getString(8));

                    gr = new Gr(cursor.getString(0), cursor.getInt(1),
                            cursor.getDouble(2), cursor.getString(3), cursor.getString(4),
                            cursor.getString(5), cursor.getString(6), cursor.getInt(7),
                            date, cursor.getString(9), cursor.getInt(10),
                            cursor.getString(11), cursor.getInt(12), cursor.getString(13),
                            cursor.getString(14));

                }
            } while (cursor.moveToNext());
        }
        cursor.close();

        return gr;
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

        int update = db.update(TABLE_GR, values, GrDBOpenHelper.NOME_GR + " = ?",
                new String[]{String.valueOf(gr.getNomeGr())});
        db.close();
        return update;

    }

}
