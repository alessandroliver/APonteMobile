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

import com.example.alessandro.myapplication.dao.FuncionarioDAO;
import com.example.alessandro.myapplication.modelo.Funcionario;

public class ListaFuncionarioActivity extends AppCompatActivity {

    private ListView listaFuncionarios;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_funcionario);

        listaFuncionarios = (ListView) findViewById(R.id.lista_funcionarios);

        listaFuncionarios.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
                Funcionario funcionario = (Funcionario) listaFuncionarios.getItemAtPosition(position);
                Intent intentVaiProFormulario = new Intent(ListaFuncionarioActivity.this,
                        MaisFuncionarioActivity.class);
                intentVaiProFormulario.putExtra("funcionario", funcionario);
                startActivity(intentVaiProFormulario);
            }
        });

        Button novoGr = (Button) findViewById(R.id.add_funcionario);
        novoGr.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intentVaiProFormulario = new Intent(ListaFuncionarioActivity.this,
                        MaisFuncionarioActivity.class);
                startActivity(intentVaiProFormulario);
            }
        });

        registerForContextMenu(listaFuncionarios);

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
                Funcionario funcionario = (Funcionario) listaFuncionarios.getItemAtPosition(info.position);
                Toast.makeText(ListaFuncionarioActivity.this, "Deletar o funcionario " + funcionario.getNome(),
                        Toast.LENGTH_SHORT).show();

                FuncionarioDAO dao = new FuncionarioDAO(ListaFuncionarioActivity.this);
                dao.deleta(funcionario);
                dao.close();

                carregaLista();
                return false;
            }
        });
    }

    private void carregaLista() {
        FuncionarioDAO dao = new FuncionarioDAO(this);
        List<Funcionario> funcionarios = dao.buscaFuncionario();
        dao.close();

        ArrayAdapter<Funcionario> adapter = new ArrayAdapter<Funcionario>(this, android.R.layout.simple_list_item_1,
                funcionarios);
        listaFuncionarios.setAdapter(adapter);
    }

}