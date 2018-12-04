package com.example.alessandro.myapplication;

import android.content.Context;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Spinner;

import com.example.alessandro.myapplication.dao.AlunoDAO;
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

public class MaisAlunoHelper {

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
    private final EditText campoMatricula;
    private final Spinner campoFarda;
    private final EditText campoResponsavel;
    private final EditText campoSerie;
    private final EditText campoEscola;
    private final Spinner campoCurso1;
    private final Spinner campoCurso2;

    private Aluno aluno;
    private Context context;
    public MaisAlunoHelper(MaisAlunoActivity activity, Context context){
        this.context = context;
        campoNome = (EditText) activity.findViewById(R.id.formulario_nome);
        campoNaturalidade = (EditText) activity.findViewById(R.id.formulario_naturalidade);
        campoCelular = (EditText) activity.findViewById(R.id.formulario_celular);
        campoNascimento = (EditText) activity.findViewById(R.id.formulario_nascimento);
        campoSexo = (RadioGroup) activity.findViewById(R.id.formulario_sexo);
        campoAltura = (EditText) activity.findViewById(R.id.formulario_altura);
        campoLogradouro = (EditText) activity.findViewById(R.id.formulario_logradouro);
        campoNumero = (EditText) activity.findViewById(R.id.formulario_numero);
        campoBairro = (EditText) activity.findViewById(R.id.formulario_bairro);
        campoCep = (EditText) activity.findViewById(R.id.formulario_cep);
        campoCidade = (EditText) activity.findViewById(R.id.formulario_cidade);
        campoUf = (Spinner) activity.findViewById(R.id.sp_state);
        campoMatricula = (EditText) activity.findViewById(R.id.formulario_matricula);
        campoFarda = (Spinner) activity.findViewById(R.id.sp_farda);
        campoResponsavel = (EditText) activity.findViewById(R.id.formulario_responsavel);
        campoSerie = (EditText) activity.findViewById(R.id.formulario_serie);
        campoEscola = (EditText) activity.findViewById(R.id.formulario_escola);
        campoCurso1 = (Spinner) activity.findViewById(R.id.sp_aluno_curso1);
        campoCurso2 = (Spinner) activity.findViewById(R.id.sp_aluno_curso2);
        aluno = new Aluno();
    }

    public Aluno getAluno() {
        aluno.setNome(campoNome.getText().toString());
        aluno.setNaturalidade(campoNaturalidade.getText().toString());
        aluno.setCelular(Integer.valueOf(campoCelular.getText().toString()));

        DateFormat df = new SimpleDateFormat("MM/dd/yyyy");
        Date date = null;
        try {
            date = df.parse(campoNascimento.getText().toString());
        } catch (ParseException e) {
            e.printStackTrace();
        }
        aluno.setNascimento(date);
        String sx = "";
        switch (campoSexo.getCheckedRadioButtonId()) {
            case R.id.radioM:
                sx = "masculino";
                break;
            case R.id.radioF:
                sx = "feminino";
                break;
        }
        aluno.setSexo(sx);
        aluno.setAltura(Double.valueOf(campoAltura.getText().toString()));
        aluno.setLogradouro(campoLogradouro.getText().toString());
        aluno.setNumero(Integer.valueOf(campoNumero.getText().toString()));
        aluno.setBairro(campoBairro.getText().toString());
        aluno.setCep(campoCep.getText().toString());
        aluno.setCidade(campoCidade.getText().toString());
        aluno.setUf(campoUf.getSelectedItem().toString());
        aluno.setMatricula(campoMatricula.getText().toString());
        aluno.setFarda(campoFarda.getSelectedItem().toString());
        aluno.setResponsavel(campoResponsavel.getText().toString());
        aluno.setSerie(campoSerie.getText().toString());
        aluno.setEscola(campoEscola.getText().toString());
        List<Curso> cursos = new ArrayList<Curso>();
        if(!(campoCurso1.getSelectedItem().toString().equals(""))){
            CursoDAO cursoDAO = new CursoDAO(this.context);
            AlunoDAO alunoDAO = new AlunoDAO(this.context);
            List<Curso> cursos1 = cursoDAO.buscaCursos();
            for (Curso curso:cursos1) {
                if (curso.getNomeCurso().equals(campoCurso1.getSelectedItem().toString())){
                    cursos.add(curso);
                    alunoDAO.insereCursoAluno(curso, campoNome.getText().toString());
                }

            }

        }
        if(!(campoCurso2.getSelectedItem().toString().equals(""))){
            CursoDAO cursoDAO = new CursoDAO(this.context);
            AlunoDAO alunoDAO = new AlunoDAO(this.context);
            List<Curso> cursos2 = cursoDAO.buscaCursos();
            for (Curso curso:cursos2) {
                if (curso.getNomeCurso().equals(campoCurso2.getSelectedItem().toString())){
                    cursos.add(curso);
                    alunoDAO.insereCursoAluno(curso, campoNome.getText().toString());
                }

            }

        }
        //add alunos aos cursos selecionados - no BD de cursos
        CursoDAO cursoDAO = new CursoDAO(context);
        if(!(campoCurso1.getSelectedItem().toString().equals(""))){
            cursoDAO.insereAlunoCurso(aluno, campoCurso1.getSelectedItem().toString());

        }
        if(!(campoCurso2.getSelectedItem().toString().equals(""))){
            cursoDAO.insereAlunoCurso(aluno, campoCurso2.getSelectedItem().toString());

        }

        aluno.setCurso(cursos);

        return aluno;
    }

    public void preencheAluno(Aluno aluno) {
        AlunoDAO alunoDAO = new AlunoDAO(context);
        Aluno a1 = null;
        List<Aluno> alu = alunoDAO.buscaAluno();
        for (Aluno a2: alu) {
            if(a2.getId() == aluno.getId()){
                a1= a2;
            }
        }
        campoNome.setText(a1.getNome());
        campoNaturalidade.setText(a1.getNaturalidade());
        campoCelular.setText(a1.getCelular() +"");
        DateFormat df = new SimpleDateFormat("MM/dd/yyyy");
        String reportDate = df.format(a1.getNascimento());
        campoNascimento.setText(reportDate);
        if(a1.getSexo().equals("feminino")){
            campoSexo.check(R.id.radioF);
        } else{
            campoSexo.check(R.id.radioM);

        }
        campoAltura.setText(a1.getAltura() +"");
        campoLogradouro.setText(a1.getLogradouro());
        campoNumero.setText(a1.getNumero() +"");
        campoBairro.setText(a1.getBairro());
        campoCep.setText(a1.getCep());
        campoCidade.setText(a1.getCidade());
        String compareValue = a1.getUf();
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(context, R.array.states, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        if (compareValue != null){
            int spinnerPosition = adapter.getPosition(compareValue);
            campoUf.setSelection(spinnerPosition);
        }

        campoMatricula.setText(aluno.getMatricula());

        String compareValues = aluno.getFarda();
        ArrayAdapter<CharSequence> adapters = ArrayAdapter.createFromResource(context, R.array.camisas, android.R.layout.simple_spinner_item);
        adapters.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        if (compareValues != null){
            int spinnerPosition = adapters.getPosition(compareValues);
            campoFarda.setSelection(spinnerPosition);
        }

        campoResponsavel.setText(aluno.getResponsavel());
        campoSerie.setText(aluno.getSerie());
        campoEscola.setText(aluno.getEscola());

        CursoDAO cursoDAO = new CursoDAO(context);
        ArrayList<String> cursosManha = new ArrayList<String>();
        ArrayList<String> cursosTarde = new ArrayList<String>();
        List<Curso> cursos = cursoDAO.buscaCursos();
        for (Curso curso:cursos) {
            if (curso.getTurno().equals("Manhã")){
                cursosManha.add(curso.getNomeCurso());
            }else{
                cursosTarde.add(curso.getNomeCurso());
            }

        }
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(context, android.R.layout.simple_spinner_dropdown_item, cursosManha);
        ArrayAdapter<String> spinnerArrayAdapter = arrayAdapter;
        spinnerArrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_item);
        ArrayAdapter<String> arrayAdapter1 = new ArrayAdapter<String>(context, android.R.layout.simple_spinner_dropdown_item, cursosTarde);
        ArrayAdapter<String> spinnerArrayAdapter1 = arrayAdapter1;

        List<Curso> cursoList = alunoDAO.buscaCursosAluno(a1.getNome());
        String compareV = cursoList.get(0).getNomeCurso();
        spinnerArrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        if (compareV != null){
            int spinnerPosition = arrayAdapter.getPosition(compareV);
            campoCurso1.setSelection(spinnerPosition);
        }

        String compareVa = cursoList.get(1).getNomeCurso();
        spinnerArrayAdapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        if (compareVa != null){
            int spinnerPosition = arrayAdapter1.getPosition(compareVa);
            campoCurso2.setSelection(spinnerPosition);
        }

        this.aluno = aluno;
    }

}
