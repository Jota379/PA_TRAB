package pt.isec.pa.apoio_poe.model.fsm;

import pt.isec.pa.apoio_poe.model.data.Docente;

public class GerirDocentesState extends Apoio_poeStateAdapter {
    public GerirDocentesState(Apoio_poeContext context, Apoio_poe ap) {
        super(ap, context);
    }

    @Override
    public Apoio_poeState getEstado() {
        return Apoio_poeState.GERIR_DOCENTES;
    }

    @Override
    public boolean addDocente(Docente docente) {
        return ap.addDocente(docente);
    }

    @Override
    public boolean editDocente(Docente docente) {
        return ap.editDocente(docente);
    }

    @Override
    public boolean findDocente(String email) {
        return ap.findDocente(email);
    }

    @Override
    public String listaDocentes() {
        return ap.listaDocentes();
    }

    @Override
    public void voltar() {
        changeState(Apoio_poeState.CONFIGURACAO);
    }

    @Override
    public boolean remDocente(String email) {
        return ap.remDocente(email);
    }
}
