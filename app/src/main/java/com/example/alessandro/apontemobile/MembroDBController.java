package com.example.alessandro.apontemobile;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.alessandro.apontemobile.modelo.Gr;
import com.example.alessandro.apontemobile.modelo.Membro;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static com.example.alessandro.apontemobile.MembroDBOpenHelper.TABLE_MEMBRO;
import static com.example.alessandro.apontemobile.MembroDBOpenHelper.TABLE_MEMBRO_GR;

public class MembroDBController {

    private SQLiteDatabase db;
    private MembroDBOpenHelper membroDB;

    public MembroDBController(Context context){
        membroDB = new MembroDBOpenHelper(context);
    }

    public List<Membro> getAllMembro() throws ParseException {
        List<Membro> membroList = new ArrayList<Membro>();
        Cursor cursor = loadItems();


        if (cursor.moveToFirst()) {
            do {
                //resgata as informações dos alunos que estão no cursor

                DateFormat df = new SimpleDateFormat("MM/dd/yyyy");
                Date date = df.parse(cursor.getString(3));

                DateFormat dft = new SimpleDateFormat("MM/dd/yyyy");
                Date dt = df.parse(cursor.getString(13));

                String nome = cursor.getString(0);
                List<Gr> listGr = null;
                listGr = getAllGrMembro(nome);

                Membro mem = new Membro(cursor.getString(0),cursor.getString(1),
                        cursor.getInt(2),date,cursor.getString(4),
                        cursor.getDouble(5),cursor.getString(6),
                        cursor.getInt(7),cursor.getString(8),cursor.getString(9),
                        cursor.getString(10),cursor.getString(11),dt,
                        cursor.getString(13),cursor.getString(14),
                        cursor.getString(15),listGr,cursor.getString(16));

                //adiciona cada aluno resgatado dentro da lista a ser retornada
                membroList.add(mem);
            } while (cursor.moveToNext());
        }
        //cursor fecha
        cursor.close();

        return membroList;
    }

    //método para carregar os alunos da tabela de alunos
    //retorna um cursor a ser usado em getAllMembro
    public Cursor loadItems(){
        Cursor cursor;
        //abrindo o bd pra leitura
        db = membroDB.getReadableDatabase();
        //faz a requisição das colunas que tá em alunoDB.COLUNA da tabela de aluno
        cursor = db.query(TABLE_MEMBRO, membroDB.COLUNA, null, null, null, null,
                null, null);

        if(cursor!=null && cursor.getCount() > 0){
            cursor.moveToFirst();
        }
        //fecha
        db.close();
        return cursor;
    }

    //método para inserir Membro
    public String insert(Membro membro){
        //checa se o bd foi criado
        long check =0;
        //abrir o bd pra escrita
        SQLiteDatabase db = membroDB.getWritableDatabase();
        //serve para colocar os valores
        ContentValues values = new ContentValues();

        values.put(MembroDBOpenHelper.NOME, membro.getNome());
        values.put(MembroDBOpenHelper.NATURALIDADE, membro.getNaturalidade());
        values.put(MembroDBOpenHelper.CELULAR, membro.getCelular());

        DateFormat df = new SimpleDateFormat("MM/dd/yyyy");
        String reportDate = df.format(membro.getNascimento());

        values.put(MembroDBOpenHelper.NASCIMENTO, reportDate);
        values.put(MembroDBOpenHelper.SEXO, membro.getSexo());
        values.put(MembroDBOpenHelper.ALTURA, membro.getAltura());
        values.put(MembroDBOpenHelper.LOGRADOURO, membro.getLogradouro());
        values.put(MembroDBOpenHelper.NUMERO, membro.getNumero());
        values.put(MembroDBOpenHelper.BAIRRO, membro.getBairro());
        values.put(MembroDBOpenHelper.CEP, membro.getCep());
        values.put(MembroDBOpenHelper.CIDADE, membro.getCidade());
        values.put(MembroDBOpenHelper.UF, membro.getUf());

        DateFormat dft = new SimpleDateFormat("MM/dd/yyyy");
        String reportDat = dft.format(membro.getData_conversao());

        values.put(MembroDBOpenHelper.DATA_CONVERSAO, reportDat);
        values.put(MembroDBOpenHelper.EQUIPE, membro.getEquipe());
        values.put(MembroDBOpenHelper.TEMPO_PONTE, membro.getTempo_ponte());
        values.put(MembroDBOpenHelper.CARGO, membro.getCargo());
        values.put(MembroDBOpenHelper.COLETE, membro.getColete());

        //insiro no BD
        check = db.insert(TABLE_MEMBRO, null, values);

        //sempre que abre, fecha
        db.close();

        //se o método insert deu erro, retorna -1
        if(check == -1){
            return "Error";
        } else{
            return "DB created";
        }
    }

    public String insertGrMembro(Gr gr, String nome){
        long check =0;
        SQLiteDatabase db = membroDB.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put(MembroDBOpenHelper.NOME_Gr, nome);
        values.put(MembroDBOpenHelper.NOME_GRGR, gr.getNomeGr());
        values.put(MembroDBOpenHelper.QUANTIDADEGR, gr.getQuantidade());
        values.put(MembroDBOpenHelper.HORARIOGR, gr.getHorario());
        values.put(MembroDBOpenHelper.DIAGR, gr.getDia());
        values.put(MembroDBOpenHelper.FREQUENCIAGR, gr.getFrequencia());
        values.put(MembroDBOpenHelper.LIDERGR, gr.getLider());
        values.put(MembroDBOpenHelper.APOIOGR, gr.getApoio());
        values.put(MembroDBOpenHelper.CONTATOGR, gr.getContato());

        DateFormat df = new SimpleDateFormat("MM/dd/yyyy");
        String reportDate = df.format(gr.getInauguracao());

        values.put(MembroDBOpenHelper.INAUGURACAOGR, reportDate);
        values.put(MembroDBOpenHelper.LOGRADOUROGR, gr.getLogradouro());
        values.put(MembroDBOpenHelper.NUMEROGR, gr.getNumero());
        values.put(MembroDBOpenHelper.BAIRROGR, gr.getBairro());
        values.put(MembroDBOpenHelper.CEPGR, gr.getCep());
        values.put(MembroDBOpenHelper.CIDADEGR, gr.getCidade());
        values.put(MembroDBOpenHelper.UFGR, gr.getUf());

        check = db.insert(TABLE_MEMBRO_GR, null, values);

        db.close();

        if(check == -1){
            return "Error";
        } else{
            return "DB created";
        }
    }

    //carrega as disciplinas de um aluno específico

    public Cursor loadGrMembro(String nome){
        db = membroDB.getReadableDatabase();
        //retorna as disciplinas onde o cpf de aluno da tabela é igual ao cpf fornecido
        Cursor cursor = db.rawQuery("SELECT * FROM " + TABLE_MEMBRO_GR
                + " WHERE nomeMembroGr = '" + nome + "'", new String[] {});
        if(cursor!=null && cursor.getCount() > 0){
            cursor.moveToFirst();
        }
        db.close();
        return cursor;
    }

    //método que retorna todas as disciplinas de um determinado aluno ( pelo cpf)
    public List<Gr> getAllGrMembro(String nome) throws ParseException {
        List<Gr> grMembro = null;
        Cursor cursor = loadGrMembro(nome);

        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            grMembro = new ArrayList<Gr>();
            do {

                DateFormat df = new SimpleDateFormat("MM/dd/yyyy");
                Date datei = null;
                if(cursor.getString(9) != null){
                    datei = df.parse(cursor.getString(3));
                }

                Gr gr = new Gr(cursor.getString(1),cursor.getInt(2),
                        cursor.getDouble(3),cursor.getString(4),cursor.getString(5),
                        cursor.getString(6), cursor.getString(7), cursor.getInt(8),
                        datei, cursor.getString(10), cursor.getInt(11),
                        cursor.getString(12), cursor.getInt(13), cursor.getString(14),
                        cursor.getString(15));

                //Adiona a disciplina na lista de disciplinas a ser retornada
                grMembro.add(gr);
            } while (cursor.moveToNext());
        }
        cursor.close();

        // return contact list
        return grMembro;
    }

    //método pra deletar tabela
    public void clearAll(){
        membroDB.getWritableDatabase().delete(TABLE_MEMBRO,null,null);

    }

    //retorna um aluno específico por meio de seu nome
    public Membro getMembro(String nome) throws ParseException {
        SQLiteDatabase db = membroDB.getWritableDatabase();
        Membro membro = null;
        //retorna apenas um aluno com esse nome dado
        Cursor cursor = db.rawQuery("SELECT * FROM " + TABLE_MEMBRO
                + " WHERE nome = '" + nome + "'", new String[] {});
        if(cursor!=null && cursor.getCount() > 0){
            cursor.moveToFirst();
        }
        if(cursor.moveToFirst()){
            DateFormat df = new SimpleDateFormat("MM/dd/yyyy");
            Date date = df.parse(cursor.getString(3));

            DateFormat dfat = new SimpleDateFormat("MM/dd/yyyy");
            Date dt = dfat.parse(cursor.getString(12));

            List<Gr> listGr = null;
            listGr = getAllGrMembro(nome);

            membro = new Membro(cursor.getString(0),cursor.getString(1),
                    cursor.getInt(2),date,cursor.getString(4),
                    cursor.getDouble(5),cursor.getString(6),
                    cursor.getInt(7),cursor.getString(8),cursor.getString(9),
                    cursor.getString(10),cursor.getString(11),
                    dt,cursor.getString(13),cursor.getString(14),
                    cursor.getString(15),listGr,cursor.getString(16));

        }
        return membro;

    }

    //atualizar disciplina de um aluno
    //usá-se quando o professor atribui as notas
    public int updateGr(Gr gr, String nome) {
        SQLiteDatabase db = membroDB.getWritableDatabase();

        ContentValues values = new ContentValues();

        values.put(MembroDBOpenHelper.NOME_Gr, nome);
        values.put(MembroDBOpenHelper.NOME_GRGR, gr.getNomeGr());
        values.put(MembroDBOpenHelper.QUANTIDADEGR, gr.getQuantidade());
        values.put(MembroDBOpenHelper.HORARIOGR, gr.getHorario());
        values.put(MembroDBOpenHelper.DIAGR, gr.getDia());
        values.put(MembroDBOpenHelper.FREQUENCIAGR, gr.getFrequencia());
        values.put(MembroDBOpenHelper.LIDERGR, gr.getLider());
        values.put(MembroDBOpenHelper.APOIOGR, gr.getApoio());
        values.put(MembroDBOpenHelper.CONTATOGR, gr.getContato());

        DateFormat df = new SimpleDateFormat("MM/dd/yyyy");
        String reportDate = df.format(gr.getInauguracao());

        values.put(MembroDBOpenHelper.INAUGURACAOGR, reportDate);
        values.put(MembroDBOpenHelper.LOGRADOUROGR, gr.getLogradouro());
        values.put(MembroDBOpenHelper.NUMEROGR, gr.getNumero());
        values.put(MembroDBOpenHelper.BAIRROGR, gr.getBairro());
        values.put(MembroDBOpenHelper.CEPGR, gr.getCep());
        values.put(MembroDBOpenHelper.CIDADEGR, gr.getCidade());
        values.put(MembroDBOpenHelper.UFGR, gr.getUf());

        int update = db.update(TABLE_MEMBRO_GR, values, MembroDBOpenHelper.NOME_GRGR + " = ?",
                new String[]{String.valueOf(gr.getNomeGr())});
        db.close();
        return update;

    }

    //atualiza as informações do aluno
    //usá-se quando se faz a matricula
    public int updateSite(Membro membro){
        SQLiteDatabase db = membroDB.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(MembroDBOpenHelper.NOME, membro.getNome());
        values.put(MembroDBOpenHelper.NATURALIDADE, membro.getNaturalidade());
        values.put(MembroDBOpenHelper.CELULAR, membro.getCelular());

        DateFormat df = new SimpleDateFormat("MM/dd/yyyy");
        String reportDate = df.format(membro.getNascimento());

        values.put(MembroDBOpenHelper.NASCIMENTO, reportDate);
        values.put(MembroDBOpenHelper.SEXO, membro.getSexo());
        values.put(MembroDBOpenHelper.ALTURA, membro.getAltura());
        values.put(MembroDBOpenHelper.LOGRADOURO, membro.getLogradouro());
        values.put(MembroDBOpenHelper.NUMERO, membro.getNumero());
        values.put(MembroDBOpenHelper.BAIRRO, membro.getBairro());
        values.put(MembroDBOpenHelper.CEP, membro.getCep());
        values.put(MembroDBOpenHelper.CIDADE, membro.getCidade());
        values.put(MembroDBOpenHelper.UF, membro.getUf());

        DateFormat dft = new SimpleDateFormat("MM/dd/yyyy");
        String reportDat = dft.format(membro.getData_conversao());

        values.put(MembroDBOpenHelper.DATA_CONVERSAO, reportDat);
        values.put(MembroDBOpenHelper.EQUIPE, membro.getEquipe());
        values.put(MembroDBOpenHelper.TEMPO_PONTE, membro.getTempo_ponte());
        values.put(MembroDBOpenHelper.CARGO, membro.getCargo());
        values.put(MembroDBOpenHelper.COLETE, membro.getColete());

        int update = db.update(TABLE_MEMBRO, values, MembroDBOpenHelper.NOME + " = ?",
                new String[]{String.valueOf(membro.getNome())});
        db.close();
        return update;

    }

}
