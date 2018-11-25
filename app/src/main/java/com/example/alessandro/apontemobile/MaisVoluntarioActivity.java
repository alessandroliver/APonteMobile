package com.example.alessandro.apontemobile;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextWatcher;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

public class MaisVoluntarioActivity extends AppCompatActivity {

    private Button cadastrarVoluntario;
    private EditText celularVoluntario, nascimentoVoluntario, alturaVoluntario, cepVoluntario, rgVoluntario,
            cpfVoluntario;
    private TextWatcher celularMark, nascimentoMask, alturaMask, cepMask, rgMask, cpfMask;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mais_voluntario);

        cadastrarVoluntario = findViewById(R.id.button_cadastro_voluntario);

        Spinner spStatesVoluntario = (Spinner) findViewById(R.id.voluntario_sp_state);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.states, android.R.layout.simple_spinner_item);
        spStatesVoluntario.setAdapter(adapter);

        Spinner spStatesVoluntario1 = (Spinner) findViewById(R.id.voluntario_sp_camisa);
        ArrayAdapter<CharSequence> adapter1 = ArrayAdapter.createFromResource(this,
                R.array.camisas, android.R.layout.simple_spinner_item);
        spStatesVoluntario1.setAdapter(adapter1);

        celularVoluntario = findViewById(R.id.voluntario_celular);
        nascimentoVoluntario = findViewById(R.id.voluntario_nascimento);
        alturaVoluntario = findViewById(R.id.voluntario_altura);
        cepVoluntario = findViewById(R.id.voluntario_cep);
        rgVoluntario = findViewById(R.id.voluntario_rg);
        cpfVoluntario = findViewById(R.id.voluntario_cpf);

        celularMark = Mask.insert("(##) #####-####", celularVoluntario);
        celularVoluntario.addTextChangedListener(celularMark);

        nascimentoMask = Mask.insert("##/##/####", nascimentoVoluntario);
        nascimentoVoluntario.addTextChangedListener(nascimentoMask);

        alturaMask = Mask.insert("#.##", alturaVoluntario);
        alturaVoluntario.addTextChangedListener(alturaMask);

        cepMask = Mask.insert("##.###-###", cepVoluntario);
        cepVoluntario.addTextChangedListener(cepMask);

        rgMask = Mask.insert("#.###.###", rgVoluntario);
        rgVoluntario.addTextChangedListener(rgMask);

        cpfMask = Mask.insert("###.###.###-##", cpfVoluntario);
        cpfVoluntario.addTextChangedListener(cpfMask);

        cadastrarVoluntario.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                alert("Voluntário cadastrado com sucesso!");
                Intent intent = new Intent(MaisVoluntarioActivity.this, InicioActivity.class);
                startActivity(intent);
            }
        });

    }

    private void alert(String s){
        Toast.makeText(this,s,Toast.LENGTH_LONG).show();
    }

}
