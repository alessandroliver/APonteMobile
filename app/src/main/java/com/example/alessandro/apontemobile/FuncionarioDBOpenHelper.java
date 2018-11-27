package com.example.alessandro.apontemobile;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class FuncionarioDBOpenHelper extends SQLiteOpenHelper {

    static final int DATABASE_VERSION = 1;
    static final String DATABASE_NAME = "DBFuncionario";
    static final String TABLE_FUNCIONARIO = "TableFuncionario";

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
    static final String CARGO = "cargo";
    static final String SALARIO = "salario";
    static final String HORA_SEMANAL = "hora_semanal";
    static final String CARTEIRA_TRABALHO = "carteira_trabalho";
    static final String PIS = "pis";
    static final String CPF = "cpf";
    static final String RG = "rg";
    static final String CONTA = "conta";
    static final String BANCO = "banco";
    static final String AGENCIA = "agencia";
    static final String CAMISA = "camisa";
    static final String ENTRADA = "entrada";
    static final String PAGAMENTO = "pagamento";
    static final String[]COLUNA = {NOME, NATURALIDADE, CELULAR, NASCIMENTO, SEXO, ALTURA, LOGRADOURO, NUMERO, BAIRRO,
            CEP, CIDADE, UF, _ID, CARGO, SALARIO, HORA_SEMANAL, CARTEIRA_TRABALHO, PIS, CPF, RG, CONTA, BANCO, AGENCIA,
            CAMISA, ENTRADA, PAGAMENTO};
    final private Context mContext;

    public FuncionarioDBOpenHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        this.mContext = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_TABLE = "CREATE TABLE " + TABLE_FUNCIONARIO + "(" + NOME + " TEXT," + NATURALIDADE + " TEXT," +
                CELULAR + "INTEGER," + NASCIMENTO + " TEXT," + SEXO + " TEXT," + ALTURA + " REAL," +
                LOGRADOURO + " TEXT," + NUMERO + " INTEGER," + BAIRRO + " TEXT," + CEP + " TEXT," + CIDADE + " TEXT," +
                UF + " TEXT," + _ID + " INTEGER PRIMARY KEY AUTOINCREMENT," + CARGO + " TEXT," + SALARIO + " REAL," +
                HORA_SEMANAL + " INTEGER," + CARTEIRA_TRABALHO + " INTEGER," + PIS + " INTEGER," + CPF + " INTEGER," +
                RG + " INTEGER," + CONTA + " TEXT," + BANCO + " TEXT," + AGENCIA + " INTEGER," + CAMISA + " TEXT," +
                ENTRADA + " TEXT," + PAGAMENTO + " TEXT" + ")";
        db.execSQL(CREATE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        db.execSQL("DROP TABLE IF EXISTS " + TABLE_FUNCIONARIO);

        onCreate(db);

    }

    void deleteDatabase(){
        mContext.deleteDatabase(TABLE_FUNCIONARIO);
    }

}
