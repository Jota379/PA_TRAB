package pt.isec.pa.apoio_poe.model.data.propostas;

public class AutoProposta extends Proposta {
    String titulo;

    public AutoProposta(String titulo, long numAluno) {
        super();
        this.titulo = titulo;
        this.numAluno = numAluno;
    }

    public AutoProposta(String titulo, long numAluno,String pid) {
        this.Pid = pid;
        this.titulo = titulo;
        this.numAluno = numAluno;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    @Override
    public long getNumAluno() {
        return numAluno;
    }

    public void setNumAluno(long numAluno) {
        this.numAluno = numAluno;
    }

    @Override
    public String toString() {
        return "T3," + "Pid= " + Pid +
                ",titulo=" + titulo + ", numAluno= " + numAluno
                ;
    }

    @Override
    public String toStringFile() {
        return "T3," +Pid+
                "," + titulo + "," + numAluno;
    }
}
