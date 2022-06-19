package pt.isec.pa.apoio_poe.model.data;

import java.io.Serializable;
import java.util.List;
import java.util.Set;

public class Candidatura implements Serializable {
    Set<String> numstr;
    long alunoID;

    public Candidatura(Set<String> numstr, long alunoID) {
        this.numstr = numstr;
        this.alunoID = alunoID;
    }

    public Set<String> getNumstr() {
        return numstr;
    }

    public void setNumstr(Set<String> numstr) {
        this.numstr = numstr;
    }



    public void setAlunoID(long alunoID) {
        this.alunoID = alunoID;
    }

    public long getAlunoID() {
        return alunoID;
    }

    public boolean addProp(String numProp){
        numstr.add(numProp);
        return true;
    }

    @Override
    public String toString() {
        return "Aluno: "+alunoID+" para as Propostas"+getNumstrStrig();
    }

    private String getNumstrStrig() {
        StringBuilder sb = new StringBuilder();
        for(String s : numstr)
            sb.append(String.format(" %s ",s));

        return sb.toString();
    }

    public String toStringFile() {
        StringBuilder sb = new StringBuilder();
        for(String s : numstr)
            sb.append(String.format(",%s",s));
        return alunoID+sb.toString();
    }
}
