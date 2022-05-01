package pt.isec.pa.apoio_poe.model.fsm;

import pt.isec.pa.apoio_poe.model.data.Aluno;

public class GerirAlunosState extends Apoio_poeStateAdapter {
    public GerirAlunosState(Apoio_poeContext context, Apoio_poe ap) {
        super(ap, context);
    }

    @Override
    public Apoio_poeState getEstado() {
        return Apoio_poeState.GERIR_ALUNOS;
    }

    @Override
    public boolean addAluno(Aluno a) {
        return ap.addAluno(a);
    }

    @Override
    public String listaAlunos() {
        return ap.listaAlunos();
    }

    @Override
    public boolean findAluno(long num) {
        return ap.findAluno(num);
    }

    @Override
    public boolean editAluno(Aluno a) {

        return ap.editAluno(a);
    }

    @Override
    public boolean remAluno(long num) {
        return ap.remAluno(num);

    }

    @Override
    public void voltar() {
        changeState(Apoio_poeState.CONFIGURACAO);
    }
}
