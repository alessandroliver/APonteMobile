package com.example.alessandro.myapplication;

import android.content.Context;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;

import com.example.alessandro.myapplication.dao.CursoDAO;
import com.example.alessandro.myapplication.modelo.Aluno;
import com.example.alessandro.myapplication.modelo.Curso;

import java.text.DateFormat;
import java.text.Format;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class MaisCursoHelper {

    private final EditText campoNomeCurso;
    private final EditText campoInicio;
    private final EditText campoFim;
    private final EditText campoCargaHoraria;
    private final EditText campoProfessor;
    private final EditText campoDia;
    private final EditText campoPegar;
    private final EditText campoLargar;
    private final EditText campoSala;
    private final Spinner campoTurno;
    private Curso curso;

    private Context context;

    public MaisCursoHelper(MaisCursoActivity activity, Context context){
        campoNomeCurso = (EditText) activity.findViewById(R.id.curso_nome);
        campoInicio = (EditText) activity.findViewById(R.id.curso_inicio);
        campoFim = (EditText) activity.findViewById(R.id.curso_fim);
        campoCargaHoraria = (EditText) activity.findViewById(R.id.curso_carga_horaria);
        campoProfessor = (EditText) activity.findViewById(R.id.curso_professor);
        campoDia = (EditText) activity.findViewById(R.id.curso_dia);
        campoPegar = (EditText) activity.findViewById(R.id.curso_pegar);
        campoLargar = (EditText) activity.findViewById(R.id.curso_largar);
        campoSala = (EditText) activity.findViewById(R.id.curso_sala);
        campoTurno = (Spinner) activity.findViewById(R.id.sp_curso_turno);
        this.context = context;
        curso = new Curso();
    }

    public Curso getCurso() {
        curso.setNomeCurso(campoNomeCurso.getText().toString());
        DateFormat df = new SimpleDateFormat("MM/dd/yyyy");
        Date date = null;
        try {
            date = df.parse(campoInicio.getText().toString());
        } catch (ParseException e) {
            e.printStackTrace();
        }
        curso.setInicio(date);
        DateFormat dft = new SimpleDateFormat("MM/dd/yyyy");
        Date dat = null;
        try {
            dat = dft.parse(campoFim.getText().toString());
        } catch (ParseException e) {
            e.printStackTrace();
        }
        curso.setFim(dat);
        curso.setCarga_horaria(Integer.parseInt(campoCargaHoraria.getText().toString()));
        curso.setProfessor(campoProfessor.getText().toString());
        curso.setDia(campoDia.getText().toString());
        curso.setPegar(Double.parseDouble(campoPegar.getText().toString()));
        curso.setLargar(Double.parseDouble(campoLargar.getText().toString()));
        curso.setSala(campoSala.getText().toString());
        curso.setTurno(campoTurno.getSelectedItem().toString());
        CursoDAO cursoDAO = new CursoDAO(context);
        List<Aluno> alunos = cursoDAO.buscaAlunosCurso(campoNomeCurso.getText().toString());
        curso.setAluno(alunos);
        return curso;
    }

    public void preencheCurso(Curso curso) {
        /*CursoDAO cursoDAO = new CursoDAO(context);
        Curso c1 = null;
        List<Cafe> crs = cursoDAO.buscaCurso();
        for (Cafe cr: crs) {
            if(cr.getId() == curso.getId()){
                c1 = cr;
            }
        }*/
        campoNomeCurso.setText(curso.getNomeCurso());
        DateFormat df = new SimpleDateFormat("MM/dd/yyyy");
        String reportDate = df.format(curso.getInicio());
        campoInicio.setText(reportDate);
        DateFormat dft = new SimpleDateFormat("MM/dd/yyyy");
        String reportDat = dft.format(curso.getFim());
        campoFim.setText(reportDat);
        campoCargaHoraria.setText(curso.getCarga_horaria() +"");
        campoProfessor.setText(curso.getProfessor());
        campoDia.setText(curso.getDia());
        campoPegar.setText(curso.getPegar() +"");
        campoLargar.setText(curso.getLargar() +"");
        campoSala.setText(curso.getSala() +"");
        String compareValue = curso.getTurno();
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(context, R.array.turnos, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        if (compareValue != null){
            int spinnerPosition = adapter.getPosition(compareValue);
            campoTurno.setSelection(spinnerPosition);
        }

        this.curso = curso;
    }

}
