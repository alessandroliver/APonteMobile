package com.example.alessandro.apontemobile;

import android.widget.EditText;
import android.widget.Spinner;

import com.example.alessandro.apontemobile.modelo.Material;

import java.sql.Date;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;

public class MaterialHelper {

    /*private final EditText campoNomeMaterial;
    private final EditText campoValorMaterial;
    private final EditText campoLojaMaterial;
    private final EditText campoDataCompraMaterial;
    private final EditText campoValidadeMaterial;
    private final EditText campoGarantiaMaterial;
    private final EditText campoTipoMaterial;
    private final EditText campoFinalidadeMaterial;
    private final EditText campoPesoMaterial;
    private final EditText campoTamanhoMaterial;
    private final EditText campoQuantidadeMaterial;

    private Material material;

    public MaterialHelper(MaisGrActivity activity) {
        campoNomeMaterial = (EditText) activity.findViewById(R.id.material_nome);
        campoValorMaterial = (EditText) activity.findViewById(R.id.material_valor);
        campoLojaMaterial = (EditText) activity.findViewById(R.id.material_loja);
        campoDataCompraMaterial = (EditText) activity.findViewById(R.id.material_data_compra);
        campoValidadeMaterial = (EditText) activity.findViewById(R.id.material_validade);
        campoGarantiaMaterial = (EditText) activity.findViewById(R.id.material_garantia);
        campoTipoMaterial = (EditText) activity.findViewById(R.id.material_tipo);
        campoFinalidadeMaterial = (EditText) activity.findViewById(R.id.material_finalidade);
        campoPesoMaterial = (EditText) activity.findViewById(R.id.material_peso);
        campoTamanhoMaterial = (EditText) activity.findViewById(R.id.material_tamanho);
        campoQuantidadeMaterial = (EditText) activity.findViewById(R.id.material_quantidade_material);

    }

    public Material getMaterial() throws ParseException {
        material.setNomeMaterial(campoNomeMaterial.getText().toString());
        material.setValor(Double.parseDouble(campoValorMaterial.getText().toString()));
        material.setLoja(campoLojaMaterial.getText().toString());

        DateFormat formatter = new SimpleDateFormat("MM/dd/yyyy");
        Date date = (Date)formatter.parse(campoDataCompraMaterial.getText().toString());

        material.setData_compra(date);

        material.setValidade(campoValidadeMaterial.getText().toString());
        material.setGarantia(campoGarantiaMaterial.getText().toString());
        material.setTipo(campoTipoMaterial.getText().toString());
        material.setFinalidade(campoFinalidadeMaterial.getText().toString());
        material.setPeso(Double.parseDouble(campoPesoMaterial.getText().toString()));
        material.setTamanho(Double.parseDouble(campoTamanhoMaterial.getText().toString()));
        material.setQuantidadeMaterial(Integer.parseInt(campoQuantidadeMaterial.getText().toString()));

        //material = new Material(campoNomeMaterial);

        return material;
    }

    public void preencheFormularioMaterial(Material material) {
        campoNomeMaterial.setText(material.getNomeMaterial());
        campoValorMaterial.setText((int) material.getValor());
        campoLojaMaterial.setText(material.getLoja());
        campoDataCompraMaterial.setText((CharSequence) material.getData_compra());
        campoValidadeMaterial.setText(material.getValidade());
        campoGarantiaMaterial.setText(material.getGarantia());
        campoTipoMaterial.setText(material.getTipo());
        campoFinalidadeMaterial.setText(material.getFinalidade());
        campoPesoMaterial.setText((int) material.getPeso());
        campoTamanhoMaterial.setText((int) material.getTamanho());
        campoQuantidadeMaterial.setText((int) material.getQuantidadeMaterial());
        this.material = material;
    }*/

}
