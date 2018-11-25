package com.example.alessandro.apontemobile;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextWatcher;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

public class MaisCafeActivity extends AppCompatActivity {

    private Button cadastrarCafe;
    private EditText dataCompraCafe;
    private TextWatcher dataCompraMark;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mais_cafe);

        cadastrarCafe = findViewById(R.id.button_cadastro_cafe);

        Spinner spTemperaturaCafe = (Spinner) findViewById(R.id.sp_cafe_temperatura);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.temperatura, android.R.layout.simple_spinner_item);
        spTemperaturaCafe.setAdapter(adapter);

        dataCompraCafe = findViewById(R.id.cafe_data_compra);

        dataCompraMark = Mask.insert("##/##/####", dataCompraCafe);
        dataCompraCafe.addTextChangedListener(dataCompraMark);

        cadastrarCafe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                alert("Produto da Ponte Café cadastrado com sucesso!");
                Intent intent = new Intent(MaisCafeActivity.this, InicioActivity.class);
                startActivity(intent);
            }
        });

    }

    private void alert(String s){
        Toast.makeText(this,s,Toast.LENGTH_LONG).show();
    }

}
