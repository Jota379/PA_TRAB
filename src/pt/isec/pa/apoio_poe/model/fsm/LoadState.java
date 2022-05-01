package pt.isec.pa.apoio_poe.model.fsm;

public class LoadState extends Apoio_poeStateAdapter {
    public LoadState(Apoio_poeContext context, Apoio_poe ap) {
        super(ap, context);
    }

    @Override
    public Apoio_poeState getEstado() {
        return Apoio_poeState.LOAD_STATE;
    }

    @Override
    public boolean avancar() {
        changeState(Apoio_poeState.CONFIGURACAO);
        return true;
    }

}
