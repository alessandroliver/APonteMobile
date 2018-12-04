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

import com.example.alessandro.myapplication.modelo.Gr;
import com.example.alessandro.myapplication.modelo.Membro;

import com.example.alessandro.myapplication.ListaMembroActivity;

public class MembroDAO extends SQLiteOpenHelper {
    private Context context;
    public MembroDAO(Context context) {
        super(context, "Membro", null, 1);
        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String sql = "CREATE TABLE Membros (nome TEXT, naturalidade TEXT, celular INTEGER, nascimento TEXT, sexo TEXT, altura REAL, logradouro TEXT, numero INTEGER, bairro TEXT, cep TEXT, cidade TEXT, uf TEXT, id INTEGER PRIMARY KEY, data_conversao TEXT, equipe TEXT, tempo_ponte TEXT, cargo TEXT, gr TEXT, colete TEXT)";
        db.execSQL(sql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        String sql = "DROP TABLE IF EXISTS Membros";
        db.execSQL(sql);
        onCreate(db);
    }

    public void insere(Membro membro) {
        SQLiteDatabase db = getWritableDatabase();

        ContentValues dados = pegaDadosDoMembro(membro);

        db.insert("Membros", null, dados);
    }

    @NonNull
    private ContentValues pegaDadosDoMembro(Membro membro) {
        ContentValues dados = new ContentValues();
        dados.put("nome", membro.getNome());
        dados.put("naturalidade", membro.getNaturalidade());
        dados.put("celular", membro.getCelular());

        DateFormat df = new SimpleDateFormat("MM/dd/yyyy");
        Date d1 = membro.getNascimento();
        String reportDate = df.format(membro.getNascimento());
        String h =reportDate;
        dados.put("nascimento", reportDate);

        dados.put("sexo", membro.getSexo());
        dados.put("altura", membro.getAltura());
        dados.put("logradouro", membro.getLogradouro());
        dados.put("numero", membro.getNumero());
        dados.put("bairro", membro.getBairro());
        dados.put("cep", membro.getCep());
        dados.put("cidade", membro.getCidade());
        dados.put("uf", membro.getUf());

        DateFormat dft = new SimpleDateFormat("MM/dd/yyyy");
        Date d1t = membro.getNascimento();
        String reportDat = dft.format(membro.getData_conversao());
        String h1 =reportDat;
        dados.put("data_conversao", reportDat);

        dados.put("equipe", membro.getEquipe());
        dados.put("tempo_ponte", membro.getTempo_ponte());
        dados.put("cargo", membro.getCargo());
        dados.put("gr", membro.getGr().getNomeGr());
        dados.put("colete", membro.getColete());
        return dados;
    }

    public List<Membro> buscaMembro() {
        String sql = "SELECT * from Membros;";
        SQLiteDatabase db = getReadableDatabase();
        Cursor c = db.rawQuery(sql, null);

        List<Membro> membros = new ArrayList<>();
        while(c.moveToNext()){
            Membro membro = new Membro();
            membro.setNome(c.getString(c.getColumnIndex("nome")));
            membro.setNaturalidade(c.getString(c.getColumnIndex("naturalidade")));
            membro.setCelular(c.getInt(c.getColumnIndex("celular")));

            DateFormat df = new SimpleDateFormat("MM/dd/yyyy");
            Date date = null;
            try {
                date = df.parse(c.getString(c.getColumnIndex("nascimento")));
            } catch (ParseException e) {
                e.printStackTrace();
            }
            Date d2 = date;
            membro.setNascimento(date);
            Date d3 = membro.getNascimento();
            membro.setSexo(c.getString(c.getColumnIndex("sexo")));
            membro.setAltura(c.getDouble(c.getColumnIndex("altura")));
            membro.setLogradouro(c.getString(c.getColumnIndex("logradouro")));
            membro.setNumero(c.getInt(c.getColumnIndex("numero")));
            membro.setBairro(c.getString(c.getColumnIndex("bairro")));
            membro.setCep(c.getString(c.getColumnIndex("cep")));
            membro.setCidade(c.getString(c.getColumnIndex("cidade")));
            membro.setUf(c.getString(c.getColumnIndex("uf")));
            membro.setId(c.getLong(c.getColumnIndex("id")));

            DateFormat dft = new SimpleDateFormat("MM/dd/yyyy");
            Date dat = null;
            try {
                dat = dft.parse(c.getString(c.getColumnIndex("data_conversao")));
            } catch (ParseException e) {
                e.printStackTrace();
            }
            Date d4 = dat;
            membro.setData_conversao(dat);
            Date d5 = membro.getData_conversao();
            membro.setEquipe(c.getString(c.getColumnIndex("equipe")));
            membro.setTempo_ponte(c.getString(c.getColumnIndex("tempo_ponte")));
            membro.setCargo(c.getString(c.getColumnIndex("cargo")));

            GrDAO grDAO = new GrDAO(context);
            List<Gr> grs = grDAO.buscaGrs();
            Gr gr1 = null;
            for (Gr gr:grs) {
                if (gr.getNomeGr().equals(c.getString(c.getColumnIndex("gr")))){
                    gr1 = gr;
                }
            }
            membro.setGr(gr1);
            membro.setColete(c.getString(c.getColumnIndex("colete")));

            membros.add(membro);
        }
        c.close();
        return membros;
    }

    public void deleta(Membro membro) {
        SQLiteDatabase db = getWritableDatabase();
        String[] params = {membro.getId()+ ""};
        db.delete("Membros", "id = ?", params);
    }

    public void altera(Membro membro) {
        SQLiteDatabase db = getWritableDatabase();

        ContentValues dados = pegaDadosDoMembro(membro);

        String[] params = {membro.getId()+ ""};
        db.update("Membros", dados, "id = ?", params);
    }

}