package pt.isec.pa.apoio_poe.model.fsm;

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
}
