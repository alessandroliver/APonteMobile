package com.example.alessandro.apontemobile;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import com.example.alessandro.apontemobile.modelo.Shop;
import com.example.alessandro.apontemobile.modelo.Voluntario;

import java.text.ParseException;
import java.util.List;

public class VoluntarioActivity extends AppCompatActivity {

    private Button maisVoluntario;
    private List<Voluntario> listaVoluntario = null;
    public static String acao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_voluntario);

        try {
            listaVoluntario = new VoluntarioDBController(this).getAllVoluntario();
        } catch (ParseException e) {
            e.printStackTrace();
        }

        Bundle extras = getIntent().getExtras();

        if (extras != null) {
            acao = extras.getString("acaoExtra");
        }

        ListView listavoluntario = findViewById(R.id.lista_voluntarios);
        ArrayAdapter<Voluntario> adapter = new ArrayAdapter<Voluntario>(this, android.R.layout.simple_list_item_1,
                listaVoluntario);
        listavoluntario.setAdapter(adapter);

        maisVoluntario = findViewById(R.id.add_voluntario);

        maisVoluntario.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(VoluntarioActivity.this, MaisVoluntarioActivity.class);
                startActivity(intent);
            }
        });

    }
}
