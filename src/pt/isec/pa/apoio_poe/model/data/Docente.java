package pt.isec.pa.apoio_poe.model.data;

import java.io.Serializable;

public class Docente implements Serializable {
    String nome;
    String email;

    public Docente(String nome, String email) {
        this.nome = nome;
        this.email = email;
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

    @Override
    public String toString() {
        return "email= " + email +
                ", nome= " + nome ;
    }

    public String toStringFile() {
        return nome+","+email;
    }
}
