package com.example.alessandro.apontemobile;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class MembroDBOpenHelper extends SQLiteOpenHelper {

    static final int DATABASE_VERSION = 1;

    static final String DATABASE_NAME = "DBMembro";

    static final String TABLE_MEMBRO = "TableMembro";
    static final String TABLE_MEMBRO_GR = "TableMembroGr";

    static final String NOME = "nome";
    static final String NATURALIDADE = "naturalidade";
    static final String CELULAR = "celular";
    static final String NASCIMENTO = "nascimento";
    static final String SEXO = "sexo";
    static final String ALTURA = "altura";
    static final String LOGRADOURO = "logradouro";
    static final String NUMERO = "numero";
    static final String BAIRRO = "bairro";
    static final String CEP = "cep";
    static final String CIDADE = "cidade";
    static final String UF = "uf";
    static final String _ID = "_id";
    static final String DATA_CONVERSAO = "data_conversao";
    static final String EQUIPE = "equipe";
    static final String TEMPO_PONTE = "tempo_ponte";
    static final String CARGO = "cargo";
    static final String COLETE = "colete";
    //coluna a ser usada nas querys do DB
    static final String[]COLUNA = {NOME, NATURALIDADE, CELULAR, NASCIMENTO, SEXO, ALTURA, LOGRADOURO, NUMERO,
            BAIRRO, CEP, CIDADE, UF, _ID, DATA_CONVERSAO, EQUIPE, TEMPO_PONTE, CARGO, COLETE};

    static final String IDGR = "idGr";
    static final String NOME_GRGR = "nomeGr";
    static final String QUANTIDADEGR = "quantidadeGr";
    static final String HORARIOGR = "horarioGr";
    static final String DIAGR = "diaGr";
    static final String FREQUENCIAGR = "frequenciaGr";
    static final String LIDERGR = "liderGr";
    static final String APOIOGR = "apoioGr";
    static final String CONTATOGR = "contatoGr";
    static final String INAUGURACAOGR = "inauguracaoGr";
    static final String LOGRADOUROGR = "logradouroGr";
    static final String NUMEROGR = "numeroGr";
    static final String BAIRROGR = "bairroGr";
    static final String CEPGR = "cepGr";
    static final String CIDADEGR = "cidadeGr";
    static final String UFGR = "ufGr";
    static final String NOME_Gr = "nomeMembroGr";
    static final String[] COLUNA_GR = {NOME_Gr, IDGR, NOME_GRGR, QUANTIDADEGR, HORARIOGR, DIAGR, FREQUENCIAGR, LIDERGR,
            APOIOGR, CONTATOGR, INAUGURACAOGR, LOGRADOUROGR, NUMEROGR, BAIRROGR, CEPGR, CIDADEGR, UFGR};
    final private Context mContext;

    public MembroDBOpenHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        this.mContext = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_TABLE = "CREATE TABLE " + TABLE_MEMBRO + "(" + NOME + " TEXT," + NATURALIDADE + " TEXT," +
                CELULAR + "INTEGER," + NASCIMENTO + " TEXT," + SEXO + " TEXT," + ALTURA + " REAL," +
                LOGRADOURO + " TEXT," + NUMERO + " INTEGER," + BAIRRO + " TEXT," + CEP + " TEXT," + CIDADE + " TEXT," +
                UF + " TEXT," + _ID + " INTEGER PRIMARY KEY AUTOINCREMENT," + DATA_CONVERSAO + " TEXT," +
                EQUIPE + " TEXT," + TEMPO_PONTE + " TEXT," + CARGO + " TEXT," + COLETE + " TEXT" + ")";

        String CREATE_TABLE_MEMBRO_GR = "CREATE TABLE " + TABLE_MEMBRO_GR + "(" + NOME_Gr + " TEXT," +
                IDGR + " INTEGER," + NOME_GRGR + "TEXT," + QUANTIDADEGR + " INTEGER," + HORARIOGR + " REAL," +
                DIAGR + " TEXT," + FREQUENCIAGR + " TEXT," + LIDERGR + " TEXT," + APOIOGR + " TEXT," +
                CONTATOGR + " INTEGER," + INAUGURACAOGR + " TEXT," + LOGRADOUROGR + " TEXT," + NUMEROGR + " INTEGER," +
                BAIRROGR + " TEXT," + CEPGR + " INTEGER," + CIDADEGR + " TEXT," + UFGR + " TEXT" + ")";

        db.execSQL(CREATE_TABLE);
        db.execSQL(CREATE_TABLE_MEMBRO_GR);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        db.execSQL("DROP TABLE IF EXISTS " + TABLE_MEMBRO);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_MEMBRO_GR);

        onCreate(db);

    }

    void deleteDatabase(){
        mContext.deleteDatabase(TABLE_MEMBRO);
    }

}
