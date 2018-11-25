package com.example.alessandro.apontemobile;

import android.widget.EditText;
import android.widget.Spinner;

import com.example.alessandro.apontemobile.modelo.Gr;

import java.sql.Date;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;

public class GrHelper {

    /*private final EditText campoNomeGr;
    private final EditText campoLiderGr;
    private final EditText campoApoioGr;
    private final EditText campoDiaGr;
    private final EditText campoHorarioGr;
    private final EditText campoContatoGr;
    private final EditText campoFrequenciaGr;
    private final EditText campoQuantidadeGr;
    private final EditText campoLogradouroGr;
    private final EditText campoNumeroGr;
    private final EditText campoBairroGr;
    private final EditText campoCepGr;
    private final EditText campoCidadeGr;
    private final Spinner campoUfGr;
    private final EditText campoInauguracaoGr;

    private Gr gr;

    public GrHelper(MaisGrActivity activity) {
        campoNomeGr = (EditText) activity.findViewById(R.id.gr_nome);
        campoLiderGr = (EditText) activity.findViewById(R.id.gr_lider);
        campoApoioGr = (EditText) activity.findViewById(R.id.gr_apoio);
        campoDiaGr = (EditText) activity.findViewById(R.id.gr_dia);
        campoHorarioGr = (EditText) activity.findViewById(R.id.gr_horario);
        campoContatoGr = (EditText) activity.findViewById(R.id.gr_contato);
        campoFrequenciaGr = (EditText) activity.findViewById(R.id.gr_frequencia);
        campoQuantidadeGr = (EditText) activity.findViewById(R.id.gr_quantidade);
        campoLogradouroGr = (EditText) activity.findViewById(R.id.gr_logradouro);
        campoNumeroGr = (EditText) activity.findViewById(R.id.gr_numero);
        campoBairroGr = (EditText) activity.findViewById(R.id.gr_bairro);
        campoCepGr = (EditText) activity.findViewById(R.id.gr_cep);
        campoCidadeGr = (EditText) activity.findViewById(R.id.gr_cidade);
        campoUfGr = (Spinner) activity.findViewById(R.id.gr_sp_state);
        campoInauguracaoGr = (EditText) activity.findViewById(R.id.gr_inauguracao);
        //gr = new Gr();
    }

    public Gr getGr() throws ParseException {
        gr.setNomeGr(campoNomeGr.getText().toString());
        gr.setLider(campoLiderGr.getText().toString());
        gr.setApoio(campoApoioGr.getText().toString());
        gr.setDia(campoDiaGr.getText().toString());
        gr.setHorario(Double.parseDouble(campoHorarioGr.getText().toString()));
        gr.setContato(Integer.parseInt(campoContatoGr.getText().toString()));
        gr.setFrequencia(campoFrequenciaGr.getText().toString());
        gr.setQuantidade(Integer.parseInt(campoQuantidadeGr.getText().toString()));
        gr.setLogradouro(campoLogradouroGr.getText().toString());
        gr.setNumero(Integer.parseInt(campoNumeroGr.getText().toString()));
        gr.setBairro(campoBairroGr.getText().toString());
        gr.setCep(campoCepGr.getText().toString());
        gr.setCidade(campoCidadeGr.getText().toString());
        gr.setUf(campoUfGr.getSelectedItem().toString());

        DateFormat formatter = new SimpleDateFormat("MM/dd/yyyy");
        Date date = (Date)formatter.parse(campoInauguracaoGr.getText().toString());

        gr.setInauguracao(date);

        return gr;
    }

    public void preencheFormularioGr(Gr gr) {
        campoNomeGr.setText(gr.getNomeGr());
        campoLiderGr.setText(gr.getLider());
        campoApoioGr.setText(gr.getApoio());
        campoDiaGr.setText(gr.getDia());
        campoHorarioGr.setText((int) gr.getHorario());
        campoContatoGr.setText(gr.getContato());
        campoFrequenciaGr.setText(gr.getFrequencia());
        campoQuantidadeGr.setText(gr.getQuantidade());
        campoLogradouroGr.setText(gr.getLogradouro());
        campoNumeroGr.setText(gr.getNumero());
        campoBairroGr.setText(gr.getBairro());
        campoCepGr.setText(gr.getCep());
        campoCidadeGr.setText(gr.getCidade());
        campoUfGr.setSelected(Boolean.parseBoolean(gr.getUf()));
        campoInauguracaoGr.setText((CharSequence) gr.getInauguracao());
        this.gr = gr;
    }*/

}
