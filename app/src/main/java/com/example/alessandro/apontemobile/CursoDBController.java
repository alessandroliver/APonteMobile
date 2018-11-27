package com.example.alessandro.apontemobile;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.alessandro.apontemobile.modelo.Curso;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static com.example.alessandro.apontemobile.CursoDBOpenHelper.TABLE_CURSO;

public class CursoDBController {

    private SQLiteDatabase db;
    private CursoDBOpenHelper cursoDB;

    public CursoDBController(Context context){
        cursoDB = new CursoDBOpenHelper(context);
    }

    public List<Curso> getAllCurso() throws ParseException {
        List<Curso> cursoList = new ArrayList<Curso>();
        Cursor cursor = loadItens();
        if (cursor.moveToFirst()){
            do{

                DateFormat df = new SimpleDateFormat("MM/dd/yyyy");
                Date date = df.parse(cursor.getString(2));

                DateFormat dft = new SimpleDateFormat("MM/dd/yyyy");
                Date dat = dft.parse(cursor.getString(3));

                Curso cur = new Curso(cursor.getInt(0),cursor.getString(1),date,dat,
                        cursor.getInt(4),cursor.getString(5),cursor.getString(6),
                        cursor.getDouble(7),cursor.getDouble(8),cursor.getString(9));

                cursoList.add(cur);
            } while (cursor.moveToNext());
        }
        cursor.close();

        return cursoList;
    }

    public Cursor loadItens(){
        Cursor cursor;
        db = cursoDB.getReadableDatabase();
        cursor = db.query(TABLE_CURSO, cursoDB.COLUNA, null, null, null, null,
                null, null);

        if(cursor!=null && cursor.getCount() > 0){
            cursor.moveToFirst();
        }
        db.close();
        return cursor;
    }

    public String insert(Curso curso){
        long check =0;
        SQLiteDatabase db = cursoDB.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put(CursoDBOpenHelper._ID, curso.getId());
        values.put(CursoDBOpenHelper.NOME_CURSO, curso.getNomeCurso());

        DateFormat df = new SimpleDateFormat("MM/dd/yyyy");
        String reportDate = df.format(curso.getInicio());

        values.put(CursoDBOpenHelper.INICIO, reportDate);

        DateFormat dft = new SimpleDateFormat("MM/dd/yyyy");
        String reportDat = dft.format(curso.getFim());

        values.put(CursoDBOpenHelper.FIM, reportDat);

        values.put(CursoDBOpenHelper.CARGA_HORARIA, curso.getCarga_horaria());
        values.put(CursoDBOpenHelper.PROFESSOR, curso.getProfessor());
        values.put(CursoDBOpenHelper.DIA, curso.getDia());
        values.put(CursoDBOpenHelper.PEGAR, curso.getPegar());
        values.put(CursoDBOpenHelper.LARGAR, curso.getLargar());
        values.put(CursoDBOpenHelper.SALA, curso.getSala());

        check = db.insert(TABLE_CURSO, null, values);


        db.close();

        if(check == -1){
            return "Error";
        } else{
            return "DB created";
        }
    }

    public void clearAll(){

        cursoDB.getWritableDatabase().delete(TABLE_CURSO,null,null);

    }

    public int updateCurso(Curso curso){
        SQLiteDatabase db = cursoDB.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put(CursoDBOpenHelper._ID, curso.getId());
        values.put(CursoDBOpenHelper.NOME_CURSO, curso.getNomeCurso());

        DateFormat df = new SimpleDateFormat("MM/dd/yyyy");
        String reportDate = df.format(curso.getInicio());

        values.put(CursoDBOpenHelper.INICIO, reportDate);

        DateFormat dft = new SimpleDateFormat("MM/dd/yyyy");
        String reportDat = dft.format(curso.getFim());

        values.put(CursoDBOpenHelper.FIM, reportDat);

        values.put(CursoDBOpenHelper.CARGA_HORARIA, curso.getCarga_horaria());
        values.put(CursoDBOpenHelper.PROFESSOR, curso.getProfessor());
        values.put(CursoDBOpenHelper.DIA, curso.getDia());
        values.put(CursoDBOpenHelper.PEGAR, curso.getPegar());
        values.put(CursoDBOpenHelper.LARGAR, curso.getLargar());
        values.put(CursoDBOpenHelper.SALA, curso.getSala());

        int update = db.update(TABLE_CURSO, values, CursoDBOpenHelper._ID + " = ?",
                new String[]{String.valueOf(curso.getId())});
        db.close();
        return update;

    }

}
