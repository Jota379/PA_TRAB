package pt.isec.pa.apoio_poe.model.data;

import java.io.Serializable;

public class Aluno implements Serializable {
    long num;
    String nome;
    String email;
    Cursos siglaCurso;
    Ramos siglaRamo;
    double classificacao;
    boolean possibilidade;

    public Aluno(long num, String nome, String email, Cursos siglaCurso, Ramos siglaRamo,double classificacao,boolean possibilidade) {
        this.num = num;
        this.nome = nome;
        this.email = email;
        this.siglaCurso = siglaCurso;
        this.siglaRamo = siglaRamo;
        this.classificacao =classificacao;
        this.possibilidade = possibilidade;
    }

    public long getNum() {
        return num;
    }

    public void setNum(long num) {
        this.num = num;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSiglaCurso() {
        return siglaCurso.toString();
    }

    public void setSiglaCurso(Cursos siglaCurso) {
        this.siglaCurso = siglaCurso;
    }

    public String getSiglaRamo() {
        return siglaRamo.toString();
    }

    public void setSiglaRamo(Ramos siglaRamo) {
        this.siglaRamo = siglaRamo;
    }

    public double getClassificação() {
        return classificacao;
    }

    public void setClassificação(double classificação) {
        this.classificacao = classificação;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Aluno aluno = (Aluno) o;

        return num == aluno.num;
    }

    @Override
    public int hashCode() {
        return (int) (num ^ (num >>> 32));
    }

    @Override
    public String toString() {
        return "num=" + num +
                ", nome=" + nome +
                ", email=" + email +
                ", siglaCurso=" + siglaCurso +
                ", siglaRamo=" + siglaRamo +
                ", classificação=" + classificacao +
                ", possibilidade=" + possibilidade;
    }

    public double getClassificacao() {
        return classificacao;
    }

    public void setClassificacao(double classificacao) {
        this.classificacao = classificacao;
    }

    public boolean isPossibilidade() {
        return possibilidade;
    }

    public void setPossibilidade(boolean possibilidade) {
        this.possibilidade = possibilidade;
    }

    public String toStringFile() {
        return num +","+nome +
                "," + email +
                "," + siglaCurso +
                "," + siglaRamo +
                "," + classificacao +
                "," + possibilidade;
    }
}
