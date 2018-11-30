package com.example.alessandro.apontemobile;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import com.example.alessandro.apontemobile.modelo.Membro;

import java.text.ParseException;
import java.util.List;

public class MembroActivity extends AppCompatActivity {

    private Button maisMembro;
    private List<Membro> listaMembros = null;
    public static String acao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_membro);

        try {
            listaMembros = new MembroDBController(this).getAllMembro();
        } catch (ParseException e) {
            e.printStackTrace();
        }

        Bundle extras = getIntent().getExtras();

        if (extras != null) {
            acao = extras.getString("acaoExtra");
        }

        ListView listamembros = findViewById(R.id.lista_membros);
        ArrayAdapter<Membro> adapter = new ArrayAdapter<Membro>(this, android.R.layout.simple_list_item_1,
                listaMembros);
        listamembros.setAdapter(adapter);

        maisMembro = findViewById(R.id.add_membro);

        maisMembro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MembroActivity.this, MaisMembroActivity.class);
                startActivity(intent);
            }
        });

    }
}
