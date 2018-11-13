package com.example.alessandro.apontemobile;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class AlunoActivity extends AppCompatActivity {

    private Button maisAluno;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_aluno);

        maisAluno = findViewById(R.id.add_aluno);

        maisAluno.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AlunoActivity.this, MaisAlunoActivity.class);
                startActivity(intent);
            }
        });

    }
}
