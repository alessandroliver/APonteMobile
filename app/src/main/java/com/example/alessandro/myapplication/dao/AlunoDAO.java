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

import com.example.alessandro.myapplication.modelo.Curso;
import com.example.alessandro.myapplication.modelo.Aluno;

import com.example.alessandro.myapplication.ListaAlunoActivity;

public class AlunoDAO extends SQLiteOpenHelper {

    private Context context;
    public AlunoDAO(Context context) {
        super(context, "Aluno", null, 1);
        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String sql = "CREATE TABLE Alunos (nome TEXT, naturalidade TEXT, celular INTEGER, nascimento TEXT, sexo TEXT, altura REAL, logradouro TEXT, numero INTEGER, bairro TEXT, cep TEXT, cidade TEXT, uf TEXT, id INTEGER PRIMARY KEY, matricula TEXT, farda TEXT, responsavel TEXT, serie TEXT, escola TEXT)";
        String sql1 = "CREATE TABLE Cursos_Aluno (nomeAluno TEXT, id INTEGER PRIMARY KEY, nomeCurso TEXT, inicio TEXT, fim TEXT, carga_horaria INTEGER, professor TEXT, dia TEXT, pegar REAL, largar REAL, sala TEXT, turno TEXT)";

        db.execSQL(sql);
        db.execSQL(sql1);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        String sql = "DROP TABLE IF EXISTS Alunos";
        String sql1 = "DROP TABLE IF EXISTS Cursos_Aluno";
        db.execSQL(sql);
        db.execSQL(sql1);
        onCreate(db);
    }

    public void insere(Aluno aluno) {
        SQLiteDatabase db = getWritableDatabase();

        ContentValues dados = pegaDadosDoAluno(aluno);

        db.insert("Alunos", null, dados);
    }

    public void insereCursoAluno(Curso curso, String nomeAluno) {
        SQLiteDatabase db = getWritableDatabase();

        ContentValues dados = pegaDadosCursoAluno(curso, nomeAluno);

        db.insert("Cursos_Aluno", null, dados);
    }

    @NonNull
    private ContentValues pegaDadosDoAluno(Aluno aluno) {
        ContentValues dados = new ContentValues();
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

    private ContentValues pegaDadosCursoAluno(Curso curso, String nomeAluno){
        ContentValues dados = new ContentValues();
        dados.put("nomeAluno", nomeAluno);
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

    public List<Aluno> buscaAluno() {
        String sql = "SELECT * from Alunos;";
        SQLiteDatabase db = getReadableDatabase();
        Cursor c = db.rawQuery(sql, null);

        List<Aluno> alunos = new ArrayList<>();
        while(c.moveToNext()){
            Aluno aluno = new Aluno();
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
            List<Curso> cursos = buscaCursosAluno(c.getString(c.getColumnIndex("nome")));
            aluno.setCurso(cursos);
            alunos.add(aluno);
        }
        c.close();
        return alunos;
    }

    public List<Curso> buscaCursosAluno(String nome){
        String sql = "SELECT * from Cursos_Aluno;";
        SQLiteDatabase db = getReadableDatabase();
        Cursor c = db.rawQuery(sql, null);

        List<Curso> cursos = new ArrayList<>();
        while(c.moveToNext()){
            Curso curso = new Curso();
            if(c.getString(c.getColumnIndex("nomeAluno")).equals(nome)) {

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
                curso.setAluno(null);
                cursos.add(curso);
            }

        }
        c.close();
        return cursos;
    }

    public void deleta(Aluno aluno) {
        SQLiteDatabase db = getWritableDatabase();
        String[] params = {aluno.getId()+ ""};
        db.delete("Alunos", "id = ?", params);
    }

    public void deletaCurso(Curso curso) {
        SQLiteDatabase db = getWritableDatabase();
        String[] params = {curso.getId()+ ""};
        db.delete("Cursos_Aluno", "id = ?", params);
    }

    public void altera(Aluno aluno) {
        SQLiteDatabase db = getWritableDatabase();

        ContentValues dados = pegaDadosDoAluno(aluno);

        String[] params = {aluno.getId()+ ""};
        db.update("Alunos", dados, "id = ?", params);
    }

    public void alteraCurso(Curso curso, String alunoCurso) {
        SQLiteDatabase db = getWritableDatabase();

        ContentValues dados = pegaDadosCursoAluno(curso, alunoCurso);

        String[] params = {curso.getId()+ ""};
        db.update("Cursos_Aluno", dados, "id = ?", params);
    }

}
