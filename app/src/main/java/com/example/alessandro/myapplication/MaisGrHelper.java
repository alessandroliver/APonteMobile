package com.example.alessandro.myapplication;
import android.content.Context;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;

import com.example.alessandro.myapplication.modelo.Gr;

import java.text.DateFormat;
import java.text.Format;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class MaisGrHelper {

    private final EditText campoNomeGr;
    private final EditText campoQuantidade;
    private final EditText campoHorario;
    private final EditText campoDia;
    private final EditText campoFrequencia;
    private final EditText campoLider;
    private final EditText campoApoio;
    private final EditText campoContato;
    private final EditText campoInauguracao;
    private final EditText campoLogradouro;
    private final EditText campoNumero;
    private final EditText campoBairro;
    private final EditText campoCep;
    private final EditText campoCidade;
    private final Spinner campoUf;
    private Gr gr;

    private Context context;

    public MaisGrHelper(MaisGrActivity activity, Context context){

        campoNomeGr = (EditText) activity.findViewById(R.id.gr_nome);
        campoQuantidade = (EditText) activity.findViewById(R.id.gr_quantidade);
        campoHorario = (EditText) activity.findViewById(R.id.gr_horario);
        campoDia = (EditText) activity.findViewById(R.id.gr_dia);
        campoFrequencia = (EditText) activity.findViewById(R.id.gr_frequencia);
        campoLider = (EditText) activity.findViewById(R.id.gr_lider);
        campoApoio = (EditText) activity.findViewById(R.id.gr_apoio);
        campoContato = (EditText) activity.findViewById(R.id.gr_contato);
        campoInauguracao = (EditText) activity.findViewById(R.id.gr_inauguracao);
        campoLogradouro = (EditText) activity.findViewById(R.id.gr_logradouro);
        campoNumero = (EditText) activity.findViewById(R.id.gr_numero);
        campoBairro = (EditText) activity.findViewById(R.id.gr_bairro);
        campoCep = (EditText) activity.findViewById(R.id.gr_cep);
        campoCidade = (EditText) activity.findViewById(R.id.gr_cidade);
        campoUf = (Spinner) activity.findViewById(R.id.gr_sp_state);
        this.context = context;
        gr = new Gr();
    }

    public Gr getGr(){
        gr.setNomeGr(campoNomeGr.getText().toString());
        gr.setQuantidade(Integer.valueOf(campoQuantidade.getText().toString()));
        gr.setHorario(Double.valueOf(campoHorario.getText().toString()));
        gr.setDia(campoDia.getText().toString());
        gr.setFrequencia(campoFrequencia.getText().toString());
        gr.setLider(campoLider.getText().toString());
        gr.setApoio(campoApoio.getText().toString());
        gr.setContato(Integer.valueOf(campoContato.getText().toString()));

        DateFormat df = new SimpleDateFormat("MM/dd/yyyy");
        Date date = null;
        try {
            date = df.parse(campoInauguracao.getText().toString());
        } catch (ParseException e) {
            e.printStackTrace();
        }
        Date d1 = date;
        gr.setInauguracao(date);
        gr.setLogradouro(campoLogradouro.getText().toString());
        gr.setNumero(Integer.valueOf(campoNumero.getText().toString()));
        gr.setBairro(campoBairro.getText().toString());
        gr.setCep(Integer.valueOf(campoCep.getText().toString()));
        gr.setCidade(campoCidade.getText().toString());
        gr.setUf(campoUf.getSelectedItem().toString());

        return gr;
    }

    public void preencheGr(Gr gr) {
        campoNomeGr.setText(gr.getNomeGr());
        campoQuantidade.setText(gr.getQuantidade() +"");
        campoHorario.setText(gr.getHorario() +"");
        campoDia.setText(gr.getDia());
        campoFrequencia.setText(gr.getFrequencia());
        campoLider.setText(gr.getLider());
        campoApoio.setText(gr.getApoio());
        campoContato.setText(gr.getContato() +"");

        DateFormat df = new SimpleDateFormat("MM/dd/yyyy");
        String reportDate = df.format(gr.getInauguracao());
        campoInauguracao.setText(reportDate);

        campoLogradouro.setText(gr.getLogradouro());
        campoNumero.setText(gr.getNumero() +"");
        campoBairro.setText(gr.getBairro());
        campoCep.setText(gr.getCep() +"");
        campoCidade.setText(gr.getCidade());

        String compareValue = gr.getUf();
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(context, R.array.states, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        if (compareValue != null){
            int spinnerPosition = adapter.getPosition(compareValue);
            campoUf.setSelection(spinnerPosition);
        }
        this.gr = gr;
    }

}
