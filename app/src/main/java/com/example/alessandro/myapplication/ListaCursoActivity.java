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

import com.example.alessandro.myapplication.dao.CursoDAO;
import com.example.alessandro.myapplication.modelo.Curso;

public class ListaCursoActivity extends AppCompatActivity {

    private ListView listaCursos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_curso);

        listaCursos = (ListView) findViewById(R.id.lista_cursos);

        listaCursos.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
                Curso curso = (Curso) listaCursos.getItemAtPosition(position);
                Intent intentVaiProFormulario = new Intent(ListaCursoActivity.this, MaisCursoActivity.class);
                intentVaiProFormulario.putExtra("curso", curso);
                startActivity(intentVaiProFormulario);
            }
        });

        Button novoMaterial = (Button) findViewById(R.id.add_curso);
        novoMaterial.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intentVaiProFormulario = new Intent(ListaCursoActivity.this, MaisCursoActivity.class);
                startActivity(intentVaiProFormulario);
            }
        });

        registerForContextMenu(listaCursos);

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
                Curso curso = (Curso) listaCursos.getItemAtPosition(info.position);
                Toast.makeText(ListaCursoActivity.this, "Deletar o curso " + curso.getNomeCurso(),
                        Toast.LENGTH_SHORT).show();

                CursoDAO dao = new CursoDAO(ListaCursoActivity.this);
                dao.deleta(curso);
                dao.close();

                carregaLista();
                return false;
            }
        });
    }

    private void carregaLista() {
        CursoDAO dao = new CursoDAO(this);
        List<Curso> cursos = dao.buscaCursos();
        dao.close();

        ArrayAdapter<Curso> adapter = new ArrayAdapter<Curso>(this, android.R.layout.simple_list_item_1, cursos);
        listaCursos.setAdapter(adapter);
    }

}
