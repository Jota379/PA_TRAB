package pt.isec.pa.apoio_poe.model.fsm;

public enum Apoio_poeState {
    LOAD_STATE,CONFIGURACAO,GERIR_ALUNOS,GERIR_DOCENTES,GERIR_PROPOSTAS,CANDIDATURA,
    GERIR_CAND,ATRIBUICAO_PROP,ATRIBUICAO_ORIENTADORES,GERIR_ORIENTADORES,CONSULTA;

    IApoio_poeState createState(Apoio_poeContext context, Apoio_poe ap) {
        return switch (this) {
            case LOAD_STATE -> new LoadState(context, ap);
            case CONFIGURACAO -> new ConfiguracaoState(context, ap);
            case GERIR_ALUNOS -> new GerirAlunosState(context, ap);
            case GERIR_DOCENTES -> new GerirDocentesState(context, ap);
            case GERIR_PROPOSTAS -> new GerirPropostasState(context, ap);
            case CANDIDATURA -> new CandidaturaState(context, ap);
            case GERIR_CAND -> new GerirCandidaturaState(context, ap);
            case ATRIBUICAO_PROP -> new AtribuicaoPropostaState(context, ap);
            case ATRIBUICAO_ORIENTADORES -> new AtribuicaoOrientadoresState(context, ap);
            case GERIR_ORIENTADORES -> new GerirOrientadoresState(context, ap);
            case CONSULTA -> new ConsultaState(context, ap);

        };
    }
}
