package pt.isec.pa.apoio_poe.model.fsm;

public class CandidaturaState extends Apoio_poeStateAdapter {
    public CandidaturaState(Apoio_poeContext context, Apoio_poe ap) {
        super(ap, context);
    }

    @Override
    public Apoio_poeState getEstado() {
        return Apoio_poeState.CANDIDATURA;
    }

    @Override
    public void gerirCandidaturas() {
        changeState(Apoio_poeState.GERIR_CAND);
    }

    @Override
    public void voltar() {
        changeState(Apoio_poeState.CONFIGURACAO);
    }

    @Override
    public boolean avancar() {
        changeState(Apoio_poeState.ATRIBUICAO_PROP);
        return true;
    }
}
