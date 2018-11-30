package com.example.alessandro.apontemobile;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.view.ContextMenu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.Toast;

import com.example.alessandro.apontemobile.modelo.Gr;

import java.text.ParseException;
import java.util.List;

public class GrActivity extends AppCompatActivity {

    private Button maisGr;
    private List<Gr> listaGr = null;
    public static String acao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gr);

        try {
            listaGr = new GrDBController(this).getAllGr();
        } catch (ParseException e) {
            e.printStackTrace();
        }

        Bundle extras = getIntent().getExtras();

        if (extras != null) {
            acao = extras.getString("acaoExtra");
        }

        ListView listagr = findViewById(R.id.lista_grs);
        ArrayAdapter<Gr> adapter = new ArrayAdapter<Gr>(this, android.R.layout.simple_list_item_1, listaGr);
        listagr.setAdapter(adapter);

        maisGr = findViewById(R.id.add_gr);

        maisGr.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(GrActivity.this, MaisGrActivity.class);
                startActivity(intent);
            }
        });

    }

    /*@Override
    public void onCreateContextMenu(ContextMenu menu, View v, final ContextMenu.ContextMenuInfo menuInfo) {
        MenuItem deletar = menu.add("Deletar");
        deletar.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem menuItem) {
                AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) menuInfo;
                Gr gr = (Gr) listaGr.getItemAtPosition(info.position);
                Toast.makeText(GrActivity.this, "Deletar o gr " + aluno.getNome(), Toast.LENGTH_SHORT).show();

                AlunoDAO dao = new AlunoDAO(ListaAlunosActivity.this);
                dao.deleta(aluno);
                dao.close();

                carregaLista();
                return false;
            }
        });
    }*/

}
