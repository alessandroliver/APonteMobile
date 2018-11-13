package com.example.alessandro.apontemobile;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class CursoActivity extends AppCompatActivity {

    private Button maisCurso;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_curso);

        maisCurso = findViewById(R.id.add_curso);

        maisCurso.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(CursoActivity.this, MaisCursoActivity.class);
                startActivity(intent);
            }
        });

    }
}
