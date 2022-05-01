package pt.isec.pa.apoio_poe.model.fsm;

public class AtribuicaoOrientadoresState extends Apoio_poeStateAdapter {
    public AtribuicaoOrientadoresState(Apoio_poeContext context, Apoio_poe ap) {
        super(ap, context);
    }

    @Override
    public Apoio_poeState getEstado() {
        return Apoio_poeState.ATRIBUICAO_ORIENTADORES;
    }

    @Override
    public void gerirOrientadores() {
        changeState(Apoio_poeState.GERIR_ORIENTADORES);
    }

    @Override
    public void voltar() {
        changeState(Apoio_poeState.ATRIBUICAO_PROP);
    }

    @Override
    public boolean avancar() {
        changeState(Apoio_poeState.CONSULTA);
        return true;
    }
}
