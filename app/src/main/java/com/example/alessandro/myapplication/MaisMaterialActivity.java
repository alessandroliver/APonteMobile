package com.example.alessandro.myapplication;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextWatcher;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.alessandro.myapplication.dao.MaterialDAO;
import com.example.alessandro.myapplication.modelo.Material;

public class MaisMaterialActivity extends AppCompatActivity {

    private MaisMaterialHelper helper;
    private TextWatcher compraMask;
    private EditText compraMaterial;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mais_material);

        compraMaterial = findViewById(R.id.material_data_compra);

        compraMask = Mask.insert("##/##/####", compraMaterial);
        compraMaterial.addTextChangedListener(compraMask);

        helper = new MaisMaterialHelper(this);

        Intent intent = getIntent();
        Material material = (Material) intent.getSerializableExtra("material");

        if(material != null){
            helper.preencheMaterial(material);
        }

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_formulario, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.menu_formulario_ok:
                Material material = helper.getMaterial();
                MaterialDAO dao = new MaterialDAO(this);

                if(material.getId() != 0){
                    dao.altera(material);
                }else{
                    dao.insere(material);
                }


                dao.close();
                Toast.makeText(MaisMaterialActivity.this, "Material " + material.getNomeMaterial() + " salvo!",
                        Toast.LENGTH_SHORT).show();
                finish();
                break;
        }
        return super.onOptionsItemSelected(item);
    }

}
