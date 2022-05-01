package pt.isec.pa.apoio_poe.model.fsm;

public class ConfiguracaoState extends Apoio_poeStateAdapter {
    public ConfiguracaoState(Apoio_poeContext context, Apoio_poe ap) {
        super(ap, context);
    }

    @Override
    public Apoio_poeState getEstado() {
        return Apoio_poeState.CONFIGURACAO;
    }

    @Override
    public void gerirAlunos() {
        changeState(Apoio_poeState.GERIR_ALUNOS);
    }

    @Override
    public void gerirDocentes() {
        changeState(Apoio_poeState.GERIR_DOCENTES);
    }

    @Override
    public void gerirPropostas() {
        changeState(Apoio_poeState.GERIR_PROPOSTAS);
    }

    @Override
    public boolean avancar() {
        changeState(Apoio_poeState.CANDIDATURA);
        return true;
    }
}
