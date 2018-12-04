package com.example.alessandro.myapplication;
import android.content.Context;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;

import com.example.alessandro.myapplication.dao.CafeDAO;
import com.example.alessandro.myapplication.modelo.Cafe;

import java.text.DateFormat;
import java.text.Format;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class MaisCafeHelper {

    private final EditText campoNome_produto;
    private final EditText campoValor_compra;
    private final EditText campoValor_venda;
    private final EditText campoQuantidade;
    private final EditText campoLoja;
    private final EditText campoLocal_fabricacao;
    private final EditText campoData_compra;
    private final EditText campoMarca;
    private final EditText campoSabor;
    private final Spinner campoTemperatura;
    private final EditText campoIngrediente;
    private final EditText campoValidade;
    private final EditText campoPeso;
    private Cafe cafe;

    private Context context;

    public MaisCafeHelper(MaisCafeActivity activity, Context context){
        campoNome_produto = (EditText) activity.findViewById(R.id.cafe_nome);
        campoValor_compra = (EditText) activity.findViewById(R.id.cafe_valor_compra);
        campoValor_venda = (EditText) activity.findViewById(R.id.cafe_valor_venda);
        campoQuantidade = (EditText) activity.findViewById(R.id.cafe_quantidade);
        campoLoja = (EditText) activity.findViewById(R.id.cafe_loja);
        campoLocal_fabricacao = (EditText) activity.findViewById(R.id.cafe_local_fabricacao);
        campoData_compra = (EditText) activity.findViewById(R.id.cafe_data_compra);
        campoMarca = (EditText) activity.findViewById(R.id.cafe_marca);
        campoSabor = (EditText) activity.findViewById(R.id.cafe_sabor);
        campoTemperatura = (Spinner) activity.findViewById(R.id.sp_cafe_temperatura);
        campoIngrediente = (EditText) activity.findViewById(R.id.cafe_ingrediente);
        campoValidade = (EditText) activity.findViewById(R.id.cafe_validade);
        campoPeso = (EditText) activity.findViewById(R.id.cafe_peso);
        this.context = context;
        cafe = new Cafe();
    }

    public Cafe getCafe() {
        cafe.setNomeProduto(campoNome_produto.getText().toString());
        cafe.setValor_compra(Double.parseDouble(campoValor_compra.getText().toString()));
        cafe.setValor_venda(Double.parseDouble(campoValor_venda.getText().toString()));
        cafe.setQuantidade(Integer.parseInt(campoQuantidade.getText().toString()));
        cafe.setLoja(campoLoja.getText().toString());
        cafe.setLocal_fabricacao(campoLocal_fabricacao.getText().toString());

        DateFormat df = new SimpleDateFormat("MM/dd/yyyy");
        Date date = null;
        try {
            date = df.parse(campoData_compra.getText().toString());
        } catch (ParseException e) {
            e.printStackTrace();
        }
        Date d1 = date;
        cafe.setData_compra(date);
        cafe.setMarca(campoMarca.getText().toString());
        cafe.setSabor(campoSabor.getText().toString());
        cafe.setTemperatura(campoTemperatura.getSelectedItem().toString());
        cafe.setIngrediente(campoIngrediente.getText().toString());
        cafe.setValidade(campoValidade.getText().toString());
        cafe.setPeso(Double.parseDouble(campoPeso.getText().toString()));

        return cafe;
    }

    public void preencheCafe(Cafe cafe) {
        CafeDAO cafeDAO = new CafeDAO(context);
        Cafe cf1 = null;
        List<Cafe> cfs = cafeDAO.buscaCafe();
        for (Cafe cf: cfs) {
            if(cf.getId() == cafe.getId()){
                cf1 = cf;
            }
        }
        campoNome_produto.setText(cf1.getNomeProduto());
        campoValor_compra.setText(cf1.getValor_compra() +"");
        campoValor_venda.setText(cf1.getValor_venda() +"");
        campoQuantidade.setText(cf1.getQuantidade() +"");
        campoLoja.setText(cf1.getLoja());
        campoLocal_fabricacao.setText(cf1.getLocal_fabricacao());

        DateFormat df = new SimpleDateFormat("MM/dd/yyyy");
        String reportDate = df.format(cf1.getData_compra());
        campoData_compra.setText(reportDate);

        campoMarca.setText(cf1.getMarca());
        campoSabor.setText(cafe.getSabor());

        String compareValue = cafe.getTemperatura();
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(context, R.array.temperatura, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            if (compareValue != null){
                int spinnerPosition = adapter.getPosition(compareValue);
                campoTemperatura.setSelection(spinnerPosition);
            }
            campoIngrediente.setText(cafe.getIngrediente());
        campoValidade.setText(cafe.getValidade());
        campoPeso.setText(cafe.getPeso() +"");
        this.cafe = cafe;
    }

}
