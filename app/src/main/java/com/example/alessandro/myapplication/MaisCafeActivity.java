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

import com.example.alessandro.myapplication.modelo.Cafe;

import com.example.alessandro.myapplication.dao.CafeDAO;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class MaisCafeActivity extends AppCompatActivity {

    private MaisCafeHelper helper;
    private Spinner temperaturaCafe;
    private TextWatcher dataCompraMask;
    private EditText dataCompraCafe;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mais_cafe);

        dataCompraCafe = findViewById(R.id.cafe_data_compra);

        dataCompraMask = Mask.insert("##/##/####", dataCompraCafe);
        dataCompraCafe.addTextChangedListener(dataCompraMask);

        temperaturaCafe = (Spinner) findViewById(R.id.sp_cafe_temperatura);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.temperatura, android.R.layout.simple_spinner_item);
        temperaturaCafe.setAdapter(adapter);

        helper = new MaisCafeHelper(this, this);

        Intent intent = getIntent();
        Cafe cafe = (Cafe) intent.getSerializableExtra("cafe");

        if(cafe != null){
            helper.preencheCafe(cafe);
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
                Cafe cafe = helper.getCafe();
                CafeDAO dao = new CafeDAO(this);

                if(cafe.getId() != 0){
                    dao.altera(cafe);
                }else{
                    dao.insere(cafe);
                }


                dao.close();
                Toast.makeText(MaisCafeActivity.this, "Cafe " + cafe.getNomeProduto() + " salvo!",
                        Toast.LENGTH_SHORT).show();
                finish();
                break;
        }
        return super.onOptionsItemSelected(item);
    }

}
