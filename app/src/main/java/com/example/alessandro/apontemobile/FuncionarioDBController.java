package com.example.alessandro.apontemobile;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.alessandro.apontemobile.modelo.Funcionario;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static com.example.alessandro.apontemobile.FuncionarioDBOpenHelper.TABLE_FUNCIONARIO;

public class FuncionarioDBController {

    private SQLiteDatabase db;
    private FuncionarioDBOpenHelper funcionarioDB;

    public FuncionarioDBController(Context context){
        funcionarioDB = new FuncionarioDBOpenHelper(context);
    }

    public List<Funcionario> getAllFuncionario() throws ParseException {
        List<Funcionario> funcionarioList = new ArrayList<Funcionario>();
        Cursor cursor = loadItens();
        if (cursor.moveToFirst()){
            do{

                DateFormat df = new SimpleDateFormat("MM/dd/yyyy");
                Date date = df.parse(cursor.getString(3));

                DateFormat dft = new SimpleDateFormat("MM/dd/yyyy");
                Date dat = dft.parse(cursor.getString(24));

                DateFormat dfo = new SimpleDateFormat("MM/dd/yyyy");
                Date dte = dfo.parse(cursor.getString(25));

                Funcionario func = new Funcionario(cursor.getString(0),cursor.getString(1),
                        cursor.getInt(2),date,cursor.getString(4),cursor.getDouble(5),
                        cursor.getString(6),cursor.getInt(7),cursor.getString(8),
                        cursor.getString(9),cursor.getString(10),cursor.getString(11),
                        cursor.getInt(12),cursor.getString(13),cursor.getDouble(14),
                        cursor.getInt(15),cursor.getInt(16),cursor.getInt(17),
                        cursor.getInt(18),cursor.getInt(19),cursor.getInt(20),
                        cursor.getString(21),cursor.getInt(22),cursor.getString(23),
                        dat,dte);

                funcionarioList.add(func);
            } while (cursor.moveToNext());
        }
        cursor.close();

        return funcionarioList;
    }

    public Cursor loadItens(){
        Cursor cursor;
        db = funcionarioDB.getReadableDatabase();
        cursor = db.query(TABLE_FUNCIONARIO, funcionarioDB.COLUNA, null, null, null,
                null, null, null);

        if(cursor!=null && cursor.getCount() > 0){
            cursor.moveToFirst();
        }
        db.close();
        return cursor;
    }

    public String insert(Funcionario funcionario){
        long check =0;
        SQLiteDatabase db = funcionarioDB.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put(FuncionarioDBOpenHelper.NOME, funcionario.getNome());
        values.put(FuncionarioDBOpenHelper.NATURALIDADE, funcionario.getNaturalidade());
        values.put(FuncionarioDBOpenHelper.CELULAR, funcionario.getCelular());

        DateFormat df = new SimpleDateFormat("MM/dd/yyyy");
        String reportDate = df.format(funcionario.getNascimento());

        values.put(FuncionarioDBOpenHelper.NASCIMENTO, reportDate);

        values.put(FuncionarioDBOpenHelper.SEXO, funcionario.getSexo());
        values.put(FuncionarioDBOpenHelper.ALTURA, funcionario.getAltura());
        values.put(FuncionarioDBOpenHelper.LOGRADOURO, funcionario.getLogradouro());
        values.put(FuncionarioDBOpenHelper.NUMERO, funcionario.getNumero());
        values.put(FuncionarioDBOpenHelper.BAIRRO, funcionario.getBairro());
        values.put(FuncionarioDBOpenHelper.CEP, funcionario.getCep());
        values.put(FuncionarioDBOpenHelper.CIDADE, funcionario.getCidade());
        values.put(FuncionarioDBOpenHelper.UF, funcionario.getUf());
        values.put(FuncionarioDBOpenHelper._ID, funcionario.getId());
        values.put(FuncionarioDBOpenHelper.CARGO, funcionario.getCargo());
        values.put(FuncionarioDBOpenHelper.SALARIO, funcionario.getSalario());
        values.put(FuncionarioDBOpenHelper.HORA_SEMANAL, funcionario.getHora_semanal());
        values.put(FuncionarioDBOpenHelper.CARTEIRA_TRABALHO, funcionario.getCarteira_trabalho());
        values.put(FuncionarioDBOpenHelper.PIS, funcionario.getPis());
        values.put(FuncionarioDBOpenHelper.CPF, funcionario.getCpf());
        values.put(FuncionarioDBOpenHelper.RG, funcionario.getRg());
        values.put(FuncionarioDBOpenHelper.CONTA, funcionario.getConta());
        values.put(FuncionarioDBOpenHelper.BANCO, funcionario.getBanco());
        values.put(FuncionarioDBOpenHelper.AGENCIA, funcionario.getAgencia());
        values.put(FuncionarioDBOpenHelper.CAMISA, funcionario.getCamisa());

        DateFormat dft = new SimpleDateFormat("MM/dd/yyyy");
        String reportDat = dft.format(funcionario.getEntrada());

        values.put(FuncionarioDBOpenHelper.ENTRADA, reportDat);

        DateFormat dfm = new SimpleDateFormat("MM/dd/yyyy");
        String reportDte = dfm.format(funcionario.getPagamento());

        values.put(FuncionarioDBOpenHelper.PAGAMENTO, reportDte);

        check = db.insert(TABLE_FUNCIONARIO, null, values);


        db.close();

        if(check == -1){
            return "Error";
        } else{
            return "DB created";
        }
    }

    public void clearAll(){

        funcionarioDB.getWritableDatabase().delete(TABLE_FUNCIONARIO,null,null);

    }

    public int updateFuncionario(Funcionario funcionario){
        SQLiteDatabase db = funcionarioDB.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put(FuncionarioDBOpenHelper.NOME, funcionario.getNome());
        values.put(FuncionarioDBOpenHelper.NATURALIDADE, funcionario.getNaturalidade());
        values.put(FuncionarioDBOpenHelper.CELULAR, funcionario.getCelular());

        DateFormat df = new SimpleDateFormat("MM/dd/yyyy");
        String reportDate = df.format(funcionario.getNascimento());

        values.put(FuncionarioDBOpenHelper.NASCIMENTO, reportDate);

        values.put(FuncionarioDBOpenHelper.SEXO, funcionario.getSexo());
        values.put(FuncionarioDBOpenHelper.ALTURA, funcionario.getAltura());
        values.put(FuncionarioDBOpenHelper.LOGRADOURO, funcionario.getLogradouro());
        values.put(FuncionarioDBOpenHelper.NUMERO, funcionario.getNumero());
        values.put(FuncionarioDBOpenHelper.BAIRRO, funcionario.getBairro());
        values.put(FuncionarioDBOpenHelper.CEP, funcionario.getCep());
        values.put(FuncionarioDBOpenHelper.CIDADE, funcionario.getCidade());
        values.put(FuncionarioDBOpenHelper.UF, funcionario.getUf());
        values.put(FuncionarioDBOpenHelper._ID, funcionario.getId());
        values.put(FuncionarioDBOpenHelper.CARGO, funcionario.getCargo());
        values.put(FuncionarioDBOpenHelper.SALARIO, funcionario.getSalario());
        values.put(FuncionarioDBOpenHelper.HORA_SEMANAL, funcionario.getHora_semanal());
        values.put(FuncionarioDBOpenHelper.CARTEIRA_TRABALHO, funcionario.getCarteira_trabalho());
        values.put(FuncionarioDBOpenHelper.PIS, funcionario.getPis());
        values.put(FuncionarioDBOpenHelper.CPF, funcionario.getCpf());
        values.put(FuncionarioDBOpenHelper.RG, funcionario.getRg());
        values.put(FuncionarioDBOpenHelper.CONTA, funcionario.getConta());
        values.put(FuncionarioDBOpenHelper.BANCO, funcionario.getBanco());
        values.put(FuncionarioDBOpenHelper.AGENCIA, funcionario.getAgencia());
        values.put(FuncionarioDBOpenHelper.CAMISA, funcionario.getCamisa());

        DateFormat dft = new SimpleDateFormat("MM/dd/yyyy");
        String reportDat = dft.format(funcionario.getEntrada());

        values.put(FuncionarioDBOpenHelper.ENTRADA, reportDat);

        DateFormat dfm = new SimpleDateFormat("MM/dd/yyyy");
        String reportDte = dfm.format(funcionario.getPagamento());

        values.put(FuncionarioDBOpenHelper.PAGAMENTO, reportDte);

        int update = db.update(TABLE_FUNCIONARIO, values, FuncionarioDBOpenHelper._ID + " = ?",
                new String[]{String.valueOf(funcionario.getId())});
        db.close();
        return update;

    }

}
