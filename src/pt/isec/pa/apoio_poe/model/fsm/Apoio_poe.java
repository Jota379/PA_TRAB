package pt.isec.pa.apoio_poe.model.fsm;

import pt.isec.pa.apoio_poe.model.data.*;
import pt.isec.pa.apoio_poe.model.data.propostas.Proposta;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

public class Apoio_poe implements Serializable {
    Set<Proposta> listaPropostas;
    Set<Aluno> listaAlunos;
    Set<Docente> listaDocentes;
    Set<Candidarura> listaCandidaturas;

    public Apoio_poe() {
        this.listaPropostas = new HashSet<>();
        this.listaAlunos = new HashSet<>();
        this.listaDocentes = new HashSet<>();
        this.listaCandidaturas = new HashSet<>();
    }

    public boolean addAluno(Aluno a){
        if(emailexiste(a.getEmail()))
            return false;
        if(!listaAlunos.add(a))
            return false;
        return true;
    }

    private boolean emailexiste(String email) {
        for(Docente docente : listaDocentes)
            if(email.equalsIgnoreCase(docente.getEmail()))
                return true;
        for(Aluno aluno : listaAlunos)
            if(email.equalsIgnoreCase(aluno.getEmail()))
                return true;
        return false;
    }

    public String listaAlunos(){
        StringBuilder sb = new StringBuilder("Alunos: ");
        if (listaAlunos ==null || listaAlunos.size()==0)
            sb.append("Não ha alunos");
        else
            for(Aluno aluno : listaAlunos)
                sb.append(String.format("\n %s",aluno.toString()));
        return sb.toString();
    }

    public boolean findAluno(long n){
        for(Aluno aluno : listaAlunos)
            if(aluno.getNum() == n)
                return true;
        return false;
    }

    private Aluno findAlunoR(long n){
        for(Aluno aluno : listaAlunos)
            if(aluno.getNum() == n)
                return aluno;
        return null;
    }


    public boolean editAluno(Aluno a) {

        if(emailexisteX(a.getEmail())!=1 && emailexisteX(a.getEmail())!=0)
            return false;
        for(Aluno aluno : listaAlunos)
            if(aluno.getNum() == a.getNum()) {
                aluno.setEmail(a.getEmail());
                aluno.setNome(a.getNome());
                aluno.setSiglaCurso(Cursos.valueOf(a.getSiglaCurso()));
                aluno.setSiglaRamo(Ramos.valueOf(a.getSiglaRamo()));
            }

        return true;
    }

    private int emailexisteX(String email) {
        int cont=0;
        for(Docente docente : listaDocentes)
            if(email.equalsIgnoreCase(docente.getEmail()))
                cont=-20;
        for(Aluno aluno : listaAlunos)
            if(email.equalsIgnoreCase(aluno.getEmail()))
                cont++;
        return cont;
    }

    public boolean remAluno(long num) {
        return listaAlunos.remove(findAlunoR(num));
    }

    public boolean addDocente(Docente docente) {
        if(emailexiste(docente.getEmail()))
            return false;
        listaDocentes.add(docente);
        return true;
    }

    public String listaDocentes(){
        StringBuilder sb = new StringBuilder("Docentes: ");
        if (listaDocentes ==null || listaDocentes.size()==0)
            sb.append("Não ha Docentes");
        else
            for(Docente docente : listaDocentes)
                sb.append(String.format("\n %s",docente.toString()));
        return sb.toString();
    }

    public boolean editDocente(Docente d) {

        if(emailexisteX(d.getEmail())!=-20)
            return false;
        for(Docente docente : listaDocentes)
            if(docente.getEmail().equalsIgnoreCase(d.getEmail())) {
                docente.setNome(d.getNome());
            }

        return true;
    }

    public boolean findDocente(String email) {
        for(Docente docente : listaDocentes) {
            if (docente.getEmail().equalsIgnoreCase(email))
                return true;
        }
        return false;
    }

    public boolean remDocente(String email) {
        return listaDocentes.remove(findDocenteR(email));
    }

    private Docente findDocenteR(String email) {
        for(Docente docente : listaDocentes) {
            if (docente.getEmail().equalsIgnoreCase(email))
                return docente;
        }
        return null;
    }

    public String listaPropostas() {
        StringBuilder sb = new StringBuilder("Propostas: ");
        if (listaPropostas ==null || listaPropostas.size()==0)
            sb.append("Não ha Propostas");
        else
            for(Proposta proposta : listaPropostas)
                sb.append(String.format("\n %s",proposta.toString()));
        return sb.toString();
    }

    public boolean addProposta(Proposta p) {
        listaPropostas.add(p);
        return true;
    }

    public Proposta getProposta(String id_da_proposta) {
        for(Proposta proposta : listaPropostas) {
            if (proposta.getPid().equalsIgnoreCase(id_da_proposta))
                return proposta;
        }
        return null;
    }

    public boolean editPropostas(Proposta pt) {
        for(Proposta proposta : listaPropostas) {
            if (proposta.getPid().equalsIgnoreCase(pt.getPid())) {
                listaPropostas.remove(proposta);
                listaPropostas.add(pt);
                return true;
            }
        }
        return false;
    }

    public boolean remPropostas(String id_da_proposta) {
        for(Proposta proposta : listaPropostas) {
            if (proposta.getPid().equalsIgnoreCase(id_da_proposta)) {
                listaPropostas.remove(proposta);
                return true;
            }
        }
        return false;
    }
}
