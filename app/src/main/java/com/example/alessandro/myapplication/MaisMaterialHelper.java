package com.example.alessandro.myapplication;

import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;

import com.example.alessandro.myapplication.modelo.Material;

import java.text.DateFormat;
import java.text.Format;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class MaisMaterialHelper {

    private final EditText campoNomeMaterial;
    private final EditText campoValor;
    private final EditText campoFinalidade;
    private final EditText campoGarantia;
    private final EditText campoLoja;
    private final EditText campoDataCompra;
    private final EditText campoValidade;
    private final EditText campoTipo;
    private final EditText campoPeso;
    private final EditText campoTamanho;
    private final EditText campoQuantidadeMaterial;

    private Material material;

    public MaisMaterialHelper(MaisMaterialActivity activity){

        campoNomeMaterial = (EditText) activity.findViewById(R.id.material_nome);
        campoValor = (EditText) activity.findViewById(R.id.material_valor);
        campoFinalidade = (EditText) activity.findViewById(R.id.material_finalidade);
        campoGarantia = (EditText) activity.findViewById(R.id.material_garantia);
        campoLoja = (EditText) activity.findViewById(R.id.material_loja);
        campoDataCompra = (EditText) activity.findViewById(R.id.material_data_compra);
        campoValidade = (EditText) activity.findViewById(R.id.material_validade);
        campoTipo = (EditText) activity.findViewById(R.id.material_tipo);
        campoPeso = (EditText) activity.findViewById(R.id.material_peso);
        campoTamanho = (EditText) activity.findViewById(R.id.material_tamanho);
        campoQuantidadeMaterial = (EditText) activity.findViewById(R.id.material_quantidade_material);
        material = new Material();
    }

    public Material getMaterial(){
        material.setNomeMaterial(campoNomeMaterial.getText().toString());
        material.setValor(Double.valueOf(campoValor.getText().toString()));
        material.setFinalidade(campoFinalidade.getText().toString());
        material.setGarantia(campoGarantia.getText().toString());
        material.setLoja(campoLoja.getText().toString());

        DateFormat df = new SimpleDateFormat("MM/dd/yyyy");
        Date date = null;
        try {
            date = df.parse(campoDataCompra.getText().toString());
        } catch (ParseException e) {
            e.printStackTrace();
        }
        Date d1 = date;
        material.setData_compra(date);
        material.setValidade(campoValidade.getText().toString());
        material.setTipo(campoTipo.getText().toString());
        material.setPeso(Double.valueOf(campoPeso.getText().toString()));
        material.setTamanho(Double.valueOf(campoTamanho.getText().toString()));
        material.setQuantidadeMaterial(Integer.valueOf(campoQuantidadeMaterial.getText().toString()));

        return material;
    }

    public void preencheMaterial(Material material) {

        campoNomeMaterial.setText(material.getNomeMaterial());
        campoValor.setText(material.getValor() +"");
        campoFinalidade.setText(material.getFinalidade());
        campoGarantia.setText(material.getGarantia());
        campoLoja.setText(material.getLoja());

        DateFormat df = new SimpleDateFormat("MM/dd/yyyy");
        String reportDate = df.format(material.getData_compra());
        campoDataCompra.setText(reportDate);

        campoValidade.setText(material.getValidade());
        campoTipo.setText(material.getTipo());
        campoPeso.setText(material.getPeso() +"");
        campoTamanho.setText(material.getTamanho() +"");
        campoQuantidadeMaterial.setText(material.getQuantidadeMaterial() +"");
        this.material = material;
    }


}
