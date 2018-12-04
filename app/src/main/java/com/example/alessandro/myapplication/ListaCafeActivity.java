package com.example.alessandro.myapplication;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.example.alessandro.myapplication.dao.CafeDAO;
import com.example.alessandro.myapplication.modelo.Cafe;

import java.text.ParseException;
import java.util.List;

public class ListaCafeActivity extends AppCompatActivity {

    private ListView listaCafe;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_cafe);

        listaCafe = (ListView) findViewById(R.id.lista_cafes);

        listaCafe.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
                Cafe cafe = (Cafe) listaCafe.getItemAtPosition(position);
                Intent intentVaiProFormulario = new Intent(ListaCafeActivity.this, MaisCafeActivity.class);
                intentVaiProFormulario.putExtra("cafe", cafe);
                startActivity(intentVaiProFormulario);
            }
        });

        Button novoCafe = (Button) findViewById(R.id.add_cafe);
        novoCafe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intentVaiProFormulario = new Intent(ListaCafeActivity.this, MaisCafeActivity.class);
                startActivity(intentVaiProFormulario);
            }
        });

        registerForContextMenu(listaCafe);

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
                Cafe cafe = (Cafe) listaCafe.getItemAtPosition(info.position);
                Toast.makeText(ListaCafeActivity.this, "Deletar o cafe " + cafe.getNomeProduto(),
                        Toast.LENGTH_SHORT).show();

                CafeDAO dao = new CafeDAO(ListaCafeActivity.this);
                dao.deleta(cafe);
                dao.close();

                carregaLista();
                return false;
            }
        });
    }

    private void carregaLista() {
        CafeDAO dao = new CafeDAO(this);
        List<Cafe> cafes = dao.buscaCafe();
        dao.close();

        ArrayAdapter<Cafe> adapter = new ArrayAdapter<Cafe>(this, android.R.layout.simple_list_item_1, cafes);
        listaCafe.setAdapter(adapter);
    }

}
