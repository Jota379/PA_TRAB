package pt.isec.pa.apoio_poe.model.fsm;

import pt.isec.pa.apoio_poe.model.data.propostas.Proposta;

public class GerirPropostasState extends Apoio_poeStateAdapter {
    public GerirPropostasState(Apoio_poeContext context, Apoio_poe ap) {
        super(ap, context);
    }

    @Override
    public Apoio_poeState getEstado() {
        return Apoio_poeState.GERIR_PROPOSTAS;
    }

    @Override
    public void voltar() {
        changeState(Apoio_poeState.CONFIGURACAO);
    }

    @Override
    public String listaPropostas() {
        return ap.listaPropostas();
    }

    @Override
    public boolean addPropostas(Proposta p) {
        return ap.addProposta(p);
    }

    @Override
    public boolean findAluno(long num) {
        return ap.findAluno(num);
    }

    @Override
    public boolean findDocente(String email) {
        return ap.findDocente(email);
    }

    @Override
    public Proposta getProposta(String id_da_proposta) {
        return ap.getProposta(id_da_proposta);
    }

    @Override
    public boolean editPropostas(Proposta pt) {
        return ap.editPropostas(pt);
    }

    @Override
    public boolean remPropostas(String id_da_proposta) {

        return ap.remPropostas(id_da_proposta);
    }
}
