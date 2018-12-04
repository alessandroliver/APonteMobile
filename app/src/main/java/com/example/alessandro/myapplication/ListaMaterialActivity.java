package com.example.alessandro.myapplication;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
//import android.support.v7.internal.widget.AdapterViewCompat;
import android.view.ContextMenu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import java.util.List;

import com.example.alessandro.myapplication.dao.MaterialDAO;
import com.example.alessandro.myapplication.modelo.Material;

public class ListaMaterialActivity extends AppCompatActivity {

    private ListView listaMateriais;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_material);

        listaMateriais = (ListView) findViewById(R.id.lista_materiais);

        listaMateriais.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
                Material material = (Material) listaMateriais.getItemAtPosition(position);
                Intent intentVaiProFormulario = new Intent(ListaMaterialActivity.this, MaisMaterialActivity.class);
                intentVaiProFormulario.putExtra("material", material);
                startActivity(intentVaiProFormulario);
            }
        });

        Button novoMaterial = (Button) findViewById(R.id.add_material);
        novoMaterial.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intentVaiProFormulario = new Intent(ListaMaterialActivity.this, MaisMaterialActivity.class);
                startActivity(intentVaiProFormulario);
            }
        });

        registerForContextMenu(listaMateriais);

    }

    @Override
    protected void onResume() {
        super.onResume();
        carregaLista();
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, final ContextMenu.ContextMenuInfo menuInfo) {
        MenuItem deletar = menu.add("Deletar");
        deletar.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem menuItem) {
                AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) menuInfo;
                Material material = (Material) listaMateriais.getItemAtPosition(info.position);
                Toast.makeText(ListaMaterialActivity.this, "Deletar o material " + material.getNomeMaterial(),
                        Toast.LENGTH_SHORT).show();

                MaterialDAO dao = new MaterialDAO(ListaMaterialActivity.this);
                dao.deleta(material);
                dao.close();

                carregaLista();
                return false;
            }
        });
    }

    private void carregaLista() {
        MaterialDAO dao = new MaterialDAO(this);
        List<Material> materiais = dao.buscaMateriais();
        dao.close();

        ArrayAdapter<Material> adapter = new ArrayAdapter<Material>(this, android.R.layout.simple_list_item_1, materiais);
        listaMateriais.setAdapter(adapter);
    }

}
