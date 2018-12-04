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

import com.example.alessandro.myapplication.modelo.Voluntario;

import com.example.alessandro.myapplication.ListaVoluntarioActivity;

public class VoluntarioDAO extends SQLiteOpenHelper {

    public VoluntarioDAO(Context context) {
        super(context, "Voluntario", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String sql = "CREATE TABLE Voluntarios (nome TEXT, naturalidade TEXT, celular INTEGER, nascimento TEXT, sexo TEXT, altura REAL, logradouro TEXT, numero INTEGER, bairro TEXT, cep TEXT, cidade TEXT, uf TEXT, id INTEGER PRIMARY KEY, funcao TEXT, horario_pegar REAL, horario_largar REAL, hora_semanal REAL, tamanho_camisa TEXT, cpf INTEGER, rg INTEGER, area TEXT)";
        db.execSQL(sql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        String sql = "DROP TABLE IF EXISTS Voluntarios";
        db.execSQL(sql);
        onCreate(db);
    }

    public void insere(Voluntario voluntario) {
        SQLiteDatabase db = getWritableDatabase();

        ContentValues dados = pegaDadosDoVoluntario(voluntario);

        db.insert("Voluntarios", null, dados);
    }

    @NonNull
    private ContentValues pegaDadosDoVoluntario(Voluntario voluntario) {
        ContentValues dados = new ContentValues();
        dados.put("nome", voluntario.getNome());
        dados.put("naturalidade", voluntario.getNaturalidade());
        dados.put("celular", voluntario.getCelular());

        DateFormat df = new SimpleDateFormat("MM/dd/yyyy");
        String reportDate = df.format(voluntario.getNascimento());
        dados.put("nascimento", reportDate);

        dados.put("sexo", voluntario.getSexo());
        dados.put("altura", voluntario.getAltura());
        dados.put("logradouro", voluntario.getLogradouro());
        dados.put("numero", voluntario.getNumero());
        dados.put("bairro", voluntario.getBairro());
        dados.put("cep", voluntario.getCep());
        dados.put("cidade", voluntario.getCidade());
        dados.put("uf", voluntario.getUf());
        dados.put("funcao", voluntario.getFuncao());
        dados.put("horario_pegar", voluntario.getHorario_pegar());
        dados.put("horario_largar", voluntario.getHorario_largar());
        dados.put("hora_semanal", voluntario.getHora_semanal());
        dados.put("tamanho_camisa", voluntario.getTamanho_camisa());
        dados.put("cpf", voluntario.getCpf());
        dados.put("rg", voluntario.getRg());
        dados.put("area", voluntario.getArea());
        return dados;
    }

    public List<Voluntario> buscaVoluntario() {
        String sql = "SELECT * from Voluntarios;";
        SQLiteDatabase db = getReadableDatabase();
        Cursor c = db.rawQuery(sql, null);

        List<Voluntario> voluntarios = new ArrayList<>();
        while(c.moveToNext()){
            Voluntario voluntario = new Voluntario();
            voluntario.setNome(c.getString(c.getColumnIndex("nome")));
            voluntario.setNaturalidade(c.getString(c.getColumnIndex("naturalidade")));
            voluntario.setCelular(c.getInt(c.getColumnIndex("celular")));

            DateFormat df = new SimpleDateFormat("MM/dd/yyyy");
            Date date = null;
            try {
                date = df.parse(c.getString(c.getColumnIndex("nascimento")));
            } catch (ParseException e) {
                e.printStackTrace();
            }
            Date d2 = date;
            voluntario.setNascimento(date);
            Date d3 = voluntario.getNascimento();
            voluntario.setSexo(c.getString(c.getColumnIndex("sexo")));
            voluntario.setAltura(c.getDouble(c.getColumnIndex("altura")));
            voluntario.setLogradouro(c.getString(c.getColumnIndex("logradouro")));
            voluntario.setNumero(c.getInt(c.getColumnIndex("numero")));
            voluntario.setBairro(c.getString(c.getColumnIndex("bairro")));
            voluntario.setCep(c.getString(c.getColumnIndex("cep")));
            voluntario.setCidade(c.getString(c.getColumnIndex("cidade")));
            voluntario.setUf(c.getString(c.getColumnIndex("uf")));
            voluntario.setId(c.getLong(c.getColumnIndex("id")));
            voluntario.setFuncao(c.getString(c.getColumnIndex("funcao")));
            voluntario.setHorario_pegar(c.getDouble(c.getColumnIndex("horario_pegar")));
            voluntario.setHorario_largar(c.getDouble(c.getColumnIndex("horario_largar")));
            voluntario.setHora_semanal(c.getDouble(c.getColumnIndex("hora_semanal")));
            voluntario.setTamanho_camisa(c.getString(c.getColumnIndex("tamanho_camisa")));
            voluntario.setCpf(c.getInt(c.getColumnIndex("cpf")));
            voluntario.setRg(c.getInt(c.getColumnIndex("rg")));
            voluntario.setArea(c.getString(c.getColumnIndex("area")));

            voluntarios.add(voluntario);
        }
        c.close();
        return voluntarios;
    }

    public void deleta(Voluntario voluntario) {
        SQLiteDatabase db = getWritableDatabase();
        String[] params = {voluntario.getId()+ ""};
        db.delete("Voluntarios", "id = ?", params);
    }

    public void altera(Voluntario voluntario) {
        SQLiteDatabase db = getWritableDatabase();

        ContentValues dados = pegaDadosDoVoluntario(voluntario);

        String[] params = {voluntario.getId()+ ""};
        db.update("Voluntarios", dados, "id = ?", params);
    }

}