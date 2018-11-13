package com.example.alessandro.apontemobile;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class FuncionarioActivity extends AppCompatActivity {

    private Button maisFuncionario;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_funcionario);

        maisFuncionario = findViewById(R.id.add_funcionario);

        maisFuncionario.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(FuncionarioActivity.this, MaisFuncionarioActivity.class);
                startActivity(intent);
            }
        });

    }
}
