package pt.isec.pa.apoio_poe.model.fsm;

public class AtribuicaoPropostaState extends Apoio_poeStateAdapter {
    public AtribuicaoPropostaState(Apoio_poeContext context, Apoio_poe ap) {
        super(ap, context);
    }

    @Override
    public Apoio_poeState getEstado() {
        return Apoio_poeState.ATRIBUICAO_PROP;
    }

    @Override
    public void voltar() {
        changeState(Apoio_poeState.CANDIDATURA);
    }

    @Override
    public boolean avancar() {
        changeState(Apoio_poeState.ATRIBUICAO_ORIENTADORES);
        return true;
    }
}
