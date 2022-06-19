package pt.isec.pa.apoio_poe.model.fsm;

import pt.isec.pa.apoio_poe.model.data.Aluno;
import pt.isec.pa.apoio_poe.model.data.Candidatura;
import pt.isec.pa.apoio_poe.model.data.propostas.AutoProposta;
import pt.isec.pa.apoio_poe.model.data.propostas.Proposta;

public class CandidaturaState extends Apoio_poeStateAdapter {
    public CandidaturaState(Apoio_poeContext context, Apoio_poe ap) {
        super(ap, context);
    }

    @Override
    public Apoio_poeState getEstado() {
        return Apoio_poeState.CANDIDATURA;
    }

    @Override
    public void gerirCandidaturas() {
        changeState(Apoio_poeState.GERIR_CAND);
    }

    @Override
    public void voltar() {
        changeState(Apoio_poeState.CONFIGURACAO);
    }

    @Override
    public boolean avancar() {
        changeState(Apoio_poeState.ATRIBUICAO_PROP);
        return true;
    }


    public String findAlunostr(long num) {
        return ap.findAlunoStr(num);
    }

    @Override
    public String getlistaAauto() {//Alunos com auto proposta
        StringBuilder sb;
        sb = new StringBuilder("Alunos com auto Proposta: ");
        for(Proposta proposta : ap.listaPropostas)
            if(proposta.getClass().getSimpleName().equalsIgnoreCase("AutoProposta"))
                sb.append(String.format("\n %s",findAlunostr(proposta.getNumAluno())));
        return sb.toString();
    }

    @Override
    public String getlistaAcCand() {//Alunos com candidaturas
        StringBuilder sb;
        sb = new StringBuilder("Alunos com Candidaturas: ");
        for(Candidatura c : ap.listaCandidaturas)
            sb.append(String.format("\n %s",findAlunostr(c.getAlunoID())));
        return sb.toString();
    }

    @Override
    public String getlistaAsCand() {//Alunos sem candidaturas
        StringBuilder sb;
        int count=0, count_prop=0;
        sb = new StringBuilder("Alunos sem Candidaturas: ");
        for(Aluno a : ap.listaAlunos){
            count = 0;
            for(Candidatura c : ap.listaCandidaturas){
                if(a.getNum() == c.getAlunoID()){
                    count++;
                }
            }
            if(count==0){
                count_prop=0;
                for(Proposta proposta : ap.listaPropostas)

                    if(proposta.toString().contains(Long.toString(a.getNum()))){
                        count_prop++;
                    }
            }
            if(count_prop==0)
                sb.append(String.format("\n %s",a.toString()));

        }
        return sb.toString();
    }

    @Override
    public String getlistaAutoPropostas() {
        StringBuilder sb;
        sb = new StringBuilder("Alunos com auto Proposta: ");
        for(Proposta proposta : ap.listaPropostas)
            if(proposta.getClass().getSimpleName().equalsIgnoreCase("AutoProposta"))
                sb.append(String.format("\n %s",proposta.toString()));
        return sb.toString();
    }

    @Override
    public String listaPropostasDocentes() {
        StringBuilder sb;
        sb = new StringBuilder("Alunos com auto Proposta: ");
        for(Proposta proposta : ap.listaPropostas)
            if(proposta.getClass().getSimpleName().equalsIgnoreCase("Projeto"))
                sb.append(String.format("\n %s",proposta.toString()));
        return sb.toString();
    }

    @Override
    public String PropostasSemCand() {
        StringBuilder sb;
        int count =0;
        sb = new StringBuilder("Propostas sem Candidaturas: ");
        for(Proposta p: ap.listaPropostas) {
            count = 0;
            for (Candidatura c : ap.listaCandidaturas) {
                for (String n : c.getNumstr()) {
                    if(p.getPid().equalsIgnoreCase(n))
                        count++;
                }
            }
            if(count==0){
                sb.append(String.format("\n %s",p.toString()));
            }

        }
        return sb.toString();
    }

    @Override
    public boolean fechar() {
        if(ap.close == 1){
            ap.close = 2;
            return avancar();
        }
        return false;
    }

    @Override
    public String PropostasComCand() {
        StringBuilder sb;
        int count =0;
        sb = new StringBuilder("Alunos com Candidaturas: ");
        for(Proposta p: ap.listaPropostas) {
            count =0;
            for (Candidatura c : ap.listaCandidaturas) {
                for (String n : c.getNumstr()) {
                   if(p.getPid().equalsIgnoreCase(n))
                        count++;
                }
            }
            if(count>0){
                sb.append(String.format("\n %s",p.toString()));
            }

        }
        return sb.toString();
    }
}
