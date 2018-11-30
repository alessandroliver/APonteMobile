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

import com.example.alessandro.apontemobile.modelo.Cafe;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class MaisCafeActivity extends AppCompatActivity {

    private Button cadastrarCafe;
    private EditText nomeCafe, marcaCafe, idCafe, lojaCafe, valorCompraCafe, valorVendaCafe, localFabricacaoCafe,
            dataCompraCafe, saborCafe, quantidadeCafe, ingredienteCafe, validadeCafe, pesoCafe;
    private TextWatcher dataCompraMark;
    private Spinner temperaturaCafe;
    private String nomeProduto;
    private double valor_compra;
    private double valor_venda;
    private int quantidade;
    private String loja;
    private String local_fabricacao;
    private Date data_compra;
    private String marca;
    private int id;
    private String sabor;
    private String temperatura;
    private String ingrediente;
    private String validade;
    private double peso;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mais_cafe);

        cadastrarCafe = findViewById(R.id.button_cadastro_cafe);

        temperaturaCafe = (Spinner) findViewById(R.id.sp_cafe_temperatura);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.temperatura, android.R.layout.simple_spinner_item);
        temperaturaCafe.setAdapter(adapter);

        nomeCafe = findViewById(R.id.cafe_nome);
        marcaCafe = findViewById(R.id.cafe_marca);
        idCafe = findViewById(R.id.cafe_id);
        lojaCafe = findViewById(R.id.cafe_loja);
        valorCompraCafe = findViewById(R.id.cafe_valor_compra);
        valorVendaCafe = findViewById(R.id.cafe_valor_venda);
        localFabricacaoCafe = findViewById(R.id.cafe_local_fabricacao);
        dataCompraCafe = findViewById(R.id.cafe_data_compra);
        saborCafe = findViewById(R.id.cafe_sabor);
        quantidadeCafe = findViewById(R.id.cafe_quantidade);
        ingredienteCafe = findViewById(R.id.cafe_ingrediente);
        validadeCafe = findViewById(R.id.cafe_validade);
        pesoCafe = findViewById(R.id.cafe_peso);

        dataCompraMark = Mask.insert("##/##/####", dataCompraCafe);
        dataCompraCafe.addTextChangedListener(dataCompraMark);

        cadastrarCafe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                nomeProduto = nomeCafe.getText().toString();
                valor_compra = Double.parseDouble(valorCompraCafe.getText().toString());
                valor_venda = Double.parseDouble(valorVendaCafe.getText().toString());
                quantidade = Integer.parseInt(quantidadeCafe.getText().toString());
                loja = lojaCafe.getText().toString();
                local_fabricacao = localFabricacaoCafe.getText().toString();
                data_compra = null;
                DateFormat formatter = new SimpleDateFormat("MM/dd/yyyy");
                try {
                    data_compra = (Date)formatter.parse(dataCompraCafe.getText().toString());
                } catch (ParseException e) {
                    e.printStackTrace();
                }
                marca = marcaCafe.getText().toString();
                id = Integer.parseInt(idCafe.getText().toString());
                sabor = saborCafe.getText().toString();
                temperatura = temperaturaCafe.getSelectedItem().toString();
                ingrediente = ingredienteCafe.getText().toString();
                validade = validadeCafe.getText().toString();
                peso = Double.parseDouble(pesoCafe.getText().toString());

                Cafe cafe = new Cafe(nomeProduto, valor_compra, valor_venda, quantidade, loja, local_fabricacao,
                        data_compra, marca, id, sabor, temperatura, ingrediente, validade, peso);

                if (cafe.getNomeProduto().equals("") || cafe.getValor_compra() == 0 || cafe.getValor_venda() == 0 ||
                        cafe.getQuantidade() == 0 || cafe.getLoja().equals("") || cafe.getLocal_fabricacao().equals("") ||
                        cafe.getData_compra().equals("") || cafe.getMarca().equals("") || cafe.getId() == 0 ||
                        cafe.getSabor().equals("") || cafe.getTemperatura().equals("") || cafe.getIngrediente().equals("")
                        || cafe.getValidade().equals("") || cafe.getPeso() == 0){
                    alert("Preencha todos os campos.");
                } else {
                    CafeDBController cafeDBController = new CafeDBController(MaisCafeActivity.this);

                    cafeDBController.insert(cafe);

                    alert("Produto da Ponte Café cadastrado com sucesso!");
                    Intent intent = new Intent(MaisCafeActivity.this, InicioActivity.class);
                    startActivity(intent);
                }
            }
        });

    }

    private void alert(String s){
        Toast.makeText(this,s,Toast.LENGTH_LONG).show();
    }

}
