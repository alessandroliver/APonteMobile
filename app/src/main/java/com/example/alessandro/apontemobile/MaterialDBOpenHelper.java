package com.example.alessandro.apontemobile;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class MaterialDBOpenHelper extends SQLiteOpenHelper {

    static final int DATABASE_VERSION = 1;
    static final String DATABASE_NAME = "DBMaterial";
    static final String TABLE_MATERIAL = "TableMaterial";

    static final String _ID = "_id";
    static final String NOME_MATERIAL = "nome_produto";
    static final String VALOR = "valor";
    static final String FINALIDADE = "finalidade";
    static final String GARANTIA = "garantia";
    static final String LOJA = "loja";
    static final String DATA_COMPRA = "data_compra";
    static final String VALIDADE = "validade";
    static final String TIPO = "tipo";
    static final String PESO = "peso";
    static final String TAMANHO = "tamanho";
    static final String QUANTIDADE_MATERIAL = "quantidade_material";
    static final String[]COLUNA = {_ID, NOME_MATERIAL, VALOR, FINALIDADE, GARANTIA, LOJA, DATA_COMPRA, VALIDADE, TIPO,
            PESO, TAMANHO, QUANTIDADE_MATERIAL};
    final private Context mContext;

    public MaterialDBOpenHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        this.mContext = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_TABLE = "CREATE TABLE " + TABLE_MATERIAL + "(" + _ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
                NOME_MATERIAL + "TEXT," + VALOR + " REAL," + FINALIDADE + " TEXT," + GARANTIA + " TEXT," +
                LOJA + " TEXT," + DATA_COMPRA + " TEXT," + VALIDADE + " TEXT," + TIPO + " TEXT," + PESO + " REAL," +
                TAMANHO + " REAL," + QUANTIDADE_MATERIAL + " INTEGER" + ")";
        db.execSQL(CREATE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        db.execSQL("DROP TABLE IF EXISTS " + TABLE_MATERIAL);

        onCreate(db);

    }

    void deleteDatabase(){
        mContext.deleteDatabase(TABLE_MATERIAL);
    }

}
