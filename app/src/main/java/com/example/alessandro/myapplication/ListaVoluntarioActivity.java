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

import com.example.alessandro.myapplication.dao.VoluntarioDAO;
import com.example.alessandro.myapplication.modelo.Voluntario;

public class ListaVoluntarioActivity extends AppCompatActivity {

    private ListView listaVoluntarios;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_voluntario);

        listaVoluntarios = (ListView) findViewById(R.id.lista_voluntarios);

        listaVoluntarios.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
                Voluntario voluntario = (Voluntario) listaVoluntarios.getItemAtPosition(position);
                Intent intentVaiProFormulario = new Intent(ListaVoluntarioActivity.this, MaisVoluntarioActivity.class);
                intentVaiProFormulario.putExtra("voluntario", voluntario);
                startActivity(intentVaiProFormulario);
            }
        });

        Button novoGr = (Button) findViewById(R.id.add_voluntario);
        novoGr.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intentVaiProFormulario = new Intent(ListaVoluntarioActivity.this, MaisVoluntarioActivity.class);
                startActivity(intentVaiProFormulario);
            }
        });

        registerForContextMenu(listaVoluntarios);

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
                Voluntario voluntario = (Voluntario) listaVoluntarios.getItemAtPosition(info.position);
                Toast.makeText(ListaVoluntarioActivity.this, "Deletar o voluntario " + voluntario.getNome(),
                        Toast.LENGTH_SHORT).show();

                VoluntarioDAO dao = new VoluntarioDAO(ListaVoluntarioActivity.this);
                dao.deleta(voluntario);
                dao.close();

                carregaLista();
                return false;
            }
        });
    }

    private void carregaLista() {
        VoluntarioDAO dao = new VoluntarioDAO(this);
        List<Voluntario> voluntarios = dao.buscaVoluntario();
        dao.close();

        ArrayAdapter<Voluntario> adapter = new ArrayAdapter<Voluntario>(this, android.R.layout.simple_list_item_1,
                voluntarios);
        listaVoluntarios.setAdapter(adapter);
    }


}
