package com.example.alessandro.myapplication;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextWatcher;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.alessandro.myapplication.dao.GrDAO;
import com.example.alessandro.myapplication.modelo.Gr;
import com.example.alessandro.myapplication.modelo.Membro;

import com.example.alessandro.myapplication.dao.MembroDAO;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class MaisMembroActivity extends AppCompatActivity {

    private MaisMembroHelper helper;
    private Spinner ufMembro, coleteMembro, grMembro;
    private TextWatcher nascimentoMask, conversaoMask;
    private EditText nascimentoMembro, conversaoMembro;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mais_membro);

        nascimentoMembro = findViewById(R.id.membro_nascimento);

        nascimentoMask = Mask.insert("##/##/####", nascimentoMembro);
        nascimentoMembro.addTextChangedListener(nascimentoMask);

        conversaoMembro = findViewById(R.id.membro_data_conversao);

        conversaoMask = Mask.insert("##/##/####", conversaoMembro);
        conversaoMembro.addTextChangedListener(conversaoMask);

        ufMembro = (Spinner) findViewById(R.id.membro_sp_state);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.states, android.R.layout.simple_spinner_item);
        ufMembro.setAdapter(adapter);

        coleteMembro = (Spinner) findViewById(R.id.membro_sp_colete);
        ArrayAdapter<CharSequence> adapter1 = ArrayAdapter.createFromResource(this,
                R.array.coletes, android.R.layout.simple_spinner_item);
        coleteMembro.setAdapter(adapter1);

        grMembro = (Spinner) findViewById(R.id.sp_membro_gr);

        GrDAO grDAO = new GrDAO(this);
        ArrayList<String> nomesGrs = new ArrayList<String>();
        List<Gr> grs1 = grDAO.buscaGrs();
        for (Gr gr: grs1) {
            nomesGrs.add(gr.getNomeGr());
        }

        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, nomesGrs);
        ArrayAdapter<String> spinnerArrayAdapter = arrayAdapter;
        spinnerArrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_item);
        grMembro.setAdapter(spinnerArrayAdapter);

        helper = new MaisMembroHelper(this, this);

        Intent intent = getIntent();
        Membro membro = (Membro) intent.getSerializableExtra("membro");

        if(membro != null){
            helper.preencheMembro(membro);
        }

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_formulario, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.menu_formulario_ok:
                Membro membro = helper.getMembro();
                MembroDAO dao = new MembroDAO(this);

                if(membro.getId() != 0){
                    dao.altera(membro);
                }else{
                    GrDAO grDAO = new GrDAO(this);
                    grDAO.insereMembroGr(membro, helper.getNomeCurso());
                    dao.insere(membro);
                }


                dao.close();
                Toast.makeText(MaisMembroActivity.this, "Membro " + membro.getNome() + " salvo!",
                        Toast.LENGTH_SHORT).show();
                finish();
                break;
        }
        return super.onOptionsItemSelected(item);
    }

}
