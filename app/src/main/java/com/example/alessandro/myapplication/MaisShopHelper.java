package com.example.alessandro.myapplication;

import android.content.Context;
import android.widget.EditText;

import com.example.alessandro.myapplication.dao.ShopDAO;
import com.example.alessandro.myapplication.modelo.Shop;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class MaisShopHelper {

    private final EditText campoNome_produto;
    private final EditText campoValor_compra;
    private final EditText campoValor_venda;
    private final EditText campoQuantidade;
    private final EditText campoLoja;
    private final EditText campoLocal_fabricacao;
    private final EditText campoData_compra;
    private final EditText campoMarca;
    private final EditText campoTipo;
    private final EditText campoCor;
    private final EditText campoData_fabricacao;
    private Shop shop;

    private Context context;

    public MaisShopHelper(MaisShopActivity activity, Context context){
        campoNome_produto = (EditText) activity.findViewById(R.id.shop_nome);
        campoValor_compra = (EditText) activity.findViewById(R.id.shop_valor_compra);
        campoValor_venda = (EditText) activity.findViewById(R.id.shop_valor_venda);
        campoQuantidade = (EditText) activity.findViewById(R.id.shop_quantidade);
        campoLoja = (EditText) activity.findViewById(R.id.shop_loja);
        campoLocal_fabricacao = (EditText) activity.findViewById(R.id.shop_local_fabricacao);
        campoData_compra = (EditText) activity.findViewById(R.id.shop_data_compra);
        campoMarca = (EditText) activity.findViewById(R.id.shop_marca);
        campoTipo = (EditText) activity.findViewById(R.id.shop_tipo);
        campoCor = (EditText) activity.findViewById(R.id.shop_cor);
        campoData_fabricacao = (EditText) activity.findViewById(R.id.shop_data_fabricacao);
        this.context = context;
        shop = new Shop();
    }

    public Shop getShop() {
        shop.setNomeProduto(campoNome_produto.getText().toString());
        shop.setValor_compra(Double.parseDouble(campoValor_compra.getText().toString()));
        shop.setValor_venda(Double.parseDouble(campoValor_venda.getText().toString()));
        shop.setQuantidade(Integer.parseInt(campoQuantidade.getText().toString()));
        shop.setLoja(campoLoja.getText().toString());
        shop.setLocal_fabricacao(campoLocal_fabricacao.getText().toString());

        DateFormat df = new SimpleDateFormat("MM/dd/yyyy");
        Date date = null;
        try {
            date = df.parse(campoData_compra.getText().toString());
        } catch (ParseException e) {
            e.printStackTrace();
        }
        Date d1 = date;
        shop.setData_compra(date);
        shop.setMarca(campoMarca.getText().toString());
        shop.setTipo(campoTipo.getText().toString());
        shop.setCor(campoCor.getText().toString());

        DateFormat dft = new SimpleDateFormat("MM/dd/yyyy");
        Date dat = null;
        try {
            dat = dft.parse(campoData_fabricacao.getText().toString());
        } catch (ParseException e) {
            e.printStackTrace();
        }
        Date d1l = dat;
        shop.setData_fabricacao(dat);

        return shop;
    }

    public void preencheShop(Shop shop) {
        ShopDAO shopDAO = new ShopDAO(context);
        Shop sh1 = null;
        List<Shop> shs = shopDAO.buscaShop();
        for (Shop sh: shs) {
            if(sh.getId() == shop.getId()){
                sh1 = sh;
            }
        }
        campoNome_produto.setText(sh1.getNomeProduto());
        campoValor_compra.setText(sh1.getValor_compra() +"");
        campoValor_venda.setText(sh1.getValor_venda() +"");
        campoQuantidade.setText(sh1.getQuantidade() +"");
        campoLoja.setText(sh1.getLoja());
        campoLocal_fabricacao.setText(sh1.getLocal_fabricacao());

        DateFormat df = new SimpleDateFormat("MM/dd/yyyy");
        Date d = sh1.getData_compra();
        String reportDate = df.format(sh1.getData_compra());
        campoData_compra.setText(reportDate);

        campoMarca.setText(sh1.getMarca());
        campoTipo.setText(shop.getTipo());
        campoCor.setText(shop.getCor());

        DateFormat daf = new SimpleDateFormat("MM/dd/yyyy");
        Date da = shop.getData_compra();
        String reportDat = daf.format(shop.getData_fabricacao());
        campoData_fabricacao.setText(reportDat);

        this.shop = shop;
    }

}
