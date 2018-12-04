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

import com.example.alessandro.myapplication.dao.VoluntarioDAO;
import com.example.alessandro.myapplication.modelo.Voluntario;

public class MaisVoluntarioActivity extends AppCompatActivity {

    private MaisVoluntarioHelper helper;
    private Spinner ufVoluntario, camisaVoluntario;
    private TextWatcher nascimentoMask;
    private EditText nascimentoVoluntario;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mais_voluntario);

        nascimentoVoluntario = findViewById(R.id.voluntario_nascimento);

        nascimentoMask = Mask.insert("##/##/####", nascimentoVoluntario);
        nascimentoVoluntario.addTextChangedListener(nascimentoMask);

        ufVoluntario = (Spinner) findViewById(R.id.voluntario_sp_state);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.states, android.R.layout.simple_spinner_item);
        ufVoluntario.setAdapter(adapter);

        camisaVoluntario = (Spinner) findViewById(R.id.voluntario_sp_camisa);
        ArrayAdapter<CharSequence> adapter1 = ArrayAdapter.createFromResource(this,
                R.array.camisas, android.R.layout.simple_spinner_item);
        camisaVoluntario.setAdapter(adapter1);

        helper = new MaisVoluntarioHelper(this, this);

        Intent intent = getIntent();
        Voluntario voluntario = (Voluntario) intent.getSerializableExtra("voluntario");

        if(voluntario != null){
            helper.preencheVoluntario(voluntario);
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
                Voluntario voluntario = helper.getVoluntario();
                VoluntarioDAO dao = new VoluntarioDAO(this);

                if(voluntario.getId() != 0){
                    dao.altera(voluntario);
                }else{
                    dao.insere(voluntario);
                }


                dao.close();
                Toast.makeText(MaisVoluntarioActivity.this, "Voluntario " + voluntario.getNome() + " salvo!",
                        Toast.LENGTH_SHORT).show();
                finish();
                break;
        }
        return super.onOptionsItemSelected(item);
    }

}
