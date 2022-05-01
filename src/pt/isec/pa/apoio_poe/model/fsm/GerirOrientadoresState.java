package pt.isec.pa.apoio_poe.model.fsm;

public class GerirOrientadoresState extends Apoio_poeStateAdapter {
    public GerirOrientadoresState(Apoio_poeContext context, Apoio_poe ap) {
        super(ap, context);
    }

    @Override
    public Apoio_poeState getEstado() {
        return Apoio_poeState.GERIR_ORIENTADORES;
    }

    @Override
    public void voltar() {
        changeState(Apoio_poeState.ATRIBUICAO_ORIENTADORES);
    }
}
