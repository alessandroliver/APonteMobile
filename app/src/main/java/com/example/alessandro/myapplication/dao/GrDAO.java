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

public class GrDAO extends SQLiteOpenHelper {

    public GrDAO(Context context) {
        super(context, "Gr", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String sql = "CREATE TABLE Grs (id INTEGER PRIMARY KEY, nomeGr TEXT NOT NULL, quantidade INTEGER, horario REAL, dia TEXT, frequencia TEXT, lider TEXT, apoio TEXT, contato INTEGER, inauguracao TEXT, logradouro TEXT, numero INTEGER, bairro TEXT, cep INTEGER, cidade TEXT, uf TEXT)";
        String sql1 = "CREATE TABLE Membros_Gr (nomeGrMembro TEXT, nome TEXT, naturalidade TEXT, celular INTEGER, nascimento TEXT, sexo TEXT, altura REAL, logradouro TEXT, numero INTEGER, bairro TEXT, cep TEXT, cidade TEXT, uf TEXT, id INTEGER PRIMARY KEY, data_conversao TEXT, equipe TEXT, tempo_ponte TEXT, cargo TEXT, gr TEXT, colete TEXT)";

        db.execSQL(sql);
        db.execSQL(sql1);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        String sql = "DROP TABLE IF EXISTS Grs";
        String sql1 = "DROP TABLE IF EXISTS Membros_Gr";
        db.execSQL(sql);
        db.execSQL(sql1);
        onCreate(db);
    }

    public void insere(Gr gr){
        SQLiteDatabase db = getWritableDatabase();

        ContentValues dados = pegaDadosDoGr(gr);

        db.insert("Grs", null, dados);

    }

    public void insereMembroGr(Membro membro, String nomeGr){
        SQLiteDatabase db = getWritableDatabase();

        ContentValues dados = pegaDadosMembroGr(membro, nomeGr);

        db.insert("Membros_Gr", null, dados);

    }

    private ContentValues pegaDadosDoGr(Gr gr){
        ContentValues dados = new ContentValues();
        dados.put("nomeGr", gr.getNomeGr());
        dados.put("quantidade", gr.getQuantidade());
        dados.put("horario", gr.getHorario());
        dados.put("dia", gr.getDia());
        dados.put("frequencia", gr.getFrequencia());
        dados.put("lider", gr.getLider());
        dados.put("apoio", gr.getApoio());
        dados.put("contato", gr.getContato());
        DateFormat df = new SimpleDateFormat("MM/dd/yyyy");
        Date d1 = gr.getInauguracao();
        String reportDate = df.format(gr.getInauguracao());
        String h =reportDate;
        dados.put("inauguracao", reportDate);
        dados.put("logradouro", gr.getLogradouro());
        dados.put("numero", gr.getNumero());
        dados.put("bairro", gr.getBairro());
        dados.put("cep", gr.getCep());
        dados.put("cidade", gr.getCidade());
        dados.put("uf", gr.getUf());
        return dados;
    }

    private ContentValues pegaDadosMembroGr(Membro membro, String nomeGr){
        ContentValues dados = new ContentValues();
        dados.put("nomeGrMembro", nomeGr);
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

    public List<Gr> buscaGrs(){
        String sql = "SELECT * from Grs;";
        SQLiteDatabase db = getReadableDatabase();
        Cursor c = db.rawQuery(sql, null);

        List<Gr> grs = new ArrayList<>();
        while(c.moveToNext()){
            Gr gr = new Gr();
            gr.setId(c.getLong(c.getColumnIndex("id")));
            gr.setNomeGr(c.getString(c.getColumnIndex("nomeGr")));
            gr.setQuantidade(c.getInt(c.getColumnIndex("quantidade")));
            gr.setHorario(c.getDouble(c.getColumnIndex("horario")));
            gr.setDia(c.getString(c.getColumnIndex("dia")));
            gr.setFrequencia(c.getString(c.getColumnIndex("frequencia")));
            gr.setLider(c.getString(c.getColumnIndex("lider")));
            gr.setApoio(c.getString(c.getColumnIndex("apoio")));
            gr.setContato(c.getInt(c.getColumnIndex("contato")));

            DateFormat df = new SimpleDateFormat("MM/dd/yyyy");
            Date date = null;
            try {
                date = df.parse(c.getString(c.getColumnIndex("inauguracao")));
            } catch (ParseException e) {
                e.printStackTrace();
            }
            Date d2 = date;

            gr.setInauguracao(date);
            gr.setLogradouro(c.getString(c.getColumnIndex("logradouro")));
            gr.setNumero(c.getInt(c.getColumnIndex("numero")));
            gr.setBairro(c.getString(c.getColumnIndex("bairro")));
            gr.setCep(c.getInt(c.getColumnIndex("cep")));
            gr.setCidade(c.getString(c.getColumnIndex("cidade")));
            gr.setUf(c.getString(c.getColumnIndex("uf")));
            gr.setMembros(null);
            grs.add(gr);
        }
        c.close();
        return grs;
    }

    public List<Membro> buscaMembroGr(String nomeGr) {
        String sql = "SELECT * from Membros_Gr;";
        SQLiteDatabase db = getReadableDatabase();
        Cursor c = db.rawQuery(sql, null);

        List<Membro> membros = new ArrayList<>();
        while(c.moveToNext()){
            Membro membro = new Membro();
            if(c.getString(c.getColumnIndex("nomeGrMembro")).equals(nomeGr)) {

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

                /*GrDAO grDAO = new GrDAO(context);
                List<Gr> grs = grDAO.buscaGrs();
                Gr gr1 = null;
                for (Gr gr:grs) {
                    if (gr.getNomeGr().equals(c.getString(c.getColumnIndex("gr")))){
                        gr1 = gr;
                    }
                }*/
                membro.setGr(null);
                membro.setColete(c.getString(c.getColumnIndex("colete")));
                membros.add(membro);
            }
        }
        c.close();
        return membros;
    }

    public void deleta(Gr gr) {
        SQLiteDatabase db = getWritableDatabase();
        String[] params = {gr.getId()+ ""};
        db.delete("Grs", "id = ?", params);
    }

    public void deletaMembro(Membro membro) {
        SQLiteDatabase db = getWritableDatabase();
        String[] params = {membro.getId()+ ""};
        db.delete("Membros_Gr", "id = ?", params);
    }

    public void altera(Gr gr) {
        SQLiteDatabase db = getWritableDatabase();

        ContentValues dados = pegaDadosDoGr(gr);

        String[] params = {gr.getId()+ ""};
        db.update("Grs", dados, "id = ?", params);
    }

    public void alteraMembro(Membro membro, String grMembro) {
        SQLiteDatabase db = getWritableDatabase();

        ContentValues dados = pegaDadosMembroGr(membro, grMembro);

        String[] params = {membro.getId()+ ""};
        db.update("Membros_Gr", dados, "id = ?", params);
    }

}
