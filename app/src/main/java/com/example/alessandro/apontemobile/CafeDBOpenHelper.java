package com.example.alessandro.apontemobile;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class CafeDBOpenHelper extends SQLiteOpenHelper {

    static final int DATABASE_VERSION = 1;

    static final String DATABASE_NAME = "DBCafe";

    static final String TABLE_CAFE = "TableCafe";

    static final String NOME_PRODUTO = "nome_produto";
    static final String VALOR_COMPRA = "valor_compra";
    static final String VALOR_VENDA = "valor_venda";
    static final String QUANTIDADE = "quantidade";
    static final String LOJA = "loja";
    static final String LOCAL_FABRICACAO = "local_fabricacao";
    static final String DATA_COMPRA = "data_compra";
    static final String MARCA = "marca";
    static final String _ID = "_id";
    static final String SABOR = "sabor";
    static final String TEMPERATURA = "temperatura";
    static final String INGREDIENTE = "ingrediente";
    static final String VALIDADE = "validade";
    static final String PESO = "peso";
    static final String[]COLUNA = {NOME_PRODUTO, VALOR_COMPRA, VALOR_VENDA, QUANTIDADE, LOJA, LOCAL_FABRICACAO,
            DATA_COMPRA, MARCA, _ID, SABOR, TEMPERATURA, INGREDIENTE, VALIDADE, PESO};
    final private Context mContext;

    public CafeDBOpenHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        this.mContext = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_TABLE = "CREATE TABLE " + TABLE_CAFE + "(" + NOME_PRODUTO + "TEXT," + VALOR_COMPRA + " REAL," +
                VALOR_VENDA + " REAL," + QUANTIDADE + " INTEGER," + LOJA + " TEXT," + LOCAL_FABRICACAO + " TEXT," +
                DATA_COMPRA + " TEXT," + MARCA + " TEXT," + _ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
                SABOR + " TEXT," + TEMPERATURA + " TEXT," + INGREDIENTE + " TEXT," + VALIDADE + " TEXT," +
                PESO + " REAL" + ")";
        db.execSQL(CREATE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        db.execSQL("DROP TABLE IF EXISTS " + TABLE_CAFE);

        onCreate(db);

    }

    void deleteDatabase(){
        mContext.deleteDatabase(TABLE_CAFE);
    }
}
