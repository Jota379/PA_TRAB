package pt.isec.pa.apoio_poe.model.fsm;

import pt.isec.pa.apoio_poe.model.data.*;
import pt.isec.pa.apoio_poe.model.data.propostas.Proposta;

import java.io.Serializable;
import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

public class Apoio_poe implements Serializable {
    Set<Proposta> listaPropostas;
    Set<Aluno> listaAlunos;
    Set<Docente> listaDocentes;
    Set<Candidatura> listaCandidaturas;
    int close=0;

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

    public String findAlunoStr(long n){
        for(Aluno aluno : listaAlunos)
            if(aluno.getNum() == n)
                return aluno.toString();
        return null;
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

    public int getARedes() {
        int conta = 0;
        for(Aluno aluno : listaAlunos)
            if(aluno.getSiglaRamo().equalsIgnoreCase(Ramos.RAS.toString()))
                conta++;
        return conta;
    }

    public int getPRedes() {
        int conta=0;
        for(Proposta proposta : listaPropostas) {
            if(proposta.toString().contains(Ramos.RAS.toString())) {
                conta++;
            }
        }
        return conta;
    }


    public int getASI() {
        int conta = 0;
        for(Aluno aluno : listaAlunos)
            if(aluno.getSiglaRamo().equalsIgnoreCase(Ramos.SI.toString()))
                conta++;
        return conta;
    }

    public int getPSI() {
        int conta=0;
        for(Proposta proposta : listaPropostas) {
            if(proposta.toString().contains(Ramos.SI.toString())) {
                conta++;
            }
        }
        return conta;
    }

    public int getADA() {
        int conta = 0;
        for(Aluno aluno : listaAlunos)
            if(aluno.getSiglaRamo().equalsIgnoreCase(Ramos.DA.toString()))
                conta++;
        return conta;
    }

    public int getPDA() {
        int conta=0;
        for(Proposta proposta : listaPropostas) {
            if(proposta.toString().contains(Ramos.DA.toString())) {
                conta++;
            }
        }
        return conta;
    }

    boolean PodeCandidatar(String projID,long num){
        for(Proposta p : listaPropostas){
            if(p.getNumAluno() == num)
                return false;
            if(p.getPid().equalsIgnoreCase(projID)){
                if(p.getNumAluno() != 0)
                    return false;
            }
        }
        return true;
    }

    public void addCandidatura(long numero, String projID) {
        boolean fez=false;
        boolean contem=false;
        Set<String> aux = new HashSet<>();

        if(PodeCandidatar(projID,numero)) {
            for (Candidatura c : listaCandidaturas) {
                if (c.getAlunoID() == numero) {
                    for (String s : c.getNumstr()) {
                        if (s.equalsIgnoreCase(projID)) {
                            return;
                        }
                    }
                    c.getNumstr().add(projID);
                    fez = true;
                }
            }
            if (!fez) {
                aux.add(projID);
                listaCandidaturas.add(new Candidatura(aux, numero));
            }
        }
    }

    public String listaCandidaturas() {
        StringBuilder sb = new StringBuilder("Alunos: ");
        if (listaCandidaturas ==null || listaCandidaturas.size()==0)
            sb.append("Não ha Candidaturas");
        else
            for(Candidatura c : listaCandidaturas)
                sb.append(String.format("\n %s",c.toString()));
        return sb.toString();
    }

    public boolean findCandidatura(long numAluno) {
        for(Candidatura c : listaCandidaturas){
            if(c.getAlunoID() == numAluno)
                return true;
        }
        return false;
    }

    public void remCandidatura(long numAluno) {
        for(Candidatura c : listaCandidaturas){
            if(c.getAlunoID() == numAluno) {
                listaCandidaturas.remove(c);
                return;
            }
        }
    }

    public void remCandidatura(long num, String id_da_proposta) {
        for(Candidatura c : listaCandidaturas){
            if(c.getAlunoID() == num)
                c.getNumstr().remove(id_da_proposta);
        }
    }

    public boolean addCandidatura(Candidatura cand) {
        boolean fez=false;
        boolean contem=false;

        if(PodeCandidatar("teste", cand.getAlunoID())) {
            for (Candidatura c : listaCandidaturas) {
                if (c.getAlunoID() == cand.getAlunoID()) {
                    for (String s : cand.getNumstr()) {
                        for(String ss : c.getNumstr())
                        if (s.equalsIgnoreCase(ss)) {
                            contem=true;
                        }
                        if(!contem){
                            c.getNumstr().add(s);
                        }
                        contem=false;
                    }
                    fez=true;
                }
            }
            if (!fez) {
                listaCandidaturas.add(cand);
                fez=true;
            }
        }else{
            return false;
        }
        return true;
    }
}
