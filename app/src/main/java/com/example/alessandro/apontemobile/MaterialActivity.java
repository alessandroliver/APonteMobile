package com.example.alessandro.apontemobile;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.view.ContextMenu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;
import android.os.Bundle;

import com.example.alessandro.apontemobile.modelo.Material;
import com.example.alessandro.apontemobile.MaterialDBController;

import java.text.ParseException;
import java.util.List;

public class MaterialActivity extends AppCompatActivity {

    private Button maisMaterial;
    private List<Material> listaMaterial = null;
    public static String acao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_material);

        try {
            listaMaterial = new MaterialDBController(this).getAllMaterial();
        } catch (ParseException e) {
            e.printStackTrace();
        }

        Bundle extras = getIntent().getExtras();

        if (extras != null) {
            acao = extras.getString("acaoExtra");
        }

        ListView listamaterial = findViewById(R.id.lista_materiais);
        ArrayAdapter<Material> adapter = new ArrayAdapter<Material>(this, android.R.layout.simple_list_item_1,
                listaMaterial);
        listamaterial.setAdapter(adapter);

        maisMaterial = findViewById(R.id.add_material);

        maisMaterial.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MaterialActivity.this, MaisMaterialActivity.class);
                startActivity(intent);
            }
        });
    }

}
