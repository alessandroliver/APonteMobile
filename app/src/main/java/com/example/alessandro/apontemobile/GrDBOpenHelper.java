package com.example.alessandro.apontemobile;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class GrDBOpenHelper extends SQLiteOpenHelper {

    static final int DATABASE_VERSION = 1;
    static final String DATABASE_NAME = "DBGr";
    static final String TABLE_GR = "TableGr";

    static final String _ID = "_id";
    static final String NOME_GR = "nome_gr";
    static final String QUANTIDADE = "quantidade";
    static final String HORARIO = "horario";
    static final String DIA = "dia";
    static final String FREQUENCIA = "frequencia";
    static final String LIDER = "lider";
    static final String APOIO = "apoio";
    static final String CONTATO = "contato";
    static final String INAUGURACAO = "inauguracao";
    static final String LOGRADOURO = "logradouro";
    static final String NUMERO = "numero";
    static final String BAIRRO = "bairro";
    static final String CEP = "cep";
    static final String CIDADE = "cidade";
    static final String UF = "uf";
    static final String[]COLUNA = {_ID, NOME_GR, QUANTIDADE, HORARIO, DIA, FREQUENCIA, LIDER, APOIO, CONTATO, INAUGURACAO,
            LOGRADOURO, NUMERO, BAIRRO, CEP, CIDADE, UF};
    final private Context mContext;

    public GrDBOpenHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        this.mContext = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_TABLE = "CREATE TABLE " + TABLE_GR + "(" + _ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
                NOME_GR + "TEXT," + QUANTIDADE + " INTEGER," + HORARIO + " REAL," + DIA + " TEXT," +
                FREQUENCIA + " TEXT," + LIDER + " TEXT," + APOIO + " TEXT," + CONTATO + " INTEGER," +
                INAUGURACAO + " TEXT," + LOGRADOURO + " TEXT," + NUMERO + " INTEGER," + BAIRRO + " TEXT," +
                CEP + " INTEGER," + CIDADE + " TEXT," + UF + " TEXT" + ")";
        db.execSQL(CREATE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        db.execSQL("DROP TABLE IF EXISTS " + TABLE_GR);

        onCreate(db);

    }

    void deleteDatabase(){
        mContext.deleteDatabase(TABLE_GR);
    }

}
