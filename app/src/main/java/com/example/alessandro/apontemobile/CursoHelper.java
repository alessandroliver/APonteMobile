package com.example.alessandro.apontemobile;

import android.widget.EditText;
import android.widget.Spinner;

import com.example.alessandro.apontemobile.modelo.Curso;

import java.sql.Date;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;

public class CursoHelper {

    /*private final EditText campoNomeCurso;
    private final EditText campoProfessorCurso;
    private final EditText campoDataInicioCurso;
    private final EditText campoDataFimCurso;
    private final EditText campoDiaCurso;
    private final EditText campoSalaCurso;
    private final EditText campoPegarCurso;
    private final EditText campoLargarCurso;
    private final EditText campoCargaHorariaCurso;

    private Curso curso;

    public CursoHelper(MaisGrActivity activity) {
        campoNomeCurso = (EditText) activity.findViewById(R.id.curso_nome);
        campoProfessorCurso = (EditText) activity.findViewById(R.id.curso_professor);
        campoDataInicioCurso = (EditText) activity.findViewById(R.id.curso_inicio);
        campoDataFimCurso = (EditText) activity.findViewById(R.id.curso_fim);
        campoDiaCurso = (EditText) activity.findViewById(R.id.curso_dia);
        campoSalaCurso = (EditText) activity.findViewById(R.id.curso_sala);
        campoPegarCurso = (EditText) activity.findViewById(R.id.curso_pegar);
        campoLargarCurso = (EditText) activity.findViewById(R.id.curso_largar);
        campoCargaHorariaCurso = (EditText) activity.findViewById(R.id.curso_carga_horaria);
        //curso = new Curso();
    }

    public Curso getCurso() throws ParseException {
        curso.setNomeCurso(campoNomeCurso.getText().toString());
        curso.setProfessor(campoProfessorCurso.getText().toString());

        DateFormat formatter = new SimpleDateFormat("MM/dd/yyyy");
        Date date = (Date)formatter.parse(campoDataInicioCurso.getText().toString());

        curso.setInicio(date);

        DateFormat formatter1 = new SimpleDateFormat("MM/dd/yyyy");
        Date date1 = (Date)formatter1.parse(campoDataFimCurso.getText().toString());

        curso.setFim(date1);

        curso.setDia(campoDiaCurso.getText().toString());
        curso.setSala(campoSalaCurso.getText().toString());
        curso.setPegar(Double.parseDouble(campoPegarCurso.getText().toString()));
        curso.setLargar(Double.parseDouble(campoLargarCurso.getText().toString()));
        curso.setCarga_horaria(Integer.parseInt(campoCargaHorariaCurso.getText().toString()));

        return curso;
    }

    public void preencheFormularioCurso(Curso curso) {
        campoNomeCurso.setText(curso.getNomeCurso());
        campoProfessorCurso.setText(curso.getProfessor());
        campoDataInicioCurso.setText((CharSequence) curso.getInicio());
        campoDataFimCurso.setText((CharSequence) curso.getFim());
        campoDiaCurso.setText(curso.getDia());
        campoSalaCurso.setText(curso.getSala());
        campoPegarCurso.setText((int) curso.getPegar());
        campoLargarCurso.setText((int) curso.getLargar());
        campoCargaHorariaCurso.setText(curso.getCarga_horaria());
        this.curso = curso;
    }*/

}
