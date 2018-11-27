package com.example.alessandro.apontemobile;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class VoluntarioDBOpenHelper extends SQLiteOpenHelper {

    static final int DATABASE_VERSION = 1;
    static final String DATABASE_NAME = "DBVoluntario";
    static final String TABLE_VOLUNTARIO = "TableVoluntario";

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
    static final String FUNCAO = "funcao";
    static final String HORARIO_PEGAR = "horario_pegar";
    static final String HORARIO_LARGAR = "horario_largar";
    static final String HORA_SEMANAL = "hora_semanal";
    static final String TAMANHO_CAMISA = "tamanho_camisa";
    static final String CPF = "cpf";
    static final String RG = "rg";
    static final String AREA = "area";
    static final String[]COLUNA = {NOME, NATURALIDADE, CELULAR, NASCIMENTO, SEXO, ALTURA, LOGRADOURO, NUMERO, BAIRRO,
            CEP, CIDADE, UF, _ID, FUNCAO, HORARIO_PEGAR, HORARIO_LARGAR, HORA_SEMANAL, TAMANHO_CAMISA, CPF, RG, AREA};
    final private Context mContext;

    public VoluntarioDBOpenHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        this.mContext = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_TABLE = "CREATE TABLE " + TABLE_VOLUNTARIO + "(" + NOME + " TEXT," + NATURALIDADE + " TEXT," +
                CELULAR + "INTEGER," + NASCIMENTO + " TEXT," + SEXO + " TEXT," + ALTURA + " REAL," +
                LOGRADOURO + " TEXT," + NUMERO + " INTEGER," + BAIRRO + " TEXT," + CEP + " TEXT," + CIDADE + " TEXT," +
                UF + " TEXT," + _ID + " INTEGER PRIMARY KEY AUTOINCREMENT," + FUNCAO + " TEXT," +
                HORARIO_PEGAR + " REAL," + HORARIO_LARGAR + " REAL," + HORA_SEMANAL + " REAL," +
                TAMANHO_CAMISA + " TEXT," + CPF + " INTEGER," + RG + " INTEGER," + AREA + " TEXT" + ")";
        db.execSQL(CREATE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        db.execSQL("DROP TABLE IF EXISTS " + TABLE_VOLUNTARIO);

        onCreate(db);

    }

    void deleteDatabase(){
        mContext.deleteDatabase(TABLE_VOLUNTARIO);
    }

}
