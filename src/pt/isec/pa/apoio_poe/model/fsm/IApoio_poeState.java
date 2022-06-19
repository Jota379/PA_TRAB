package pt.isec.pa.apoio_poe.model.fsm;

import pt.isec.pa.apoio_poe.model.data.Aluno;
import pt.isec.pa.apoio_poe.model.data.Candidatura;
import pt.isec.pa.apoio_poe.model.data.Docente;
import pt.isec.pa.apoio_poe.model.data.propostas.Proposta;

public interface IApoio_poeState {
    Apoio_poeState getEstado();
    //Comum
    boolean fechar();
    boolean avancar();
    void voltar();

    //Configuração //estado
    void gerirAlunos(); //estado
        String listaAlunos();
        boolean addAluno(Aluno a);
        boolean remAluno(long num);
        boolean editAluno(Aluno a);
    void gerirDocentes(); //estado
        String listaDocentes();
        boolean addDocente(Docente docente);
        boolean remDocente(String email);
        boolean editDocente(Docente docente);
    void gerirPropostas(); //estado
        String listaPropostas();
        boolean addPropostas(Proposta p);
        boolean remPropostas(String id_da_proposta);
        boolean editPropostas(Proposta pt);
    //Opções de Candidatura //estado
    void gerirCandidaturas();   //estado
    void listaAlunos2();//varias formas
    void listaPropostas2();//varias formas

    //Atribuição de propostas //estado
    void atribuicaoAuto();
    void atribuicaoMan();
    void listaAlunos3();
    void listaPropostas3();

    //Atribuição de Orientadores //estado

    void gerirOrientadores(); //estado
    void listasFase4();

    boolean findAluno(long num);

    boolean findDocente(String email);

    Proposta getProposta(String id_da_proposta);

    boolean isClose();

    String getlistaAauto();

    String getlistaAcCand();

    String getlistaAsCand();

    String getlistaAutoPropostas();

    String listaPropostasDocentes();

    String PropostasComCand();

    String PropostasSemCand();

    void addCandidatura(long numero, String projID);

    String listaCandidaturas();

    boolean findCandidatura(long numAluno);

    void remCandidatura(long numAluno);

    void remCandidatura(long num, String id_da_proposta);

    boolean addCandidatura(Candidatura c);

    //Consulta
        //listas
}
