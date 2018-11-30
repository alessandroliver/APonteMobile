package com.example.alessandro.apontemobile;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import com.example.alessandro.apontemobile.modelo.Cafe;

import java.text.ParseException;
import java.util.List;

public class CafeActivity extends AppCompatActivity {

    private Button maisCafe;
    private List<Cafe> listaCafe = null;
    public static String acao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cafe);

        try {
            listaCafe = new CafeDBController(this).getAllCafe();
        } catch (ParseException e) {
            e.printStackTrace();
        }

        Bundle extras = getIntent().getExtras();

        if (extras != null) {
            acao = extras.getString("acaoExtra");
        }

        ListView listacafe = findViewById(R.id.lista_cafes);
        ArrayAdapter<Cafe> adapter = new ArrayAdapter<Cafe>(this, android.R.layout.simple_list_item_1, listaCafe);
        listacafe.setAdapter(adapter);

        maisCafe = findViewById(R.id.add_cafe);

        maisCafe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(CafeActivity.this, MaisCafeActivity.class);
                startActivity(intent);
            }
        });

    }

}
