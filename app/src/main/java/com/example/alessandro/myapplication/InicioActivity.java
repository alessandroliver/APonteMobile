package com.example.alessandro.myapplication;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class InicioActivity extends AppCompatActivity {

    private Button membro, voluntario, aluno, funcionario, shop, cafe, gr, material, curso, sair;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inicio);

        membro = findViewById(R.id.membro);
        voluntario = findViewById(R.id.voluntario);
        aluno = findViewById(R.id.aluno);
        funcionario = findViewById(R.id.funcionario);
        shop = findViewById(R.id.shop);
        cafe = findViewById(R.id.cafe);
        gr = findViewById(R.id.gr);
        material = findViewById(R.id.material);
        curso = findViewById(R.id.curso);
        sair = findViewById(R.id.sair);

        membro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(InicioActivity.this, ListaMembroActivity.class);
                startActivity(intent);
            }
        });
        voluntario.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(InicioActivity.this, ListaVoluntarioActivity.class);
                startActivity(intent);
            }
        });
        aluno.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(InicioActivity.this, ListaAlunoActivity.class);
                startActivity(intent);
            }
        });
        funcionario.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(InicioActivity.this, ListaFuncionarioActivity.class);
                startActivity(intent);
            }
        });
        shop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(InicioActivity.this, ListaShopActivity.class);
                startActivity(intent);
            }
        });
        cafe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(InicioActivity.this, ListaCafeActivity.class);
                startActivity(intent);
            }
        });
        gr.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(InicioActivity.this, ListaGrActivity.class);
                startActivity(intent);
            }
        });
        material.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(InicioActivity.this, ListaMaterialActivity.class);
                startActivity(intent);
            }
        });
        curso.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(InicioActivity.this, ListaCursoActivity.class);
                startActivity(intent);
            }
        });
        sair.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}
