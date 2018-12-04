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

import com.example.alessandro.myapplication.modelo.Funcionario;

import com.example.alessandro.myapplication.ListaFuncionarioActivity;

public class FuncionarioDAO extends SQLiteOpenHelper {

    public FuncionarioDAO(Context context) {
        super(context, "Funcionario", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String sql = "CREATE TABLE Funcionarios (nome TEXT, naturalidade TEXT, celular INTEGER, nascimento TEXT, sexo TEXT, altura REAL, logradouro TEXT, numero INTEGER, bairro TEXT, cep TEXT, cidade TEXT, uf TEXT, id INTEGER PRIMARY KEY, cargo TEXT, salario REAL, hora_semanal INTEGER, carteira_trabalho INTEGER, pis REAL, cpf INTEGER, rg INTEGER, conta INTEGER, banco TEXT, agencia INTEGER, camisa TEXT, entrada TEXT, pagamento TEXT)";
        db.execSQL(sql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        String sql = "DROP TABLE IF EXISTS Funcionarios";
        db.execSQL(sql);
        onCreate(db);
    }

    public void insere(Funcionario funcionario) {
        SQLiteDatabase db = getWritableDatabase();

        ContentValues dados = pegaDadosDoFuncionario(funcionario);

        db.insert("Funcionarios", null, dados);
    }

    @NonNull
    private ContentValues pegaDadosDoFuncionario(Funcionario funcionario) {
        ContentValues dados = new ContentValues();
        dados.put("nome", funcionario.getNome());
        dados.put("naturalidade", funcionario.getNaturalidade());
        dados.put("celular", funcionario.getCelular());

        DateFormat df = new SimpleDateFormat("MM/dd/yyyy");
        String reportDate = df.format(funcionario.getNascimento());
        dados.put("nascimento", reportDate);

        dados.put("sexo", funcionario.getSexo());
        dados.put("altura", funcionario.getAltura());
        dados.put("logradouro", funcionario.getLogradouro());
        dados.put("numero", funcionario.getNumero());
        dados.put("bairro", funcionario.getBairro());
        dados.put("cep", funcionario.getCep());
        dados.put("cidade", funcionario.getCidade());
        dados.put("uf", funcionario.getUf());
        dados.put("cargo", funcionario.getCargo());
        dados.put("salario", funcionario.getSalario());
        dados.put("hora_semanal", funcionario.getHora_semanal());
        dados.put("carteira_trabalho", funcionario.getCarteira_trabalho());
        dados.put("pis", funcionario.getPis());
        dados.put("cpf", funcionario.getCpf());
        dados.put("rg", funcionario.getRg());
        dados.put("conta", funcionario.getConta());
        dados.put("banco", funcionario.getBanco());
        dados.put("agencia", funcionario.getAgencia());
        dados.put("camisa", funcionario.getCamisa());

        DateFormat df1 = new SimpleDateFormat("MM/dd/yyyy");
        String reportDate1 = df1.format(funcionario.getEntrada());
        dados.put("entrada", reportDate1);

        DateFormat df2 = new SimpleDateFormat("MM/dd/yyyy");
        String reportDate2 = df2.format(funcionario.getPagamento());
        dados.put("pagamento", reportDate2);

        return dados;
    }

    public List<Funcionario> buscaFuncionario() {
        String sql = "SELECT * from Funcionarios;";
        SQLiteDatabase db = getReadableDatabase();
        Cursor c = db.rawQuery(sql, null);

        List<Funcionario> funcionarios = new ArrayList<>();
        while(c.moveToNext()){
            Funcionario funcionario = new Funcionario();
            funcionario.setNome(c.getString(c.getColumnIndex("nome")));
            funcionario.setNaturalidade(c.getString(c.getColumnIndex("naturalidade")));
            funcionario.setCelular(c.getInt(c.getColumnIndex("celular")));

            DateFormat df = new SimpleDateFormat("MM/dd/yyyy");
            Date date = null;
            try {
                date = df.parse(c.getString(c.getColumnIndex("nascimento")));
            } catch (ParseException e) {
                e.printStackTrace();
            }
            Date d2 = date;
            funcionario.setNascimento(date);
            Date d3 = funcionario.getNascimento();
            funcionario.setSexo(c.getString(c.getColumnIndex("sexo")));
            funcionario.setAltura(c.getDouble(c.getColumnIndex("altura")));
            funcionario.setLogradouro(c.getString(c.getColumnIndex("logradouro")));
            funcionario.setNumero(c.getInt(c.getColumnIndex("numero")));
            funcionario.setBairro(c.getString(c.getColumnIndex("bairro")));
            funcionario.setCep(c.getString(c.getColumnIndex("cep")));
            funcionario.setCidade(c.getString(c.getColumnIndex("cidade")));
            funcionario.setUf(c.getString(c.getColumnIndex("uf")));
            funcionario.setId(c.getLong(c.getColumnIndex("id")));
            funcionario.setCargo(c.getString(c.getColumnIndex("cargo")));
            funcionario.setSalario(c.getDouble(c.getColumnIndex("salario")));
            funcionario.setHora_semanal(c.getInt(c.getColumnIndex("hora_semanal")));
            funcionario.setCarteira_trabalho(c.getInt(c.getColumnIndex("carteira_trabalho")));
            funcionario.setPis(c.getInt(c.getColumnIndex("pis")));
            funcionario.setCpf(c.getInt(c.getColumnIndex("cpf")));
            funcionario.setRg(c.getInt(c.getColumnIndex("rg")));
            funcionario.setConta(c.getInt(c.getColumnIndex("conta")));
            funcionario.setBanco(c.getString(c.getColumnIndex("banco")));
            funcionario.setAgencia(c.getInt(c.getColumnIndex("agencia")));
            funcionario.setCamisa(c.getString(c.getColumnIndex("camisa")));

            DateFormat df1 = new SimpleDateFormat("MM/dd/yyyy");
            Date date1 = null;
            try {
                date1 = df1.parse(c.getString(c.getColumnIndex("entrada")));
            } catch (ParseException e) {
                e.printStackTrace();
            }
            Date d21 = date1;
            funcionario.setEntrada(date1);
            Date d31 = funcionario.getEntrada();

            DateFormat df2 = new SimpleDateFormat("MM/dd/yyyy");
            Date date2 = null;
            try {
                date2 = df2.parse(c.getString(c.getColumnIndex("pagamento")));
            } catch (ParseException e) {
                e.printStackTrace();
            }
            Date d22 = date2;
            funcionario.setPagamento(date2);
            Date d32 = funcionario.getPagamento();

            funcionarios.add(funcionario);
        }
        c.close();
        return funcionarios;
    }

    public void deleta(Funcionario funcionario) {
        SQLiteDatabase db = getWritableDatabase();
        String[] params = {funcionario.getId()+ ""};
        db.delete("Funcionarios", "id = ?", params);
    }

    public void altera(Funcionario funcionario) {
        SQLiteDatabase db = getWritableDatabase();

        ContentValues dados = pegaDadosDoFuncionario(funcionario);

        String[] params = {funcionario.getId()+ ""};
        db.update("Funcionarios", dados, "id = ?", params);
    }

}
