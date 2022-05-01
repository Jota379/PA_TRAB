package pt.isec.pa.apoio_poe.model.fsm;

public class AtribuicaoOrientadoresState extends Apoio_poeStateAdapter {
    public AtribuicaoOrientadoresState(Apoio_poeContext context, Apoio_poe ap) {
        super(ap, context);
    }

    @Override
    public Apoio_poeState getEstado() {
        return Apoio_poeState.ATRIBUICAO_ORIENTADORES;
    }
}
