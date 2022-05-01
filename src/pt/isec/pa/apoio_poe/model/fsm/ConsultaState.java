package pt.isec.pa.apoio_poe.model.fsm;

public class ConsultaState extends Apoio_poeStateAdapter {
    public ConsultaState(Apoio_poeContext context, Apoio_poe ap) {
        super(ap, context);
    }

    @Override
    public Apoio_poeState getEstado() {
        return Apoio_poeState.CONSULTA;
    }
}
