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

import com.example.alessandro.myapplication.dao.MembroDAO;
import com.example.alessandro.myapplication.modelo.Membro;

import java.text.ParseException;
import java.util.List;

public class ListaMembroActivity extends AppCompatActivity {

    private ListView listaMembro;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_membro);

        listaMembro = (ListView) findViewById(R.id.lista_membros);

        listaMembro.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
                Membro membro = (Membro) listaMembro.getItemAtPosition(position);
                Intent intentVaiProFormulario = new Intent(ListaMembroActivity.this, MaisMembroActivity.class);
                intentVaiProFormulario.putExtra("membro", membro);
                startActivity(intentVaiProFormulario);
            }
        });

        Button novoCafe = (Button) findViewById(R.id.add_membro);
        novoCafe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intentVaiProFormulario = new Intent(ListaMembroActivity.this, MaisMembroActivity.class);
                startActivity(intentVaiProFormulario);
            }
        });

        registerForContextMenu(listaMembro);

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
                Membro membro = (Membro) listaMembro.getItemAtPosition(info.position);
                Toast.makeText(ListaMembroActivity.this, "Deletar o membro " + membro.getNome(),
                        Toast.LENGTH_SHORT).show();

                MembroDAO dao = new MembroDAO(ListaMembroActivity.this);
                dao.deleta(membro);
                dao.close();

                carregaLista();
                return false;
            }
        });
    }

    private void carregaLista() {
        MembroDAO dao = new MembroDAO(this);
        List<Membro> membros = dao.buscaMembro();
        dao.close();

        ArrayAdapter<Membro> adapter = new ArrayAdapter<Membro>(this, android.R.layout.simple_list_item_1, membros);
        listaMembro.setAdapter(adapter);
    }

}
