package com.example.alessandro.myapplication;

import android.content.Context;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Spinner;

import com.example.alessandro.myapplication.dao.GrDAO;
import com.example.alessandro.myapplication.dao.MembroDAO;
import com.example.alessandro.myapplication.modelo.Gr;
import com.example.alessandro.myapplication.modelo.Membro;

import java.text.DateFormat;
import java.text.Format;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class MaisMembroHelper {

    private final EditText campoNome;
    private final EditText campoNaturalidade;
    private final EditText campoCelular;
    private final EditText campoNascimento;
    private final RadioGroup campoSexo;
    private final EditText campoAltura;
    private final EditText campoLogradouro;
    private final EditText campoNumero;
    private final EditText campoBairro;
    private final EditText campoCep;
    private final EditText campoCidade;
    private final Spinner campoUf;
    private final EditText campoDataConversao;
    private final EditText campoEquipe;
    private final EditText campoTempoPonte;
    private final EditText campoCargo;
    private final Spinner campoGr;
    private final Spinner campoColete;
    private Context context;

    private Membro membro;

    public MaisMembroHelper(MaisMembroActivity activity, Context context){
        campoNome = (EditText) activity.findViewById(R.id.membro_nome);
        campoNaturalidade = (EditText) activity.findViewById(R.id.membro_naturalidade);
        campoCelular = (EditText) activity.findViewById(R.id.membro_celular);
        campoNascimento = (EditText) activity.findViewById(R.id.membro_nascimento);
        campoSexo = (RadioGroup) activity.findViewById(R.id.membro_sexo);
        campoAltura = (EditText) activity.findViewById(R.id.membro_altura);
        campoLogradouro = (EditText) activity.findViewById(R.id.membro_logradouro);
        campoNumero = (EditText) activity.findViewById(R.id.membro_numero);
        campoBairro = (EditText) activity.findViewById(R.id.membro_bairro);
        campoCep = (EditText) activity.findViewById(R.id.membro_cep);
        campoCidade = (EditText) activity.findViewById(R.id.membro_cidade);
        campoUf = (Spinner) activity.findViewById(R.id.membro_sp_state);
        campoDataConversao = (EditText) activity.findViewById(R.id.membro_data_conversao);
        campoEquipe = (EditText) activity.findViewById(R.id.membro_equipe);
        campoTempoPonte = (EditText) activity.findViewById(R.id.membro_tempo_ponte);
        campoCargo = (EditText) activity.findViewById(R.id.membro_cargo);
        campoGr = (Spinner) activity.findViewById(R.id.sp_membro_gr);
        campoColete = (Spinner) activity.findViewById(R.id.membro_sp_colete);
        this.context = context;
        membro = new Membro();
    }

    public Membro getMembro() {
        membro.setNome(campoNome.getText().toString());
        membro.setNaturalidade(campoNaturalidade.getText().toString());
        membro.setCelular(Integer.valueOf(campoCelular.getText().toString()));

        DateFormat df = new SimpleDateFormat("MM/dd/yyyy");
        Date date = null;
        try {
            date = df.parse(campoNascimento.getText().toString());
        } catch (ParseException e) {
            e.printStackTrace();
        }
        Date d1 = date;
        membro.setNascimento(date);
        String sx = "";
        switch (campoSexo.getCheckedRadioButtonId()) {
            case R.id.membroRadioM:
                sx = "masculino";
                break;
            case R.id.membroRadioF:
                sx = "feminino";
                break;
        }
        membro.setSexo(sx);
        membro.setAltura(Double.valueOf(campoAltura.getText().toString()));
        membro.setLogradouro(campoLogradouro.getText().toString());
        membro.setNumero(Integer.valueOf(campoNumero.getText().toString()));
        membro.setBairro(campoBairro.getText().toString());
        membro.setCep(campoCep.getText().toString());
        membro.setCidade(campoCidade.getText().toString());
        membro.setUf(campoUf.getSelectedItem().toString());

        DateFormat dft = new SimpleDateFormat("MM/dd/yyyy");
        Date dat = null;
        try {
            dat = dft.parse(campoNascimento.getText().toString());
        } catch (ParseException e) {
            e.printStackTrace();
        }
        Date d2 = dat;
        membro.setData_conversao(dat);
        membro.setEquipe(campoEquipe.getText().toString());
        membro.setTempo_ponte(campoTempoPonte.getText().toString());
        membro.setCargo(campoCargo.getText().toString());
        GrDAO grDAO = new GrDAO(context);
        List<Gr> grs = grDAO.buscaGrs();
        Gr gr1 = null;
        for (Gr gr:grs) {
            if (gr.getNomeGr().equals(campoGr.getSelectedItem().toString())){
                gr1 = gr;
            }
        }
        membro.setGr(gr1);
        membro.setColete(campoColete.getSelectedItem().toString());

        return membro;
    }

    public String getNomeGr(){
        return campoGr.getSelectedItem().toString();
    }

    public void preencheMembro(Membro membro) {
        MembroDAO membroDAO = new MembroDAO(context);
        Membro mb1 = null;
        List<Membro> mbs = membroDAO.buscaMembro();
        for (Membro mb: mbs) {
            if(mb.getId() == membro.getId()){
                mb1 = mb;
            }
        }
        campoNome.setText(mb1.getNome());
        campoNaturalidade.setText(mb1.getNaturalidade());
        campoCelular.setText(mb1.getCelular() +"");

        DateFormat df = new SimpleDateFormat("MM/dd/yyyy");
        Date d = mb1.getNascimento();
        String reportDate = df.format(mb1.getNascimento());
        campoNascimento.setText(reportDate);

        if(mb1.getSexo().equals("feminino")){
            campoSexo.check(R.id.membroRadioF);
        } else{
            campoSexo.check(R.id.membroRadioM);

        }
        campoAltura.setText(mb1.getAltura() +"");
        campoLogradouro.setText(mb1.getLogradouro());
        campoNumero.setText(mb1.getNumero() +"");
        campoBairro.setText(mb1.getBairro());
        campoCep.setText(mb1.getCep());
        campoCidade.setText(mb1.getCidade());

        String compareValue = mb1.getUf();
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(context, R.array.states, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        if (compareValue != null){
            int spinnerPosition = adapter.getPosition(compareValue);
            campoUf.setSelection(spinnerPosition);
        }

        DateFormat dft = new SimpleDateFormat("MM/dd/yyyy");
        Date dt = membro.getNascimento();
        String reportDat = dft.format(membro.getData_conversao());
        campoDataConversao.setText(reportDat);

        campoEquipe.setText(membro.getEquipe());
        campoTempoPonte.setText(membro.getTempo_ponte());
        campoCargo.setText(membro.getCargo());

        String compareValues = membro.getGr().getNomeGr();
        GrDAO grDAO = new GrDAO(context);
        ArrayList<String> nomesGrs = new ArrayList<String>();
        List<Gr> grs1 = grDAO.buscaGrs();
        for (Gr gr: grs1) {
            nomesGrs.add(gr.getNomeGr());
        }

        ArrayAdapter<String> arrayAdapter =
                new ArrayAdapter<String>(context, android.R.layout.simple_spinner_dropdown_item, nomesGrs);
        ArrayAdapter<String> spinnerArrayAdapter = arrayAdapter;
        spinnerArrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        if (compareValues != null){
            int spinnerPosition = arrayAdapter.getPosition(compareValues);
            campoGr.setSelection(spinnerPosition);
        }

        String compareV = membro.getColete();
        ArrayAdapter<CharSequence> adapter2 = ArrayAdapter.createFromResource(context, R.array.coletes, android.R.layout.simple_spinner_item);
        adapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        if (compareV != null){
            int spinnerPosition = adapter2.getPosition(compareV);
            campoColete.setSelection(spinnerPosition);
        }

        this.membro = membro;
    }

}