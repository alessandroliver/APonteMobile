package com.example.alessandro.apontemobile;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MaisCursoActivity extends AppCompatActivity {

    private Button cadastrarCurso;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mais_curso);

        cadastrarCurso = findViewById(R.id.button_cadastro_curso);

        cadastrarCurso.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                alert("Curso cadastrado com sucesso!");
                Intent intent = new Intent(MaisCursoActivity.this, InicioActivity.class);
                startActivity(intent);
            }
        });

    }

    private void alert(String s){
        Toast.makeText(this,s,Toast.LENGTH_LONG).show();
    }

}
