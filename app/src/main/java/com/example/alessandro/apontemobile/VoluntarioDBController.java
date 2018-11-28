package com.example.alessandro.apontemobile;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.alessandro.apontemobile.modelo.Voluntario;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static com.example.alessandro.apontemobile.VoluntarioDBOpenHelper.TABLE_VOLUNTARIO;

public class VoluntarioDBController {

    private SQLiteDatabase db;
    private VoluntarioDBOpenHelper voluntarioDB;

    public VoluntarioDBController(Context context){
        voluntarioDB = new VoluntarioDBOpenHelper(context);
    }

    public List<Voluntario> getAllVoluntario() throws ParseException {
        List<Voluntario> voluntarioList = new ArrayList<Voluntario>();
        Cursor cursor = loadItens();
        if (cursor.moveToFirst()){
            do{

                DateFormat df = new SimpleDateFormat("MM/dd/yyyy");
                Date date = df.parse(cursor.getString(3));

                Voluntario vol = new Voluntario(cursor.getString(0),cursor.getString(1),
                        cursor.getInt(2),date,cursor.getString(4),cursor.getDouble(5),
                        cursor.getString(6),cursor.getInt(7),cursor.getString(8),
                        cursor.getString(9),cursor.getString(10),cursor.getString(11),
                        cursor.getString(12),cursor.getDouble(13),cursor.getDouble(14),
                        cursor.getInt(15),cursor.getString(16),cursor.getInt(17),
                        cursor.getInt(18),cursor.getString(19));

                voluntarioList.add(vol);
            } while (cursor.moveToNext());
        }
        cursor.close();

        return voluntarioList;
    }

    public Cursor loadItens(){
        Cursor cursor;
        db = voluntarioDB.getReadableDatabase();
        cursor = db.query(TABLE_VOLUNTARIO, voluntarioDB.COLUNA, null, null, null,
                null, null, null);

        if(cursor!=null && cursor.getCount() > 0){
            cursor.moveToFirst();
        }
        db.close();
        return cursor;
    }

    public String insert(Voluntario voluntario){
        long check =0;
        SQLiteDatabase db = voluntarioDB.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put(VoluntarioDBOpenHelper.NOME, voluntario.getNome());
        values.put(VoluntarioDBOpenHelper.NATURALIDADE, voluntario.getNaturalidade());
        values.put(VoluntarioDBOpenHelper.CELULAR, voluntario.getCelular());

        DateFormat df = new SimpleDateFormat("MM/dd/yyyy");
        String reportDate = df.format(voluntario.getNascimento());

        values.put(VoluntarioDBOpenHelper.NASCIMENTO, reportDate);

        values.put(VoluntarioDBOpenHelper.SEXO, voluntario.getSexo());
        values.put(VoluntarioDBOpenHelper.ALTURA, voluntario.getAltura());
        values.put(VoluntarioDBOpenHelper.LOGRADOURO, voluntario.getLogradouro());
        values.put(VoluntarioDBOpenHelper.NUMERO, voluntario.getNumero());
        values.put(VoluntarioDBOpenHelper.BAIRRO, voluntario.getBairro());
        values.put(VoluntarioDBOpenHelper.CEP, voluntario.getCep());
        values.put(VoluntarioDBOpenHelper.CIDADE, voluntario.getCidade());
        values.put(VoluntarioDBOpenHelper.UF, voluntario.getUf());
        values.put(VoluntarioDBOpenHelper.FUNCAO, voluntario.getFuncao());
        values.put(VoluntarioDBOpenHelper.HORARIO_PEGAR, voluntario.getHorario_pegar());
        values.put(VoluntarioDBOpenHelper.HORARIO_LARGAR, voluntario.getHorario_largar());
        values.put(VoluntarioDBOpenHelper.HORA_SEMANAL, voluntario.getHora_semanal());
        values.put(VoluntarioDBOpenHelper.TAMANHO_CAMISA, voluntario.getTamanho_camisa());
        values.put(VoluntarioDBOpenHelper.CPF, voluntario.getCpf());
        values.put(VoluntarioDBOpenHelper.RG, voluntario.getRg());
        values.put(VoluntarioDBOpenHelper.AREA, voluntario.getArea());

        check = db.insert(TABLE_VOLUNTARIO, null, values);


        db.close();

        if(check == -1){
            return "Error";
        } else{
            return "DB created";
        }
    }

    public void clearAll(){

        voluntarioDB.getWritableDatabase().delete(TABLE_VOLUNTARIO,null,null);

    }

    public int updateVoluntario(Voluntario voluntario){
        SQLiteDatabase db = voluntarioDB.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put(VoluntarioDBOpenHelper.NOME, voluntario.getNome());
        values.put(VoluntarioDBOpenHelper.NATURALIDADE, voluntario.getNaturalidade());
        values.put(VoluntarioDBOpenHelper.CELULAR, voluntario.getCelular());

        DateFormat df = new SimpleDateFormat("MM/dd/yyyy");
        String reportDate = df.format(voluntario.getNascimento());

        values.put(VoluntarioDBOpenHelper.NASCIMENTO, reportDate);

        values.put(VoluntarioDBOpenHelper.SEXO, voluntario.getSexo());
        values.put(VoluntarioDBOpenHelper.ALTURA, voluntario.getAltura());
        values.put(VoluntarioDBOpenHelper.LOGRADOURO, voluntario.getLogradouro());
        values.put(VoluntarioDBOpenHelper.NUMERO, voluntario.getNumero());
        values.put(VoluntarioDBOpenHelper.BAIRRO, voluntario.getBairro());
        values.put(VoluntarioDBOpenHelper.CEP, voluntario.getCep());
        values.put(VoluntarioDBOpenHelper.CIDADE, voluntario.getCidade());
        values.put(VoluntarioDBOpenHelper.UF, voluntario.getUf());
        values.put(VoluntarioDBOpenHelper.FUNCAO, voluntario.getFuncao());
        values.put(VoluntarioDBOpenHelper.HORARIO_PEGAR, voluntario.getHorario_pegar());
        values.put(VoluntarioDBOpenHelper.HORARIO_LARGAR, voluntario.getHorario_largar());
        values.put(VoluntarioDBOpenHelper.HORA_SEMANAL, voluntario.getHora_semanal());
        values.put(VoluntarioDBOpenHelper.TAMANHO_CAMISA, voluntario.getTamanho_camisa());
        values.put(VoluntarioDBOpenHelper.CPF, voluntario.getCpf());
        values.put(VoluntarioDBOpenHelper.RG, voluntario.getRg());
        values.put(VoluntarioDBOpenHelper.AREA, voluntario.getArea());

        int update = db.update(TABLE_VOLUNTARIO, values, VoluntarioDBOpenHelper.NOME + " = ?",
                new String[]{String.valueOf(voluntario.getNome())});
        db.close();
        return update;

    }

}
