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

public class MaisGrActivity extends AppCompatActivity {

    private MaisGrHelper helper;
    private Spinner ufGr;
    private TextWatcher inauguracaoMask;
    private EditText inauguracaoGr;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mais_gr);

        inauguracaoGr = findViewById(R.id.gr_inauguracao);

        inauguracaoMask = Mask.insert("##/##/####", inauguracaoGr);
        inauguracaoGr.addTextChangedListener(inauguracaoMask);

        ufGr = (Spinner) findViewById(R.id.gr_sp_state);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.states, android.R.layout.simple_spinner_item);
        ufGr.setAdapter(adapter);

        helper = new MaisGrHelper(this, this);

        Intent intent = getIntent();
        Gr gr = (Gr) intent.getSerializableExtra("gr");

        if(gr != null){
            helper.preencheGr(gr);
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
                Gr gr = helper.getGr();
                GrDAO dao = new GrDAO(this);

                if(gr.getId() != 0){
                    dao.altera(gr);
                }else{
                    dao.insere(gr);
                }


                dao.close();
                Toast.makeText(MaisGrActivity.this, "Gr " + gr.getNomeGr() + " salvo!", Toast.LENGTH_SHORT).show();
                finish();
                break;
        }
        return super.onOptionsItemSelected(item);
    }

}