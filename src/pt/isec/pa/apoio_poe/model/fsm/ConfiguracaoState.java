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

    @Override
    public boolean fechar() {
        if(ap.getARedes() <= ap.getPRedes() && ap.getASI() <= ap.getPSI() && ap.getADA() <= ap.getPDA()) {
            ap.close = 1;
            return avancar();
        }
        return false;
    }

    @Override
    public String listaAlunos() {
        return ap.listaAlunos();
    }

    @Override
    public String listaDocentes() {
        return ap.listaDocentes();
    }

    @Override
    public String listaPropostas() {
        return ap.listaPropostas();
    }

    @Override
    public boolean isClose() {
        if(ap.close >0)
            return true;
        return false;
    }
}
