package pt.isec.pa.apoio_poe.model.data.propostas;

import java.io.Serializable;

public class Estagio extends Proposta {
    String siglasRamos;
    String titulo;
    String identificacao;
    long numAluno ;//caso esteja atribuido

    public Estagio(String siglasRamos, String titulo, String identificacao) {
        super();
        this.siglasRamos = siglasRamos;
        this.titulo = titulo;
        this.identificacao = identificacao;
    }

    public Estagio(String siglasRamos, String titulo, String identificacao,String pid) {
        this.Pid = pid;
        this.siglasRamos = siglasRamos;
        this.titulo = titulo;
        this.identificacao = identificacao;
    }

    public Estagio(String siglasRamos, String titulo, String identificacao, long numAluno) {
        super();
        this.siglasRamos = siglasRamos;
        this.titulo = titulo;
        this.identificacao = identificacao;
        this.numAluno = numAluno;
    }

    public Estagio(String siglasRamos, String titulo, String identificacao, long numAluno,String pid) {
        this.Pid = pid;
        this.siglasRamos = siglasRamos;
        this.titulo = titulo;
        this.identificacao = identificacao;
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

    public String getIdentificacao() {
        return identificacao;
    }

    public void setIdentificacao(String identificacao) {
        this.identificacao = identificacao;
    }

    public long getNumAluno() {
        return numAluno;
    }

    public void setNumAluno(long numAluno) {
        this.numAluno = numAluno;
    }

    @Override
    public String toString() {
        return "T1" + ",Pid= " + Pid +
                ", siglasRamos= " + siglasRamos +
                ", titulo= " + titulo +
                ", identificacao= " + identificacao +
                ", numAluno= " + numAluno ;
    }

    @Override
    public String toStringFile() {
        String str;
        str="T1" + "," + Pid +
                "," + siglasRamos +
                "," + titulo +
                "," + identificacao;
        if(numAluno != 0)
            str+="," + numAluno;
        return str;
    }
}
