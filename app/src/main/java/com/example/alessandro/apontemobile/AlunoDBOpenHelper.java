package com.example.alessandro.apontemobile;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class AlunoDBOpenHelper extends SQLiteOpenHelper {

    static final int DATABASE_VERSION = 1;

    static final String DATABASE_NAME = "DBAluno";

    static final String TABLE_ALUNO = "TableAluno";
    static final String TABLE_ALUNO_CURSO = "TableAlunoCurso";

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
    static final String MATRICULA = "matricula";
    static final String FARDA = "farda";
    static final String RESPONSAVEL = "resp";
    static final String SERIE = "serie";
    static final String ESCOLA = "escola";
    static final String[]COLUNA = {NOME, NATURALIDADE, CELULAR, NASCIMENTO, SEXO, ALTURA, LOGRADOURO, NUMERO,
            BAIRRO, CEP, CIDADE, UF, _ID, MATRICULA, FARDA, RESPONSAVEL, SERIE, ESCOLA};

    static final String _IDCURSO = "_idCurso";
    static final String NOME_CURSOCURSO = "nome_cursoCurso";
    static final String INICIOCURSO = "inicioCurso";
    static final String FIMCURSO = "fimCurso";
    static final String CARGA_HORARIACURSO = "carga_horariaCurso";
    static final String PROFESSORCURSO = "professorCurso";
    static final String DIACURSO = "diaCurso";
    static final String PEGARCURSO = "pegarCurso";
    static final String LARGARCURSO = "largarCurso";
    static final String SALACURSO = "salaCurso";
    static final String MATRICULA_Cur = "matriculaAlunoCurso";
    static final String[]COLUNA_CURSO = {MATRICULA_Cur, _IDCURSO, NOME_CURSOCURSO, INICIOCURSO, FIMCURSO,
            CARGA_HORARIACURSO, PROFESSORCURSO, DIACURSO, PEGARCURSO, LARGARCURSO, SALACURSO};
    final private Context mContext;

    public AlunoDBOpenHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        this.mContext = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_TABLE = "CREATE TABLE " + TABLE_ALUNO + "(" + NOME + " TEXT," + NATURALIDADE + " TEXT," +
                CELULAR + "INTEGER," + NASCIMENTO + " TEXT," + SEXO + " TEXT," + ALTURA + " REAL," +
                LOGRADOURO + " TEXT," + NUMERO + " INTEGER," + BAIRRO + " TEXT," + CEP + " TEXT," + CIDADE + " TEXT," +
                UF + " TEXT," + _ID + " INTEGER PRIMARY KEY AUTOINCREMENT," + MATRICULA + " TEXT," +
                FARDA + " TEXT," + RESPONSAVEL + " TEXT," + SERIE + " TEXT," + ESCOLA + " TEXT" + ")";

        String CREATE_TABLE_ALUNO_CURSO = "CREATE TABLE " + TABLE_ALUNO_CURSO + "(" + MATRICULA_Cur + " TEXT," +
                _IDCURSO + " INTEGER," + NOME_CURSOCURSO + "TEXT," + INICIOCURSO + " TEXT," + FIMCURSO + " TEXT," +
                CARGA_HORARIACURSO + " INTEGER," + PROFESSORCURSO + " TEXT," + DIACURSO + " TEXT," +
                PEGARCURSO + " REAL," + LARGARCURSO + " REAL," + SALACURSO + " TEXT" + ")";

        db.execSQL(CREATE_TABLE);
        db.execSQL(CREATE_TABLE_ALUNO_CURSO);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        db.execSQL("DROP TABLE IF EXISTS " + TABLE_ALUNO);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_ALUNO_CURSO);

        onCreate(db);

    }

    void deleteDatabase(){
        mContext.deleteDatabase(TABLE_ALUNO);
    }

}
