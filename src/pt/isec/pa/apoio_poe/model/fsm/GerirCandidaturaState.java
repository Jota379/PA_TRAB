package pt.isec.pa.apoio_poe.model.fsm;

import pt.isec.pa.apoio_poe.model.data.Candidatura;

public class GerirCandidaturaState extends Apoio_poeStateAdapter {
    public GerirCandidaturaState(Apoio_poeContext context, Apoio_poe ap) {
        super(ap, context);
    }

    @Override
    public Apoio_poeState getEstado() {
        return Apoio_poeState.GERIR_CAND;
    }

    @Override
    public boolean findDocente(String email) {
        return false;
    }

    @Override
    public void voltar() {
        changeState(Apoio_poeState.CANDIDATURA);
    }

    @Override
    public boolean findAluno(long num) {
        return ap.findAluno(num);
    }

    @Override
    public void addCandidatura(long numero, String projID) {
        ap.addCandidatura(numero,projID);
    }

    @Override
    public String listaCandidaturas() {
        return ap.listaCandidaturas();
    }

    @Override
    public boolean findCandidatura(long numAluno) {
        return ap.findCandidatura(numAluno);
    }

    @Override
    public void remCandidatura(long numAluno) {
        ap.remCandidatura(numAluno);
    }

    @Override
    public void remCandidatura(long num, String id_da_proposta) {
        ap.remCandidatura(num, id_da_proposta);
    }

    @Override
    public boolean addCandidatura(Candidatura c) {
        return ap.addCandidatura(c);
    }
}
