package com.example.alessandro.myapplication;

import android.content.Context;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Spinner;

import com.example.alessandro.myapplication.dao.VoluntarioDAO;
import com.example.alessandro.myapplication.modelo.Voluntario;

import java.text.DateFormat;
import java.text.Format;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class MaisVoluntarioHelper {

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
    private final EditText campoFuncao;
    private final EditText campoHorarioPegar;
    private final EditText campoHorarioLargar;
    private final EditText campoHoraSemanal;
    private final Spinner campoTamanhoCamisa;
    private final EditText campoCpf;
    private final EditText campoRg;
    private final EditText campoArea;

    private Voluntario voluntario;
    private Context context;
    public MaisVoluntarioHelper(MaisVoluntarioActivity activity, Context context){
        this.context = context;
        campoNome = (EditText) activity.findViewById(R.id.voluntario_nome);
        campoNaturalidade = (EditText) activity.findViewById(R.id.voluntario_naturalidade);
        campoCelular = (EditText) activity.findViewById(R.id.voluntario_celular);
        campoNascimento = (EditText) activity.findViewById(R.id.voluntario_nascimento);
        campoSexo = (RadioGroup) activity.findViewById(R.id.voluntario_sexo);
        campoAltura = (EditText) activity.findViewById(R.id.voluntario_altura);
        campoLogradouro = (EditText) activity.findViewById(R.id.voluntario_logradouro);
        campoNumero = (EditText) activity.findViewById(R.id.voluntario_numero);
        campoBairro = (EditText) activity.findViewById(R.id.voluntario_bairro);
        campoCep = (EditText) activity.findViewById(R.id.voluntario_cep);
        campoCidade = (EditText) activity.findViewById(R.id.voluntario_cidade);
        campoUf = (Spinner) activity.findViewById(R.id.voluntario_sp_state);
        campoFuncao = (EditText) activity.findViewById(R.id.voluntario_funcao);
        campoHorarioPegar = (EditText) activity.findViewById(R.id.voluntario_horario_pegar);
        campoHorarioLargar = (EditText) activity.findViewById(R.id.voluntario_horario_largar);
        campoHoraSemanal = (EditText) activity.findViewById(R.id.voluntario_hora_semanal);
        campoTamanhoCamisa = (Spinner) activity.findViewById(R.id.voluntario_sp_camisa);
        campoCpf = (EditText) activity.findViewById(R.id.voluntario_cpf);
        campoRg = (EditText) activity.findViewById(R.id.voluntario_rg);
        campoArea = (EditText) activity.findViewById(R.id.voluntario_area);
        voluntario = new Voluntario();
    }

    public Voluntario getVoluntario() {
        voluntario.setNome(campoNome.getText().toString());
        voluntario.setNaturalidade(campoNaturalidade.getText().toString());
        voluntario.setCelular(Integer.valueOf(campoCelular.getText().toString()));

        DateFormat df = new SimpleDateFormat("MM/dd/yyyy");
        Date date = null;
        try {
            date = df.parse(campoNascimento.getText().toString());
        } catch (ParseException e) {
            e.printStackTrace();
        }
        Date d1 = date;
        voluntario.setNascimento(date);
        String sx = "";
        switch (campoSexo.getCheckedRadioButtonId()) {
            case R.id.voluntarioRadioM:
                sx = "masculino";
                break;
            case R.id.voluntarioRadioF:
                sx = "feminino";
                break;
        }
        voluntario.setSexo(sx);
        voluntario.setAltura(Double.valueOf(campoAltura.getText().toString()));
        voluntario.setLogradouro(campoLogradouro.getText().toString());
        voluntario.setNumero(Integer.valueOf(campoNumero.getText().toString()));
        voluntario.setBairro(campoBairro.getText().toString());
        voluntario.setCep(campoCep.getText().toString());
        voluntario.setCidade(campoCidade.getText().toString());
        voluntario.setUf(campoUf.getSelectedItem().toString());
        voluntario.setFuncao(campoFuncao.getText().toString());
        voluntario.setHorario_pegar(Double.valueOf(campoHorarioPegar.getText().toString()));
        voluntario.setHorario_largar(Double.valueOf(campoHorarioLargar.getText().toString()));
        voluntario.setHora_semanal(Double.valueOf(campoHoraSemanal.getText().toString()));
        voluntario.setTamanho_camisa(campoTamanhoCamisa.getSelectedItem().toString());
        voluntario.setCpf(Integer.valueOf(campoCpf.getText().toString()));
        voluntario.setRg(Integer.valueOf(campoRg.getText().toString()));
        voluntario.setArea(campoArea.getText().toString());

        return voluntario;
    }

    public void preencheVoluntario(Voluntario voluntario) {
        VoluntarioDAO voluntarioDAO = new VoluntarioDAO(context);
        Voluntario v1 = null;
        List<Voluntario> vs = voluntarioDAO.buscaVoluntario();
        for (Voluntario v2: vs) {
            if(v2.getId() == voluntario.getId()){
                v1= v2;
            }
        }
        campoNome.setText(v1.getNome());
        campoNaturalidade.setText(v1.getNaturalidade());
        campoCelular.setText(v1.getCelular() +"");
        DateFormat df = new SimpleDateFormat("MM/dd/yyyy");
        String reportDate = df.format(v1.getNascimento());
        campoNascimento.setText(reportDate);
        if(v1.getSexo().equals("feminino")){
            campoSexo.check(R.id.voluntarioRadioF);
        } else{
            campoSexo.check(R.id.voluntarioRadioM);

        }
        campoAltura.setText(v1.getAltura() +"");
        campoLogradouro.setText(v1.getLogradouro());
        campoNumero.setText(v1.getNumero() +"");
        campoBairro.setText(v1.getBairro());
        campoCep.setText(v1.getCep());
        campoCidade.setText(v1.getCidade());
        String compareValue = v1.getUf();
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(context, R.array.states, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        if (compareValue != null){
            int spinnerPosition = adapter.getPosition(compareValue);
            campoUf.setSelection(spinnerPosition);
        }

        campoFuncao.setText(voluntario.getFuncao());
        campoHorarioPegar.setText(voluntario.getHorario_pegar() +"");
        campoHorarioLargar.setText(voluntario.getHorario_largar() +"");
        campoHoraSemanal.setText(voluntario.getHora_semanal() +"");

        String compareValues = voluntario.getTamanho_camisa();
        ArrayAdapter<CharSequence> adapters = ArrayAdapter.createFromResource(context, R.array.camisas, android.R.layout.simple_spinner_item);
        adapters.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        if (compareValues != null){
            int spinnerPosition = adapters.getPosition(compareValues);
            campoTamanhoCamisa.setSelection(spinnerPosition);
        }

        campoCpf.setText(voluntario.getCpf() +"");
        campoRg.setText(voluntario.getRg() +"");
        campoArea.setText(voluntario.getArea());
        this.voluntario = voluntario;
    }

}
