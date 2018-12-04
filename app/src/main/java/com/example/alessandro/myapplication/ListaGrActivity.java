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

import com.example.alessandro.myapplication.dao.GrDAO;
import com.example.alessandro.myapplication.modelo.Gr;

public class ListaGrActivity extends AppCompatActivity {

    private ListView listaGrs;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_gr);

        listaGrs = (ListView) findViewById(R.id.lista_grs);

        listaGrs.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
                Gr gr = (Gr) listaGrs.getItemAtPosition(position);
                Intent intentVaiProFormulario = new Intent(ListaGrActivity.this, MaisGrActivity.class);
                intentVaiProFormulario.putExtra("gr", gr);
                startActivity(intentVaiProFormulario);
            }
        });

        Button novoGr = (Button) findViewById(R.id.add_gr);
        novoGr.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intentVaiProFormulario = new Intent(ListaGrActivity.this, MaisGrActivity.class);
                startActivity(intentVaiProFormulario);
            }
        });

        registerForContextMenu(listaGrs);

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
                Gr gr = (Gr) listaGrs.getItemAtPosition(info.position);
                Toast.makeText(ListaGrActivity.this, "Deletar o gr " + gr.getNomeGr(), Toast.LENGTH_SHORT).show();

                GrDAO dao = new GrDAO(ListaGrActivity.this);
                dao.deleta(gr);
                dao.close();

                carregaLista();
                return false;
            }
        });
    }

    private void carregaLista() {
        GrDAO dao = new GrDAO(this);
        List<Gr> grs = dao.buscaGrs();
        dao.close();

        ArrayAdapter<Gr> adapter = new ArrayAdapter<Gr>(this, android.R.layout.simple_list_item_1, grs);
        listaGrs.setAdapter(adapter);
    }

}
