package com.example.alessandro.apontemobile;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.alessandro.apontemobile.modelo.Curso;
import com.example.alessandro.apontemobile.modelo.Aluno;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static com.example.alessandro.apontemobile.AlunoDBOpenHelper.TABLE_ALUNO;
import static com.example.alessandro.apontemobile.AlunoDBOpenHelper.TABLE_ALUNO_CURSO;

public class AlunoDBController {

    private SQLiteDatabase db;
    private AlunoDBOpenHelper alunoDB;

    public AlunoDBController(Context context){
        alunoDB = new AlunoDBOpenHelper(context);
    }

    public List<Aluno> getAllAluno() throws ParseException {
        List<Aluno> alunoList = new ArrayList<Aluno>();
        Cursor cursor = loadItems();

        if (cursor.moveToFirst()) {
            do {
                //resgata as informações dos alunos que estão no cursor

                DateFormat df = new SimpleDateFormat("MM/dd/yyyy");
                Date date = df.parse(cursor.getString(3));

                String matricula = cursor.getString(0);
                List<Curso> listCurso = null;
                listCurso = getAllCursoAluno(matricula);

                Aluno alu = new Aluno(cursor.getString(0),cursor.getString(1),
                        cursor.getInt(2),date,cursor.getString(4),
                        cursor.getDouble(5),cursor.getString(6),cursor.getInt(7),
                        cursor.getString(8),cursor.getString(9),cursor.getString(10),
                        cursor.getString(11),listCurso,cursor.getString(13),
                        cursor.getString(14),cursor.getString(15),cursor.getString(16),
                        cursor.getString(17));

                //adiciona cada aluno resgatado dentro da lista a ser retornada
                alunoList.add(alu);
            } while (cursor.moveToNext());
        }
        //cursor fecha
        cursor.close();

        return alunoList;
    }

    //método para carregar os alunos da tabela de alunos
    //retorna um cursor a ser usado em getAllMembro
    public Cursor loadItems(){
        Cursor cursor;
        //abrindo o bd pra leitura
        db = alunoDB.getReadableDatabase();
        //faz a requisição das colunas que tá em alunoDB.COLUNA da tabela de aluno
        cursor = db.query(TABLE_ALUNO, alunoDB.COLUNA, null, null, null, null,
                null, null);

        if(cursor!=null && cursor.getCount() > 0){
            cursor.moveToFirst();
        }
        //fecha
        db.close();
        return cursor;
    }

    //método para inserir Membro
    public String insert(Aluno aluno){
        //checa se o bd foi criado
        long check =0;
        //abrir o bd pra escrita
        SQLiteDatabase db = alunoDB.getWritableDatabase();
        //serve para colocar os valores
        ContentValues values = new ContentValues();

        values.put(AlunoDBOpenHelper.NOME, aluno.getNome());
        values.put(AlunoDBOpenHelper.NATURALIDADE, aluno.getNaturalidade());
        values.put(AlunoDBOpenHelper.CELULAR, aluno.getCelular());

        DateFormat df = new SimpleDateFormat("MM/dd/yyyy");
        String reportDate = df.format(aluno.getNascimento());

        values.put(AlunoDBOpenHelper.NASCIMENTO, reportDate);
        values.put(AlunoDBOpenHelper.SEXO, aluno.getSexo());
        values.put(AlunoDBOpenHelper.ALTURA, aluno.getAltura());
        values.put(AlunoDBOpenHelper.LOGRADOURO, aluno.getLogradouro());
        values.put(AlunoDBOpenHelper.NUMERO, aluno.getNumero());
        values.put(AlunoDBOpenHelper.BAIRRO, aluno.getBairro());
        values.put(AlunoDBOpenHelper.CEP, aluno.getCep());
        values.put(AlunoDBOpenHelper.CIDADE, aluno.getCidade());
        values.put(AlunoDBOpenHelper.UF, aluno.getUf());

        values.put(AlunoDBOpenHelper.MATRICULA, aluno.getMatricula());
        values.put(AlunoDBOpenHelper.FARDA, aluno.getFarda());
        values.put(AlunoDBOpenHelper.RESPONSAVEL, aluno.getResponsavel());
        values.put(AlunoDBOpenHelper.SERIE, aluno.getSerie());
        values.put(AlunoDBOpenHelper.ESCOLA, aluno.getEscola());

        //insiro no BD
        check = db.insert(TABLE_ALUNO, null, values);

        //sempre que abre, fecha
        db.close();

        //se o método insert deu erro, retorna -1
        if(check == -1){
            return "Error";
        } else{
            return "DB created";
        }
    }

    public String insertCursoAluno(Curso curso, String matricula){
        long check =0;
        SQLiteDatabase db = alunoDB.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put(AlunoDBOpenHelper.MATRICULA_Cur, matricula);
        values.put(AlunoDBOpenHelper.NOME_CURSOCURSO, curso.getNomeCurso());

        DateFormat df = new SimpleDateFormat("MM/dd/yyyy");
        String reportDate = df.format(curso.getInicio());

        values.put(AlunoDBOpenHelper.INICIOCURSO, reportDate);

        DateFormat dft = new SimpleDateFormat("MM/dd/yyyy");
        String reportDat = dft.format(curso.getFim());

        values.put(AlunoDBOpenHelper.FIMCURSO, reportDat);

        values.put(AlunoDBOpenHelper.CARGA_HORARIACURSO, curso.getCarga_horaria());
        values.put(AlunoDBOpenHelper.PROFESSORCURSO, curso.getProfessor());
        values.put(AlunoDBOpenHelper.DIACURSO, curso.getDia());
        values.put(AlunoDBOpenHelper.PEGARCURSO, curso.getPegar());
        values.put(AlunoDBOpenHelper.LARGARCURSO, curso.getLargar());
        values.put(AlunoDBOpenHelper.SALACURSO, curso.getSala());

        check = db.insert(TABLE_ALUNO_CURSO, null, values);

        db.close();

        if(check == -1){
            return "Error";
        } else{
            return "DB created";
        }
    }

    //carrega as disciplinas de um aluno específico

    public Cursor loadCursoAluno(String matricula){
        db = alunoDB.getReadableDatabase();
        //retorna as disciplinas onde o matricula de aluno da tabela é igual ao matricula fornecido
        Cursor cursor = db.rawQuery("SELECT * FROM " + TABLE_ALUNO_CURSO
                + " WHERE natriculaAlunoCurso = '" + matricula + "'", new String[] {});
        if(cursor!=null && cursor.getCount() > 0){
            cursor.moveToFirst();
        }
        db.close();
        return cursor;
    }

    //método que retorna todas as disciplinas de um determinado aluno ( pela matricula)
    public List<Curso> getAllCursoAluno(String matricula) throws ParseException {
        List<Curso> cursoAluno = null;
        Cursor cursor = loadCursoAluno(matricula);

        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            cursoAluno = new ArrayList<Curso>();
            do {

                DateFormat df = new SimpleDateFormat("MM/dd/yyyy");
                Date datei = df.parse(cursor.getString(2));

                DateFormat dft = new SimpleDateFormat("MM/dd/yyyy");
                Date datef = dft.parse(cursor.getString(3));

                Curso curso = new Curso(cursor.getString(1),datei,datef,cursor.getInt(5),
                        cursor.getString(6),cursor.getString(7),cursor.getDouble(8),
                        cursor.getDouble(9),cursor.getString(10));

                //Adiona a disciplina na lista de disciplinas a ser retornada
                cursoAluno.add(curso);
            } while (cursor.moveToNext());
        }
        cursor.close();

        // return contact list
        return cursoAluno;
    }

    //método pra deletar tabela
    public void clearAll(){
        alunoDB.getWritableDatabase().delete(TABLE_ALUNO,null,null);

    }

    //retorna um aluno específico por meio de sua matricula
    public Aluno getAlunoCurso(String matricula) throws ParseException {
        SQLiteDatabase db = alunoDB.getWritableDatabase();
        Aluno aluno = null;
        //retorna apenas um aluno com esse matricula dado
        Cursor cursor = db.rawQuery("SELECT * FROM " + TABLE_ALUNO
                + " WHERE matricula = '" + matricula + "'", new String[] {});
        if(cursor!=null && cursor.getCount() > 0){
            cursor.moveToFirst();
        }
        if(cursor.moveToFirst()){
            DateFormat df = new SimpleDateFormat("MM/dd/yyyy");
            Date date = df.parse(cursor.getString(3));

            DateFormat dfat = new SimpleDateFormat("MM/dd/yyyy");
            Date dt = dfat.parse(cursor.getString(13));

            List<Curso> listCurso = null;
            listCurso = getAllCursoAluno(matricula);

            aluno = new Aluno(cursor.getString(0),cursor.getString(1),
                    cursor.getInt(2),date,cursor.getString(4),cursor.getDouble(5),
                    cursor.getString(6),cursor.getInt(7),cursor.getString(8),
                    cursor.getString(9),cursor.getString(10),cursor.getString(11),
                    listCurso,cursor.getString(12),cursor.getString(13),
                    cursor.getString(14),cursor.getString(15),cursor.getString(16));

        }
        return aluno;

    }

    //atualizar disciplina de um aluno
    //usá-se quando o professor atribui as notas
    public int updateCurso(Curso curso, String matricula) {
        SQLiteDatabase db = alunoDB.getWritableDatabase();

        ContentValues values = new ContentValues();

        values.put(AlunoDBOpenHelper.MATRICULA_Cur, matricula);
        values.put(AlunoDBOpenHelper.NOME_CURSOCURSO, curso.getNomeCurso());
        DateFormat df = new SimpleDateFormat("MM/dd/yyyy");
        String reportDate = df.format(curso.getInicio());

        values.put(AlunoDBOpenHelper.INICIOCURSO, reportDate);

        DateFormat dft = new SimpleDateFormat("MM/dd/yyyy");
        String reportDat = dft.format(curso.getInicio());

        values.put(AlunoDBOpenHelper.FIMCURSO, reportDat);
        values.put(AlunoDBOpenHelper.CARGA_HORARIACURSO, curso.getCarga_horaria());
        values.put(AlunoDBOpenHelper.PROFESSORCURSO, curso.getProfessor());
        values.put(AlunoDBOpenHelper.DIACURSO, curso.getDia());
        values.put(AlunoDBOpenHelper.PEGARCURSO, curso.getPegar());
        values.put(AlunoDBOpenHelper.LARGARCURSO, curso.getLargar());
        values.put(AlunoDBOpenHelper.SALACURSO, curso.getSala());

        int update = db.update(TABLE_ALUNO_CURSO, values, AlunoDBOpenHelper.NOME_CURSOCURSO + " = ?",
                new String[]{String.valueOf(curso.getNomeCurso())});
        db.close();
        return update;

    }

    //atualiza as informações do aluno
    //usá-se quando se faz a matricula
    public int updateSite1(Aluno aluno){
        SQLiteDatabase db = alunoDB.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(AlunoDBOpenHelper.NOME, aluno.getNome());
        values.put(AlunoDBOpenHelper.NATURALIDADE, aluno.getNaturalidade());
        values.put(AlunoDBOpenHelper.CELULAR, aluno.getCelular());

        DateFormat df = new SimpleDateFormat("MM/dd/yyyy");
        String reportDate = df.format(aluno.getNascimento());

        values.put(AlunoDBOpenHelper.NASCIMENTO, reportDate);
        values.put(AlunoDBOpenHelper.SEXO, aluno.getSexo());
        values.put(AlunoDBOpenHelper.ALTURA, aluno.getAltura());
        values.put(AlunoDBOpenHelper.LOGRADOURO, aluno.getLogradouro());
        values.put(AlunoDBOpenHelper.NUMERO, aluno.getNumero());
        values.put(AlunoDBOpenHelper.BAIRRO, aluno.getBairro());
        values.put(AlunoDBOpenHelper.CEP, aluno.getCep());
        values.put(AlunoDBOpenHelper.CIDADE, aluno.getCidade());
        values.put(AlunoDBOpenHelper.UF, aluno.getUf());
        values.put(AlunoDBOpenHelper.MATRICULA, aluno.getMatricula());
        values.put(AlunoDBOpenHelper.FARDA, aluno.getFarda());
        values.put(AlunoDBOpenHelper.RESPONSAVEL, aluno.getResponsavel());
        values.put(AlunoDBOpenHelper.SERIE, aluno.getSerie());
        values.put(AlunoDBOpenHelper.ESCOLA, aluno.getEscola());

        int update = db.update(TABLE_ALUNO, values, AlunoDBOpenHelper.MATRICULA + " = ?",
                new String[]{String.valueOf(aluno.getMatricula())});
        db.close();
        return update;

    }

}
