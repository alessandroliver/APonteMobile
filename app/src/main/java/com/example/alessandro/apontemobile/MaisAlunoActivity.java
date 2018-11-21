package com.example.alessandro.apontemobile;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

public class MaisAlunoActivity extends AppCompatActivity {

    private Button maisCurso;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mais_aluno);

        maisCurso = findViewById(R.id.button_cursos);

        Spinner spStates = (Spinner) findViewById(R.id.sp_state);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.states, android.R.layout.simple_spinner_item);
        spStates.setAdapter(adapter);

        Spinner spStates1 = (Spinner) findViewById(R.id.sp_farda);
        ArrayAdapter<CharSequence> adapter1 = ArrayAdapter.createFromResource(this,
                R.array.camisas, android.R.layout.simple_spinner_item);
        spStates1.setAdapter(adapter1);

        maisCurso.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MaisAlunoActivity.this, AlunoActivity.class);
                startActivity(intent);
            }
        });

    }
}
