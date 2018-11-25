package com.example.alessandro.apontemobile;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MaisMaterialActivity extends AppCompatActivity {

    private Button cadastrarMaterial;
    private EditText dataCompraMaterial;
    private TextWatcher dataCompraMark;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mais_material);

        cadastrarMaterial = findViewById(R.id.button_cadastro_material);

        dataCompraMaterial = findViewById(R.id.material_data_compra);

        dataCompraMark = Mask.insert("##/##/####", dataCompraMaterial);
        dataCompraMaterial.addTextChangedListener(dataCompraMark);

        cadastrarMaterial.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                alert("Material cadastrado com sucesso!");
                Intent intent = new Intent(MaisMaterialActivity.this, InicioActivity.class);
                startActivity(intent);
            }
        });

    }

    private void alert(String s){
        Toast.makeText(this,s,Toast.LENGTH_LONG).show();
    }

}
