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

import com.example.alessandro.myapplication.modelo.Aluno;
import com.example.alessandro.myapplication.modelo.Curso;

import com.example.alessandro.myapplication.ListaCursoActivity;

public class CursoDAO extends SQLiteOpenHelper {

    public CursoDAO(Context context) {
        super(context, "Curso", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String sql = "CREATE TABLE Cursos (id INTEGER PRIMARY KEY, nomeCurso TEXT, inicio TEXT, fim TEXT, carga_horaria INTEGER, professor TEXT, dia TEXT, pegar REAL, largar REAL, sala TEXT, turno TEXT)";
        String sql1 = "CREATE TABLE Alunos_Curso (nomeCursoAluno TEXT, nome TEXT, naturalidade TEXT, celular INTEGER, nascimento TEXT, sexo TEXT, altura REAL, logradouro TEXT, numero INTEGER, bairro TEXT, cep TEXT, cidade TEXT, uf TEXT, id INTEGER PRIMARY KEY, matricula TEXT, farda TEXT, responsavel TEXT, serie TEXT, escola TEXT)";

        db.execSQL(sql);
        db.execSQL(sql1);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        String sql = "DROP TABLE IF EXISTS Cursos";
        String sql1 = "DROP TABLE IF EXISTS Alunos_Curso";
        db.execSQL(sql);
        db.execSQL(sql1);
        onCreate(db);
    }

    public void insere(Curso curso){
        SQLiteDatabase db = getWritableDatabase();

        ContentValues dados = pegaDadosDoCurso(curso);

        db.insert("Cursos", null, dados);

    }

    public void insereAlunoCurso(Aluno aluno, String nomeCurso){
        SQLiteDatabase db = getWritableDatabase();

        ContentValues dados = pegaDadosAlunoCurso(aluno, nomeCurso);

        db.insert("Alunos_Curso", null, dados);

    }

    private ContentValues pegaDadosDoCurso(Curso curso){
        ContentValues dados = new ContentValues();
        dados.put("nomeCurso", curso.getNomeCurso());
        DateFormat df = new SimpleDateFormat("MM/dd/yyyy");
        String reportDate = df.format(curso.getInicio());
        dados.put("inicio", reportDate);
        DateFormat dft = new SimpleDateFormat("MM/dd/yyyy");
        String reportDat = dft.format(curso.getFim());
        dados.put("fim", reportDat);
        dados.put("carga_horaria", curso.getCarga_horaria());
        dados.put("professor", curso.getProfessor());
        dados.put("dia", curso.getDia());
        dados.put("pegar", curso.getPegar());
        dados.put("largar", curso.getLargar());
        dados.put("sala", curso.getSala());
        dados.put("turno", curso.getTurno());
        return dados;
    }


    private ContentValues pegaDadosAlunoCurso(Aluno aluno, String nomeCurso){
        ContentValues dados = new ContentValues();
        dados.put("nomeCursoAluno", nomeCurso);
        dados.put("nome", aluno.getNome());
        dados.put("naturalidade", aluno.getNaturalidade());
        dados.put("celular", aluno.getCelular());

        DateFormat df = new SimpleDateFormat("MM/dd/yyyy");
        String reportDate = df.format(aluno.getNascimento());
        dados.put("nascimento", reportDate);

        dados.put("sexo", aluno.getSexo());
        dados.put("altura", aluno.getAltura());
        dados.put("logradouro", aluno.getLogradouro());
        dados.put("numero", aluno.getNumero());
        dados.put("bairro", aluno.getBairro());
        dados.put("cep", aluno.getCep());
        dados.put("cidade", aluno.getCidade());
        dados.put("uf", aluno.getUf());
        dados.put("matricula", aluno.getMatricula());
        dados.put("farda", aluno.getFarda());
        dados.put("responsavel", aluno.getResponsavel());
        dados.put("serie", aluno.getSerie());
        dados.put("escola", aluno.getEscola());
        return dados;
    }

    public List<Curso> buscaCursos(){
        String sql = "SELECT * from Cursos;";
        SQLiteDatabase db = getReadableDatabase();
        Cursor c = db.rawQuery(sql, null);

        List<Curso> cursos = new ArrayList<>();
        while(c.moveToNext()){
            Curso curso = new Curso();
            curso.setId(c.getLong(c.getColumnIndex("id")));
            curso.setNomeCurso(c.getString(c.getColumnIndex("nomeCurso")));
            DateFormat df = new SimpleDateFormat("MM/dd/yyyy");
            Date date = null;
            try {
                date = df.parse(c.getString(c.getColumnIndex("inicio")));
            } catch (ParseException e) {
                e.printStackTrace();
            }
            curso.setInicio(date);
            DateFormat dft = new SimpleDateFormat("MM/dd/yyyy");
            Date dat = null;
            try {
                dat = dft.parse(c.getString(c.getColumnIndex("fim")));
            } catch (ParseException e) {
                e.printStackTrace();
            }
            curso.setFim(dat);
            curso.setCarga_horaria(c.getInt(c.getColumnIndex("carga_horaria")));
            curso.setProfessor(c.getString(c.getColumnIndex("professor")));
            curso.setDia(c.getString(c.getColumnIndex("dia")));
            curso.setPegar(c.getDouble(c.getColumnIndex("pegar")));
            curso.setLargar(c.getDouble(c.getColumnIndex("largar")));
            curso.setSala(c.getString(c.getColumnIndex("sala")));
            curso.setTurno(c.getString(c.getColumnIndex("turno")));
            cursos.add(curso);
        }
        c.close();
        return cursos;
    }

    public List<Aluno> buscaAlunosCurso(String nomeCurso){
        String sql = "SELECT * from Alunos_Curso;";
        SQLiteDatabase db = getReadableDatabase();
        Cursor c = db.rawQuery(sql, null);

        List<Aluno> alunos = new ArrayList<>();
        while(c.moveToNext()){
            Aluno aluno = new Aluno();
            if(c.getString(c.getColumnIndex("nomeCursoAluno")).equals(nomeCurso)) {

                aluno.setNome(c.getString(c.getColumnIndex("nome")));
                aluno.setNaturalidade(c.getString(c.getColumnIndex("naturalidade")));
                aluno.setCelular(c.getInt(c.getColumnIndex("celular")));

                DateFormat df = new SimpleDateFormat("MM/dd/yyyy");
                Date date = null;
                try {
                    date = df.parse(c.getString(c.getColumnIndex("nascimento")));
                } catch (ParseException e) {
                    e.printStackTrace();
                }
                aluno.setNascimento(date);
                aluno.setSexo(c.getString(c.getColumnIndex("sexo")));
                aluno.setAltura(c.getDouble(c.getColumnIndex("altura")));
                aluno.setLogradouro(c.getString(c.getColumnIndex("logradouro")));
                aluno.setNumero(c.getInt(c.getColumnIndex("numero")));
                aluno.setBairro(c.getString(c.getColumnIndex("bairro")));
                aluno.setCep(c.getString(c.getColumnIndex("cep")));
                aluno.setCidade(c.getString(c.getColumnIndex("cidade")));
                aluno.setUf(c.getString(c.getColumnIndex("uf")));
                aluno.setId(c.getLong(c.getColumnIndex("id")));
                aluno.setMatricula(c.getString(c.getColumnIndex("matricula")));
                aluno.setFarda(c.getString(c.getColumnIndex("farda")));
                aluno.setResponsavel(c.getString(c.getColumnIndex("responsavel")));
                aluno.setSerie(c.getString(c.getColumnIndex("serie")));
                aluno.setEscola(c.getString(c.getColumnIndex("escola")));
                aluno.setCurso(null);
                alunos.add(aluno);
            }
        }
            c.close();
        return alunos;
    }

    public void deleta(Curso curso) {
        SQLiteDatabase db = getWritableDatabase();
        String[] params = {curso.getId()+ ""};
        db.delete("Cursos", "id = ?", params);
    }


    public void deletaAluno(Aluno aluno) {
        SQLiteDatabase db = getWritableDatabase();
        String[] params = {aluno.getId()+ ""};
        db.delete("Alunos_Curso", "id = ?", params);
    }

    public void altera(Curso curso) {
        SQLiteDatabase db = getWritableDatabase();

        ContentValues dados = pegaDadosDoCurso(curso);

        String[] params = {curso.getId()+ ""};
        db.update("Cursos", dados, "id = ?", params);
    }


    public void alteraAluno(Aluno aluno, String cursoAluno) {
        SQLiteDatabase db = getWritableDatabase();

        ContentValues dados = pegaDadosAlunoCurso(aluno, cursoAluno);

        String[] params = {aluno.getId()+ ""};
        db.update("Alunos_Curso", dados, "id = ?", params);
    }

}
