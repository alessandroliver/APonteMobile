package com.example.alessandro.apontemobile;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.alessandro.apontemobile.modelo.Material;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class MaisMaterialActivity extends AppCompatActivity {

    private Button cadastrarMaterial;
    private EditText idMaterial, nomeMaterialMaterial, valorMaterial, lojaMaterial, dataCompraMaterial, validadeMaterial,
            garantiaMaterial, tipoMaterial, finalidadeMaterial, pesoMaterial, tamanhoMaterial, quantidadeMaterialMaterial;
    private TextWatcher dataCompraMark;
    int id;
    String nomeMaterial;
    double valor;
    String finalidade;
    String garantia;
    String loja;
    Date data_compra;
    String validade;
    String tipo;
    double peso;
    double tamanho;
    int quantidadeMaterial;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mais_material);

        cadastrarMaterial = findViewById(R.id.button_cadastro_material);

        idMaterial = findViewById(R.id.material_id);
        nomeMaterialMaterial = findViewById(R.id.material_nome);
        valorMaterial = findViewById(R.id.material_valor);
        lojaMaterial = findViewById(R.id.material_loja);
        dataCompraMaterial = findViewById(R.id.material_data_compra);
        validadeMaterial = findViewById(R.id.material_validade);
        garantiaMaterial = findViewById(R.id.material_garantia);
        tipoMaterial = findViewById(R.id.material_tipo);
        finalidadeMaterial = findViewById(R.id.material_finalidade);
        pesoMaterial = findViewById(R.id.material_peso);
        tamanhoMaterial = findViewById(R.id.material_tamanho);
        quantidadeMaterialMaterial = findViewById(R.id.material_quantidade_material);

        dataCompraMark = Mask.insert("##/##/####", dataCompraMaterial);
        dataCompraMaterial.addTextChangedListener(dataCompraMark);

        cadastrarMaterial.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                id = Integer.parseInt(idMaterial.getText().toString());
                nomeMaterial = nomeMaterialMaterial.getText().toString();
                valor = Double.parseDouble(valorMaterial.getText().toString());
                finalidade = finalidadeMaterial.getText().toString();
                garantia = garantiaMaterial.getText().toString();
                loja = lojaMaterial.getText().toString();
                data_compra = null;
                DateFormat formatter1 = new SimpleDateFormat("MM/dd/yyyy");
                try {
                    data_compra = (Date)formatter1.parse(dataCompraMaterial.getText().toString());
                } catch (ParseException e) {
                    e.printStackTrace();
                }
                validade = validadeMaterial.getText().toString();
                tipo = tipoMaterial.getText().toString();
                peso = Double.parseDouble(pesoMaterial.getText().toString());
                tamanho = Double.parseDouble(tamanhoMaterial.getText().toString());
                quantidadeMaterial = Integer.parseInt(quantidadeMaterialMaterial.getText().toString());

                Material material = new Material(id,nomeMaterial,valor,finalidade,garantia,loja,data_compra,validade,
                        tipo,peso,tamanho,quantidadeMaterial);

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
