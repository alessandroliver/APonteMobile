package com.example.alessandro.apontemobile;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class InicioActivity extends AppCompatActivity {

    private Button membro, voluntario, aluno, funcionario, shop, cafe, gr, material;

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

        membro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(InicioActivity.this, MembroActivity.class);
                startActivity(intent);
            }
        });
        voluntario.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(InicioActivity.this, VoluntarioActivity.class);
                startActivity(intent);
            }
        });
        aluno.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(InicioActivity.this, AlunoActivity.class);
                startActivity(intent);
            }
        });
        funcionario.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(InicioActivity.this, FuncionarioActivity.class);
                startActivity(intent);
            }
        });
        shop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(InicioActivity.this, ShopActivity.class);
                startActivity(intent);
            }
        });
        cafe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(InicioActivity.this, CafeActivity.class);
                startActivity(intent);
            }
        });
        gr.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(InicioActivity.this, GrActivity.class);
                startActivity(intent);
            }
        });
        material.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(InicioActivity.this, MaterialActivity.class);
                startActivity(intent);
            }
        });

    }
}
