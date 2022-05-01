package pt.isec.pa.apoio_poe.model.data.propostas;

import java.io.Serializable;

public class Projeto extends Proposta  {
    String siglasRamos;
    String titulo;
    String emailDocente;
    long numAluno;

    public Projeto(String siglasRamos, String titulo, String emailDocente) {
        super();
        this.siglasRamos = siglasRamos;
        this.titulo = titulo;
        this.emailDocente = emailDocente;
    }

    public Projeto(String siglasRamos, String titulo, String emailDocente,String pid) {
        this.Pid = pid;
        this.siglasRamos = siglasRamos;
        this.titulo = titulo;
        this.emailDocente = emailDocente;
    }

    public Projeto(String siglasRamos, String titulo, String emailDocente, long numAluno) {
        super();
        this.siglasRamos = siglasRamos;
        this.titulo = titulo;
        this.emailDocente = emailDocente;
        this.numAluno = numAluno;
    }

    public Projeto(String siglasRamos, String titulo, String emailDocente, long numAluno,String pid) {
        this.Pid = pid;
        this.siglasRamos = siglasRamos;
        this.titulo = titulo;
        this.emailDocente = emailDocente;
        this.numAluno = numAluno;
    }

    public String getSiglasRamos() {
        return siglasRamos;
    }

    public void setSiglasRamos(String siglasRamos) {
        this.siglasRamos = siglasRamos;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getEmailDocente() {
        return emailDocente;
    }

    public void setEmailDocente(String emailDocente) {
        this.emailDocente = emailDocente;
    }

    public long getNumAluno() {
        return numAluno;
    }

    public void setNumAluno(long numAluno) {
        this.numAluno = numAluno;
    }

    @Override
    public String toString() {
        return "T2" + ", Pid= " + Pid +
                ", siglasRamos= " + siglasRamos +
                ", titulo= " + titulo +
                ", emailDocente= " + emailDocente +
                ", numAluno=" + numAluno;
    }

    @Override
    public String toStringFile() {
        String str = "T2" + "," + Pid +
                "," + siglasRamos +
                "," + titulo +
                "," + emailDocente;
        if(numAluno != 0)
            str += ","+numAluno;
        return str;
    }
}
