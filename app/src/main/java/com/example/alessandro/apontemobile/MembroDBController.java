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

public class MembroDBController {

    private SQLiteDatabase db;
    private MembroDBOpenHelper membroDB;
    private Context context;
    
    public MembroDBController(Context context){
        membroDB = new MembroDBOpenHelper(context);
        this.context = context;    
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

                Gr grMembro = null;
                GrDBController grDBController = new GrDBController(context);
                ArrayList<Gr> grs = grDBController.getAllGr();
                for (Gr gr:grs) {
                    if (gr.getNomeGr().equals(cursor.getString(17))){
                        grMembro = gr;
                    }
                }
                
                Membro mem = new Membro(cursor.getString(0),cursor.getString(1),
                        cursor.getInt(2),date,cursor.getString(4),
                        cursor.getDouble(5),cursor.getString(6),
                        cursor.getInt(7),cursor.getString(8),cursor.getString(9),
                        cursor.getString(10),cursor.getString(11),dt,
                        cursor.getString(13),cursor.getString(14),
                        cursor.getString(15),grMembro,cursor.getString(16));

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

            Gr grMembro = null;
            GrDBController grDBController = new GrDBController(context);
            ArrayList<Gr> grs = grDBController.getAllGr();
            for (Gr gr:grs) {
                if (gr.getNomeGr().equals(cursor.getString(17))){
                    grMembro = gr;
                }
            }

            membro = new Membro(cursor.getString(0),cursor.getString(1),
                    cursor.getInt(2),date,cursor.getString(4),
                    cursor.getDouble(5),cursor.getString(6),
                    cursor.getInt(7),cursor.getString(8),cursor.getString(9),
                    cursor.getString(10),cursor.getString(11),
                    dt,cursor.getString(13),cursor.getString(14),
                    cursor.getString(15),grMembro,cursor.getString(16));

        }
        return membro;

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
