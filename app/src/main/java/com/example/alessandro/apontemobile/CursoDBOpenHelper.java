package com.example.alessandro.apontemobile;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class CursoDBOpenHelper extends SQLiteOpenHelper {

    static final int DATABASE_VERSION = 1;
    static final String DATABASE_NAME = "DBCurso";
    static final String TABLE_CURSO = "TableCurso";

    static final String _ID = "_id";
    static final String NOME_CURSO = "nome_curso";
    static final String INICIO = "inicio";
    static final String FIM = "fim";
    static final String CARGA_HORARIA = "carga_horaria";
    static final String PROFESSOR = "professor";
    static final String DIA = "dia";
    static final String PEGAR = "pegar";
    static final String LARGAR = "largar";
    static final String SALA = "sala";
    static final String[]COLUNA = {_ID, NOME_CURSO, INICIO, FIM, CARGA_HORARIA, PROFESSOR, DIA, PEGAR, LARGAR, SALA};
    final private Context mContext;

    public CursoDBOpenHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        this.mContext = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_TABLE = "CREATE TABLE " + TABLE_CURSO + "(" + _ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
                NOME_CURSO + "TEXT," + INICIO + " TEXT," + FIM + " TEXT," + CARGA_HORARIA + " INTEGER," +
                PROFESSOR + " TEXT," + DIA + " TEXT," + PEGAR + " REAL," + LARGAR + " REAL," + SALA + " TEXT" + ")";
        db.execSQL(CREATE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        db.execSQL("DROP TABLE IF EXISTS " + TABLE_CURSO);

        onCreate(db);

    }

    void deleteDatabase(){
        mContext.deleteDatabase(TABLE_CURSO);
    }

}