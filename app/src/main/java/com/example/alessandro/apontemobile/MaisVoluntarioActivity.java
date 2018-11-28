package com.example.alessandro.apontemobile;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextWatcher;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.alessandro.apontemobile.modelo.Voluntario;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class MaisVoluntarioActivity extends AppCompatActivity {

    private Button cadastrarVoluntario;
    private EditText nomeVoluntario, celularVoluntario, nascimentoVoluntario, alturaVoluntario, naturalidadeVoluntario,
            logradouroVoluntario, numeroVoluntario, bairroVoluntario, cepVoluntario, cidadeVoluntario, rgVoluntario,
            cpfVoluntario, areaVoluntario, funcaoVoluntario, horaSemanalVoluntario, pegarVoluntario, largarVoluntario;
    private TextWatcher celularMark, nascimentoMask, alturaMask, cepMask, rgMask, cpfMask;
    private RadioGroup radioSexo;
    private Spinner ufVoluntario, camisaVoluntario;
    String nome;
    String naturalidade;
    int celular;
    Date nascimento;
    String sexo;
    double altura;
    String logradouro;
    int numero;
    String bairro;
    String cep;
    String cidade;
    String uf;
    String funcao;
    double horario_pegar;
    double horario_largar;
    double hora_semanal;
    String tamanho_camisa;
    int cpf;
    int rg;
    String area;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mais_voluntario);

        cadastrarVoluntario = findViewById(R.id.button_cadastro_voluntario);

        radioSexo = findViewById(R.id.voluntario_sexo);

        ufVoluntario = (Spinner) findViewById(R.id.voluntario_sp_state);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.states, android.R.layout.simple_spinner_item);
        ufVoluntario.setAdapter(adapter);

        camisaVoluntario = (Spinner) findViewById(R.id.voluntario_sp_camisa);
        ArrayAdapter<CharSequence> adapter1 = ArrayAdapter.createFromResource(this,
                R.array.camisas, android.R.layout.simple_spinner_item);
        camisaVoluntario.setAdapter(adapter1);

        nomeVoluntario = findViewById(R.id.voluntario_nome);
        celularVoluntario = findViewById(R.id.voluntario_celular);
        nascimentoVoluntario = findViewById(R.id.voluntario_nascimento);
        alturaVoluntario = findViewById(R.id.voluntario_altura);
        naturalidadeVoluntario = findViewById(R.id.voluntario_naturalidade);
        logradouroVoluntario = findViewById(R.id.voluntario_logradouro);
        numeroVoluntario = findViewById(R.id.voluntario_numero);
        bairroVoluntario = findViewById(R.id.voluntario_bairro);
        cepVoluntario = findViewById(R.id.voluntario_cep);
        cidadeVoluntario = findViewById(R.id.voluntario_cidade);
        rgVoluntario = findViewById(R.id.voluntario_rg);
        cpfVoluntario = findViewById(R.id.voluntario_cpf);

        areaVoluntario = findViewById(R.id.voluntario_area);
        funcaoVoluntario = findViewById(R.id.voluntario_funcao);
        horaSemanalVoluntario = findViewById(R.id.voluntario_hora_semanal);
        pegarVoluntario = findViewById(R.id.voluntario_horario_pegar);
        largarVoluntario = findViewById(R.id.voluntario_horario_largar);

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
                nome = nomeVoluntario.getText().toString();
                naturalidade = naturalidadeVoluntario.getText().toString();
                celular = Integer.parseInt(celularVoluntario.getText().toString());
                nascimento = null;
                DateFormat formatter = new SimpleDateFormat("MM/dd/yyyy");
                try {
                    nascimento = (Date)formatter.parse(nascimentoVoluntario.getText().toString());
                } catch (ParseException e) {
                    e.printStackTrace();
                }
                switch (radioSexo.getCheckedRadioButtonId()) {
                    case R.id.voluntarioRadioM:
                        sexo = "masculino";
                        break;
                    case R.id.voluntarioRadioF:
                        sexo = "feminino";
                        break;
                }
                altura = Double.parseDouble(alturaVoluntario.getText().toString());
                logradouro = logradouroVoluntario.getText().toString();
                numero = Integer.parseInt(numeroVoluntario.getText().toString());
                bairro = bairroVoluntario.getText().toString();
                cep = cepVoluntario.getText().toString();
                cidade = cidadeVoluntario.getText().toString();
                uf = ufVoluntario.getSelectedItem().toString();
                funcao = funcaoVoluntario.getText().toString();
                horario_pegar = Double.parseDouble(pegarVoluntario.getText().toString());
                horario_largar = Double.parseDouble(largarVoluntario.getText().toString());
                hora_semanal = Double.parseDouble(horaSemanalVoluntario.getText().toString());
                tamanho_camisa = camisaVoluntario.getSelectedItem().toString();
                cpf = Integer.parseInt(cpfVoluntario.getText().toString());
                rg = Integer.parseInt(rgVoluntario.getText().toString());
                area = areaVoluntario.getText().toString();

                Voluntario voluntario = new Voluntario(nome,naturalidade,celular,nascimento,sexo,altura,logradouro,
                        numero,bairro,cep,cidade,uf,funcao,horario_pegar,horario_largar,hora_semanal,tamanho_camisa,
                        cpf,rg,area);

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
